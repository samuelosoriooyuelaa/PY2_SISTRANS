package uniandes.edu.co.proyecto.controller;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import uniandes.edu.co.proyecto.modelo.Cotizante;
import uniandes.edu.co.proyecto.modelo.CotizantePK;
import uniandes.edu.co.proyecto.repository.CotizanteRepository;

public class CotizanteController {

    @Autowired
    private CotizanteRepository cotizanteRepository;

    //obtener los cotizantes 
    @GetMapping("/cotizantes")
    public ResponseEntity<Collection<Cotizante>> cotizantes(){
        try {
            Collection<Cotizante> cotizantes = cotizanteRepository.darAllCotizantes();
            return ResponseEntity.ok(cotizantes);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //insertar cotizante
    @PostMapping("/cotizantes/new/save")
    public ResponseEntity<?> crearRelacionCotizante(@RequestBody Cotizante request) {
        try {

            CotizantePK pk = request.getPk();
        
            if (pk.getId_cotizante() == null || pk.getId_beneficiario() == null) {
                return ResponseEntity.badRequest().body("Los IDs de cotizante y beneficiario son obligatorios");
            }
            
            if (pk.getParentesco() == null || pk.getParentesco().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("El parentesco es obligatorio");
            }

            
            if (pk.getId_cotizante().equals(pk.getId_beneficiario())) {
                return ResponseEntity.badRequest().body("Un afiliado no puede ser cotizante y beneficiario de sí mismo");
            }

            Integer id_cotizante = pk.getId_cotizante().getId();
            Integer id_beneficiario = pk.getId_beneficiario().getId();


            cotizanteRepository.insertarCotizante(
                id_cotizante,
                id_beneficiario,
                pk.getParentesco()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Relación cotizante-beneficiario creada exitosamente");
            
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body("Error: Posiblemente uno de los afiliados no existe o la relación ya existe");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                   .body("Error al crear la relación: " + e.getMessage());
        }
    }


}

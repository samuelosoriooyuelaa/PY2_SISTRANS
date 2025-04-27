package uniandes.edu.co.proyecto.controller;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<?> crearCotizante(@RequestBody Cotizante cotizante) {
        try {
            // Validar que los IDs y parentesco estén presentes
            CotizantePK pk = cotizante.getPk();
            if (cotizante.getPk() == null || 
                pk.getId_cotizante() == null || 
                pk.getId_beneficiario() == null ||
                pk.getParentesco() == null) {
                return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
            }
            
            cotizanteRepository.insertarCotizante(
                pk.getId_cotizante().getId(),
                pk.getId_beneficiario().getId(),
                pk.getParentesco()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED)
                   .body("Relación cotizante-beneficiario creada exitosamente");
                   
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body("Error: Verifica que los IDs existen y son del tipo correcto");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                   .body("Error al crear relación: " + e.getMessage());
        }
    }

}

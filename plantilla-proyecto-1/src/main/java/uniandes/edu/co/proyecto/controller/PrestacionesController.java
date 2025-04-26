package uniandes.edu.co.proyecto.controller;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Prestacion;
import uniandes.edu.co.proyecto.repository.PrestacionRepository;


@RestController
public class PrestacionesController {
    @Autowired
    private PrestacionRepository prestacionRepository;

    //obtener todas las prestaciones
    @GetMapping("/prestaciones")
    public ResponseEntity<Collection<Prestacion>> prestaciones(){
        try {
            Collection<Prestacion> prestaciones = prestacionRepository.darPrestaciones();
            return ResponseEntity.ok(prestaciones);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //insertar prestacion RF8
    @PostMapping("/prestaciones/new/save")
    public ResponseEntity<?> crearPrestacion(@RequestBody Prestacion prestacion) {
        try {
            // Validaciones básicas
            if (prestacion.getId_afiliado() == null || prestacion.getId_afiliado().getId() == null) {
                return ResponseEntity.badRequest().body("El afiliado es obligatorio");
            }
            
            if (prestacion.getId_ips() == null || prestacion.getId_ips().getId() == null) {
                return ResponseEntity.badRequest().body("La IPS es obligatoria");
            }
            
            if (prestacion.getId_serviciosalud() == null || prestacion.getId_serviciosalud().getId() == null) {
                return ResponseEntity.badRequest().body("El servicio de salud es obligatorio");
            }
            
            if (prestacion.getFechaHoraInicio() == null) {
                return ResponseEntity.badRequest().body("La fecha/hora de inicio es obligatoria");
            }
            
            if (prestacion.getFechaHoraFinal() == null) {
                return ResponseEntity.badRequest().body("La fecha/hora final es obligatoria");
            }
            
            
            if (prestacion.getFechaHoraFinal().isBefore(prestacion.getFechaHoraInicio())) {
                return ResponseEntity.badRequest().body("La fecha final debe ser posterior a la fecha inicial");
            }

           
            prestacionRepository.insertarPrestacion(
                prestacion.getId_afiliado().getId(),
                prestacion.getId_ips().getId(),
                prestacion.getId_serviciosalud().getId(),
                prestacion.getFechaHoraInicio(),
                prestacion.getFechaHoraFinal()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Prestación creada exitosamente");
            
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body("Error: Posible violación de integridad - " + e.getMostSpecificCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                   .body("Error al crear la prestación: " + e.getMessage());
        }
    }






}

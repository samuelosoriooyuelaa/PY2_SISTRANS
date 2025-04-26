package uniandes.edu.co.proyecto.controller;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.OrdenServicio;
import uniandes.edu.co.proyecto.repository.OrdenServicioRepository;

@RestController
public class OrdenServicioController {

    private OrdenServicioRepository ordenServicioRepository;


    //obtener las ordenes servicio
    
    @GetMapping("/orden-servicio")
    public ResponseEntity<Collection<OrdenServicio>> ordenServicios() {
        try {
            Collection<OrdenServicio> ordenes = ordenServicioRepository.darOrdenServicios();
            return ResponseEntity.ok(ordenes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //insertar una orden servicio RF6
    @PostMapping("/orden-servicio/new/save")
    public ResponseEntity<?> crearOrdenServicio(@RequestBody OrdenServicio orden) {
        try {
            if (orden.getId_medico() == null || orden.getId_medico().getId() == null) {
                return ResponseEntity.badRequest().body("El médico es obligatorio");
            }
            
            if (orden.getId_afiliado() == null || orden.getId_afiliado().getId() == null) {
                return ResponseEntity.badRequest().body("El afiliado es obligatorio");
            }
            
            if (orden.getId_serviciosalud() == null || orden.getId_serviciosalud().getId() == null) {
                return ResponseEntity.badRequest().body("El servicio de salud es obligatorio");
            }
            
            if (orden.getFecha() == null) {
                return ResponseEntity.badRequest().body("La fecha es obligatoria");
            }
            
            if (orden.getEstadoOrden() == null) {
                return ResponseEntity.badRequest().body("El estado es obligatorio. Valores permitidos: " + 
                    OrdenServicio.EstadoOrden.values());
            }
            ordenServicioRepository.insertarOrdenServicio(
                orden.getId_medico().getId(),
                orden.getId_afiliado().getId(),
                orden.getId_serviciosalud().getId(),
                orden.getFecha(),
                orden.getEstadoOrden().toString()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Orden de servicio creada exitosamente");
            
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body("Error: Posible violación de integridad - " + e.getMostSpecificCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                   .body("Error al crear la orden de servicio: " + e.getMessage());
        }
    }






}

package uniandes.edu.co.proyecto.controller;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.interfaz.ServiciosMasSolicitadosProjection;
import uniandes.edu.co.proyecto.interfaz.UtilizacionServiciosProjection;
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
            
            if (prestacion.getFecha_Hora_Inicio() == null) {
                return ResponseEntity.badRequest().body("La fecha/hora de inicio es obligatoria");
            }
            
            if (prestacion.getFecha_Hora_Final() == null) {
                return ResponseEntity.badRequest().body("La fecha/hora final es obligatoria");
            }
            
            
            if (prestacion.getFecha_Hora_Final().isBefore(prestacion.getFecha_Hora_Inicio())) {
                return ResponseEntity.badRequest().body("La fecha final debe ser posterior a la fecha inicial");
            }

           
            prestacionRepository.insertarPrestacion(
                prestacion.getId_afiliado().getId(),
                prestacion.getId_ips().getId(),
                prestacion.getId_serviciosalud().getId(),
                prestacion.getFecha_Hora_Inicio(),
                prestacion.getFecha_Hora_Final()
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

//rfc2
    @GetMapping("/prestaciones/servicios-mas-solicitados")
public ResponseEntity<?> getTop3ServiciosMasSolicitados(
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
    
    try {
        if (fechaFin.isBefore(fechaInicio)) {
            return ResponseEntity.badRequest().body("La fecha final debe ser posterior a la fecha inicial");
        }

        List<ServiciosMasSolicitadosProjection> resultados = 
            prestacionRepository.findTop3ServiciosMasSolicitados(fechaInicio, fechaFin);
        
        if (resultados.isEmpty()) {
            return ResponseEntity.ok("No hay prestaciones registradas en el período especificado");
        }
        
        return ResponseEntity.ok(resultados);
        
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
               .body("Error al consultar servicios más solicitados: " + e.getMessage());
    }
}


//rfc4
@GetMapping("/prestaciones/utilizacion-afiliado/{idAfiliado}")
public ResponseEntity<?> getUtilizacionByAfiliado(
    @PathVariable Integer idAfiliado,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
    
    try {
        if (fechaFin.isBefore(fechaInicio)) {
            return ResponseEntity.badRequest().body("La fecha final debe ser posterior a la fecha inicial");
        }

        List<UtilizacionServiciosProjection> utilizacion = 
            prestacionRepository.findUtilizacionByAfiliado(idAfiliado, fechaInicio, fechaFin);
        
        if (utilizacion.isEmpty()) {
            return ResponseEntity.ok("El afiliado no tiene servicios registrados en el período especificado");
        }
        
        return ResponseEntity.ok(utilizacion);
        
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
               .body("Error al consultar utilización de servicios: " + e.getMessage());
    }
}

   


    






}

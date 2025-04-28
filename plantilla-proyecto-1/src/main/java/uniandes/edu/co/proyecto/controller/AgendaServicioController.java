package uniandes.edu.co.proyecto.controller;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import jakarta.persistence.QueryTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.interfaz.DisponibilidadServicioProjection;
import uniandes.edu.co.proyecto.interfaz.IndiceUsoServicioProjection;
import uniandes.edu.co.proyecto.modelo.AgendaServicio;
import uniandes.edu.co.proyecto.repository.AgendaServicioRepository;


@RestController
public class AgendaServicioController {

    @Autowired
    private AgendaServicioRepository agendaServicioRepository;

    //obtener los agenda servicio
    @GetMapping("/agenda-servicio")
    public ResponseEntity<Collection<AgendaServicio>> agendas(){
        try {
            Collection<AgendaServicio> agendaservicios = agendaServicioRepository.darAgendaServicios();
            return ResponseEntity.ok(agendaservicios);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //insertar agenda servicio (sin afiliado sin orden)
    @PostMapping("/agenda-servicio/new/save")
    public ResponseEntity<?> crearAgendaServicio(@RequestBody AgendaServicio agenda) {
        try {
            
            if (agenda.getId_medico() == null) {
                return ResponseEntity.badRequest().body("El médico es obligatorio");
            }
            
            if (agenda.getId_serviciosalud() == null) {
                return ResponseEntity.badRequest().body("El servicio de salud es obligatorio");
            }
            
            if (agenda.getId_ips() == null) {
                return ResponseEntity.badRequest().body("La IPS es obligatoria");
            }
            
            if (agenda.getFechaHora() == null) {
                return ResponseEntity.badRequest().body("La fecha y hora son obligatorias");
            }
            agendaServicioRepository.insertarAgendaServicio(
                agenda.getId_medico().getId(),
                agenda.getId_serviciosalud().getId(),
                agenda.getId_ips().getId(),
                agenda.getFechaHora()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body("Agenda de servicio creada exitosamente");
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                   .body("Error al crear la agenda de servicio: " + e.getMessage());
        }
    }




    //insertar agenda servicio (afiliado reserva su srvicio)
    @PutMapping("/agenda-servicio/{id}/reservar")  
    public ResponseEntity<?> reservarAgenda(
            @PathVariable Integer id,
            @RequestBody Map<String, Integer> request) {
        
        try {
            
            if (request.get("idAfiliado") == null || request.get("idOrdenServicio") == null) {
                return ResponseEntity.badRequest().body("Se requieren idAfiliado e idOrdenServicio");
            }

           
            int updatedRows = agendaServicioRepository.AgendarServicio(
                id,
                request.get("idAfiliado"),
                request.get("idOrdenServicio")
            );

            if (updatedRows == 0) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                       .body("No se pudo reservar. La agenda puede no existir o ya estar reservada");
            }

            return ResponseEntity.ok("Agenda reservada exitosamente");

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                   .body("Error al reservar agenda: " + e.getMessage());
        }
    }


//rfc1
@GetMapping("/agenda-servicio/disponibilidad/{idServicio}")
public ResponseEntity<?> consultarDisponibilidadServicio(
    @PathVariable Integer idServicio) {
    
    try {
        LocalDateTime fechaActual = LocalDateTime.now();
        LocalDateTime fechaFin = fechaActual.plusWeeks(4);
        
        List<DisponibilidadServicioProjection> disponibilidad = 
            agendaServicioRepository.findDisponibilidadByServicio(
                idServicio, 
                fechaActual, 
                fechaFin);
        
        if (disponibilidad.isEmpty()) {
            return ResponseEntity.ok("No hay disponibilidad para este servicio en las próximas 4 semanas");
        }
        
        return ResponseEntity.ok(disponibilidad);
        
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
               .body("Error al consultar disponibilidad: " + e.getMessage());
    }
}

//rfc3
@GetMapping("/agenda-servicio/indice-uso")
public ResponseEntity<?> getIndiceUsoServicios(
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
    
    try {
        if (fechaFin.isBefore(fechaInicio)) {
            return ResponseEntity.badRequest().body("La fecha final debe ser posterior a la fecha inicial");
        }

        List<IndiceUsoServicioProjection> indices = 
            agendaServicioRepository.calcularIndiceUsoServicios(fechaInicio, fechaFin);
        
        if (indices.isEmpty()) {
            return ResponseEntity.ok("No hay datos disponibles para el período especificado");
        }
        
        return ResponseEntity.ok(indices);
        
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
               .body("Error al calcular índices de uso: " + e.getMessage());
    }
}


@GetMapping("/agenda-servicio/disponibilidad-read-committed-timeout/{idServicio}")
public ResponseEntity<?> consultarDisponibilidadServicioReadCommittedWithTimeout(
    @PathVariable Integer idServicio,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
    
    try {
        LocalDateTime fechaInicioConsulta = fechaInicio != null ? fechaInicio : LocalDateTime.now();
        LocalDateTime fechaFinConsulta = fechaFin != null ? fechaFin : fechaInicioConsulta.plusWeeks(4);
        
        if (fechaFinConsulta.isBefore(fechaInicioConsulta)) {
            return ResponseEntity.badRequest().body("La fecha final debe ser posterior a la fecha inicial");
        }
        
        List<DisponibilidadServicioProjection> disponibilidad = 
            agendaServicioRepository.findDisponibilidadByServicioReadCommittedWithTimeout(
                idServicio, fechaInicioConsulta, fechaFinConsulta);
        
        if (disponibilidad.isEmpty()) {
            return ResponseEntity.ok("No hay disponibilidad para este servicio en el período especificado");
        }
        
        return ResponseEntity.ok(disponibilidad);
        
    } catch (QueryTimeoutException e) {
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
               .body("La consulta ha excedido el tiempo máximo de 30 segundos");
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
               .body("Error al consultar disponibilidad: " + e.getMessage());
    }
}










}






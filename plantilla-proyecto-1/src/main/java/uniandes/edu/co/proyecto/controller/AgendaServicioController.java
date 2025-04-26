package uniandes.edu.co.proyecto.controller;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.AgendaReservaRequest;
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
    @GetMapping("/agenda-servicio/{id}/disponible")
    public ResponseEntity<?> verificarDisponibilidad(@PathVariable Integer id) {
        try {
             AgendaServicio agenda = agendaServicioRepository.darAgendaServicio(id);
        
             if (agenda == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la agenda con ID: " + id);
        }
        
              if (agenda.getId_afiliado() != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("La agenda ya está reservada");
        }
        
        return ResponseEntity.ok(Map.of(
            "disponible", true,
            "mensaje", "Agenda disponible para reserva",
            "detalles", Map.of(
                "medico", agenda.getId_medico().getNombre(),
                "servicio", agenda.getId_serviciosalud().getNombre(),
                "fechaHora", agenda.getFechaHora()
            )
        ));
        
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
               .body("Error al verificar disponibilidad: " + e.getMessage());
    }
}

@PutMapping("/agenda-servicio/{id}/reservar")
public ResponseEntity<?> reservarAgendaServicio(
        @PathVariable Integer id,
        @RequestBody AgendaReservaRequest request)
         {
           try {
        
        if (request.getId_afiliado() == null || request.getId_ordenservicio() == null) {
            return ResponseEntity.badRequest().body("Todos los campos son obligatorios");
        }

        
        AgendaServicio agenda = agendaServicioRepository.darAgendaServicio(id);
        if (agenda == null) {
            return ResponseEntity.notFound().build();
        }
        if (agenda.getId_afiliado() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body("Esta agenda ya está reservada");
        }

        
        int updated = agendaServicioRepository.AgendarServicio(
            id,
            request.getId_afiliado(),
            request.getId_ordenservicio()
        );

        return updated > 0 
            ? ResponseEntity.ok("Reserva realizada con éxito") 
            : ResponseEntity.internalServerError().body("Error al procesar la reserva");
            
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
               .body("Error: " + e.getMessage());
    }
}







}






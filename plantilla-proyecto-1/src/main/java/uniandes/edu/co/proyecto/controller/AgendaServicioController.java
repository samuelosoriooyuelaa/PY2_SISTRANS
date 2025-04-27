package uniandes.edu.co.proyecto.controller;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
@GetMapping("/agenda-servicio/{idServicio}/disponibilidad")
public ResponseEntity<List<DisponibilidadServicioProjection>> getDisponibilidad(
        @PathVariable Integer idServicio,
        @RequestParam(defaultValue = "#{T(java.time.LocalDateTime).now()}") LocalDateTime inicio,
        @RequestParam(defaultValue = "#{T(java.time.LocalDateTime).now().plusWeeks(4)}") LocalDateTime fin) {
    
    return ResponseEntity.ok(
        agendaServicioRepository.findDisponibilidad(idServicio, inicio, fin)
    );
}

//rfc3
@GetMapping("/agenda-servicio/indice-uso")
public ResponseEntity<?> getIndiceUsoServicios(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
    
    
    if (fechaInicio.isAfter(fechaFin)) {
        return ResponseEntity.badRequest()
               .body("La fecha de inicio no puede ser posterior a la fecha final");
    }

    try {
        List<IndiceUsoServicioProjection> indices = 
            agendaServicioRepository.calcularIndiceUsoServicios(fechaInicio, fechaFin);
        
        return ResponseEntity.ok(indices);
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
               .body("Error al calcular índices: " + e.getMessage());
    }
}










}






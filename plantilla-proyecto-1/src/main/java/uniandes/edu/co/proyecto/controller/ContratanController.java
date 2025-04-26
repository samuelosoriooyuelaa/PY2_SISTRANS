package uniandes.edu.co.proyecto.controller;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Contratan;
import uniandes.edu.co.proyecto.modelo.ContratanPK;
import uniandes.edu.co.proyecto.repository.ContratanRepository;

@RestController
public class ContratanController {
    @Autowired
    private ContratanRepository contratanRepository;

    //obtener todos los contratan 
    @GetMapping("/contratan")
    public ResponseEntity<Collection<Contratan>> Contratar(){
        try {
            Collection<Contratan> contratan = contratanRepository.darAllContratan();
            return ResponseEntity.ok(contratan);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //insertar un contratan
    @PostMapping("/contratan/new/save")
    public ResponseEntity <String> crearContratan(@RequestBody Contratan request) {

        try {
            ContratanPK pk= request.getPk();
            if (pk.getId_ips() == null || pk.getId_medico() == null) {
                return ResponseEntity.badRequest().body("Se requieren id_ips e id_medico");
            }

            Integer id_ips = pk.getId_ips().getId();
            Integer id_medico = pk.getId_medico().getId();



            contratanRepository.insertarContratan(id_ips, id_medico);
    
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Relación IPS-Medico creada exitosamente");
    
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error al crear relación: " + e.getMessage());
        }

    }



}

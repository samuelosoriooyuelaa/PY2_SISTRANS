package uniandes.edu.co.proyecto.controller;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Prestan;
import uniandes.edu.co.proyecto.modelo.PrestanPK;
import uniandes.edu.co.proyecto.repository.PrestanRepository;

@RestController
public class PrestanController {
    @Autowired
    private PrestanRepository prestanRepository;

    //obtener todos los prestan
    @GetMapping("/prestan")
    public ResponseEntity<Collection<Prestan>> Prestan(){
        try {
            Collection<Prestan> prestan = prestanRepository.darAllPrestan();
            return ResponseEntity.ok(prestan);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //insertar prestan RF3
    @PostMapping("/prestan/new/save")
    public ResponseEntity <String> crearPrestan(@RequestBody Prestan request) {

        try {
            PrestanPK pk= request.getPk();
            if (pk.getId_ips() == null || pk.getId_serviciosalud() == null) {
                return ResponseEntity.badRequest().body("Se requieren id_ips e id_serviciosalud");
            }

            Integer id_ips = pk.getId_ips().getId();
            Integer id_serviciosalud = pk.getId_serviciosalud().getId();



            prestanRepository.insertarPrestan(
                id_ips,
                id_serviciosalud
            );
    
            return ResponseEntity.status(HttpStatus.CREATED)
                .body("Relación IPS-Servicio creada exitosamente");
    
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Error al crear relación: " + e.getMessage());
        }

    }

    






}

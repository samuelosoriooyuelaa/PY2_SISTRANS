package uniandes.edu.co.proyecto.controller;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.IPS;
import uniandes.edu.co.proyecto.repository.IPSRepository;


@RestController
public class IPSController {
    @Autowired
    private IPSRepository ipsRepository;

    //obtener todas las ips
    @GetMapping("/ipss")
    public ResponseEntity<Collection<IPS>> ipss(){
        try {
            Collection<IPS> ipss = ipsRepository.darIPSS();
            return ResponseEntity.ok(ipss);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //insertar ips RF1
    @PostMapping("/ipss/new/save")
    public ResponseEntity<?> ipsGuardar(@RequestBody IPS ips){
        try {
            ipsRepository.insertarIPS(ips.getNombre(), ips.getNit(), ips.getDireccion(), ips.getTelefono());
            return ResponseEntity.status(HttpStatus.CREATED).body("IPS creada exitosamente ");
        } catch (Exception e){
            return new ResponseEntity<>("error al crear la entidad", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //actualizar ips 
     @PostMapping("/ipss/{id}/edit/save")
    public ResponseEntity<?> ipsActualizar(@PathVariable("id") Integer id, @RequestBody IPS ips) {
        try {
            ipsRepository.actualizarIPS(id, ips.getNombre(), ips.getNit(), ips.getDireccion(), ips.getTelefono());
            return ResponseEntity.ok("IPS actualizada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al editar la IPS", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //eliminar ips 
    @GetMapping("/ipss/{id}/delete")
    public ResponseEntity<?> bebidaBorrar(@PathVariable("id") Integer id) {
        try {
            ipsRepository.eliminarIPS(id);
            return ResponseEntity.ok("ips eliminada exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la ips", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    


}

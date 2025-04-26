package uniandes.edu.co.proyecto.controller;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.ServicioSalud;
import uniandes.edu.co.proyecto.repository.ServicioSaludRepository;

@RestController
public class ServicioSaludController {

    @Autowired
    private ServicioSaludRepository servicioSaludRepository;

    //obtener todos servicios salud
    @GetMapping("/servicios-salud")
    public ResponseEntity<Collection<ServicioSalud>> serviciosSalud() {
        try {
            Collection<ServicioSalud> servicios = servicioSaludRepository.darServiciosSalud();
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //insertar servicio salud RF2
    @PostMapping("/servicios-salud/new/save")
    public ResponseEntity<String> servicioGuardar(@RequestBody ServicioSalud request) {
        try{
        if (request.getNombre() == null) {
            return ResponseEntity.badRequest().body("El campo 'nombre' es obligatorio");
        }

        try {
            ServicioSalud.TipoServicio.valueOf(request.getNombre().name());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Tipo de servicio inválido. Los valores permitidos son: " + 
                Arrays.toString(ServicioSalud.TipoServicio.values()));
        }

        servicioSaludRepository.insertarServicioSalud(request.getNombre().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body("servicio creado exitosamente");


    } catch (org.springframework.dao.DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
               .body("Error: los campos son obligatorios o estan invalidos");
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
               .body("Error al crear servicio: " + e.getMessage());
    }


        

   







        
        
    }

    //Eliminar servicio salud
    @GetMapping("/servicio-salud/{id}/delete")
    public ResponseEntity<String> barEliminar(@PathVariable("id") Integer id) {
        try {
            servicioSaludRepository.eliminarServicioSalud(id);
            return new ResponseEntity<>("Servicio salud eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el servicio salud", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

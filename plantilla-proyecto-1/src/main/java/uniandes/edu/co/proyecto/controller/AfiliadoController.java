package uniandes.edu.co.proyecto.controller;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Afiliado;
import uniandes.edu.co.proyecto.repository.AfiliadoRepository;



@RestController
public class AfiliadoController { 

    @Autowired
    private AfiliadoRepository afiliadoRepository;

    //obtener todos los afiliados
    @GetMapping("/afiliados")
    public ResponseEntity<Collection<Afiliado>> Afiliados(){
        try {
            Collection<Afiliado> afiliados = afiliadoRepository.darAfiliados();
            return ResponseEntity.ok(afiliados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //insertar afiliado RF5


    @PostMapping("/afiliados/new/save")
    public ResponseEntity<?> afiliadosGuardar(@RequestBody Afiliado afiliado) {
       try {
        if (afiliado.getNumeroDocumento() == null || afiliado.getNumeroDocumento().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El número de documento es obligatorio");
        }
        if (afiliado.getTipo() == null) {
            return ResponseEntity.badRequest().body("El tipo de afiliado es obligatorio");
        }

        try {
            Afiliado.TipoAfiliado.valueOf(afiliado.getTipo().name());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Tipo de afiliado inválido. Los valores permitidos son: " + 
            Arrays.toString(Afiliado.TipoAfiliado.values()));
        }

        afiliadoRepository.insertarAfiliado(
            afiliado.getNombre(),
            afiliado.getTipoDocumento(),
            afiliado.getNumeroDocumento(),
            afiliado.getFechaNacimiento(),
            afiliado.getDireccion(),
            afiliado.getTelefono(),
            afiliado.getTipo().toString() 

        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Afiliado creado exitosamente");
        
    } catch (org.springframework.dao.DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
               .body("Error: El número de documento ya existe o hay datos inválidos");
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
               .body("Error al crear afiliado: " + e.getMessage());
    }
}
    



















}

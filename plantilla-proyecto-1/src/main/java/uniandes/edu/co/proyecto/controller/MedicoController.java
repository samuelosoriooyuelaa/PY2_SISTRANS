package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.Medico;
import uniandes.edu.co.proyecto.repository.MedicoRepository;

@RestController
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    //obtener los medicos
    @GetMapping("/medicos")
    public ResponseEntity<Collection<Medico>> medicos(){
        try {
            Collection<Medico> medicos = medicoRepository.darMedicos();
            return ResponseEntity.ok(medicos);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //insertar un medico RF4
    @PostMapping("/medicos/new/save")
    public ResponseEntity<?> medicosGuardar(@RequestBody Medico medico){
        try {

            if (medico.getNombre() == null || medico.getNombre().trim().isEmpty() ||
            medico.getNumeroDocumento() == null || medico.getNumeroDocumento().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nombre y número de documento son obligatorios");
        }

            medicoRepository.insertarMedico(medico.getNombre(), medico.getTipoDocumento(), medico.getNumeroDocumento(), medico.getRegistroMedico(), medico.getEspecialidad());
            return ResponseEntity.status(HttpStatus.CREATED).body("medico creado exitosamente ");
        } catch (Exception e){
            return new ResponseEntity<>("error al crear medico", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }








}

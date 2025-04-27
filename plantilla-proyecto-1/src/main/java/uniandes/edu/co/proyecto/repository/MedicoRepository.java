package uniandes.edu.co.proyecto.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.IPS;
import uniandes.edu.co.proyecto.modelo.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    //obtener todos los medicos
    @Query(value="SELECT * FROM medico", nativeQuery=true)
    Collection<Medico> darMedicos();

    //obtener un medico por su id
    @Query(value = "SELECT * FROM medico WHERE id = :id", nativeQuery = true)
    IPS darMedico(@Param("id") Integer id);

    //insertar un medico RF4
    @Modifying
    @Transactional
    @Query(value="INSERT INTO medico (nombre, tipo_documento, numero_documento, registro_medico, especialidad) VALUES (:nombre, :tipo_documento, :numero_documento, :registro_medico, :especialidad)", nativeQuery=true)
    void insertarMedico(@Param("nombre") String nombre, @Param("tipo_documento") String tipo_documento, @Param("numero_documento") String numero_documento,
    @Param("registro_medico") String registro_medico, @Param("especialidad") String especialidad);

    //actualizar un medico 
    @Modifying
    @Transactional
    @Query(value="UPDATE medico SET nombre = :nombre, tipo_documento = :tipo_documento, numero_documento = :numero_documento, registro_medico = :registro_medico, especialidad = :especialidad  WHERE id = :id", nativeQuery=true)
    void actualizarMedico(@Param("id") Integer id, @Param("nombre") String nombre, @Param("tipo_documento") String tipo_documento, @Param("numero_documento") String numero_documento, @Param("registro_medico") String registro_medico, @Param("especialidad") String especialidad);

    //Eliminar un medico
    @Modifying
    @Transactional
    @Query(value="DELETE FROM medico WHERE id =:id", nativeQuery=true)
    void eliminarMedico(@Param("id") Integer id);











}

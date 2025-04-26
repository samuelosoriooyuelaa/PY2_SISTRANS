package uniandes.edu.co.proyecto.repository;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Prestacion;

public interface PrestacionRepository extends JpaRepository<Prestacion, Integer>{
    //obtener todas las prestaciones 
    @Query(value="SELECT * FROM prestacion", nativeQuery=true)
    Collection<Prestacion> darPrestaciones();

    //obtener orden servicio por id
    @Query(value = "SELECT * FROM ordenservicio WHERE id = :id", nativeQuery = true)
    Prestacion darPrestacion(@Param("id") Integer id);

    //Insertar una prestacion RF8
    @Modifying
    @Transactional
    @Query(value="INSERT INTO prestacion (id, id_afiliado, id_ips, id_serviciosalud, fechaHoraInicio, fechaHoraFinal) VALUES (eps_sequence.nextval, :id_afiliado, :id_ips, :id_serviciosalud, :fechaHoraInicio, :fechaHoraFinal)", nativeQuery=true)
    void insertarPrestacion(@Param("id_afiliado") Integer id_afiliado, @Param("id_ips") Integer id_ips, @Param("id_serviciosalud") Integer id_serviciosalud,
    @Param("fechaHoraInicio") LocalDateTime fechaHoraInicio, @Param("fechaHoraFinal") LocalDateTime fechaHoraFinal);

    //actualizar una prestacion 
    @Modifying
    @Transactional
    @Query(value="UPDATE prestacion SET id_afiliado = :id_afiliado, id_ips = :id_ips, id_serviciosalud = :id_serviciosalud, fechaHoraInicio = :fechaHoraInicio, fechaHoraFinal = :fechaHoraFinal  WHERE id = :id", nativeQuery=true)
    void actualizarPrestacion(@Param("id") Integer id, @Param("id_afiliado") Integer id_afiliado, @Param("id_ips") Integer id_ips, @Param("id_serviciosalud") Integer id_serviciosalud, @Param("fechaHoraInicio") LocalDateTime fechaHoraInicio, @Param("fechaHoraFinal") LocalDateTime fechaHoraFinal);

    //Eliminar una prestacion
    @Modifying
    @Transactional
    @Query(value="DELETE FROM prestacion WHERE id =:id", nativeQuery=true)
    void eliminarPrestacion(@Param("id") Integer id);
    



    




}

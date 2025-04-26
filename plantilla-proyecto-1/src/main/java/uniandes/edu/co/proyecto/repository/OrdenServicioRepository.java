package uniandes.edu.co.proyecto.repository;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OrdenServicio;

public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Integer>{

    //obtener todas las orden servicio
    @Query(value="SELECT * FROM ordenservicio", nativeQuery=true)
    Collection<OrdenServicio> darOrdenServicios();

    //obtener orden servicio por id
    @Query(value = "SELECT * FROM ordenservicio WHERE id = :id", nativeQuery = true)
    OrdenServicio darOrdenServicio(@Param("id") Integer id);

    //insertar un orden servicio RF6
    @Modifying
    @Transactional
    @Query(value="INSERT INTO ordenservicio (id, id_medico, id_afiliado, id_serviciosalud, fecha, estado) VALUES (eps_sequence.nextval, :id_medico, :id_afiliado, :id_serviciosalud, :fecha, :estado)", nativeQuery=true)
    void insertarOrdenServicio(@Param("id_medico") Integer id_medico, @Param("id_afiliado") Integer id_afiliado, @Param("id_serviciosalud") Integer id_serviciosalud,
    @Param("fecha") Date fecha, @Param("estado") String estado);

    //actualizar una orden servicio
    @Modifying
    @Transactional
    @Query(value="UPDATE ordenservicio SET id_medico = :id_medico, id_afiliado = :id_afiliado, id_serviciosalud = :id_serviciosalud, fecha = :fecha, estado = :estado  WHERE id = :id", nativeQuery=true)
    void actualizarOrdenServicio(@Param("id") Integer id, @Param("id_medico") Integer id_medico, @Param("id_afiliado") Integer id_afiliado, @Param("id_serviciosalud") Integer id_serviciosalud, @Param("fecha") Date fecha, @Param("estado") String estado);

    //eliminar orden servicio
    @Modifying
    @Transactional
    @Query(value="DELETE FROM ordenservicio WHERE id =:id", nativeQuery=true)
    void eliminarOrdenServicio(@Param("id") Integer id);






    







}

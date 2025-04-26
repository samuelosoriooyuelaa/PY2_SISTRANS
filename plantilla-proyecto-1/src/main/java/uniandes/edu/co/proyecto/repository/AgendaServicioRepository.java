package uniandes.edu.co.proyecto.repository;
import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.AgendaServicio;

public interface AgendaServicioRepository extends JpaRepository<AgendaServicio, Integer> {

    //obtener todos los agenda servicio
    @Query(value="SELECT * FROM agendaservicio", nativeQuery=true)
    Collection<AgendaServicio> darAgendaServicios();

    //obtener agenda servicio por id
    @Query(value = "SELECT * FROM agendaservicio WHERE id = :id", nativeQuery = true)
    AgendaServicio darAgendaServicio(@Param("id") Integer id);

    //insertar agenda servicio RF7.1 vacía
    @Modifying
    @Transactional
    @Query(value="INSERT INTO agendaservicio (id, id_medico, id_serviciosalud, id_ips, fechaHora, id_afiliado, id_ordenservicio) VALUES (eps_sequence.nextval, :id_medico, :id_serviciosalud, :id_ips, :fechaHora, NULL, NULL)", nativeQuery=true)
    void insertarAgendaServicio(@Param("id_medico") Integer id_medico, @Param("id_serviciosalud") Integer id_serviciosalud, @Param("id_ips") Integer id_ips, @Param("fechaHora") LocalDateTime fechaHora);

    //insertar agenda servicio RF7.2 agendamiento
    @Modifying
    @Transactional
    @Query(value="UPDATE agendaservicio SET id_afiliado = :id_afiliado, id_ordenservicio = :id_ordenservicio WHERE id = :id AND id_afiliado IS NULL", nativeQuery=true)
    int AgendarServicio(@Param("id") Integer id, @Param("id_afiliado") Integer id_afiliado, @Param("id_ordenservicio") Integer id_ordenservicio);

    //actualizar una agenda servicio
    @Modifying
    @Transactional
    @Query(value="UPDATE agendaservicio SET id_medico = :id_medico, id_serviciosalud = :id_serviciosalud, id_ips = :id_ips, fechaHora = :fechaHora, id_afiliado = :id_afiliado, id_ordenservicio = :id_ordenservicio  WHERE id = :id", nativeQuery=true)
    void actualizarAgendaServicio(@Param("id") Integer id, @Param("id_medico") Integer id_medico, @Param("id_serviciosalud") Integer id_serviciosalud, @Param("id_ips") Integer id_ips, @Param("fechaHora") LocalDateTime fechaHora, @Param("id_afiliado") Integer id_afiliado, @Param("id_ordenservicio") Integer id_ordenservicio);

    //eliminar agenda servicio
    @Modifying
    @Transactional
    @Query(value="DELETE FROM agendaservicio WHERE id =:id", nativeQuery=true)
    void eliminarAgendaServicio(@Param("id") Integer id);

    






}

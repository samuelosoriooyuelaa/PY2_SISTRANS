package uniandes.edu.co.proyecto.repository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.interfaz.DisponibilidadServicioProjection;
import uniandes.edu.co.proyecto.interfaz.IndiceUsoServicioProjection;
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
    @Query(value="INSERT INTO agendaservicio ( id_medico, id_serviciosalud, id_ips, fecha_Hora, id_afiliado, id_ordenservicio) VALUES ( :id_medico, :id_serviciosalud, :id_ips, :fecha_Hora, NULL, NULL)", nativeQuery=true)
    void insertarAgendaServicio(@Param("id_medico") Integer id_medico, @Param("id_serviciosalud") Integer id_serviciosalud, @Param("id_ips") Integer id_ips, @Param("fecha_Hora") LocalDateTime fecha_Hora);

    //insertar agenda servicio RF7.2 agendamiento
    @Modifying
    @Transactional
    @Query(value="UPDATE agendaservicio SET id_afiliado = :id_afiliado, id_ordenservicio = :id_ordenservicio WHERE id = :id AND id_afiliado IS NULL", nativeQuery=true)
    int AgendarServicio(@Param("id") Integer id, @Param("id_afiliado") Integer id_afiliado, @Param("id_ordenservicio") Integer id_ordenservicio);

    //actualizar una agenda servicio
    @Modifying
    @Transactional
    @Query(value="UPDATE agendaservicio SET id_medico = :id_medico, id_serviciosalud = :id_serviciosalud, id_ips = :id_ips, fecha_Hora = :fecha_Hora, id_afiliado = :id_afiliado, id_ordenservicio = :id_ordenservicio  WHERE id = :id", nativeQuery=true)
    void actualizarAgendaServicio(@Param("id") Integer id, @Param("id_medico") Integer id_medico, @Param("id_serviciosalud") Integer id_serviciosalud, @Param("id_ips") Integer id_ips, @Param("fecha_Hora") LocalDateTime fechaHora, @Param("id_afiliado") Integer id_afiliado, @Param("id_ordenservicio") Integer id_ordenservicio);

    //eliminar agenda servicio
    @Modifying
    @Transactional
    @Query(value="DELETE FROM agendaservicio WHERE id =:id", nativeQuery=true)
    void eliminarAgendaServicio(@Param("id") Integer id);


    //RFC1
    @Query("""
        SELECT 
            s.nombre AS nombreServicio,
            a.fecha_Hora AS fechaHoraDisponible,
            i.nombre AS nombreIPS,
            m.nombre AS nombreMedico
        FROM AgendaServicio a
        JOIN a.id_serviciosalud s
        JOIN a.id_ips i
        JOIN a.id_medico m
        WHERE s.id = :idServicio
        AND a.fecha_Hora BETWEEN :inicio AND :fin
        AND a.id_afiliado IS NULL""")
    List<DisponibilidadServicioProjection> findDisponibilidad(
        @Param("idServicio") Integer idServicio,
        @Param("inicio") LocalDateTime inicio,
        @Param("fin") LocalDateTime fin);



    @Query(value = """
    SELECT 
        ss.nombre AS nombreServicio,
        COUNT(ag.id) AS totalDisponibles,
        COUNT(p.id) AS totalUsados,
        CASE 
            WHEN COUNT(ag.id) = 0 THEN 0.0
            ELSE ROUND(COUNT(p.id) * 1.0 / COUNT(ag.id), 2)
        END AS indiceUso
    FROM serviciosalud ss
    LEFT JOIN agendaservicio ag ON ss.id = ag.id_serviciosalud
        AND ag.fecha_Hora BETWEEN :fechaInicio AND :fechaFin
    LEFT JOIN prestacion p ON ag.id = p.id_agenda
        AND p.fechaHoraInicio BETWEEN :fechaInicio AND :fechaFin
    GROUP BY ss.nombre
    ORDER BY indiceUso DESC
    """, nativeQuery = true)
List<IndiceUsoServicioProjection> calcularIndiceUsoServicios(
    @Param("fechaInicio") LocalDateTime fechaInicio,
    @Param("fechaFin") LocalDateTime fechaFin);



}

    








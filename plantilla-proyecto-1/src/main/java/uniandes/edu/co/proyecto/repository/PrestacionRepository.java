package uniandes.edu.co.proyecto.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.interfaz.ServiciosMasSolicitadosProjection;
import uniandes.edu.co.proyecto.interfaz.UtilizacionServiciosProjection;
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
    @Query(value="INSERT INTO prestacion ( id_afiliado, id_ips, id_serviciosalud, fecha_Hora_Inicio, fecha_Hora_Final) VALUES ( :id_afiliado, :id_ips, :id_serviciosalud, :fecha_Hora_Inicio, :fecha_Hora_Final)", nativeQuery=true)
    void insertarPrestacion(@Param("id_afiliado") Integer id_afiliado, @Param("id_ips") Integer id_ips, @Param("id_serviciosalud") Integer id_serviciosalud,
    @Param("fecha_Hora_Inicio") LocalDateTime fechaHoraInicio, @Param("fecha_Hora_Final") LocalDateTime fechaHoraFinal);

    //actualizar una prestacion 
    @Modifying
    @Transactional
    @Query(value="UPDATE prestacion SET id_afiliado = :id_afiliado, id_ips = :id_ips, id_serviciosalud = :id_serviciosalud, fecha_Hora_Inicio = :fecha_Hora_Inicio, fecha_Hora_Final = :fecha_Hora_Final  WHERE id = :id", nativeQuery=true)
    void actualizarPrestacion(@Param("id") Integer id, @Param("id_afiliado") Integer id_afiliado, @Param("id_ips") Integer id_ips, @Param("id_serviciosalud") Integer id_serviciosalud, @Param("fecha_Hora_Inicio") LocalDateTime fecha_Hora_Inicio, @Param("fecha_Hora_Final") LocalDateTime fecha_Hora_Final);

    //Eliminar una prestacion
    @Modifying
    @Transactional
    @Query(value="DELETE FROM prestacion WHERE id =:id", nativeQuery=true)
    void eliminarPrestacion(@Param("id") Integer id);

    //RFC2
    @Query(value = """
    SELECT * FROM (
        SELECT 
            ss.id as idServicio,
            ss.nombre as nombreServicio,
            COUNT(p.id) as totalSolicitudes
        FROM 
            prestacion p
        JOIN 
            serviciosalud ss ON p.id_serviciosalud = ss.id
        WHERE 
            p.fecha_Hora_Final BETWEEN :fechaInicio AND :fechaFin
        GROUP BY 
            ss.id, ss.nombre
        ORDER BY 
            COUNT(p.id) DESC
    ) WHERE ROWNUM <= 3
    """, nativeQuery = true)
List<ServiciosMasSolicitadosProjection> findTop3ServiciosMasSolicitados(
    @Param("fechaInicio") LocalDateTime fechaInicio,
    @Param("fechaFin") LocalDateTime fechaFin);



    //rfc4
    @Query(value = """
    SELECT 
        ss.nombre as nombreServicio,
        p.fecha_Hora_Final as fecha,
        i.nombre as nombreIPS
    FROM 
        prestacion p
    JOIN 
        serviciosalud ss ON p.id_serviciosalud = ss.id
    JOIN 
        ips i ON p.id_ips = i.id
    WHERE 
        p.id_afiliado = :idAfiliado
        AND p.fecha_Hora_Final BETWEEN :fechaInicio AND :fechaFin
    ORDER BY 
        p.fecha_Hora_Final DESC
    """, nativeQuery = true)
List<UtilizacionServiciosProjection> findUtilizacionByAfiliado(
    @Param("idAfiliado") Integer idAfiliado,
    @Param("fechaInicio") LocalDateTime fechaInicio,
    @Param("fechaFin") LocalDateTime fechaFin);
    
    


    

    
}

    



    






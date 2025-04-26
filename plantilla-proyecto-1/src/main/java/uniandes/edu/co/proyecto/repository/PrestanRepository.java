package uniandes.edu.co.proyecto.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Prestan;
import uniandes.edu.co.proyecto.modelo.PrestanPK;

public interface PrestanRepository extends JpaRepository<Prestan, PrestanPK> {

    //Obtener todos los prestan (ips, serviciosalud)
    @Query(value="SELECT * FROM prestan", nativeQuery=true)
    Collection<Prestan> darAllPrestan();

    //obtener prestan por id_ips (servicios salud que presta cierta ips)
    @Query(value = "SELECT * FROM prestan WHERE id_ips = :id_ips", nativeQuery = true)
    Collection<Prestan> darServiciosByIPS(@Param("id_ips") Integer id_ips);

    //obtener prestan por id_serviciosalud (ips´s que prestan cierto servicio salud)
    @Query(value = "SELECT * FROM prestan WHERE id_serviciosalud = :id_serviciosalud", nativeQuery = true)
    Collection<Prestan> darIPSByServicioSalud(@Param("id_serviciosalud") Integer id_serviciosalud);

    //insertar prestan RF3
    @Modifying
    @Transactional
    @Query(value="INSERT INTO prestan (id_ips, id_serviciosalud) VALUES(:id_ips, :id_serviciosalud)", nativeQuery=true)
    void insertarPrestan(@Param("id_ips") Integer id_ips, @Param("id_serviciosalud") Integer id_serviciosalud);

    //Eliminar prestan por id_ips (borra los serviciosalud relacionados a una ips)
    @Modifying
    @Transactional
    @Query(value="DELETE FROM prestan WHERE id_ips = :id_ips", nativeQuery=true)
    void eliminarPorIPS(@Param("id_ips") Integer id_ips);

    //Eliminar prestan por id_serviciosalud (borra los ips relacionados a un serviciosalud)
    @Modifying
    @Transactional
    @Query(value="DELETE FROM prestan WHERE id_serviciosalud = :id_serviciosalud", nativeQuery=true)
    void eliminarPorServicioSalud(@Param("id_serviciosalud") Integer id_serviciosalud);









}

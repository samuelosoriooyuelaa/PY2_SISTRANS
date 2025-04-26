package uniandes.edu.co.proyecto.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ServicioSalud;

public interface  ServicioSaludRepository extends JpaRepository<ServicioSalud, Integer> {
    //Obtener todos servicios salud
    @Query(value="SELECT * FROM serviciosalud", nativeQuery=true)
    Collection<ServicioSalud> darServiciosSalud();

    //obtener servicio salud por id
    @Query(value = "SELECT * FROM serviciosalud WHERE id = :id", nativeQuery = true)
    ServicioSalud darServicioSalud(@Param("id") Integer id);

    //insertar un servicio salud RF2
    @Modifying
    @Transactional
    @Query(value="INSERT INTO serviciosalud (id, nombre) VALUES (eps_sequence.nextval, :nombre)", nativeQuery=true)
    void insertarServicioSalud(@Param("nombre") String nombre);

    //actualizar un servicio salud
    @Modifying
    @Transactional
    @Query(value="UPDATE serviciosalud SET nombre = :nombre WHERE id = :id", nativeQuery=true)
    void actualizarServicioSalud(@Param("id") Integer id, @Param("nombre") String nombre);

    //eliminar un servicio salud
    @Modifying
    @Transactional
    @Query(value="DELETE FROM serviciosalud WHERE id =:id", nativeQuery=true)
    void eliminarServicioSalud(@Param("id") Integer id);






    



}

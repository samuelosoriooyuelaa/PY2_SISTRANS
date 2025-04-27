package uniandes.edu.co.proyecto.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.IPS;


public interface IPSRepository extends JpaRepository<IPS, Integer>{
    //Obtener todos ips
    @Query(value="SELECT * FROM ips", nativeQuery=true)
    Collection<IPS> darIPSS();

    //obtener ips por id
    @Query(value = "SELECT * FROM ips WHERE id = :id", nativeQuery = true)
    IPS darIPS(@Param("id") Integer id);

    //insertar ips RF1
    @Modifying
    @Transactional
    @Query(value="INSERT INTO ips (nombre, nit, direccion, telefono) VALUES (:nombre, :nit, :direccion, :telefono)", nativeQuery=true)
    void insertarIPS(
        @Param("nombre") String nombre, 
        @Param("nit") String nit, 
        @Param("direccion") String direccion, 
        @Param("telefono") String telefono);



    //actualizar ips
    @Modifying
    @Transactional
    @Query(value="UPDATE ips SET nombre = :nombre, nit = :nit, direccion = :direccion, telefono = :telefono WHERE id = :id", nativeQuery=true)
    void actualizarIPS(@Param("id") Integer id, @Param("nombre") String nombre, @Param("nit") String nit, @Param("direccion") String direccion, @Param("telefono") String telefono);

    //eliminar ips
    @Modifying
    @Transactional
    @Query(value="DELETE FROM ips WHERE id =:id", nativeQuery=true)
    void eliminarIPS(@Param("id") Integer id);
    
    



}

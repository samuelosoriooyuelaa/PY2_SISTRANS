package uniandes.edu.co.proyecto.repository;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Afiliado;
import uniandes.edu.co.proyecto.modelo.IPS;



public interface AfiliadoRepository extends JpaRepository<Afiliado, Integer>{

    //obtener todos los afiliados 
    @Query(value="SELECT * FROM afiliado", nativeQuery=true)
    Collection<Afiliado> darAfiliados();

    //obtener un afiliado por id
    @Query(value = "SELECT * FROM afiliado WHERE id = :id", nativeQuery = true)
    IPS darAfiliado(@Param("id") Integer id);

    //insertar un afiliado RF5
    @Modifying
    @Transactional
    @Query(value="INSERT INTO afiliado (id, nombre, tipo_documento, numero_documento, fecha_nacimiento, direccion, telefono, tipo) VALUES (eps_sequence.nextval, :nombre, :tipo_documento, :numero_documento, :fecha_nacimiento, :direccion, :telefono, :tipo)", nativeQuery=true)
    void insertarAfiliado(@Param("nombre") String nombre, @Param("tipo_documento") String tipo_documento, @Param("numero_documento") String numero_documento, 
    @Param("fecha_nacimiento") Date fecha_nacimiento, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("tipo") String tipo);

    //actualizar un afiliado
    @Modifying
    @Transactional
    @Query(value="UPDATE afiliado SET nombre = :nombre, tipo_documento = :tipo_documento, numero_documento = :numero_documento, fecha_nacimiento = :fecha_nacimiento, direccion = :direccion, telefono =:telefono, tipo = :tipo WHERE id = :id", nativeQuery=true)
    void actualizarAfiliado(@Param("id") Integer id, @Param("nombre") String nombre, @Param("tipo_documento") String tipo_documento,
     @Param("numero_documento") String numero_documento, @Param("fecha_nacimiento") Date fecha_nacimiento, @Param("direccion") String direccion, @Param("telefono") String telefono, @Param("tipo") String tipo);
    

    //eliminar afiliado
    @Modifying
    @Transactional
    @Query(value="DELETE FROM afiliado WHERE id =:id", nativeQuery=true)
    void eliminarAfiliado(@Param("id") Integer id);



    





}

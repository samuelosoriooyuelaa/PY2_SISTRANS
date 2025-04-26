package uniandes.edu.co.proyecto.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Contratan;
import uniandes.edu.co.proyecto.modelo.ContratanPK;


public interface ContratanRepository extends JpaRepository<Contratan, ContratanPK> {
    //obtener todos los contratan (id_ips, id_medico)
    @Query(value="SELECT * FROM contratan", nativeQuery=true)
    Collection<Contratan> darAllContratan();

    //obtener contratan por id_ips (obtener los medicos contratados por una ips)
    @Query(value = "SELECT * FROM contratan WHERE id_ips = :id_ips", nativeQuery = true)
    Collection<Contratan> darMedicosByIPS(@Param("id_ips") Integer id_ips);

    //obtener contratan por id_medico (obtener los ips que contrató a cierto medico)
    @Query(value = "SELECT * FROM contratan WHERE id_medico = :id_medico", nativeQuery = true)
    Collection<Contratan> darIPSSByMedico(@Param("id_medico") Integer id_medico);

    //insertar un contratan
    @Modifying
    @Transactional
    @Query(value="INSERT INTO contratan (id_ips, id_medico) VALUES(:id_ips, :id_medico)", nativeQuery=true)
    void insertarContratan(@Param("id_ips") Integer id_ips, @Param("id_medico") Integer id_medico);

    //eliminar por ips (elimina los medicos contratados por cierta ips)
    @Modifying
    @Transactional
    @Query(value="DELETE FROM contratan WHERE id_ips = :id_ips", nativeQuery=true)
    void eliminarPorIPS(@Param("id_ips") Integer id_ips);

    //eliminar por medico (elimina las ips´s que contrataron a ese medico)
    @Modifying
    @Transactional
    @Query(value="DELETE FROM contratan WHERE id_medico = :id_medico", nativeQuery=true)
    void eliminarPorMedico(@Param("id_medico") Integer id_medico);

    








}

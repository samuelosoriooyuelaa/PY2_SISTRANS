package uniandes.edu.co.proyecto.repository;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Cotizante;
import uniandes.edu.co.proyecto.modelo.CotizantePK;

public interface CotizanteRepository extends JpaRepository<Cotizante, CotizantePK>{
    
    //obtener todos los cotizantes
    @Query(value="SELECT * FROM cotizante", nativeQuery=true)
    Collection<Cotizante> darAllCotizantes();

    //obtener afiliados beneficiarios por id_cotizante 
    @Query(value = "SELECT * FROM cotizante WHERE id_cotizante = :id_cotizante", nativeQuery = true)
    Collection<Cotizante> darBeneficiariosByCotizante(@Param("id_cotizante") Integer id_cotizante);

    //insertar un cotizante
    @Modifying
    @Transactional
    @Query(value="INSERT INTO cotizante (id_cotizante, id_beneficiario, parentesco) VALUES(:id_cotizante, :id_beneficiario, :parentesco)", nativeQuery=true)
    void insertarCotizante(@Param("id_cotizante") Integer id_cotizante, @Param("id_beneficiario") Integer id_beneficiario, @Param("parentesco") String parentesco);

    //eliminar cotizante 
    @Modifying
    @Transactional
    @Query(value="DELETE FROM cotizante WHERE id_cotizante = :id_cotizante", nativeQuery=true)
    void eliminarPorCotizante(@Param("id_cotizante") Integer id_cotizante);

    //eliminar beneficiario
    @Modifying
    @Transactional
    @Query(value="DELETE FROM contizante WHERE id_beneficiario = :id_beneficiario", nativeQuery=true)
    void eliminarPorBeneficiario(@Param("id_beneficiario") Integer id_beneficiario);

    



    


}

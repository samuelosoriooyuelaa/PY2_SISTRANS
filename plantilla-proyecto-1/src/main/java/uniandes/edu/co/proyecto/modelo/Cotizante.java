package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="cotizante")
public class Cotizante {
    //atributos
    @EmbeddedId
    private CotizantePK pk;

    //constructor
    public Cotizante(){;}

    public Cotizante(Afiliado id_cotizante, Afiliado id_beneficiario, String parentesco ){
        
        this.pk= new CotizantePK(id_cotizante, id_beneficiario, parentesco);
    }

    //getters y setters
    public CotizantePK getPk(){
        return this.pk;
    }
    public void setPk(CotizantePK pk){
        this.pk=pk;
    }




}

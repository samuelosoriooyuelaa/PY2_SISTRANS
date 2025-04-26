package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="contratan")
public class Contratan {
    //atributos
    @EmbeddedId
    private ContratanPK pk;

    //constructor
    public Contratan(){;}

    public Contratan(IPS id_ips, Medico id_medico){
        this.pk= new ContratanPK(id_ips, id_medico);
    }

    //getters y setters
    public ContratanPK getPk(){
        return this.pk;
    }
    public void setPk(ContratanPK pk){
        this.pk=pk;
    }



}

package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="prestan")
public class Prestan {
    //atributos
    @EmbeddedId
    private PrestanPK pk;

    //constructores
    public Prestan (){;}

    public Prestan(IPS id_ips, ServicioSalud id_serviciosalud){
        this.pk = new PrestanPK(id_ips, id_serviciosalud);
    }

    //setters y getters

    public PrestanPK getPk(){
        return this.pk;
    }
    public void setPk(PrestanPK pk){
        this.pk = pk;
    }







}

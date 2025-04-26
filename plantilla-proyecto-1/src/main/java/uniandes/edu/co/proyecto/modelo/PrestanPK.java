package uniandes.edu.co.proyecto.modelo;
import java.io.Serializable;
import java.sql.Date;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Embeddable
public class PrestanPK implements Serializable{

    //Atributos
    @ManyToOne
    @JoinColumn(name="id_ips", referencedColumnName="id")
    private IPS id_ips;

    @ManyToOne
    @JoinColumn(name="id_serviciosalud", referencedColumnName="id")
    private ServicioSalud id_servicioSalud;

    //constructor
    public PrestanPK(){
        super();
    }

    public PrestanPK(IPS id_ips, ServicioSalud id_servicioSalud){
        super();
        this.id_ips = id_ips;
        this.id_servicioSalud = id_servicioSalud;
    }

    //getters y setters 
    public IPS getId_ips(){
        return this.id_ips;
    }
    public void setId_ips(IPS id_ips){
        this.id_ips = id_ips;
    }
    public ServicioSalud getId_serviciosalud(){
        return this.id_servicioSalud;
    }
    public void setId_serviciosalud(ServicioSalud id_serviciosalud)
    {
        this.id_servicioSalud = id_serviciosalud;
    }

}

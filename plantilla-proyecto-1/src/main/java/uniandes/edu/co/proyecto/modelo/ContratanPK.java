package uniandes.edu.co.proyecto.modelo;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ContratanPK implements Serializable {

    //atributos
    @ManyToOne
    @JoinColumn(name="id_ips", referencedColumnName="id")
    private IPS id_ips;

    @ManyToOne
    @JoinColumn(name="id_medico",referencedColumnName="id")
    private Medico id_medico;

    //constructor
    public ContratanPK(){super();}

    public ContratanPK(IPS id_ips, Medico id_medico){
        super();
        this.id_ips=id_ips;
        this.id_medico=id_medico;
    }
    //setters y getters
    public IPS getId_ips(){
        return this.id_ips;
    }
    public void setId_ips(IPS id_ips){
        this.id_ips=id_ips;
    }
    public Medico getId_medico(){
        return this.id_medico;
    }
    public void setId_medico(Medico id_medico){
        this.id_medico=id_medico;
    }





}

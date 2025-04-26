package uniandes.edu.co.proyecto.modelo;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ordenservicio")
public class AgendaServicio {
    //Atributos
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_medico", referencedColumnName="id", nullable=false)
    private Medico id_medico;

    @ManyToOne
    @JoinColumn(name="id_serviciosalud", referencedColumnName="id", nullable=false)
    private ServicioSalud id_serviciosalud;

    @ManyToOne
    @JoinColumn(name="id_ips", referencedColumnName="id",nullable=false)
    private IPS id_ips;

    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name="id_afiliado", referencedColumnName="id",nullable=true)
    private Afiliado id_afiliado;

    @ManyToOne
    @JoinColumn(name="id_ordenservicio", referencedColumnName="id",nullable=true)
    private OrdenServicio id_ordenservicio;

    //constructores
    public AgendaServicio(){;}

    public AgendaServicio(int id, Medico id_medico, ServicioSalud id_serviciosalud, IPS id_ips,
    LocalDateTime fechaHora){
        this.id=id;
        this.id_medico=id_medico;
        this.id_ips=id_ips;
        this.fechaHora=fechaHora;
        this.id_afiliado=null;
        this.id_ordenservicio=null;
    }

    //setters y getters 
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public Medico getId_medico(){
        return this.id_medico;
    }
    public void setId_medico(Medico medico_id){
        this.id_medico=medico_id;
    }
    public ServicioSalud getId_serviciosalud(){
        return this.id_serviciosalud;
    }
    public void setId_serviciosalud(ServicioSalud id_serviciosalud){
        this.id_serviciosalud=id_serviciosalud;
    }
    public IPS getId_ips(){
        return this.id_ips;
    }
    public void setId_ips(IPS id_ips){
        this.id_ips=id_ips;
    }
    public LocalDateTime getFechaHora(){
        return this.fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora){
        this.fechaHora=fechaHora;
    }
    public Afiliado getId_afiliado(){
        return this.id_afiliado;
    }
    public void setId_afiliado(Afiliado id_afiliado){
        this.id_afiliado=id_afiliado;
    }
    public OrdenServicio getId_ordenservicio(){
        return this.id_ordenservicio;
    }
    public void setId_ordenservicio(OrdenServicio id_ordenservicio){
        this.id_ordenservicio=id_ordenservicio;
    }






}

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
@Table(name="prestacion")
public class Prestacion {
    //atributos
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_afiliado", referencedColumnName="id")
    private Afiliado id_afiliado;

    @ManyToOne
    @JoinColumn(name="id_ips", referencedColumnName="id")
    private IPS id_ips;

    @ManyToOne
    @JoinColumn(name="id_serviciosalud", referencedColumnName="id")
    private ServicioSalud id_serviciosalud;

    private LocalDateTime fechaHoraInicio;

    private  LocalDateTime fechaHoraFinal;

    //constructor
    public Prestacion(){;}

    public Prestacion(Integer id, Afiliado id_afiliado, IPS id_ips, ServicioSalud id_serviciosalud, 
    LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFinal){
        this.id =id;
        this.id_afiliado=id_afiliado;
        this.id_ips = id_ips;
        this.id_serviciosalud=id_serviciosalud;
        this.fechaHoraInicio=fechaHoraInicio;
        this.fechaHoraFinal=fechaHoraFinal;
    }
    //setters y getters

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public Afiliado getId_afiliado(){
        return this.id_afiliado;
    }
    public void setId_afiliado(Afiliado id_afiliado){
        this.id_afiliado=id_afiliado;
    }
    public IPS getId_ips(){
        return this.id_ips;
    }
    public void setId_ips(IPS id_ips){
        this.id_ips=id_ips;
    }
    public ServicioSalud getId_serviciosalud(){
        return this.id_serviciosalud;
    }
    public void setId_serviciosalud(ServicioSalud id_serviciosalud){
        this.id_serviciosalud=id_serviciosalud;
    }
    public LocalDateTime getFechaHoraInicio(){
        return this.fechaHoraInicio;
    }
    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio){
        this.fechaHoraInicio=fechaHoraInicio;
    }
    public LocalDateTime getFechaHoraFinal(){
        return this.fechaHoraFinal;
    }
    public void setFechaHoraFinal(LocalDateTime fechaHoraFinal){
        this.fechaHoraFinal=fechaHoraFinal;
    }








}

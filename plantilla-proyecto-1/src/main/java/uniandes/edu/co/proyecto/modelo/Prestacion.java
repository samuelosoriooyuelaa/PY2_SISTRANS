package uniandes.edu.co.proyecto.modelo;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="prestacion")
public class Prestacion {
    //atributos
    @Id
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

    @Column(name = "FECHA_HORA_INICIO")
    @JsonProperty("fecha_Hora_Inicio")
    private LocalDateTime fecha_Hora_Inicio;

    @Column(name = "FECHA_HORA_FINAL")
    @JsonProperty("fecha_Hora_Final")
    private  LocalDateTime fecha_Hora_Final;

    //constructor
    public Prestacion(){;}

    public Prestacion(Integer id, Afiliado id_afiliado, IPS id_ips, ServicioSalud id_serviciosalud, 
    LocalDateTime fecha_Hora_Inicio, LocalDateTime fecha_Hora_Final){
        this.id =id;
        this.id_afiliado=id_afiliado;
        this.id_ips = id_ips;
        this.id_serviciosalud=id_serviciosalud;
        this.fecha_Hora_Inicio=fecha_Hora_Inicio;
        this.fecha_Hora_Final=fecha_Hora_Final;
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
    public LocalDateTime getFecha_Hora_Inicio(){
        return this.fecha_Hora_Inicio;
    }
    public void setFechaHoraInicio(LocalDateTime fecha_Hora_Inicio){
        this.fecha_Hora_Inicio=fecha_Hora_Inicio;
    }
    public LocalDateTime getFecha_Hora_Final(){
        return this.fecha_Hora_Final;
    }
    public void setFechaHoraFinal(LocalDateTime fecha_Hora_Final){
        this.fecha_Hora_Final=fecha_Hora_Final;
    }








}

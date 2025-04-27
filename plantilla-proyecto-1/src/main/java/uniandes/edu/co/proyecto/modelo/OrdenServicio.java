package uniandes.edu.co.proyecto.modelo;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name="ordenservicio")
public class OrdenServicio {
    

    //Atributos
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_medico", referencedColumnName= "id")
    private Medico id_medico;

    @ManyToOne
    @JoinColumn(name="id_afiliado",referencedColumnName= "id" )
    private Afiliado id_afiliado;

    @ManyToOne
    @JoinColumn(name="id_serviciosalud",referencedColumnName= "id" )
    private ServicioSalud id_serviciosalud;

    private Date fecha;

    
    private String estado;

    //Constructor 
    public OrdenServicio(){;}

    public OrdenServicio(Integer id, Medico id_medico, Afiliado id_afiliado, ServicioSalud id_serviciosalud, Date fecha, String estado){
        this.id=id;
        this.id_medico=id_medico;
        this.id_afiliado=id_afiliado;
        this.id_serviciosalud=id_serviciosalud;
        this.fecha=fecha;
        this.estado=estado;
    }

    

    //setters y getters
    public Integer getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public Medico getId_medico(){
        return this.id_medico;
    }
    public void setId_medico(Medico id_medico){
        this.id_medico=id_medico;
    }
    public Afiliado getId_afiliado(){
        return this.id_afiliado;
    }
    public void setId_afiliado(Afiliado id_afiliado){
        this.id_afiliado=id_afiliado;
    }
    public ServicioSalud getId_serviciosalud(){
        return this.id_serviciosalud;
    }
    public void setId_serviciosalud(ServicioSalud id_serviciosalud){
        this.id_serviciosalud=id_serviciosalud;
    }
    public Date getFecha(){
        return this.fecha;
    }
    public void setFecha(Date fecha){
        this.fecha=fecha;
    }
    public String getEstadoOrden(){
        return this.estado;
    }
    public void setEstadoOrden(String estado){
        this.estado=estado;
    }


}

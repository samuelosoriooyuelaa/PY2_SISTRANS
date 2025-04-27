package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="serviciosalud")
public class ServicioSalud {
    public enum TipoServicio{
        CONSULTA_GENERAL,
        CONSULTA_ESPECIALISTA,
        CONSULTA_CONTROL,
        URGENCIAS,
        EXAMEN_DIAGNOSTICO,
        TERAPIA,
        PROCEDIMIENTO_ESPECIAL,
        HOSPITALIZACION
    }
    //atributos
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable=false)
    private TipoServicio nombre; 

    //constructor
    public ServicioSalud(){;}

    public ServicioSalud(Integer id, TipoServicio nombre){
        this.id = id ;
        this.nombre = nombre;
    }
    
    //setters y getters 
    public Integer getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public TipoServicio getNombre(){
        return this.nombre;
    }
    public void setNombre(TipoServicio nombre){
        this.nombre = nombre;
    }


    

}

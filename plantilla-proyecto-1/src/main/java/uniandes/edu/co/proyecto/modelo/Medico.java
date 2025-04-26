package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="medico")
public class Medico {

    //Atributos 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String nombre;

    private String tipo_documento;

    private String numero_documento;

    @Column(unique=true, nullable=false)
    private String registro_medico;

    private String especialidad;

    //constructor 
    public Medico (){;}

    public Medico(Integer id, String nombre, String t_documento, String num_documento, String reg_medico, String especialidad){
        this.id = id;
        this.nombre= nombre;
        this.tipo_documento= t_documento;
        this.numero_documento = num_documento;
        this.registro_medico = reg_medico;
        this.especialidad= especialidad;
    }
    //getters y setters
    public Integer getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getTipoDocumento(){
        return this.tipo_documento;
    }
    public void setTipoDocumento(String tipo){
        this.tipo_documento=tipo;
    }
    public String getNumeroDocumento(){
        return this.numero_documento;
    }
    public void setNumeroDocumento(String numero){
        this.numero_documento=numero;
    }
    public String getRegistroMedico(){
        return this.registro_medico;
    }
    public void setRegistroMedico(String registro){
        this.registro_medico=registro;
    }
    public String getEspecialidad(){
        return this.especialidad;
    }
    public void setEspecialidad(String especialidad){
        this.especialidad=especialidad;
    }






}

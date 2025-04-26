package uniandes.edu.co.proyecto.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="ips")
public class IPS {
    //Atributos
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String nombre;

    @Column(unique = true)
    private String nit;

    private String direccion;

    private String telefono;

    //Constructores

    public IPS(){;}

    public IPS(Integer id, String nombre, String nit, String direccion, String telefono){
        this.id = id;
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    //SETTERS Y GETTERS

    public Integer getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNit(){
        return this.nit;
    }
    public void setNit(String nit){
        this.nit = nit;
    }
    public String getDireccion(){
        return this.direccion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public String getTelefono(){
        return this.telefono;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }




}

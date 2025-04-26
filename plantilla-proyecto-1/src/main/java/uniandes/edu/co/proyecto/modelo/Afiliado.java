package uniandes.edu.co.proyecto.modelo;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="afiliado")
public class Afiliado {
    public enum TipoAfiliado{
        COTIZANTE,
        BENEFICIARIO
    }

    //atributos
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String nombre;

    private String tipo_documento;

    @Column(unique=true)
    private String numero_documento;

    private Date fecha_nacimiento;

    private String direccion;

    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAfiliado tipo;   

    //constructor
    public Afiliado(){;}

    public Afiliado(Integer id, String nombre, String t_documento, String num_documento, Date fecha_nacimiento,
    String direccion, String telefono, TipoAfiliado tipo){
        this.id=id;
        this.nombre=nombre;
        this.tipo_documento=t_documento;
        this.numero_documento=num_documento;
        this.fecha_nacimiento=fecha_nacimiento;
        this.direccion=direccion;
        this.telefono=telefono;
        this.tipo=tipo;

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
    public Date getFechaNacimiento(){
        return this.fecha_nacimiento;
    }
    public void setFechaNacimiento(Date fecha){
        this.fecha_nacimiento=fecha;
    }
    public String getDireccion(){
        return this.direccion;
    }
    public void setDireccion(String direccion){
        this.direccion=direccion;
    }
    public String getTelefono(){
        return this.telefono;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public TipoAfiliado getTipo(){
        return this.tipo;
    }
    public void setTipo(TipoAfiliado tipo){
        this.tipo=tipo;
    }





}

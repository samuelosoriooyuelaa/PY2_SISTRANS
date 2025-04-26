package uniandes.edu.co.proyecto.modelo;
import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CotizantePK  implements Serializable{

    //atributos
    @ManyToOne
    @JoinColumn(name = "id_cotizante", referencedColumnName = "id")
    private Afiliado id_cotizante;

    @ManyToOne
    @JoinColumn(name = "id_beneficiario", referencedColumnName = "id")
    private Afiliado id_beneficiario;

    private String parentesco;

    //constructor
    public CotizantePK(){;}

    public CotizantePK(Afiliado id_cotizante,Afiliado id_beneficiario, String parentesco ){
        super();
        this.id_cotizante=id_cotizante;
        this.id_beneficiario=id_beneficiario;
        this.parentesco=parentesco;
    }

    //setters y getters

    public Afiliado getId_cotizante(){
        return this.id_cotizante;
    }
    public void setId_cotizante(Afiliado id_cotizante){
        this.id_cotizante=id_cotizante;
    }
    public Afiliado getId_beneficiario(){
        return this.id_beneficiario;
    }
    public void setId_beneficiario(Afiliado id_beneficiario){
        this.id_beneficiario=id_beneficiario;
    }
    public String getParentesco(){
        return this.parentesco;
    }
    public void setParentesco(String parentesco){
        this.parentesco=parentesco;
    }



}

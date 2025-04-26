package uniandes.edu.co.proyecto.modelo;

public class AgendaReservaRequest {
    private Integer id_afiliado;
    private Integer id_ordenservicio;

    
    public AgendaReservaRequest() {}

    
    public AgendaReservaRequest(Integer id_afiliado, Integer id_ordenservicio) {
        this.id_afiliado = id_afiliado;
        this.id_ordenservicio = id_ordenservicio;
    }

    
    public Integer getId_afiliado() {
        return id_afiliado;
    }

    public void setId_afiliado(Integer id_afiliado) {
        this.id_afiliado = id_afiliado;
    }

    public Integer getId_ordenservicio() {
        return id_ordenservicio;
    }

    public void setId_ordenservicio(Integer id_ordenservicio) {
        this.id_ordenservicio = id_ordenservicio;
    }
}

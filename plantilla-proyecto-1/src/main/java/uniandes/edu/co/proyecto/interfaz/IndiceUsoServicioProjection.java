package uniandes.edu.co.proyecto.interfaz;

import java.math.BigDecimal;
//rfc3
public interface IndiceUsoServicioProjection {
    String getNombreServicio();
    Long getTotalDisponibles();
    Long getTotalUsados();
    Double getIndiceUso();

}

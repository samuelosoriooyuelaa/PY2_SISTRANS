package uniandes.edu.co.proyecto.interfaz;
import java.time.LocalDateTime;


//rfc1
public interface DisponibilidadServicioProjection {
    String getNombreServicio();
    LocalDateTime getFechaHoraDisponible();
    String getNombreIPS();
    String getNombreMedico();


}

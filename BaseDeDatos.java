import java.util.Date;

public class BaseDeDatos {

    private String nombre;
    private String carnet;
    private Date fechaRegistro;

    public void guardar(String nombre, String carnet) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.fechaRegistro = new Date();
    }

    public boolean buscar(String carnet) {
        return carnet.equals(this.getCarnet());
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getCarnet() {
        return this.carnet;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
}

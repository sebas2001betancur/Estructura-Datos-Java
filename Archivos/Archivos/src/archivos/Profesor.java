package archivos;

public class Profesor {
    private String nombre;
    private String titulo;
    private int tiempovin; 

    public Profesor(String nombre, String titulo, int tiempovin) {
        this.nombre = nombre;
        this.titulo = titulo;
        this.tiempovin = tiempovin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTiempovin() {
        return tiempovin;
    }

    public void setTiempovin(int tiempovin) {
        this.tiempovin = tiempovin;
    }

    @Override
    public String toString() {
        return "Profesor{" + "nombre=" + nombre + ", titulo=" + 
                titulo + ", tiempovin=" + tiempovin + '}';
    }
    
    
}

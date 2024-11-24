package quiz3;
import java.time.LocalDate;

public class Empleado {
    private String id;
    private String nombre;
    private String tipoContrato;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private int salario;
    private String cargo;

    public Empleado(String id, String nombre, String tipoContrato, LocalDate fechaInicio, LocalDate fechaFinal, int salario, String cargo) {
        this.id = id;
        this.nombre = nombre;
        this.tipoContrato = tipoContrato;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.salario = salario;
        this.cargo = cargo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
    @Override
    public String toString() {
        return id + "\t" + nombre + "\t" + tipoContrato + "\t" + fechaInicio + "\t" + fechaFinal + "\t" + salario + "\t" + cargo;
    }
}

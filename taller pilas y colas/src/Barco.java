public class Barco {
    private String codigo;
    private String nombre;
    private String capitan;
    private String paisO;
    private int cantContenedores;

    public Barco(String codigo, String nombre, String capitan, String paisO, int cantContenedores) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capitan = capitan;
        this.paisO = paisO;
        this.cantContenedores = cantContenedores;
    }

    public Barco() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapitan() {
        return capitan;
    }

    public void setCapitan(String capitan) {
        this.capitan = capitan;
    }

    public String getPaisO() {
        return paisO;
    }

    public void setPaisO(String paisO) {
        this.paisO = paisO;
    }

    public int getCantContenedores() {
        return cantContenedores;
    }

    public void setCantContenedores(int cantContenedores) {
        this.cantContenedores = cantContenedores;
    }

    @Override
    public String toString() {
        return "Barco{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capitan='" + capitan + '\'' +
                ", paisO='" + paisO + '\'' +
                ", cantContenedores=" + cantContenedores +
                '}';
    }
}

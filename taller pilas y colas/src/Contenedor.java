import java.util.Date;

public class Contenedor {
    private String codigo;
    private String descrip;
    private String tipoProduct;
    private int cantidadProduct;
    private double valorEst;
    private String paisProce;
    private Date fechaCadu;
    private double tempMin;
    private double tempMax;

    public Contenedor(String codigo, String descrip, String tipoProduct, int cantidadProduct, double valorEst, String paisProce, Date fechaCadu, double tempMin, double tempMax) {
        this.codigo = codigo;
        this.descrip = descrip;
        this.tipoProduct = tipoProduct;
        this.cantidadProduct = cantidadProduct;
        this.valorEst = valorEst;
        this.paisProce = paisProce;
        this.fechaCadu = fechaCadu;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public Contenedor() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getTipoProduct() {
        return tipoProduct;
    }

    public void setTipoProduct(String tipoProduct) {
        this.tipoProduct = tipoProduct;
    }

    public int getCantidadProduct() {
        return cantidadProduct;
    }

    public void setCantidadProduct(int cantidadProduct) {
        this.cantidadProduct = cantidadProduct;
    }

    public double getValorEst() {
        return valorEst;
    }

    public void setValorEst(double valorEst) {
        this.valorEst = valorEst;
    }

    public String getPaisProce() {
        return paisProce;
    }

    public void setPaisProce(String paisProce) {
        this.paisProce = paisProce;
    }

    public Date getFechaCadu() {
        return fechaCadu;
    }

    public void setFechaCadu(Date fechaCadu) {
        this.fechaCadu = fechaCadu;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    @Override
    public String toString() {
        return "Contenedor{" +
                "codigo='" + codigo + '\'' +
                ", descrip='" + descrip + '\'' +
                ", tipoProduct='" + tipoProduct + '\'' +
                ", cantidadProduct=" + cantidadProduct +
                ", valorEst=" + valorEst +
                ", paisProce='" + paisProce + '\'' +
                ", fechaCadu=" + fechaCadu +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                '}';
    }
}

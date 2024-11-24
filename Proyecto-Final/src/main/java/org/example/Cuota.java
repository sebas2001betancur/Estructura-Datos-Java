package org.example;

import java.io.Serializable;
import java.time.LocalDate;

public class Cuota implements Serializable { private static final long serialVersionUID = 1L;
    private int numero;
    private double valor;
    private double saldoPendiente;
    private LocalDate fecha;
    private String estado;
    private double interes;
    private double capitalVigente;

    public Cuota(int numero, double valor, double capitalVigente, LocalDate fechaInicio) {
        this.numero = numero;
        this.valor = valor;
        this.saldoPendiente = valor; // Inicializa el saldo pendiente
        this.fecha = fechaInicio.plusMonths(numero - 1);
        this.estado = "pendiente";
        this.capitalVigente = capitalVigente;
    }

    public double pagar(double monto) {
        System.out.println("Saldo pendiente antes del pago: " + saldoPendiente);
        if (monto >= saldoPendiente) {
            double excedente = monto - saldoPendiente;
            saldoPendiente = 0;
            estado = "pagada";
            System.out.println("Saldo pendiente después del pago completo: 0");
            return excedente;
        } else {
            saldoPendiente -= monto;
            System.out.println("Saldo pendiente después del pago parcial: " + saldoPendiente);
            return 0;
        }
    }

    public double calcularInteres(double tasaEfectivaMensual) {
        return tasaEfectivaMensual * capitalVigente;
    }

    public int getNumero() {
        return numero;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public double getCapitalVigente() {
        return capitalVigente;
    }

    public void actualizarCapitalVigente(double amortizacion) {
        capitalVigente = Math.max(0, capitalVigente - amortizacion);
    }

    @Override
    public String toString() {
        return "Cuota{" +
                "numero=" + numero +
                ", valor=" + valor +
                ", saldoPendiente=" + saldoPendiente +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", interes=" + calcularInteres(0.02) + // Ejemplo de tasa mensual fija
                ", capitalVigente=" + capitalVigente +
                '}';
    }
}

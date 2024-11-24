package org.example;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SolicitudCredito implements Serializable { private static final long serialVersionUID = 1L;
    private static int consecutivoGlobal = 1; // Para generar códigos consecutivos
    private int codigo;
    private String tipo;  // "tarjeta", "libre inversión", "nómina", "hipotecario"
    private Cliente titular;
    private LocalDate fechaSolicitud;
    private double valor;
    private int numeroCuotas;
    private Lista cuotas;
    private String estado; // Puede ser "aprobado" o "rechazado"

    public SolicitudCredito(String tipo, Cliente titular, LocalDate fechaSolicitud, double valor, int numeroCuotas,  String estado) {
        this.codigo = consecutivoGlobal++;
        this.tipo = tipo;
        this.titular = titular;
        this.fechaSolicitud = fechaSolicitud;
        this.valor = valor;
        this.numeroCuotas = numeroCuotas;
        this.cuotas = new Lista();  // Inicializamos la lista personalizada de cuotas
        this.estado = estado;
        validarTipoCredito();
        generarCuotas();  // Genera las cuotas automáticamente al crear la solicitud
    }

    // Metodo para validar el tipo de crédito
    private void validarTipoCredito() {
        String[] tiposValidos = {"tarjeta", "libre inversión", "nómina", "hipotecario"};
        boolean tipoValido = false;
        for (String tipo : tiposValidos) {
            if (this.tipo.equalsIgnoreCase(tipo)) {
                tipoValido = true;
                break;
            }
        }
        if (!tipoValido) {
            throw new IllegalArgumentException("Tipo de crédito no válido");
        }
    }

    private void generarCuotas() {
        double cuotaMensual = calcularCuotaMensual();
        LocalDate fechaInicio = fechaSolicitud;
        for (int i = 1; i <= numeroCuotas; i++) {
            Cuota cuota = new Cuota(i, cuotaMensual, valor, fechaInicio);
            cuota.setSaldoPendiente(cuotaMensual);
            cuota.setEstado("pendiente");
            cuotas.AddLast(cuota);
            System.out.println("Cuota generada: " + cuota.getNumero() + " con valor: " + cuotaMensual + ", saldo pendiente: " + cuota.getSaldoPendiente() + " y estado: " + cuota.getEstado()); // Línea de depuración
        }
    }

    public void marcarCuotasComoPagadas() {
        Node actual = cuotas.getFirst();
        while (actual != null) {
            Cuota cuota = (Cuota) actual.getData();
            cuota.setEstado("pagada");
            cuota.setSaldoPendiente(0); // Aseguramos que el saldo pendiente sea 0
            actual = actual.getLink();
        }
    }

    // Metodo para obtener la proxima cuota pendiente en la lista de cuotas
    public Cuota getProximaCuotaPendiente() {
        Node actual = cuotas.getFirst();  // Obtener el primer nodo en la lista de cuotas
        while (actual != null) {
            Cuota cuota = (Cuota) actual.getData();  // Obtener el dato del nodo y hacer cast a Cuota
            if (cuota.getEstado().equals("pendiente")) {
                return cuota;
            }
            actual = actual.getLink();  // Avanzar al siguiente nodo
        }
        return null;  // Retornar null si no hay ninguna cuota pendiente
    }

    // Otros métodos y getters/setters


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public Cliente getTitular() {
        return titular;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public double getValor() {
        return valor;
    }

    public Lista getCuotas() {
        return cuotas;
    }


    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    // Metodo para calcular el valor de la cuota mensual
    public double calcularCuotaMensual() {
        double tasaEA = obtenerTasaAnual();
        double tasaEfectivaMensual = Math.pow(1 + tasaEA, 1.0 / 12) - 1;
        return valor * ((tasaEfectivaMensual * Math.pow(1 + tasaEfectivaMensual, numeroCuotas)) /
                (Math.pow(1 + tasaEfectivaMensual, numeroCuotas) - 1));
    }

    private double obtenerTasaAnual() {
        switch (tipo.toLowerCase()) {
            case "tarjeta":
                return 0.30;
            case "libre inversión":
                return 0.225;
            case "nómina":
                return 0.175;
            case "hipotecario":
                return 0.11;
            default:
                return 0.0;
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return String.format("SolicitudCredito{tipo='%s', id='%s', fecha='%s', valor=%s, cuotas=%d, estado='%s'}",
                tipo,
                titular.getId(),
                fechaSolicitud.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                df.format(valor),
                numeroCuotas,
                estado);
    }

    public static SolicitudCredito fromString(String line, Lista listaClientes) {
        try {
            // Extraer los valores entre comillas simples y llaves
            String content = line.substring(line.indexOf("{") + 1, line.lastIndexOf("}"));
            // Dividir por comas y procesar cada campo
            String[] fields = content.split(",");

            // Extraer valores usando expresiones regulares
            String tipo = extraerValor(fields[0], "tipo='(.*?)'");
            String cedula = extraerValor(fields[1], "id='(.*?)'");
            String fecha = extraerValor(fields[2], "fecha='(.*?)'");
            double valor = Double.parseDouble(extraerValor(fields[3], "valor=(.*?)").replace(",", ""));
            int cuotas = Integer.parseInt(extraerValor(fields[4], "cuotas=(.*?)"));
            String estado = extraerValor(fields[5], "estado='(.*?)'");

            // Buscar el cliente
            Cliente titular = listaClientes.buscarCliente(cedula);

            // Parsear la fecha
            LocalDate fechaSolicitud = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return new SolicitudCredito(tipo, titular, fechaSolicitud, valor, cuotas, estado);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al procesar la solicitud: " + e.getMessage() + ". Línea: " + line);
        }
    }

    private static String extraerValor(String field, String pattern) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(field.trim());
        if (m.find()) {
            return m.group(1);
        }
        throw new IllegalArgumentException("Formato incorrecto en el campo: " + field);
    }


}

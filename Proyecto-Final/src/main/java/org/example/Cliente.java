package org.example;

import java.io.Serializable;

public class Cliente implements Serializable { private static final long serialVersionUID = 1L;
    private String id;
    private String tipo;  // "jurídico" o "natural"
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipoContrato;
    private double salarioMensual;

    // Constructor
    public Cliente(String id, String tipo, String nombre, String direccion,
                   String telefono, String tipoContrato, double salarioMensual) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoContrato = tipoContrato;
        this.salarioMensual = salarioMensual;
    }

    public Cliente() {
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipoContrato='" + tipoContrato + '\'' +
                ", salarioMensual=" + salarioMensual +
                '}';
    }

    public static Cliente fromString(String line) {
        // Suponiendo que la línea tiene el formato "'1005911567', 'tipo', 'nombre', 'direccion', 'telefono', 'tipoContrato', salarioMensual=3500000.0}"
        String[] parts = line.split(","); // Dividir la cadena por comas

        // Extraer y procesar cada parte
        String id = parts[0].replace("'", "").trim(); // Eliminar comillas y espacios en blanco
        String tipo = parts[1].replace("'", "").trim(); // Obtener el tipo y eliminar comillas y espacios en blanco
        String nombre = parts[2].replace("'", "").trim(); // Obtener el nombre y eliminar comillas y espacios en blanco
        String direccion = parts[3].replace("'", "").trim(); // Obtener la dirección y eliminar comillas y espacios en blanco
        String telefono = parts[4].replace("'", "").trim(); // Obtener el teléfono y eliminar comillas y espacios en blanco
        String tipoContrato = parts[5].replace("'", "").trim(); // Obtener el tipo de contrato y eliminar comillas y espacios en blanco

        // Obtener el valor del salario y eliminar cualquier carácter no numérico
        String salarioStr = parts[6].split("=")[1].replace("}", "").trim();
        double salarioMensual = Double.parseDouble(salarioStr); // Convertir a double

        // Crear y devolver un nuevo objeto Cliente con los valores extraídos
        return new Cliente(id, tipo, nombre, direccion, telefono, tipoContrato, salarioMensual);
    }





}
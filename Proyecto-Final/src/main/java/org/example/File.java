package org.example;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class File implements Serializable { private static final long serialVersionUID = 1L;

    public static void enviarFactura(Cliente cliente, Lista listaCreditos) {
        String nombreArchivo = cliente.getId() + ".txt";
        DecimalFormat df = new DecimalFormat("#,###.00");
        try {
            FileWriter escritor = new FileWriter(nombreArchivo);
            String nombreMes = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MMMM"));

            escritor.write("Señor " + cliente.getNombre() + ":\n");
            escritor.write("Dentro de los primeros 5 días del mes " + nombreMes + " debe realizar el pago de las siguientes cuotas:\n\n");
            escritor.write("CódigoCrédito\tValor\tPagosPendiente\tTotal\n");

            Node actual = listaCreditos.getFirst();
            double totalCreditos = 0;
            while (actual != null) {
                if (actual.getData() instanceof SolicitudCredito) {
                    SolicitudCredito credito = (SolicitudCredito) actual.getData();
                    if (credito.getTitular().getId().equals(cliente.getId())) {
                        double totalCuotasPendientes = 0;
                        Node cuotaNode = credito.getCuotas().getFirst();
                        while (cuotaNode != null) {
                            Cuota cuota = (Cuota) cuotaNode.getData();
                            if (cuota.getEstado().equals("pendiente")) {
                                escritor.write(credito.getCodigo() + "\t" + df.format(cuota.getValor()) + "\t" + df.format(cuota.getSaldoPendiente()) + "\t" + df.format(cuota.getValor()) + "\n");
                                totalCuotasPendientes += cuota.getSaldoPendiente();
                            }
                            cuotaNode = cuotaNode.getLink();
                        }
                        totalCreditos += totalCuotasPendientes;
                    }
                }
                actual = actual.getLink();
            }

            escritor.write("\nEl total a pagar de sus créditos es: $" + df.format(totalCreditos) + " pesos.");
            escritor.close();
            JOptionPane.showMessageDialog(null, "Factura generada exitosamente para el cliente: " + cliente.getNombre());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar la factura: " + e.getMessage());
        }
    }


    public static void guardarDatos(Lista listaClientes, Queue colaSolicitudes) {
            // Guardar clientes
            try (PrintWriter writer = new PrintWriter(new FileWriter("clientes.txt"))) {
                Node current = listaClientes.getFirst();
                while (current != null) {
                    if (current.getData() instanceof Cliente) {
                        Cliente cliente = (Cliente) current.getData();
                        writer.println(cliente.toString());
                    }
                    current = current.getLink();
                }
            } catch (IOException e) {
                System.out.println("Error al guardar los clientes: " + e.getMessage());
            }

            // Guardar solicitudes de crédito
            try (PrintWriter writer = new PrintWriter(new FileWriter("solicitudes.txt"))) {
                Node current = colaSolicitudes.getFirst();
                while (current != null) {
                    SolicitudCredito solicitud = (SolicitudCredito) current.getData();
                    writer.println(solicitud.toString());
                    current = current.getLink();
                }
            } catch (IOException e) {
                System.out.println("Error al guardar las solicitudes: " + e.getMessage());
            }
        }

        public static Lista cargarClientes() {
            Lista listaClientes = new Lista();
            try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Cliente cliente = Cliente.fromString(line);
                    listaClientes.agregarCliente(cliente);
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los clientes: " + e.getMessage());
            }
            return listaClientes;
        }

    public static Queue cargarSolicitudes(Lista listaClientes) {
        Queue colaSolicitudes = new Queue();
        try (BufferedReader reader = new BufferedReader(new FileReader("solicitudes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    // Verificar que la línea tenga el formato correcto
                    if (line.startsWith("SolicitudCredito{") && line.endsWith("}")) {
                        SolicitudCredito solicitud = SolicitudCredito.fromString(line, listaClientes);
                        colaSolicitudes.agregarSolicitud(solicitud);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error al procesar la línea: " + line);
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar las solicitudes: " + e.getMessage());
        }
        return colaSolicitudes;
    }
}

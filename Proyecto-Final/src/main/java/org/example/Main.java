package org.example;

import javax.swing.JOptionPane;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Inicialización de estructuras
        File archivo = new File();
        Lista listaClientes = new Lista();
        Queue colaSolicitudes = new Queue();
        BinaryTree arbol = new BinaryTree();

        // Menú principal
        String[] menu = {
                "Registrar Cliente",
                "Registrar Solicitud de Crédito",
                "Procesar Solicitudes",
                "Pagar Cuota",
                "Cancelar Crédito",
                "Revaluar Solicitudes",
                "Enviar Factura",
                "Mostrar Créditos por Cliente",
                "Porcentaje de Deuda por Tipo",
                "Estadísticas de Créditos",
                "Guardar y Cargar Datos",
                "Contar Préstamos Aprobados por Tipo",
                "Salir"
        };

        String opcion;

        do {
            opcion = (String) JOptionPane.showInputDialog(
                    null, "Seleccione una opción", "Menú Principal - Banco Ahorro Seguro",
                    1, null, menu, menu[0]
            );

            switch(opcion) {
                case "Registrar Cliente":
                    try {
                        String id = JOptionPane.showInputDialog("Ingrese la identificación del cliente:");
                        String[] tiposCliente = {"natural", "jurídico"};
                        String tipo = (String) JOptionPane.showInputDialog(null, "Seleccione tipo de cliente",
                                "Tipo de Cliente", 1, null, tiposCliente, tiposCliente[0]);
                        String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
                        String direccion = JOptionPane.showInputDialog("Ingrese la dirección:");
                        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono:");
                        String tipoContrato = JOptionPane.showInputDialog("Ingrese el tipo de contrato:");
                        double salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario mensual:"));

                        Cliente nuevoCliente = new Cliente(id, tipo, nombre, direccion, telefono, tipoContrato, salario);
                        listaClientes.agregarCliente(nuevoCliente);

                        JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Error: El salario debe ser un número válido");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                    }
                    break;

                case "Registrar Solicitud de Crédito":
                    try {
                        String idCliente = JOptionPane.showInputDialog("Ingrese ID del cliente:");
                        if (idCliente == null || idCliente.trim().isEmpty()) {
                            throw new RuntimeException("El ID del cliente no puede estar vacío");
                        }

                        Cliente cliente = listaClientes.buscarCliente(idCliente);
                        if (cliente != null) {
                            String[] tiposCredito = {"tarjeta", "libre inversion", "nomina", "hipotecario"};
                            String tipoCredito = (String) JOptionPane.showInputDialog(null,
                                    "Seleccione tipo de crédito",
                                    "Tipo de Crédito",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    tiposCredito,
                                    tiposCredito[0]);

                            if (tipoCredito == null) {
                                throw new RuntimeException("Debe seleccionar un tipo de crédito");
                            }

                            String valorStr = JOptionPane.showInputDialog("Ingrese el valor del crédito:");
                            if (valorStr == null || valorStr.trim().isEmpty()) {
                                throw new RuntimeException("El valor del crédito no puede estar vacío");
                            }
                            double valor = Double.parseDouble(valorStr);
                            if (valor <= 0) {
                                throw new RuntimeException("El valor del crédito debe ser mayor que 0");
                            }

                            String cuotasStr = JOptionPane.showInputDialog("Ingrese el número de cuotas:");
                            if (cuotasStr == null || cuotasStr.trim().isEmpty()) {
                                throw new RuntimeException("El número de cuotas no puede estar vacío");
                            }
                            int cuotas = Integer.parseInt(cuotasStr);
                            if (cuotas <= 0) {
                                throw new RuntimeException("El número de cuotas debe ser mayor que 0");
                            }

                            LocalDate fechaSolicitud = LocalDate.now();

                            SolicitudCredito solicitud = new SolicitudCredito(
                                    tipoCredito,
                                    cliente,
                                    fechaSolicitud,
                                    valor,
                                    cuotas,
                                    "pendiente"
                            );

                            // Asegurarse de que colaSolicitudes está inicializada
                            if (colaSolicitudes == null) {
                                colaSolicitudes = new Queue();
                            }

                            colaSolicitudes.agregarSolicitud(solicitud);
                            JOptionPane.showMessageDialog(null, "Solicitud registrada exitosamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Error: El valor y las cuotas deben ser números válidos");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al registrar la solicitud: " + e.getMessage());
                    }
                    break;

                case "Procesar Solicitudes":
                    if (!colaSolicitudes.isEmpty()) {
                        SolicitudCredito solicitudProcesada = colaSolicitudes.obtenerSiguienteSolicitud();
                        Cliente cliente = solicitudProcesada.getTitular();
                        double cuotaMensual = solicitudProcesada.calcularCuotaMensual();
                        double totalCuotasExistentes = listaClientes.obtenerTotalCuotasCliente(cliente);
                        double limiteCuotas = cliente.getSalarioMensual() * 0.5;
                        if ((totalCuotasExistentes + cuotaMensual) <= limiteCuotas) {
                            solicitudProcesada.setEstado("aprobado"); // Cambiar estado
                            listaClientes.agregarSolicitudAprobada(solicitudProcesada);
                            JOptionPane.showMessageDialog(null, "Solicitud aprobada: " + solicitudProcesada);
                        } else {
                            solicitudProcesada.setEstado("rechazado"); // Cambiar estado
                            JOptionPane.showMessageDialog(null, "Solicitud rechazada: El monto de la cuota excede el 50% del salario del cliente");
                        }
                        colaSolicitudes.agregarSolicitud(solicitudProcesada); // Reagregar la solicitud a la cola
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay solicitudes en la cola");
                    }
                    break;

                case "Pagar Cuota":
                    try {
                        int codigoCredito = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código del préstamo:"));
                        double montoPago = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a pagar:"));

                        if (montoPago <= 0) {
                            JOptionPane.showMessageDialog(null, "El monto a pagar debe ser mayor que cero.");
                            break;
                        }

                        boolean pagoExitoso = listaClientes.pagarCuota(codigoCredito, montoPago);
                        if (!pagoExitoso) {
                            JOptionPane.showMessageDialog(null,
                                    "No se pudo procesar el pago. Verifique que:\n" +
                                            "- El código del préstamo existe\n" +
                                            "- El préstamo tiene cuotas pendientes\n" +
                                            "- El monto ingresado es válido");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Error: Ingrese valores numéricos válidos.\n" +
                                        "- El código del préstamo debe ser un número entero\n" +
                                        "- El monto a pagar debe ser un número decimal");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al procesar el pago: " + e.getMessage());
                    }
                    break;

                case "Cancelar Crédito":
                    try {
                        int codigoCredito = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código del préstamo que desea cancelar:"));
                        boolean cancelacionExitosa = listaClientes.cancelarCredito(codigoCredito);
                        if (!cancelacionExitosa) {
                            JOptionPane.showMessageDialog(null, "No se pudo cancelar el crédito. Verifique que:\n" +
                                    "- El código del préstamo existe\n" +
                                    "- El préstamo tiene cuotas vigentes");
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Error: Ingrese valores numéricos válidos.\n" +
                                "- El código del préstamo debe ser un número entero");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al cancelar el crédito: " + e.getMessage());
                    }
                    break;

                case "Revaluar Solicitudes":
                    try {
                        // Crear un nuevo árbol binario para la revaluación
                        BinaryTree arbolRevaluacion = new BinaryTree();

                        // Pasar todas las solicitudes de la cola al árbol
                        while (!colaSolicitudes.isEmpty()) {
                            SolicitudCredito solicitud = colaSolicitudes.obtenerSiguienteSolicitud();
                            arbolRevaluacion.add(solicitud);
                        }

                        // Revaluar las solicitudes en el árbol usando la lista de clientes
                        arbolRevaluacion.revaluarSolicitudes(listaClientes);

                        // Mostrar un mensaje de éxito
                        arbolRevaluacion.toString();
                        JOptionPane.showMessageDialog(null, "Solicitudes revaluadas y actualizadas" +
                                " según el árbol binario.");
                    } catch (Exception e) {
                        // Mostrar un mensaje de error en caso de que ocurra una excepción
                        JOptionPane.showMessageDialog(null, "Error al revaluar solicitudes: " + e.getMessage());
                    }
                    break;


                case "Enviar Factura":
                    try {
                        String idClienteFactura = JOptionPane.showInputDialog("Ingrese ID del cliente:");
                        Cliente clienteFactura = listaClientes.buscarCliente(idClienteFactura);
                        if (clienteFactura != null) {
                            archivo.enviarFactura(clienteFactura, listaClientes);
                        } else {
                            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al enviar factura: " + e.getMessage());
                    }
                    break;


                case "Mostrar Créditos por Cliente":
                    try {
                        String idBusqueda = JOptionPane.showInputDialog("Ingrese ID del cliente:");
                        listaClientes.mostrarCreditosCliente(idBusqueda);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al mostrar créditos: " + e.getMessage()); }
                    break;
                case "Porcentaje de Deuda por Tipo":
                    try {
                        String idClienteDeuda = JOptionPane.showInputDialog("Ingrese ID del cliente:");
                        listaClientes.porcentajeDeudaPorTipo(idClienteDeuda);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al calcular el porcentaje de deuda: " + e.getMessage()); }

                    break;
                case "Estadísticas de Créditos":
                    try {
                        double promedio = listaClientes.calcularPromedio();
                        double mediana = listaClientes.calcularMediana();
                        double moda = listaClientes.calcularModa();

                        JOptionPane.showMessageDialog(null, "Estadísticas de Créditos:\n" +
                                "Promedio: " + promedio + "\n" +
                                "Mediana: " + mediana + "\n" +
                                "Moda: " + moda);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al calcular las estadísticas: " + e.getMessage());
                    }

                    break;
                case "Guardar y Cargar Datos":
                    archivo.guardarDatos(listaClientes, colaSolicitudes);
                    listaClientes = archivo.cargarClientes();
                    colaSolicitudes = archivo.cargarSolicitudes(listaClientes);
                    JOptionPane.showMessageDialog(null, "Datos guardados exitosamente.");
                    break;


                case "Contar Préstamos Aprobados por Tipo":
                    BinaryTree arbolSolicitudes = new BinaryTree();
                    Node actual = colaSolicitudes.getFirst();

                    while (actual != null) {
                        if (actual.getData() instanceof SolicitudCredito) {
                            SolicitudCredito solicitud = (SolicitudCredito) actual.getData();
                            arbolSolicitudes.add(solicitud);
                        }
                        actual = actual.getLink();
                    }

                    // Creamos las listas para almacenar los resultados
                    Lista tipos = new Lista();
                    Lista counts = new Lista();

                    // Inicializamos las listas con todos los tipos de préstamos posibles
                    String[] tiposPrestamos = {"tarjeta", "libre inversion", "nomina", "hipotecario"};
                    for (String tipo : tiposPrestamos) {
                        tipos.AddLast(tipo);
                        counts.AddLast(0);
                    }

                    // Debug: Imprimir el contenido del árbol antes de contar
                    System.out.println("Contenido del árbol antes de contar:");
                    arbolSolicitudes.inOrder(); // Metodo inOrder para imprimir el contenido del arbol

                    // Contamos los préstamos no rechazados por tipo
                    arbolSolicitudes.countNonRejectedLoansByType(tipos, counts);

                    // Construimos el mensaje de resultado
                    StringBuilder resultado = new StringBuilder("Cantidad de Préstamos por Tipo:\n");
                    Node tipoActual = tipos.getFirst();
                    Node countActual = counts.getFirst();

                    while (tipoActual != null && countActual != null) {
                        resultado.append(tipoActual.getData()).append(": ")
                                .append(countActual.getData()).append("\n");
                        tipoActual = tipoActual.getLink();
                        countActual = countActual.getLink();
                    }

                    JOptionPane.showMessageDialog(null, resultado.toString());
                    break;

                case "Salir":
                    JOptionPane.showMessageDialog(null, "¡Gracias por usar el sistema!");
                    break;
            }

        } while (!opcion.equals("Salir"));
    }
}
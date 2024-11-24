package org.example;

import javax.swing.*;
import java.io.Serializable;
import java.text.DecimalFormat;

public class Lista implements Serializable { private static final long serialVersionUID = 1L;
    private Node first;
    private Node last;
    private NodeSolicitudCredito primero;

    public Lista() {
        this.first = null;
    }

    public NodeSolicitudCredito getPrimero() {
        return primero;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public boolean isEmpty() {
        return first == null;
    }

    // Agregar cliente al final de la lista
    public void agregarCliente(Cliente cliente) {
        Node newNode = new Node(cliente);
        if (first == null) {
            first = last = newNode;
        } else {
            // Verificar si el cliente ya existe
            Node current = first;
            while (current != null) {
                if (current.getData() instanceof Cliente) {
                    Cliente clienteExistente = (Cliente) current.getData();
                    if (clienteExistente.getId().equals(cliente.getId())) {
                        throw new RuntimeException("Ya existe un cliente con esa identificación");
                    }
                }
                current = current.getLink();
            }
            // Si no existe, agregarlo al final
            last.setLink(newNode);
            last = newNode;
        }
    }

    public void AddLast(Object data) {
        Node newNode = new Node(data);
        if (first == null) {
            first = last = newNode;
        } else {
            last.setLink(newNode);
            last = newNode;
        }
    }

    public void agregarSolicitudAprobada(SolicitudCredito solicitud) {
        AddLast(solicitud); // Metodo para agregar la solicitud a la lista
    }


    /**public void agregar(Object data) {
        Node nuevoNodo = new Node(data);
        if (first == null) {
            first = nuevoNodo;
        } else {
            Node actual = first;
            while (actual.getLink() != null) {
                actual = actual.getLink();
            }
            actual.setLink(nuevoNodo);
        }
    }**/
    public void AddFirst(Object data)
    {
        if(isEmpty())
            first=new Node(data);
        else
        {
            Node n = new Node(data);
            n.setLink(first);
            first=n;
        }
    }

    public Node Last()
    {
        Node aux=first;
        while(aux!=null && aux.getLink()!=null)
        {
            aux=aux.getLink();
        }
        return aux;
    }

    /**public void AddLast(Object data)
    {
        if(isEmpty())
            AddFirst(data);
        else
        {
            Last().setLink(new Node(data));
        }
    }*/


    // Buscar cliente por ID
    public Cliente buscarCliente(String id) {
        Node current = first;
        while (current != null) {
            if (current.getData() instanceof Cliente) {
                Cliente cliente = (Cliente) current.getData();
                if (cliente.getId().equals(id)) {
                    return cliente;
                }
            }
            current = current.getLink();
        }
        return null;
    }

    // Eliminar cliente por ID
    public boolean eliminarCliente(String id) {
        if (isEmpty()) {
            return false;
        }

        if (((Cliente) first.getData()).getId().equals(id)) {
            first = first.getLink();
            return true;
        }

        Node current = first;
        Node prev = null;

        while (current != null && !((Cliente) current.getData()).getId().equals(id)) {
            prev = current;
            current = current.getLink();
        }

        if (current != null) {
            prev.setLink(current.getLink());
            return true;
        }

        return false;
    }

    // Modificar cliente
    public boolean modificarCliente(String id, Cliente nuevosDatos) {
        Node current = first;
        while (current != null) {
            Cliente cliente = (Cliente) current.getData();
            if (cliente.getId().equals(id)) {
                cliente.setNombre(nuevosDatos.getNombre());
                cliente.setDireccion(nuevosDatos.getDireccion());
                cliente.setTelefono(nuevosDatos.getTelefono());
                cliente.setTipoContrato(nuevosDatos.getTipoContrato());
                cliente.setSalarioMensual(nuevosDatos.getSalarioMensual());
                return true;
            }
            current = current.getLink();
        }
        return false;
    }

    // Listar todos los clientes
    @Override
    public String toString() {
        if (isEmpty()) {
            return "No hay clientes registrados";
        }

        StringBuilder result = new StringBuilder("Lista de Clientes:\n");
        Node current = first;
        while (current != null) {
            result.append(current.getData().toString()).append("\n");
            current = current.getLink();
        }
        return result.toString();
    }

    // Metodo corregido para obtener el total de cuotas de un cliente específico
    public double obtenerTotalCuotasCliente(Cliente cliente) {
        double totalCuotas = 0.0;

        // Iteramos sobre cada crédito del cliente y calculamos el total de las cuotas
        NodeSolicitudCredito actualCredito = getPrimero(); // suponiendo que tienes este metodo en `Cliente`
        while (actualCredito != null) {
            totalCuotas += actualCredito.getCredito().calcularCuotaMensual();
            actualCredito = actualCredito.getLink();
        }

        return totalCuotas;
    }

    public void agregarCreditoAprobado(SolicitudCredito credito) {
        AddLast(credito);
        System.out.println("Crédito aprobado agregado: " + credito.getCodigo()); // Línea de depuración
    }


    public boolean pagarCuota(int codigoCredito, double montoPago) {
        if (isEmpty()) {
            return false;
        }
        Node actual = first;
        while (actual != null) {
            if (actual.getData() instanceof SolicitudCredito) {
                SolicitudCredito credito = (SolicitudCredito) actual.getData();
                System.out.println("Comparando: " + credito.getCodigo() + " con " + codigoCredito);
                if (credito.getCodigo() == codigoCredito) {
                    Cuota proximaCuota = credito.getProximaCuotaPendiente();
                    if (proximaCuota == null) {
                        JOptionPane.showMessageDialog(null, "No hay cuotas pendientes para este crédito.");
                        return false;
                    }
                    double saldoPendiente = proximaCuota.getSaldoPendiente();
                    System.out.println("Saldo pendiente antes del pago: " + saldoPendiente);
                    if (montoPago >= saldoPendiente) {
                        double excedente = proximaCuota.pagar(saldoPendiente);
                        montoPago -= saldoPendiente;
                        proximaCuota.setEstado("pagada");
                        System.out.println("Cuota pagada: " + proximaCuota.getNumero() + " Estado: " + proximaCuota.getEstado());
                        System.out.println("Saldo pendiente después del pago completo: " + proximaCuota.getSaldoPendiente());
                        while (excedente > 0) {
                            Cuota siguienteCuota = credito.getProximaCuotaPendiente();
                            if (siguienteCuota != null) {
                                System.out.println("Excedente antes del siguiente pago: " + excedente);
                                double saldoAnterior = siguienteCuota.getSaldoPendiente();
                                excedente = siguienteCuota.pagar(excedente);
                                double saldoRestanteSiguienteCuota = siguienteCuota.getSaldoPendiente();
                                System.out.println("Saldo de la siguiente cuota antes del pago: " + saldoAnterior);
                                System.out.println("Saldo de la siguiente cuota después del pago: " + saldoRestanteSiguienteCuota); //sigue dando 0
                                if (saldoRestanteSiguienteCuota == 0) {
                                    siguienteCuota.setEstado("pagada");
                                    System.out.println("Cuota siguiente pagada: " + siguienteCuota.getNumero() + " Estado: " + siguienteCuota.getEstado());
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Excedente de $" + excedente + " aplicado a la siguiente cuota.");
                                break;
                            }
                        }
                        return true;
                    } else {
                        double saldoRestanteAntes = proximaCuota.getSaldoPendiente();
                        proximaCuota.pagar(montoPago);
                        double saldoRestante = proximaCuota.getSaldoPendiente();
                        System.out.println("Pago parcial procesado para la cuota " + proximaCuota.getNumero() + ". Saldo restante antes del pago: $" + saldoRestanteAntes + ", saldo restante después del pago: $" + saldoRestante);
                        JOptionPane.showMessageDialog(null, "Pago parcial procesado. Resta $" + saldoRestante + " para completar la cuota " + proximaCuota.getNumero());
                        return true;
                    }
                }
            }
            actual = actual.getLink();
        }
        JOptionPane.showMessageDialog(null, "Crédito no encontrado con el código: " + codigoCredito);
        return false;
    }

    public boolean cancelarCredito(int codigoCredito) {
        if (isEmpty()) {
            return false;
        }
        Node actual = first;
        while (actual != null) {
            if (actual.getData() instanceof SolicitudCredito) {
                SolicitudCredito credito = (SolicitudCredito) actual.getData();
                if (credito.getCodigo() == codigoCredito) {
                    double totalCapitalVigente = 0;
                    Node currentCuotaNode = credito.getCuotas().getFirst(); // Accede correctamente a la lista de cuotas
                    while (currentCuotaNode != null) {
                        Cuota cuota = (Cuota) currentCuotaNode.getData();
                        totalCapitalVigente += cuota.getCapitalVigente();
                        currentCuotaNode = currentCuotaNode.getLink();
                    }
                    double totalConDescuento = totalCapitalVigente * 0.9;
                    credito.marcarCuotasComoPagadas();
                    JOptionPane.showMessageDialog(null, "El total a cancelar, con el 10% de descuento, es: $" + totalConDescuento);
                    return true;
                }
            }
            actual = actual.getLink();
        }
        JOptionPane.showMessageDialog(null, "Crédito no encontrado con el código: " + codigoCredito);
        return false;
    }

    public void mostrarCreditosCliente(String idCliente) {
        // Busca al cliente por su ID
        Cliente cliente = buscarCliente(idCliente);

        // Si el cliente existe, procede a buscar sus créditos
        if (cliente != null) {
            StringBuilder resultado = new StringBuilder("Créditos del Cliente: " + cliente.getNombre() + "\n");

            // Recorre la lista de solicitudes de crédito
            Node actual = getFirst();
            while (actual != null) {
                // Verifica si el nodo actual contiene una SolicitudCredito
                if (actual.getData() instanceof SolicitudCredito) {
                    SolicitudCredito credito = (SolicitudCredito) actual.getData();

                    // Verifica si la solicitud pertenece al cliente buscado
                    if (credito.getTitular().getId().equals(idCliente)) {
                        resultado.append(credito.toString()).append("\n");
                    }
                }
                actual = actual.getLink(); // Avanza al siguiente nodo
            }

            // Muestra los créditos encontrados en un cuadro de diálogo
            if (resultado.toString().equals("Créditos del Cliente: " + cliente.getNombre() + "\n")) {
                JOptionPane.showMessageDialog(null, "No se encontraron créditos para el cliente: " + cliente.getNombre());
            } else {
                JOptionPane.showMessageDialog(null, resultado.toString());
            }
        } else {
            // Si no se encuentra el cliente, muestra un mensaje de error
            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
        }
    }

    public void porcentajeDeudaPorTipo(String idCliente) {
        Cliente cliente = buscarCliente(idCliente);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            return;
        }

        double deudaTotal = 0;
        Lista tipos = new Lista();  // Lista para almacenar los tipos de crédito
        Lista montos = new Lista(); // Lista para almacenar los montos correspondientes

        Node actual = getFirst();
        while (actual != null) {
            if (actual.getData() instanceof SolicitudCredito) {
                SolicitudCredito credito = (SolicitudCredito) actual.getData();

                if (credito.getTitular().getId().equals(idCliente)) {
                    deudaTotal += credito.getValor();

                    Node tipoActual = tipos.getFirst();
                    Node montoActual = montos.getFirst();
                    boolean tipoEncontrado = false;

                    while (tipoActual != null) {
                        if (tipoActual.getData().equals(credito.getTipo())) {
                            tipoEncontrado = true;
                            double montoExistente = (double) montoActual.getData();
                            montoExistente += credito.getValor();
                            montoActual.setData(montoExistente);
                            break;
                        }
                        tipoActual = tipoActual.getLink();
                        montoActual = montoActual.getLink();
                    }

                    if (!tipoEncontrado) {
                        tipos.AddLast(credito.getTipo());
                        montos.AddLast(credito.getValor());
                    }
                }
            }
            actual = actual.getLink();
        }

        if (deudaTotal > 0) {
            DecimalFormat df = new DecimalFormat("#.##");
            StringBuilder resultado = new StringBuilder("Porcentaje de Deuda por Tipo para " + cliente.getNombre() + ":\n");

            Node tipoActual = tipos.getFirst();
            Node montoActual = montos.getFirst();

            while (tipoActual != null && montoActual != null) {
                double porcentaje = ((double) montoActual.getData() / deudaTotal) * 100;
                resultado.append(tipoActual.getData())
                        .append(": ")
                        .append(df.format(porcentaje))
                        .append("%\n");

                tipoActual = tipoActual.getLink();
                montoActual = montoActual.getLink();
            }

            JOptionPane.showMessageDialog(null, resultado.toString());
        } else {
            JOptionPane.showMessageDialog(null, "El cliente no tiene deudas registradas.");
        }
    }

    public double calcularPromedio() {
        double suma = 0;
        int count = 0;
        Node actual = getFirst();
        while (actual != null) {
            if (actual.getData() instanceof SolicitudCredito) {
                SolicitudCredito credito = (SolicitudCredito) actual.getData();
                suma += credito.getValor();
                count++;
            }
            actual = actual.getLink();
        }
        return (count > 0) ? (suma / count) : 0;
    }

    public double calcularMediana() {
        Lista valores = new Lista();
        Node actual = getFirst();
        while (actual != null) {
            if (actual.getData() instanceof SolicitudCredito) {
                SolicitudCredito credito = (SolicitudCredito) actual.getData();
                valores.AddLast(credito.getValor());
            }
            actual = actual.getLink();
        }

        // Ordenar los valores de la lista
        valores = ordenarLista(valores);

        int n = contarNodos(valores);
        if (n == 0) return 0;
        Node middle = valores.getFirst();
        for (int i = 0; i < n / 2; i++) {
            middle = middle.getLink();
        }
        if (n % 2 == 1) {
            return (double) middle.getData();
        } else {
            double valor1 = (double) middle.getData();
            double valor2 = (double) middle.getLink().getData();
            return (valor1 + valor2) / 2.0;
        }
    }

    private Lista ordenarLista(Lista lista) {
        if (lista.isEmpty()) return lista;
        Lista sortedList = new Lista();
        Node current = lista.getFirst();
        while (current != null) {
            double value = (double) current.getData();
            Node sortedCurrent = sortedList.getFirst();
            Node previous = null;
            while (sortedCurrent != null && (double) sortedCurrent.getData() < value) {
                previous = sortedCurrent;
                sortedCurrent = sortedCurrent.getLink();
            }
            Node newNode = new Node(value);
            if (previous == null) {
                newNode.setLink(sortedList.getFirst());
                sortedList.setFirst(newNode);
            } else {
                newNode.setLink(sortedCurrent);
                previous.setLink(newNode);
            }
            current = current.getLink();
        }
        return sortedList;
    }

    private int contarNodos(Lista lista) {
        int count = 0;
        Node actual = lista.getFirst();
        while (actual != null) {
            count++;
            actual = actual.getLink();
        }
        return count;
    }

    public double calcularModa() {
        Lista valores = new Lista();
        Node actual = getFirst();
        while (actual != null) {
            if (actual.getData() instanceof SolicitudCredito) {
                SolicitudCredito credito = (SolicitudCredito) actual.getData();
                valores.AddLast(credito.getValor());
            }
            actual = actual.getLink();
        }

        double moda = 0;
        int maxFrecuencia = 0;
        Node current = valores.getFirst();
        while (current != null) {
            double valor = (double) current.getData();
            int frecuencia = contarFrecuencia(valores, valor);
            if (frecuencia > maxFrecuencia) {
                maxFrecuencia = frecuencia;
                moda = valor;
            }
            current = current.getLink();
        }
        return moda;
    }

    private int contarFrecuencia(Lista lista, double valor) {
        int frecuencia = 0;
        Node actual = lista.getFirst();
        while (actual != null) {
            if ((double) actual.getData() == valor) {
                frecuencia++;
            }
            actual = actual.getLink();
        }
        return frecuencia;
    }


}
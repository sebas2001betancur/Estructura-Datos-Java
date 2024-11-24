package org.example;

import java.io.Serializable;

public class Queue implements Serializable {
    private static final long serialVersionUID = 1L;
    private Node first;
    private Node last;

    public Queue() {
        this.first = null;
        this.last = null;
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }


    // Agregar solicitud al final de la cola (Enqueue)
   public void agregarSolicitud(SolicitudCredito solicitud) {
        Node newNode = new Node(solicitud);
        if (first == null) {
            first = last = newNode;
        } else {
            last.setLink(newNode);
            last = newNode;
        }
    }

    public SolicitudCredito obtenerSiguienteSolicitud() {
        if (first == null) {
            return null;
        }
        SolicitudCredito solicitud = (SolicitudCredito) first.getData();
        first = first.getLink();
        if (first == null) {
            last = null;
        }
        return solicitud;
    }

    // Ver la primera solicitud sin removerla (Peek)
    public SolicitudCredito verSiguienteSolicitud() {
        if (isEmpty()) {
            return null;
        }
        return (SolicitudCredito) first.getData();
    }

    // Contar número de solicitudes en la cola
    public int size() {
        int count = 0;
        Node current = first;
        while (current != null) {
            count++;
            current = current.getLink();
        }
        return count;
    }

    // Mostrar todas las solicitudes en la cola
    @Override
    public String toString() {
        if (isEmpty()) {
            return "No hay solicitudes pendientes";
        }

        StringBuilder result = new StringBuilder("Solicitudes en cola:\n");
        Node current = first;
        while (current != null) {
            result.append(current.getData().toString()).append("\n");
            current = current.getLink();
        }
        return result.toString();
    }
}


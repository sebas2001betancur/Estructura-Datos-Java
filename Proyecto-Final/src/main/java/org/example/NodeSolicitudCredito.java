package org.example;

import java.io.Serializable;

public class NodeSolicitudCredito implements Serializable { private static final long serialVersionUID = 1L;

    private SolicitudCredito credito;
    private NodeSolicitudCredito Link;

    public SolicitudCredito getCredito() {
        return credito;
    }

    public void setCredito(SolicitudCredito credito) {
        this.credito = credito;
    }

    public NodeSolicitudCredito getLink() {
        return Link;
    }

    public void setLink(NodeSolicitudCredito link) {
        Link = link;
    }
}

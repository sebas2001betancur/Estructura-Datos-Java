package grafo;

public class Vertice {
    private Object dato;
    private List adyacencia;

    public Vertice(Object dato) {
        this.dato = dato;
        adyacencia = new List();
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public List getAdyacencia() {
        return adyacencia;
    }

    public void setAdyacencia(List adyacencia) {
        this.adyacencia = adyacencia;
    }
    
    
    
}

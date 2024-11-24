package quiz3;

public class Node {
    private Object data;
    private Node link;
    
    private String dato;
    
    public Node(Object data) {
        this.data = data;
        link = null;  //por defecto en null
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
    
    
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Node getLink() {
        return link;
    }
    public void setLink(Node link) {
        this.link = link;
    }  
    
    
    
}

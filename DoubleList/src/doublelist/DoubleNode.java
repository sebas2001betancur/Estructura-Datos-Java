package doublelist;

public class DoubleNode {
    private DoubleNode previous;
    private Object data;
    private DoubleNode next;

    public DoubleNode(Object data) {
        this.data = data;
        previous =null;
        next = null;
    }

    public DoubleNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode previous) {
        this.previous = previous;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }
    
    
    
    
}

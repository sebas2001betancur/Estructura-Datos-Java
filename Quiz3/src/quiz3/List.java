package quiz3;



public class List {
    private Node first;

    public List() {
    }

    public Node getFirst() {
        return first;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void AddFirst(Object data) {
        if (isEmpty()) {
            first = new Node(data);
        } else {
            Node n = new Node(data);
            n.setLink(first);
            first = n;
        }
    }

    public void AddLast(Object data) {
        if (isEmpty()) {
            AddFirst(data);
        } else {
            Node last = getLast();
            last.setLink(new Node(data));
        }
    }

    private Node getLast() {
        Node aux = first;
        while (aux.getLink() != null) {
            aux = aux.getLink();
        }
        return aux;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        Node aux = first;
        while (aux != null) {
            output.append(aux.getData().toString()).append("\n");
            aux = aux.getLink();
        }
        return output.toString();
    }
}

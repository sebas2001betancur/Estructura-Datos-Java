public class Queue {

    private Node first;
    private Node last;

    public Queue() {
    }

    public boolean isEmpty()
    {
        return first==null && last==null;
    }

    public void Enqueue(Object data)
    {
        if(isEmpty())
        {
            first=new Node(data);
            last=first;
        }
        else
        {
            Node n= new Node(data);
            last.setLink(n);
            last=n;
        }
    }

    public String toString() {
        StringBuilder text = new StringBuilder();
        Node aux = first;

        while (aux != null) {
            Barco b = (Barco) aux.getData();
            text.append(b.toString()).append("\n");
            aux = aux.getLink();
        }

        return text.toString();
    }



    public Object Dequeue()
    {
        Object data=null;
        if(!isEmpty())
        {
            data = first.getData();
            first = first.getLink();
            if(first==null)
                last=null;
        }
        return data;
    }
}

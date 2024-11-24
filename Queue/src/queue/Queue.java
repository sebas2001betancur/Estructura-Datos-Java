package queue;

public class Queue {
    private Node first;//donde sacamos
    private Node last; //donde metemos

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

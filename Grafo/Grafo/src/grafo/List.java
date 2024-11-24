package grafo;

public class List {
    private Node first;

    public List() {
    }

    public Node getFirst() {
        return first;
    }
    
    public boolean isEmpty()
    {
        return first==null;
    }
    
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
    
    public String toString()
    {
        String text="";
        Node aux=first;
        while(aux!=null)
        {
            text=text+((Vertice)aux.getData()).getDato()+", ";
            aux= aux.getLink();
        }
        return text;
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
    
    public void AddLast(Object data)
    {
        if(isEmpty())
           AddFirst(data);
        else
        {
            Last().setLink(new Node(data));
        }
    }
    
    public int Size()
    {
        int count=0;
        Node aux = first;
        while(aux!=null)
        {
            count++;
            aux=aux.getLink();
        }
        return count;
    }
    
    public void AddPos(Object data, int pos)
    {
        if(pos==1)
            AddFirst(data);
        else
        {
            if(pos==Size()+1)
                AddLast(data);
            else
            {
                int count=1;
                Node aux=first, pre=null;
                while(aux!=null && count!=pos)
                {
                    pre=aux;
                    aux=aux.getLink();
                    count++;
                }
                Node n=new Node(data);
                n.setLink(aux);
                pre.setLink(n);
            }
        }
            
    }
    
    public boolean DeleteFirst()
    {
        if(!isEmpty())
        {
            first=first.getLink();
            return true;
        }
        return false;
    }
    
    public Node Previous(Node search)
    {
        
        
        Node aux=first, pre=null;
        while(aux!=null  && aux!=search)
        {
            pre=aux;
            aux=aux.getLink();
        }
        return pre;
    }
    
    public boolean DeleteLast()
    {
        if(!isEmpty())
        {
            Node pre=Previous(Last());
            if(pre!=null)
                pre.setLink(null);
            else
                first=null;
            return true;
        }
        return false;
    }
   
    
    public Vertice SearchV(String nombre)
    {
        Vertice v = null;
        Node aux=first;
        while(aux!=null)
        {
            if(((Vertice)aux.getData()).getDato().equals(nombre))
                v=(Vertice)aux.getData();
            aux=aux.getLink();
        }
        return v;
    }
   
}

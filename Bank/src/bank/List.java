package bank;

public class List {
    private Node first;

    public List() {
    }
    
    public boolean isEmpty()
    {
        return first==null;
    }
    
    public void AddFirst(SavingsAccount data)
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
            text=text+aux.getData()+"\n";
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
    
    public void AddLast(SavingsAccount data)
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
    
    public void AddPos(SavingsAccount data, int pos)
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
   
    public void Add(SavingsAccount data)
    {
        Node n = new Node(data);
        if(isEmpty())
            first=n;
        else
        {
            //recorremos la lista buscando donde se inserta
            Node aux=first;
            while(aux!=null &&
                    data.getClient().compareTo(aux.getData().getClient())>0)
                aux=aux.getLink();
            
            //comparamos el valor del aux
            if(aux==null)
                AddLast(data);
            else
            {
                Node pre=Previous(aux);
                if(pre==null)
                    AddFirst(data);
                else
                {
                    pre.setLink(n);
                    n.setLink(aux);
                }
            }
        }
    }
    
    public SavingsAccount Search(String number)
    {
        Node aux = first;
        while(aux!=null && !aux.getData().getNumber().equals(number))
            aux=aux.getLink();
        
        if(aux==null)
            return null;
        else
            return aux.getData();
                
    }
    
    public boolean Delete (String number)
    {
        Node aux=first;
        while(aux!=null && !aux.getData().getNumber().equals(number))
            aux=aux.getLink();
        
        if(aux!=null)
        {
            Node pre=Previous(aux);
            if(pre!=null)
                pre.setLink(aux.getLink());
            else
                DeleteFirst();
            
            return true;
        }
        return false;
    }
    
    public double Total()
    {
        Node aux=first;
        double total=0;
        while(aux!=null)
        {
            total+=aux.getData().getValue();
            aux=aux.getLink();
        }
        return total;
    }
}

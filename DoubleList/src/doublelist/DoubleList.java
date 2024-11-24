package doublelist;

public class DoubleList {
    private DoubleNode first;
    private DoubleNode last;

    public DoubleList() {
    }
    
    public boolean isEmpty()
    {
        return first==null && last==null;
    }
    
    public void AddFirst(Object data)
    {
        if(isEmpty())
        {
            first = new DoubleNode(data);
            last= first;
        }
        else
        {
            DoubleNode n = new DoubleNode(data);
            n.setNext(first);
            first.setPrevious(n);
            first=n;
        }
    }
    
    public void AddLast(Object data)
    {
        if(isEmpty())
        {
            first = new DoubleNode(data);
            last= first;
        }
        else
        {
            DoubleNode n = new DoubleNode(data);
            last.setNext(n);
            n.setPrevious(last);
            last=n;
        }
    }
    
    public boolean DeleteFirst()
    {
        if(!isEmpty())
        {
            first=first.getNext();
            if(first!=null)            
                first.setPrevious(null);
            else
                last=null;
            return true;            
        }
        return false;
    }
    
    public boolean DeleteLast()
    {
        if(!isEmpty())
        {
            last=last.getPrevious();
            if(last!=null)            
                last.setNext(null);
            else
                first=null;
            return true;            
        }
        return false;
    }
    
    public String toString()
    {
        String text="";
        DoubleNode aux=first;
        while(aux!=null)
        {
            text=text + aux.getData()+"\n";
            aux=aux.getNext();
        }
        return text;
    }
    
    public int Size()
    {
        int cont=0;
        DoubleNode aux = last;
        while(aux!=null)
        {
            cont++;
            aux=aux.getPrevious();
        }
        return cont;
    }
    
    public void Add(Object data)
    {
        Advice ad = (Advice)data;
        if(isEmpty())
            AddFirst(ad);
        else
        {
            //recorremos la lista buscando donde insertar
            DoubleNode aux= first;
            while(aux!=null &&
                    ((Advice)aux.getData()).getDate().isBefore(ad.getDate()))
                aux=aux.getNext();
            
            //verificamos en que posicion quedo primera, ultima o intermedio
            if(aux!=null)
            {
                if(aux.getPrevious()==null) //primera
                    AddFirst(ad);
                else  //intermedia
                {
                    DoubleNode n = new DoubleNode(data);
                    n.setNext(aux);
                    n.setPrevious(aux.getPrevious());
                    aux.getPrevious().setNext(n);
                    aux.setPrevious(n);
                }
            }
            else
            {
                AddLast(ad);
            }            
        }
    }
    
    public void Delete(String theme)
    {
        DoubleNode aux = first;
        while(aux!=null)
        {
            if(((Advice)aux.getData()).getTheme().equals(theme))
            {
                if(aux.getPrevious()==null)
                    DeleteFirst();
                else
                {
                    if(aux.getNext()==null)
                        DeleteLast();
                    else
                    {
                        aux.getPrevious().setNext(aux.getNext());
                        aux.getNext().setPrevious(aux.getPrevious());
                    }
                }
            }
            aux=aux.getNext();
        }
    
    }
    
    public DoubleList List(String name)
    {
       DoubleList student=new DoubleList();
       DoubleNode aux = last;
       while(aux!=null)
       {
           if(((Advice)aux.getData()).getName().equals(name))
               student.AddFirst(aux.getData());
           aux=aux.getPrevious();
       }
       return student;
    }
    
    public Advice Search(int code)
    {
        DoubleNode aux = first;
        while(aux!=null && 
                ((Advice)aux.getData()).getCode()!=code)
        {
            aux=aux.getNext();
        }
        if(aux!=null)
            return (Advice)aux.getData();
        else
            return null;
    }
    
    public int Amount(String subject)
    {
        DoubleNode aux=first;
        int count=0;
        while(aux!=null)
        {
            if(((Advice)aux.getData()).getSubject().equals(subject))
                count++;
            
            aux=aux.getNext();
        }
        return count;
    }
}

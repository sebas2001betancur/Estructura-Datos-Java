package queue;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
       Queue so = new Queue();
       Process p = null;
       String menu[]={"Create","toString","Process","toFirst",
           "time total","Percentage","Exit"};
       String option;
       double timepc=1, timepg=0.4;  //se puede preguntar al usuario
       String description;
       int number=100;
       do{
           option=(String)JOptionPane.showInputDialog(null,"Selected:",
                   "Main", 1, null, menu, menu[0]);
           switch(option)
           {
               case "Create":
                   description=JOptionPane.showInputDialog("Enter description");
                   double tr=Math.random()*4;
                   int pri = (int)(Math.random()*2);
                   p = new Process(number, description, tr, pri);
                   so.Enqueue(p);
                   number++;
                   JOptionPane.showMessageDialog(null,"created process \n:" +
                           p.toString());
                   break;
               case "Process":
                   if(!so.isEmpty())
                   {
                       p=(Process)so.Dequeue();
                       //incrementamos el tiempo asignado al proceso
                       if(p.getPriority()==0)
                           p.setTimea(p.getTimea()+timepc);
                       else
                           p.setTimea(p.getTimea()+timepg);
                       //verificamos si el proceso termino
                       if(p.getTimer()>p.getTimea())
                       {
                           so.Enqueue(p);
                           JOptionPane.showMessageDialog(null,"pending process");
                       }
                       else
                           JOptionPane.showMessageDialog(null,"executed process");
                                           
                   }
                   else
                       JOptionPane.showMessageDialog(null,"the process doesn´t "
                               + "exist ");
                   break;
                   
               case "toString":
                   JOptionPane.showMessageDialog(null,toString(so));
                   break;
               case "toFirst":
                   int code=Integer.parseInt(JOptionPane.showInputDialog("Enter code"));
                   toFirst(so, code);
                   JOptionPane.showMessageDialog(null,"prioritized process");
                   break;
                   
                   
           }
       }while(!option.equals("Exit"));
       
       
    }
    
    public static String toString (Queue q)
    {
        Queue aux = new Queue();
        String text="";
        //recorremos la cola
        while(!q.isEmpty())
        {
            Process p =(Process)q.Dequeue();
            text = text + p.toString()+"\n";
            aux.Enqueue(p);
        }
        while(!aux.isEmpty())
            q.Enqueue(aux.Dequeue());
        
        return text;
    }
    
    public static void toFirst(Queue q, int code)
    {
        Queue aux=new Queue();
        Process p, first=null;
        while(!q.isEmpty())
        {
            p=(Process)q.Dequeue();
            if(p.getCode()==code)
                first=p;
            else
                aux.Enqueue(p);
        }
        if(first!=null)
            q.Enqueue(first);
        
        while(!aux.isEmpty())
            q.Enqueue(aux.Dequeue());        
    }
    
}

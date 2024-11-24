package archivos;

import java.io.*;
public class Archivo {
    private File f;
    private FileReader fr;
    private FileWriter fw;
    private BufferedReader br;
    private BufferedWriter bw;
    
    public void EscribirLista(List lista)
    {
        f=new File("profesores.txt"); //paso 1
        try{
            fw=new FileWriter(f); //paso 2
            bw = new BufferedWriter(fw); //paso 3
            //paso 4
            Node aux = lista.getFirst();
            while(aux!=null)
            {
                Profesor p = (Profesor)aux.getData();
                bw.write(p.getNombre()+"\t"+p.getTitulo()+"\t" +
                        p.getTiempovin() + "\n");
                aux=aux.getLink();
            }
            bw.close(); //paso 5
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }       
        
    }
    
    public List LeerLista()
    {
        List lista = new List();
        f=new File("profesores.txt");
        String registro, campos[];
        try{
            fr=new FileReader(f);
            br = new BufferedReader(fr);
            //paso 4
            while((registro=br.readLine())!=null)
            {
                campos=registro.split("\t");
                Profesor p = new Profesor(campos[0],
                    campos[1], Integer.parseInt(campos[2]));
                lista.AddLast(p);
            }
            br.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return lista;
    }
    
    //Pila
    public Stack LeerPila(String ruta)
    {
        Stack pila = new Stack();
        f = new File(ruta);
        String registro, campos[];
        try
        {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            while((registro=br.readLine())!=null)
            {
                campos=registro.split("\t");
                pila.Push(new Profesor(campos[0],campos[1],
                        Integer.parseInt(campos[2])));
            }
            br.close();            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return pila;
    }
    
    public void EscribirPila(Stack pila, String ruta)
    {
        Stack aux = new Stack();
        f=new File(ruta);
        try{
            fw=new FileWriter(f);
            bw = new BufferedWriter(fw);
            while(!pila.isEmpty())
            {
                Profesor p=(Profesor)pila.Pop();
                bw.write(p.getNombre()+"\t"+p.getTitulo()+"\t"
                        + p.getTiempovin()+"\n");
                aux.Push(p);
            }
            while(!aux.isEmpty())
                pila.Push(aux.Pop());
            
            bw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public BinaryTree LeerArbol(String ruta)
    {
        BinaryTree bt= new BinaryTree();
        f = new File(ruta);
        String registro, campos[];
        try
        {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            while((registro=br.readLine())!=null)
            {
                campos=registro.split("\t");
                bt.Add(new Profesor(campos[0],campos[1],
                        Integer.parseInt(campos[2])));
            }
            br.close();            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return bt;
    }
    
    public void EscribirArbol(BinaryTree arbol, String ruta)
    {
        f=new File(ruta);
        try{
            fw=new FileWriter(f);
            bw = new BufferedWriter(fw);
            EscribirArbol(arbol.getRoot(),bw);
            bw.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void EscribirArbol(BinaryNode aux, BufferedWriter bw)
    {
       try{
        if(aux!=null)
        {
            Profesor p = (Profesor)aux.getData();
            bw.write(p.getNombre()+"\t"+p.getTitulo()+"\t"+p.getTiempovin()+"\n");
            EscribirArbol(aux.getLeft(), bw);
            EscribirArbol(aux.getRight(),bw);
        }
       }
       catch(IOException e)
       {
           e.printStackTrace();
       }
    }
    
}

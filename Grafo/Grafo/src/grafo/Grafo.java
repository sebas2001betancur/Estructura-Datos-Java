
package grafo;


public class Grafo {

  private List vertices;

    public Grafo() {
        vertices = new List();
    }
    
    
    public void AdicionarVertice(Object dato)
    {
        Vertice v = new Vertice(dato);
        vertices.AddLast(v);
    }
    
    
    public boolean AdicionarArista(String origen, String destino)
    {
            Vertice vi = vertices.SearchV(origen);
            Vertice vf = vertices.SearchV(destino);
            if(vi!=null && vf!=null)
            {
                vi.getAdyacencia().AddLast(vf);
                vf.getAdyacencia().AddLast(vi);
                return true;
            }
            return false;
    }
    
  @Override
    public String toString()
    {
        String text ="Vertice:          Adyacencia\n";
        Node aux=vertices.getFirst();
        while(aux!=null)
        {
            Vertice v = ((Vertice)aux.getData());
            text = text + v.getDato() + "->\t\t\t" + v.getAdyacencia().toString() +"\n";
            aux=aux.getLink();
        }
        return text;
    }
    
    
  
  
    
}

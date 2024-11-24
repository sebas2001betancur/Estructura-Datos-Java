package org.example;

import javax.swing.*;

public class Stack {

    private Queue SolicitudCredito;
    private Node top;

    public Stack() {
        SolicitudCredito = new Queue();
    }

    public boolean isEmpty() {
        return top == null;
    }


    public void Push(Object data) {
        if (isEmpty())
            top = new Node(data);
        else {
            Node n = new Node(data);
            n.setLink(top);
            top = n;
        }
    }



    public Object Pop()
    {
        Object data=null;
        if(!isEmpty())
        {
            data=top.getData();
            top=top.getLink();
            return data;
        }
        return null;
    }

}


package org.example;

import java.io.Serializable;

public class Node implements Serializable { private static final long serialVersionUID = 1L;
    private Object data;
    private Node link;

    public Node(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }


}
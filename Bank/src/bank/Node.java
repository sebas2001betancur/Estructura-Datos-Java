
package bank;

public class Node {
    private SavingsAccount data;
    private Node link;

    public Node(SavingsAccount data) {
        this.data = data;
    }

    public SavingsAccount getData() {
        return data;
    }

    public void setData(SavingsAccount data) {
        this.data = data;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }
    
    
}

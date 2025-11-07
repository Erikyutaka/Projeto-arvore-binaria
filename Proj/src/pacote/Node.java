package pacote;

public class Node {
    private Palavra elemento;
    private Node left;
    private Node right;
    //inclusão do nó pai
    private Node pai;

    public Node(Palavra elemento) {
        this.elemento = elemento;
        this.left = null;
        this.right = null;
    }

    public  Node getLeft(){
        return left;
    }
    public  Node getRight(){
        return right;
    }
    public  void setLeft(Node novo){
        left = novo;
    }
    public  void setRight(Node novo){
        right = novo;
    }

    public Palavra getElemento(){
        return elemento;
    }
}


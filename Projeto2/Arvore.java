public class Arvore {
    private Node root;
    private int totalLinha;
    private int totalPalavra;

    public Arvore(){
        this.root = null;
        this.totalLinha = 0;
        this.totalPalavra = 0;
    }

    public int getTotalLinhas(){
        return totalLinha;
    }
    public void setTotalLinhas(int totalLinha){
        this.totalLinha = totalLinha++;
    }
    
    public int getTotalPalavra(){
        return totalPalavra;
    }
    public void setTotalPalavras(int totalPalavra){
        this.totalPalavra = totalPalavra++;
    }

    private Node inserir(Node node, Palavra palavra){
        if(node == null){
            return new Node(palavra);
        }
        
        int comparaResultado = palavra.getPalavra().compareTo(node.getElemento().getPalavra());
        if(comparaResultado < 0){
            node.setLeft(inserir(node.getLeft(), palavra));
        }else if(comparaResultado < 0){
            node.setRight(inserir(node.getRight(), palavra));
        }
    }

}

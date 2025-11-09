package pacote;
public class Arvore {
    private Node root;
    private int totalPalavrasDistintas;
    private long totalOcorrencias;

    public Arvore(){
        this.root = null;
        this.totalPalavrasDistintas = 0;
        this.totalOcorrencias= 0;
    }

	public int getTotalPalavrasDistintas() {
		return totalPalavrasDistintas;
	}

	public void setTotalPalavrasDistintas(int totalPalavrasDistintas) {
		this.totalPalavrasDistintas = totalPalavrasDistintas;
	}

	public long getTotalOcorrencias() {
		return totalOcorrencias;
	}

	public void setTotalOcorrencias(long totalOcorrencias) {
		this.totalOcorrencias = totalOcorrencias;
	}

	
	//implementação das classes de inserir:
	
    public void inserir(Palavra novaPalavra) {
    	this.root = inserirRecursivo(this.root, novaPalavra);
    	this.totalOcorrencias++;
    }
    
    private Node inserirRecursivo(Node noAtual, Palavra novaPalavra) {
    	if(noAtual == null) {
    		this.totalPalavrasDistintas++;
    		return new Node(novaPalavra);
    	}
    	
    	int compara = novaPalavra.compareTo(noAtual.getElemento());
    	
    	if(compara < 0) {
    		noAtual.setLeft(inserirRecursivo(noAtual.getLeft(), novaPalavra));
    	}else if(compara > 0){
    		noAtual.setRight(inserirRecursivo(noAtual.getRight(), novaPalavra));
    	}else {
    		//se a palavra já existir:
    		noAtual.getElemento().acrescentarOcorrencia();
    	}
    	return noAtual;
    }
    
    public Palavra buscaPalavraEspecifica(String palavra) {
    	Palavra palavraBusca = new Palavra(palavra);
    	return buscarExatoRecursivo(this.root, palavraBusca);
    }
    
    public Palavra buscarExatoRecursivo(Node noAtual, Palavra palavraBusca) {
		if(noAtual == null) {
			return null;
		}
		
		int compara = palavraBusca.compareTo(noAtual.getElemento());
		
		if(compara == 0) {
			return noAtual.getElemento();
		}else if(compara < 0){
			return buscarExatoRecursivo(noAtual.getLeft(), palavraBusca);
		}else {
			return buscarExatoRecursivo(noAtual.getRight(), palavraBusca);
		}
    		
    }
    
    public int buscarEImprimirPalavraParcial(String partePalavra) {
    	final String partePalavraLowerCase = partePalavra.toLowerCase();
    	System.out.println("\n Palavras encontradas que contêm '" + partePalavra + "':");
    	System.out.println("----------------------------------------------");
    	int encontradas = buscarParcialRecursivo(this.root, partePalavraLowerCase);
    	System.out.println("----------------------------------------------");
    	return encontradas;
    }
    
    public int buscarParcialRecursivo(Node noAtual, String partePalavraLowerCase) {
    	if(noAtual == null) {
    		return 0;
    	}
    	
    	int count = 0;
    	
    	count += buscarParcialRecursivo(noAtual.getLeft(), partePalavraLowerCase);
    	
    	if(noAtual.getElemento().getPalavra().toLowerCase().contains(partePalavraLowerCase)) {
    		System.out.println(noAtual.getElemento());
    		count++;
    	}
    	
    	count += buscarParcialRecursivo(noAtual.getRight(), partePalavraLowerCase);
    	
    	return count;
    }
    
    public long calcularSomaCaracteres() {
    	return calcularSomaCaracteresRecursivo(this.root);
    }
    
    public long calcularSomaCaracteresRecursivo(Node noAtual) {
    	if(noAtual == null) {
    		return 0;
    	}
    	
    	long soma = (long) noAtual.getElemento().getNumeroCaracteres() * noAtual.getElemento().getOcorrencias();
    	soma += calcularSomaCaracteresRecursivo(noAtual.getLeft());
    	soma += calcularSomaCaracteresRecursivo(noAtual.getRight());
    	
    	return soma;
    }
    
    public void exibirEmOrdem() {
    	System.out.println("Palavras em ordem alfabética (Percurso em-ordem): ");
    	System.out.println("------------------------------------");
    	exibirEmOrdemRecursivo(this.root);
    	System.out.println("------------------------------------");
    }
    
    public void exibirEmOrdemRecursivo(Node noAtual) {
    	if(noAtual != null) {
    		exibirEmOrdemRecursivo(noAtual.getLeft());
    		System.out.println(noAtual.getElemento());
    		exibirEmOrdemRecursivo(noAtual.getRight());
    	}
    }
    
    
    //Função inventada: Palavra mais longa
    public void encontrarPalavrasMaisLongasEImprimir() {
    	if(this.root == null) {
    		System.out.println("Árvore vazia");
    		return;
    	}
    	
    	int maxCaracteres = encontrarMaxCaracteresRecursivo(this.root);
    	
    	System.out.println("--------------------------------------");
    	System.out.println("Palavra(s) com " + maxCaracteres + " caracteres (máximo encontrado)");
    	
    	imprimirPalavrasComTamanho(this.root, maxCaracteres);
    	System.out.println("---------------------------------------");
    }
    
    private int encontrarMaxCaracteresRecursivo(Node noAtual) {
    	if(noAtual == null) {
    		return 0;
    	}
    	
    	int maxEsq = encontrarMaxCaracteresRecursivo(noAtual.getLeft());
    	int maxDir = encontrarMaxCaracteresRecursivo(noAtual.getRight());
    	
    	int maxFilhos = Math.max(maxEsq, maxDir);
    	
    	return Math.max(noAtual.getElemento().getNumeroCaracteres(), maxFilhos);
    }
    
    public void imprimirPalavrasComTamanho(Node noAtual, int tamanho) {
    	if(noAtual != null) {
    		imprimirPalavrasComTamanho(noAtual.getLeft(), tamanho);
    		
    		if(noAtual.getElemento().getNumeroCaracteres() == tamanho) {
    			System.out.println(noAtual.getElemento());
    		}
    		
    		imprimirPalavrasComTamanho(noAtual.getRight(), tamanho);
    	}
    }
    
    
    
    /*
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
    */
    

}

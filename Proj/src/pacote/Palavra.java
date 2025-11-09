package pacote;

public class Palavra implements Comparable<Palavra>{
    private String palavra;
    private int ocorrencias;
    private int numeroCaracteres;

    public Palavra(String palavra){
        this.palavra = palavra;
        this.numeroCaracteres = palavra.length();
    }

    public String getPalavra(){
        return palavra;
    }
    
    public int getOcorrencias(){
        return ocorrencias;
    }
    
    public int getNumeroCaracteres(){
        return numeroCaracteres;
    }

    public void acrescentarOcorrencia(){
        this.ocorrencias++;
    }
    
    //CompareTo e toString:
    
    @Override
    public int compareTo(Palavra outraPalavra) {
    		return this.palavra.compareToIgnoreCase(outraPalavra.palavra);
    }
    
    @Override
    public String toString() {
    	return String.format("%-25s | Ocorrências: %-5d | Caracteres: %d", 
                palavra, ocorrencias, numeroCaracteres);
    }

}

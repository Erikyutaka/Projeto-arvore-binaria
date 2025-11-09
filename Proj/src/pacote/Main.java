package pacote;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static final String NOME_ARQUIVO = "musica.txt";
	private static boolean textoCarregado = false;
	private static Arvore abb = new Arvore();
	private static long totalLinhas = 0;
	
	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do{
            System.out.println("1- Carregar o texto");
            System.out.println("2- Estatísticas");
            System.out.println("3- Busca por palavra");
            System.out.println("4- Busca por parte da palavra");
            System.out.println("5- Exibição do texto");
            System.out.println("6- Função inventada pelo grupo");
            System.out.println("7- Encerrar");
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    carregarTexto();
                    break;
                case 2:
                	exibirEstatisticas();
                	break;
                case 3:
                	buscarPalavraExata(scanner);
                	break;
                case 4:
                	buscarPalavraParcial(scanner);
                	break;
                case 5:
                	exibirTextoEmOrdem();
                	break;
                case 6:
                	funcaoInventada();
                	break;
                case 7:
                	System.out.println("Encerrando...");
                	break;
                                
                default:
                
            }
        }while (opcao != 7);
        System.out.println("Opção invalida");
        scanner.close();
        
        //implementação da Classe carregarTexto():
        
        
    }
    
    private static void carregarTexto() {
            abb = new Arvore();
            totalLinhas = 0;
            
            try (Scanner fileScanner = new Scanner(new File(NOME_ARQUIVO))) {
                System.out.println("\nProcessando arquivo...");
                
                while (fileScanner.hasNextLine()) {
                    String linha = fileScanner.nextLine();
                    totalLinhas++;
                    
                    String linhaLimpa = linha.replaceAll("[^\\p{L}\\p{Nd}\\s-]+", " "); 
                    
                    String[] palavras = linhaLimpa.split("[\\s-]+");
                    
                    for (String palavraString : palavras) {
                        palavraString = palavraString.trim();
                        if (!palavraString.isEmpty()) {
                            abb.inserir(new Palavra(palavraString));
                        }
                    }
                }
                
                textoCarregado = true;
                System.out.println("\n TEXTO CARREGADO E PROCESSADO COM SUCESSO na Árvore Binária de Busca.");
                System.out.println("Total de linhas lidas: " + totalLinhas);

            } catch (FileNotFoundException e) {
                System.out.println("\n ERRO ao carregar o arquivo.");
                System.out.println("Certifique-se de que o arquivo '" + NOME_ARQUIVO + "' existe no diretório do projeto.");
                textoCarregado = false;
            }
    }
    
    private static void exibirEstatisticas() {
        if (!textoCarregado) {
            System.out.println("Por favor, carregue o texto (Opção 1) antes de exibir as estatísticas.");
            return;
        }

        long totalPalavras = abb.getTotalOcorrencias();
        int palavrasDistintas = abb.getTotalPalavrasDistintas();
        long somaCaracteres = abb.calcularSomaCaracteres(); // Obtém a soma da ABB

        double mediaCaracteres = (totalPalavras > 0) ? (double) somaCaracteres / totalPalavras : 0.0;
        
        // Taxa de Redundância: (1 - (Distintas / Total)) * 100
        double taxaRedundancia = (totalPalavras > 0) ? 
            (1.0 - (double) palavrasDistintas / totalPalavras) * 100.0 : 0.0;
        
        System.out.println("ESTATÍSTICAS DA MÚSICA");
        System.out.println("------------------------------------");
        System.out.println("Total de Linhas:             " + totalLinhas);
        System.out.println("Total de Palavras (Ocorrências): " + totalPalavras);
        System.out.println("Total de Palavras Distintas: " + palavrasDistintas);
        System.out.printf("Média de Caracteres por Palavra: %.2f\n", mediaCaracteres);
        System.out.printf("Taxa de Redundância:         %.2f%%\n", taxaRedundancia);
        System.out.println("------------------------------------");
    }

    // =======================================================
    // OPÇÃO 3: BUSCA POR PALAVRA (EXATA)
    // =======================================================
    private static void buscarPalavraExata(Scanner scanner) {
        if (!textoCarregado) {
            System.out.println("Por favor, carregue o texto (Opção 1) antes de fazer uma busca.");
            return;
        }
        System.out.print("Digite a palavra para busca (exata): ");
        String termo = scanner.nextLine().trim();
        if (termo.isEmpty()) {
            System.out.println("O termo de busca não pode ser vazio.");
            return;
        }

        Palavra resultado = abb.buscaPalavraEspecifica(termo);

        if (resultado != null) {
            System.out.println("\nPalavra Encontrada:");
            System.out.println(resultado);
        } else {
            System.out.println("\nA palavra '" + termo + "' não foi encontrada na música.");
        }
    }


    private static void buscarPalavraParcial(Scanner scanner) {
        if (!textoCarregado) {
            System.out.println("Por favor, carregue o texto (Opção 1) antes de fazer uma busca.");
            return;
        }
        System.out.print("Digite a parte da palavra para busca (parcial): ");
        String termo = scanner.nextLine().trim();
        if (termo.isEmpty()) {
            System.out.println("O termo de busca não pode ser vazio.");
            return;
        }

        int encontradas = abb.buscarEImprimirPalavraParcial(termo);

        if (encontradas > 0) {
            System.out.println("Total de palavras distintas encontradas: " + encontradas);
        } else {
            System.out.println("\nNenhuma palavra contendo '" + termo + "' foi encontrada na música.");
        }
    }

    private static void exibirTextoEmOrdem() {
        if (!textoCarregado) {
            System.out.println("Por favor, carregue o texto (Opção 1) antes de exibir.");
            return;
        }
        abb.exibirEmOrdem();
    }

    
    // OPÇÃO 6: FUNÇÃO INVENTADA (PALAVRA MAIS LONGA)
    private static void funcaoInventada() {
        if (!textoCarregado) {
            System.out.println("Por favor, carregue o texto (Opção 1) antes de executar a função inventada.");
            return;
        }

        System.out.println("FUNÇÃO INVENTADA: ENCONTRAR A(S) PALAVRA(S) MAIS LONGA(S)");
        
        abb.encontrarPalavrasMaisLongasEImprimir();
    }
}


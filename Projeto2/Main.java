import java.util.Scanner;

public class Main {
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
                    System.out.println("Digite o nome do arquivo TXT: ");
                    String nomeArquivo = scanner.nextLine();
                    
                    break;
            
                default:
                
            }
        }while (opcao != 7);
        System.out.println("Opção invalida");
        scanner.close();
    }
}


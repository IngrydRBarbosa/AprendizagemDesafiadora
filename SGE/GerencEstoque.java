package SGE;
import java.util.Scanner;

public class GerencEstoque {
    private static Produto[] produtos = new Produto[100];
    private static int produtosCadastrados=0; // contador dos produtos já cadastrados
    
    public static void preencherCadastrar(Scanner leia){
       Produto cadastro;
       String nome,categoria;
       int qtdEstoque;
       long codigo;
       double valor;

       // PREENCHE OS DADOS DO NOVO PRODUTO
        System.out.println("---------- CADASTRO DO PRODUTO -----------");
        System.out.print("Digite o nome do novo produto: ");
        nome = leia.next();
        System.out.print("Digite a categoria do novo produto: ");
        categoria = leia.next();
        System.out.print("Digite o valor do novo produto: ");
        valor = leia.nextDouble();
        System.out.print("Digite o código do novo produto: ");
        codigo = leia.nextLong();
        System.out.print("Digite a quantidade de estoque do novo produto: ");
        qtdEstoque = leia.nextInt();
        Produto cadastro = new Produto(nome,categoria,valor,codigo,qtdEstoque); //criação do produto com os dados preenchidos
        cadastrarProduto(cadastro); // chama o método para adicionar no contador
    }

    //ADICIONA O NOVO PRODUTO NO CONTADOR E MOSTRA MENSAGEM DE CADASTRO FEITO
    public static void cadastrarProduto (Produto novoProduto){
        if (produtosCadastrados < produtos.length){
            produtos [produtosCadastrados] = novoProduto;
            produtosCadastrados++;
            System.out.printf("\n--- Cadastro de %s efetuado com sucesso. ---\n", novoProduto.nome);
        }else {
            // Mensagem de erro se o estoque estiver cheio.
            System.out.println("\n--- ERRO: Limite máximo de cadastros atingido. ---");
        }
    }

    // Exibe a lista completa dos produtos cadastrados em tabela
    public static void listarProdutos(Produto[] lista){
        System.out.printf("----------- LISTA DOS PRODUTOS CADASTRADOS ----------");
        System.out.printf("Cod\tNome\tCategoria\tValor\tQtd Estoque\n");
        for(int i =0; i < produtosCadastrados; i++){
            System.out.printf("%d\t%s\t%s\t %.2f\t %d\n",produtos[i].codigo, produtos[i].nome,produtos[i].categoria, produtos[i].valor, produtos[i].qtdEstoque);
            System.out.println("-----------------------------------------------------");

        }
    }
    
    public static void main(String[] args) {;
        Scanner leia = new Scanner(System.in); 
        int opcao; // Opção principal do menu (1 a 4)
        int escolha;
        long codigoProcurado;

        do {
            System.out.println("------- MENU DE INTERAÇÃO -------");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar");
            System.out.println("3. Listar");
            System.out.println("4. Sair do sistema");
            System.out.print("Escolha uma opção: ");

            opcao = leia.nextInt();
            switch (opcao) {
                case 1: 
                    preencherCadastrar(leia);
                    break;
                case 2:
                    System.out.print("Digite o código produto a ser buscado: "); 
                    codigoProcurado = leia.nextLong();
                    Busca.BuscaSequencialSGE(produtos, codigoProcurado);   
                case 3:// colocar escolha por código ou nome
                System.out.println("Escolha 1 para ordenar por Nome (Selection Sort) ou 2 para ordenar por Valor (Bubble Sort): ");
                escolha= leia.nextInt();
                if (escolha == 1){
                    // Ordena por Nome
                    Busca.selectionSortPorNomeSGE(produtos, produtosCadastrados);
                    listarProdutos(produtos);
                    }else if (escolha == 2) {
                        // Ordena por Valor
                        Busca.bubllesort(produtos,produtosCadastrados);
                        listarProdutos(produtos);
                    }else{
                        System.out.println("Opção inválida ");
                    }
                    break;
                case 4:
                    // Opção Sair. A mensagem de encerramento é exibida.
                    System.out.println("Encerrando o Sistema de Gerenciamento de Estoque. Até mais!");
                    break;
                default:
                    // Trata qualquer número fora das opções válidas.
                    System.out.println("Número inválido. Tente novamente")
                    break;
            }
        } while (opcao!=4);
        leia.close();
    }
}

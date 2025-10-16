package SGE;
import java.util.Scanner;

public class GerencEstoque {
    //private: para usar as variáveis de modo geral, mas somente nessa classe
    private static Produto[] produtos = new Produto[100];
    private static int produtosCadastrados=0; // contador dos produtos já cadastrados
    
    // PREENCHE OS DADOS DO NOVO PRODUTO
    public static void preencherCadastrar(Scanner leia){
       Produto cadastro;
       String nome,categoria;
       int qtdEstoque;
       long codigo;
       double valor;

        System.out.println("\n========= CADASTRO DO PRODUTO ==========");
        System.out.print("Digite o nome do novo produto: ");
        nome = leia.next();
        System.out.print("Digite a categoria: ");
        categoria = leia.next();
        System.out.print("Digite o valor: ");
        valor = leia.nextDouble();
        System.out.print("Digite o código do novo produto: ");
        codigo = leia.nextLong();
        System.out.print("Digite a quantidade de estoque: ");
        qtdEstoque = leia.nextInt();
        cadastro = new Produto(nome,categoria,valor,codigo,qtdEstoque); //criação do produto com os dados preenchidos
        cadastrarProduto(cadastro); // chama o método para adicionar no contador
    }

    //ADICIONA O NOVO PRODUTO NO CONTADOR E MOSTRA MENSAGEM DE CADASTRO FEITO
    public static void cadastrarProduto (Produto novoProduto){
        if (produtosCadastrados < produtos.length){
            produtos [produtosCadastrados] = novoProduto;
            produtosCadastrados++;
            System.out.printf("\nProduto %s cadastrado com sucesso!\n", novoProduto.nome);
        }else {
            // Mensagem de erro se o estoque estiver cheio.
            System.out.println("\nERRO: Limite máximo de cadastros atingido");
        }
    }

    // caso encontre o código procurado no caso 2 (busca) irá printar os dados do produto
    public static void exibirProduto(Produto produto) {
    System.out.println("\n\tFICHA TÉCNICA DO PRODUTO");
    System.out.printf("Nome: \t\t\t%s\n", produto.nome);
    System.out.printf("Código: \t\t%d\n", produto.codigo);
    System.out.printf("Categoria: \t\t%s\n", produto.categoria);
    System.out.printf("Valor: \t\t\tR$%.2f\n", produto.valor);
    System.out.printf("Qtd. em Estoque: \t%d\n\n", produto.qtdEstoque);
    }

    // SUBMENU PARA MODIFICAR QUANTIDADE DO ESTOQUE
    public static void menuAcoesProdutoEncontrado(Scanner leia, Produto produto) {
        int opcaoSubMenu;

        do {
            
            System.out.println("\nO que deseja fazer com esse produto?");
            System.out.println("1. Modificar estoque");
            System.out.println("2. Retornar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoSubMenu = leia.nextInt();
    
            switch (opcaoSubMenu) {
                case 1:
                    modificarEstoque(leia, produto);
                    break;
                case 2:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente");
                    break;
            }
        } while (opcaoSubMenu != 2);
    }

    // ACRESCENTA OU REMOVE QUANTIDADE EM ESTOQUE APÓS BUSCAR CÓDIGO DO PRODUTO
    public static void modificarEstoque(Scanner leia, Produto produto) {
        int escolha, quantidade;

        System.out.println("\n ---- MODIFICAR ESTOQUE DE:  " + produto.nome + " ----");
        System.out.printf("Estoque atual: %d\n", produto.qtdEstoque);

        System.out.println("1. Adicionar (entrada)");
        System.out.println("2. Remover (saída/dar baixa)");
        System.out.print("Escolha a opção: ");
        escolha = leia.nextInt();

        if(escolha != 1 && escolha != 2) {
            System.out.println("ERRO! Número inválido\n");
            return;
        }

        System.out.print("Digite a quantidade: ");
        quantidade = leia.nextInt();

        if(escolha == 1) { // entrada
            produto.qtdEstoque += quantidade;
            System.out.printf("Adicionado +%d com sucesso! Quantidade atual em estoque: %d", quantidade, produto.qtdEstoque);
        } else { // saída
            if(produto.qtdEstoque >= quantidade) {
                produto.qtdEstoque -= quantidade;
                System.out.printf("Removido -%d com sucesso! Quantidade atual em estoque: %d", quantidade, produto.qtdEstoque);
            } else {
                System.out.println("ERRO: valor maior do que quantidade em estoque. Disponível: \n" + produto.qtdEstoque);

            }
        }
    }

    // Exibe a lista completa dos produtos cadastrados em tabela
    public static void listarProdutos(Produto[] lista){
        if (produtosCadastrados == 0) {
        System.out.println("\nNenhum produto cadastrado para listar");
        return;
        }
        System.out.printf("\n\t\t LISTA DOS PRODUTOS CADASTRADOS \n");
        System.out.printf("\nCódigo\tNome\tCategoria\tValor\tEstoque\n");
        for(int i =0; i < produtosCadastrados; i++){
            System.out.printf("%d\t%s\t%s\tR$%.2f\t\t%d\n",produtos[i].codigo, produtos[i].nome,produtos[i].categoria, produtos[i].valor, produtos[i].qtdEstoque);
        }
    }
    
    public static void main(String[] args) {;
        Scanner leia = new Scanner(System.in); 
        int opcao; // Opção principal do menu (1 a 4)
        int escolha;
        long codigoProcurado;

        do {
            System.out.println("\n========== MENU DE INTERAÇÃO =========");
            System.out.println("1. Cadastrar");
            System.out.println("2. Buscar");
            System.out.println("3. Listar");
            System.out.println("4. Sair do sistema");
            System.out.print("Escolha uma opção: ");

            opcao = leia.nextInt();
            switch (opcao) {

                // opção cadastro
                case 1: 
                    preencherCadastrar(leia);
                    break;

                // opção busca
                case 2:
                    System.out.print("\nDigite o código do produto a ser buscado: "); 
                    codigoProcurado = leia.nextLong();
                    Produto busca = Busca.BuscaSequencialSGE(produtos, codigoProcurado); // chama o buscador
                    if(busca == null) { // mensagem se não tiver produto com o código buscado
                        System.out.println("Não há produto cadastrado com esse código.\n");
                    }else {
                        exibirProduto(busca); // método com as mensagens dos dados do produto encontrado
                        menuAcoesProdutoEncontrado(leia, busca);
                    } 
                    break;

                // opção listagem ordenada
                case 3:
                    System.out.print("\nEscolha 1 para ordenar por nome ou 2 para ordenar por valor: ");
                    escolha= leia.nextInt();
                    if (escolha == 1){ // Ordena por Nome
                        Busca.selectionSortPorNomeSGE(produtos, produtosCadastrados);
                        listarProdutos(produtos);
                    }else if (escolha == 2) { // Ordena por Valor
                        Busca.bubllesort(produtos,produtosCadastrados);
                        listarProdutos(produtos);
                    }else{ // se o usuário digitar um número diferente de 1 ou 2
                        System.out.println("Opção inválida ");
                    }
                break;

                // Encerra o programa
                case 4:
                    System.out.println("Encerrando o Sistema de Gerenciamento de Estoque. Até mais!");
                    break;

                default: // Qualquer número fora das opções válidas
                    System.out.println("Número inválido. Tente novamente");
                    break;
            }
        } while (opcao!=4);
        leia.close();
    }
}
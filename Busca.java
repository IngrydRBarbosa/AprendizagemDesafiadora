package SGE;
public class Busca {
    public static Produto BuscaSequencialSGE (Produto [] produtos, long codigoProcurado){
      
        for (int i = 0; i < produtos.length; i ++){
            if (produtos[i].codigo == codigoProcurado){
                return produtos[i]; // usamos este método pois foi o pedido no enunciado
            }
        }
      return null;
    }
    public static void selectionSortPorNomeSGE(Produto[] produtos, int n){
        for(int i = 0; i<n -1; i++){
            int posicaoDoMenor = i;
            for(int j = i+1; j<n;j++){
                if(produtos[j].nome.compareToIgnoreCase(produtos[posicaoDoMenor].nome) < 0){
                    posicaoDoMenor = j; //
                }
            }
            Produto temp = produtos[posicaoDoMenor];
            produtos[posicaoDoMenor] = produtos[i];
            produtos[i] = temp;
            }
         }
         public static void bubllesort (Produto[] produtos, int n){
          
            for (int i = 0; i < n - 1; i ++){
                for (int j = 0; j < n - i - 1; j++){
                    if (produtos[j].valor>produtos[j+1].valor){
                        Produto temp = produtos[j];
                        produtos [j] = produtos [j + 1];
                        produtos [j + 1] = temp; // foi o mais básico de se aplicar
                    }
                }                   
            }
        }
    }
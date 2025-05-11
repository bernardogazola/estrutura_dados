public class ArvoreTrie {
    private Node raiz;

    public ArvoreTrie() {
        raiz = new Node();
    }

    private int obterIndiceCaractere(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a';
        }
        return -1;
    }

    public void inserir(String palavra) {
        if (palavra == null) {
            System.out.println("Nao e possivel inserir palavra nula.");
            return;
        }

        Node noAtual = raiz;

        for (int i = 0; i < palavra.length(); i++) {
            char charAtual = palavra.charAt(i);
            int indice = obterIndiceCaractere(charAtual);

            if (indice == -1) {
                System.out.println("Palavra '" + palavra + "' contem caractere invalido: '" + charAtual + "'. Insercao abortada.");
                return;
            }

            if (noAtual.filhos[indice] == null) {
                noAtual.filhos[indice] = new Node();
            }

            noAtual = noAtual.filhos[indice];
        }

        noAtual.isFimDaPalavra = true;
        System.out.println("Palavra '" + palavra + "' inserida.");
    }

    public boolean buscar(String palavra) {
        if (palavra == null) {
            return false;
        }

        Node noAtual = raiz;
        for (int i = 0; i < palavra.length(); i++) {
            char charAtual = palavra.charAt(i);
            int indice = obterIndiceCaractere(charAtual);

            if (indice == -1) {
                return false;
            }

            if (noAtual.filhos[indice] == null) {
                return false;
            }

            noAtual = noAtual.filhos[indice];
        }
        return noAtual.isFimDaPalavra;
    }

    public void remover(String palavra) {
        if (palavra == null) {
            System.out.println("Nao e possivel remover palavra nula.");
            return;
        }

        if (!buscar(palavra)) {
            System.out.println("Palavra '" + palavra + "' nao encontrada para remocao.");
            return;
        }

        Node noAtual = raiz;
        for (int i = 0; i < palavra.length(); i++) {
            char charAtual = palavra.charAt(i);
            int indice = obterIndiceCaractere(charAtual);

            if (indice == -1) {
                System.out.println("Erro inesperado: caractere invalido em palavra existente durante remocao.");
                return;
            }

            noAtual = noAtual.filhos[indice];
        }

        if (noAtual.isFimDaPalavra) {
            noAtual.isFimDaPalavra = false;
            System.out.println("Palavra '" + palavra + "' desmarcada (removida logicamente).");
        }
    }
}
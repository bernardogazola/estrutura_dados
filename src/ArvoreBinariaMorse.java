public class ArvoreBinariaMorse {
    private Node raiz;

    public ArvoreBinariaMorse() {
        this.raiz = new Node('\0');
        this.carregarTabelaPadrao();
    }

    public void inserir(String codigo, char letra) {
        Node atual = raiz;
        int i = 0;
        while (i < codigo.length()) {
            char c = codigo.charAt(i);
            if (c == '.') {
                if (atual.esquerda == null) atual.esquerda = new Node('\0');
                atual = atual.esquerda;
            } else {
                if (atual.direita == null) atual.direita = new Node('\0');
                atual = atual.direita;
            }
            i++;
        }
        atual.valor = letra;
    }

    public char buscar(String codigo) {
        Node atual = raiz;
        int i = 0;
        while (i < codigo.length() && atual != null) {
            char c = codigo.charAt(i);
            atual = (c == '.') ? atual.esquerda : atual.direita;
            i++;
        }
        return (atual == null || atual.valor == '\0') ? '\0' : atual.valor;
    }

    public String codificar(char letra) {
        return codificarRec(raiz, Character.toUpperCase(letra), "");
    }

    private String codificarRec(Node node, char alvo, String caminho) {
        if (node == null) {
            return "";
        }

        if (node.valor == alvo) {
            return caminho;
        }

        String esq = codificarRec(node.esquerda, alvo, caminho + ".");

        if (esq.length() > 0) {
            return esq;
        }

        return codificarRec(node.direita, alvo, caminho + "-");
    }

    public boolean codigoValido(String codigo) {
        int i = 0;
        while (i < codigo.length()) {
            char c = codigo.charAt(i);

            if (c != '.' && c != '-') {
                return false;
            }

            i++;
        }
        return codigo.length() > 0;
    }

    public String traduzirMorse(String mensagem) {
        String texto = "";
        String codigo = "";
        int i = 0, espacosSeguidos = 0;

        while (i < mensagem.length()) {
            char ch = mensagem.charAt(i);

            switch(ch) {
                case ' ':
                    espacosSeguidos++;
                    if (codigo.length() > 0) {
                        texto += traduzCodigo(codigo);
                        codigo = "";
                    } else if (espacosSeguidos == 3) {
                        texto += " ";
                        espacosSeguidos = 0;
                    }
                    break;
                case '/':
                    if (codigo.length() > 0) {
                        texto += traduzCodigo(codigo);
                        codigo = "";
                    }
                    texto += " ";
                    espacosSeguidos = 0;
                    break;
                default:
                    codigo += ch;
                    espacosSeguidos = 0;
                    break;
            }
            i++;
        }
        if (codigo.length() > 0) texto += traduzCodigo(codigo);
        return texto;
    }

    private char traduzCodigo(String codigo) {
        char c = buscar(codigo);
        return (c == '\0') ? '?' : c;
    }

    public String traduzirTexto(String texto) {
        texto = texto.toUpperCase();
        String morse = "";
        int i = 0;

        while (i < texto.length()) {
            char ch = texto.charAt(i);

            if (ch == ' ') {
                morse += "/ ";
            } else {
                String cod = codificar(ch);
                morse += (cod.length() == 0 ? "?" : cod) + " ";
            }
            i++;
        }

        if (morse.length() > 0 && morse.charAt(morse.length() - 1) == ' ')
            morse = morse.substring(0, morse.length() - 1);
        return morse;
    }

    public void desenhar() {
        desenharRec(raiz, 0);
    }

    private void desenharRec(Node n, int nivel) {
        if (n == null) {
            return;
        }

        desenharRec(n.direita, nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }

        System.out.println((n.valor == '\0') ? "*" : n.valor);
        desenharRec(n.esquerda, nivel + 1);
    }

    public void exibir() {
        exibirRec(raiz, "");
    }

    private void exibirRec(Node node, String caminho) {
        if (node == null) {
            return;
        }

        if (node.valor != '\0') {
            System.out.println(caminho + " : " + node.valor);
        }

        exibirRec(node.esquerda, caminho + ".");
        exibirRec(node.direita, caminho + "-");
    }

    public void carregarTabelaPadrao() {
        inserir(".-"   , 'A');
        inserir("-...", 'B');
        inserir("-.-.", 'C');
        inserir("-.."  , 'D');
        inserir("."   , 'E');
        inserir("..-.", 'F');
        inserir("--."  , 'G');
        inserir("....", 'H');
        inserir(".."  , 'I');
        inserir(".---" , 'J');
        inserir("-.-" , 'K');
        inserir(".-..", 'L');
        inserir("--"  ,  'M');
        inserir("-."  , 'N');
        inserir("---" , 'O');
        inserir(".--.", 'P');
        inserir("--.-", 'Q');
        inserir(".-.", 'R');
        inserir("..." , 'S');
        inserir("-"   , 'T');
        inserir("..-" , 'U');
        inserir("...-", 'V');
        inserir(".--" , 'W');
        inserir("-..-", 'X');
        inserir("-.--", 'Y');
        inserir("--..", 'Z');

        inserir("-----", '0');
        inserir(".----", '1');
        inserir("..---", '2');
        inserir("...--", '3');
        inserir("....-", '4');
        inserir(".....", '5');
        inserir("-....", '6');
        inserir("--...", '7');
        inserir("---..", '8');
        inserir("----.", '9');
    }
}

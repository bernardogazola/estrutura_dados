public class Main {
    public static void main(String[] args) {
        ArvoreTrie trie = new ArvoreTrie();

        System.out.println("--- Inserções ---");
        trie.inserir("casa");
        trie.inserir("casaco");
        trie.inserir("caso");
        trie.inserir("cama");
        trie.inserir("capa");
        trie.inserir("dado");
        trie.inserir("dador");
        trie.inserir("teste invalido!");

        System.out.println("\n--- Buscas ---");
        String[] palavrasParaBuscar = {"casa", "cas", "casaco", "capo", "dado", "cama", "naoexiste"};

        for (int i=0; i < 7; i++) {
            String p = palavrasParaBuscar[i];
            System.out.println("Buscando '" + p + "': " + trie.buscar(p));
        }

        System.out.println("\n--- Remocoes (Logicas) ---");

        trie.remover("casaco");

        System.out.println("Buscando 'casaco' apos remocao: " + trie.buscar("casaco"));
        System.out.println("Buscando 'caso' (ainda deve existir): " + trie.buscar("caso"));

        trie.remover("dado");
        System.out.println("Buscando 'dado' apos remocao: " + trie.buscar("dado"));
        System.out.println("Buscando 'dador' (ainda deve existir): " + trie.buscar("dador"));

        trie.remover("naoexiste");
    }
}
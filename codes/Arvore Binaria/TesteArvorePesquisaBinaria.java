public class TesteArvorePesquisaBinaria {

    public static void main(String[] args) {

        // =========================================================
        // 1. Arvore vazia
        // =========================================================
        System.out.println("=== Arvore Vazia ===");
        ArvorePesquisaBinaria vazia = new ArvorePesquisaBinaria();
        System.out.println("isEmpty(): " + vazia.isEmpty());
        System.out.println("size():    " + vazia.size());
        System.out.println("root():    " + vazia.root());
        System.out.print("mostrar(): ");
        vazia.mostrar();

        // =========================================================
        // 2. Insercao
        // =========================================================
        System.out.println("\n=== Insercao ===");
        ArvorePesquisaBinaria arvore = new ArvorePesquisaBinaria();
        int[] chaves = { 10, 5, 15, 2, 8, 22 };
        for (int c : chaves)
            arvore.insert(c, "v" + c);

        System.out.println("size():    " + arvore.size());    // 6
        System.out.println("isEmpty(): " + arvore.isEmpty()); // false
        System.out.println("root():    " + arvore.root().getChave()); // 10

        System.out.println("\nmostrar():");
        arvore.mostrar();

        // =========================================================
        // 3. Busca (search)
        // =========================================================
        System.out.println("\n=== Busca ===");
        No achado = arvore.search(8, arvore.root());
        System.out.println("search(8):  chave=" + achado.getChave()
                + ", elemento=" + achado.getElemento()); // chave=8, elemento=v8
        System.out.println("search(15): " + arvore.search(15, arvore.root()).getChave()); // 15
        System.out.println("search(99): " + arvore.search(99, arvore.root())); // null (nao existe)

        // =========================================================
        // 4. Remocao - folha
        // =========================================================
        System.out.println("\n=== Remocao de folha (2) ===");
        arvore.remove(2);
        System.out.println("size(): " + arvore.size());            // 5
        System.out.println("search(2): " + arvore.search(2, arvore.root())+"\n"); // null
        arvore.mostrar();

        // =========================================================
        // 5. Remocao - nó com um filho
        // =========================================================
        System.out.println("\n=== Remocao com um filho (15) ===");
        arvore.remove(15);
        System.out.println("size(): " + arvore.size());              // 4
        System.out.println("search(15): " + arvore.search(15, arvore.root())); // null
        System.out.println("search(22): " + arvore.search(22, arvore.root()).getChave()+"\n"); // 22
        arvore.mostrar();
        //         10
        //     5       22
        //         8

        // =========================================================
        // 6. Remocao - nó com dois filhos
        // =========================================================
        System.out.println("\n=== Remocao com dois filhos (10 - raiz) ===");
        arvore.remove(10);
        System.out.println("size(): " + arvore.size());               // 3
        System.out.println("search(10): " + arvore.search(10, arvore.root())); // null
        System.out.println("nova raiz: " + arvore.root().getChave()+"\n");  // 22
        arvore.mostrar();
        //     22
        // 5
        //     8

        // =========================================================
        // 7. Remocao de chave inexistente (nao altera nada)
        // =========================================================
        System.out.println("\n=== Remocao de chave inexistente (99) ===");
        arvore.remove(99);
        System.out.println("size(): " + arvore.size());

        System.out.println("\n===== Todos os testes passaram =====");
    }
}

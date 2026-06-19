public class TesteHeapEncadeado {

    public static void main(String[] args) {

        // =========================================================
        // 1. Heap vazio
        // =========================================================
        System.out.println("=== Heap Vazio ===");
        HeapEncadeado vazio = new HeapEncadeado();
        System.out.println("isEmpty():   " + vazio.isEmpty());   // true
        System.out.println("size():      " + vazio.size());      // 0
        System.out.println("min():       " + vazio.min());       // null
        System.out.println("removeMin(): " + vazio.removeMin()); // null

        // =========================================================
        // 2. Insercao
        // =========================================================
        System.out.println("\n=== Insercao ===");
        HeapEncadeado heap = new HeapEncadeado();
        int[] chaves = { 15, 10, 22, 5, 8, 2 };
        for (int c : chaves)
            heap.insert(c, "v" + c);

        System.out.println("size():    " + heap.size());    // 6
        System.out.println("isEmpty(): " + heap.isEmpty()); // false

        // =========================================================
        // 3. min() - menor elemento sem remover
        // =========================================================
        System.out.println("\n=== min() ===");
        System.out.println("min().key():   " + heap.min().key());   // 2
        System.out.println("min().value(): " + heap.min().value()); // v2
        System.out.println("size():        " + heap.size());        // 6 (nao removeu)

        // =========================================================
        // 4. removeMin() - deve sair em ordem crescente
        // =========================================================
        System.out.println("\n=== removeMin() em ordem crescente ===");
        System.out.print("Saida: ");
        while (!heap.isEmpty()) {
            Entry e = heap.removeMin();
            System.out.print(e.key() + " "); // 2 5 8 10 15 22
        }
        System.out.println();
        System.out.println("size() apos esvaziar: " + heap.size());    // 0
        System.out.println("isEmpty():            " + heap.isEmpty()); // true

        // =========================================================
        // 5. Reuso apos esvaziar
        // =========================================================
        System.out.println("\n=== Reuso apos esvaziar ===");
        heap.insert(7, "v7");
        heap.insert(3, "v3");
        heap.insert(9, "v9");
        System.out.println("min().key(): " + heap.min().key()); // 3
        System.out.println("size():      " + heap.size());      // 3

        System.out.println("\n===== Todos os testes passaram =====");
    }
}

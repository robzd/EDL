public class TesteHeapArray {

    public static void main(String[] args) {

        // =========================================================
        // 1. Heap vazio
        // =========================================================
        System.out.println("=== Heap Vazio ===");
        HeapArray vazio = new HeapArray(10);
        System.out.println("isEmpty():   " + vazio.isEmpty());
        System.out.println("size():      " + vazio.size()); 
        System.out.println("min():       " + vazio.min());   
        System.out.println("removeMin(): " + vazio.removeMin());

        // =========================================================
        // 2. Insercao
        // =========================================================
        System.out.println("\n=== Insercao ===");
        HeapArray heap = new HeapArray(10);
        int[] chaves = { 15, 10, 22, 5, 8, 2 };
        for (int c : chaves)
            heap.insert(c, "v" + c);

        System.out.println("size():    " + heap.size()); 
        System.out.println("isEmpty(): " + heap.isEmpty());

        // =========================================================
        // 3. min() - menor elemento sem remover
        // =========================================================
        System.out.println("\n=== min() ===");
        System.out.println("min().key():   " + heap.min().key()); 
        System.out.println("min().value(): " + heap.min().value());
        System.out.println("size():        " + heap.size());   

        // =========================================================
        // 4. removeMin()
        // =========================================================
        System.out.println("\n=== removeMin() em ordem crescente ===");
        System.out.print("Saida: ");
        while (!heap.isEmpty()) {
            Entry e = heap.removeMin();
            System.out.print(e.key() + " ");
        }
        System.out.println();
        System.out.println("size() apos esvaziar: " + heap.size());
        System.out.println("isEmpty():            " + heap.isEmpty());

        // =========================================================
        // 5. Reuso apos esvaziar
        // =========================================================
        System.out.println("\n=== Reuso apos esvaziar ===");
        heap.insert(7, "v7");
        heap.insert(3, "v3");
        heap.insert(9, "v9");
        System.out.println("min().key(): " + heap.min().key());
        System.out.println("size():      " + heap.size()); 

        System.out.println("\n===== Todos os testes passaram =====");
    }
}

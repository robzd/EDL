public class TestePilha {
    public static void main(String[] args) {
        Pilha pilha = new Pilha(5);

        // Teste 1: Pilha vazia
        System.out.println("Teste 1 - Pilha vazia:");
        System.out.println("isEmpty(): " + pilha.isEmpty());
        System.out.println("size(): " + pilha.size());

        // Teste 2: Push e size
        System.out.println("\nTeste 2 - Push de elementos:");
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        System.out.println("size() após 3 pushs: " + pilha.size());
        System.out.println("isEmpty(): " + pilha.isEmpty());

        // Teste 3: objectTop
        System.out.println("\nTeste 3 - Elemento do topo:");
        System.out.println("objectTop(): " + pilha.objectTop());

        // Teste 4: Pop
        System.out.println("\nTeste 4 - Pop:");
        System.out.println("pop(): " + pilha.pop());
        System.out.println("size() após pop: " + pilha.size());
        System.out.println("objectTop(): " + pilha.objectTop());

        // Teste 5: Expansão da pilha
        System.out.println("\nTeste 5 - Expansão da pilha:");
        pilha.push(40);
        pilha.push(50);
        pilha.push(60);
        pilha.push(70);
        System.out.println("size() após mais 4 pushs: " + pilha.size());
        System.out.println("tamanho do array interno: " + pilha.capacidade());

        // Teste 6: Exception - Pop em pilha vazia
        System.out.println("\nTeste 6 - Exceção EPilhaVazia:");
        try {
            Pilha pilhaVazia = new Pilha(0);
            pilhaVazia.pop();
        } catch (EPilhaVazia e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }

        // Teste 7: Exception - objectTop em pilha vazia
        System.out.println("\nTeste 7 - objectTop em pilha vazia:");
        try {
            Pilha pilhaVazia = new Pilha(0);
            pilhaVazia.objectTop();
        } catch (EPilhaVazia e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
    }
}

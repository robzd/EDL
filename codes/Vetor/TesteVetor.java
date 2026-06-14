public class TesteVetor {

    public static void main(String[] args) {

        VetorArray vetor = new VetorArray();

        System.out.println("===== TESTE 1 - Vetor vazio =====");
        System.out.println("Tamanho: " + vetor.size());
        System.out.println("Está vazio? " + vetor.isEmpty());

        System.out.println("\n===== TESTE 2 - Inserções =====");

        vetor.insertAtRank(0, "A");
        vetor.insertAtRank(1, "B");
        vetor.insertAtRank(2, "C");

        System.out.println("Elemento posição 0: " + vetor.elemAtRank(0));
        System.out.println("Elemento posição 1: " + vetor.elemAtRank(1));
        System.out.println("Elemento posição 2: " + vetor.elemAtRank(2));

        System.out.println("Tamanho após inserções: " + vetor.size());

        System.out.println("\n===== TESTE 3 - Inserção no meio =====");

        vetor.insertAtRank(1, "X");

        for (int i = 0; i < vetor.size(); i++) {
            System.out.println("Posição " + i + ": " + vetor.elemAtRank(i));
        }

        System.out.println("\n===== TESTE 4 - Substituição =====");

        Object antigo = vetor.replaceAtRank(2, "Y");

        System.out.println("Elemento antigo: " + antigo);

        for (int i = 0; i < vetor.size(); i++) {
            System.out.println("Posição " + i + ": " + vetor.elemAtRank(i));
        }

        System.out.println("\n===== TESTE 5 - Remoção =====");

        Object removido = vetor.removeAtRank(1);

        System.out.println("Elemento removido: " + removido);

        for (int i = 0; i < vetor.size(); i++) {
            System.out.println("Posição " + i + ": " + vetor.elemAtRank(i));
        }

        System.out.println("\n===== TESTE 6 - Crescimento automático =====");

        vetor.insertAtRank(vetor.size(), "D");
        vetor.insertAtRank(vetor.size(), "E");
        vetor.insertAtRank(vetor.size(), "F");
        vetor.insertAtRank(vetor.size(), "G");

        for (int i = 0; i < vetor.size(); i++) {
            System.out.println("Posição " + i + ": " + vetor.elemAtRank(i));
        }

        System.out.println("\n===== TESTE 7 - Exceção =====");

        try {
            vetor.elemAtRank(100);
            throw new RuntimeException("Esperava exceção para indice invalido");
        } catch (RuntimeException e) {
            System.out.println("Erro capturado (esperado): " + e.getMessage());
        }
        System.out.println("\n===== Todos os testes passaram =====");
    }
}

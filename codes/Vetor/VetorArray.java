public class VetorArray {
    private Object[] V;
    private int n = 0; // tamanho atual
    private int N = 10; // capacidade total

    public VetorArray() {
        V = new Object[N];
    }

    public int size() { return n; }

    public boolean isEmpty() { return n == 0; }

    public Object elemAtRank(int r) {
        return V[r]; // Acesso direto e rápido [3]
    }

    public void insertAtRank(int r, Object o) {
        // Se o array estiver cheio, duplicamos (Estratégia de duplicação) [4, 5]
        if (n == N) {
            N *= 2;
            Object[] novo = new Object[N];
            for (int i = 0; i < n; i++) novo[i] = V[i];
            V = novo;
        }
        // "Arruma espaço" deslocando para a direita [1, 2]
        for (int i = n; i > r; i--) {
            V[i] = V[i - 1];
        }
        V[r] = o;
        n++;
    }

    public Object removeAtRank(int r) {
        Object o = V[r];
        // Preenche o "buraco" deslocando para a esquerda [1, 6]
        for (int i = r; i < n - 1; i++) {
            V[i] = V[i + 1];
        }
        n--;
        return o;
    }

    public Object replaceAtRank(int r, Object o) {
        Object antigo = V[r];
        V[r] = o;
        return antigo;
    }
}
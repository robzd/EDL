public class ListaArray {
    private Object[] array;
    private int tam = 0;
    private int capacidade;

    public ListaArray(int cap) {
        this.capacidade = cap;
        this.array = new Object[cap];
    }

    // Métodos de Acesso (Retornam o índice como "posição")
    public int first() {
        if (tam == 0) throw new EListaVazia("Lista vazia.");
        return 0;
    }

    public int last() {
        if (tam == 0) throw new EListaVazia("Lista vazia.");
        return tam - 1;
    }

    public int next(int p) {
        if (p < 0 || p >= tam - 1) throw new RuntimeException("Indice fora dos limites.");
        return p + 1;
    }

    public int prev(int p) {
        if (p <= 0 || p >= tam) throw new RuntimeException("Indice fora dos limites.");
        return p - 1;
    }

    public Object elemAtRank(int p) {
        if (p < 0 || p >= tam) throw new RuntimeException("Indice fora dos limites.");
        return array[p];
    }

    public Object replaceAtRank(int p, Object o) {
        if (p < 0 || p >= tam) throw new RuntimeException("Indice fora dos limites.");
        Object antigo = array[p];
        array[p] = o;
        return antigo;
    }

    // Inserção: exige deslocamento (O(n)) [5]
    public void insertAfter(int p, Object o) {
        if (p < -1 || p >= tam) throw new RuntimeException("Indice fora dos limites.");
        if (tam == capacidade) aumentarCapacidade();
        // Desloca para a direita para abrir espaço após p
        for (int i = tam; i > p + 1; i--) {
            array[i] = array[i - 1];
        }
        array[p + 1] = o;
        tam++;
    }

    public void insertFirst(Object o) {
        if (tam == capacidade) aumentarCapacidade();
        for (int i = tam; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = o;
        tam++;
    }

    public void insertLast(Object o) {
        insertAfter(tam - 1, o);
    }

    // Remoção: exige deslocamento para fechar o buraco (O(n)) [4, 5]
    public Object remove(int p) {
        if (p < 0 || p >= tam) throw new RuntimeException("Indice fora dos limites.");
        Object objetoRemovido = array[p];
        for (int i = p; i < tam - 1; i++) {
            array[i] = array[i + 1];
        }
        array[tam - 1] = null;
        tam--;
        return objetoRemovido;
    }

    private void aumentarCapacidade() {
        capacidade *= 2;
        Object[] novo = new Object[capacidade];
        for (int i = 0; i < tam; i++) novo[i] = array[i];
        array = novo;
    }

    public boolean isEmpty() { return tam == 0; }

    public int size() { return tam; }
}
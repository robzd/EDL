public class HeapArray {
    private Entry[] V;
    private int n = 0;

    public HeapArray(int capacidade) {
        V = new Entry[capacidade + 1];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Entry min() {
        if (isEmpty())
            return null;
        return V[1];
    }

    public void insert(Object k, Object o) {
        n++;
        V[n] = new Item(k, o);
        upHeap(n);
    }

    private void upHeap(int i) {
        while (i > 1 && comparar(V[i].key(), V[i / 2].key()) < 0) { // se o primeiro for menor que o segundo
            swap(i, i / 2);
            i = i / 2;
        }
    }

    public Entry removeMin() {
        if (isEmpty())
            return null;
        Entry min = V[1];
        V[1] = V[n];
        V[n] = null;
        n--;
        downHeap(1);
        return min;
    }

    private void downHeap(int i) {
        while (2 * i <= n) {
            int menorFilho = 2 * i;

            if (2 * i + 1 <= n && comparar(V[2 * i + 1].key(), V[2 * i].key()) < 0) { // se o primeiro for menor que o segundo
                menorFilho = 2 * i + 1;
            }
            
            if (comparar(V[i].key(), V[menorFilho].key()) <= 0)
                break;

            swap(i, menorFilho);
            i = menorFilho;
        }
    }

    // Métodos Auxiliares
    private int comparar(Object a, Object b) {
        return ((Integer) a).compareTo((Integer) b); // metodo java pra comparar inteiros, retorna -1 se a < b, 0 se a == b, e 1 se a > b
    }

    private void swap(int i, int j) {
        Entry temp = V[i];
        V[i] = V[j];
        V[j] = temp;
    }
}
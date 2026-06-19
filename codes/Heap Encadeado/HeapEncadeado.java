public class HeapEncadeado {
    private No raiz;
    private No ultimo;
    private int n = 0;

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Entry min() {
        if (isEmpty())
            return null;
        return raiz.getItem();
    }

    public void insert(Object k, Object o) {
        if (isEmpty()) {
            raiz = new No(k, o, null);
            ultimo = raiz;
        } else {
            No z = encontrarNo();
            No novo = new No(k, o, z);

            if (z.getEsquerdo() == null)
                z.setEsquerdo(novo);
            else
                z.setDireito(novo);

            ultimo = novo;
            upHeap(ultimo);
        }
        n++;
    }

    private void upHeap(No v) {
        // Troca k com o pai enquanto k < pai.key
        while (v != raiz && comparar(v.getItem().key(), v.getPai().getItem().key()) < 0) {
            swap(v, v.getPai());
            v = v.getPai();
        }
    }

    public Entry removeMin() {
        if (isEmpty())
            return null;
        // Copia os dados do minimo antes de sobrescrever a raiz,
        // pois o swap/downHeap alteram o MyEntry no proprio lugar.
        Entry min = new MyEntry(raiz.getItem().key(), raiz.getItem().value());

        if (n == 1) {
            raiz = ultimo = null;
        } else {
            raiz.getItem().setKey(ultimo.getItem().key());
            raiz.getItem().setValue(ultimo.getItem().value());

            No antigoUltimo = ultimo;
            ultimo = encontrarNovoUltimo();

            if (antigoUltimo == antigoUltimo.getPai().getDireito())
                antigoUltimo.getPai().setDireito(null);
            else
                antigoUltimo.getPai().setEsquerdo(null);

            downHeap(raiz);
        }
        n--;
        return min;
    }

    private void downHeap(No v) {

        while (v.getEsquerdo() != null) {
            No menorFilho = v.getEsquerdo();
            if (v.getDireito() != null && comparar(v.getDireito().getItem().key(), menorFilho.getItem().key()) < 0) {
                menorFilho = v.getDireito();
            }
            if (comparar(v.getItem().key(), menorFilho.getItem().key()) <= 0)
                break;
            swap(v, menorFilho);
            v = menorFilho;
        }
    }

    private No encontrarNo() {
        No v = ultimo;
        // Sobe até encontrar um filho da esquerda ou a raiz
        while (v != raiz && v == v.getPai().getDireito())
            v = v.getPai();
        if (v != raiz) {
            if (v.getPai().getDireito() != null) {
                v = v.getPai().getDireito();
                
                while (v.getEsquerdo() != null)
                    v = v.getEsquerdo();
                return v;
            } else
                return v.getPai();
        }
        // Se chegou na raiz, desce tudo pela esquerda
        while (v.getEsquerdo() != null)
            v = v.getEsquerdo();
        return v;
    }

    private No encontrarNovoUltimo() {
        No v = ultimo;
        // sobe até achar um filho da direita ou a raiz
        while (v != raiz && v == v.getPai().getEsquerdo())
            v = v.getPai();
        if (v != raiz)
            v = v.getPai().getEsquerdo();
        while (v.getDireito() != null)
            v = v.getDireito();
        return v;
    }

    private void swap(No a, No b) {
        Object tempK = a.getItem().key();
        Object tempV = a.getItem().value();
        a.getItem().setKey(b.getItem().key());
        a.getItem().setValue(b.getItem().value());
        b.getItem().setKey(tempK);
        b.getItem().setValue(tempV);
    }

    private int comparar(Object a, Object b) {
        return ((Integer) a).compareTo((Integer) b);
    }
}
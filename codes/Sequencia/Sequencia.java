public class Sequencia {
    private No inicio, fim; // Nós sentinelas [3]
    private int tam;

    public Sequencia() {
        inicio = new No(null);
        fim = new No(null);
        inicio.setNext(fim);
        fim.setPrev(inicio);
        tam = 0;
    }

    // --- Métodos Genéricos ---
    public int size() { return tam; }
    public boolean isEmpty() { return tam == 0; }

    // --- Métodos "Ponte" (Essenciais para Sequência) ---
    
    // Converte Rank em No (Otimizado para buscar da extremidade mais próxima) [4]
    public No atRank(int rank) {
        if (rank < 0 || rank >= tam) throw new IndexOutOfBoundsException();
        No node;
        if (rank <= size() / 2) {
            node = inicio.getNext();
            for (int i = 0; i < rank; i++) node = node.getNext();
        } else {
            node = fim.getPrev();
            for (int i = 0; i < size() - rank - 1; i++) node = node.getPrev();
        }
        return node;
    }

    // Converte No em Rank [5]
    public int rankOf(No no) {
        No n = inicio.getNext();
        int r = 0;
        while (n != no && n != fim) {
            n = n.getNext();
            r++;
        }
        return r;
    }

    // --- Métodos de Vetor (Usam Rank) [2] ---
    public Object elemAtRank(int r) {
        return atRank(r).getElemento();
    }

    public Object replaceAtRank(int r, Object o) {
        No p = atRank(r);
        Object antigo = p.getElemento();
        p.setElemento(o);
        return antigo;
    }

    public void insertAtRank(int r, Object o) {
        if (r == size()) { // Inserção no final
            insertLast(o);
        } else {
            insertBefore(atRank(r), o);
        }
    }

    public Object removeAtRank(int r) {
        No p = atRank(r);
        Object e = p.getElemento();
        remove(p);
        return e;
    }

    // --- Métodos de Lista (Usam No/Posição) [2] ---
    public No first() { return inicio.getNext(); }
    public No last() { return fim.getPrev(); }
    
    public No before(No p) { 
        No prev = p.getPrev();
        return (prev == inicio) ? null : prev;
    }

    public No after(No p) {
        No next = p.getNext();
        return (next == fim) ? null : next;
    }

    public void insertFirst(Object o) { insertAfter(inicio, o); }
    public void insertLast(Object o) { insertBefore(fim, o); }

    public void insertAfter(No p, Object o) {
        No q = new No(o);
        No proximo = p.getNext();
        q.setPrev(p);
        q.setNext(proximo);
        proximo.setPrev(q);
        p.setNext(q);
        tam++;
    }

    public void insertBefore(No p, Object o) {
        No q = new No(o);
        No anterior = p.getPrev();
        q.setNext(p);
        q.setPrev(anterior);
        anterior.setNext(q);
        p.setPrev(q);
        tam++;
    }

    public void remove(No p) {
        p.getPrev().setNext(p.getNext());
        p.getNext().setPrev(p.getPrev());
        p.setNext(null); // Invalida o nó [1]
        p.setPrev(null);
        tam--;
    }

    public void swapElements(No p, No q) {
        Object temp = p.getElemento();
        p.setElemento(q.getElemento());
        q.setElemento(temp);
    }
}
class No {
    private Object elemento;
    private No next;
    private No prev;

    public No(Object x) { this.elemento = x; }
    public void setElemento(Object x) { elemento = x; }
    public Object getElemento() { return elemento; }
    public void setNext(No n) { next = n; }
    public No getNext() { return next; }
    public void setPrev(No p) { prev = p; }
    public No getPrev() { return prev; }
}

public class VetorListaDupla {
    private No inicio, fim;
    private int tam = 0;

    public VetorListaDupla() {
        inicio = new No(null);
        fim = new No(null);
        inicio.setNext(fim);
        fim.setPrev(inicio);
    }

    public boolean isEmpty() {
        return tam == 0;
    }

    // Método auxiliar crucial: busca o nó pelo índice (rank) [8, 10]
    private No getNoAtRank(int r) {
        No aux = inicio.getNext();
        for (int i = 0; i < r; i++) {
            aux = aux.getNext();
        }
        return aux;
    }

    public Object elemAtRank(int r) {
        if (r < 0 || r >= tam) {
            throw new EVetorVazio("Indice fora dos limites.");
        }
        return getNoAtRank(r).getElemento();
    }

    public Object replaceAtRank(int r, Object o) {
        if (r < 0 || r >= tam) {
            throw new EVetorVazio("Indice fora dos limites.");
        }

        No atual = getNoAtRank(r);
        Object antigo = atual.getElemento();
        atual.setElemento(o);
        return antigo;
    }

    public void insertAtRank(int r, Object o) {
        if (r < 0 || r > tam) {
            throw new EVetorVazio("Indice fora dos limites.");
        }

        No sucessor = getNoAtRank(r); // O nó que hoje está em 'r' será empurrado
        No antecessor = sucessor.getPrev();
        
        No novo = new No(o);
        // Faz as 4 ligações necessárias [9, 11]
        novo.setPrev(antecessor);
        novo.setNext(sucessor);
        antecessor.setNext(novo);
        sucessor.setPrev(novo);
        
        tam++;
    }

    public Object removeAtRank(int r) {
        if (r < 0 || r >= tam) {
            throw new EVetorVazio("Indice fora dos limites.");
        }

        No p = getNoAtRank(r);
        Object temp = p.getElemento();
        
        // "Pula" o nó p, ligando seus vizinhos diretamente [11, 12]
        No antecessor = p.getPrev();
        No sucessor = p.getNext();
        antecessor.setNext(sucessor);
        sucessor.setPrev(antecessor);
        
        tam--;
        return temp;
    }

    public int size() { return tam; }
}
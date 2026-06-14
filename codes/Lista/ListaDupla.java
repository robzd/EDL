// Implementação do TAD Lista usando Encadeamento Duplo
public class ListaDupla {
    private No first, last;
    private int tam = 0;

    // Métodos de Acesso [2]
    public No first() {
        return first;
    }

    public No last() {
        return last;
    }

    public No next(No p) {
        return p.getNext();
    }

    public No prev(No p) {
        return p.getPrev();
    }

    // Inserção: apenas troca de referências (O(1)) [9, 10]
    public void insertAfter(No p, Object o) {
        No novo = new No(o);
        No proximo = p.getNext();

        novo.setPrev(p);
        novo.setNext(proximo);
        p.setNext(novo);

        if (proximo != null)
            proximo.setPrev(novo);
        else
            last = novo; // Se p era o último, novo vira last
        tam++;
    }

    public void insertFirst(Object o) {
        No novo = new No(o);
        if (tam == 0) {
            first = last = novo;
        } else {
            novo.setNext(first);
            first.setPrev(novo);
            first = novo;
        }
        tam++;
    }

    // Remoção: "desreferencia" o nó ajustando os vizinhos (O(1)) [9, 10]
    public void remove(No p) {
        if (p == null) {
            throw new RuntimeException("Nó inválido.");
        }

        if (p == first)
            first = p.getNext();
        else
            p.getPrev().setNext(p.getNext());

        if (p == last)
            last = p.getPrev();
        else
            p.getNext().setPrev(p.getPrev());

        tam--;
    }

    public int size() {
        return tam;
    }
}
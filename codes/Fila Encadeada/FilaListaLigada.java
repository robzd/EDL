public class FilaListaLigada {
    private No first; 
    private No last;
    private int tm;

    public FilaListaLigada() {
        this.first = null;
        this.last = null;
        this.tm = 0;
    }

    public int tamanho() {
        return tm;
    }

    public boolean estaVazia() {
        return tm == 0;
    }

    public void enfileirar(Object x) {
        No novoNo = new No(x);
        if (estaVazia()) {
            first = novoNo;
        } else {
            last.setProximo(novoNo);
        }
        last = novoNo;
        tm++;
    }

    // Remove e retorna o elemento do início (dequeue) [1, 7]
    public Object desenfileirar() {
        if (estaVazia()) {
            throw new EFilaVazia("Fila vazia.");
        }
        
        Object elementoRemovido = first.getElemento();
        
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.getProximo();
        }
        
        tm--;
        return elementoRemovido;
    }

    public Object inicio() {
        if (estaVazia()) {
            throw new EFilaVazia("Fila vazia.");
        }
        return first.getElemento();
    }
}
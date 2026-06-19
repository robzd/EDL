import java.util.Iterator;

public class ArvoreGenerica {
    private No raiz;
    private int tamanho;

    // Árvore vazia — permite isEmpty() retornar true
    public ArvoreGenerica() {
        this.raiz = null;
        this.tamanho = 0;
    }

    // Árvore já iniciada com uma raiz
    public ArvoreGenerica(Object elementoRaiz) {
        this.raiz = new No(null, elementoRaiz);
        this.tamanho = 1;
    }

    // --- Métodos de Acesso ---

    public No root() {
        return raiz;
    }

    public No parent(No v) {
        if (isRoot(v))
            throw new IllegalArgumentException("A raiz não possui pai.");
        return v.getPai();
    }

    public Iterator<No> children(No v) {
        return v.getFilhos().iterator();
    }

    // --- Métodos de Consulta ---

    public boolean isInternal(No v) {
        return !v.getFilhos().isEmpty();
    }

    public boolean isExternal(No v) {
        return v.getFilhos().isEmpty();
    }

    public boolean isRoot(No v) {
        return v == raiz;
    }

    // Profundidade: número de ligações do nó até a raiz — O(profundidade)
    public int depth(No v) {
        if (isRoot(v))
            return 0;
        return 1 + depth(parent(v));
    }

    // Altura: número de ligações do nó até o "filho" mais distante — O(n)
    public int height(No v) {
        if (isExternal(v))
            return 0;
        int h = 0;
        Iterator<No> filhos = children(v);
        while (filhos.hasNext()) {
            h = Math.max(h, height(filhos.next()));
        }
        return 1 + h;
    }

    // --- Métodos Genéricos ---

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    // --- Métodos de Atualização ---

    public Object replace(No v, Object o) {
        Object antigo = v.getElemento();
        v.setElemento(o);
        return antigo;
    }

    public No addChild(No pai, Object o) {
        No novo = new No(pai, o);
        pai.addFilho(novo);
        tamanho++;
        return novo;
    }
}

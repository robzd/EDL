public class No {
    private int chave;
    private Object elemento;
    private No pai, esquerdo, direito;

    public No(int chave, Object elemento, No pai) {
        this.chave = chave;
        this.elemento = elemento;
        this.pai = pai;
        this.esquerdo = null;
        this.direito = null;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int c) {
        this.chave = c;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object o) {
        this.elemento = o;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No p) {
        this.pai = p;
    }

    public No getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(No e) {
        this.esquerdo = e;
    }

    public No getDireito() {
        return direito;
    }

    public void setDireito(No d) {
        this.direito = d;
    }
}
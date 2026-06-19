class No {
    private MyEntry item;
    private No pai, esquerdo, direito;

    public No(Object k, Object v, No pai) {
        this.item = new MyEntry(k, v);
        this.pai = pai;
    }

    public MyEntry getItem() {
        return item;
    }

    public No getPai() {
        return pai;
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
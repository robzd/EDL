import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class No {
    private Object elemento;
    private No pai;
    private List<No> filhos;

    public No(No pai, Object elemento) {
        this.pai = pai;
        this.elemento = elemento;
        this.filhos = new ArrayList<>();
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

    void setPai(No pai) {
        this.pai = pai;
    }

    public List<No> getFilhos() {
        return Collections.unmodifiableList(filhos);
    }

    void addFilho(No filho) {
        filhos.add(filho);
    }
}

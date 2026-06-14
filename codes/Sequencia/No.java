public class No {
    private Object elemento;
    private No next, prev;

    public No(Object e) { this.elemento = e; }
    public Object getElemento() { return elemento; }
    public void setElemento(Object e) { this.elemento = e; }
    public No getNext() { return next; }
    public void setNext(No n) { this.next = n; }
    public No getPrev() { return prev; }
    public void setPrev(No p) { this.prev = p; }
}
public class Fila {
    private Object array[];
    private int inicio = 0;
    private int fim = 0;

    public Fila(int tam) {
        array = new Object[tam];
    }

    public int size() {
        return (array.length - inicio + fim) % array.length;
    }

    public boolean isEmpty() {
        if (fim == inicio)
            return true;
        return false;
    }

    public Object first() throws EFilaVazia {
        if (isEmpty())
            throw new EFilaVazia("Fila vazia.");
        return array[inicio];
    }

    public Object dequeue() throws EFilaVazia {
        if (isEmpty())
            throw new EFilaVazia("Fila vazia.");
        inicio++;
        if (inicio == array.length) inicio = 0;

        return array[inicio];
    }

    public void enqueue(int o) {
        if (size() == array.length - 1) {
            Object[] aux;
            aux = (Object[]) new Object[array.length * 2];
            int inicio2 = inicio;
            for (int i = 0; i < array.length; i++) {
                aux[i] = array[inicio2];
                inicio2 = (inicio2 + 1) % array.length;
            }
            fim = size();
            inicio = 0;
            array.length *= 2;
            array = aux;
        }
        array[fim] = o;
        fim = (fim + 1) % array.length;
    }
}
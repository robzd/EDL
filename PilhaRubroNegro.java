public class PilhaRubroNegro {
    private int topoVermelho;
    private int topoPreto;

    private int capacidade;
    private int crescimento;

    private Object[] pilhaRubroNegro;

    public PilhaRubroNegro(int capacidade, int crescimento) {
        this.capacidade = capacidade;
        this.crescimento = crescimento > 0 ? crescimento : 2;
        this.topoVermelho = -1;
        this.topoPreto = capacidade;

        pilhaRubroNegro = new Object[capacidade];
    }

    public int sizeVermelho() {
        return topoVermelho + 1;
    }

    public int sizePreto() {
        return capacidade - topoPreto;
    }

    public int sizeArray() {
        return sizePreto() + sizeVermelho();
    }

    public int sizeArray_Disponivel() {
        return topoPreto - topoVermelho - 1;
    }

    public boolean isEmpty_Vermelho() {
        return topoVermelho == -1;
    }

    public boolean isEmpty_Preto() {
        return topoPreto == capacidade;
    }

    private void verificarEspaco() {

        if (topoVermelho + 1 == topoPreto) {
            int novoTamanho = capacidade * crescimento;
            redimensionar(novoTamanho);
        }

        else if (sizeArray() <= capacidade / 3 && capacidade > 1) {
            int novoTamanho = capacidade / 2;
            redimensionar(novoTamanho);
        }
    }

    private void redimensionar(int novoTamanho) {
        Object[] novaPilha = new Object[novoTamanho];

        for (int i = 0; i <= topoVermelho; i++) {
            novaPilha[i] = pilhaRubroNegro[i];
        }

        int tamanhoPreto = capacidade - topoPreto;
        int novatopoPreto = novoTamanho - tamanhoPreto;
        for (int i = 0; i < tamanhoPreto; i++) {
            novaPilha[novatopoPreto + i] = pilhaRubroNegro[topoPreto + i];
        }

        topoPreto = novatopoPreto;
        capacidade = novoTamanho;
        pilhaRubroNegro = novaPilha;
    }

    public Object topVermelho() throws PilhaVaziaExcecao {
        if (isEmpty_Vermelho())
            throw new PilhaVaziaExcecao("Pilha Vermelha Vazia");
        return pilhaRubroNegro[topoVermelho];
    }

    public Object topPreto() throws PilhaVaziaExcecao {
        if (isEmpty_Preto())
            throw new PilhaVaziaExcecao("Pilha Preta Vazia");
        return pilhaRubroNegro[topoPreto];
    }

    public Object popVermelho() {
        if (isEmpty_Vermelho()) throw new PilhaVaziaExcecao("Pilha Vermelha Vazia");
        Object obj = pilhaRubroNegro[topoVermelho--];
        verificarEspaco();
        return obj;
    }

    public Object popPreto() {
        if (isEmpty_Preto()) throw new PilhaVaziaExcecao("Pilha Preta Vazia");
        Object obj = pilhaRubroNegro[topoPreto++];
        verificarEspaco();
        return obj;
    }

    public void pushVermelho(Object novo_objeto) {
        verificarEspaco();
        pilhaRubroNegro[++topoVermelho] = novo_objeto;
    }

    public void pushPreto(Object novo_objeto) {
        verificarEspaco();
        pilhaRubroNegro[--topoPreto] = novo_objeto;
    }

    public void printar_array() {
        System.out.println("-----------------------------------");
        System.out.println("Pilha Array");
        System.out.println(String.format(
                "Tamanho do Array: %d\nTamanho Pilha Vermelha: %d - P: %d\nTamanho Pilha Preta: %d - P: %d",
                capacidade, sizeVermelho(), topoVermelho, sizePreto(), topoPreto));

        for (int i = 0; i < capacidade; i++) {
            if (i <= topoVermelho) {
                System.out.print(String.format(" %s V |", pilhaRubroNegro[i]));
            } else if (i >= topoPreto) {
                System.out.print(String.format(" %s P |", pilhaRubroNegro[i]));
            } else {
                System.out.print("     |");
            }
        }
        System.out.println("\n-----------------------------------");
    }
}
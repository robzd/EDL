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

    public int sizePilha() {
        return sizePreto() + sizeVermelho();
    }

    public int sizePilhaDisponivel() {
        return topoPreto - topoVermelho - 1;
    }

    public boolean isEmptyVermelho() {
        return topoVermelho == -1;
    }

    public boolean isEmptyPreto() {
        return topoPreto == capacidade;
    }

    private void verificarEspaco() {

        if (topoVermelho + 1 == topoPreto) {
            int novoTamanho = capacidade * crescimento;
            redimensionar(novoTamanho);
        }

        else if (sizePilha() <= capacidade / 3 && capacidade > 1) {
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
        int novotopoPreto = novoTamanho - tamanhoPreto;
        for (int i = 0; i < tamanhoPreto; i++) {
            novaPilha[novotopoPreto + i] = pilhaRubroNegro[topoPreto + i];
        }

        topoPreto = novotopoPreto;
        capacidade = novoTamanho;
        pilhaRubroNegro = novaPilha;
    }

    public Object topVermelho() throws PilhaVaziaExcecao {
        if (isEmptyVermelho())
            throw new PilhaVaziaExcecao("Pilha Vermelha Vazia");
        return pilhaRubroNegro[topoVermelho];
    }

    public Object topPreto() throws PilhaVaziaExcecao {
        if (isEmptyPreto())
            throw new PilhaVaziaExcecao("Pilha Preta Vazia");
        return pilhaRubroNegro[topoPreto];
    }

    public Object popVermelho() throws PilhaVaziaExcecao {
        if (isEmptyVermelho())
            throw new PilhaVaziaExcecao("Pilha Vermelha Vazia");
        Object obj = pilhaRubroNegro[topoVermelho--];
        verificarEspaco();
        return obj;
    }

    public Object popPreto() throws PilhaVaziaExcecao {
        if (isEmptyPreto())
            throw new PilhaVaziaExcecao("Pilha Preta Vazia");
        Object obj = pilhaRubroNegro[topoPreto++];
        verificarEspaco();
        return obj;
    }

    public void pushVermelho(Object novoObjeto) {
        verificarEspaco();
        pilhaRubroNegro[++topoVermelho] = novoObjeto;
    }

    public void pushPreto(Object novoObjeto) {
        verificarEspaco();
        pilhaRubroNegro[--topoPreto] = novoObjeto;
    }

    public void printarArray() {
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
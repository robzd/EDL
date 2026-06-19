public class ArvorePesquisaBinaria {
    private No raiz;
    private int tamanho = 0;

    public ArvorePesquisaBinaria() {
        this.raiz = null;
    }

    public No search(int chave, No no) {
        if (no == null)
            return null;
        if (chave == no.getChave())
            return no;
        if (chave < no.getChave())
            return search(chave, no.getEsquerdo());
        else
            return search(chave, no.getDireito());
    }

    public void insert(int chave, Object objeto) {
        if (raiz == null) {
            raiz = new No(chave, objeto, null);
        } else {
            insertRecursivo(raiz, chave, objeto);
        }
        tamanho++;
    }

    private void insertRecursivo(No no, int chave, Object objeto) {
        if (chave < no.getChave()) {
            if (no.getEsquerdo() == null)
                no.setEsquerdo(new No(chave, objeto, no));
            else
                insertRecursivo(no.getEsquerdo(), chave, objeto);
        } else {
            if (no.getDireito() == null)
                no.setDireito(new No(chave, objeto, no));
            else
                insertRecursivo(no.getDireito(), chave, objeto);
        }
    }

    public void remove(int chave) {
        No no = search(chave, raiz);
        if (no != null) {
            raiz = removeRecursivo(no);
            tamanho--;
        }
    }

    private No removeRecursivo(No no) {
        if (no.getEsquerdo() == null)
            return trocarNos(no, no.getDireito());
        if (no.getDireito() == null)
            return trocarNos(no, no.getEsquerdo());

        No sucessor = menorChave(no.getDireito());
        no.setChave(sucessor.getChave());
        no.setElemento(sucessor.getElemento());

        removeRecursivo(sucessor);
        return raiz;
    }

    private No menorChave(No no) {
        while (no.getEsquerdo() != null)
            no = no.getEsquerdo();
        return no;
    }

    private No trocarNos(No no, No filho) {
        if (no.getPai() == null)
            raiz = filho;
        else if (no == no.getPai().getEsquerdo())
            no.getPai().setEsquerdo(filho);
        else
            no.getPai().setDireito(filho);

        if (filho != null)
            filho.setPai(no.getPai());
        return raiz;
    }

    public void mostrar() {
        if (raiz == null) {
            System.out.println("(árvore vazia)");
            return;
        }

        int linhas = altura(raiz) + 1;
        int colunas = tamanho;
        String[][] matriz = new String[linhas][colunas];

        preencherMatriz(raiz, 0, new int[] { 0 }, matriz);

        int largura = 4;
        StringBuilder sb = new StringBuilder(); // serve para construir uma string e ir adicionando novos valores (no
                                                // caso, números) dentro dela sem ter que usar concatenação de vários
                                                // system.out.print...
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                String valor = (matriz[i][j] == null) ? "" : matriz[i][j];
                sb.append(String.format("%-" + largura + "s", valor));
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private void preencherMatriz(No no, int profundidade, int[] coluna, String[][] matriz) {
        if (no == null)
            return;
        preencherMatriz(no.getEsquerdo(), profundidade + 1, coluna, matriz);
        matriz[profundidade][coluna[0]] = String.valueOf(no.getChave());
        coluna[0]++;
        preencherMatriz(no.getDireito(), profundidade + 1, coluna, matriz);
    }

    private int altura(No no) {
        if (no == null)
            return -1;
        return 1 + Math.max(altura(no.getEsquerdo()), altura(no.getDireito()));
    }

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public No root() {
        return raiz;
    }
}

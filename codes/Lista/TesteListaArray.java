public class TesteListaArray {

    public static void main(String[] args) {
        ListaArray lista = new ListaArray(2);

        System.out.println("===== TESTE 1 - ListaArray vazia =====");
        conferir(lista.isEmpty(), "A lista deveria iniciar vazia.");
        conferir(lista.size() == 0, "O tamanho inicial deveria ser 0.");

        System.out.println("\n===== TESTE 2 - Inserções =====");
        lista.insertFirst("B");
        lista.insertFirst("A");
        lista.insertLast("C");
        conferir(lista.size() == 3, "O tamanho deveria ser 3.");
        conferir("A".equals(lista.elemAtRank(0)), "Posição 0 deveria conter A.");
        conferir("B".equals(lista.elemAtRank(1)), "Posição 1 deveria conter B.");
        conferir("C".equals(lista.elemAtRank(2)), "Posição 2 deveria conter C.");

        System.out.println("\n===== TESTE 3 - insertAfter =====");
        lista.insertAfter(1, "X");
        conferir(lista.size() == 4, "O tamanho deveria ser 4 após insertAfter.");
        conferir("A".equals(lista.elemAtRank(0)), "Posição 0 deveria conter A.");
        conferir("B".equals(lista.elemAtRank(1)), "Posição 1 deveria conter B.");
        conferir("X".equals(lista.elemAtRank(2)), "Posição 2 deveria conter X.");
        conferir("C".equals(lista.elemAtRank(3)), "Posição 3 deveria conter C.");

        System.out.println("\n===== TESTE 4 - replaceAtRank =====");
        Object antigo = lista.replaceAtRank(2, "Y");
        conferir("X".equals(antigo), "replaceAtRank deveria retornar o valor antigo.");
        conferir("Y".equals(lista.elemAtRank(2)), "Posição 2 deveria conter Y.");

        System.out.println("\n===== TESTE 5 - remove =====");
        Object removido = lista.remove(1);
        conferir("B".equals(removido), "remove deveria retornar B.");
        conferir(lista.size() == 3, "O tamanho deveria ser 3 após a remoção.");
        conferir("A".equals(lista.elemAtRank(0)), "Posição 0 deveria conter A.");
        conferir("Y".equals(lista.elemAtRank(1)), "Posição 1 deveria conter Y.");
        conferir("C".equals(lista.elemAtRank(2)), "Posição 2 deveria conter C.");

        System.out.println("\n===== TESTE 6 - first/last/next/prev =====");
        conferir(lista.first() == 0, "first deveria retornar 0.");
        conferir(lista.last() == 2, "last deveria retornar 2.");
        conferir(lista.next(1) == 2, "next(1) deveria retornar 2.");
        conferir(lista.prev(2) == 1, "prev(2) deveria retornar 1.");

        System.out.println("\n===== TESTE 7 - Exceções =====");
        conferirExcecao(new Acao() {
            public void executar() {
                new ListaArray(1).first();
            }
        }, "first deveria lançar exceção em lista vazia.");
        conferirExcecao(new Acao() {
            public void executar() {
                lista.elemAtRank(100);
            }
        }, "elemAtRank deveria rejeitar índice inválido.");
        conferirExcecao(new Acao() {
            public void executar() {
                lista.replaceAtRank(100, "Z");
            }
        }, "replaceAtRank deveria rejeitar índice inválido.");
        conferirExcecao(new Acao() {
            public void executar() {
                lista.remove(100);
            }
        }, "remove deveria rejeitar índice inválido.");

        System.out.println("\n===== Todos os testes passaram =====");
    }

    private static void conferir(boolean condicao, String mensagem) {
        if (!condicao) throw new RuntimeException(mensagem);
    }

    private static void conferirExcecao(Acao acao, String mensagem) {
        boolean lancou = false;
        try {
            acao.executar();
        } catch (RuntimeException e) {
            lancou = true;
        }
        if (!lancou) throw new RuntimeException(mensagem);
    }

    private interface Acao {
        void executar();
    }
}
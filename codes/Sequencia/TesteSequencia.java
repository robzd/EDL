public class TesteSequencia {

    public static void main(String[] args) {
        Sequencia sequencia = new Sequencia();

        System.out.println("===== TESTE 1 - Sequencia vazia =====");
        conferir(sequencia.size() == 0, "A sequencia deveria iniciar vazia.");
        conferir(sequencia.isEmpty(), "isEmpty() deveria retornar true no inicio.");
        conferir(sequencia.first() != null, "first() deveria retornar a sentinela inicial.");
        conferir(sequencia.last() != null, "last() deveria retornar a sentinela final.");
        conferir(sequencia.first().getElemento() == null, "A sentinela inicial deveria armazenar null.");
        conferir(sequencia.last().getElemento() == null, "A sentinela final deveria armazenar null.");

        System.out.println("\n===== TESTE 2 - Insercoes =====");
        sequencia.insertFirst("B");
        sequencia.insertFirst("A");
        sequencia.insertLast("C");
        conferir(sequencia.size() == 3, "O tamanho deveria ser 3.");
        conferir("A".equals(sequencia.elemAtRank(0)), "Rank 0 deveria conter A.");
        conferir("B".equals(sequencia.elemAtRank(1)), "Rank 1 deveria conter B.");
        conferir("C".equals(sequencia.elemAtRank(2)), "Rank 2 deveria conter C.");

        System.out.println("\n===== TESTE 3 - Insercao por rank =====");
        sequencia.insertAtRank(1, "X");
        conferir(sequencia.size() == 4, "O tamanho deveria ser 4 apos insertAtRank.");
        conferir("A".equals(sequencia.elemAtRank(0)), "Rank 0 deveria continuar com A.");
        conferir("X".equals(sequencia.elemAtRank(1)), "Rank 1 deveria conter X.");
        conferir("B".equals(sequencia.elemAtRank(2)), "Rank 2 deveria conter B.");
        conferir("C".equals(sequencia.elemAtRank(3)), "Rank 3 deveria conter C.");

        System.out.println("\n===== TESTE 4 - Substituicao =====");
        Object antigo = sequencia.replaceAtRank(2, "Y");
        conferir("B".equals(antigo), "replaceAtRank deveria retornar o valor antigo.");
        conferir("Y".equals(sequencia.elemAtRank(2)), "Rank 2 deveria conter Y.");

        System.out.println("\n===== TESTE 5 - Remocao =====");
        Object removido = sequencia.removeAtRank(1);
        conferir("X".equals(removido), "removeAtRank deveria retornar X.");
        conferir(sequencia.size() == 3, "O tamanho deveria ser 3 apos a remocao.");
        conferir("A".equals(sequencia.elemAtRank(0)), "Rank 0 deveria conter A.");
        conferir("Y".equals(sequencia.elemAtRank(1)), "Rank 1 deveria conter Y.");
        conferir("C".equals(sequencia.elemAtRank(2)), "Rank 2 deveria conter C.");

        System.out.println("\n===== TESTE 6 - Operacoes com No =====");
        conferir("A".equals(sequencia.first().getElemento()), "first() deveria apontar para A.");
        conferir("C".equals(sequencia.last().getElemento()), "last() deveria apontar para C.");
        conferir(sequencia.after(sequencia.first()).getElemento().equals("Y"), "after(first) deveria apontar para Y.");
        conferir(sequencia.before(sequencia.last()).getElemento().equals("Y"), "before(last) deveria apontar para Y.");
        conferir(sequencia.rankOf(sequencia.first()) == 0, "rankOf(first) deveria ser 0.");
        conferir(sequencia.rankOf(sequencia.last()) == 2, "rankOf(last) deveria ser 2.");

        System.out.println("\n===== TESTE 7 - swapElements =====");
        sequencia.swapElements(sequencia.first(), sequencia.last());
        conferir("C".equals(sequencia.elemAtRank(0)), "Depois do swap, rank 0 deveria conter C.");
        conferir("A".equals(sequencia.elemAtRank(2)), "Depois do swap, rank 2 deveria conter A.");

        System.out.println("\n===== TESTE 8 - Excecoes =====");
        conferirExcecao(new Acao() {
            public void executar() {
                sequencia.elemAtRank(-1);
            }
        }, "elemAtRank deveria rejeitar rank negativo.");
        conferirExcecao(new Acao() {
            public void executar() {
                sequencia.insertAtRank(100, "Z");
            }
        }, "insertAtRank deveria rejeitar rank invalido.");
        conferirExcecao(new Acao() {
            public void executar() {
                sequencia.removeAtRank(100);
            }
        }, "removeAtRank deveria rejeitar rank invalido.");

        conferirExcecao(new Acao() {
            public void executar() {
                sequencia.atRank(100);
            }
        }, "atRank deveria rejeitar rank invalido.");

        System.out.println("\n===== Todos os testes passaram =====");
    }

    private static void conferir(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new RuntimeException(mensagem);
        }
    }

    private static void conferirExcecao(Acao acao, String mensagem) {
        boolean lancou = false;
        try {
            acao.executar();
        } catch (IndexOutOfBoundsException e) {
            lancou = true;
        }

        if (!lancou) {
            throw new RuntimeException(mensagem);
        }
    }

    private interface Acao {
        void executar();
    }
}
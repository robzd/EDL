public class TesteListaDupla {

    public static void main(String[] args) {
        ListaDupla lista = new ListaDupla();

        System.out.println("===== TESTE 1 - ListaDupla vazia =====");
        conferir(lista.size() == 0, "A lista deveria iniciar vazia.");
        conferir(lista.first() == null, "first deveria ser null em lista vazia.");
        conferir(lista.last() == null, "last deveria ser null em lista vazia.");

        System.out.println("\n===== TESTE 2 - Inserções =====");
        lista.insertFirst("B");
        lista.insertFirst("A");
        conferir(lista.size() == 2, "O tamanho deveria ser 2.");
        conferir("A".equals(lista.first().getElemento()), "first deveria conter A.");
        conferir("B".equals(lista.last().getElemento()), "last deveria conter B.");

        System.out.println("\n===== TESTE 3 - insertAfter =====");
        lista.insertAfter(lista.first(), "X");
        conferir(lista.size() == 3, "O tamanho deveria ser 3 após insertAfter.");
        conferir("A".equals(lista.first().getElemento()), "first deveria continuar com A.");
        conferir("X".equals(lista.first().getNext().getElemento()), "O nó do meio deveria conter X.");
        conferir("B".equals(lista.last().getElemento()), "last deveria continuar com B.");

        System.out.println("\n===== TESTE 4 - Remoção =====");
        lista.remove(lista.first().getNext());
        conferir(lista.size() == 2, "O tamanho deveria ser 2 após a remoção.");
        conferir("A".equals(lista.first().getElemento()), "first deveria conter A.");
        conferir("B".equals(lista.last().getElemento()), "last deveria conter B.");

        System.out.println("\n===== TESTE 5 - Encadeamento =====");
        conferir(lista.next(lista.first()).getElemento().equals("B"), "next(first) deveria apontar para B.");
        conferir(lista.prev(lista.last()).getElemento().equals("A"), "prev(last) deveria apontar para A.");

        System.out.println("\n===== TESTE 6 - Exceções =====");
        conferirExcecao(new Acao() {
            public void executar() {
                new ListaDupla().remove(null);
            }
        }, "remove deveria falhar com nó inválido.");

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
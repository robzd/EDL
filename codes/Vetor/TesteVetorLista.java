public class TesteVetorLista {

    public static void main(String[] args) {
        VetorListaDupla vetor = new VetorListaDupla();

        System.out.println("===== TESTE 1 - VetorLista vazio =====");
        conferir(vetor.size() == 0, "O tamanho inicial deveria ser 0.");
        conferir(vetor.isEmpty(), "O VetorLista deveria iniciar vazio.");

        System.out.println("\n===== TESTE 2 - Inserções =====");
        vetor.insertAtRank(0, "A");
        vetor.insertAtRank(1, "B");
        vetor.insertAtRank(2, "C");
        conferir("A".equals(vetor.elemAtRank(0)), "Posição 0 deveria conter A.");
        conferir("B".equals(vetor.elemAtRank(1)), "Posição 1 deveria conter B.");
        conferir("C".equals(vetor.elemAtRank(2)), "Posição 2 deveria conter C.");
        conferir(vetor.size() == 3, "O tamanho deveria ser 3 após as inserções.");

        System.out.println("\n===== TESTE 3 - Inserção no meio =====");
        vetor.insertAtRank(1, "X");
        conferir("A".equals(vetor.elemAtRank(0)), "Posição 0 deveria continuar com A.");
        conferir("X".equals(vetor.elemAtRank(1)), "Posição 1 deveria conter X.");
        conferir("B".equals(vetor.elemAtRank(2)), "Posição 2 deveria conter B.");
        conferir("C".equals(vetor.elemAtRank(3)), "Posição 3 deveria conter C.");

        System.out.println("\n===== TESTE 4 - Substituição =====");
        Object antigo = vetor.replaceAtRank(2, "Y");
        conferir("B".equals(antigo), "replaceAtRank deveria retornar o valor antigo.");
        conferir("Y".equals(vetor.elemAtRank(2)), "Posição 2 deveria conter Y.");

        System.out.println("\n===== TESTE 5 - Remoção =====");
        Object removido = vetor.removeAtRank(1);
        conferir("X".equals(removido), "removeAtRank deveria retornar o valor removido.");
        conferir(vetor.size() == 3, "O tamanho deveria ser 3 após a remoção.");
        conferir("A".equals(vetor.elemAtRank(0)), "Posição 0 deveria conter A.");
        conferir("Y".equals(vetor.elemAtRank(1)), "Posição 1 deveria conter Y.");
        conferir("C".equals(vetor.elemAtRank(2)), "Posição 2 deveria conter C.");

        System.out.println("\n===== TESTE 6 - Inserções nas extremidades =====");
        vetor.insertAtRank(0, "Inicio");
        vetor.insertAtRank(vetor.size(), "Fim");
        conferir("Inicio".equals(vetor.elemAtRank(0)), "O primeiro elemento deveria ser Inicio.");
        conferir("Fim".equals(vetor.elemAtRank(vetor.size() - 1)), "O último elemento deveria ser Fim.");

        System.out.println("\n===== TESTE 7 - Exceções =====");
        conferirExcecao(new Acao() {
            public void executar() {
                vetor.elemAtRank(-1);
            }
        }, "elemAtRank deveria rejeitar índice negativo.");
        conferirExcecao(new Acao() {
            public void executar() {
                vetor.replaceAtRank(100, "Z");
            }
        }, "replaceAtRank deveria rejeitar índice inválido.");
        conferirExcecao(new Acao() {
            public void executar() {
                vetor.removeAtRank(100);
            }
        }, "removeAtRank deveria rejeitar índice inválido.");
        conferirExcecao(new Acao() {
            public void executar() {
                vetor.insertAtRank(100, "Z");
            }
        }, "insertAtRank deveria rejeitar índice maior que size().");

        System.out.println("\n===== Todos os testes passaram =====");
    }

    private static void conferir(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new RuntimeException(mensagem);
        }
    }

    private static void conferirExcecao(Acao acao, String mensagem) {
        boolean lancouExcecao = false;
        try {
            acao.executar();
        } catch (RuntimeException e) {
            lancouExcecao = true;
        }

        if (!lancouExcecao) {
            throw new RuntimeException(mensagem);
        }
    }

    private interface Acao {
        void executar();
    }
}
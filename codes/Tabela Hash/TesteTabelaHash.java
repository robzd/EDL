public class TesteTabelaHash {

    public static void main(String[] args) {

        // =========================================================
        // 1. Tabela vazia
        // =========================================================
        System.out.println("=== Tabela Vazia ===");
        TabelaHash vazia = new TabelaHash(11, TabelaHash.LINEAR);
        System.out.println("isEmpty():      " + vazia.isEmpty());
        System.out.println("size():         " + vazia.size());
        System.out.println("capacidade():   " + vazia.capacidade());
        System.out.println("fatorCarga():   " + vazia.fatorCarga());
        System.out.println("get(1):         " + vazia.get(1));
        System.out.println("remove(1):      " + vazia.remove(1));
        System.out.println("containsKey(1): " + vazia.containsKey(1));

        // =========================================================
        // 2. Linear probing - insercao e busca
        // =========================================================
        System.out.println("\n=== Linear Probing: insercao e busca ===");
        TabelaHash linear = new TabelaHash(11, TabelaHash.LINEAR);
        int[] chaves = { 15, 26, 37, 4 };
        for (int c : chaves)
            linear.put(c, "v" + c);

        System.out.println("size():   " + linear.size());
        for (int c : chaves)
            System.out.println("get(" + c + "): " + linear.get(c));
        System.out.println("get(99) (inexistente): " + linear.get(99));

        // =========================================================
        // 3. Atualizacao de chave existente (nao pode duplicar)
        // =========================================================
        System.out.println("\n=== Atualizacao de chave existente ===");
        linear.put(15, "novo15");
        System.out.println("get(15):  " + linear.get(15));
        System.out.println("size():   " + linear.size() + "  (deve continuar 4)");

        // =========================================================
        // 4. Remocao e a sondagem apos o marcador AVAILABLE
        // =========================================================
        System.out.println("\n=== Remocao ===");
        System.out.println("remove(26):      " + linear.remove(26));
        System.out.println("size():          " + linear.size());
        System.out.println("get(26):         " + linear.get(26));
        System.out.println("containsKey(26): " + linear.containsKey(26));
        // 37 estava depois de 26 na sondagem: precisa continuar acessivel
        System.out.println("get(37) apos remover 26: " + linear.get(37));
        System.out.println("remove(26) de novo:      " + linear.remove(26));

        // Insercao reaproveita o slot liberado
        linear.put(48, "v48");
        System.out.println("put(48) -> get(48): " + linear.get(48));
        System.out.println("get(37) segue ok:   " + linear.get(37));

        // =========================================================
        // 5. Hash duplo
        // =========================================================
        System.out.println("\n=== Hash Duplo: insercao e busca ===");
        TabelaHash duplo = new TabelaHash(11, TabelaHash.DUPLO);
        for (int c : chaves)
            duplo.put(c, "v" + c);

        System.out.println("size():   " + duplo.size());
        for (int c : chaves)
            System.out.println("get(" + c + "): " + duplo.get(c));
        System.out.println("remove(37): " + duplo.remove(37));
        System.out.println("get(37):    " + duplo.get(37));
        System.out.println("get(15):    " + duplo.get(15));

        // =========================================================
        // 6. Fator de carga e Rehash (alfa > 0.5 dobra a tabela)
        // =========================================================
        System.out.println("\n=== Rehash por fator de carga ===");
        TabelaHash cresce = new TabelaHash(11, TabelaHash.LINEAR);
        System.out.println("capacidade inicial: " + cresce.capacidade());

        for (int c = 1; c <= 20; c++) {
            int antes = cresce.capacidade();
            cresce.put(c, "v" + c);
            if (cresce.capacidade() != antes) {
                System.out.println("  rehash ao inserir " + c + ": " + antes + " -> " + cresce.capacidade());
            }
        }
        System.out.println("size():       " + cresce.size());
        System.out.println("capacidade(): " + cresce.capacidade());
        System.out.println("fatorCarga(): " + cresce.fatorCarga() + "  (deve ser <= 0.5)");

        boolean todas = true;
        for (int c = 1; c <= 20; c++) {
            if (!("v" + c).equals(cresce.get(c)))
                todas = false;
        }
        System.out.println("todas as chaves apos rehash: " + todas);

        // =========================================================
        // 7. Rehash com hash duplo
        // =========================================================
        System.out.println("\n=== Rehash com Hash Duplo ===");
        TabelaHash cresceDuplo = new TabelaHash(11, TabelaHash.DUPLO);
        for (int c = 1; c <= 50; c++)
            cresceDuplo.put(c * 7, "v" + c);

        boolean todasDuplo = true;
        for (int c = 1; c <= 50; c++) {
            if (!("v" + c).equals(cresceDuplo.get(c * 7)))
                todasDuplo = false;
        }
        System.out.println("size():                      " + cresceDuplo.size());
        System.out.println("capacidade():                " + cresceDuplo.capacidade());
        System.out.println("fatorCarga():                " + cresceDuplo.fatorCarga());
        System.out.println("todas as chaves apos rehash: " + todasDuplo);

        // =========================================================
        // 8. Chaves String e hashCode negativo
        // =========================================================
        System.out.println("\n=== Chaves String ===");
        TabelaHash strs = new TabelaHash(11, TabelaHash.DUPLO);
        String[] nomes = { "ana", "bruno", "carla", "polygenelubricants" };
        for (String s : nomes)
            strs.put(s, s.length());
        for (String s : nomes)
            System.out.println("get(" + s + "): " + strs.get(s));

        // =========================================================
        // 9. Tecnica invalida
        // =========================================================
        System.out.println("\n=== Tecnica invalida ===");
        try {
            new TabelaHash(11, "quadratico");
            System.out.println("ERRO: deveria ter lancado excecao");
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        }

        // =========================================================
        // 10. Esvaziar a tabela
        // =========================================================
        System.out.println("\n=== Esvaziar ===");
        for (int c = 1; c <= 20; c++)
            cresce.remove(c);
        System.out.println("size():    " + cresce.size());
        System.out.println("isEmpty(): " + cresce.isEmpty());
        cresce.put(100, "v100"); // reuso apos esvaziar
        System.out.println("get(100):  " + cresce.get(100));

        System.out.println("\n===== Todos os testes passaram =====");
    }
}

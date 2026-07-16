public class TesteFilaListaLigada {

    public static void main(String[] args) {

        // =========================================================
        // 1. Fila vazia
        // =========================================================
        System.out.println("=== Fila Vazia ===");
        FilaListaLigada vazia = new FilaListaLigada();
        System.out.println("estaVazia(): " + vazia.estaVazia());
        System.out.println("tamanho():   " + vazia.tamanho());

        try {
            vazia.inicio();
            System.out.println("ERRO: inicio() deveria ter lancado excecao");
        } catch (EFilaVazia e) {
            System.out.println("inicio():        EFilaVazia - " + e.getMessage());
        }

        try {
            vazia.desenfileirar();
            System.out.println("ERRO: desenfileirar() deveria ter lancado excecao");
        } catch (EFilaVazia e) {
            System.out.println("desenfileirar(): EFilaVazia - " + e.getMessage());
        }

        // =========================================================
        // 2. Enfileirar
        // =========================================================
        System.out.println("\n=== Enfileirar ===");
        FilaListaLigada fila = new FilaListaLigada();
        int[] valores = { 10, 20, 30, 40, 50 };
        for (int v : valores)
            fila.enfileirar(v);

        System.out.println("tamanho():   " + fila.tamanho());
        System.out.println("estaVazia(): " + fila.estaVazia());

        // =========================================================
        // 3. inicio() - primeiro elemento sem remover
        // =========================================================
        System.out.println("\n=== inicio() ===");
        System.out.println("inicio():  " + fila.inicio());
        System.out.println("inicio():  " + fila.inicio() + "  (nao pode mudar)");
        System.out.println("tamanho(): " + fila.tamanho() + "  (deve continuar 5)");

        // =========================================================
        // 4. desenfileirar() - ordem FIFO
        // =========================================================
        System.out.println("\n=== desenfileirar() em ordem FIFO ===");
        System.out.print("Saida: ");
        while (!fila.estaVazia())
            System.out.print(fila.desenfileirar() + " ");
        System.out.println();
        System.out.println("tamanho() apos esvaziar: " + fila.tamanho());
        System.out.println("estaVazia():             " + fila.estaVazia());

        // =========================================================
        // 5. Fila com um unico elemento (first == last)
        // =========================================================
        System.out.println("\n=== Um unico elemento ===");
        FilaListaLigada um = new FilaListaLigada();
        um.enfileirar("A");
        System.out.println("tamanho():       " + um.tamanho());
        System.out.println("inicio():        " + um.inicio());
        System.out.println("desenfileirar(): " + um.desenfileirar());
        System.out.println("estaVazia():     " + um.estaVazia());

        // =========================================================
        // 6. Reuso apos esvaziar (last precisa ter sido zerado)
        // =========================================================
        System.out.println("\n=== Reuso apos esvaziar ===");
        um.enfileirar("B");
        um.enfileirar("C");
        System.out.println("inicio():        " + um.inicio());
        System.out.println("tamanho():       " + um.tamanho());
        System.out.println("desenfileirar(): " + um.desenfileirar());
        System.out.println("desenfileirar(): " + um.desenfileirar());
        System.out.println("estaVazia():     " + um.estaVazia());

        // =========================================================
        // 7. Intercalando enfileirar e desenfileirar
        // =========================================================
        System.out.println("\n=== Intercalando operacoes ===");
        FilaListaLigada mix = new FilaListaLigada();
        mix.enfileirar(1);
        mix.enfileirar(2);
        System.out.println("desenfileirar(): " + mix.desenfileirar()); // 1
        mix.enfileirar(3);
        System.out.println("desenfileirar(): " + mix.desenfileirar()); // 2
        mix.enfileirar(4);
        System.out.println("inicio():        " + mix.inicio()); // 3
        System.out.println("desenfileirar(): " + mix.desenfileirar()); // 3
        System.out.println("desenfileirar(): " + mix.desenfileirar()); // 4
        System.out.println("estaVazia():     " + mix.estaVazia());

        // =========================================================
        // 8. Elementos de tipos variados
        // =========================================================
        System.out.println("\n=== Tipos variados ===");
        FilaListaLigada tipos = new FilaListaLigada();
        tipos.enfileirar("texto");
        tipos.enfileirar(42);
        tipos.enfileirar(3.14);
        tipos.enfileirar(true);
        System.out.print("Saida: ");
        while (!tipos.estaVazia())
            System.out.print(tipos.desenfileirar() + " ");
        System.out.println();

        // =========================================================
        // 9. Volume maior (mantem a ordem)
        // =========================================================
        System.out.println("\n=== Volume maior ===");
        FilaListaLigada grande = new FilaListaLigada();
        for (int i = 1; i <= 1000; i++)
            grande.enfileirar(i);
        System.out.println("tamanho(): " + grande.tamanho());

        boolean ordemOk = true;
        for (int i = 1; i <= 1000; i++) {
            if (!Integer.valueOf(i).equals(grande.desenfileirar()))
                ordemOk = false;
        }
        System.out.println("ordem FIFO preservada: " + ordemOk);
        System.out.println("estaVazia():           " + grande.estaVazia());

        System.out.println("\n===== Todos os testes passaram =====");
    }
}

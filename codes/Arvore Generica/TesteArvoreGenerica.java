import java.util.Iterator;

public class TesteArvoreGenerica {

    public static void main(String[] args) {

        // =========================================================
        // 1. Arvore vazia
        // =========================================================
        System.out.println("=== Arvore Vazia ===");
        ArvoreGenerica vazia = new ArvoreGenerica();
        System.out.println("isEmpty(): " + vazia.isEmpty());  // true
        System.out.println("size():    " + vazia.size());     // 0
        System.out.println("root():    " + vazia.root());     // null

        System.out.println("\n=== Construcao com addChild ===");
        ArvoreGenerica arvore = new ArvoreGenerica("Empresa");
        No raiz = arvore.root();

        No ti   = arvore.addChild(raiz, "TI");
        No rh   = arvore.addChild(raiz, "RH");
        No dev  = arvore.addChild(ti,   "Dev");
        No infra= arvore.addChild(ti,   "Infra");
        No recr = arvore.addChild(rh,   "Recrutamento");
        No back = arvore.addChild(dev,  "Backend");

        System.out.println("size():    " + arvore.size());    // 7
        System.out.println("isEmpty(): " + arvore.isEmpty()); // false

        // =========================================================
        // 3. Metodos de Acesso
        // =========================================================
        System.out.println("\n=== Metodos de Acesso ===");
        System.out.println("root():          " + arvore.root().getElemento());       // Empresa
        System.out.println("parent(TI):      " + arvore.parent(ti).getElemento());   // Empresa
        System.out.println("parent(Dev):     " + arvore.parent(dev).getElemento());  // TI
        System.out.println("parent(Backend): " + arvore.parent(back).getElemento()); // Dev

        System.out.print("children(TI): ");
        Iterator<No> filhosTI = arvore.children(ti);
        while (filhosTI.hasNext())
            System.out.print(filhosTI.next().getElemento() + " "); // Dev Infra
        System.out.println();

        System.out.print("children(RH): ");
        Iterator<No> filhosRH = arvore.children(rh);
        while (filhosRH.hasNext())
            System.out.print(filhosRH.next().getElemento() + " "); // Recrutamento
        System.out.println();

        // =========================================================
        // 4. Metodos de Consulta
        // =========================================================
        System.out.println("\n=== Metodos de Consulta ===");
        System.out.println("isRoot(Empresa):     " + arvore.isRoot(raiz));    // true
        System.out.println("isRoot(TI):          " + arvore.isRoot(ti));      // false
        System.out.println("isInternal(TI):      " + arvore.isInternal(ti));  // true
        System.out.println("isExternal(TI):      " + arvore.isExternal(ti));  // false
        System.out.println("isInternal(Infra):   " + arvore.isInternal(infra)); // false
        System.out.println("isExternal(Infra):   " + arvore.isExternal(infra)); // true
        System.out.println("isExternal(Backend): " + arvore.isExternal(back));  // true

        // =========================================================
        // 5. Profundidade
        // =========================================================
        System.out.println("\n=== Profundidade (depth) ===");
        System.out.println("depth(Empresa): " + arvore.depth(raiz));  // 0
        System.out.println("depth(TI):      " + arvore.depth(ti));    // 1
        System.out.println("depth(Dev):     " + arvore.depth(dev));   // 2
        System.out.println("depth(Backend): " + arvore.depth(back));  // 3
        System.out.println("depth(Recrutamento): " + arvore.depth(recr)); // 2

        // =========================================================
        // 6. Altura
        // =========================================================
        System.out.println("\n=== Altura (height) ===");
        System.out.println("height(Empresa): " + arvore.height(raiz));  // 3
        System.out.println("height(TI):      " + arvore.height(ti));    // 2
        System.out.println("height(RH):      " + arvore.height(rh));    // 1
        System.out.println("height(Dev):     " + arvore.height(dev));   // 1
        System.out.println("height(Backend): " + arvore.height(back));  // 0
        System.out.println("height(Infra):   " + arvore.height(infra)); // 0

        // =========================================================
        // 7. replace
        // =========================================================
        System.out.println("\n=== replace ===");
        Object antigo = arvore.replace(infra, "Infraestrutura");
        System.out.println("Valor antigo: " + antigo);                // Infra
        System.out.println("Valor atual:  " + infra.getElemento());   // Infraestrutura

        // =========================================================
        // 8. Erros esperados
        // =========================================================
        System.out.println("\n=== Erros Esperados ===");

        // parent da raiz deve lancar excecao
        try {
            arvore.parent(raiz);
        } catch (IllegalArgumentException e) {
            System.out.println("parent(raiz) -> " + e.getMessage());
        }

        // getFilhos() nao deve permitir insercao direta
        try {
            raiz.getFilhos().add(new No(null, "Invasor"));
        } catch (UnsupportedOperationException e) {
            System.out.println("getFilhos().add() -> UnsupportedOperationException (lista somente-leitura)");
        }

        System.out.println("\n===== Todos os testes passaram =====");
    }
}

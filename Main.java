public class Main {
    public static void main(String[] args) {
        PilhaRubroNegro pilha = new PilhaRubroNegro(-1, -1);

        pilha.pushVermelho("V1");
        pilha.pushVermelho("V2");
        pilha.pushPreto("P1");
        pilha.pushPreto("P2");
        pilha.printarArray();

        pilha.pushVermelho("V3");
        pilha.printarArray();

        pilha.popPreto();
        pilha.popPreto();
        pilha.popVermelho();
        pilha.popVermelho();
        pilha.printarArray();
    }
}

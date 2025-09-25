public class Main {
    public static void main(String[] args) {
        PilhaRubroNegro pilha = new PilhaRubroNegro(4, 2);

        pilha.pushVermelho("V1");
        pilha.pushVermelho("V2");
        pilha.pushPreto("P1");
        pilha.pushPreto("P2");
        pilha.printar_array();

        pilha.pushVermelho("V3");
        pilha.printar_array();

        pilha.popPreto();
        pilha.popPreto();
        pilha.popVermelho();
        pilha.popVermelho();
        pilha.printar_array();
    }
}

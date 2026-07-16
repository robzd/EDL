public class TabelaHash {

    public static final String LINEAR = "linear";
    public static final String DUPLO = "duplo";

    private static final Entry AVAILABLE = new Item(null, null);
    private static final double ALFA_MAX = 0.5; // limite do fator de carga

    private Entry[] tabela;
    private int n = 0;
    private int N; // capacidade da tabela (primo)
    private int q; // primo menor que N
    private String tecnica; // "linear" ou "duplo"

    public TabelaHash(int capacidadeInicial, String tecnica) {
        this.tecnica = tecnica.toLowerCase();

        if (!this.tecnica.equals(LINEAR) && !this.tecnica.equals(DUPLO))
            throw new IllegalArgumentException("tecnica deve ser \"linear\" ou \"duplo\"");

        if (capacidadeInicial < 5)
            capacidadeInicial = 5;

        this.N = proximoPrimo(capacidadeInicial);
        this.q = primoAnterior(N);
        this.tabela = new Entry[N];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int capacidade() {
        return N;
    }

    // Fator de carga alfa = n / N
    public double fatorCarga() {
        return (double) n / N;
    }

    public void put(Object key, Object value) {
        if (key == null)
            throw new IllegalArgumentException("chave nula");

        // se o proximo insert estourar o limite de eficiencia, dobra antes
        if ((double) (n + 1) / N > ALFA_MAX)
            rehash();

        int k = hash(key);
        int idx = h1(k);
        int passo = passo(k);
        int disponivel = -1;

        for (int j = 0; j < N; j++) {
            Entry e = tabela[idx];

            if (e == null) {
                if (disponivel == -1)
                    disponivel = idx;
                tabela[disponivel] = new Item(key, value);
                n++;
                return;
            }

            if (e == AVAILABLE) {
                if (disponivel == -1)
                    disponivel = idx;
            } else if (e.key().equals(key)) {
                ((Item) e).setValue(value);
                return;
            }

            idx = (idx + passo) % N;
        }

        if (disponivel != -1) {
            tabela[disponivel] = new Item(key, value);
            n++;
            return;
        }

        throw new IllegalStateException("tabela cheia");
    }

    public Object get(Object key) {
        int i = buscarIndice(key);
        if (i == -1)
            return null;
        return tabela[i].value();
    }

    public Object remove(Object key) {
        int i = buscarIndice(key);
        if (i == -1)
            return null;

        Object antigo = tabela[i].value();
        tabela[i] = AVAILABLE;
        n--;
        return antigo;
    }

    public boolean containsKey(Object key) {
        return buscarIndice(key) != -1;
    }

    private int buscarIndice(Object key) {
        if (key == null)
            return -1;

        int k = hash(key);
        int idx = h1(k);
        int passo = passo(k);

        for (int j = 0; j < N; j++) {
            Entry e = tabela[idx];

            if (e == null)
                return -1;
            if (e != AVAILABLE && e.key().equals(key))
                return idx;

            idx = (idx + passo) % N;
        }
        return -1;
    }

    private void rehash() {
        Entry[] antigaTabela = tabela;

        N = proximoPrimo(N * 2);
        q = primoAnterior(N);
        tabela = new Entry[N];
        n = 0;

        for (Entry e : antigaTabela) {
            if (e != null && e != AVAILABLE)
                put(e.key(), e.value());
        }
    }

    // Metodos Auxiliares

    private int hash(Object key) {
        return key.hashCode() & 0x7fffffff; // zera o sinal
    }

    private int h1(int k) {
        return k % N;
    }

    private int h2(int k) {
        return q - (k % q);
    }

    private int passo(int k) {
        return tecnica.equals(LINEAR) ? 1 : h2(k);
    }

    private int proximoPrimo(int x) {
        while (!ehPrimo(x))
            x++;
        return x;
    }

    private int primoAnterior(int x) {
        x--;
        while (!ehPrimo(x))
            x--;
        return x;
    }

    private boolean ehPrimo(int x) {
        if (x < 2)
            return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0)
                return false;
        }
        return true;
    }
}

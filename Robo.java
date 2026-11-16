package Exercicio_avalitivo;

public abstract class Robo {
    private int codigo;
    private String nome;
    private int ataque;
    private int defesa;
    private int energia;
    private int vitorias;
    private int derrotas;
    private int pontos;
    private int combates;

    public Robo(int codigo, String nome, int ataque, int defesa) {
        // A Main já confere código, nome, ataque e defesa antes do cadastro.
        this.codigo = codigo;
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;
        this.energia = 100;
    }

    // Regras comuns de cadastro. A Main usa esses métodos para repetir a pergunta se necessário.
    public static boolean codigoValido(int codigo) { return codigo > 0; }
    public static boolean nomeValido(String nome) { return nome != null && !nome.trim().isEmpty(); }
    public static boolean ataqueValido(int ataque) { return ataque >= 10 && ataque <= 30; }
    public static boolean defesaValida(int defesa) { return defesa >= 0 && defesa <= 20; }

    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getEnergia() { return energia; }
    public int getVitorias() { return vitorias; }
    public int getDerrotas() { return derrotas; }
    public int getPontos() { return pontos; }
    public int getCombates() { return combates; }
    public int getEmpates() { return combates - vitorias - derrotas; }
    public String getTipo() { return "Robô"; }

    // Cada subclasse pode modificar o valor do golpe, sem alterar o ataque armazenado.
    public int ataqueNoGolpe() { return ataque; }

    // Cada subclasse pode modificar o dano recebido após os bônus da rodada.
    public int ajustarDanoRecebido(int dano) { return dano; }

    public boolean estaDisponivel() { return energia >= 30; }

    public void verificarEnergia() {
        if (estaDisponivel()) System.out.println("Robô disponível!");
        else System.out.println("Em recuperação!");
    }

    // Somente este método altera energia em consequência de um ataque.
    private void receberDano(int dano) {
        if (dano <= 0) {
            return;
        }
        energia = energia - dano;
        if (energia < 0) energia = 0;
    }

    public int atacar(Robo adversario, int rodada) {
        // Se o golpe for inválido, não altera a energia de ninguém.
        if (adversario == null || adversario == this || rodada < 1 || energia <= 0 || adversario.getEnergia() <= 0) {
            return 0;
        }
        int dano = ataqueNoGolpe() - adversario.getDefesa();
        if (dano < 5) dano = 5;
        if (rodada % 2 == 0) dano = dano + 5;
        dano = adversario.ajustarDanoRecebido(dano);
        adversario.receberDano(dano);
        return dano;
    }

    public void vencer() {
        vitorias++;
        pontos += 3;
        combates++;
    }
    public void perder() {
        derrotas++;
        combates++;
    }
    public void empatar() {
        pontos++;
        combates++;
    }
    public void receberFolga() { pontos++; }

    public void recuperarEnergia(int quantidade) {
        if (quantidade <= 0) System.out.println("A quantidade deve ser positiva!");
        else if (quantidade % 10 != 0) System.out.println("A quantidade deve ser múltipla de 10!");
        else if (energia + quantidade > 100) System.out.println("A energia não pode ultrapassar 100!");
        else if (pontos < quantidade / 10) System.out.println("Pontos insuficientes!");
        else {
            energia += quantidade;
            pontos -= quantidade / 10;
            System.out.println("Energia recuperada com sucesso!");
        }
    }
    public double calcularAproveitamento() {
        if (combates == 0) return 0;
        return vitorias * 100.0 / combates;
    }
}

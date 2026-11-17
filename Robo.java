package Exercicio_avalitivo;

public abstract class Robo {

    // ATRIBUTOS QUE EU JÁ TINHA NO MEU ROBÔ
    // agora estão private por causa do encapsulamento pedido na atividade
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
        this.codigo = codigo;
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;

        this.energia = 100;
        this.vitorias = 0;
        this.derrotas = 0;
        this.pontos = 0;
        this.combates = 0;
    }

    // VALIDAÇÕES QUE OS 3 TIPOS DE ROBÔ USAM NO CADASTRO
    public static boolean codigoValido(int codigo) {
        return codigo > 0;
    }

    public static boolean ataqueValido(int ataque) {
        return ataque >= 10 && ataque <= 30;
    }

    public static boolean defesaValida(int defesa) {
        return defesa >= 0 && defesa <= 20;
    }

    // como os atributos são privados, a Main usa os get para consultar
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getEnergia() {
        return energia;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getPontos() {
        return pontos;
    }

    public int getCombates() {
        return combates;
    }

    public int getEmpates() {
        return combates - vitorias - derrotas;
    }

    // AS 3 CLASSES FILHAS PODEM USAR OU REDEFINIR ESTES MÉTODOS
    public String getTipo() {
        return "Robô";
    }

    public int ataqueNoGolpe() {
        return ataque;
    }

    public int ajustarDanoRecebido(int dano) {
        return dano;
    }

    // MEUS MÉTODOS DO CAMPEONATO:

    // receber dano: energia nunca fica abaixo de 0
    // deixei private porque só um ataque pode chamar esta alteração
    private void receberDano(int dano) {
        this.energia = this.energia - dano;

        if (this.energia < 0) {
            this.energia = 0;
        }
    }

    // registrar vitórias
    public void vencer() {
        vitorias = vitorias + 1;
        pontos = pontos + 3;
        combates = combates + 1;
    }

    // registrar derrotas
    public void perder() {
        derrotas = derrotas + 1;
        combates = combates + 1;
    }

    // registrar empates
    public void empatar() {
        pontos = pontos + 1;
        combates = combates + 1;
    }

    public boolean estaDisponivel() {
        return energia >= 30;
    }

    public void verificarEnergia() {
        if (this.energia >= 30) {
            System.out.println("Robo Disponível!");
        } else {
            System.out.println("Em recuperação!");
        }
    }

    // Meu método para recuperar energia: cada 10 de energia custa 1 ponto
    public void recuperarEnergia(int quantidade) {
        if (quantidade <= 0) {
            System.out.println("A quantidade deve ser positiva!");
        } else if (quantidade % 10 != 0) {
            System.out.println("A quantidade deve ser múltipla de 10!");
        } else if (energia + quantidade > 100) {
            System.out.println("A energia não pode ultrapassar 100!");
        } else if (pontos < quantidade / 10) {
            System.out.println("Pontos insuficientes!");
        } else {
            energia = energia + quantidade;
            pontos = pontos - (quantidade / 10);
            System.out.println("Energia recuperada com sucesso!");
        }
    }

    // MÉTODO DE ATAQUE QUE EU JÁ UTILIZAVA
    public int atacar(Robo adversario, int rodada) {
        // agora o cálculo consulta o método de cada tipo de robô
        int dano = this.ataqueNoGolpe() - adversario.getDefesa();

        if (dano < 5) {
            dano = 5; // dano mínimo original
        }

        if (rodada % 2 == 0) {
            dano = dano + 5; // bônus das rodadas pares
        }

        // A redução defensiva vem por último, depois dos bônus
        dano = adversario.ajustarDanoRecebido(dano);

        adversario.receberDano(dano);
        return dano;
    }

    // folga da rodada geral: ganha 1 ponto mas NÃO conta como combate
    public void receberFolga() {
        pontos = pontos + 1;
    }

    // aproveitamento = vitórias / combates * 100
    public double calcularAproveitamento() {
        if (combates == 0) {
            return 0;
        }
        return vitorias * 100.0 / combates;
    }
}

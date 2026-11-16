package Exercicio_avalitivo;

public class RoboEquilibrado extends Robo {

    public RoboEquilibrado(int codigo, String nome, int ataque, int defesa, int energia,
                          int vitorias, int derrotas, int pontos, int combates) {
        super(codigo, nome, ataque, defesa, energia, vitorias, derrotas, pontos, combates);
    }

    @Override
    public String getTipo() {
        return "Equilibrado";
    }

    // O equilibrado usa o ataque e a defesa normais herdados da classe Robo
}

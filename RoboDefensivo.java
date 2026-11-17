package Exercicio_avalitivo;

public class RoboDefensivo extends Robo {

    public RoboDefensivo(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() {
        return "Defensivo";
    }

    // Recebe 3 a menos de dano, mas o mínimo final é 2
    @Override
    public int ajustarDanoRecebido(int dano) {
        dano = dano - 3;
        if (dano < 2) {
            dano = 2;
        }
        return dano;
    }
}

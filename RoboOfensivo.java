package Exercicio_avalitivo;

public class RoboOfensivo extends Robo {
    public RoboOfensivo(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() { return "Ofensivo"; }

    @Override
    public int ataqueNoGolpe() { return getAtaque() + 5; }
}

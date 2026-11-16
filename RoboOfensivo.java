package Exercicio_avalitivo;

public class RoboOfensivo extends Robo {

    public RoboOfensivo(int codigo, String nome, int ataque, int defesa) {

        super(codigo, nome, ataque, defesa);
    }

    @Override
    public String getTipo() {
        return "Ofensivo";
    }

    // Soma 5 somente neste golpe; o atributo ataque original continua igual
    @Override
    public int ataqueNoGolpe() {
        return getAtaque() + 5;
    }
}

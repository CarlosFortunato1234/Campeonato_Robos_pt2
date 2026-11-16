package Exercicio_avalitivo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Robo> robos = new ArrayList<>(); // Uma única lista para os três tipos de robôs.
        int opt;

        do {
            System.out.println("=========== CAMPEONATO DE ROBOS ===========");
            System.out.println("BEEEM VINDO AO CAMPEONATO DE ROBOS MAIS BRUTAL DA HISTÓRIA!");
            System.out.println("0. Sair");
            System.out.println("1. Cadastrar ou Consultar Robos:");
            System.out.println("2. Realizar um combate:");
            System.out.println("3. Recuperar energia:");
            System.out.println("4. Executar uma rodada geral:");
            System.out.println("5. Exibir classificação:");
            System.out.println("6. Emitir estatísticas:");
            System.out.println("7. Excluir participante:");
            System.out.println("Digite a operação:");
            opt = leitorMenu(s);

            switch (opt) {
                case 0:
                    System.out.println("Tchau!");
                    break;
                case 1:
                    System.out.println("== GERENCIAMENTO DE ROBOS ==");
                    System.out.println("1- Cadastrar Robo:");
                    System.out.println("2- Consultar Robo:");
                    System.out.println("3- Listar todos os robos:");
                    int opcaoRobo = leitorGeral(s);
                    if (opcaoRobo == 1) {
                        System.out.println("Digite o código do robô:");
                        int codigo = leitorGeral(s);
                        while (!Robo.codigoValido(codigo) || buscarPorCodigo(robos, codigo) != null) {
                            System.out.println("Código inválido ou já cadastrado! Digite outro código positivo:");
                            codigo = leitorGeral(s);
                        }
                        System.out.println("Digite o nome do robô:");
                        s.nextLine(); // Consome o Enter deixado por nextInt.
                        String nome = s.nextLine();
                        while (!Robo.nomeValido(nome)) {
                            System.out.println("Nome obrigatório. Digite o nome do robô:");
                            nome = s.nextLine();
                        }
                        System.out.println("Digite o ataque do robô entre 10 e 30:");
                        int ataque = leitorGeral(s);
                        while (!Robo.ataqueValido(ataque)) {
                            System.out.println("Ataque inválido! Digite um ataque entre 10 e 30:");
                            ataque = leitorGeral(s);
                        }
                        System.out.println("Digite a defesa do robô entre 0 e 20:");
                        int defesa = leitorGeral(s);
                        while (!Robo.defesaValida(defesa)) {
                            System.out.println("Defesa inválida! Digite uma defesa entre 0 e 20:");
                            defesa = leitorGeral(s);
                        }
                        // Pergunta dentro do cadastro: não é uma opção nova do menu principal.
                        System.out.println("Escolha o tipo: 1- Ofensivo | 2- Defensivo | 3- Equilibrado");
                        int tipo = leitorGeral(s);
                        while (tipo < 1 || tipo > 3) {
                            System.out.println("Tipo inválido! Digite 1, 2 ou 3:");
                            tipo = leitorGeral(s);
                        }
                        Robo robo;
                        if (tipo == 1) robo = new RoboOfensivo(codigo, nome, ataque, defesa);
                        else if (tipo == 2) robo = new RoboDefensivo(codigo, nome, ataque, defesa);
                        else robo = new RoboEquilibrado(codigo, nome, ataque, defesa);
                        robos.add(robo);
                        System.out.println("Robô " + robo.getTipo() + " cadastrado com sucesso!");
                    } else if (opcaoRobo == 2) {
                        System.out.println("Digite o código do robô que deseja consultar:");
                        Robo robo = buscarPorCodigo(robos, leitorGeral(s));
                        if (robo == null) System.out.println("Robô não encontrado!");
                        else {
                            System.out.println("====== ROBÔ ENCONTRADO ======");
                            exibirRobo(robo);
                        }
                    } else if (opcaoRobo == 3) {
                        if (robos.isEmpty()) System.out.println("Nenhum robô cadastrado!");
                        else {
                            System.out.println("====== LISTA DE ROBÔS ======");
                            for (Robo robo : robos) {
                                exibirRobo(robo);
                                System.out.println("----------------------------");
                            }
                        }
                    } else System.out.println("Opção inválida!");
                    break;
                case 2:
                    System.out.println("Digite o código do primeiro robô:");
                    int codigo1 = leitorGeral(s);
                    System.out.println("Digite o código do segundo robô:");
                    int codigo2 = leitorGeral(s);
                    Robo robo1 = buscarPorCodigo(robos, codigo1);
                    Robo robo2 = buscarPorCodigo(robos, codigo2);
                    if (codigo1 == codigo2) System.out.println("Os robôs devem ser diferentes!");
                    else if (robo1 == null || robo2 == null) System.out.println("Um ou mais robôs não existem!");
                    else if (!robo1.estaDisponivel()) System.out.println("O primeiro robô está em recuperação!");
                    else if (!robo2.estaDisponivel()) System.out.println("O segundo robô está em recuperação!");
                    else executarCombate(robo1, robo2);
                    break;
                case 3:
                    System.out.println("====== RECUPERAÇÃO DE ENERGIA ======");
                    System.out.println("Cada bloco de 10 de energia custa 1 ponto.");
                    System.out.println("Digite o código do robô:");
                    Robo roboRecuperar = buscarPorCodigo(robos, leitorGeral(s));
                    if (roboRecuperar == null) System.out.println("Robô não encontrado!");
                    else {
                        System.out.println("Digite a quantidade de energia:");
                        roboRecuperar.recuperarEnergia(leitorGeral(s));
                        System.out.println("Situação atual de " + roboRecuperar.getNome() + ": energia = "
                                + roboRecuperar.getEnergia() + " | pontos = " + roboRecuperar.getPontos());
                    }
                    break;
                case 4:
                    ArrayList<Robo> classificacaoRodada = montarClassificacao(robos);
                    ArrayList<Robo> disponiveisRodada = new ArrayList<>();
                    for (Robo candidato : classificacaoRodada) {
                        if (candidato.estaDisponivel()) disponiveisRodada.add(candidato);
                    }
                    if (disponiveisRodada.size() < 2) {
                        System.out.println("Rodada geral recusada! É preciso ter pelo menos 2 robôs disponíveis.");
                    } else {
                        ArrayList<Robo> confrontoA = new ArrayList<>();
                        ArrayList<Robo> confrontoB = new ArrayList<>();
                        int i = 0;
                        while (i + 1 < disponiveisRodada.size()) {
                            confrontoA.add(disponiveisRodada.get(i));
                            confrontoB.add(disponiveisRodada.get(i + 1));
                            i += 2;
                        }
                        Robo roboFolga = null;
                        if (i < disponiveisRodada.size()) roboFolga = disponiveisRodada.get(i);
                        System.out.println("====== CONFRONTOS DA RODADA GERAL ======");
                        for (int k = 0; k < confrontoA.size(); k++) {
                            System.out.println((k + 1) + ") " + confrontoA.get(k).getNome() + " x " + confrontoB.get(k).getNome());
                        }
                        if (roboFolga != null) System.out.println("Folga: " + roboFolga.getNome());
                        for (int k = 0; k < confrontoA.size(); k++) {
                            System.out.println("========== CONFRONTO " + (k + 1) + " ==========");
                            executarCombate(confrontoA.get(k), confrontoB.get(k));
                        }
                        if (roboFolga != null) {
                            roboFolga.receberFolga();
                            System.out.println(roboFolga.getNome() + " ficou sem adversário e recebeu 1 ponto de folga.");
                        }
                    }
                    break;
                case 5:
                    if (robos.isEmpty()) System.out.println("Nenhum robô cadastrado!");
                    else {
                        ArrayList<Robo> classificacao = montarClassificacao(robos);
                        System.out.println("====== CLASSIFICAÇÃO ======");
                        for (int i = 0; i < classificacao.size(); i++) {
                            Robo r = classificacao.get(i);
                            System.out.println((i + 1) + "º | Código: " + r.getCodigo() + " | Nome: " + r.getNome()
                                    + " | Tipo: " + r.getTipo() + " | Pontos: " + r.getPontos()
                                    + " | Combates: " + r.getCombates() + " | Vitórias: " + r.getVitorias()
                                    + " | Empates: " + r.getEmpates() + " | Derrotas: " + r.getDerrotas()
                                    + " | Energia: " + r.getEnergia());
                        }
                    }
                    break;
                case 6:
                    emitirEstatisticas(robos);
                    break;
                case 7:
                    if (robos.isEmpty()) System.out.println("Nenhum robô cadastrado!");
                    else {
                        System.out.println("Digite o código do robô que deseja excluir:");
                        Robo roboExcluir = buscarPorCodigo(robos, leitorGeral(s));
                        if (roboExcluir == null) System.out.println("Robô não encontrado!");
                        else if (roboExcluir.getCombates() > 0) {
                            System.out.println("Exclusão recusada! Esse robô já realizou combates.");
                        } else {
                            robos.remove(roboExcluir);
                            System.out.println("Robô " + roboExcluir.getNome() + " excluído com sucesso!");
                        }
                    }
                    break;
            }
        } while (opt != 0);
        s.close();
    }

    public static Robo buscarPorCodigo(ArrayList<Robo> robos, int codigo) {
        for (Robo robo : robos) {
            if (robo.getCodigo() == codigo) return robo;
        }
        return null;
    }

    public static void exibirRobo(Robo robo) {
        System.out.println("Código: " + robo.getCodigo() + " | Nome: " + robo.getNome() + " | Tipo: " + robo.getTipo());
        System.out.println("Ataque: " + robo.getAtaque() + " | Defesa: " + robo.getDefesa() + " | Energia: " + robo.getEnergia());
        System.out.println("Vitórias: " + robo.getVitorias() + " | Empates: " + robo.getEmpates()
                + " | Derrotas: " + robo.getDerrotas() + " | Pontos: " + robo.getPontos()
                + " | Combates: " + robo.getCombates());
        robo.verificarEnergia();
    }

    public static int leitorMenu(Scanner s) {
        int opt = -1;
        do {
            opt = leitorGeral(s);
            if (opt < 0 || opt > 7) System.out.println("Operação inválida! Digite um número entre 0 e 7.");
        } while (opt < 0 || opt > 7);
        return opt;
    }

    public static int leitorGeral(Scanner s) {
        while (true) {
            try {
                return s.nextInt();
            } catch (InputMismatchException e) {
                s.next();
                System.out.println("Entrada inválida! Digite um número inteiro:");
            }
        }
    }

    public static void executarCombate(Robo robo1, Robo robo2) {
        System.out.println("COMBATE AUTORIZADO!");
        Robo primeiro;
        Robo segundo;
        if (robo1.getPontos() < robo2.getPontos()
                || (robo1.getPontos() == robo2.getPontos() && robo1.getCodigo() < robo2.getCodigo())) {
            primeiro = robo1;
            segundo = robo2;
        } else {
            primeiro = robo2;
            segundo = robo1;
        }
        System.out.println(primeiro.getNome() + " ataca primeiro em todas as rodadas!");
        System.out.println("Energia inicial: " + primeiro.getNome() + " = " + primeiro.getEnergia()
                + " | " + segundo.getNome() + " = " + segundo.getEnergia());
        int rodada = 1;
        while (rodada <= 5 && primeiro.getEnergia() > 0 && segundo.getEnergia() > 0) {
            System.out.println("===== RODADA " + rodada + " =====");
            int dano = primeiro.atacar(segundo, rodada);
            System.out.println(primeiro.getNome() + " atacou " + segundo.getNome() + " e causou " + dano + " de dano.");
            System.out.println("Energia restante de " + segundo.getNome() + ": " + segundo.getEnergia());
            if (segundo.getEnergia() > 0) {
                dano = segundo.atacar(primeiro, rodada);
                System.out.println(segundo.getNome() + " atacou " + primeiro.getNome() + " e causou " + dano + " de dano.");
                System.out.println("Energia restante de " + primeiro.getNome() + ": " + primeiro.getEnergia());
                if (primeiro.getEnergia() == 0) {
                    System.out.println(primeiro.getNome() + " ficou sem energia! Combate encerrado.");
                }
            } else System.out.println(segundo.getNome() + " ficou sem energia! Combate encerrado, sem contra-ataque.");
            rodada++;
        }
        System.out.println("===== RESULTADO FINAL =====");
        System.out.println("Energia final: " + primeiro.getNome() + " = " + primeiro.getEnergia()
                + " | " + segundo.getNome() + " = " + segundo.getEnergia());
        if (primeiro.getEnergia() > segundo.getEnergia()) {
            System.out.println(primeiro.getNome() + " VENCEU!");
            primeiro.vencer();
            segundo.perder();
        } else if (segundo.getEnergia() > primeiro.getEnergia()) {
            System.out.println(segundo.getNome() + " VENCEU!");
            segundo.vencer();
            primeiro.perder();
        } else {
            System.out.println("EMPATE!");
            primeiro.empatar();
            segundo.empatar();
        }
    }

    public static boolean veioAntes(Robo a, Robo b) {
        if (a.getPontos() != b.getPontos()) return a.getPontos() > b.getPontos();
        if (a.getVitorias() != b.getVitorias()) return a.getVitorias() > b.getVitorias();
        if (a.getEnergia() != b.getEnergia()) return a.getEnergia() > b.getEnergia();
        return a.getCodigo() < b.getCodigo();
    }

    public static ArrayList<Robo> montarClassificacao(ArrayList<Robo> robos) {
        ArrayList<Robo> classificacao = new ArrayList<>();
        for (Robo robo : robos) classificacao.add(robo);
        for (int i = 0; i < classificacao.size() - 1; i++) {
            int melhor = i;
            for (int j = i + 1; j < classificacao.size(); j++) {
                if (veioAntes(classificacao.get(j), classificacao.get(melhor))) melhor = j;
            }
            Robo temp = classificacao.get(i);
            classificacao.set(i, classificacao.get(melhor));
            classificacao.set(melhor, temp);
        }
        return classificacao;
    }

    public static void emitirEstatisticas(ArrayList<Robo> robos) {
        System.out.println("====== ESTATÍSTICAS ======");
        System.out.println("Total de robôs cadastrados: " + robos.size());
        if (robos.isEmpty()) {
            System.out.println("Não há robôs para calcular estatísticas.");
            return;
        }
        int somaEnergia = 0;
        Robo melhor = null;
        System.out.println("Robôs em recuperação:");
        boolean encontrou = false;
        for (Robo robo : robos) {
            somaEnergia += robo.getEnergia();
            if (!robo.estaDisponivel()) {
                System.out.println("- " + robo.getNome() + " (" + robo.getTipo() + "), energia: " + robo.getEnergia());
                encontrou = true;
            }
            if (robo.getCombates() > 0
                    && (melhor == null || robo.calcularAproveitamento() > melhor.calcularAproveitamento())) {
                melhor = robo;
            }
        }
        if (!encontrou) System.out.println("Nenhum.");
        System.out.printf("Média de energia: %.2f%n", somaEnergia * 1.0 / robos.size());
        if (melhor == null) System.out.println("Melhor aproveitamento: nenhum robô realizou combates.");
        else {
            System.out.printf("Melhor aproveitamento: %s (%s), %.2f%%%n",
                    melhor.getNome(), melhor.getTipo(), melhor.calcularAproveitamento());
            System.out.println("Combates: " + melhor.getCombates() + " | Vitórias: " + melhor.getVitorias()
                    + " | Empates: " + melhor.getEmpates() + " | Derrotas: " + melhor.getDerrotas());
        }
    }
}

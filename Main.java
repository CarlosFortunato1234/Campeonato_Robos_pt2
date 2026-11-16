package Exercicio_avalitivo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Robo> robos = new ArrayList<>(); // arraylist dos meus robos no programa
        int opt = -1;

        // ESSE É O MEU MENU PRINCIPAL, Não precisei mexer nesse menu.
        do {
            System.out.println("=========== CAMPEONATO DE ROBOS  ===========");
            System.out.println("BEEEM VINDO AO CAMPEONATO DE ROBOS MAIS BRUTAL DA HISTÓRIA!");
            System.out.println("DIGITE UMA OPÇÃO: ");
            System.out.println("0. Sair ");
            System.out.println("1. Cadastrar ou Consultar Robos: ");
            System.out.println("2. Realizar um combate: ");
            System.out.println("3. Recuperar energia: ");
            System.out.println("4. Executar uma rodada geral: ");
            System.out.println("5. Exibir classificação: ");
            System.out.println("6. Emitir estatísticas: ");
            System.out.println("7. Excluir participante: ");
            System.out.println("Digite a operação: ");
            opt = leitorMenu(s);

            switch (opt) {
                case 0:
                    System.out.println("Tchau!");
                    break;

                case 1:
                    // INICIO DO CASE 1
                    System.out.println("== GERENCIAMENTO DE ROBOS: ");
                    System.out.println("1- Cadastrar Robo: ");
                    System.out.println("2- Consultar Robo: ");
                    System.out.println("3- Listar todos os robos: ");
                    System.out.println("Digite uma opção: ");
                    int opcaoRobo = leitorGeral(s);

                    if (opcaoRobo == 1) {
                        System.out.println("Digite o código do robo: ");
                        int codigo = leitorGeral(s);

                        // VALIDAÇÕES DO CÓDIGO: NÃO PODE SER NEGATIVO NEM REPETIDO
                        boolean codigoExiste = true; // aq ja inicio codigoExite como true, pro meu while executar pelo
                                                     // menos uma vez
                        while (codigoExiste == true) // enquanto codigoexite for true, vai dale nesse while
                        {
                            codigoExiste = false;
                            // esse codigoValido é um método do robo.java
                            if (!Robo.codigoValido(codigo)) {
                                codigoExiste = true;
                            }

                            for (Robo competidor : robos) {
                                if (competidor.getCodigo() == codigo) {
                                    codigoExiste = true;
                                    break;
                                }
                            }

                            if (codigoExiste == true) {
                                System.out.println("Código inválido ou já cadastrado! Digite outro código positivo: ");
                                codigo = leitorGeral(s);
                            }
                        }

                        System.out.println("Digite o nome do robo: ");
                        s.nextLine(); // limpa o enter de novo
                        String nome = s.nextLine();

                        System.out.println("Digite o ataque do robo entre 10 e 30: ");
                        int ataque = leitorGeral(s);
                        while (!Robo.ataqueValido(ataque)) {
                            System.out.println("Ataque inválido! Digite um ataque entre 10 e 30! ");
                            ataque = leitorGeral(s);
                        }

                        System.out.println("Digite a defesa do robo entre 0 e 20: ");
                        int defesa = leitorGeral(s);
                        while (!Robo.defesaValida(defesa)) {
                            System.out.println("Defesa inválida! Digite uma defesa entre 0 e 20: ");
                            defesa = leitorGeral(s);
                        }

                        // ÚNICA PERGUNTA NOVA DO CADASTRO: ESCOLHER O TIPO
                        System.out.println("Tipo do robô: 1- Ofensivo | 2- Defensivo | 3- Equilibrado");
                        int tipo = leitorGeral(s);
                        while (tipo < 1 || tipo > 3) {
                            System.out.println("Tipo inválido! Digite 1, 2 ou 3: ");
                            tipo = leitorGeral(s);
                        }

                        // A lista continua sendo ArrayList<Robo>. O que muda é o objeto criado.
                        Robo robo;

                        if (tipo == 1) {
                            robo = new RoboOfensivo(codigo, nome, ataque, defesa);
                        } else if (tipo == 2) {
                            robo = new RoboDefensivo(codigo, nome, ataque, defesa);
                        } else {
                            robo = new RoboEquilibrado(codigo, nome, ataque, defesa);
                        }
                        robos.add(robo); // guardo o robô recém criado na lista
                        System.out.println("Robô " + robo.getTipo() + " cadastrado com sucesso!");

                    } else if (opcaoRobo == 2) {
                        System.out.println("Digite o código do robo que deseja consultar: ");
                        int codigoBusca = leitorGeral(s);
                        boolean encontrado = false;

                        // mantive o for que eu já usava para consultar por código
                        for (Robo lutador : robos) {
                            if (lutador.getCodigo() == codigoBusca) {
                                System.out.println("====== ROBÔ ENCONTRADO ======");
                                System.out.println("Código: " + lutador.getCodigo());
                                System.out.println("Nome: " + lutador.getNome());
                                System.out.println("Tipo: " + lutador.getTipo());
                                System.out.println("Ataque: " + lutador.getAtaque());
                                System.out.println("Defesa: " + lutador.getDefesa());
                                System.out.println("Energia: " + lutador.getEnergia());
                                System.out.println("Vitórias: " + lutador.getVitorias());
                                System.out.println("Empates: " + lutador.getEmpates());
                                System.out.println("Derrotas: " + lutador.getDerrotas());
                                System.out.println("Pontos: " + lutador.getPontos());
                                System.out.println("Combates: " + lutador.getCombates());
                                lutador.verificarEnergia();
                                encontrado = true;
                                break;
                            }
                        }
                        if (encontrado == false) {
                            System.out.println("Robô não encontrado!");
                        }

                    } else if (opcaoRobo == 3) {
                        if (robos.isEmpty()) {
                            System.out.println("Nenhum robô cadastrado!");
                        } else {
                            System.out.println("====== LISTA DE ROBÔS ======");
                            for (Robo participante : robos) {
                                System.out.println("Código: " + participante.getCodigo() + " || Nome: "
                                        + participante.getNome() + " || Tipo: " + participante.getTipo());
                                System.out.println("Ataque: " + participante.getAtaque() + " || Defesa: "
                                        + participante.getDefesa() + " || Energia: " + participante.getEnergia());
                                System.out.println("Vitórias: " + participante.getVitorias() + " || Empates: "
                                        + participante.getEmpates()
                                        + " || Derrotas: " + participante.getDerrotas() + " || Pontos: "
                                        + participante.getPontos()
                                        + " || Combates: " + participante.getCombates());
                                participante.verificarEnergia();
                                System.out.println("----------------------------");
                            }
                        }
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                // FIM DO CASE 1

                case 2:
                    // INICIO DO CASE 2: COMBATE ESCOLHIDO PELO USUÁRIO
                    System.out.println("Digite o código do primeiro robo: ");
                    int codigo1 = leitorGeral(s);
                    System.out.println("Digite o código do segundo robo: ");
                    int codigo2 = leitorGeral(s);

                    Robo robo1 = null;
                    Robo robo2 = null;
                    for (Robo competidor : robos) {
                        if (competidor.getCodigo() == codigo1) {
                            robo1 = competidor;
                        }
                        if (competidor.getCodigo() == codigo2) {
                            robo2 = competidor;
                        }
                    }

                    if (codigo1 == codigo2) {
                        System.out.println("Os robôs devem ser diferentes! ");
                    } else if (robo1 == null || robo2 == null) {
                        System.out.println("Um ou mais robôs não existem! ");
                    } else if (robo1.getEnergia() < 30) {
                        System.out.println("O primeiro robô está em recuperação! ");
                    } else if (robo2.getEnergia() < 30) {
                        System.out.println("O segundo robô está em recuperação! ");
                    } else {
                        System.out.println("COMBATE AUTORIZADO! ");
                        Robo primeiro;
                        Robo segundo;

                        // A ordem continua igual: menos pontos, desempate por menor código
                        if (robo1.getPontos() < robo2.getPontos()) {
                            primeiro = robo1;
                            segundo = robo2;
                        } else if (robo2.getPontos() < robo1.getPontos()) {
                            primeiro = robo2;
                            segundo = robo1;
                        } else if (robo1.getCodigo() < robo2.getCodigo()) {
                            primeiro = robo1;
                            segundo = robo2;
                        } else {
                            primeiro = robo2;
                            segundo = robo1;
                        }

                        System.out.println(primeiro.getNome() + " ataca primeiro em todas as rodadas! ");
                        System.out.println("Energia inicial: " + primeiro.getNome() + " = " + primeiro.getEnergia()
                                + " | " + segundo.getNome() + " = " + segundo.getEnergia());

                        int rodada = 1;
                        while (rodada <= 5 && primeiro.getEnergia() > 0 && segundo.getEnergia() > 0) {
                            System.out.println("===== RODADA " + rodada + " =====");
                            int dano = primeiro.atacar(segundo, rodada);
                            System.out.println(primeiro.getNome() + " atacou " + segundo.getNome() + " e causou " + dano
                                    + " de dano.");
                            System.out
                                    .println("Energia restante de " + segundo.getNome() + ": " + segundo.getEnergia());

                            if (segundo.getEnergia() > 0) {
                                dano = segundo.atacar(primeiro, rodada);
                                System.out.println(segundo.getNome() + " atacou " + primeiro.getNome() + " e causou "
                                        + dano + " de dano.");
                                System.out.println(
                                        "Energia restante de " + primeiro.getNome() + ": " + primeiro.getEnergia());
                                if (primeiro.getEnergia() == 0) {
                                    System.out.println(primeiro.getNome() + " ficou sem energia! Combate encerrado.");
                                }
                            } else {
                                System.out.println(segundo.getNome()
                                        + " ficou sem energia! Combate encerrado, sem contra-ataque.");
                            }
                            rodada = rodada + 1;
                        }

                        // RESULTADO FINAL: MANTIVE MEUS IFS DE VITÓRIA, DERROTA OU EMPATE
                        System.out.println("===== RESULTADO FINAL =====");
                        System.out.println("Energia final: " + primeiro.getNome() + " = " + primeiro.getEnergia()
                                + " | " + segundo.getNome() + " = " + segundo.getEnergia());

                        if (primeiro.getEnergia() == 0) {
                            System.out.println(segundo.getNome() + " VENCEU!");
                            segundo.vencer();
                            primeiro.perder();
                        } else if (segundo.getEnergia() == 0) {
                            System.out.println(primeiro.getNome() + " VENCEU!");
                            primeiro.vencer();
                            segundo.perder();
                        } else if (primeiro.getEnergia() > segundo.getEnergia()) {
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
                    break;
                // FIM DO CASE 2

                case 3:
                    // INICIO DO CASE 3: RECUPERAÇÃO DE ENERGIA
                    System.out.println("====== RECUPERAÇÃO DE ENERGIA ====== ");
                    System.out.println("ATENÇÃO! O número deverá ser positivo e múltiplo de 10! ");
                    System.out.println("Cada bloco de 10 de energia custará um ponto no campeonato.");
                    System.out.println("Digite o código do robo:");
                    int codigoRobo = leitorGeral(s);
                    Robo roboRecuperar = null;

                    for (Robo alvo : robos) {
                        if (alvo.getCodigo() == codigoRobo) {
                            roboRecuperar = alvo;
                        }
                    }
                    if (roboRecuperar == null) {
                        System.out.println("Robô não encontrado!");
                    } else {
                        System.out.println("Digite a quantidade de energia: ");
                        int qtdeEnergia = leitorGeral(s);
                        roboRecuperar.recuperarEnergia(qtdeEnergia);
                        System.out.println("Situação atual de " + roboRecuperar.getNome() + ": energia = "
                                + roboRecuperar.getEnergia() + " | pontos = " + roboRecuperar.getPontos());
                    }
                    break;
                // FIM DO CASE 3

                case 4:
                    // RODADA GERAL: NÃO MUDEI O MEU JEITO DE FORMAR OS CONFRONTOS
                    ArrayList<Robo> classificacaoRodada = montarClassificacao(robos);
                    ArrayList<Robo> disponiveisRodada = new ArrayList<>();
                    for (Robo candidato : classificacaoRodada) {
                        if (candidato.getEnergia() >= 30) {
                            disponiveisRodada.add(candidato);
                        }
                    }

                    if (disponiveisRodada.size() < 2) {
                        System.out.println("Rodada geral recusada! É preciso ter pelo menos 2 robôs disponíveis.");
                    } else {
                        ArrayList<Robo> confrontoA = new ArrayList<>();
                        ArrayList<Robo> confrontoB = new ArrayList<>();
                        Robo roboFolga = null;
                        int i = 0;

                        while (i + 1 < disponiveisRodada.size()) {
                            confrontoA.add(disponiveisRodada.get(i));
                            confrontoB.add(disponiveisRodada.get(i + 1));
                            i = i + 2;
                        }
                        if (i < disponiveisRodada.size()) {
                            roboFolga = disponiveisRodada.get(i);
                        }

                        System.out.println("====== CONFRONTOS DA RODADA GERAL ======");
                        for (int k = 0; k < confrontoA.size(); k++) {
                            System.out.println(
                                    (k + 1) + ") " + confrontoA.get(k).getNome() + " x " + confrontoB.get(k).getNome());
                        }
                        if (roboFolga != null) {
                            System.out.println("Folga: " + roboFolga.getNome());
                        }

                        for (int k = 0; k < confrontoA.size(); k++) {
                            System.out.println("========== CONFRONTO " + (k + 1) + " ==========");
                            executarCombate(confrontoA.get(k), confrontoB.get(k));
                        }

                        if (roboFolga != null) {
                            roboFolga.receberFolga();
                            System.out
                                    .println(roboFolga.getNome() + " ficou sem adversário e recebeu 1 ponto de folga.");
                        }
                    }
                    break;
                // FIM DO CASE 4

                case 5:
                    // CLASSIFICAÇÃO: UTILIZO MINHA LISTA AUXILIAR
                    if (robos.isEmpty()) {
                        System.out.println("Nenhum robô cadastrado!");
                    } else {
                        ArrayList<Robo> classificacao = montarClassificacao(robos);
                        System.out.println("====== CLASSIFICAÇÃO ======");
                        for (int i = 0; i < classificacao.size(); i++) {
                            Robo posicionado = classificacao.get(i);
                            System.out.println((i + 1) + "º | Código: " + posicionado.getCodigo()
                                    + " | Nome: " + posicionado.getNome() + " | Tipo: " + posicionado.getTipo()
                                    + " | Pontos: " + posicionado.getPontos() + " | Combates: "
                                    + posicionado.getCombates()
                                    + " | Vitórias: " + posicionado.getVitorias() + " | Empates: "
                                    + posicionado.getEmpates()
                                    + " | Derrotas: " + posicionado.getDerrotas() + " | Energia: "
                                    + posicionado.getEnergia());
                        }
                    }
                    break;

                case 6:
                    // ANTES EU NÃO TINHA TERMINADO ESTE CASE. AGORA TENHO A ESTATÍSTICA PEDIDA.
                    System.out.println("====== ESTATÍSTICAS ======");
                    System.out.println("Total de robôs cadastrados: " + robos.size());
                    if (robos.isEmpty()) {
                        System.out.println("Nenhum robô cadastrado!");
                    } else {
                        int somaEnergia = 0;
                        Robo melhor = null;
                        boolean emRecuperacao = false;
                        System.out.println("Robôs em recuperação:");

                        for (Robo competidor : robos) {
                            somaEnergia = somaEnergia + competidor.getEnergia();
                            if (competidor.getEnergia() < 30) {
                                emRecuperacao = true;
                                System.out.println(competidor.getNome() + " (" + competidor.getTipo() + ") - energia: "
                                        + competidor.getEnergia());
                            }
                            if (competidor.getCombates() > 0) {
                                if (melhor == null) {
                                    melhor = competidor;
                                } else if (competidor.calcularAproveitamento() > melhor.calcularAproveitamento()) {
                                    melhor = competidor;
                                }
                            }
                        }
                        if (emRecuperacao == false) {
                            System.out.println("Nenhum.");
                        }

                        double mediaEnergia = somaEnergia * 1.0 / robos.size();
                        System.out.println("Média de energia: " + mediaEnergia);
                        if (melhor == null) {
                            System.out.println("Melhor aproveitamento: nenhum robô realizou combates.");
                        } else {
                            System.out.println(
                                    "Melhor aproveitamento: " + melhor.getNome() + " (" + melhor.getTipo() + ") = "
                                            + melhor.calcularAproveitamento() + "%");
                            System.out.println("Combates: " + melhor.getCombates() + " | Vitórias: "
                                    + melhor.getVitorias()
                                    + " | Empates: " + melhor.getEmpates() + " | Derrotas: " + melhor.getDerrotas());
                        }
                    }
                    break;

                case 7:
                    // EXCLUSÃO: SO PODE EXCLUIR QUEM NUNCA LUTOU
                    if (robos.isEmpty()) {
                        System.out.println("Nenhum robô cadastrado!");
                    } else {
                        System.out.println("Digite o código do robô que deseja excluir: ");
                        int codigoExcluir = leitorGeral(s);
                        Robo roboExcluir = null;

                        for (Robo alvoExclusao : robos) {
                            if (alvoExclusao.getCodigo() == codigoExcluir) {
                                roboExcluir = alvoExclusao;
                            }
                        }
                        if (roboExcluir == null) {
                            System.out.println("Robô não encontrado!");
                        } else if (roboExcluir.getCombates() > 0) {
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

    // FIM DOS CASES, INICIOS DOS LEITORES

    // MEU LEITOR MENU: valida especificamente a opção do menu principal de 0 a 7
    public static int leitorMenu(Scanner s) {
        int opt = -1;
        do {
            try {
                opt = s.nextInt();
                if (opt < 0 || opt > 7) {
                    System.out.println("Operação inválida!");
                    System.out.println("Digite um número entre 0 e 7.");
                    opt = -1;
                }
            } catch (InputMismatchException e) {
                s.next();
                System.out.println("Operação inválida");
                System.out.println("Digite novamente a informação!");
                opt = -1;
            }
        } while (opt < 0);
        return opt;
    }

    // MEU LEITOR GERAL: aceita qualquer número inteiro, mas não aceita letras
    public static int leitorGeral(Scanner s) {
        int numero = 0;
        boolean valido = false;
        while (valido == false) {
            try {
                numero = s.nextInt();
                valido = true;
            } catch (InputMismatchException e) {
                s.next();
                System.out.println("Entrada inválida! Digite um número inteiro: ");
            }
        }
        return numero;
    }

    // Eu já tinha feito este método separado para o combate da rodada geral
    public static void executarCombate(Robo robo1, Robo robo2) {
        System.out.println("COMBATE AUTORIZADO! ");
        Robo primeiro;
        Robo segundo;

        if (robo1.getPontos() < robo2.getPontos()) {
            primeiro = robo1;
            segundo = robo2;
        } else if (robo2.getPontos() < robo1.getPontos()) {
            primeiro = robo2;
            segundo = robo1;
        } else if (robo1.getCodigo() < robo2.getCodigo()) {
            primeiro = robo1;
            segundo = robo2;
        } else {
            primeiro = robo2;
            segundo = robo1;
        }

        System.out.println(primeiro.getNome() + " ataca primeiro em todas as rodadas! ");
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
                System.out.println(
                        segundo.getNome() + " atacou " + primeiro.getNome() + " e causou " + dano + " de dano.");
                System.out.println("Energia restante de " + primeiro.getNome() + ": " + primeiro.getEnergia());
                if (primeiro.getEnergia() == 0) {
                    System.out.println(primeiro.getNome() + " ficou sem energia! Combate encerrado.");
                }
            } else {
                System.out.println(segundo.getNome() + " ficou sem energia! Combate encerrado, sem contra-ataque.");
            }
            rodada = rodada + 1;
        }

        System.out.println("===== RESULTADO FINAL =====");
        System.out.println("Energia final: " + primeiro.getNome() + " = " + primeiro.getEnergia()
                + " | " + segundo.getNome() + " = " + segundo.getEnergia());

        if (primeiro.getEnergia() == 0) {
            System.out.println(segundo.getNome() + " VENCEU!");
            segundo.vencer();
            primeiro.perder();
        } else if (segundo.getEnergia() == 0) {
            System.out.println(primeiro.getNome() + " VENCEU!");
            primeiro.vencer();
            segundo.perder();
        } else if (primeiro.getEnergia() > segundo.getEnergia()) {
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

    // Meu método veioAntes define quem fica na frente da classificação
    public static boolean veioAntes(Robo a, Robo b) {
        if (a.getPontos() != b.getPontos()) {
            return a.getPontos() > b.getPontos();
        }
        if (a.getVitorias() != b.getVitorias()) {
            return a.getVitorias() > b.getVitorias();
        }
        if (a.getEnergia() != b.getEnergia()) {
            return a.getEnergia() > b.getEnergia();
        }
        return a.getCodigo() < b.getCodigo();
    }

    // Minha ordenação é manual, sem sort e sem Collections.sort
    public static ArrayList<Robo> montarClassificacao(ArrayList<Robo> robos) {
        ArrayList<Robo> classificacao = new ArrayList<>();
        for (Robo competidor : robos) {
            classificacao.add(competidor);
        }
        for (int i = 0; i < classificacao.size() - 1; i++) {
            int melhor = i;
            for (int j = i + 1; j < classificacao.size(); j++) {
                if (veioAntes(classificacao.get(j), classificacao.get(melhor))) {
                    melhor = j;
                }
            }
            Robo temp = classificacao.get(i);
            classificacao.set(i, classificacao.get(melhor));
            classificacao.set(melhor, temp);
        }
        return classificacao;
    }
}

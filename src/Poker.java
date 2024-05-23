import java.util.*;
import java.io.PrintStream;
import java.lang.Thread;

public class Poker{
    public static void limparTerminal() {
        PrintStream printStream = new PrintStream(System.out);
        printStream.println("\u001b[H\u001b[2J");
    }
    public static void darCartasJogadores(Baralho baralho, Jogador[] jogadores, int cartasPorJogador){
        for(int i=0; i < cartasPorJogador; i++){
            for(Jogador jogador : jogadores){
                if(!baralho.isEmpty()){
                    jogador.receberCarta(baralho.puxaCarta());
                }
                else{
                    System.err.println("A pilha está vazia");
                    return;
                }
            }
        }
    }
    public static void darCartasFlop(Baralho baralho, Carta[] cartasComu, int cartasFlop){
        for(int i=0;i<cartasFlop; i++){
            if(!baralho.isEmpty()){
                for(int j=0;j<cartasFlop;j++){
                    cartasComu[j]=baralho.puxaCarta(); 
                }
            }
            else{
                System.out.println("Pilha esta vazia");
                return;
            }
        }
    }
    public static void darPenultimaCarta(Baralho baralho, Carta[] cartaPen){
        if(!baralho.isEmpty()){
            cartaPen[0]=baralho.puxaCarta();
        }
        else{
            System.out.println("Pilha esta vazia");
            return;
        }
    }
    public static void darUltimaCarta(Baralho baralho, Carta[] cartaUlt){
        if(!baralho.isEmpty()){
            cartaUlt[0]=baralho.puxaCarta();
        }
        else{
            System.out.println("Pilha esta vazia");
            return;
        }
    }
    public static void mostrarCartasFlop(Carta[] cartasComu){
        for(Carta carta: cartasComu){
            carta.display();
        }
    }
    public static void mostrarPenCarta(Carta[] cartaPen){
        cartaPen[0].display();
    }
    public static void mostrarUltimaCarta(Carta[] cartaUlt){
        cartaUlt[0].display();
    }
    public static int valorCarta(String num){
        switch (num) {
            case "A":
                return 14;
            case "K":
                return 13;
            case "Q":
                return 12;
            case "J":
                return 11;
            default:
                return Integer.parseInt(num);
        }
    }
    public static int valorNaipe(String naipe){
        switch (naipe) {
            case "Ouros":
                return 1;
            case "Espadas":
                return 2;
            case "Copas":
                return 3;
            case "Paus":
                return 4;
            default:
                return 0;
        }
    }
    public static boolean fullHouse(Jogador jogador, Carta[] flop){
        return pares(jogador,flop) && trinca(jogador, flop);
    }
    public static boolean flush(Jogador jogador, Carta[] flop){
        int contO=0, contE=0, contC=0, contP=0;
        List<Carta> mao = jogador.getMao();
        for(Carta c : flop){
            mao.add(c);
        }
        for(Carta c: mao){
            String naipe = c.getNaipe();
            switch(naipe){
                case "Ouros":
                    contC++;
                    break;
                case "Espadas":
                    contE++;
                    break;
                case "Copas":
                    contC++;
                    break;
                case "Paus":
                    contP++;
                    break;
            }
        }
        return contO >= 5 || contE >= 5 || contC >= 5 || contP >= 5;
    }

    public static boolean pares(Jogador jogador, Carta[] flop){
        List<Carta> mao = jogador.getMao();
        Carta[] cartaMao = new Carta[2];
        for(int i=0;i<2;i++){
            cartaMao[i] = mao.get(i);
        }
        if(cartaMao[0].getNum() == cartaMao[1].getNum()){
            return true;
        }
        for(int i=0;i<2;i++){
            for(int j=0;j<5;j++){
                if(cartaMao[i].getNum() == flop[j].getNum()){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean doisPares(Jogador jogador, Carta[] flop) {
        List<Carta> mao = new ArrayList<>(jogador.getMao());
        mao.addAll(Arrays.asList(flop));

        int[] occurrences = new int[15]; // Índices de 2 a 14 para representar cartas de 2 a A
        for (Carta carta : mao) {
            int num = valorCarta(carta.getNum());
            occurrences[num]++;
        }

        int pairCount = 0;
        for (int count : occurrences) {
            if (count >= 2) {
            pairCount++;
            }
        }

        return pairCount >= 2;
    }
    public static boolean trinca(Jogador jogador, Carta[] flop) {
        List<Carta> mao = new ArrayList<>(jogador.getMao());
        mao.addAll(Arrays.asList(flop));
    
        int[] occurrences = new int[15]; // Índices de 2 a 14 para representar cartas de 2 a A
        for (Carta carta : mao) {
            int num = valorCarta(carta.getNum());
            occurrences[num]++;
        }
    
        for (int count : occurrences) {
            if (count >= 3) {
                return true;
            }
        }
    
        return false;
    }
    public static boolean quadra(Jogador jogador, Carta[] flop) {
        List<Carta> mao = new ArrayList<>(jogador.getMao());
        mao.addAll(Arrays.asList(flop));
    
        int[] occurrences = new int[15]; // Índices de 2 a 14 para representar cartas de 2 a A
        for (Carta carta : mao) {
            int num = valorCarta(carta.getNum());
            occurrences[num]++;
        }
    
        for (int count : occurrences) {
            if (count == 4) {
                return true;
            }
        }
    
        return false;
    }
    public static Carta maiorCarta(Jogador jogador){
        Carta[] mao = new Carta[2];
        Carta maiorCarta = new Carta();
        mao[0]=jogador.getMao().get(0);
        mao[1]=jogador.getMao().get(1);

        if(valorCarta(mao[0].getNum()) > valorCarta(mao[1].getNum())){
            maiorCarta = mao[0];
        }
        else if(valorCarta(mao[0].getNum()) < valorCarta(mao[1].getNum())){
            maiorCarta = mao[1];
        }
        return maiorCarta;

    }

    public static int comparaMao(Jogador jogador, Carta[] cartasComu, Carta[] cartaPen, Carta[] cartaUlt){
        int forca = 0;
        Carta[] flop = new Carta[5];
        for(int i=0;i<3;i++){
            flop[i] = cartasComu[i];
        }
        flop[3]=cartaPen[0];
        flop[4]=cartaUlt[0];

        if(quadra(jogador, flop)){
            forca = 7;
        }
        else if(fullHouse(jogador, flop)){
            forca = 6;
        }
        else if(flush(jogador, flop)){
            forca = 5;
        }
        else if(trinca(jogador, flop)){
            forca = 4;
        }
        else if(doisPares(jogador, flop)){
            forca = 3;
        }
        else if(pares(jogador,flop)){
            forca = 2;
        }
        else{
            forca = 1;
        }
        return forca;
    }
    public static void menuJogador(Jogador jogador, int valorMesa){
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        //int aposta;
        int selec;
        do{
        System.out.println("O que deseja fazer " + jogador.getNome() + " ?");
        System.out.println("[1] Mostrar as cartas");
        System.out.println("[2] Passar para o Proximo");

        selec = scan.nextInt();
        scan.nextLine();
        switch(selec){
            case 1:
                jogador.mostrarMao();
                break;
            case 2:
                break;
            default:
                System.out.println("OPÇAO INVALIDA");
                break;
            }
        }while(selec!=2);
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Selecione a quantidade de Jogadores: ");
        int numJogadores = scan.nextInt();
        scan.nextLine();

        Jogador[] jogadores = new Jogador[numJogadores];
        for(int i=0;i<numJogadores;i++){
            System.out.print("Digite o nome do Jogador " + (i+1) + ": ");
            String nomeJogador = scan.nextLine();
            jogadores[i] = new Jogador(nomeJogador, 10);
        }

        Baralho baralho = new Baralho();
        int valorMesa=0;
        darCartasJogadores(baralho, jogadores, 2);
        
        for(int i=0;i<jogadores.length;i++){
            limparTerminal();
            menuJogador(jogadores[i], valorMesa);
        }
        
        
        Carta[] cartasFlop = new Carta[3];
        Carta[] penCarta = new Carta[1];
        Carta[] ultCarta = new Carta[1];
        darCartasFlop(baralho, cartasFlop, cartasFlop.length);
        
        for(int i=0;i<jogadores.length;i++){
            limparTerminal();
            System.out.println("Cartas FLOP-------------------");
            mostrarCartasFlop(cartasFlop);
            menuJogador(jogadores[i], valorMesa);
        }
        
        darPenultimaCarta(baralho, penCarta);
        for(int i=0;i<jogadores.length;i++){
            limparTerminal();
            System.out.println("Cartas FLOP-------------------");
            mostrarCartasFlop(cartasFlop);
            mostrarPenCarta(penCarta);
            menuJogador(jogadores[i], valorMesa);
        }
        
        darUltimaCarta(baralho, ultCarta);
        for(int i=0;i<jogadores.length;i++){
            limparTerminal();
            System.out.println("Cartas FLOP-------------------");
            mostrarCartasFlop(cartasFlop);
            mostrarPenCarta(penCarta);
            mostrarUltimaCarta(ultCarta);
            menuJogador(jogadores[i], valorMesa);
        }

        limparTerminal();
        for(Jogador jogador : jogadores){
            System.out.println("Cartas FLOP-------------------");
            mostrarCartasFlop(cartasFlop);
            mostrarPenCarta(penCarta);
            mostrarUltimaCarta(ultCarta);
            jogador.mostrarMao();
        }
        
        JogadorComparacao[] jogadorComparacoes = new JogadorComparacao[numJogadores];

        for (int i = 0; i < numJogadores; i++) {
            int comparacao = comparaMao(jogadores[i], cartasFlop, penCarta, ultCarta);
            jogadorComparacoes[i] = new JogadorComparacao(jogadores[i], comparacao);
        }

        // Encontra o jogador com a maior força de mão
        int maiorForca = -1;
        for (JogadorComparacao jc : jogadorComparacoes) {
            if (jc.comparacao > maiorForca) {
                maiorForca = jc.comparacao;
            }
        }

        // Identifica jogadores empatados com a maior força de mão
        List<JogadorComparacao> jogadoresEmpatados = new ArrayList<>();
        for (JogadorComparacao jc : jogadorComparacoes) {
            if (jc.comparacao == maiorForca) {
                jogadoresEmpatados.add(jc);
            }
        }

        if (jogadoresEmpatados.size() > 1) {
            // Desempate pela maior carta
            Jogador vencedor = jogadoresEmpatados.get(0).jogador;
            Carta maiorCartaVencedor = maiorCarta(vencedor);

            boolean empate = false;
            for (int i = 1; i < jogadoresEmpatados.size(); i++) {
                Jogador jogadorAtual = jogadoresEmpatados.get(i).jogador;
                Carta maiorCartaAtual = maiorCarta(jogadorAtual);

                if (valorCarta(maiorCartaAtual.getNum()) > valorCarta(maiorCartaVencedor.getNum())) {
                    vencedor = jogadorAtual;
                    maiorCartaVencedor = maiorCartaAtual;
                    empate = false;
                } else if (valorCarta(maiorCartaAtual.getNum()) == valorCarta(maiorCartaVencedor.getNum())) {
                    empate = true;
                }
            }

            if (empate) {
                System.out.println("EMPATE entre:");
                for (JogadorComparacao jc : jogadoresEmpatados) {
                    System.out.println(jc.jogador.getNome());
                }
            } else {
                System.out.println(vencedor.getNome() + " GANHOU!!!");
            }
        } else {
            System.out.println(jogadoresEmpatados.get(0).jogador.getNome() + " GANHOU!!!");
        }

        scan.close();
        Thread.sleep(100000);
    }
}


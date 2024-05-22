import java.util.*;
import java.io.PrintStream;

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

    /*public static boolean continuar(Jogador[] jogadores){
        for(int i=0;i<jogadores.length;i++){
            if(!jogadores[i].getContinua()){
                return false;
            }
        }
        return true;
    }
    public static boolean verificaAposta(int valorMesa, Jogador[] jogadores){
        for(int i=0; i<jogadores.length; i++){
            if(jogadores[i].getAposta()!=valorMesa){
                return false;
            }
        }
        return true;
    }*/
    public static int comparaMao(Jogador jogador, Carta[] cartasComu, Carta[] cartaPen, Carta[] cartaUlt){
        int forca = 0;
        Carta[] flop = new Carta[5];
        for(int i=0;i<3;i++){
            flop[i] = cartasComu[i];
        }
        flop[3]=cartaPen[0];
        flop[4]=cartaUlt[0];

        if(trinca(jogador, flop)){
            forca = 3;
        }
        else if(doisPares(jogador, flop)){
            forca = 2;
        }
        else if(pares(jogador,flop)){
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
        //System.out.println("[3] Apostar");

        selec = scan.nextInt();
        scan.nextLine();
        switch(selec){
            case 1:
                jogador.mostrarMao();
                break;
            case 2:
                jogador.setContinua(true);
                break;
            /*case 3:
                do{
                    System.out.print("Quanto apostar");
                    aposta = scan.nextInt();
                    scan.nextLine();
                    if(aposta>=valorMesa){
                        System.out.println("Valor minimo não preenchido");
                    }
                }while(aposta>=valorMesa);
                break;*/
    
            default:
                System.out.println("OPÇAO INVALIDA");
                break;
            }
        }while(selec!=2);
    }

    public static void main(String[] args) {
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
        
        for(int i=0;i<jogadores.length;i++){
            limparTerminal();
            System.out.println("Cartas FLOP-------------------");
            mostrarCartasFlop(cartasFlop);
            darPenultimaCarta(baralho, penCarta);
            mostrarPenCarta(penCarta);
            menuJogador(jogadores[i], valorMesa);
        }
        
        for(int i=0;i<jogadores.length;i++){
            limparTerminal();
            System.out.println("Cartas FLOP-------------------");
            mostrarCartasFlop(cartasFlop);
            darPenultimaCarta(baralho, penCarta);
            mostrarPenCarta(penCarta);
            darUltimaCarta(baralho, ultCarta);
            mostrarUltimaCarta(ultCarta);
            menuJogador(jogadores[i], valorMesa);
        }

        for(Jogador jogador : jogadores){
            jogador.mostrarMao();
        }
        
        int[] comparacoes = new int[numJogadores];

        for(int i=0; i< numJogadores;i++){
            comparacoes[i]= comparaMao(jogadores[i], cartasFlop, penCarta, ultCarta);
        }
        for(int i=0; i<numJogadores; i++){
            for(int j=0;j<numJogadores-1;j++){
                if(comparacoes[j]<comparacoes[j+1]){
                    int temp = comparacoes[j];
                    comparacoes[j] = comparacoes[j+1];
                    comparacoes[j+1] = temp;
                }
            }
        }
        if (comparacoes[0] == comparacoes[1]) {
            System.out.println("EMPATE entre:");
            for (int i = 0; i < numJogadores && comparacoes[i] == comparacoes[0]; i++) {
                System.out.println(jogadores[i].getNome());
            }
        } else {
            System.out.println(jogadores[comparacoes[0]].getNome() + " GANHOU!!!");
        }

        scan.close();
    }
}


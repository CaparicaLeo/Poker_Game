import java.util.*;
public class Poker{

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
    public static void darUltimaCarta(Baralho baralho, Carta[] cartaPen){
        if(!baralho.isEmpty()){
            cartaPen[0]=baralho.puxaCarta();
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
    public static void mostrarUltimaCarta(Carta[] cartaPen){
        cartaPen[0].display();
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
            jogadores[i] = new Jogador(nomeJogador);
        }

        Baralho baralho = new Baralho();
        darCartasJogadores(baralho, jogadores, 2);

        Carta[] cartasFlop = new Carta[3];
        Carta[] penCarta = new Carta[1];
        Carta[] ultCarta = new Carta[1];
        darCartasFlop(baralho, cartasFlop, cartasFlop.length);
        System.out.println("Cartas FLOP-------------------");
        mostrarCartasFlop(cartasFlop);
        darPenultimaCarta(baralho, penCarta);
        mostrarPenCarta(penCarta);
        darUltimaCarta(baralho, ultCarta);
        mostrarUltimaCarta(ultCarta);
    
        for(Jogador jogador : jogadores){
            jogador.mostrarMao();
        }

        scan.close();
    }
}

import java.util.*;
public class Jogador {
    private String nome;
    private List<Carta> mao;
 

    public Jogador(String nome, int saldo) {
        this.nome = nome;
        this.mao = new ArrayList<>();
    }

    public void receberCarta(Carta carta){
        mao.add(carta);
    }
    public void mostrarMao(){
        System.out.println("------------------------------------------------");
        System.out.println("Mao de " + this.getNome());
        for(Carta carta : mao){
            carta.display();
        }
        System.out.println("------------------------------------------------");
    }
    public String getNome() {
        return nome;
    }

    public List<Carta> getMao() {
        return mao;
    }
}

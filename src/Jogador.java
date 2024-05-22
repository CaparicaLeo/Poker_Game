import java.util.*;
public class Jogador {
    private String nome;
    private List<Carta> mao;
    private int saldo;
    public int aposta;
    public boolean continua;

    public boolean getContinua() {
        return continua;
    }

    public void setContinua(boolean continua) {
        this.continua = continua;
    }

    public int getAposta() {
        return aposta;
    }

    public void setAposta(int aposta) {
        this.aposta = aposta;
    }

    public int getSaldo() {
        return saldo;
    }

    private void setSaldo(int dinheiro) {
        this.saldo = dinheiro;
    }

    public Jogador(String nome, int saldo) {
        this.nome = nome;
        this.saldo = saldo;
        this.mao = new ArrayList<>();
        this.aposta = 0;
        this.continua = false;
    }

    public void receberCarta(Carta carta){
        mao.add(carta);
    }

    public void apostar(int aposta){
        if(this.saldo<aposta){
            System.out.println("APOSTA INVALIDA");
        }
        else{
            this.setSaldo(this.getSaldo()-aposta);
        }
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

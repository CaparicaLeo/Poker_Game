import java.util.*;
public class Baralho{
    private Stack<Carta> cartas;

    public Baralho() {
        cartas = new Stack<>();
        String[] naipes = {"Ouros", "Espadas", "Copas", "Paus"};
        String[] nums = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        List<Carta> tempBaralho = new ArrayList<>();
        for(String naipe : naipes){
            for(String num : nums){
                tempBaralho.add(new Carta(naipe,num));
            }
        }
        Collections.shuffle(tempBaralho);
        for(Carta carta : tempBaralho){
            cartas.push(carta);
        }
    }

    public boolean isEmpty(){
        return cartas.empty();
    }

    public Carta puxaCarta() {
        if (!isEmpty()) {
            return cartas.pop();
        } else {
            throw new RuntimeException("A pilha esta vazia!!!");
        }
    }
}
public class Carta {
    private String naipes;
    private String nums;
    public Carta(){
        this.naipes = "0";
        this.nums = "0";
    }
    public Carta(String naipe, String num) {
        this.naipes = naipe;
        this.nums = num;
    }
    public void display(){
        if(this.naipes != "0" && this.nums != "0"){
            System.out.println(this.nums + " de " + this.naipes);
        }
    }
    public String getNaipe() {
        return this.naipes;
    }
    public String getNum() {
        return this.nums;
    }
}

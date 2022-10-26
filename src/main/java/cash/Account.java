package cash;

public class Account {
    final int id;
    int amount;

    public Account (int id, int amount){
        this.id = id;
        this.amount = amount;
    }

    public int amount(){
        return amount;
    }
}


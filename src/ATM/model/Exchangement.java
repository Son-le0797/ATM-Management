package ATM.model;

import java.util.Date;

public class Exchangement {

    String name;
    String amount;


    public Exchangement() {
    }



    public String getAmount() {
        return amount;
    }

    public Exchangement(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Exchangement{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String toStringCSV2(){
        return name + "," +
                amount + ",";
    }
}
package ATM.model;

import java.util.ArrayList;

public class Customer {

    String customerName;
    String password;
    String dob;
    String gender;
    String email;
    String balance;
    ArrayList<Exchangement> history;

    public Customer(){
        history = new ArrayList<>();
    }

    public Customer(String customerName, String password, String dob, String gender, String email, String balance){
        this.customerName = customerName;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.balance = balance;
        this.history = new ArrayList<>();
    }

    public Customer(String customerName, String password, String dob, String gender, String email, String balance, ArrayList<Exchangement> history) {
        this.customerName = customerName;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.balance = balance;
        this.history = history;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String username) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBalance() { return balance; }

    public void setBalance(String balance) { this.balance = balance; }

    public ArrayList<Exchangement> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Exchangement> history) {
        this.history = history;
    }

//    String hidePassword = password;
//    public String hidePassword() {
//        for (int i = 0; i < hidePassword.length(); i++) {
//            if (i > 2) {
//                hidePassword.charAt(i) = '*';
//            }
//        }
//    }

    @Override
    public String toString() {
        return "Tên khách hàng: " + customerName
                + "\nNgày, tháng, năm sinh: " + dob
                + "\nGiới tính: " + gender
                + "\nEmail: " + email
                + String.format("\nSố dư khả dụng: %,d VND.",Long.parseLong(balance) - 50000)
                +"\n============================================================";
    }

    public String toStringCSV(){
        return
                customerName + "," +
                        password + "," +
                        dob + "," +
                        gender + "," +
                        email + "," +
                        balance + "," +
                        historyToStringCSV() + "\n";
    }

    public String historyToStringCSV(){
        String result = "";
        for (Exchangement exchangement : history) {
            result += exchangement.toStringCSV2();
        }
        return result;
    }

//        public String addHistory() {
//
//        }
}
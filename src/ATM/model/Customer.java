package ATM.model;

import java.util.ArrayList;

public class Customer {

        String customerName;
        String password;
        String dob;
        String gender;
        String email;
        long balance;
        ArrayList<Exchangement> history;
        public Customer(){
            history = new ArrayList<>();
        }

        public Customer(String customerName, String password, String dob, String gender, String email, long balance){
            this.customerName = customerName;
            this.password = password;
            this.dob = dob;
            this.gender = gender;
            this.email = email;
            this.balance = balance;
            this.history = new ArrayList<>();
        }

        public Customer(String customerName, String password, String dob, String gender, String email, int balance, ArrayList<Exchangement> history) {
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

        public long getBalance() { return balance; }

        public void setBalance(long balance) { this.balance = balance; }

        public ArrayList<Exchangement> getHistory() {
            return history;
        }

        public void setHistory(ArrayList<Exchangement> history) {
            this.history = history;
        }

        @Override
        public String toString() {
            return "Ten khach hang: " + customerName
                    + "\nMat khau: " + password
                    + "\nNgay, thang, nam sinh: " + dob
                    + "\nGioi Tinh: " + gender
                    + "\nEmail: " + email
                    + String.format("\nSo du tai khoan: %,d VND.",balance)
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

package ATM.data;

import ATM.model.Customer;
import ATM.model.Exchangement;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomerData {
    private static final String PATH = "customer.csv";
    public HashMap<String, Customer> listCustomer;

    public CustomerData(){
        listCustomer = new HashMap<>();
    }

    public void writeFile() throws IOException {
        File file = new File(PATH);
        if (!file.exists()) {
            file.createNewFile();

        }
        else {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Map.Entry<String, Customer> entry : listCustomer.entrySet()){

                bufferedWriter.write(entry.getValue().toStringCSV());
            }
            bufferedWriter.close();
        }

    }

    public void add(Customer customer){
        listCustomer.put(customer.getCustomerName(),customer);
    }

    public void readFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));

        String line;
        while ((line = bufferedReader.readLine()) != null){
            String[] arr = line.split(",");
            ArrayList<Exchangement> exchangements = new ArrayList<>();
            for (int i = 0; i < (arr.length - 6)/2; i++) {
                exchangements.add(new Exchangement(arr[i*2+6],Integer.parseInt(arr[i * 2 + 7])));
            }

            this.add(new Customer(arr[0],arr[1],arr[2],arr[3], arr[4],Integer.parseInt(arr[5]),exchangements));
//            Customer customer = new Customer();
//            add( customer.setCustomerName(arr[0]),
//            customer.setPassword(arr[1]),
//            customer.setDob(arr[2]),
//            customer.setGender(arr[3]),
//            customer.setEmail(arr[4]),
//            customer.setBalance(Integer.parseInt(arr[5])),
//            customer.setHistory(exchangements));

        }
        bufferedReader.close();
    }

    public Customer findByCustomerName(String customerName){
        return listCustomer.get(customerName);
    }

    public boolean checkIsExistEmail( String email) {
        boolean a = false;
        for (Map.Entry<String, Customer> check : listCustomer.entrySet()) {
            a = check.getValue().getEmail().equals(email);
        }
        return a;
    }

    public Customer findByEmail(String email) {
        return listCustomer.get(email);
    }

    public void setPassword(String customerName, String newPassword){
        findByCustomerName(customerName).setPassword(newPassword);
    }

    public void setDob(String customerName, String newDob){
        findByCustomerName(customerName).setDob(newDob);
    }

    public void setGender(String customerName, String newGender){
        findByCustomerName(customerName).setGender(newGender);
    }

    public void setEmail(String customerName, String newEmail){
        findByCustomerName(customerName).setEmail(newEmail);
    }

    public void setBalance(String customerName, long newBalance){
        findByCustomerName(customerName).setBalance(newBalance);
    }

    public void addHistory(String customerName, Exchangement exchangement){
        findByCustomerName(customerName).getHistory().add(exchangement);
    }

    public void printHistory(String customerName){
        ArrayList<Exchangement> exchangements = findByCustomerName(customerName).getHistory();
        if (exchangements.size() == 0){
            System.out.println("Không có lịch sử giao dịch nào.");
        } else {
            int count = 0;
            for (int i = exchangements.size()-1; i >= 0 ; i--) {
                System.out.printf("%d. %s: %,d VND\n",(exchangements.size() - i), exchangements.get(i).getName(), exchangements.get(i).getAmount());
                count++;
                if (count == 3){
                    break;
                }
            }
        }

    }
}

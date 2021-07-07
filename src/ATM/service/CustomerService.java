package ATM.service;

import ATM.data.CustomerData;
import ATM.model.Customer;
import ATM.model.Exchangement;


import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerService {
    public Customer customer;

    public CustomerData customerData;

    public CustomerService(){
        customerData = new CustomerData();
    }

    public void updateFile() throws IOException {
        customerData.writeFile();
    }

    public void loadFile() throws IOException {
        customerData.readFile();
    }

    public void add(Customer customer) throws IOException {
        customerData.add(customer);
        System.out.println("Dang ki tai khoan thanh cong!");
        System.out.println("============================================================");
        updateFile();
    }

    public Customer findByCustomerName(String customerName){
        return customerData.findByCustomerName(customerName);
    }

    public void setPassword(String customerName, String newPassword) throws IOException {
        customerData.setPassword(customerName,newPassword);
        System.out.println("Thay doi mat khau thanh cong.");
        updateFile();
    }

    public void setDob(String customerName, String newDob) throws IOException {
        customerData.setDob(customerName,newDob);
        System.out.println("Thay doi ngay sinh thanh cong.");
        updateFile();
    }

    public void setGender(String customerName, String newGender) throws IOException {
        customerData.setGender(customerName,newGender);
        System.out.println("Thay doi gioi tinh thanh cong.");
        updateFile();
    }

    public void setEmail(String customerName, String newEmail) throws IOException {
        customerData.setEmail(customerName,newEmail);
        System.out.println("Thay doi email thanh cong.");
        updateFile();
    }

    public void setBalance(String customerName, long newBalance) throws IOException {
        customerData.setBalance(customerName,newBalance);
        System.out.println("\n============================================================");
        updateFile();
    }

    public void addHistory(String customerName, Exchangement exchangement) throws IOException {
        customerData.addHistory(customerName,exchangement);
        updateFile();
    }

    public void printHistory(String username){
        customerData.printHistory(username);
        System.out.println("============================================================");
    }

    public void register() throws  IOException {
        System.out.println("Nhap ten dang nhap: ");
        String Name = new Scanner(System.in).nextLine();

        while (findByCustomerName(Name) != null) {
            System.out.println("Ten dang nhap da ton tai, vui long nhap lai!");
            Name = new Scanner(System.in).nextLine();
            while (!checkCustomerName(Name)) {
                System.out.println("Ten dang nhap khong hop le, vui long nhap lai!");
                Name = new Scanner(System.in).nextLine();
            }
        }

        System.out.println("Nhap mat khau: ");
        String password = new Scanner(System.in).nextLine();
        while (!checkPassword(password)) {
            System.out.println("Mat khau khong hop le, vui long nhap lai!");

            password = new Scanner(System.in).nextLine();
        }

        System.out.println("Nhap ngay, thang, nam sinh: ");
        String dob = new Scanner(System.in).nextLine();
        while (!checkDoB(dob)) {
            System.out.println("Ngay, thang, nam sinh khong hop le, vui long nhap lai!");
            dob = new Scanner(System.in).nextLine();
        }

        System.out.println("Nhap gioi tinh(Nam, Nu, Khac): ");
        String gender = new Scanner(System.in).nextLine();
        while (!checkGender(gender)) {
            System.out.println("Gioi tinh nhap vao khong hop le!");
            gender = new Scanner(System.in).nextLine();
        }

        System.out.println("Nhap email: ");
        String email = new Scanner(System.in).nextLine();
        while (!checkEmail(email)) {
            System.out.println("Email khong hop le, vui long nhap lai!");
            email = new Scanner(System.in).nextLine();
        }

        System.out.println("Nhap so tien khoi tao tai khoan: ");
        long balance = new Scanner(System.in).nextLong();

        add(new Customer(Name,password,dob,gender,email,balance));
    }

    public void login() throws IOException{
        System.out.println("Nhap ten dang nhap: ");
        String customerName = new Scanner(System.in).nextLine();
        System.out.println("Nhap mat khau: ");
        String password = new Scanner(System.in).nextLine();

        if (!this.customerData.listCustomer.containsKey(customerName)) {
            System.out.println("Ten dang nhap khong ton tai, vui long nhap lai");
        } else if (!this.findByCustomerName(customerName).getPassword().equals(password)) {
            System.out.println("Mat khau khong chinh xac, vui long nhap lai");
        } else {
            System.out.println("Chao mung quy khach " + this.findByCustomerName(customerName).getCustomerName() + " !");
            this.customer = this.findByCustomerName(customerName);
            int check = -1;
            while (check != 6) {
                System.out.println("\nDanh sach cac dich vu: ");
                System.out.println("\t 1. Thay doi thong tin.");
                System.out.println("\t 2. Kiem tra tai khoan.");
                System.out.println("\t 3. Dich vu rut tien.");
                System.out.println("\t 4. Dich vu nap tien.");
                System.out.println("\t 5. Xem lich su giao dich.");
                System.out.println("\t 6. Quay lai.");
                System.out.println("============================================================");
                System.out.println("Nhap lua chon cua ban: ");

                check = new Scanner(System.in).nextInt();
                switch (check) {
                    case 1:
                        int check2 = -1;
                        while (check2 != 5) {
                            System.out.println("Thay doi thong tin: ");
                            System.out.println("\t 1. Mat khau.");
                            System.out.println("\t 2. Ngay ,thang, nam sinh.");
                            System.out.println("\t 3. Email.");
                            System.out.println("\t 4. Gioi tinh.");
                            System.out.println("\t 5. Quay lai.");
                            System.out.println("Nhap lua chon cua ban: ");
                            check2 = new Scanner(System.in).nextInt();
                            switch (check2) {
                                case 1:
                                    System.out.println("Nhap mat khau moi: ");
                                    String newPassword = new Scanner(System.in).nextLine();
                                    while (newPassword.equals(password) || !checkPassword(newPassword)) {
                                        System.out.println("Mat khau moi khong hop le, vui long nhap lai");
                                        newPassword = new Scanner(System.in).nextLine();
                                    }
                                    this.setPassword(customerName,newPassword);
                                    break;
                                case 2:
                                    System.out.println("Nhap ngay, thang, nam sinh: ");
                                    String newDob = new Scanner(System.in).nextLine();
                                    while (!checkDoB(newDob)) {
                                        System.out.println("Ngay, thang, nam sinh khong hop le, vui long nhap lai!");
                                        newDob = new Scanner(System.in).nextLine();
                                    }
                                    this.setDob(customerName,newDob);
                                    break;
                                case 3:
                                    System.out.println("Nhap email moi: ");
                                    String newEmail = new Scanner(System.in).nextLine();
                                    while (!checkEmail(newEmail)) {
                                        System.out.println("Email khong hop le, vui long nhap lai");
                                        newEmail = new Scanner(System.in).nextLine();
                                    }
                                    this.setEmail(customerName,newEmail);
                                    break;
                                case 4:
                                    System.out.println("Nhap gioi tinh(Nam, Nu, Khac): ");
                                    String newGender = new Scanner(System.in).nextLine();
                                    while (!checkGender(newGender)) {
                                        System.out.println("Gioi tinh nhap vao khong hop le, vui long nhap lai!");
                                        newGender = new Scanner(System.in).nextLine();
                                    }
                                    this.setGender(customerName,newGender);
                                    break;
//                                case 5:
//                                    System.exit(5);
//                                    break;
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Thong tin tai khoan cua quy khach: ");
                        System.out.println(findByCustomerName(customerName).toString());
                        break;
                    case 3:
                        System.out.println("Dich vu rut tien: ");
                        System.out.println("\tNhap so tien ban muon rut: ");
                        long Withdrawal = new Scanner(System.in).nextLong();
//                        Date time = new Date();
                        if (Withdrawal > findByCustomerName(customerName).getBalance()) {
                            System.out.println("Tai khoan quy khach khong du de thuc hien giao dich nay!");
                            System.out.printf("So du kha dung cua ban la: %,d VND", findByCustomerName(customerName).getBalance());
                        }
                        else {

                            this.findByCustomerName(customerName).getHistory().add(new Exchangement("rut tien", Withdrawal));

                            this.setBalance(customerName, findByCustomerName(customerName).getBalance() - Withdrawal);
                            System.out.printf("Ban vua rut < %,d > VND.",Withdrawal);
                            System.out.printf("\nSo du kha dung cua ban la: %,d VND",findByCustomerName(customerName).getBalance());
                            // customer.getHistory().add(new Exchangement("rut tien", Withdrawal))

                        }
                        break;
                    case 4:
                        System.out.println("Dich vu nap tien: ");
                        System.out.println("\tNhap so tien ban muon nap: ");
                        long recharge = new Scanner(System.in).nextLong();

                        this.findByCustomerName(customerName).getHistory().add(new Exchangement("nap tien",recharge));

                        this.setBalance(customerName,findByCustomerName(customerName).getBalance() + recharge);
                        System.out.printf("Ban vua nap < %,d > VND.",recharge);
                        System.out.printf("\nSo du kha dung cua ban la: %,d VND",findByCustomerName(customerName).getBalance());
                        break;
                    case 5:
                        printHistory(customerName);
                        break;
//                    case 6:
//                        System.exit(6);
//                        break;
                }
            }
        }
    }

    public static boolean checkEmail(String email){
        String regex = "^[a-zA-Z0-9]+[a-zA-Z0-9._]*@\\w{4,24}.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkDoB(String dob){
        String regex = "^(3[01]|[1-2][0-9]|0[1-9])([/\\-])(1[0-2]|0[1-9])([/\\-])(19[0-9]{2}|20[0-9]{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dob);
        return matcher.matches();
    }

    public static boolean checkGender(String gender){
        String regex = "^(Nam|Nu|Khac)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(gender);
        return matcher.matches();
    }

    public static boolean checkCustomerName(String username){
        String regex = "^[A-Za-z0-9]+[a-zA-Z0-9._\\s]{4,256}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean checkPassword(String password){
        String regex = "^[A-Za-z0-9]+[a-zA-Z0-9._]{4,256}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}

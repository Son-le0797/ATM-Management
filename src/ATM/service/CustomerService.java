package ATM.service;

import ATM.data.CustomerData;
import ATM.model.Customer;
import ATM.model.Exchangement;


import java.io.IOException;
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
        System.out.println("Đăng kí tài khoản thành công");
        System.out.println("============================================================");
        updateFile();
    }

    public Customer findByCustomerName(String customerName){
        return customerData.findByCustomerName(customerName);
    }

    public boolean checkIsExistEmail(String email) {
        return customerData.checkIsExistEmail(email);
    }

    public void setPassword(String customerName, String newPassword) throws IOException {
        customerData.setPassword(customerName,newPassword);
        System.out.println("Thay đổi mật khẩu thành công.");
        System.out.println("\n============================================================");
        updateFile();
    }

    public void setDob(String customerName, String newDob) throws IOException {
        customerData.setDob(customerName,newDob);
        System.out.println("Thay đổi ngày sinh thành công.");
        System.out.println("\n============================================================");
        updateFile();
    }

    public void setGender(String customerName, String newGender) throws IOException {
        customerData.setGender(customerName,newGender);
        System.out.println("Thay đổi giới tính thành công.");
        System.out.println("\n============================================================");
        updateFile();
    }

    public void setEmail(String customerName, String newEmail) throws IOException {
        customerData.setEmail(customerName,newEmail);
        System.out.println("Thay đổi email thành công.");
        System.out.println("\n============================================================");
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
        System.out.println("Nhập tên đăng nhập: ");
        String Name = new Scanner(System.in).nextLine();

        while (findByCustomerName(Name) != null) {
            System.out.println("Tên đăng nhập đã tồn tại, vui lòng nhập lại!");
            Name = new Scanner(System.in).nextLine();
            while (!checkCustomerName(Name)) {
                System.out.println("Tên đăng nhập không hợp lệ, vui lòng nhập lại!");
                Name = new Scanner(System.in).nextLine();
            }
        }

        System.out.println("Nhập mật khẩu: ");
        String password = new Scanner(System.in).nextLine();
        while (!checkPassword(password)) {
            System.out.println("Mật khảu không hợp lệ, vui lòng nhập lại!");
            password = new Scanner(System.in).nextLine();
        }

        System.out.println("Nhập ngày, tháng, năm sinh(vd: 20/03/2015): ");
        String dob = new Scanner(System.in).nextLine();
        while (!checkDoB(dob)) {
            System.out.println("Ngày, tháng, năm sinh không hợp lệ, vui lòng nhập lại!");
            System.out.println("Nhập lại ngày, tháng, năm sinh(vd: 20/03/2015): ");
            dob = new Scanner(System.in).nextLine();
        }

        System.out.println("Nhập giới tính(Nam, Nữ, Khác): ");
        String gender = new Scanner(System.in).nextLine();
        while (!checkGender(gender)) {
            System.out.println("Giới tính nhập vào không hợp lệ, vui lòng nhập lại!");
            System.out.println("Nhập giới tính(Nam, Nữ, Khác): ");
            gender = new Scanner(System.in).nextLine();
        }

        System.out.println("Nhập email: ");
        String email = new Scanner(System.in).nextLine();
        while (checkIsExistEmail(email)) {
            System.out.println("Email này đã tồn tại, vui lòng nhập lại!");
            email = new Scanner(System.in).nextLine();
            while (!checkEmail(email)) {
                System.out.println("Email không hợp lệ, vui lòng nhập lại!");
                email = new Scanner(System.in).nextLine();
            }
        }


        long balance = -1;
        while (!checkMoney(String.valueOf(balance)) || balance < 50000) {
            System.out.println("Nhập số tiền khởi tạo tài khoản(tối thiểu 50,000 VND): ");
            balance = new Scanner(System.in).nextLong();
        }

        add(new Customer(Name,password,dob,gender,email,balance));
    }

    public void login() throws IOException{
        System.out.println("Nhập tên đăng nhập: ");
        String customerName = new Scanner(System.in).nextLine();
        System.out.println("Nhập mật khẩu: ");
        String password = new Scanner(System.in).nextLine();

        if (!this.customerData.listCustomer.containsKey(customerName)) {
            System.out.println("Tên đăng nhập không tồn tại, vui lòng nhập lại!");
        } else if (!this.findByCustomerName(customerName).getPassword().equals(password)) {
            System.out.println("Mật khảu không chính xác, vui lòng nhập lại!");
        } else {
            System.out.println("Chào mừng quý khách " + this.findByCustomerName(customerName).getCustomerName() + " !");
            this.customer = this.findByCustomerName(customerName);
            int check = -1;
            while (check != 6) {
                System.out.println("\nDanh sách các dịch vụ: ");
                System.out.println("\t 1. Thay đổi thông tin.");
                System.out.println("\t 2. Kiểm tra tài khoản.");
                System.out.println("\t 3. Dịch vụ rút tiền.");
                System.out.println("\t 4. Dịch vụ nạp tiền.");
                System.out.println("\t 5. Xem lịch sử giao dịch.");
                System.out.println("\t 6. Quay lai.");
                System.out.println("============================================================");
                System.out.println("Nhập lựa chọn của bạn: ");

                check = new Scanner(System.in).nextInt();
                switch (check) {
                    case 1:
                        int check2 = -1;
                        while (check2 != 5) {
                            System.out.println("\t--- Thay đổi thông tin --- ");
                            System.out.println("\n\t 1. Mật khẩu.");
                            System.out.println("\t 2. Ngày, tháng, năm sinh.");
                            System.out.println("\t 3. Email.");
                            System.out.println("\t 4. Giới tính.");
                            System.out.println("\t 5. Quay lại.");
                            System.out.println("Nhập lựa chọn của bạn: ");
                            check2 = new Scanner(System.in).nextInt();
                            switch (check2) {
                                case 1:
                                    System.out.println("Nhập mật khảu mới: ");
                                    String newPassword = new Scanner(System.in).nextLine();
                                    while (newPassword.equals(password) || !checkPassword(newPassword)) {
                                        System.out.println("Mật khảu không hợp lệ, vui lòng nhập lại");
                                        newPassword = new Scanner(System.in).nextLine();
                                    }
                                    this.setPassword(customerName,newPassword);
                                    break;
                                case 2:
                                    System.out.println("Nhập ngày, tháng, năm sinh: ");
                                    String newDob = new Scanner(System.in).nextLine();
                                    while (!checkDoB(newDob)) {
                                        System.out.println("Ngày, tháng, năm sinh không hợp lệ!");
                                        System.out.println("Nhập lại ngày, tháng, năm sinh(vd: 20/03/2015): ");
                                        newDob = new Scanner(System.in).nextLine();
                                    }
                                    this.setDob(customerName,newDob);
                                    break;
                                case 3:
                                    System.out.println("Nhập email mới: ");
                                    String newEmail = new Scanner(System.in).nextLine();
                                    while (!checkEmail(newEmail)) {
                                        System.out.println("Email không hợp lệ, vui lòng nhập lại");
                                        newEmail = new Scanner(System.in).nextLine();
                                    }
                                    this.setEmail(customerName,newEmail);
                                    break;
                                case 4:
                                    System.out.println("Nhập giới tính(Nam, Nữ, Khác): ");
                                    String newGender = new Scanner(System.in).nextLine();
                                    while (!checkGender(newGender)) {
                                        System.out.println("Giới tính nhập vào không hợp lệ!");
                                        System.out.println("Nhập lại giới tính(Nam, Nữ, Khác): ");
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
                        System.out.println("\t--- Thông tin tài khoản của khách hàng --- ");
                        System.out.println(findByCustomerName(customerName).toString());
                        break;
                    case 3:
                        System.out.println("\t--- Dịch vụ rút tiền --- ");
                        String Withdrawal = "-1";
                        while (!checkMoney(Withdrawal) || Long.parseLong(Withdrawal) <= 0 || Long.parseLong(Withdrawal) > findByCustomerName(customerName).getBalance()) {
                            System.out.printf("Số dư khả dụng của quý khách là: %,d VND\n",findByCustomerName(customerName).getBalance());
                            System.out.println("Nhập số tiền bạn muốn rút: ");
                            Withdrawal = new Scanner(System.in).nextLine();
                        }
//                        Date time = new Date();
//                        if (Withdrawal > findByCustomerName(customerName).getBalance()) {
//                            System.out.println("Tai khoan quy khach khong du de thuc hien giao dich nay!");
//                            System.out.printf("So du kha dung cua ban la: %,d VND", findByCustomerName(customerName).getBalance());
//                        }
//                        else if (Withdrawal <= 0){
//                            System.out.println("Số tiền muốn rút không hợp lệ, vui long nhập lại");
//                        }

                        this.findByCustomerName(customerName).getHistory().add(new Exchangement("rut tien", Long.parseLong(Withdrawal)));

                        this.setBalance(customerName, findByCustomerName(customerName).getBalance() - Long.parseLong(Withdrawal));
                        System.out.printf("Quý khách vừa rút < %,d > VND.",Long.parseLong(Withdrawal));
                        System.out.printf("\nSố dư khả dụng của quý khách là: %,d VND",findByCustomerName(customerName).getBalance());
                        System.out.println("\n============================================================");
                        // customer.getHistory().add(new Exchangement("rut tien", Withdrawal))

                        break;
                    case 4:
                        System.out.println("\t--- Dịch vụ nạp tiền --- ");
                        System.out.printf("Số dư khả dụng của quý khách là: %,d VND\n",findByCustomerName(customerName).getBalance());

                        String recharge = "-1";
                        while (!checkMoney(recharge) || Long.parseLong(recharge) <= 0) {
                            System.out.println("Nhập số tiền quý khách muốn nạp: ");
                            recharge = new Scanner(System.in).nextLine();
                        }

                        this.findByCustomerName(customerName).getHistory().add(new Exchangement("nap tien",Long.parseLong(recharge)));

                        this.setBalance(customerName,findByCustomerName(customerName).getBalance() + Long.parseLong(recharge));
                        System.out.printf("Quý khách vừa nạp < %,d > VND.",Long.parseLong(recharge));
                        System.out.printf("\nSố dư khả dụng của quý khách là: %,d VND",findByCustomerName(customerName).getBalance());
                        System.out.println("\n============================================================");
                        break;
                    case 5:
                        System.out.println("\t--- Xem lịch sử giao dịch 3 lần gần nhất ---");
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
        String regex = "^[a-zA-Z0-9]+[a-zA-Z0-9._]*@\\w{4,24}\\.com$";
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
        String regex = "^(Nam|Nữ|Khác)";
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

    public static boolean checkIsANumber(String number) {
        String regex = "[123]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }



    public static boolean checkMoney(String balance) {
        String regex = "^[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(balance);
        return matcher.matches();
    }
}

package ATM.presentation;

import ATM.service.CustomerService;
import java.io.IOException;
import java.util.Scanner;

import static ATM.service.CustomerService.checkIsANumber;


public class Main {
    public  static CustomerService customerService = new CustomerService();

    public static void main(String[] args) {

        try {
//            customerService.updateFile();
            customerService.loadFile();
        }

        catch (IOException e) { e.printStackTrace(); }

        Scanner sc = new Scanner(System.in);

        System.out.println("Kính chào quý khách !!");

        String choice = "-1";

        while (!choice.equals("3")) {
            System.out.println("\nDanh sách các chức năng: ");
            System.out.println("\t 1. Đăng nhập vào tài khoản.");
            System.out.println("\t 2. Đăng kí tài khoản mới.");
            System.out.println("\t 3. Thoát.");
            System.out.println("===============================================================");


//            choice = sc.nextInt();
//            while (choice != 1 || choice != 2 || choice != 3) {
//                choice = sc.nextInt();
//            }
//            while (isNaN(choice))
            choice = "-1";
            while (!checkIsANumber(choice)) {
                System.out.print("Nhập lựa chọn của bạn: ");
                choice = new Scanner(System.in).nextLine();
            }

            switch (Integer.parseInt(choice)) {
                case 1:
                    try {
                        customerService.login();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        customerService.register();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.exit(3);
                    break;
                default:

            }
        }
    }
}
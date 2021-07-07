package ATM.presentation;

import ATM.service.CustomerService;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public  static CustomerService customerService = new CustomerService();

    public static void main(String[] args) {

        try {
            customerService.updateFile();
            customerService.loadFile();
        }

        catch (IOException e) { e.printStackTrace(); }

        Scanner sc = new Scanner(System.in);

        System.out.println("Kinh chao quy khach !!");
        System.out.println("Danh sach cac chuc nang: \n");
        int choice = -1;

        while (choice !=3) {

            System.out.println("\t 1. Dang nhap vao tai khoan.");
            System.out.println("\t 2. Dang ki tai khoan moi.");
            System.out.println("\t 3. Thoat.");
            System.out.print("\nNhap lua chon cua ban: ");

            System.out.println("\n===============================================================");

            choice = sc.nextInt();
//            while (choice != 1 || choice != 2 || choice != 3) {
//                choice = sc.nextInt();
//            }

//            while (isNaN(choice))
            switch (choice) {
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

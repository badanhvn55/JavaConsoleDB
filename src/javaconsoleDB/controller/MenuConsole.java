/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaconsoleDB.controller;

import java.util.Scanner;

/**
 *
 * @author ueda
 */
public class MenuConsole {
    public static void main(String[] args) {
        MenuConsole menu = new MenuConsole();
        menu.createMenu();
    }

    public void createMenu() {
        while (true) {
            System.out.println("=========Student Manager========");
            System.out.println("1. Student list.");
            System.out.println("2. Add student.");
            System.out.println("3. Edit student.");
            System.out.println("4. Delete student.");
            System.out.println("5. Exit.");
            System.out.println("=================================");
            System.out.println("Please enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            // Yêu cầu người dùng nhập chuỗi ký tự, gán giá trị người dùng nhập vào
            // ra biến kiểu  chuỗi tên là strChoice.
            String strChoice = scanner.nextLine();            
            // Kiểm tra dữ liệu người dùng nhập vào có là số hay không ?
            // Trong trường hợp không phải là số thì thông báo cho người dùng và bắt đầu lại vòng lặp.
            int choice = 0;
            try {
                // Ép kiểu của biến strChoice về int.
                choice = Integer.parseInt(strChoice);                
            } catch (java.lang.NumberFormatException e) {
                // Cần có phần lưu log lỗi ở đây.
                System.err.println("Please enter a number.");
                continue;
            }

            StudentController studentController = new StudentController();
            if (choice == 5) {
                break;
            } else {
                switch (choice) {
                    case 1:
                        // Do something. Please do something.
                        studentController.getList();
                        break;
                    case 2:
                        // Do something. Please do something.
                        studentController.addStudent();
                        break;
                    case 3:
                        // Do something. Please do something.
                        studentController.editStudent();
                        break;
                    case 4:
                        // Do something. Please do something.
                        studentController.deleteStudent();
                        break;
                    default:
                        // Do something. Please do something.
                        System.out.println("Please enter number from 1 to 5.");
                        break;
                }
            }
        }
    }
    
    
}

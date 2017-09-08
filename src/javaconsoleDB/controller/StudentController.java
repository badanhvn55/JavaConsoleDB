/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaconsoleDB.controller;

import java.util.Scanner;
import javaconsoleDB.entity.Student;
import javaconsoleDB.model.StudentModel;

/**
 *
 * @author ueda
 */
public class StudentController {
    // 1. getList.
    // 2. add.
    // 3. edit.
    // 4. delete.
    
    private StudentModel studentModel = new StudentModel();

    // Hàm này sẽ phải trả về danh sách sinh viên.
    public void getList() {
        System.out.println("Get list");
    }

    // Thêm mới sinh viên, thực hiện việc lấy dữ liệu từ người dùng,
    // validate dữ liệu đó, tiến hành lưu thông tin.
    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter student information.");
        System.out.println("Please enter name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter email: ");
        String email = scanner.nextLine();
        System.out.println("Name: " + name + ", email: " + email);
        
        // Validate dữ liệu ở đây.
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
//        student.setId(System.currentTimeMillis());
        // Lưu thông tin sinh viên vào db.
 //       studentModel.insert(student);
    }

    public void editStudent() {
        System.out.println("Edit");
    }

    public void deleteStudent() {
        System.out.println("Delete");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaconsoleDB.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javaconsoleDB.entity.Student;
import javax.sql.DataSource;

/**
 *
 * @author ueda
 */
public class StudentModel {

    private static ArrayList<Student> listStudent;

    public void getList() {

    }

    public void insert(String name, String email, String rollNumber, String className, int status) {
        try {
            Connection cnn = DAO.getConnection();
            Statement stt = cnn.createStatement();
            String sqlQuery = "insert into student(name, email, roll_number, class_name, status) values"
                    + "('"+name+"', '"+email+"', '"+rollNumber+"', '"+className+"', '"+status+"')";
            stt.execute(sqlQuery);
            System.out.println("Thành công!");
        } catch (SQLException e) {
            System.err.println("lỗi trong quá trình update dữ liệu..." + e.getMessage());
        }

    }

    public ArrayList<Student> getListStudent() {
        ArrayList<Student> listStudent = new ArrayList<>();
        try {
            Statement stt = DAO.getConnection().createStatement();
            ResultSet rs = stt.executeQuery("select * from Student");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setClassName(rs.getString("class_name"));
                student.setRollNumber(rs.getString("roll_number"));
                student.setStatus(rs.getInt("status"));
                listStudent.add(student);

            }
        } catch (SQLException e) {
            System.err.println("Lỗi trong quá trình lấy danh sách sinh viên..." + e.getMessage());
        }

        return listStudent;
    }

    private DataSource dataSource;

    public void update(String name, String email, String className, String rollNumber, int status, int id) {
        try {

            String sqlQuery = "update student set name=?, email=?, class_name=?, roll_number=?, status=? where id=?";
            PreparedStatement stm = DAO.getConnection().prepareStatement(sqlQuery);
            stm.setString(1, name);
            stm.setString(2, email);
            stm.setString(3, className);
            stm.setString(4, rollNumber);
            stm.setInt(5, status);
            stm.setInt(6, id);

            int rowsUpdated = stm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi trong quá trình update..." + e.getMessage());
        }

    }

    public void delete(String name) {
        try {
            String sqlQuery = "delete from student where name=?";
            PreparedStatement stm = DAO.getConnection().prepareStatement(sqlQuery);
            stm.setString(1, name);
            int rowsDeleted=stm.executeUpdate();
            if(rowsDeleted>0){
                System.out.println("Đã xóa thành công một sinh viên!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi trong quá trình xóa dữ liệu"+e.getMessage());
        }

    }

    public static void main(String[] args) {
        StudentModel studentModel = new StudentModel();
        ArrayList<Student> listStudent = studentModel.getListStudent();;
        //studentModel.insert("Nguyen Van Linh", "leelinh@kuto.com", "A987", "C2814G", 25);
        //studentModel.delete("Nguyen Van Linh");
        //studentModel.update("Nguyen Van Linh", "linhlee@gmail.com", "C1803G", "A789", 5, 2);
        listStudent.forEach(arr -> {
            System.out.println(arr.getId());
            System.out.println(arr.getName());
            System.out.println(arr.getEmail());
            System.out.println(arr.getClassName());
            System.out.println(arr.getRollNumber());
            System.out.println(arr.getStatus());

        });
    }

}

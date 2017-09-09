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

    public void insert(Student student) {
        try {
            Connection cnn = DAO.getConnection();
            Statement stt = cnn.createStatement();
            
            StringBuilder sqlQueryBuilder=new StringBuilder();
            sqlQueryBuilder.append("insert into student(name, email, roll_number, class_name, status) values");
            sqlQueryBuilder.append(" (");
            sqlQueryBuilder.append("'"+student.getName()+"'");
            sqlQueryBuilder.append(",");
            sqlQueryBuilder.append("'"+student.getEmail()+"'");
            sqlQueryBuilder.append(",");
            sqlQueryBuilder.append("'"+student.getRollNumber()+"'");
            sqlQueryBuilder.append(",");
            sqlQueryBuilder.append("'"+student.getStatus()+"'");
            sqlQueryBuilder.append(",");
            sqlQueryBuilder.append("'"+student.getStatus()+"'");
            sqlQueryBuilder.append(");");
            System.out.println("------Cau lenh SQL------");
            System.out.println(sqlQueryBuilder.toString());
            System.out.println("------ket thuc cau lenh SQL");
            
//            String sqlQuery = "insert into student(name, email, roll_number, class_name, status) values"
//                    + "('"+student.getName()+"', '"+student.getEmail()+"', '"+student.getRollNumber()+"', '"+student.getClassName()+"', '"+student.getStatus()+"')";
            stt.execute(sqlQueryBuilder.toString());
            System.out.println("Thành công!");
        } catch (SQLException e) {
            System.err.println("lỗi trong quá trình update dữ liệu..." + e.getMessage());
        }

    }

    public ArrayList<Student> getListStudent() {
        ArrayList<Student> listStudent = new ArrayList<>();
        try {
            Statement stt = DAO.getConnection().createStatement();
            ResultSet rs = stt.executeQuery("select * from student");
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

    public void update(Student student) {
        try {

            String sqlQuery = "update student set name=?, email=?, class_name=?, roll_number=?, status=? where id=?";
            PreparedStatement stm = DAO.getConnection().prepareStatement(sqlQuery);
            stm.setString(1, student.getName());
            stm.setString(2, student.getEmail());
            stm.setString(3, student.getClassName());
            stm.setString(4, student.getRollNumber());
            stm.setInt(5, student.getStatus());
            stm.setInt(6, student.getId());

            int rowsUpdated = stm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi trong quá trình update..." + e.getMessage());
        }

    }

    public void delete(Student student) {
        try {
            String sqlQuery = "delete from student where id=?";
            PreparedStatement stm = DAO.getConnection().prepareStatement(sqlQuery);
            stm.setInt(1, student.getId());
            int rowsDeleted=stm.executeUpdate();
            if(rowsDeleted>0){
                System.out.println("Đã xóa thành công một sinh viên!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi trong quá trình xóa dữ liệu"+e.getMessage());
        }

    }

    public static void main(String[] args) {
        Student studentUpdate1=new Student();
        studentUpdate1.setName("Nguyễn Bá Danh");
        studentUpdate1.setEmail("vodanh@master.top.co");
        studentUpdate1.setClassName("C1702G");
        studentUpdate1.setStatus(5);
        studentUpdate1.setRollNumber("A123");
        studentUpdate1.setId(4);
        StudentModel studentModel = new StudentModel();
        
        studentModel.getListStudent();
        
        //ArrayList<Student> listStudent = studentModel.getListStudent();
        //studentModel.insert();
        //studentModel.delete("Nguyen Van Linh");
        //studentModel.update("Nguyen Van Linh", "linhlee@gmail.com", "C1803G", "A789", 5, 2);
        
    }

}

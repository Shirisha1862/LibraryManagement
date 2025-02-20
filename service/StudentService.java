package service;

import databaseconnection.BookDao;
import databaseconnection.StudentDao;
import dto.Book;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class StudentService {
    
    Scanner sc=new Scanner(System.in);
    public void searchBySerialNo(Connection conn) throws SQLException{
        System.out.println("Enter Serial no of book: ");
        int srNo=sc.nextInt();
        BookDao dao=new BookDao();
        Book book=dao.getBooksBySno(conn, srNo);
        if(book!=null){
            System.out.println("=== Book details === ");
            System.out.println("Sr No : "+book.getSrNo()+" Book Name "+book.getBookName()+" Author Name: "+book.getAuthorName()); 
        }else{
            System.out.println("no Book for serial No "+srNo+ "Found");
        }
    }
   
    public void addStudent(Connection conn) throws SQLException{
        
        System.out.println("enter the student name: ");
        String studentName=sc.nextLine();
        System.out.println("enter Registration number :");
        String regNo=sc.nextLine();

        StudentDao dao=new StudentDao();
        boolean isStudentExist =dao.getStudentByRegNo(conn, regNo);
        if(isStudentExist){
            System.out.println("Student Deatails exist into our system . please save with other book  ");
            return;
        }
        dao.saveStudent(conn, studentName,regNo);
    }
    public void getAllStudents(Connection conn) throws SQLException{
        StudentDao dao=new StudentDao();
        dao.getAllStudents(conn);
    }
    
    
}


package databaseconnection;


import dto.Book;
import dto.BookingDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StudentDao {
    public boolean getStudentByRegNo(Connection conn,String regNo) throws SQLException{
        String query="select * from students where reg_num=?";
        try(PreparedStatement ps=conn.prepareStatement(query)){
            ps.setString(1, regNo);
            try(ResultSet rs=ps.executeQuery()){
                return rs.next();
            }
        }
    }

    public int getStudentByRegNo_(Connection conn,String regNo) throws SQLException{
        String query="select * from students where reg_num=?";
        try(PreparedStatement ps=conn.prepareStatement(query)){
            ps.setString(1, regNo);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
    
   
    public void saveStudent(Connection conn,String studentName,String regNo) throws SQLException{
        String query="insert into students(std_name,reg_num) values (?,?)";
        try(PreparedStatement ps=conn.prepareStatement(query)){
            ps.setString(1,studentName);
            ps.setString(2, regNo);
            

            int rows=ps.executeUpdate();

            if(rows>0){
                System.out.println("Student added succesfully.. ");
            }else{
                System.out.println("failed to add Student.. ");
            }

        }

    }

    public void getAllStudents(Connection conn) throws SQLException{
        String query="select * from students";
        System.out.println("+-----------+--------------+-------------+");
        System.out.println("|  Book Srno|  student Name| Reg no      |");
        System.out.println("+-----------+--------------+--------------+");
       
        
        try(PreparedStatement ps=conn.prepareStatement(query)){
           
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next()){
                    System.out.printf("| %-9d | %-13s | %-11s |\n", rs.getInt(1),rs.getString(2), rs.getString(3));
                    System.out.println("+-----------+--------------+--------------+");
                }
            }
        }
    }
    public void updateBookQty(Connection conn,Book book) throws SQLException{
        String query="update books set qty=? where sr_no=?";
        try(PreparedStatement ps=conn.prepareStatement(query)){
            ps.setInt(1,book.getBookQty());
            ps.setInt(2,book.getSrNo());

            int rows=ps.executeUpdate();
            if(rows>0){
                System.out.println("Book updated Succesfully ..");
            }else{
                System.out.println("failed to update book");
            }
        }
    }

    public void saveBookDetails(Connection conn,int stdId,int bookId,int qty) throws SQLException{
        String query="insert into booking_details(std_id,book_id,qty) values (?,?,?)";
        try(PreparedStatement preparedStatement=conn.prepareStatement(query)){
           
            preparedStatement.setInt(1,stdId);
            preparedStatement.setInt(2,bookId);
            preparedStatement.setInt(3,qty);
            

            int affectedRows=preparedStatement.executeUpdate();

            if(affectedRows>0){
                System.out.println("Booking details added succesfully.. ");
            }else{
                System.out.println("failed to add booking details.. ");
            }

        }

    }
    public List<BookingDetails> getBookDetailsId(Connection conn,int stdId) throws SQLException{
        String query="select * from booking_details bd "
                        +"INNER JOIN books b ON b.id=bd.book_id "+"WHERE bd.std_id=?";
           List<BookingDetails> bookingDetails=new ArrayList<>();
        
           try(PreparedStatement preparedStatement=conn.prepareStatement(query)){
            preparedStatement.setInt(1,stdId);
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                BookingDetails bookingDetail=new BookingDetails();
                bookingDetail.setAuthorName(resultSet.getString("author_name"));
                bookingDetail.setBookId(resultSet.getInt("book_id"));
                bookingDetail.setBookName(resultSet.getString("name"));
                bookingDetail.setQty(resultSet.getInt("qty"));
                bookingDetail.setStdId(resultSet.getInt("std_id"));
                bookingDetail.setSrNo(resultSet.getInt("sr_no"));
                bookingDetail.setId(resultSet.getInt("id"));
                
                bookingDetails.add(bookingDetail);
            }
           }
           return bookingDetails;
    }

    public void deleteBookingDetails(Connection conn,int id) throws SQLException{
        String query="delete from booking_details where id=?";
        try(PreparedStatement preparedStatement=conn.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        }
    }
}

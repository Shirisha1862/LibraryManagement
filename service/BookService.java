package service;

import databaseconnection.BookDao;
import databaseconnection.StudentDao;
import dto.Book;
import dto.BookingDetails;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
public class BookService{
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
    public void searchByAuthorName(Connection conn) throws SQLException{
        System.out.println("Enter Author name of book: ");
        sc.nextLine();
        String authorName=sc.nextLine();
        BookDao dao=new BookDao();
        Book book=dao.getBooksByAuthorName(conn, authorName);
        if(book!=null){
            System.out.println("=== Book details === ");
            System.out.println("Sr No : "+book.getSrNo()+" Book Name "+book.getBookName()+" Author Name: "+book.getAuthorName()); 
        }else{
            System.out.println("no Book for serial No "+authorName+ "Found");
        }
    }
    public void addBook(Connection conn) throws SQLException{
        System.out.println("Enter the serial number of the book ");
        int srNo=sc.nextInt();
        sc.nextLine();
        System.out.println("enter the book name: ");
        String bookName=sc.nextLine();
        System.out.println("enter Author name :");
        String authorName=sc.nextLine();

        System.out.println("enter the quantity :");
        int qty=sc.nextInt();
        BookDao dao=new BookDao();
        Book book=dao.getBooksBySnoOrAuthName(conn, authorName, srNo);
        if(book!=null){
            System.out.println("Book Deatails exist into our system . please save with other book");
            return;
        }
        Book input=new Book();
        input.setAuthorName(authorName);
        input.setBookName(bookName);
        input.setBookQty(qty);
        input.setSrNo(srNo);
        dao.saveBook(conn, input);
    }
    public void getAllBooks(Connection conn) throws SQLException{
        BookDao dao=new BookDao();
        List<Book> books=dao.getAllBooks(conn);
        System.out.println("+-----------+--------------+-------------++-----------+");
        System.out.println("|  Book Srno|  Name        |  Auth Name   |  qnty      ");
        System.out.println("+-----------+--------------+--------------++-----------+");
        for(Book book:books){
            System.out.printf("| %-9d | %-13s | %-11s | %-9d |\n", book.getSrNo(), book.getBookName(), book.getAuthorName(),book.getBookQty());
            System.out.println("+-----------+--------------+--------------++-----------+");
        }
    }
    public void updateBookQty(Connection conn) throws SQLException{
        System.out.println("Enter the serial number of the book ");
        int srNo=sc.nextInt();
        sc.nextLine();
        
        
        BookDao dao=new BookDao();
        Book book=dao.getBooksBySno(conn,srNo);
        if(book==null){
            System.out.println("Book not available");
        }
        System.out.println("enter the quantity :");
        int qty=sc.nextInt();

       // Book input=new Book();
        
        book.setBookQty(book.getBookQty()+qty);
        book.setSrNo(srNo);
        dao.updateBookQty(conn, book);
    }

    public void checkOutbook(Connection conn) throws SQLException{
        StudentDao dao=new StudentDao();
        System.out.println("Enter the registration number: ");
        String regNum=sc.nextLine();
        boolean isExist= dao.getStudentByRegNo(conn, regNum);
        if(!isExist){
            System.out.println("Student is not Registered. Get Registered First. ");
            return;
        }
        getAllBooks(conn);
        System.out.println("Enter Serial number of the book to be Checked out.");
        int sNo=sc.nextInt();

        BookDao bookDao=new BookDao();
        Book book=bookDao.getBooksBySno(conn, sNo);
       // System.out.println(book+" book");
        if(book==null){
            System.out.println("Book is not available ... ");
            return;
        }
         //insert into book_deatils table
         //reduce the quantity of the respective book from books table 
         book.setBookQty(book.getBookQty()-1);

         int id= dao.getStudentByRegNo_(conn, regNum);
         dao.saveBookDetails(conn, id, book.getId(),1);
         bookDao.updateBookQty(conn, book);
    }
    public void checkInBook(Connection conn) throws SQLException{
        StudentDao dao=new StudentDao();
        System.out.println("Enter the registration number: ");
        String regNum=sc.nextLine();
        boolean isExist= dao.getStudentByRegNo(conn, regNum);
        if(!isExist){
            System.out.println("Student is not Registered. Get Registered First. ");
            return;
        }
        int id= dao.getStudentByRegNo_(conn, regNum);
        List<BookingDetails> bookingDetails=dao.getBookDetailsId(conn, id);
        bookingDetails.stream().forEach(b ->System.out.println(b.srNo + "\t\t\t" +b.bookName + "\t\t\t"+ b.authorName));
        System.out.println("Enter Serial number of book to be Checked In: ");
        int sNo=sc.nextInt();
        BookingDetails filterDetails=bookingDetails.stream().filter(b ->b.getSrNo()==sNo).findAny().orElse(null);

        
        BookDao bookDao=new BookDao();
        Book book=bookDao.getBooksBySno(conn, sNo);
        book.setBookQty(book.getBookQty()+1);
        bookDao.updateBookQty(conn, book);
        dao.deleteBookingDetails(conn, filterDetails.getId());
    }
    
}
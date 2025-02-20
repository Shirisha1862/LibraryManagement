package login;
import databaseconnection.LoginDao;
import databaseconnection.connectionclass;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import service.BookService;
import service.StudentService;

public class LoginService {
    Scanner sc=new Scanner(System.in);
    public void Login() throws ClassNotFoundException,SQLException{
        System.out.println("please provide username ");
        String userName=sc.nextLine();
        System.out.println("please provide password");
        String password=sc.nextLine();
        try(Connection conn=connectionclass.getConnection()){
            LoginDao loginDao=new LoginDao();
            String userType=loginDao.doLogin(conn,userName,password);
            if(userType==null){
                System.out.println("Invalid user");
                return;
            }
            System.out.println("Login success. you logged in as a "+userType+" please select from below options " );
            if(userType.equals("admin")){
                //display admin related menu
                displayAdminMenu(conn);
                 
            }else{
                //display student realted menu
                displayStudentMenu(conn);
            }
        }
    }
    public void displayAdminMenu(Connection conn) throws SQLException{
        int choice;
        BookService bookService=new BookService();
        StudentService studentService=new StudentService();

        do{
        System.out.println("******************************************");
        System.out.println("1. Search a book");
        System.out.println("2. Add new book");
        System.out.println("3. Upgrade quantity of a book");
        System.out.println("4. Show all books");
        System.out.println("5. Register Student");
        System.out.println("6. Show all Registered Students");
        System.out.println("7. Exit from application");
        System.out.println("******************************************");

        System.err.println("please enter your choice .");
        choice=sc.nextInt();
        switch(choice){
            
        case 1:
            searchBook(conn);
            break;
        case 2:
            bookService.addBook(conn);
            break;
        case 3:
            bookService.updateBookQty(conn);
            break;
        case 4:
            bookService.getAllBooks(conn);
            break;
        case 5:
            studentService.addStudent(conn);
            break;
        case 6:
            studentService.getAllStudents(conn);
            break;
        case 7:
            System.out.println("Thank you for using Management System. ");
            break;
        default:
            System.out.println("Please select valid option. ");
        }
        }while(choice<=7);
    }
    public void displayStudentMenu(Connection conn) throws SQLException{
        int choice;
        BookService bookService=new BookService();
        StudentService studentService=new StudentService();

        do{
        System.out.println("******************************************");
        System.err.println("1. Search a book");
        System.out.println("2. check out book");
        System.out.println("3. check in book");
        System.out.println("4. Exit from application");
        System.out.println("******************************************");

        System.out.println("please enter your choice .");
        choice=sc.nextInt();
        switch(choice){
            
        case 1:
            searchBook(conn);
            break;
        case 2:
            bookService.checkOutbook(conn);
            break;
        case 3:
            bookService.checkInBook(conn);
            break;
        case 4:
            System.out.println("Thank you for using Management System. ");
            System.exit(0);
            break;
        default:
            System.out.println("Please select valid option. ");
        }
        }while(choice!=4);
    }

    private void searchBook(Connection conn) throws SQLException{
        BookService bookService=new BookService();
        int choice;
        System.out.println(" 1. Search with Book Serial no. ");
        System.out.println(" 2. Search with Book 's author name. ");
        System.err.println("please enter your choice .");

        choice=sc.nextInt();
        switch(choice){
        case 1:
            bookService.searchBySerialNo(conn);
            break;
        case 2:
            bookService.searchByAuthorName(conn);
        
    }
}
}

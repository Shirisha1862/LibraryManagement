
package librarymanagementsys;
import java.sql.SQLException;
import login.LoginService;

public class libraryManagementSystem { 
    public static void main(String[] args) {
        System.out.println("**********welcome to college library********");
        System.out.println("Please do login first for accessing menu");
        LoginService loginService = new LoginService();  // ✅ Create instance
        try {
            loginService.Login();  // ✅ Call the login method
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();  // Handle errors
        }
    }
}
 
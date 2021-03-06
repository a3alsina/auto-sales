package db;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


/**
 * Logs users into application.
 *
 * @author RayDeveloper
 */
public class LoginManager {
    
    private static final Connection CONN = ConnectionUtil.getInstance().getConnection(); 
    
    public static boolean login(String user, String pass) throws SQLException{

        //get all users that match user/password combo
        String query = "SELECT * FROM Users WHERE USERNAME = ? AND PASSWORD = ?";
        ResultSet rs = null; 

        //try with resources
        try (
                PreparedStatement stmt = CONN.prepareStatement(query,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            
            stmt.setString(1, user);
            stmt.setString(2, pass); 
            
            rs = stmt.executeQuery(); 

            //if not found return false
            if(!rs.isBeforeFirst()){
                
                return false;
                
            }else{
                
                return true;
            
            }

        }

    }
    
}

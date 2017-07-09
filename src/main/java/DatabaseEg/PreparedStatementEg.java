package DatabaseEg;

/**
 * Created by ADMIN on 7/9/17.
 */
import java.sql.*;

public class PreparedStatementEg {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/alphaa";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            //STEP 2: Register and Load JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection to database server
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.prepareStatement("insert into students values(?, ?, ?)");

            stmt.setInt(1, 20);

            stmt.setString(2, "Kalyan");
            stmt.setInt(3, 28);

            int cnt = stmt.executeUpdate();
            if(cnt>0)
            {
                System.out.println("Successfully inserted...");
            }
            else
            {
                System.out.println("Error inserting...");
            }


            //STEP 6: Clean-up environment
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
        }//end try
        System.out.println("Done...");
    }//end main
}



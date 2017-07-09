package DatabaseEg;

/**
 * Created by ADMIN on 7/9/17.
 DELIMITER $$
 CREATE PROCEDURE getStdntName1(IN stdntid BIGINT, OUT stdntname VARCHAR(500))
 BEGIN
 SELECT name INTO stdntname FROM students WHERE id=stdntid;
 END $$
 DELIMITER ;

 */
//STEP 1. Import required packages
import java.sql.*;

public class CallableStatementDemo {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";


    static final String DB_URL = "jdbc:mysql://localhost/alphaa";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String sql = "{call getStdntName1(?, ?)}";//? is place holder for IN and OUT parameters
            stmt = conn.prepareCall(sql);

            //Bind IN parameter first, then bind OUT parameter
            int student_id = 41;
            stmt.setInt(1/*parameter number*/, student_id); // This would set ID
            // Because second parameter is OUT so register it
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);

            //Use execute method to run stored procedure.
            System.out.println("Executing stored procedure..." );
            stmt.execute(); //now stored procedure gets executed in the database

            //Retrieve student name with getXXX method
            String student_name = stmt.getString(2);//2 is OUT parameter number


            System.out.println("Student with ID:" +student_id + " is " + student_name);
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
                conn.close();
            }catch(SQLException se2){
            }// nothing we can do
        }//end try
        System.out.println("Done!");
    }//end main
}//end JDBCExample

package DatabaseEg;

import java.sql.*;

public class DatabaseApp {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/alphaa";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register and Load JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection to database server
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String isql = "insert into students values(10, 'Pavan', 34)";
            int cnt = stmt.executeUpdate(isql);
            if(cnt>0)
            {
                System.out.println("Successfully inserted...");
            }
            else
            {
                System.out.println("Error inserting...");
            }
            String sql;
            sql = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                //Can retrieve values with column index also eg. getInt(int column_index);
                int sid=0;

                sid  = rs.getInt("id");

                String sname = rs.getString("name");

                int smarks = rs.getInt("marks");

                //Display values
                System.out.print("ID: " + sid);
                System.out.print(", name: " + sname);
                System.out.println(", marks: " + smarks);

                //printDisplayDirection(rs);
            }

            //STEP 6: Clean-up environment
            rs.close();
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

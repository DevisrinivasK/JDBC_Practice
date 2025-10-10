import java.sql.*;
public class ResultSetMetaDataExample {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/Employee";
    private static final String user = "root";
    private static final String password = "mySqlAdmin#@$3";
    public static void main(String[] args){
        try(Connection conn=DriverManager.getConnection(url,user,password)){
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM Employee");
            ResultSetMetaData rsMeta=rs.getMetaData();
            System.out.println("<-------------ResultSet Meta Data------------->");
            System.out.println("Number of columns in the table are: "+rsMeta.getColumnCount());
            System.out.println("The Columns in the table are: ");
            for(int i=1;i<=rsMeta.getColumnCount();i++){
                System.out.println(rsMeta.getColumnName(i));
            }
            stmt.close();
            rs.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

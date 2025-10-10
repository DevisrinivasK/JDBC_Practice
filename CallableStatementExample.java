import java.sql.*;
public class CallableStatementExample {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/Employee";
    private static final String user = "root";
    private static final String password = "mySqlAdmin#@$3";
    public static void main(String[] args){
        try(Connection connection=DriverManager.getConnection(url,user,password)){
            // Prepare the callable statement to execute stored procedure
            CallableStatement cs=connection.prepareCall("{ call getAllEmployees()}");
            ResultSet rs = cs.executeQuery();

            // Print the retrieved employee data
            System.out.println("Employee Details:");
            System.out.println("----------------------------");
            System.out.println("EmployeeName\tDepartment\tSalary");
            while (rs.next()) {
                System.out.println( rs.getString("ename") + "\t" + rs.getString("dept")+"\t" +rs.getInt("salary"));
            }

            // Close the ResultSet and CallableStatement
            rs.close();
            cs.close();

            System.out.println("----------------------------");
            System.out.println("Stored procedure executed successfully!");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

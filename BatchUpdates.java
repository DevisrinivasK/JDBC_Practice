import java.sql.*;
import java.util.Scanner;
import java.lang.Math;
public class BatchUpdates {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/Employee";
    private static final String user = "root";
    private static final String password = "mySqlAdmin#@$3";
    static final String query="INSERT INTO Employee VALUES(?,?,?)";
    public static void main(String[] args)  {
        Scanner scanner=new Scanner(System.in);
        String[] names={"Ram","Krishna","Radha","Arjuna","Madhava","Pardha","Balarama","Srinivasa","Sita","Siva","Parvathi"};
        String[] dept={"IT","Sales","HR","SocialMedia"};
        int[] Salaries={20000, 25000, 30000, 15000};
        try(Connection connection=DriverManager.getConnection(url,user,password);
        PreparedStatement ps=connection.prepareStatement(query)){
            connection.setAutoCommit(false);
            //Inserting 50 fake employees
            try{
                for(int i=1;i<=50;i++){
                    int nameIndex=(int)(Math.random()*names.length);
                    int deptIndex=(int)(Math.random()*dept.length);
                    int salariesIndex=(int)(Math.random()*Salaries.length);
                    ps.setString(1,names[nameIndex]);
                    ps.setString(2,dept[deptIndex]);
                    ps.setInt(3,Salaries[salariesIndex]);
                    ps.addBatch();
                }
                ps.executeBatch();
                connection.commit();
                System.out.println("Batch update done successfully");
            }catch(Exception e){
                connection.rollback();
                System.out.println(e.getMessage());
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

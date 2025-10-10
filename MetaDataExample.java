import java.sql.*;
public class MetaDataExample {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/Employee";
    private static final String user = "root";
    private static final String password = "mySqlAdmin#@$3";
    public static void main(String[] args){
        try(Connection conn=DriverManager.getConnection(url,user,password)){
            DatabaseMetaData dbMetadata=conn.getMetaData();
            System.out.println("Database Product Name: " + dbMetadata.getDatabaseProductName());
            System.out.println("Database Product Version: " + dbMetadata.getDatabaseProductVersion());
            System.out.println("Database Driver Name: " + dbMetadata.getDriverName());
            System.out.println("Database Driver Version: " + dbMetadata.getDriverVersion());
            System.out.println("Database Support transactions: " + dbMetadata.supportsTransactions());
            System.out.println("Database URL: " + dbMetadata.getURL());
            System.out.println("Database User: " + dbMetadata.getUserName());
            // List all tables
            ResultSet tables = dbMetadata.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Tables in the Database:");
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

import java.sql.*;
import java.util.Scanner;

public class CRUDUsingStatement{
    private static final String url = "jdbc:mysql://127.0.0.1:3306/Student";
    private static final String user = "root";
    private static final String password = "mySqlAdmin#@$3";

    public static void main(String[] args) {
        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establish Database connection
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database Connected Successfully!");

            Statement statement = connection.createStatement();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n------ MENU ------");
                System.out.println("1. Insert Student");
                System.out.println("2. Update Student");
                System.out.println("3. Delete Student");
                System.out.println("4. Display Students");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1: // INSERT
                        System.out.print("Enter Student ID: ");
                        int sid = sc.nextInt();
                        sc.nextLine(); // consume newline
                        System.out.print("Enter Student Name: ");
                        String sname = sc.nextLine();

                        String insertQuery = "INSERT INTO Student (sid, sname) VALUES (" + sid + ", '" + sname + "')";
                        int rowsInserted = statement.executeUpdate(insertQuery);
                        System.out.println(rowsInserted + " row(s) inserted.");
                        break;

                    case 2: // UPDATE
                        System.out.print("Enter Student ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new Student Name: ");
                        String newName = sc.nextLine();

                        String updateQuery = "UPDATE Student SET sname = '" + newName + "' WHERE sid = " + updateId;
                        int rowsUpdated = statement.executeUpdate(updateQuery);
                        System.out.println(rowsUpdated + " row(s) updated.");
                        break;

                    case 3: // DELETE
                        System.out.print("Enter Student ID to delete: ");
                        int deleteId = sc.nextInt();

                        String deleteQuery = "DELETE FROM Student WHERE sid = " + deleteId;
                        int rowsDeleted = statement.executeUpdate(deleteQuery);
                        System.out.println(rowsDeleted + " row(s) deleted.");
                        break;

                    case 4: // DISPLAY
                        String selectQuery = "SELECT * FROM Student";
                        ResultSet rs = statement.executeQuery(selectQuery);
                        System.out.println("SID\tSNAME");
                        System.out.println("------------------");
                        while (rs.next()) {
                            int id = rs.getInt("sid");
                            String name = rs.getString("sname");
                            System.out.println(id + "\t" + name);
                        }
                        break;

                    case 5: // EXIT
                        System.out.println("Exiting Program. Goodbye!");
                        sc.close();
                        statement.close();
                        connection.close();
                        System.exit(0);

                    default:
                        System.out.println(" Invalid choice! Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
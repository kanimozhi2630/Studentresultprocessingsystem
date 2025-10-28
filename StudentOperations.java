import java.sql.*;
import java.util.Scanner;

public class StudentOperations {

    static Scanner sc = new Scanner(System.in);

    
    public static void addStudent() {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return;

            System.out.print("Enter Roll No: ");
            String roll_no = sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks for AI: ");
            int ai = sc.nextInt();
            System.out.print("Enter Marks for JAVA: ");
            int javaMarks = sc.nextInt();
            System.out.print("Enter Marks for DBMS: ");
            int dbms = sc.nextInt();
            System.out.print("Enter Marks for DAA: ");
            int daa = sc.nextInt();
            System.out.print("Enter Marks for MATHS: ");
            int maths = sc.nextInt();
            sc.nextLine(); 

            int total = ai + javaMarks + dbms + daa + maths;
            float average = total / 5.0f;
            String grade = calculateGrade(average);

            String sql = "INSERT INTO students (roll_no, name, AI, JAVA, DBMS, DAA, MATHS, total, average, grade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roll_no);
            ps.setString(2, name);
            ps.setInt(3, ai);
            ps.setInt(4, javaMarks);
            ps.setInt(5, dbms);
            ps.setInt(6, daa);
            ps.setInt(7, maths);
            ps.setInt(8, total);
            ps.setFloat(9, average);
            ps.setString(10, grade);

            ps.executeUpdate();
            System.out.println("Student Added Successfully");

            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public static void viewAllStudents() {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return;

            String sql = "SELECT * FROM students";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

         
            System.out.printf("%-3s %-8s %-15s %-4s %-4s %-4s %-4s %-5s %-5s %-7s %-5s\n",
                    "ID", "RollNo", "Name", "AI", "JAVA", "DBMS", "DAA", "MATHS", "Total", "Avg", "Grade");
            System.out.println("-------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-3d %-8s %-15s %-4d %-4d %-4d %-4d %-5d %-5d %-7.2f %-5s\n",
                        rs.getInt("id"),
                        rs.getString("roll_no"),
                        rs.getString("name"),
                        rs.getInt("AI"),
                        rs.getInt("JAVA"),
                        rs.getInt("DBMS"),
                        rs.getInt("DAA"),
                        rs.getInt("MATHS"),
                        rs.getInt("total"),
                        rs.getFloat("average"),
                        rs.getString("grade"));
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static void searchStudent() {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return;

            System.out.print("Enter Roll No to search: ");
            String roll_no = sc.nextLine();

            String sql = "SELECT * FROM students WHERE roll_no = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roll_no);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.printf("%-3s %-8s %-15s %-4s %-4s %-4s %-4s %-5s %-5s %-7s %-5s\n",
                        "ID", "RollNo", "Name", "AI", "JAVA", "DBMS", "DAA", "MATHS", "Total", "Avg", "Grade");
                System.out.println("-------------------------------------------------------------------------------");
                System.out.printf("%-3d %-8s %-15s %-4d %-4d %-4d %-4d %-5d %-5d %-7.2f %-5s\n",
                        rs.getInt("id"),
                        rs.getString("roll_no"),
                        rs.getString("name"),
                        rs.getInt("AI"),
                        rs.getInt("JAVA"),
                        rs.getInt("DBMS"),
                        rs.getInt("DAA"),
                        rs.getInt("MATHS"),
                        rs.getInt("total"),
                        rs.getFloat("average"),
                        rs.getString("grade"));
            } else {
                System.out.println("Student Not Found");
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public static void updateMarks() {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return;

            System.out.print("Enter Roll No to update: ");
            String roll_no = sc.nextLine();

            System.out.print("Enter new Marks for AI: ");
            int ai = sc.nextInt();
            System.out.print("Enter new Marks for JAVA: ");
            int javaMarks = sc.nextInt();
            System.out.print("Enter new Marks for DBMS: ");
            int dbms = sc.nextInt();
            System.out.print("Enter new Marks for DAA: ");
            int daa = sc.nextInt();
            System.out.print("Enter new Marks for MATHS: ");
            int maths = sc.nextInt();
            sc.nextLine();

            int total = ai + javaMarks + dbms + daa + maths;
            float average = total / 5.0f;
            String grade = calculateGrade(average);

            String sql = "UPDATE students SET AI=?, JAVA=?, DBMS=?, DAA=?, MATHS=?, total=?, average=?, grade=? WHERE roll_no=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ai);
            ps.setInt(2, javaMarks);
            ps.setInt(3, dbms);
            ps.setInt(4, daa);
            ps.setInt(5, maths);
            ps.setInt(6, total);
            ps.setFloat(7, average);
            ps.setString(8, grade);
            ps.setString(9, roll_no);

            int rows = ps.executeUpdate();
            if(rows > 0) {
                System.out.println("Student Marks Updated Successfully!");
            } else {
                System.out.println("Student Not Found!");
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public static void deleteStudent() {
        try {
            Connection con = DBConnection.getConnection();
            if (con == null) return;

            System.out.print("Enter Roll No to delete: ");
            String roll_no = sc.nextLine();

            String sql = "DELETE FROM students WHERE roll_no=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roll_no);

            int rows = ps.executeUpdate();
            if(rows > 0) {
                System.out.println("Student Deleted Successfully");
            } else {
                System.out.println("Student Not Found!");
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public static String calculateGrade(float avg) {
        if(avg >= 90) return "A+";
        else if(avg >= 80) return "A";
        else if(avg >= 70) return "B+";
        else if(avg >= 60) return "B";
        else return "F";
    }
}

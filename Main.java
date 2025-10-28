import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\n--- Student Result System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Marks");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch(choice){
                case 1: StudentOperations.addStudent(); break;
                case 2: StudentOperations.viewAllStudents(); break;
                case 3: StudentOperations.searchStudent(); break;
                case 4: StudentOperations.updateMarks(); break;
                case 5: StudentOperations.deleteStudent(); break;
                case 6: System.out.println("Exiting..."); System.exit(0);
                default: System.out.println("Invalid Choice!");
            }
        }
    }
}

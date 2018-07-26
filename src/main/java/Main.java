/**
 * Created by carlosballadares on 2018-07-25.
 */
public class Main {
    public static void main(String[] args){

        ManageEmployee ME = new ManageEmployee();
        Integer id = ME.addEmployee("hello", "world", 10);
        System.out.println("New employye with id: "+id);
        // ME.listEmployees();
        // Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
        // Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
        // Integer empID3 = ME.addEmployee("John", "Paul", 10000);
  
        // /* List down all the employees */
        // ME.listEmployees();
  
        // /* Update employee's records */
        // ME.updateEmployee(empID1, 5000);
  
        // /* Delete an employee from the database */
        // ME.deleteEmployee(empID2);
  
        // /* List down new list of the employees */
        // ME.listEmployees();


        System.exit(0);
    }
}

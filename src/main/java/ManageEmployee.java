import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by carlosballadares on 2018-07-25.
 */
public class ManageEmployee {
    private static SessionFactory factory;

    public ManageEmployee() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Integer addEmployee(String fName, String lName, int salary){
        Session session = factory.openSession();
        Transaction tx=null;
        Integer employeeId= null;

        try{
            tx = session.beginTransaction();
            Employee em = new Employee(fName, lName, salary);
            employeeId = (Integer) session.save(em);
            tx.commit();

        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }

        return employeeId;
    }
    @SuppressWarnings("unchecked")
    public void listEmployees(){
      Session session = factory.openSession();
      Transaction tx = null;

      try {
        tx = session.beginTransaction();
        List<Employee> employees = session.createQuery("FROM Employee").list();
        for(Iterator<Employee> it = employees.iterator(); it.hasNext();){
          Employee e = it.next();
          System.out.println("First Name: "   + e.getFirstName());
          System.out.println("Last Name: "    + e.getLastName());
          System.out.println("Salary Name: "  + e.getSalary());
        }
        tx.commit();
      } catch (HibernateException e){
        if(tx!=null){ tx.rollback(); }
        e.printStackTrace();
      } finally {
        session.close();
      }
    }

    public void updateEmployee(Integer id, int salary){
      Session session = factory.openSession();
      Transaction tx = null;
      
      try {
        tx = session.beginTransaction();
        Employee em =(Employee) session.get(Employee.class, id);
        em.setSalary(salary);
        session.update(em);
        tx.commit();
      } catch (HibernateException e){
        if(tx!=null){ tx.rollback();}
        e.printStackTrace();
      } finally {
        session.close();
      }
    }

    public void deleteEmployee(Integer id){
      Session session = factory.openSession();
      Transaction tx = null;
      // Defina variables here
      try {
        tx = session.beginTransaction();
        Employee em = (Employee)session.get(Employee.class, id);
        session.delete(em);
        tx.commit();
      } catch (HibernateException e){
        if(tx!=null){ tx.rollback(); }
        e.printStackTrace();
      } finally {
        session.close();
      }
    }
}

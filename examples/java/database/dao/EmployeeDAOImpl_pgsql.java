package com.revature.database.dao;

import com.revature.database.models.Employee;
import com.revature.database.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl_pgsql implements EmployeeDAO {
    @Override
    public Employee selectEmployeeByName(String name) {
        PreparedStatement ps = null;
        Employee emp = null;
        // '?' are parameters (placeholders) for data we will insert into our prepared statements later...

        /*
         * SQL Injection:
         *      " ''; DROP examples.employees; "
         */
        String query = "SELECT * FROM examples.employees WHERE emp_name=?";

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(query);

            // the PreparedStatement 'set' methods allow you to pass values into
            // the placeholders ('?') in your query
            // Here, we know the name should be a string, so we use setString
            // The first argument of the PrepareStatement 'set' methods should
            // be an integer which relates to the placeholder we are replacing
            ps.setString(1, name);

            // If we had a second '?' expecting a string, it could look like this
//            ps.setString(2, "Developer");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                emp = new Employee (
                        rs.getInt("emp_id"),
                        rs.getString("emp_name"),
                        rs.getString("emp_title"),
                        rs.getDouble("emp_salary")
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return emp;
    }

    @Override
    public List<Employee> selectAllEmployees() {
        /*
         * This implementation showcases how a Simple Statement
         * Object works - this is used because there is no
         * fear of SQL injection (the query takes no user input)
         */
        Statement stmt = null;
        List<Employee> employees = new ArrayList<Employee>();

        String query = "SELECT * FROM examples.employees";

        try (Connection conn = ConnectionUtil.getConnection()){
            // Our Connection object creates the Statements
            stmt = conn.createStatement();

            // use stmt.execute to Execute our query!
            stmt.execute(query);

            // After executing our query, we can access the result set, which will
            // be stored in our Simple Statement using 'getResultSet'
            ResultSet rs = stmt.getResultSet();

            // We have a while-loop below, because we are selecting all Employees
            // from our database. This means the ResultSet should contain multiple records!
            while (rs.next()) {
                employees.add(
                        new Employee (
                                rs.getInt("emp_id"),
                                rs.getString("emp_name"),
                                rs.getString("emp_title"),
                                rs.getDouble("emp_salary")
                        )
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return employees;

    }

    @Override
    public Boolean insertIntoEmployees(Employee emp) {
        PreparedStatement ps = null;
        String query = "INSERT INTO examples.employees VALUES(NULL,?,?,?)";

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(query);

            ps.setString(1, emp.getEmp_name());
            ps.setString(2, emp.getEmp_title());
            ps.setDouble(3, emp.getEmp_salary());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Boolean updateEmployeeByName(String name, Employee emp) {
        PreparedStatement ps = null;
        String query = "UPDATE examples.employees SET emp_name=?, emp_title=?, emp_salary=? WHERE emp_name=?";

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(query);

            ps.setString(1, emp.getEmp_name());
            ps.setString(2, emp.getEmp_title());
            ps.setDouble(3, emp.getEmp_salary());
            ps.setString(4, name);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Boolean deleteEmployeeByName(String name) {
        PreparedStatement ps = null;
        String query = "DELETE FROM examples.employees WHERE emp_name=?";

        try (Connection conn = ConnectionUtil.getConnection()) {
            ps = conn.prepareStatement(query);

            // Before deleting, it is best practice to perform a search using the
            // same where clause to make sure we are not potentially deleting records
            // that we are not supposed to... (that would be logic for your service layer...)

            ps.setString(1, name);

            int rowsAffected = ps.executeUpdate();

            // executeUpdate() returns the number of rows affected --
            // 0 means no employee with that id existed to delete
            if (rowsAffected == 0) {
                return false;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }

        return true;
    }
}
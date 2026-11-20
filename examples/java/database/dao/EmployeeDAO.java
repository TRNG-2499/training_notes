package com.revature.database.dao;

import com.revature.database.models.Employee;
import java.util.List;

/*
 * DAO (Data Access Object) is a design pattern wherein we provide a common
 * interface for potentionally varying implementations to different databases
 *
 * For instance:
 *      We can create an implemetation of this interface that uses PostgreSQL & JDBC
 *      and a second implementation which uses MongoDB& MongoDriver!
 *
 * Repository Layers in your application are responsible for sending queries and
 * receiving results to/from our databases
 */
public interface EmployeeDAO {
    // If you want to provide more flexibility with respect to
    // JDBC transactions, you could either:
    // 1. Overload methods that might need auto-commit (default) vs non-auto-commit; or
    // 2. just take in connection for all methods
    // Note: this functionality should belong to a service layer

    public Employee selectEmployeeByName(String name);
    public List<Employee> selectAllEmployees();

    public Boolean insertIntoEmployees(Employee emp);
    public Boolean updateEmployeeByName(String name, Employee emp);
    public Boolean deleteEmployeeByName(String name);
}

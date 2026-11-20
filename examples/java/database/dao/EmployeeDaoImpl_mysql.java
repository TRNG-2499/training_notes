package com.revature.database.dao;

import com.revature.database.models.Employee;

import java.util.List;

/*
 * The DAO Interfaces gives you a common set of methods that you could
 * implement differently (for instance providing a slightly
 * different implementation for a mysql server, instead of postgresql
 *
 * This could be useful if you are creating a library for your project/
 * company wherein different teams use different databases during development/production
 * for example
 */
public class EmployeeDaoImpl_mysql implements EmployeeDAO {
    @Override
    public Employee selectEmployeeByName(String name) {
        return null;
    }

    @Override
    public List<Employee> selectAllEmployees() {
        return List.of();
    }

    @Override
    public Boolean insertIntoEmployees(Employee emp) {
        return null;
    }

    @Override
    public Boolean updateEmployeeByName(String name, Employee emp) {
        return null;
    }

    @Override
    public Boolean deleteEmployeeByName(String name) {
        return null;
    }
}

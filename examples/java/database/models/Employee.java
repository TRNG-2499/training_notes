package com.revature.database.models;

/*
 * This is our Model class used as our mechanism for providing context
 * for records from RDBMS (Postgre) within our Java Application.
 *
 * Essentially, this class should model a single Row that would be
 * inserted/queried for in our RDBMS.
 *
 * Notice that we are matching the names of the columns to the fields
 * in this class, which is intentional (this is why in RDBMS you will
 * commonly see names using camel casing...)
 *
 * Note: I intentionally am NOT using camel casing - we'll see why
 * when we get to Spring! (we can still map column names in RDBMS to
 * a model in Spring, it just takes another step).
 */
public class Employee {
    private Integer emp_id;
    private String emp_name;
    private String emp_title;
    private Double emp_salary;

    public Employee() { }

    // Used by PostgreSQL DAO implementation - Ids from Relational DB is always an Integer
    public Employee(Integer emp_id, String emp_name, String emp_title, Double emp_salary) {
        super();
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_title = emp_title;
        this.emp_salary = emp_salary;
    }

    public Integer getEmp_id() {
        return emp_id;
    }
    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }
    public String getEmp_name() {
        return emp_name;
    }
    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
    public String getEmp_title() {
        return emp_title;
    }
    public void setEmp_title(String emp_title) {
        this.emp_title = emp_title;
    }
    public Double getEmp_salary() {
        return emp_salary;
    }
    public void setEmp_salary(Double emp_salary) {
        this.emp_salary = emp_salary;
    }
    @Override
    public String toString() {
        return "Employee [emp_id=" + emp_id +
                ", emp_name=" + emp_name +
                ", emp_title=" + emp_title +
                ", emp_salary="+ emp_salary + "]";
    }

    // should implement equals() and hashcode() as well...
}

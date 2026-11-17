/*
 * View : An entity which effectively acts as a virtual table. It allows
 * you to apply an identifier to a query statement and refer to the named
 * view instead of the actual query. These are typically used to reference
 * commonly executed queries on a database for easier/faster retrieval. 
 * 
 * Syntax:
 * 
 * CREATE [OR REPLACE] VIEW <view_name> AS <query>
 */
CREATE OR REPLACE VIEW high_salaries AS
SELECT * FROM examples.employees
WHERE emp_salary > ( SELECT AVG(emp_salary) FROM examples.employees );


-- You may reference a view similar to referencing a traditional table
SELECT * FROM HIGH_SALARIES; -- Start

-- Modification on examples.employees by another transaction

SELECT * FROM HIGH_SALARIES; -- Later


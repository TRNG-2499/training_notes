/* Custom Functions and Stored Procedures provide you with additional, custom
 * Functionality that can be used database side.
 * 
 * Note: Complex functions and buisiness logic should be handled by your backend
 * or web server. Your RDBMS is not optimized to handle programatic logic.
 *
 * Stored procedures and functions can help reduce the number of queries that
 * need to be sent to your database, if designed correctly.
 */

/*
 * Commonly when creating functional entities (functions, views, sequences, etc...)
 * the CREATE, OR and REPLACE keywords can be used together:
 * CREATE OR REPLACE <entity> ...
 *
 * The REPLACE keyword is used to change the statements of an entity without breaking
 * any existing rules (DCL permissions, triggers/views that rely on the function, etc...)
 *	i.e. if you drop a function, then create a new one of the same name, you will have to
 *		update all locations which reference the function...
 */
CREATE OR REPLACE FUNCTION examples.count_emp_salary_range(
	min_sal INTEGER, max_sal INTEGER)
	RETURNS INTEGER AS 
	$$
	DECLARE 
		sal_count INTEGER;
	BEGIN 
		SELECT COUNT(*) INTO sal_count FROM examples.EMPLOYEES
		WHERE emp_salary BETWEEN min_sal AND max_sal;
		RETURN sal_count;		
	END
	$$ LANGUAGE PLPGSQL;

-- Use the select statement to call a function
SELECT examples.count_emp_salary_range(90000, 160000);
select * from examples.employees;

/*
 * Creating Sequences allow for more control over the automatic
 * scaling of a value - can be used with ID (effectively our own
 * custom 'SERIAL')
 * 
 * Syntax:
 * 
 * CREATE SEQUENCE <sequence_name> 
 * 	START WITH <value> INCREMENT | DECREMENT BY <amount>
 */
CREATE SEQUENCE IF NOT EXISTS examples.emp_id_seq START WITH 2000 INCREMENT BY 1;

/*
 * NOTE: You can create something called a 'Trigger Function' as well.
 * A Trigger Function is a Function which returns a Trigger, and CAN
 * be used with Transactional CRUD operations. However, it must still
 * be called with a trigger.
 */
/* Keywords that can be used in a Trigger Function
 * NEW - Reference the the new record to be created
 * OLD - Reference the the old record that is being changed
 */
CREATE OR REPLACE FUNCTION examples.emp_id_func()
	RETURNS TRIGGER AS
$$ BEGIN
	IF NEW.emp_id IS NULL THEN
		NEW.emp_id:=NEXTVAL('examples.emp_id_seq');
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;

/*
 * Triggers : An entity that can perform some operation (typically invoking
 * a stored procedure or function) in response to another operation which
 * is called.  
 */
CREATE TRIGGER emp_id_trig
BEFORE INSERT ON examples.employees
FOR EACH ROW 
EXECUTE FUNCTION examples.emp_id_func();


-- Use Drop to remove a Trigger
DROP TRIGGER IF EXISTS emp_id_trig ON examples.employees;

-- This insert statement triggers the emp_id_trig, 
-- which in turn calls the emp_id_func()
INSERT INTO examples.employees (emp_name, emp_title, emp_salary) VALUES ('Rob', 'Developer', 97000);

select * from examples.employees;



/* ALTER SEQUENCE to reset starting value (2000)
 * You can also specify a value to restart at: ALTER SEQUENCE <sequence_name> RESTART WITH <some_value>
 */
-- ALTER SEQUENCE examples.emp_id_seq RESTART;
-- ALTER SEQUENCE examples.emp_id_seq RESTART WITH 2001;

-- The example below showcases a workflow to create then use a custom sequence with the initial table DDL:
CREATE SEQUENCE IF NOT EXISTS login_id_seq START WITH 10000 INCREMENT BY 1;

/* You may reference a created sequence by name using the 'DEFAULT'
 * keyword within a DDL table creation statement
 *
 * DEFAULT is used to set the 'default' value, if the value is either not supplied, or the 'DEFAULT' keyword
 * is used in a DML Insert statement.
 */
CREATE TABLE user_login (
	login_id INTEGER DEFAULT nextval('login_id_seq') PRIMARY KEY,
	login_name VARCHAR(200),
	login_pass VARCHAR(200)
);

/*
 * Stored Procedure : An entity, similar to a function, which can perform
 * transactional operations (INSERT/UPDATE/DELETE), but DOES NOT return data
 */
CREATE OR REPLACE PROCEDURE examples.emp_percent_raise(
	percent_inc DECIMAL, id INT
) LANGUAGE PLPGSQL
AS $$
BEGIN 
	UPDATE examples.EMPLOYEES 
	SET emp_salary = emp_salary * percent_inc
	WHERE emp_id = id;
COMMIT;
END $$

-- Invoke a stored procedure using the keyword 'Call'
CALL examples.emp_percent_raise (1.1, 1000);
SELECT * FROM examples.employees WHERE emp_id = 1000;

-- To drop a procedure
DROP PROCEDURE examples.emp_percent_raise;
/*
 * the SELECT statement allows you to retrieve data from your DB.
 * When using SELECT you can specify the exact columns (and apply
 * functionality throughout the entire search).
 * 
 * the Select operation is non-transactional (we do not effect the data in our
 * table even if we apply some other functionality such as a search for averages.
 * 
 * the select operation will return a 'resultset'
 * 
 * SYNTAX:
 * SELECT <column_name>... FROM <table_name> [WHERE...] [GROUP BY] [HAVING] [ORDER BY]
 */ 
-- The '*' is a wildcard and specifies all columns in a select statement
SELECT * FROM examples.EMPLOYEES;

-- WHERE : allow for a filter on which values to return in our Resultset
SELECT emp_name, emp_title FROM EXAMPLES.EMPLOYEES;
SELECT emp_name, emp_title FROM EXAMPLES.EMPLOYEES WHERE EMP_TITLE='Developer';

-- GROUP BY : allows FOR the RESULT SET TO GROUP the VALUES based ON a COLUMN that IS specified
SELECT emp_title FROM EXAMPLES.EMPLOYEES GROUP BY EMP_TITLE;

-- HAVING : a secondary aggregation/filter of data based on the grouping of information that is specifed earlier in the query
SELECT emp_title, SUM(emp_salary) FROM EXAMPLES.EMPLOYEES
GROUP BY EMP_TITLE;

SELECT emp_title, SUM(emp_salary) FROM EXAMPLES.EMPLOYEES
GROUP BY EMP_TITLE HAVING SUM(EMP_SALARY) > 600000;

-- ORDER BY : allows you TO specify how the DATA IS displayed WITHIN the resultset
SELECT * FROM EXAMPLES.EMPLOYEES;
SELECT * FROM EXAMPLES.EMPLOYEES ORDER BY EMP_SALARY DESC;

/* ALL TOGETHER: */
SELECT ROUND(AVG(emp_salary), 2), emp_title FROM EXAMPLES.EMPLOYEES
WHERE EMP_SALARY < 130000 GROUP BY EMP_TITLE
HAVING avg(EMP_SALARY) > 100000 ORDER BY EMP_TITLE ASC;

/*
 * ALIAS : an alias assigns a temporary label to a column or table within
 * a query, used to make the resultset more readable or to shorten a long
 * table/schema reference. Aliases exist only for the duration of the query
 * and do not rename anything in the database.
 * 
 * SYNTAX:
 *   Column alias : SELECT <column_name> AS <alias>
 *   Table alias  : SELECT <alias>.<column_name> FROM <table_name> AS <alias>
 * 
 * The AS keyword is optional in PostgreSQL — the alias can be written
 * directly after the column or table name — however AS is recommended
 * for clarity and readability.
 */
-- Column alias: rename the resultset column headers for readability
SELECT emp_name AS "Employee Name", emp_title AS "Job Title", emp_salary AS "Salary"
FROM EXAMPLES.EMPLOYEES;

-- Alias on an aggregate: without an alias, PostgreSQL labels the column 'sum'
-- which is not descriptive. An alias gives the result column a meaningful name.
SELECT emp_title AS "Job Title", sum(emp_salary) AS "Total Salary"
FROM EXAMPLES.EMPLOYEES
GROUP BY EMP_TITLE;

-- Table alias: shortens repeated references to the schema-qualified table name
-- NOTE: In this example, using a table alias is overkill - this is typically used with JOIN operations
SELECT e.emp_name AS "Employee Name", e.emp_title AS "Job Title"
FROM EXAMPLES.EMPLOYEES AS e -- This line lets us shorten our query verbiage be replacing 'examples.employees' with 'e'
WHERE e.emp_salary > 100000;

/*
 * LIKE : allows you to filter WHERE clauses using string values based on
 * character matching sequences. LIKE offers two wild-card indicators:
 * 
 * '%' - Wildcard for any # of characters AND any value for those characters
 * 
 * '_' - Wildcard for any value of character BUT specifically a SINGLE character value
 * 
 * Casing for the letters supplied in the sequence you wish to match matters
 * Note that wildcards match either casing.
 * 
 * When performing a LIKE operation, it is traditional to use a Scalar function
 * to convert the resultset values to a particular casing 
 */
SELECT * FROM examples.employees WHERE EMP_NAME LIKE 'J%e%'; -- Jessica & Joseph

SELECT * FROM examples.EMPLOYEES WHERE EMP_NAME LIKE '__s%'; -- <any_letter><any_letter>s<any_number_of_letters_after>

SELECT * FROM examples.EMPLOYEES WHERE LOWER(EMP_NAME) LIKE '%e%e%'; -- By default LIKE and character matching is case-sensitive. So normalizing the case with 'LOWER' allows us more flexible character matching templates
SELECT * FROM examples.EMPLOYEES WHERE UPPER(EMP_NAME) LIKE '%S%';

/*
 * BETWEEN & AND : When using the 'BETWEEN' keyword, we also use the 'AND'
 * keyword to specify the upper and lower bounds of the search range
 */ 
SELECT * FROM examples.employees WHERE EMP_SALARY BETWEEN 90000 AND 130000;

/*
 * IN - Allows you to perform a search filter based on a set of values that
 * you specify in a list. The resultset returned must match a value within the
 * IN clause based on the column you are trying to match.
 */
SELECT * FROM EXAMPLES.EMPLOYEES WHERE LOWER(emp_title) IN ('tester','developer');
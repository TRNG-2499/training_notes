/*
 * Aggregate Functions : Operations that are performed across multiple
 * rows (or every single row) of your records based on a specified column
 */
SELECT AVG(emp_salary) FROM examples.employees;
SELECT ROUND(AVG(emp_salary),2) FROM examples.employees; -- You can combine funnctions as well
SELECT COUNT(emp_id) FROM examples.employees;

/*
 * SUM : Returns the total sum of a numeric column across all rows
 * in the result set
 */
SELECT SUM(emp_salary) FROM examples.employees;

/*
 * MIN / MAX : Returns the lowest or highest value found in a column
 * across all rows in the result set. Can be used on numeric, string,
 * and date columns.
 */
SELECT MIN(emp_salary) FROM examples.employees;
SELECT MIN(emp_salary) FROM examples.employees;
SELECT MAX(emp_salary) FROM examples.employees;

/*
 * Combining aggregate functions : Multiple aggregates can be returned
 * in a single SELECT statement, giving a summary of the data in one
 * result set. Aliases are used here to label each result column clearly.
 */
SELECT
    COUNT(emp_id)       AS "Total Employees",
    AVG(emp_salary)     AS "Average Salary",
    SUM(emp_salary)     AS "Total Payroll",
    MIN(emp_salary)     AS "Lowest Salary",
    MAX(emp_salary)     AS "Highest Salary"
FROM examples.employees;

/*
 * Aggregates with GROUP BY : Aggregate functions become significantly
 * more useful when combined with GROUP BY, allowing the operation to
 * be applied independently to each group rather than the entire table
 */
SELECT emp_title, COUNT(emp_id) AS "Headcount", AVG(emp_salary) AS "Average Salary"
FROM examples.employees
GROUP BY emp_title
ORDER BY emp_title ASC;

/*
 * SCALAR FUNCTIONS : Operations which are performed on a record-by-record
 * basis across a column of our resultset that we specify
 */ 
-- STRING FUNCTIONS:
SELECT UPPER(emp_name) FROM examples.employees;
SELECT CHAR_LENGTH(emp_name) FROM examples.employees;

/*
 * LOWER : Converts all characters in the specified column or string
 * value to lowercase on a record-by-record basis
 */
SELECT LOWER(emp_name) FROM examples.employees;

/*
 * TRIM : Removes leading and trailing whitespace from a string value.
 * Useful for cleaning data that may have been entered inconsistently.
 * LTRIM and RTRIM remove whitespace from the left or right side only.
 */
SELECT TRIM(emp_name) FROM examples.employees;
SELECT LTRIM(emp_name) FROM examples.employees;
SELECT RTRIM(emp_name) FROM examples.employees;

/*
 * SUBSTRING : Extracts a portion of a string starting at a specified
 * position for a specified length.
 * SYNTAX: SUBSTRING(<column> FROM <start_position> FOR <length>)
 */
SELECT SUBSTRING(emp_name FROM 1 FOR 3) FROM examples.employees;

/*
 * REPLACE : Substitutes all occurrences of a specified substring within
 * a string column with a new value, on a record-by-record basis.
 * SYNTAX: REPLACE(<column>, <target_string>, <replacement_string>)
 */
SELECT REPLACE(emp_title, 'Developer', 'Engineer') FROM examples.employees;

/*
 * anytime we want to perform an operation on our database that provides
 * data to us, we use a SELECT statement (or call some custom created procedures)
 */ 
SELECT ('Hello ' || 'World') as hello_world; -- String concatenation in SQL, we use ' || '
/*
 * CONCAT : An explicit function alternative to the || operator for
 * combining two or more string values together into a single result.
 * Unlike ||, CONCAT handles NULL values gracefully by treating them
 * as empty strings rather than returning NULL for the entire expression.
 */
SELECT CONCAT(emp_name, ' - ', emp_title) AS "Employee Summary" FROM examples.employees;

-- Mathematical Functions:
SELECT ABS(emp_salary) FROM examples.employees;
SELECT ABS(-175);

/*
 * ROUND : Rounds a numeric value to a specified number of decimal places.
 * SYNTAX: ROUND(<value>, <decimal_places>)
 */
SELECT ROUND(emp_salary, 2) FROM examples.employees;
SELECT ROUND(AVG(emp_salary), 2) AS "Average Salary (Rounded)" FROM examples.employees;

/*
 * CEIL / FLOOR : CEIL rounds a numeric value up to the nearest whole
 * number. FLOOR rounds a numeric value down to the nearest whole number.
 * (in our table, we are already using whole numbers - so this example is 
 *	rudimentary - but rounding may be helpful for your decimal tables)
 */
SELECT CEIL(emp_salary)  AS "Ceiling Value" FROM examples.employees;
SELECT FLOOR(emp_salary) AS "Floor Value"   FROM examples.employees;

/*
 * POWER / SQRT : POWER raises a numeric value to the specified exponent.
 * SQRT returns the square root of a numeric value.
 */
SELECT POWER(2, 8);       -- Returns 256
SELECT SQRT(emp_salary) AS "Salary Square Root" FROM examples.employees;

/*
 * MOD : Returns the remainder of a division operation between two
 * numeric values. Useful for determining even/odd values or cycling
 * through groups of records.
 * SYNTAX: MOD(<dividend>, <divisor>)
 */
SELECT MOD(emp_id, 2) AS "Even(0) or Odd(1)", emp_name FROM examples.employees;


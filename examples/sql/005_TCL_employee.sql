/*
 * TCL - Transaction Control Language allows you to bundle multiple
 * transactional statements and even create savepoints within the same
 * transaction in case a mistake is made, or you want to simply revert
 * to a previous state of your DB...
 * 
 * BEGIN - marks the start of a transaction
 * COMMIT - marks the end of a transaction
 * 
 * SAVEPOINT - creation of an entity which tracks the state of our DB at
 * 	some given point in time
 * 		Syntax:
 * 			SAVEPOINT <savepoint_name>
 * 
 * ROLLBACk - revert changes made to our DB during the transaction or back
 *				to a previous savepoint created
 * 		Syntax:
 * 			ROLLBACK TO SAVEPOINT <savepoint_name>
 * 
 * **NOTE: Savepoint and Rollback can only be used on a transaction-by-transaction
 * basis 
 */
BEGIN;
	INSERT INTO examples.employees VALUES(1010, 'RevWorker_1','Developer',150000);
	SAVEPOINT my_save_1;
	-- At this point we have 1 save point, and we only attempted to insert a single employee
	INSERT INTO examples.employees VALUES(1011, 'RevWorker_2','Manager',150000);
	-- savepoint 2
	INSERT INTO examples.employees VALUES(1012, 'RevWorker_3','Tester',150000);
	-- savepoint 3
	-- At this point, we still only have 1 save point, and we attempted to insert three emplyoees total
	ROLLBACK TO SAVEPOINT my_save_1;
	-- When we rollback to our previous savepoint, we effectively 'lose' the two Employees we tried to insert (RevWorker_2 + 3)
COMMIT;

SELECT * FROM examples.employees;


/*
 * Most Libraries that are used by other languages to communicate with SQL
 * (e.g. JDBC) Manage transactions for you!
 * This effectively looks like this:
 * BEGIN;
 * SAVEPOINT start_of_transaction;
 *	[Operations you perform using JDBC i.e. inserts/updates/deletes]
 * IF SOMETHING GOES WRONG!
 *		ROLLBACK TO SAVEPOINT start_of_transaction;
 * COMMIT;
 */
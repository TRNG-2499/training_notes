/*
 * DDL : Data Definition Language - (When we create anything structurally
 * 		for our DB)
 * 
 * DCL : Data Control Language : Provide permissions to perform certain actions
 * 		across values in our DB, across schemas and tables 		
 * 
 * GRANT : provide certain privileges to a user
 * 		GRANT <option> ON <schem_name>.<table_name> TO <user_name>
 * REVOKE : remove certain privileges from a user
 * 		REVOKE <option> ON <table> IN SCHEMA <schema_name> FROM <user_name>
 */
-- This creates a USER that can specifically access records in your
-- database (this is associated with a DB connection)
-- DDL for User:
CREATE USER joe WITH PASSWORD 'password';

-- Create an admin role to bundle privileges (rather than granting directly to joe)
CREATE ROLE admin_role;

-- DCL:
/* NOTE: Privileges could be granted directly to joe, but we typically use roles instead:
 * i.e. GRANT SELECT, UPDATE, INSERT, DELETE ON examples.employees TO joe;
 */
-- You can GRANT specific privileges:
-- GRANT SELECT, UPDATE, INSERT, DELETE ON examples.employees IN SCHEMA examples TO admin_role; 
-- or you can GRANT ALL PRIVILEGES (you should be selective with the privileges granted)

-- Make sure that YOU do not grant these blanket privileges in your applications! Grant only the privileges needed!
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA examples TO admin_role; -- Grant all privileges on Tables

-- Note: Privileges on the SCHEMA vs Tables are managed differently: This will be
-- for some functions & Stored procedures we will make - later
GRANT ALL PRIVILEGES ON SCHEMA examples to admin_role;

-- Assign the admin_role to joe
GRANT admin_role TO joe;

-- You can REVOKE privileges as well : individually, or by Revoking Roles
--REVOKE DELETE ON ALL TABLES IN SCHEMA examples FROM admin_role; -- manually revoke privileges from a role (admin_role can not longer delete)
--REVOKE admin_role FROM joe; -- Revoke a role from a user

/*
 * Privileges for Sequences and Functions (we'll examine this later...)
 */ 
GRANT USAGE ON ALL SEQUENCES IN SCHEMA examples TO admin_role;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA examples TO admin_role;

/*
 * When attempting to Drop a user or role, you may get an error if the role has remaining privileges
 * Remember to revoke privileges from the following places:
 *   Tables in a Schema
 *   Functions in a Schema
 *   Sequences in a Schema
 *   All privileges in a Schema
 */
REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA examples FROM admin_role;
REVOKE ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA examples FROM admin_role;
REVOKE ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA examples FROM admin_role;
REVOKE ALL PRIVILEGES ON SCHEMA examples FROM admin_role;

/*
 * Use DROP to remove a created USER or ROLE
 */
DROP USER joe;
DROP ROLE admin_role;


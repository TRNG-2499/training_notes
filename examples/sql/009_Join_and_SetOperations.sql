-- INNER JOIN : Join data across tables which match the column values
/*
 * Typically when joining records from multiple tables, the PK & associated
 * FK columns are referenced in an Equi-join condition. However, there is
 * nothing wrong with associating data from any column across both tables
 * so long as the datatype is similar
 */
SELECT p.player_name, t.team_name FROM examples.players AS p
INNER JOIN examples.teams AS t ON p.team_id = t.team_id;

-- FULL OUTER JOIN: Note: Player_ID and Team_ID comparisons aren't super useful, but the result set does showcase how this type of join functions!
SELECT * FROM examples.players p FULL OUTER JOIN
examples.teams t ON p.PLAYER_ID = t.TEAM_ID;

-- LEFT JOINS : The result set is based on the first table's values
SELECT p.player_salary, t.team_name FROM examples.players p
LEFT JOIN examples.teams t ON p.player_id = t.team_id;

SELECT p.player_salary, t.team_name FROM examples.players p
LEFT JOIN examples.teams t ON p.team_id = t.team_id;

-- RIGHT JOINS : The result set is based on the second table's values
SELECT p.player_salary, t.team_name FROM examples.players p
RIGHT JOIN examples.teams t ON p.player_id = t.team_id;

SELECT p.player_salary, t.team_name FROM examples.players p
RIGHT JOIN examples.teams t ON p.team_id = t.team_id;


/* SELF JOIN : The Result set is based on records on the same table
 *	If we assume we add a 'supervisor_id' column to our 'employees' table
 *	which references an emp_id value of another employee on the same table...
 *
 * SELECT a.emp_name AS emp1, b.emp_name AS emp2 FROM examples.employees a
 * INNER JOIN examples.employees b ON a.emp_id = b.supervisor_id;
 */

/* MULTI-TABLE (CHAIN) JOIN
 * A chain join links three or more tables together by extending
 * the query with additional JOIN ... ON clauses. Each JOIN
 * introduces a new table and specifies how it relates to the
 * tables already in the query. The result set combines columns
 * from all joined tables into a single resultset.
 */

-- Select one column from 4 tables using PK/FK relationships
-- This will show the player_name, team_name, sport_name and state_name
-- in a readable resultset
SELECT
    p.player_name   AS "Player",
    t.team_name     AS "Team",
    sp.sport_name   AS "Sport",
    st.state_name   AS "State"
FROM examples.players p
INNER JOIN examples.teams t  ON p.team_id  = t.team_id
INNER JOIN examples.sports sp ON t.team_sport = sp.sport_id
INNER JOIN examples.states st ON t.team_state = st.state_id
ORDER BY sp.sport_name, st.state_name, p.player_name;
-- Notice how we use ORDER BY to sort by sport_name, then state_name, then player_name (alphabetically)


/*
 * SET Operations : Perform a query against mutliple result sets and
 * 		compare data from those sets to determine a final result set
 * 		to be returned
 */
-- UNIONS : All records from both result sets displayed together
SELECT p.player_id AS id, p.player_name AS name FROM examples.players p
UNION SELECT t.team_id, t.team_name FROM examples.teams t;

-- INTERSECT : Return data from 2 results sets that match values
SELECT p.player_id AS id FROM examples.players p
INTERSECT SELECT t.team_id FROM examples.teams t;

-- EXCEPT : Return data from 2 result sets that do NOT match values
SELECT p.player_id AS id FROM examples.players p
EXCEPT SELECT t.team_id FROM examples.teams t;

/*
 * Subqueries : Performing multiple queries within the same query
 * 		operation. Specifically subqueries are performed with WHERE
 * 		clauses to specify some additional information that is searched
 * 		first.
 */
 -- Here we manually find the average, then hard-code that average in our queries...
 -- This will need to be maintained manually as our database grows...
SELECT avg(player_salary) FROM examples.PLAYERS;
SELECT player_name, player_salary FROM examples.PLAYERS WHERE PLAYER_SALARY > 590500;
SELECT player_name, player_salary FROM examples.PLAYERS WHERE PLAYER_SALARY < 590500;

-- Using a sub-query, we can calculate the average, and query records simultaneously
SELECT player_name, player_salary FROM examples.PLAYERS WHERE PLAYER_SALARY > 
(SELECT avg(player_salary) FROM examples.PLAYERS);

SELECT player_name, player_salary FROM examples.PLAYERS WHERE PLAYER_SALARY < 
(SELECT avg(player_salary) FROM examples.PLAYERS);
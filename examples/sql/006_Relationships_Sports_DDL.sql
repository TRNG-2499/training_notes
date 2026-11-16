/*
 * Players on Sports Teams - table
 * 		-player name, player salary, draft date, team?
 * 
 * Team - table
 * 		-team, state?, sport?
 * 
 * Sport - table
 * 		-sport name
 * 
 * State - table
 * 		-state name
 */  
CREATE TABLE examples.states (
/*
 * SERIAL is a datatype in SQL that will create a sequence of numbers, starting
 * from 1, and increasing by 1, each time it is called.
 * 
 * Sequences : A numerical value that can begin with some number and automatically
 * change each time the sequence is called upon.
 */
	state_id SERIAL PRIMARY KEY, -- Serial is used to auto-increment the ID, when inserting values
	state_name VARCHAR(200) UNIQUE NOT NULL
	
	/*
	 * In addition to listing constraints after each column definition,
	 * we can consolidate constraint declrations after defining the actual
	 * column below it:
	 * 
	 * CONSTRAINT state_pk PRIMARY (state_id)
	 * 
	 * Example of Composite primary key:
	 * CONSTRAINT state_pk PRIMARY (state_id, state_name)
	 */
);

CREATE TABLE examples.sports (
	sport_id SERIAL PRIMARY KEY,
	sport_name VARCHAR(200) NOT NULL
);

CREATE TABLE examples.teams (
	team_id SERIAL PRIMARY KEY, -- Serial is default value
	team_name VARCHAR(100) check(length(team_name) > 1),
	team_state INTEGER,
	team_sport INTEGER,
	/*
	 * CONSTRAINT <constraint_name> FOREIGN KEY
	 * REFERENCES <table_name>(<column_name>)
	 */
	CONSTRAINT team_state_fk FOREIGN KEY (team_state) REFERENCES examples.states(state_id),
	CONSTRAINT team_sport_fk FOREIGN KEY (team_sport) REFERENCES examples.sports(sport_id)
);


CREATE TABLE examples.players (
	player_id SERIAL PRIMARY KEY,
	-- Use the REFERENCES keyword to establish a foreign key
	team_id INTEGER REFERENCES examples.teams(team_id),
	player_name VARCHAR(200),
	player_salary NUMERIC,
	draft_date date
);

-- DROP TABLE examples.players;
-- DROP TABLE examples.teams;
-- DROP TABLE examples.sports;
-- DROP TABLE examples.states;
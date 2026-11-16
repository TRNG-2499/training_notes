/*
 * DML for data inserted into the 'state', 'sports', 'team' and 'players' tables 
 */

-- STATE DML
/*
 * NOTE : When you insert into a table, you can specify the exact columns
 * you want to associate with values
 * 
 * here, we provide a value for state_name, not state_id. But this is OK because
 * state_id uses serial as the datatype, which will automatically populate this
 * column with data
 * 
 * Insert Syntax:
 * 		INSERT INTO <table_name>[(<col_1>, <col_2>)] VALUES(<val_1>, <val_2>,...);
 */
INSERT INTO examples.states(state_name) VALUES('Colorado');
INSERT INTO examples.states(state_name) VALUES('California');
INSERT INTO examples.states(state_name) VALUES('Texas');

SELECT * FROM examples.states;

-- SPORTS DML
INSERT INTO examples.sports(sport_name) VALUES('football');
INSERT INTO examples.sports(sport_name) VALUES('basketball');

-- TEAM DML
/* 
 * Sometimes when using serial, listing all other columns can be cumbersome...
 * for example, for team table if we want to insert into every column except
 * the team_id (as to use the serial datatype) the statement would need to 
 * look like the following:
 * 
 * INSERT INTO examples.team (team_name, team_state, team_sport) VALUES (...);
 * 
 * Instead, when using 'serial' as a datatype, you can use the keyword 'DEFAULT' 
 * to use the serial value, which can condense your statements
 */
INSERT INTO examples.teams VALUES(DEFAULT,'broncos',1,1); 
INSERT INTO examples.teams VALUES(DEFAULT,'49ers',2,1);
INSERT INTO examples.teams VALUES(DEFAULT,'cowboys',3,1);
INSERT INTO examples.teams VALUES(DEFAULT,'nuggets',1,2);
INSERT INTO examples.teams VALUES(DEFAULT,'lakers',2,2);
INSERT INTO examples.teams VALUES(DEFAULT,'spurs',3,2);

SELECT * FROM examples.teams;

/*
 * You can add Dates using a variety of methods, for example:
 * 'YYYY-MM-DD' : i.e. 2000-01-10
 * 'Month DD, YYYY' : i.e. January 10, 2000
 * 'YYYY-Mon-DD' : i.e. 2000-Jan-10
 * TO_DATE('<VALUE>','<Formatting>')
 * Or using the built-in 'CURRENT_DATE' for the current date
 * to name a few...
 */
INSERT INTO examples.players VALUES (DEFAULT,1,'Bill Smith',450000,DATE '2000-01-10');
INSERT INTO examples.players VALUES (DEFAULT,1,'Matt Jones',470000,TO_DATE('2000/01/10','YYYY/MM/DD'));
INSERT INTO examples.players VALUES (DEFAULT,1,'Joe Williams',480000,'January 01, 2000');
INSERT INTO examples.players VALUES (DEFAULT,1,'Gary Brown',447500,'2000-Jan-01');

INSERT INTO examples.players VALUES (DEFAULT,2,'Dill Smith',650000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,2,'Pat Jones',660000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,2,'Moe Williams',620000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,2,'Larry Brown',635000,CURRENT_DATE);

INSERT INTO examples.players VALUES (DEFAULT,3,'Hill Smith',700000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,3,'Cat Jones',710000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,3,'Poe Williams',690000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,3,'Perry Brown',730000,CURRENT_DATE);

INSERT INTO examples.players VALUES (DEFAULT,4,'Phil Smith',550000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,4,'Kat Jones',590000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,4,'Whoa Williams',540000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,4,'Terri Brown',620000,CURRENT_DATE);

INSERT INTO examples.players VALUES (DEFAULT,5,'Will Smith',580000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,5,'Yatt Jones',550000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,5,'Slo-Mo Williams',540000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,5,'Jerri Brown',580000,CURRENT_DATE);

INSERT INTO examples.players VALUES (DEFAULT,6,'Nill Smith',600000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,6,'Fatt Jones',580000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,6,'Romo Williams',590000,CURRENT_DATE);
INSERT INTO examples.players VALUES (DEFAULT,6,'Scary Brown',610000,CURRENT_DATE);

SELECT * FROM examples.players;
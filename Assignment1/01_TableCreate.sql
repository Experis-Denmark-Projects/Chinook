/* Creates tables called Superheroes, Assistant and Power that all have id as a primary key and other table specific columns with value types and constraints. :*/
CREATE TABLE Superheroes (
	SH_ID SERIAL PRIMARY KEY,
	SH_NAME VARCHAR(50) NOT NULL,
	ALIAS VARCHAR(50) NOT NULL,
	ORIGIN TEXT NOT NULL
);

CREATE TABLE Assistant (
	ASS_ID SERIAL PRIMARY KEY,
	ASS_Name VARCHAR(50) NOT NULL
);

CREATE TABLE Power (
	Power_ID SERIAL PRIMARY KEY,
	Power_Name VARCHAR(50) NOT NULL,
	Description TEXT NOT NULL
);

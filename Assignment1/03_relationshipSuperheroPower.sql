/* Creates SuperheroPowers table witg superhero id and power id which references the tables Superheroes and Power respectively. 
* Additionally, the table has a composite primary key consisting of the superhero id and power id.
:*/
CREATE TABLE SuperheroPowers (
	sh_id serial REFERENCES Superheroes,
	power_id serial REFERENCES Power,
	PRIMARY KEY (sh_id, power_id)
);

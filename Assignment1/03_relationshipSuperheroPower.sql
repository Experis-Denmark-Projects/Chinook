CREATE TABLE SuperheroPowers (
	sh_id serial REFERENCES Superheroes,
	power_id serial REFERENCES Power,
	PRIMARY KEY (sh_id, power_id)
);

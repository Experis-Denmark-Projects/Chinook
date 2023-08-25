ALTER TABLE Superheroes DROP COLUMN ass_id;

INSERT INTO Superheroes VALUES (1, 'Batman', 'Bruce Wayne', 'Parents died');

INSERT INTO Superheroes VALUES (2, 'Superman', 'Clark Kent', 'From Krypton');

INSERT INTO Superheroes VALUES (3, 'The Flash', 'Barry Allen', 'Father framed for murdering mother');

ALTER TABLE Assistant ADD COLUMN sh_id SERIAL REFERENCES Superheroes;

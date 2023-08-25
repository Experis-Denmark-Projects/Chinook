-- added 4 powers to the table and assigns powers to different heroes based on their id

INSERT INTO Power VALUES (1, 'Money', 'Has a lot of money');

INSERT INTO Power VALUES (2, 'Super speed', 'Is very fast');

INSERT INTO Power VALUES (3, 'Gadget', 'Has many gadgets');

INSERT INTO Power VALUES (4, 'Super strength', 'Is very strong');

INSERT INTO superheropowers (sh_id, power_id ) VALUES
(1,1),(1,3),(2,2),(2,4),(3,2); 

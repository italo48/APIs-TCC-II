-- Insert vets
INSERT INTO vets(first_name, last_name) VALUES('Shannon', 'Jenkins');
INSERT INTO vets(first_name, last_name) VALUES('James', 'Carter');
INSERT INTO vets(first_name, last_name) VALUES('Julio', 'Serafim');
INSERT INTO vets(first_name, last_name) VALUES('Dhulya', 'Brito');
-- Insert specialties
INSERT INTO specialties(name) VALUES('radiology');
INSERT INTO specialties(name) VALUES('surgery');
INSERT INTO specialties(name) VALUES('all');
-- Insert ManyToMany vet and specialties
INSERT INTO vet_specialties(vet_id, specialty_id)VALUES (1, 1);
INSERT INTO vet_specialties(vet_id, specialty_id)VALUES (2, 2);
INSERT INTO vet_specialties(vet_id, specialty_id)VALUES (3, 3);
INSERT INTO vet_specialties(vet_id, specialty_id)VALUES (4, 3);
-- Insert types
INSERT INTO types(name) VALUES('dog');
INSERT INTO types(name) VALUES('bird');
INSERT INTO types(name) VALUES('cat');
INSERT INTO types(name) VALUES('lizard');
INSERT INTO types(name) VALUES('hamster');
INSERT INTO types(name) VALUES('snake');
INSERT INTO types(name) VALUES('rabbit');
INSERT INTO types(name) VALUES('goat');
INSERT INTO types(name) VALUES('chicken');
INSERT INTO types(name) VALUES('dragon');
-- Insert one owner
INSERT INTO owners(first_name, last_name, address, city, telephone) VALUES ('Ítalo', 'Costa', 'Rua A, 1727', 'Quixadá', '102348569');

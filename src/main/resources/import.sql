truncate table clients;
truncate table adresses;
truncate table genres;

insert into genres (no_genre, libelle) values (1, 'Jeu de plateau');
insert into genres (no_genre, libelle) values (2, 'Jeu de cartes');
insert into genres (no_genre, libelle) values (3, 'Jeu de stratégie');
insert into genres (no_genre, libelle) values (4, 'Coopératif');
insert into genres (no_genre, libelle) values (5, 'Jeu de dé');
insert into genres (no_genre, libelle) values (6, 'Jeu d''enquete');
insert into adresses(rue, code_postal, ville) values ('rue des Cormorans', '79000', 'Niort');
INSERT INTO CLIENTS (nom, prenom, email, no_telephone, no_adresse) VALUES ('Curie', 'Marie', 'marie.curie@example.com', '123456789', scope_identity());
insert into adresses(rue, code_postal, ville) values ('rue des marguerites', '79500', 'Melle');
INSERT INTO CLIENTS (nom, prenom, email, no_telephone, no_adresse) VALUES ('Einstein', 'Albert', 'albert.einstein@example.com', '0123456789', scope_identity());



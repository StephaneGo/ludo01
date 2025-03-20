truncate table clients;
truncate table adresses;

insert into adresses(rue, code_postal, ville) values ('rue des Cormorans', '79000', 'Niort');
INSERT INTO CLIENTS (nom, prenom, email, no_telephone, no_adresse) VALUES ('Curie', 'Marie', 'marie.curie@example.com', '123456789', scope_identity());
insert into adresses(rue, code_postal, ville) values ('rue des marguerites', '79500', 'Melle');
INSERT INTO CLIENTS (nom, prenom, email, no_telephone, no_adresse) VALUES ('Einstein', 'Albert', 'albert.einstein@example.com', '0123456789', scope_identity());



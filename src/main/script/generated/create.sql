create table Client (numero bigint not null auto_increment, adresse varchar(64), nom varchar(32), prenom varchar(32), primary key (numero)) engine=InnoDB
create table Compte (numero bigint not null auto_increment, label varchar(32), solde double precision, refClient bigint, primary key (numero)) engine=InnoDB
alter table Compte add constraint FK13we1adnrf41ipo8r3ye22i95 foreign key (refClient) references Client (numero)
INSERT INTO Client(numero,nom,prenom,adresse) VALUES (1,'Therieur' ,'alex', '2 rue Elle 75000 Paris')
INSERT INTO Client(numero,nom,prenom,adresse) VALUES (2,'Therieur' ,'alain', '20 rue xy 69000 Lyon')
INSERT INTO Compte(numero,label,solde,refClient) VALUES (1,'compte 1' , 280.0 , 1)
INSERT INTO Compte(numero,label,solde,refClient) VALUES (2,'compte 2' , 150.0 , 1)
INSERT INTO Compte(numero,label,solde,refClient) VALUES (3,'compte 3' , 350.0 , 2)
INSERT INTO Client(numero,nom,prenom,adresse) VALUES (1,'Therieur' ,'alex', '2 rue Elle 75000 Paris')
INSERT INTO Client(numero,nom,prenom,adresse) VALUES (2,'Therieur' ,'alain', '20 rue xy 69000 Lyon')
INSERT INTO Compte(numero,label,solde,refClient) VALUES (1,'compte 1' , 280.0 , 1)
INSERT INTO Compte(numero,label,solde,refClient) VALUES (2,'compte 2' , 150.0 , 1)
INSERT INTO Compte(numero,label,solde,refClient) VALUES (3,'compte 3' , 350.0 , 2)

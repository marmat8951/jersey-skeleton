drop table if exists list_hobbies;
drop table if exists disponibilité;
drop table if exists rdv;  
drop table if exists user;
drop table if exists hobbies;
drop table if exists service;

-- Table des utilisateurs avec leur login et leur mdp qu'ils donnent lors de la création du compte
create table user (
       nom varchar(100),
       prenom varchar(100),
       login varchar(100) primary key,
       numero varchar(100),
       statut varchar(100),
       email varchar(100),
       passwdHash varchar(64),
       salt varchar(64),
       search varchar(1024)
       )

-- Table contenant les différents hobbies possibles qui seront listés dans la gestion du profil
create table hobbies(id_hobbie serial primary key,
       	             libelle text
		     );

-- Table qui lie un hobbie avec un utilisateur 
create table list_hobbies(id_user int not null,
       	     		  id_hob int not null,
       	     		  foreign key(id_hob) references hobbies(id_hobbie),
			  foreign key(id_user) references users(id_user),
			  constraint pk_list_hob primary key(id_hob,id_user)
			  ); 

-- Table de disponibilités des étudiants 
create table disponibilité(id_user int not null,
       	     		   jour text,
			   matin boolean,
			   aprem boolean,
			   soir boolean,
			   foreign key(id_user) references users(id_user),
			   constraint pk_dispo primary key(id_user,jour)
			   );

create table service(libelle text primary key);

-- Table des demandes des senior, validés lorqu'un id_etu est ajouté

create table rdv(id_senior int not null,
       	         jour TIMESTAMP,
		 matin boolean,
		 aprem boolean,
		 soir boolean,
		 libelle text,
		 id_etu int,
		 foreign key(libelle) references service(libelle),
		 foreign key(id_senior) references users(id_user),
		 foreign key(id_etu) references users(id_user),
		 constraint pk_rdv primary key(id_senior,jour,matin,aprem,soir)
		 );

--Insertions d'étudiants
insert into hobbies(libelle) values('curling sur gazon');
insert into hobbies(libelle) values('deltaplane');
insert into hobbies(libelle) values('poney aquatique');
insert into hobbies(libelle) values('COBOL');

insert into users(login,mdp) values('jordaon','moi');
insert into informations values(1,'nom','prenom','mail','0669696969', 'ETUDIANT');
insert into disponibilité values(1,'lundi',TRUE,FALSE,FALSE);
insert into disponibilité values(1,'mardi',FALSE,TRUE,FALSE);
insert into list_hobbies values(1,1);
insert into list_hobbies values(1,2);

insert into users(login,mdp) values('boucherc','moi');
insert into informations values(2,'nom','prenom','mail','0669696969', 'ETUDIANT');
insert into disponibilité values(2,'lundi',TRUE,FALSE,FALSE);
insert into disponibilité values(2,'mardi',FALSE,TRUE,FALSE);
insert into list_hobbies values(2,3);
insert into list_hobbies values(2,4);

insert into users(login,mdp) values('trucmuche','moi');
insert into informations  values(3,'nom','prenom','mail','0669696969', 'SENIOR');
insert into list_hobbies values(3,1);
insert into list_hobbies values(3,4);

insert into users(login,mdp) values('booba','moi');
insert into informations values(4,'nom','prenom','mail','0669696969', 'SENIOR');
insert into list_hobbies values(4,3);
insert into list_hobbies values(4,2);

/*
RECUPERER ETUDIANTS

informations : SELECT * FROM informations WHERE statut = 'ETUDIANT';
login: SELECT login FROM users, informations where users.id_user = informations.id_user AND statut = 'ETUDIANT';
Disponibilités : SELECT * FROM disponibilité [WHERE id_user = {user}];
Hobbies : SELECT * from list_hobbies where id_user = {user}


RECUPERER SENIORS

informations : SELECT * FROM informations WHERE statut = 'SENIOR';
login: SELECT login FROM users, informations where users.id_user = informations.id_user AND statut = 'SENIOR';



*/


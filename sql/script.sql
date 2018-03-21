drop table if exists list_hobbies;
drop table if exists disponibilité;
drop table if exists rdv; 
drop table if exists informations; 
drop table if exists users ;
drop table if exists hobbies;

-- Table des utilisateurs avec leur login et leur mdp qu'ils donnent lors de la création du compte
create table users(id_user serial primary key,
       	           login text,
		    mdp text
		  ); 

-- Table contenant les informations liées à un utilisateur donné, avec son statut (étudiant, senior)
create table informations(id_user int not null,
       	     	          nom text,
			  prenom text,
			  mail text,
			  numero text,
			  statut text check(statut in ('ETUDIANT','SENIOR','	ADMIN')),
			  foreign key(id_user) references users(id_user)
			  );


-- Table contenant les différents hobbies possibles qui seront listés dans la gestion du profil
create table hobbies(id_hobbie serial primary key,
       	             libelle text
		     );

-- Table qui lie un hobbie avec un utilisateur 
create table list_hobbies(id_hob int not null,
       	     	          id_user int not null,
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

-- Table des demandes des senior, validés lorqu'un id_etu est ajouté
create table rdv(id_senior int not null,
       	         jour TIMESTAMP,
		 matin boolean,
		 aprem boolean,
		 soir boolean,
		 libelle text,
		 id_etu int,
		 foreign key(id_senior) references users(id_user),
		 foreign key(id_etu) references users(id_user),
		 constraint pk_rdv primary key(id_senior,jour,matin,aprem,soir)
		 );


			   

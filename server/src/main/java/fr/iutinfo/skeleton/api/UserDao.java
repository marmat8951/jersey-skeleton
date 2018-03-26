package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
    @SqlUpdate("create table user (nom varchar(100), prenom varchar(100), login varchar(100) primary key, numero varchar(100), statut varchar(100), passwdHash varchar(64), salt varchar(64), search varchar(1024), valide text)")
    void createUserTable();

    @SqlUpdate("insert into user (nom, prenom, login, numero, statut, passwdHash, salt, search, valide) values (:nom, :prenom, :login, :numero, :statut, :passwdHash, :salt, :search, 'non')")
    @GetGeneratedKeys
    String insert(@BindBean() User user);

    @SqlQuery("select * from user where login = :login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByLogin(@Bind("login") String name);

    @SqlQuery("select * from user where search like :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> search(@Bind("nom") String name);

    @SqlUpdate("drop table if exists user")
    void dropUserTable();

    @SqlUpdate("UPDATE user SET nom=:nom, prenom=:prenom, numero=:numero, passwdHash = :passwdHash, salt = :salt, search = :search WHERE login = :login")
    void update(@BindBean() User user);
    
    @SqlUpdate("delete from user where login = :login")
    void delete(@Bind("login") String login);
    
    @SqlUpdate("UPDATE user SET valide = 'oui' WHERE login = :login")
    int valide(@Bind("login") String login);
    
    @SqlUpdate("UPDATE user SET valide = 'non' WHERE login = :login")
    int invalide(@Bind("login") String login);

    @SqlQuery("select * from user order by login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> all();
    
    @SqlQuery("select * from user where statut = 'etudiant'")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> etudiants();
    
    @SqlQuery("select * from user where statut = 'senior'")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> seniors();
    
    @SqlQuery("select * from user where valide = 'oui'")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> isValideList();
    
    @SqlQuery("select * from user where valide = 'non'")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> isNotValideList();

    @SqlQuery("select * from user where login = :login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByUserId(@Bind("login") String login);
    
    

    void close();
}
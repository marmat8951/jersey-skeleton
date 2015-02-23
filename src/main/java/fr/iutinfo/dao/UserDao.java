package fr.iutinfo.dao;


import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import fr.iutinfo.beans.User;

public interface UserDao {
	@SqlUpdate("create table users (id integer primary key autoincrement, name varchar(100), password text, email varchar(50))")
	void createUserTable();

	@SqlUpdate("insert into users (name, password, email) values (:name, :password, :email)")
	@GetGeneratedKeys
	int insert(@Bind("name") String name, @Bind("password") String password, @Bind("email") String email);

	@SqlQuery("select id, name, email from users where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User findById(@Bind("id") int id);
	
	
	@SqlQuery("select id, name, email from users where name=:name AND password=:password")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User userIsCorrect(@Bind("name") String name, @Bind("password") String password);
	
	@SqlQuery("select id, name, email from users where name=:name")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User isNameExist(@Bind("name") String name);
	
	@SqlQuery("select id, name, email from users where email=:email")
    @RegisterMapperFactory(BeanMapperFactory.class)
	User isEmailExist(@Bind("email") String email);
	
	
	@SqlQuery("select id, name, email from users")
    @RegisterMapperFactory(BeanMapperFactory.class)
	List<User> getAll();
	
	@SqlUpdate("drop table if exists users")
	void dropUserTable(); 

	void close();
}
package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
    @SqlUpdate("create table users (id_user integer primary key autoincrement, login varchar(100), mdp varchar(64), salt varchar(64))")
    void createUserTable();

    @SqlUpdate("insert into users (login, mdp, salt) values (:login, :password, :salt)")
    @GetGeneratedKeys
    int insert(@BindBean() User user);

    @SqlQuery("select * from users where login = :login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByLogin(@Bind("login") String login);
    
    @SqlQuery("select * from users where id_user = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findById(@Bind("id") int id);

    @SqlUpdate("drop table if exists users")
    void dropUserTable();

    @SqlUpdate("delete from users where id_user = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from users order by id_user")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> all();

    void close();
}

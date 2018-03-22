package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface ServiceDao {
	 @SqlUpdate("create table service (libelle text primary key)")
	   	void createServiceTable(); 
	 
	 @SqlUpdate("insert into service (libelle) values (:libelle)")
	    @GetGeneratedKeys
	    int insert(@BindBean() Service service);
	 
	 @SqlQuery("select * from users where login = :login")
	    @RegisterMapperFactory(BeanMapperFactory.class)
	    User findByLogin(@Bind("login") String login);

	 @SqlQuery("select * from service where libelle = :libelle")
	    @RegisterMapperFactory(BeanMapperFactory.class)
	    User findById(@Bind("libelle") String libelle);
	 
	 @SqlUpdate("drop table if exists service")
	    void dropServiceTable();

	    @SqlUpdate("delete from service where libelle = :libelle")
	    void delete(@Bind("libelle") String libelle);

	    @SqlQuery("select * from service order by libelle")
	    @RegisterMapperFactory(BeanMapperFactory.class)
	    List<Service> all();

	    void close();
}
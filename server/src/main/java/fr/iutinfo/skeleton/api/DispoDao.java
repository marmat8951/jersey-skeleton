package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface DispoDao {
	
	@SqlUpdate("create table dispo(id_user serial primary key,\n" + 
    		"       	         jour text)") void createDispoTable();
	 	/*	"			   matin boolean,\n" + 
	 		"			   aprem boolean,\n" + 
	 		"			   soir boolean,\n" + 
	 		"			   foreign key(id_user) references users(id_user),\n" + 
	 		"			   constraint pk_dispo primary key(id_user,jour))")*/
	   	
	 
	 @SqlUpdate("insert into dispo (id_user, jour) values (:id_user, :jour)")
	    @GetGeneratedKeys
	    int insert(@BindBean() Dispo dispo);
	 
	 @SqlQuery("select * from dispo where id_user = :id_user")
	    @RegisterMapperFactory(BeanMapperFactory.class)
	    Dispo findByIdUser(@Bind("id_user") int id);
	    
	    @SqlUpdate("drop table if exists dispo")
	    void dropDispoTable();
	    
	    
	    
	    @SqlUpdate("delete from dispo where id_user = :id_user")
	    void delete(@Bind("id_user") int id);
	    
	    @SqlQuery("select * from dispo order by id_user")
	    @RegisterMapperFactory(BeanMapperFactory.class)
	    List<Dispo> all();
	    
	    void close();

}

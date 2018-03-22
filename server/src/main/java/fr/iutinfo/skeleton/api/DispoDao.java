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
	
	 @SqlUpdate("create table disponibilité (id_user int not null" + 
	 		"       	     		   jour text,\n" + 
	 		"			   matin boolean,\n" + 
	 		"			   aprem boolean,\n" + 
	 		"			   soir boolean,\n" + 
	 		"			   foreign key(id_user) references users(id_user),\n" + 
	 		"			   constraint pk_dispo primary key(id_user,jour))")
	   	void createDispoTable(); 
	 
	 @SqlUpdate("insert into disponibilité (id_user, jour, matin, aprem, soir) values (:id_user, :jour, :matin, :aprem, :soir)")
	    @GetGeneratedKeys
	    int insert(@BindBean() Dispo dispo);
	 
	 @SqlQuery("select * from disponibilité where id_user = :id_user")
	    @RegisterMapperFactory(BeanMapperFactory.class)
	    Dispo findByIdUser(@Bind("id_user") int id);
	    
	    @SqlUpdate("drop table if exists disponibilité")
	    void dropDispoTable();
	    
	    
	    
	    @SqlUpdate("delete from disponibilité where id_user = :id_user")
	    void delete(@Bind("id_user") int id);
	    
	    @SqlQuery("select * from disponibilité order by id_user")
	    @RegisterMapperFactory(BeanMapperFactory.class)
	    List<Dispo> all();
	    
	    void close();

}

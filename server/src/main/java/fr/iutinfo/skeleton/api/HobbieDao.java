package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface HobbieDao {
	@SqlUpdate("create table hobbies (libelle text primary key)")
   	void createServiceTable(); 
 
 @SqlUpdate("insert into hobbies (libelle) values (:libelle)")
    @GetGeneratedKeys
    String insert(@BindBean() Hobbie hobbies);
 
 @SqlQuery("select * from hobbies where libelle = :libelle")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Hobbie findByLibelle(@Bind("libelle") String libelle);

 
 @SqlUpdate("drop table if exists hobbies")
    void dropServiceTable();

    @SqlUpdate("delete from hobbies where libelle = :libelle")
    void delete(@Bind("libelle") String libelle);

    @SqlQuery("select * from hobbies order by libelle")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Hobbie> all();
    
   

    void close();
}

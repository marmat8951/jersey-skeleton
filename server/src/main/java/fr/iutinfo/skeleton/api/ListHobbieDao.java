package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface ListHobbieDao {
    @SqlUpdate("create table list_hobbies(id_user int not null, id_hob text not null, " 
    		+ " foreign key(id_hob) references hobbies(libelle), foreign key(id_user) references users(id_user)"
    		+ "constraint pk_list_hob primary key(id_user,id_hob))") void createListHobbiesTable();
    	

    
    @SqlUpdate("insert into list_hobbies (id_user,id_hob ) values (:id_user, :id_hob)")
    @GetGeneratedKeys
    int insert(@BindBean() ListHobbie liste);

    @SqlQuery("select * from list_hobbies where id_user = :id_user")
    @RegisterMapperFactory(BeanMapperFactory.class)
    ListHobbie findByIdUser(@Bind("id_user") int id);
    
    @SqlUpdate("drop table if exists list_hobbies")
    void dropListTable();
    
    
    @SqlUpdate("delete from list_hobbies where id_user = :id_user AND id_hob = :id_hob")
    void delete(@Bind("id_user") int id, @Bind("id_hob") String id_hob);
    
    @SqlQuery("select * from list_hobbies order by id_user")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<ListHobbie> all();
    
    void close();
}
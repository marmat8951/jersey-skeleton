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
    @SqlUpdate("create table list_hobbies(login varchar(100) not null, id_hob text not null, " 
    		+ " foreign key(id_hob) references hobbies(libelle), foreign key(login) references user(login)"
    		+ "constraint pk_list_hob primary key(login,id_hob))") void createListHobbiesTable();
    	

    
    @SqlUpdate("insert into list_hobbies (login,id_hob ) values (:login, :id_hob)")
    @GetGeneratedKeys
    String insert(@BindBean() ListHobbie liste);

    //Affiche la liste des hobbies d'un utilisateur d'id login
    @SqlQuery("select * from list_hobbies where login = :login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<ListHobbie> findByIdUser(@Bind("login") String login);
    
    @SqlUpdate("drop table if exists list_hobbies")
    void dropListTable();
    
    
    @SqlUpdate("delete from list_hobbies where login = :login AND id_hob = :id_hob")
    void delete(@Bind("login") String login, @Bind("id_hob") String id_hob);
    
    @SqlQuery("select * from list_hobbies order by login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<ListHobbie> all();
    
    void close();
}
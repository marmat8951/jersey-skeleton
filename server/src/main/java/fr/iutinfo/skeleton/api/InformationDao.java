package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface InformationDao {
    @SqlUpdate("create table informations(id_user int not null primary key,\n" + 
    		"       	     	          nom text,\n" + 
    		"			  prenom text,\n" + 
    		"			  mail text,\n" + 
    		"			  numero text,\n" + 
    		"			  statut text check(statut in ('ETUDIANT','SENIOR','	ADMIN')),\n" + 
    		"			  foreign key(id_user) references users(id_user)\n" + 
    		"			  );")
    void createInfoTable();

    @SqlUpdate("insert into informations (id_user, nom, prenom, mail, numero, statut) values (:uid, :nom, :prenom, :mail, :numero, :type)")
    @GetGeneratedKeys
    int insert(@BindBean() Information info);

    
    @SqlQuery("select * from informations where uid = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Information findById(@Bind("id") int id);

    @SqlUpdate("drop table if exists informations")
    void dropUserTable();

    @SqlUpdate("delete from informations where uid = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from informations order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Information> all();

    void close();
}

package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface RDVDao {
    @SqlUpdate("create table rdv(id_senior serial primary key,\n" + 
    		"       	         jour date)") void createRDVTable();
    	/*	"		 matin boolean,\n" + 
    		"		 aprem boolean,\n" + 
    		"		 soir boolean,\n" + 
    		"		 libelle text,\n" + 
    		"		 id_etu int,\n" + 
    		"		 foreign key(libelle) references service(libelle),\n" + 
    		"		 foreign key(id_senior) references users(id_user),\n" + 
    		"		 foreign key(id_etu) references users(id_user),\n" + 
    		"		 constraint pk_rdv primary key(id_senior,jour,matin,aprem,soir)\n" +*/ 
    				 
    
    
    @SqlUpdate("insert into rdv (id_senior, jour) values (:id_senior, :jour)")
    @GetGeneratedKeys
    int insert(@BindBean() RDV rdv);

    @SqlQuery("select * from rdv where id_senior = :id_senior")
    @RegisterMapperFactory(BeanMapperFactory.class)
    RDV findByIdSenior(@Bind("id_senior") int id);
    
    @SqlUpdate("drop table if exists rdv")
    void dropRDVTable();
    
    /*
    @SqlUpdate("UPDATE RDV\n SET id_etu = :id_etu WHERE id_senior = :id_senior")
    void ValideRdv(@Bind("id_senior") int id_senior, @Bind("id_etu") int id_etu);
    */
    
    @SqlUpdate("delete from rdv where id_senior = :id_senior")
    void delete(@Bind("id_senior") int id);
    
    @SqlQuery("select * from rdv order by id_senior")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<RDV> all();
    
    void close();
}

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
    @SqlUpdate("create table rdv(senior varchar(100) not null," + 
    		"jour text, matin boolean, aprem boolean, soir boolean,"
    		+ " etudiant varchar(100), service text, foreign key(service) references service(libelle), foreign key(senior) references users(login)"
    		+ "constraint pk_rdv primary key(senior,jour,matin,aprem,soir))") void createRDVTable();
    	

    
    @SqlUpdate("insert into rdv (senior, jour, matin, aprem, soir, etudiant, service) values (:senior, :jour, :matin, :aprem, :soir, :etudiant, :service )")
    @GetGeneratedKeys
    int insert(@BindBean() RDV rdv);

    @SqlQuery("select * from rdv where senior = :senior")
    @RegisterMapperFactory(BeanMapperFactory.class)
    RDV findBySenior(@Bind("senior") String id);
    
    @SqlUpdate("drop table if exists rdv")
    void dropRDVTable();
    
    /*
    @SqlUpdate("UPDATE RDV\n SET id_etu = :id_etu WHERE id_senior = :id_senior")
    void ValideRdv(@Bind("id_senior") int id_senior, @Bind("id_etu") int id_etu);
    */
    
    @SqlUpdate("delete from rdv where senior = :senior")
    void delete(@Bind("senior") String id);
    
    @SqlQuery("select * from rdv order by senior")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<RDV> all();
    
    void close();
}

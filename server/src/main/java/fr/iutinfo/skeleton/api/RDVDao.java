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
    		+ "etudiant varchar(100), service text, foreign key(service) references service(libelle), foreign key(senior) references users(login)"
    		+ "constraint pk_rdv primary key(senior,jour,matin,aprem,soir))") void createRDVTable();
    	

    
    @SqlUpdate("insert into rdv (senior, jour, matin, aprem, soir, etudiant, service) values (:senior, :jour, :matin, :aprem, :soir, :etudiant, :service )")
    @GetGeneratedKeys
    int insert(@BindBean() RDV rdv);

    @SqlQuery("select * from rdv where senior = :senior")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<RDV> findBySenior(@Bind("senior") String id);
     
    @SqlQuery("select * from rdv where etudiant = :etudiant")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<RDV> findByEtudiant(@Bind("etudiant") String id);
    
    @SqlQuery("select * from rdv where etudiant IS NOT NULL")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<RDV> findRDVValide();
    
    @SqlQuery("select * from rdv where senior = :senior AND etudiant = :etudiant")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<RDV> findRdvByCouple(@Bind("senior") String senior, @Bind("etudiant") String etudiant );
    
    @SqlUpdate("drop table if exists rdv")
    void dropRDVTable();
    
    @SqlUpdate("UPDATE rdv SET etudiant = :etudiant WHERE jour = :jour AND matin = :matin AND aprem = :aprem AND soir = :soir AND service = :service AND senior = :senior")
    void valide(@Bind("senior") String senior,
    		@Bind("jour") String jour, 
    		@Bind("matin") boolean matin, 
    		@Bind("aprem") boolean aprem, 
    		@Bind("soir") boolean soir, 
    		@Bind("service") String service,
    		@Bind("etudiant") String etudiant
    		);
    
    @SqlUpdate("UPDATE rdv SET etudiant = null WHERE jour = :jour AND matin = :matin AND aprem = :aprem AND soir = :soir AND service = :service AND senior = :senior")
    void unvalide(@Bind("senior") String senior,
    		@Bind("jour") String jour, 
    		@Bind("matin") boolean matin, 
    		@Bind("aprem") boolean aprem, 
    		@Bind("soir") boolean soir, 
    		@Bind("service") String service
    		);
    
    /*
     * 	private String senior ;
	private String jour;
	private boolean matin;
	private boolean aprem;
	private boolean soir;
	private String service;
	private String etudiant=null;
     */
    
    @SqlUpdate("delete from rdv where jour = :jour AND matin = :matin AND aprem = :aprem AND soir = :soir AND service = :service AND senior = :senior")
    void delete(@Bind("senior") String senior,
    		@Bind("jour") String jour, 
    		@Bind("matin") boolean matin, 
    		@Bind("aprem") boolean aprem, 
    		@Bind("soir") boolean soir, 
    		@Bind("service") String service);
    
    @SqlQuery("select * from rdv order by senior")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<RDV> all();
    
    void close();


}

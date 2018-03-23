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

	@SqlUpdate("create table dispo(iduser int not null," + "jour text," + "matin boolean," + "aprem boolean,"
			+ "soir boolean)")
	void createDispoTable();
	/*
	 * "			   foreign key(id_user) references users(id_user),\n" +
	 * "			   constraint pk_dispo primary key(id_user,jour))")
	 */

	@SqlUpdate("insert into dispo (iduser, jour,matin,aprem,soir) values (:iduser, :jour, :matin, :aprem, :soir)")
	@GetGeneratedKeys
	public int insert(@BindBean() Dispo dispo);

	@SqlQuery("select * from dispo where iduser = :iduser")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Dispo findByIduser(@Bind("iduser") int id);

	@SqlUpdate("drop table if exists dispo")
	void dropDispoTable();

	@SqlUpdate("delete from dispo where iduser = :iduser")
	void delete(@Bind("iduser") int id);

	@SqlQuery("select * from dispo order by iduser")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Dispo> all();

	void close();

}

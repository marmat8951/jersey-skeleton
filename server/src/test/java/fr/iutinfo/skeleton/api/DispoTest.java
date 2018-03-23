package fr.iutinfo.skeleton.api;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class DispoTest {

	@Test
	public void should_save_dispo_in_db() {		
		DispoDao dao = BDDFactory.getDbi().open(DispoDao.class);
		dao.dropDispoTable();
		dao.createDispoTable();
		Dispo dispo = new Dispo();
		dispo.setIduser(1);
		dispo.setJour("lundi");
		dispo.setMatin(true);
		dispo.setAprem(false);
		dispo.setSoir(false);
		dao.insert(dispo);
		List<Dispo> dispos = dao.all();
		Assert.assertEquals(1, dispos.get(0).getIduser());
	}

}

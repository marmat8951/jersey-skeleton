package fr.iutinfo.skeleton.api;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import fr.iutinfo.skeleton.common.dto.DispoDto;

public class DispoResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new Api();
	}

	@Test
	public void shoud_write_from_enpoint() {
		DispoDto dispo = new DispoDto();
		dispo.setIduser(2);
		Entity<DispoDto> dispoEntity = Entity.entity(dispo, MediaType.APPLICATION_JSON);
		DispoDto newDispo = target("/dispo/").request().post(dispoEntity).readEntity(DispoDto.class);
		Assert.assertEquals(dispo.getIduser(), newDispo.getIduser());
	}
}

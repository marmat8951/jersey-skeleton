package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.DispoDto;
import fr.iutinfo.skeleton.common.dto.ListHobbieDto;
import fr.iutinfo.skeleton.common.dto.RDVDto;
import fr.iutinfo.skeleton.common.dto.ServiceDto;

@Path("/listhobbies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListHobbieResource {
	final static Logger logger = LoggerFactory.getLogger(ListHobbieResource.class);
	private static ListHobbieDao dao = getDbi().open(ListHobbieDao.class);
	
	public ListHobbieResource() throws SQLException {
        if (!tableExist("list_hobbies")) {
            logger.debug("Create table list_hobbies");
            dao.createListHobbiesTable();
        }
    }
	
	 @GET
		@Path("/test")
		public ListHobbieDto listhobTest() {
			ListHobbieDto dto = new ListHobbieDto();
			dto.setId_hob("poney");
			dto.setLogin("jordaon");
			return dto;
		}
	 
	@POST
    public ListHobbieDto createListeHobbies(ListHobbieDto dto) {
        ListHobbie liste = new ListHobbie();
        liste.initFromDto(dto);
        String login =  (String) dao.insert(liste);
        dto.setLogin(login);
        return dto;
    }
	
	@GET
	@Path("/{login}")
	public List<ListHobbieDto> getByIdUser(@PathParam("login") String login) {
		List<ListHobbie> liste = dao.findByIdUser(login);
		if (liste == null) {
			throw new WebApplicationException(404);
		}
		return liste.stream().map(ListHobbie::convertToDto).collect(Collectors.toList());
	}
	
	@GET
    public List<ListHobbieDto> getAllHobbies() {
        List<ListHobbie> liste;
            liste = dao.all();
        return liste.stream().map(ListHobbie::convertToDto).collect(Collectors.toList());
    }
	
	@DELETE
    @Path("/{login}&{id_hob}")
    public void deleteService(@PathParam("login") String login, @PathParam("id_hob") String id_hob) {
        dao.delete(login,id_hob);
    }
	
	
	
}
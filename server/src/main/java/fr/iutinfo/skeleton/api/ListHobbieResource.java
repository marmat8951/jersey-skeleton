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

import fr.iutinfo.skeleton.common.dto.ListHobbieDto;
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
	
	@POST
    public ListHobbieDto createListeHobbies(ListHobbieDto dto) {
        ListHobbie liste = new ListHobbie();
        liste.initFromDto(dto);
        int id_user =  dao.insert(liste);
        dto.setId_user(id_user);
        return dto;
    }
	
	@GET
    @Path("{id_user}")
    public ListHobbieDto getListById(@PathParam("id_user") int id) {
        ListHobbie liste = dao.findByIdUser(id);
        if (liste == null) {
            throw new WebApplicationException(404);
        }
        return liste.convertToDto();
    }
	
	@GET
    public List<ListHobbieDto> getAllHobbies() {
        List<ListHobbie> liste;
            liste = dao.all();
        return liste.stream().map(ListHobbie::convertToDto).collect(Collectors.toList());
    }
	
	@DELETE
    @Path("/{id_user}&{id_hob}")
    public void deleteService(@PathParam("id_user") int id_user, @PathParam("id_hob") String id_hob) {
        dao.delete(id_user,id_hob);
    }
	
	
	
}
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

import fr.iutinfo.skeleton.common.dto.HobbieDto;

@Path("/hobbies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HobbieResource {
	final static Logger logger = LoggerFactory.getLogger(ServiceResource.class);
	private static HobbieDao dao = getDbi().open(HobbieDao.class);

	public HobbieResource() throws SQLException {
		if (!tableExist("hobbies")) {
			logger.debug("Create table hobbies");
			dao.createServiceTable();
		}
	}

	@POST
	public HobbieDto createHobbie(HobbieDto dto) {
		Hobbie hobbie = new Hobbie();
		hobbie.initFromDto(dto);
		String libelle = (String) dao.insert(hobbie);
		dto.setLibelle(libelle);
		return dto;
	}
	
	@GET
    @Path("{libelle}")
    public HobbieDto getHobbieById(@PathParam("libelle") String libelle) {
        Hobbie hobbie = dao.findByLibelle(libelle);
        if (hobbie == null) {
            throw new WebApplicationException(404);
        }
        return hobbie.convertToDto();
    }
	
	@GET
    public List<HobbieDto> getAllHobbies() {
        List<Hobbie> hobbies;
            hobbies = dao.all();
        return hobbies.stream().map(Hobbie::convertToDto).collect(Collectors.toList());
    }
    
    
    @DELETE
    @Path("/{libelle}")
    public void deleteHobbies(@PathParam("libelle") String libelle) {
        dao.delete(libelle);
    }

}

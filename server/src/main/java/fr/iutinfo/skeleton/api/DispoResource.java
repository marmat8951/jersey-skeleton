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
import fr.iutinfo.skeleton.common.dto.RDVDto;
import fr.iutinfo.skeleton.common.dto.ServiceDto;

@Path("/dispo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DispoResource {
	final static Logger logger = LoggerFactory.getLogger(UserResource.class);
	public static final DispoDao dao = getDbi().open(DispoDao.class);
	
	public DispoResource() throws SQLException {
	    if (!tableExist("disponibilité")) {
	        logger.debug("Create table disponibilité");
	        dao.createDispoTable();
	    }
	}
	
	@POST
    public DispoDto createDispo(DispoDto dto) {
        Dispo dispo = new Dispo();
        dispo.initFromDto(dto);
        dao.insert(dispo);
        return dto;
    }
	
	 @GET
	    @Path("dispo/{id}")
	    public DispoDto getDispoById(@PathParam("id") int id) {
	        Dispo dispo= dao.findByIdUser(id);
	        if (dispo == null) {
	            throw new WebApplicationException(404);
	        }
	        return dispo.convertToDto();
	    }
	    
	    @GET
	    public List<DispoDto> getAllDispo() {
	        List<Dispo> dispos;
	            dispos = dao.all();
	        return dispos.stream().map(Dispo::convertToDto).collect(Collectors.toList());
	    }
	    
	    
	    @DELETE
	    @Path("/{id}")
	    public void deleteDispo(@PathParam("id") int id) {
	        dao.delete(id);
	    }

}

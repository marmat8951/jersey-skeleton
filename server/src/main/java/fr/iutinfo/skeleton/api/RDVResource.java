package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.RDVDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/RDV")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RDVResource {
	final static Logger logger = LoggerFactory.getLogger(UserResource.class);
	public static final RDVDao dao = getDbi().open(RDVDao.class);

	public RDVResource() throws SQLException {
	    if (!tableExist("RDV")) {
	        logger.debug("Crate table RDV");
	        dao.createRDVTable();
	    }
	}
	
    @POST
    public RDVDto createRDV(RDVDto dto) {
        RDV rdv = new RDV();
        rdv.initFromDto(dto);
        dao.insert(rdv);
        return dto;
    }
    
    @GET
    @Path("/{id_senior}")
    public RDVDto getRDV(@PathParam("id_senior") int id_senior) {
        RDV rdv = dao.findByIdSenior(id_senior);
        if (rdv == null) {
            throw new WebApplicationException(404);
        }
        return rdv.convertToDto();
    }
    
    @GET
    public List<RDVDto> getAllRDV() {
        List<RDV> rdvs;
        rdvs = dao.all();
        return rdvs.stream().map(RDV::convertToDto).collect(Collectors.toList());
    }
    
    @DELETE
    @Path("/{id_senior}")
    public void deleteUser(@PathParam("id_senior") int id) {
        dao.delete(id);
    }
}

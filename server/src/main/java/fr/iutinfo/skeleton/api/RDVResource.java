package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
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

@Path("/RDV")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RDVResource {
	final static Logger logger = LoggerFactory.getLogger(RDVResource.class);
	public static final RDVDao dao = getDbi().open(RDVDao.class);

	public RDVResource() throws SQLException {
	    if (!tableExist("RDV")) {
	        logger.debug("Create table RDV");
	        dao.createRDVTable();
	    }
	}
	
    @POST
    public RDVDto createRDV(RDVDto dto) {
        RDV rdv = new RDV();
        rdv.initFromDto(dto);
        int id = dao.insert(rdv);
        dto.setId_senior(id);
        return dto;
    }
    
    @GET
	@Path("/test")
	public RDVDto rdvTest() {
		RDVDto dto = new RDVDto();
		dto.setAprem(true);
		dto.setId_etu(1);
		dto.setId_senior(1);
		dto.setJour("lundi");
		dto.setLibelle("conduite");
		dto.setMatin(true);
		dto.setSoir(false);
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

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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.RDVDto;

@Path("/rdv")
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
        dao.insert(rdv);
        //dto.setSenior(id);
        return dto;
    }
    
    @GET
    @Path("/{senior}")
    public RDVDto getRDV(@PathParam("senior") String senior) {
        RDV rdv = dao.findBySenior(senior);
        if (rdv == null) {
            throw new WebApplicationException(404);
        }
        return rdv.convertToDto();
    }
    
    
    //Méthode pour récuperer un rendez vous validé, c'est à dire qu'un étudiant a été ajouté au rendez vous. 
    @GET
    @Path("/{senior}&{etudiant}")
    public RDVDto getRdvValide(@PathParam("senior") String senior, @PathParam("etudiant") String etudiant) {
        RDV rdv = dao.findRdvValide(senior,etudiant);
        if (rdv == null) {
            throw new WebApplicationException(404);
        }
        return rdv.convertToDto();
    }
    
    
    @PUT
    @Path("/validate/{login}")
    public void valideRDV(@PathParam("login") String login) {
    	dao.ValideRdv(login);
    }
    
    
    @GET
    public List<RDVDto> getAllRDV() {
        List<RDV> rdvs;
        rdvs = dao.all();
        return rdvs.stream().map(RDV::convertToDto).collect(Collectors.toList());
    }
    
    @DELETE
    @Path("/{senior}")
    public void deleteUser(@PathParam("senior") String id) {
        dao.delete(id);
    }
}

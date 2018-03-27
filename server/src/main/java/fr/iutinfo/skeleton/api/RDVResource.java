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
    @Path("/senior/{senior}")
    public List<RDVDto> getRDVbySenior(@PathParam("senior") String senior) {
    	List<RDV> l = dao.findBySenior(senior);
        if (l == null) {
            throw new WebApplicationException(404);
        }
        return l.stream().map(RDV::convertToDto).collect(Collectors.toList());
    }
    
    @GET
    @Path("/valide/")
    public List<RDVDto> getRDValide() {
    	List<RDV> l = dao.findRDVValide();
        if (l == null) {
            throw new WebApplicationException(404);
        }
        return l.stream().map(RDV::convertToDto).collect(Collectors.toList());
    }
    
    
    @GET
    @Path("/etudiant/{etudiant}")
    public List<RDVDto> getRDVbyEtudiant(@PathParam("etudiant") String etudiant) {
    	List<RDV> l = dao.findByEtudiant(etudiant);
        if (l == null) {
            throw new WebApplicationException(404);
        }
        return l.stream().map(RDV::convertToDto).collect(Collectors.toList());
    }
    
    
    //Méthode pour récuperer un rendez vous validé, c'est à dire qu'un étudiant a été ajouté au rendez vous. 
    @GET
    @Path("/{senior}&{etudiant}")
    public List<RDVDto> getRdvByCouple(@PathParam("senior") String senior, @PathParam("etudiant") String etudiant) {
    	List<RDV> l = dao.findRdvByCouple(senior, etudiant);
        if (l == null) {
            throw new WebApplicationException(404);
        }
        return l.stream().map(RDV::convertToDto).collect(Collectors.toList());
    }
    
    
    @PUT
    @Path("/validate/{senior}&{jour}&{matin}&{aprem}&{soir}&{service}&{etudiant}")
    public void valide(@PathParam("senior") String senior,
    		@PathParam("jour") String jour,
    		@PathParam("matin") boolean matin,
    		@PathParam("aprem") boolean aprem,
    		@PathParam("soir") boolean soir,
    		@PathParam("service") String service,
    		@PathParam("etudiant") String etudiant) {
    	dao.valide(senior,jour,matin,aprem,soir,service,etudiant);
    }
    
    @PUT
    @Path("/validate/{senior}&{jour}&{matin}&{aprem}&{soir}&{service}")
    public void unvalide(@PathParam("senior") String senior,
    		@PathParam("jour") String jour,
    		@PathParam("matin") boolean matin,
    		@PathParam("aprem") boolean aprem,
    		@PathParam("soir") boolean soir,
    		@PathParam("service") String service) {
    	dao.unvalide(senior,jour,matin,aprem,soir,service);
    }
    
    
    @GET
    public List<RDVDto> getAllRDV() {
        List<RDV> rdvs;
        rdvs = dao.all();
        return rdvs.stream().map(RDV::convertToDto).collect(Collectors.toList());
    }
    
    @DELETE
    @Path("/{senior}&{jour}&{matin}&{aprem}&{soir}&{service}")
    public void deleteUser(@PathParam("senior") String senior,
    		@PathParam("jour") String jour,
    		@PathParam("matin") boolean matin,
    		@PathParam("aprem") boolean aprem,
    		@PathParam("soir") boolean soir,
    		@PathParam("service") String service){
        dao.delete(senior,jour,matin,aprem,soir,service);
    }
}

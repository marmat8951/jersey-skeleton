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

import fr.iutinfo.skeleton.common.dto.ServiceDto;
import fr.iutinfo.skeleton.common.dto.UserDto;

@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceResource {
	final static Logger logger = LoggerFactory.getLogger(ServiceResource.class);
	private static ServiceDao dao = getDbi().open(ServiceDao.class);

	public ServiceResource() throws SQLException {
        if (!tableExist("service")) {
            logger.debug("Create table service");
            dao.createServiceTable();
        }
    }

	
    @POST
    public ServiceDto createService(ServiceDto dto) {
        Service service = new Service();
        service.initFromDto(dto);
        String libelle = (String) dao.insert(service);
        dto.setLibelle(libelle);
        return dto;
    }
    
    @GET
    @Path("{libelle}")
    public ServiceDto getServiceById(@PathParam("libelle") String libelle) {
        Service service = dao.findByLibelle(libelle);
        if (service == null) {
            throw new WebApplicationException(404);
        }
        return service.convertToDto();
    }
    
    @GET
    public List<ServiceDto> getAllUsers() {
        List<Service> services;
            services = dao.all();
        return services.stream().map(Service::convertToDto).collect(Collectors.toList());
    }
    
    
    @DELETE
    @Path("/{libelle}")
    public void deleteService(@PathParam("libelle") String libelle) {
        dao.delete(libelle);
    }
    
	
}

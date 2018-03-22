package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
            logger.debug("Crate table service");
            dao.createServiceTable();
        }
    }

	
    @POST
    public ServiceDto createService(ServiceDto dto) {
        Service service = new Service();
        service.initFromDto(dto);
        String libelle = dao.insert(service);
        dto.setLibelle(libelle);
        return dto;
    }
	
}

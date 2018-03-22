package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    final static Logger logger = LoggerFactory.getLogger(UserResource.class);
    private static UserDao dao = getDbi().open(UserDao.class);

    public UserResource() throws SQLException {
        if (!tableExist("users")) {
            logger.debug("Crate table users");
            dao.createUserTable();
        }
    }

    @POST
    @Path("/etudiant")
    public UserDto createEtudiant(UserDto dto) {
        User user = new User();
        
        user.initFromDto(dto);
        user.resetPasswordHash();
        int id = dao.insert(user);
        dto.setId(id);
        return dto;
    }
    
    @POST
    @Path("/senior")
    public UserDto createSenior(UserDto dto) {
        User user = new User();
        user.initFromDto(dto);
        user.resetPasswordHash();
        int id = dao.insert(user);
        dto.setId(id);
        return dto;
    }
    
    @POST
    @Path("/test")
    public UserDto dtoTestPost() {
    	UserDto udto = new UserDto();
    	udto.setId(1);
    	udto.setLogin("yoloswag");
    	udto.setMail("etudiant@etudiant");
    	udto.setNom("Machta");
    	udto.setPrenom("Ossama");
    	udto.setStatut("Etudiant");
    	return udto;
   
    	
    }
    
    @GET
    @Path("/test")
    public UserDto dtoTestGet() {
    	UserDto udto = new UserDto();
    	udto.setId(1);
    	udto.setLogin("yoloswag");
    	udto.setMail("etudiant@etudiant");
    	udto.setNom("Machta");
    	udto.setPrenom("Ossama");
    	udto.setStatut("Etudiant");
    	return udto;
   
    	
    }

    @GET
    @Path("etudiant/{id}")
    public UserDto getEtudiantById(@PathParam("id") int id) {
        User user = dao.findById(id);
        if (user == null) {
            throw new WebApplicationException(404);
        }
        return user.convertToDto();
    }

    @GET
    public List<UserDto> getAllUsers() {
        List<User> users;
            users = dao.all();
        return users.stream().map(User::convertToDto).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") int id) {
        dao.delete(id);
    }

}

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NoContentException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.UserDto;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    final static Logger logger = LoggerFactory.getLogger(UserResource.class);
    private static UserDao dao = getDbi().open(UserDao.class);

    public UserResource() throws SQLException {
        if (!tableExist("user")) {
            logger.debug("Create table user");
            dao.createUserTable();
            UserDto dto = new UserDto();
            dto.setLogin("admin@admin");
            dto.setNom("Dhellemmes");
            dto.setPrenom("Edouard");
            dto.setNumero("0680859040");
            dto.setPassword("entreedetvous");
            dto.setStatut("admin");
            createUser(dto);
            valideUser("admin@admin");
        }
    }

    @POST
    public UserDto createUser(UserDto dto) {
        User user = new User();
        user.initFromDto(dto);
        user.resetPasswordHash();
        String key = (String) dao.insert(user);
        dto.setLogin(key);
        return dto;
    }
    
    @PUT
    @Path("/validate/{login}")
    public void valideUser(@PathParam("login") String login) {
    	dao.valide(login);
    }
    
    @PUT
    @Path("/!validate/{login}")
    public void invalideUser(@PathParam("login") String login) {
    	dao.invalide(login);
    }

    @PUT
    @Path("/{login}")
    public UserDto modifyUser(@PathParam("login") String login, UserDto dto) {
        User user = dao.findByLogin(login);
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setNumero(dto.getNumero());
        user.setPassword(dto.getPassword());
        dao.update(user);
        return dto;
    }
    
    @GET
    @Path("/{login}")
    public UserDto getUser(@PathParam("login") String login) {
        User user = dao.findByLogin(login);
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
    
    @GET
    @Path("/etudiants")
    public List<UserDto> getEtudiants() {
        List<User> users;
        users = dao.etudiants();
        return users.stream().map(User::convertToDto).collect(Collectors.toList());
    }
    
    @GET
    @Path("/valide")
    public List<UserDto> getListValide() {
        List<User> users;
        users = dao.isValideList();
        return users.stream().map(User::convertToDto).collect(Collectors.toList());
    }
    
    @GET
    @Path("/!valide")
    public List<UserDto> getListNotValide() {
        List<User> users;
        users = dao.isNotValideList();
        return users.stream().map(User::convertToDto).collect(Collectors.toList());
    }
    
    @GET
    @Path("/seniors")
    public List<UserDto> getSeniors() {
        List<User> users;
        users = dao.seniors();
        return users.stream().map(User::convertToDto).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{login}")
    public void deleteUser(@PathParam("login") String login) {
        dao.delete(login);
    }

}
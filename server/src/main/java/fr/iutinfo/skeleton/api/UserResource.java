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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

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
        }
    }

    @POST
    public UserDto createUser(UserDto dto) {
        User user = new User();
        user.initFromDto(dto);
        user.resetPasswordHash();
        String key = (String) dao.insert(user);
        dto.setLogin(key);;
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

    @DELETE
    @Path("/{login}")
    public void deleteUser(@PathParam("login") String login) {
        dao.delete(login);
    }

}
package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	final static Logger logger = LoggerFactory.getLogger(UserResource.class);
	private static UserDao dao = getDbi().open(UserDao.class);
	private static InformationDao infodao = getDbi().open(InformationDao.class);

	public UserResource() throws SQLException {
		if (!tableExist("users")) {
			logger.debug("Create table users");
			dao.createUserTable();
		}
		if (!tableExist("informations")) {
			logger.debug("Create table informations");
			infodao.createInfoTable();
		}/*
		User u1 = new User();
		Information ui1 = new Information();
		u1.setId(1);
		u1.setLogin("test");
		u1.setPassword("pass");
		ui1.setUid(1);
		ui1.setMail("mail@mail");
		ui1.setNom("nom");
		ui1.setNumero("0606060606");
		ui1.setType("ETUDIANT");
		dao.insert(u1);
		infodao.insert(ui1);*/
	}

	@POST
	@Path("/etudiant")
	public UserDto createEtudiant(UserDto dto) {
		User user = new User();
		Information info = new Information();
		user.initFromDto(dto);
		user.resetPasswordHash();
		int id = dao.insert(user);
		dto.setId(id);
		info.setUid(id);
		info.setType("ETUDIANT");
		infodao.insert(info);
		return dto;
	}

	@POST
	@Path("/senior")
	public UserDto createSenior(UserDto dto) {
		User user = new User();
		Information info = new Information();
		user.initFromDto(dto);
		user.resetPasswordHash();
		int id = dao.insert(user);
		dto.setId(id);
		info.setUid(id);
		info.setType("SENIOR");
		infodao.insert(info);
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
		udto.setStatut("ETUDIANT");
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
		udto.setStatut("ETUDIANT");
		return udto;

	}

	@GET
	@Path("etudiant/{id}")
	public UserDto getEtudiantById(@PathParam("id") int id) {
		User user = dao.findById(id);
		Information i = infodao.findById(id);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		UserDto udto = user.convertToDto();
		i.pushToDto(udto);
		return udto;
	}

	@GET
	public List<UserDto> getAllUsers() {
		List<User> users;
		users = dao.all();
		List<UserDto> res = new ArrayList<>();

		for (User i : users) {
			UserDto udto = i.convertToDto();
			Information info = infodao.findById(udto.getId());
			if (info != null) {
				info.pushToDto(udto);
			}
			res.add(udto);
		}
		return res;
	}

	@DELETE
	@Path("/{id}")
	public void deleteUser(@PathParam("id") int id) {
		dao.delete(id);
		infodao.delete(id);
	}

}

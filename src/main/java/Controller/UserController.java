/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import domain.Kweet;
import domain.Role;
import domain.User;
import java.awt.Robot;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.KweetService;
import service.UserService;

/**
 *
 * @author Luc
 */
@Stateless
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Resource
    private SessionContext sessionContext;

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    @GET
    public List<User> get() {
        return userService.getAll();
    }

    @GET
    @Path("/{id}/tweet")
    public List<Kweet> getKweetsByUId(@PathParam("id") long id) {
        return kweetService.getKweetsByUId(id);
    }

    @GET
    @Path("/{id}")
    public User getByUId(@PathParam("id") long id) {
        User u = userService.getByUId(id);
        if (u == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return u;
    }

    @GET
    @Path("/{username}")
    public User getByUsername(@QueryParam("username") String username) {
        User u = userService.getByUsername(username);
        if (u == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return u;
    }

    @GET
    @Path("/email")
    public User getByEmail(@QueryParam("email") String email) {
        User u = userService.getByEmail(email);
        if (u == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return u;
    }

    @POST
    public User post(@QueryParam("username") String username, @QueryParam("email") String email, @QueryParam("password") String password) {
        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(Role.USER);
        u = userService.create(u);
        if (u == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return u;
    }

    @PUT
    public void update(@QueryParam("url") String url, @QueryParam("bio") String bio, @QueryParam("location") String location) {
        User u = userService.getByUsername(sessionContext.getCallerPrincipal().getName());
        if (u == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        u.setBio(bio);
        u.setLocation(location);
        u.setWeburl(url);
        userService.update(u);

    }

    @PUT
    @Path("/{id}")
    public void updateRole(@QueryParam("role") Role role, @PathParam("id") long id) {
        User user = userService.getByUId(id);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userService.updateRole(role, user);
    }

    @PUT
    @Path("/{id}/username")
    public void updateUsername(@QueryParam("username") String username, @PathParam("id") long id) {
        User user = userService.getByUId(id);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userService.updateUname(username, user);
    }

    @PUT
    @Path("/{id}/follower/{followingId}")
    public void updateFollowing(@PathParam("id") long id, @PathParam("followingId") long followingId) {
        userService.addFollowee(followingId, id);
    }

    @PUT
    @Path("/{id}/follower/{followingId}")
    public void updateFollower(@PathParam("id") long id, @PathParam("followingId") long followingId) {
        userService.remmoveFollowee(followingId, id);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id){
        userService.delete(id);
    }
}

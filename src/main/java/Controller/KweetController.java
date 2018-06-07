/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import domain.Kweet;
import domain.User;
import java.util.List;
import javax.annotation.*;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import service.KweetService;
import service.UserService;

/**
 *
 * @author Luc
 */
@Stateless
@Path("/kweet")
public class KweetController {

    @Resource
    private SessionContext sc;

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    @GET
    public List<Kweet> get() {
        return kweetService.getAll();
    }

    @GET
    @Path("/{id}")
    public Kweet getById(@PathParam("id") long id) {
        Kweet kweet = kweetService.getById(id);
        if (kweet == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return kweet;
    }

    @GET
    @Path("/{message}")
    public List<Kweet> getByMessage(@QueryParam("arg") String arg) {
        return kweetService.getByText(arg);
    }

    @POST
    public Kweet post(@QueryParam("arg") String arg) throws Exception {
        User loggedinUser = new User();
        if (loggedinUser != null) {
            Kweet k = new Kweet();
            k.setOwner(loggedinUser);
            k.setText(arg);
            return kweetService.create(k);
        }
        throw new WebApplicationException(Response.Status.UNAUTHORIZED);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id) throws Exception {
        User loggedinUser = new User();
        if (loggedinUser == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        kweetService.delete(id, loggedinUser);

    }

}

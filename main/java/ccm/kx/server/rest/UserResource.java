package ccm.kx.server.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import ccm.kx.server.service.UserService;

/**
 * @author KX
 */
@Path("user")
public class UserResource extends AbstractResource {
    private final UserService userService = new UserService();

    @GET
    @Path("list")
    public Response listUsers() {
        return runAction(() -> userService.listAllUsers());
    }

    @GET
    @Path("updatePassword")
    public Response updatePassword(@QueryParam("userLogin") String userLogin, @QueryParam("userPassword") String userPassword, @QueryParam("newPassword") String newPassword) {
        return runAuthenticatedAction(userLogin, userPassword, () -> userService.updatePassword(userLogin, newPassword));
    }

    @GET
    @Path("create")
    public Response create(@QueryParam("userLogin") String userLogin, @QueryParam("userPassword") String userPassword, @QueryParam("newLogin") String newLogin, @QueryParam("newPassword") String newPassword) {
        return runAuthenticatedAction(userLogin, userPassword, () -> userService.createUser(newLogin, newPassword));
    }
}

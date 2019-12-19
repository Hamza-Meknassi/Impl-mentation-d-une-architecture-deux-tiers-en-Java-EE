package ccm.kx.server.rest;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ccm.kx.server.service.UserService;

/**
 * @author KX
 */
public abstract class AbstractResource {
    private final UserService userService = new UserService();

    protected final Response runAction(Supplier<? extends Object> actionWithResult) {
        try {
            return Response.status(Status.OK).entity(String.valueOf(actionWithResult.get())).build();
        } catch (RuntimeException e) {
            Logger.getGlobal().log(Level.SEVERE, e.toString(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    protected final Response runAction(Runnable actionWithoutResult) {
        return runAction(asSupplier(actionWithoutResult));
    }

    protected final Response runAuthenticatedAction(String userLogin, String userPassword, Supplier<? extends Object> actionWithResult) {
        if (!userService.checkUser(userLogin, userPassword)) {
            return Response.status(Status.UNAUTHORIZED).build();
        } else {
            return runAction(actionWithResult);
        }
    }

    protected final Response runAuthenticatedAction(String userLogin, String userPassword, Runnable actionWithoutResult) {
        return runAuthenticatedAction(userLogin, userPassword, asSupplier(actionWithoutResult));
    }

    private static Supplier<Void> asSupplier(Runnable runnable) {
        return () -> {
            runnable.run();
            return null;
        };
    }
}

package ccm.kx.server;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author KX
 */
public class ServerLauncher {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ccm_kx_server");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void main(String[] args) throws IOException {
        try {
            URI uri = URI.create("http://localhost:8080/");
            ResourceConfig configuration = new ResourceConfig().packages("ccm.kx.server.rest");
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, configuration);

            Logger.getGlobal().info("\n\tPress Enter to shutdown server.\n");
            System.in.read();

            server.shutdownNow();
        } finally {
            entityManagerFactory.close();
        }
    }
}

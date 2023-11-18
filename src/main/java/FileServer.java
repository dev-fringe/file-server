import java.nio.file.Paths;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.PathResourceManager;
import static io.undertow.Handlers.resource;
public class FileServer {

	public static void main(String[] args) {
        Undertow
        .builder()
        .addHttpListener(8080, "0.0.0.0")
        .setHandler(
        		resource(new PathResourceManager(Paths.get(System.getProperty("user.home")), 100)).setDirectoryListingEnabled(true))
        .build()
        .start();
	}
}

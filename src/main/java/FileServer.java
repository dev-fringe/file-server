import static io.undertow.Handlers.resource;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.file.Paths;
import java.util.Enumeration;

import io.undertow.Undertow;
import io.undertow.server.handlers.resource.PathResourceManager;
public class FileServer {

	public static void main(String[] args) throws SocketException {
        Undertow
        .builder()
        .addHttpListener(8080, getLocalIP())
        .setHandler(
        		resource(new PathResourceManager(Paths.get(System.getProperty("user.home")), 100)).setDirectoryListingEnabled(true))
        .build()
        .start();
	}
	
	
    public static String getLocalIP() throws SocketException {
        String localIP = null;
        Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address) {
                    localIP = ip.getHostAddress();
                    if (!"127.0.0.1".equalsIgnoreCase(localIP)) {
                        return localIP;
                    }
                }
            }
        }
        return localIP;
    }
}

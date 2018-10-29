package Server;

/**
 * Instantiate server object, generating a new RMIManager and a new SocketManager
 */
public class Server {
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.WARNING);
    }
}
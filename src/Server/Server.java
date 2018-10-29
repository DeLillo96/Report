package Server;

import Server.Remote.RemoteManager;

/**
 * Instantiate server object, generating a new RemoteManager
 */
public class Server {
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.WARNING);

        RemoteManager rmiManager = new RemoteManager();
        System.out.println("Server listening on port: " + rmiManager.getPort());
    }
}
package jeong.pro.messenger.server.authentication;

import java.util.ArrayList;

public class AuthenticationSessionContainer {
    /**
     * Singleton
     */
    private static AuthenticationSessionContainer authenticationSessionContainerInstance = new AuthenticationSessionContainer();
    private AuthenticationSessionContainer() {

    }

    ArrayList<AuthenticationSession> AuthenticationSessions = new ArrayList<>();

    public static AuthenticationSessionContainer getAuthenticationSessionContainerInstance() {
        return authenticationSessionContainerInstance;
    }

    public boolean getAuthorized(String id) {
        for(int i = 0; i < AuthenticationSessions.size(); i++) {
            if(AuthenticationSessions.get(i).isAuthorized(id)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void appendSession(String id, String password) {
        AuthenticationSessions.add(new AuthenticationSession(id, password));
    }

    public void deleteSession(String id) {
        for(int i = 0; i < AuthenticationSessions.size(); i++) {
            if(AuthenticationSessions.get(i).getID().equals(id)) {
                AuthenticationSessions.remove(i);
            }
        }
    }

    public void setAuthorized(String id) {
        for(int i = 0; i < AuthenticationSessions.size(); i++) {
            if(AuthenticationSessions.get(i).getID().equals(id)) {
                AuthenticationSessions.get(i).setAuthorized(true);
            }
        }
    }
}

class AuthenticationSession {
    private boolean isAuthorized = false;

    private String id = "";
    private String password = "";
    AuthenticationSession(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public boolean isAuthorized(String ID) {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        this.isAuthorized = authorized;
    }

    public String getID() {
        return id;
    }
}

package rao.saikrishna.apps.credmgr.api.model;


import java.io.Serializable;

public class AuthResponse implements Serializable {
    private String authToken;

    public AuthResponse(String authToken) {
        this.authToken = authToken;
    }

    public AuthResponse() {
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}

package rao.saikrishna.apps.credmgr.api.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AuthRequest implements Serializable {

    @NotNull(message = "userName cannot be null")
    private String userName;
    @NotNull(message = "password cannot be null")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

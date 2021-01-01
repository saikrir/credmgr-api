/* Sai Katterishetty (C) 2021 */
package rao.saikrishna.apps.credmgr.api.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

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

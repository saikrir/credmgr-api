/* Sai Katterishetty (C) 2021 */
package rao.saikrishna.apps.credmgr.api.model.core;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "system_credentials")
public class SystemCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sys_nm", nullable = false)
    private String systemName;

    @Column(name = "sys_user_id", nullable = false)
    private String systemUserId;

    @Column(name = "sys_password", nullable = false)
    private String systemPassword;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "app_user", nullable = false)
    private String appUser;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getSystemPassword() {
        return systemPassword;
    }

    public void setSystemPassword(String systemPassword) {
        this.systemPassword = systemPassword;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

package rao.saikrishna.apps.credmgr.api.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rao.saikrishna.apps.credmgr.api.model.SystemCredentialRO;
import rao.saikrishna.apps.credmgr.api.model.core.SystemCredentials;

import java.time.LocalDateTime;

@Component
public class ModelMapper {

    @Autowired
    private CryptoUtils cryptoUtils;

    public SystemCredentials toCoreModel(SystemCredentialRO systemCredentialRO) {
        SystemCredentials systemCredentials = new SystemCredentials();
        systemCredentials.setSystemUserId(systemCredentialRO.getUserId());
        systemCredentials.setSystemPassword(cryptoUtils.encryptText(systemCredentialRO.getPassword()));
        systemCredentials.setSystemName(systemCredentialRO.getSystemName());
        systemCredentials.setLastUpdated(LocalDateTime.now());
        systemCredentials.setId(systemCredentialRO.getId());
        if (StringUtils.isNotEmpty(systemCredentialRO.getDescription())) {
            systemCredentials.setAdditionalInfo(systemCredentialRO.getDescription());
        }
        if (systemCredentialRO.getLastUpdated() != null) {
            systemCredentials.setLastUpdated(systemCredentialRO.getLastUpdated());
        }
        return systemCredentials;
    }

    public SystemCredentialRO fromCoreModel(SystemCredentials systemCredentials) {
        SystemCredentialRO systemCredentialRO = new SystemCredentialRO();
        systemCredentialRO.setSystemName(systemCredentials.getSystemName());
        systemCredentialRO.setUserId(systemCredentials.getSystemUserId());
        systemCredentialRO.setPassword(cryptoUtils.decrypt(systemCredentials.getSystemPassword()));
        systemCredentialRO.setDescription(systemCredentials.getAdditionalInfo());
        systemCredentialRO.setLastUpdated(systemCredentials.getLastUpdated());
        systemCredentialRO.setId(systemCredentials.getId());
        return systemCredentialRO;
    }

    public void copyValuesToModel(SystemCredentialRO systemCredentialRO, SystemCredentials systemCredentials) {
        systemCredentials.setId(systemCredentialRO.getId());
        systemCredentials.setAdditionalInfo(systemCredentialRO.getDescription());
        systemCredentials.setSystemName(systemCredentialRO.getSystemName());
        systemCredentials.setSystemPassword(cryptoUtils.encryptText(systemCredentialRO.getPassword()));
        systemCredentials.setSystemUserId(systemCredentialRO.getUserId());
        systemCredentials.setLastUpdated(LocalDateTime.now());
    }
}

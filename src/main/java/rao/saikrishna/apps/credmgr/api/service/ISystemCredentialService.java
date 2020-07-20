package rao.saikrishna.apps.credmgr.api.service;

import rao.saikrishna.apps.credmgr.api.model.SystemCredentialRO;

import java.util.List;

public interface ISystemCredentialService {
    void newSystemCredential(SystemCredentialRO systemCredentialRO, String appUser);

    public List<SystemCredentialRO> searchSystemCredentials(String systemName, String appUser);
}

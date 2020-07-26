package rao.saikrishna.apps.credmgr.api.service;

import rao.saikrishna.apps.credmgr.api.model.SystemCredentialRO;

import java.util.List;

public interface ISystemCredentialService {

    String getSystemRecordOwner(Long id);

    SystemCredentialRO getSystemRecordSingle(Long byId);

    List<SystemCredentialRO> searchSystemCredentials(String systemName, String appUser);

    void newSystemCredential(SystemCredentialRO systemCredentialRO, String appUser);

    void removeSystemCredential(Long credentialId);

    void updateSystemCredential(SystemCredentialRO systemCredentialRO);
}

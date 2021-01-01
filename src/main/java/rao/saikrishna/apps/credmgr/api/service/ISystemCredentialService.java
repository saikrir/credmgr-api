/* Sai Katterishetty (C) 2021 */
package rao.saikrishna.apps.credmgr.api.service;

import java.util.List;
import rao.saikrishna.apps.credmgr.api.model.SystemCredentialRO;

public interface ISystemCredentialService {
    String getSystemRecordOwner(Long id);

    SystemCredentialRO getSystemRecordSingle(Long byId);

    List<SystemCredentialRO> searchSystemCredentials(String systemName, String appUser);

    void newSystemCredential(SystemCredentialRO systemCredentialRO, String appUser);

    void removeSystemCredential(Long credentialId);

    void updateSystemCredential(SystemCredentialRO systemCredentialRO);
}

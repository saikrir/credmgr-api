/* Sai Katterishetty (C) 2021 */
package rao.saikrishna.apps.credmgr.api.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rao.saikrishna.apps.credmgr.api.model.core.SystemCredentials;

@Repository
public interface ISystemCredentialRepository extends CrudRepository<SystemCredentials, Long> {
    List<SystemCredentials> findByAppUserAndSystemNameLike(String appUser, String systemName);

    List<SystemCredentials> findByAppUser(String appUser);
}

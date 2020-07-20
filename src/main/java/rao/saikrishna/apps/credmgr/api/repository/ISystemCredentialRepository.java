package rao.saikrishna.apps.credmgr.api.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rao.saikrishna.apps.credmgr.api.model.core.SystemCredentials;

import java.util.List;

@Repository
public interface ISystemCredentialRepository extends CrudRepository<SystemCredentials, Long> {
    List<SystemCredentials> findByAppUserAndSystemNameLike(String appUser, String systemName);

    List<SystemCredentials> findByAppUser(String appUser);
}

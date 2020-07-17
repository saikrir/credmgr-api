package rao.saikrishna.apps.credmgr.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rao.saikrishna.apps.credmgr.api.model.ApplicationUser;

import java.util.Optional;

@Repository
public interface IApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUserName(String userName);
}

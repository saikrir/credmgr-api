/* Sai Katterishetty (C) 2021 */
package rao.saikrishna.apps.credmgr.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rao.saikrishna.apps.credmgr.api.model.core.ApplicationUser;

@Repository
public interface IApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUserName(String userName);
}

package rao.saikrishna.apps.credmgr.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rao.saikrishna.apps.credmgr.api.model.ApplicationUser;
import rao.saikrishna.apps.credmgr.api.repository.IApplicationUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationUserService implements UserDetailsService {


    @Autowired
    private IApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ApplicationUser> optionalApplicationUser = applicationUserRepository.findByUserName(username);

        optionalApplicationUser.orElseThrow(() -> {
            throw new UsernameNotFoundException((username + " was not found"));
        });

        return optionalApplicationUser.map(applicationUser -> {
            List<GrantedAuthority> grantedAuthorities = applicationUser.getApplicationRoles()
                    .stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toCollection(ArrayList::new));
            return new User(applicationUser.getUserName(), applicationUser.getPassword(), grantedAuthorities);
        }).get();

    }
}

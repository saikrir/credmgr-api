package rao.saikrishna.apps.credmgr.api.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rao.saikrishna.apps.credmgr.api.model.AuthRequest;
import rao.saikrishna.apps.credmgr.api.model.AuthResponse;
import rao.saikrishna.apps.credmgr.api.utils.TokenUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/")
public class UserResourceController {

    private static final Logger logger = LoggerFactory.getLogger(UserResourceController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> authorize(@Valid @RequestBody AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword());

        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            logger.info("Authentication for user {} has succeeded ", authRequest.getUserName());
        }
        catch(BadCredentialsException badCredentialsException) {
            logger.error("Invalid Username and password combination {} ", authRequest.getUserName());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
        String token = tokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

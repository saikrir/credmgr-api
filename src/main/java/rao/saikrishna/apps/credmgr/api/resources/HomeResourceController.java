package rao.saikrishna.apps.credmgr.api.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rao.saikrishna.apps.credmgr.api.repository.IApplicationUserRepository;

@RestController
public class HomeResourceController {

    private static final Logger logger = LoggerFactory.getLogger(HomeResourceController.class);

    @Autowired
    IApplicationUserRepository appUserRepo;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello!");
    }
}

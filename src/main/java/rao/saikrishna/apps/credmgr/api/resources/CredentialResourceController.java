package rao.saikrishna.apps.credmgr.api.resources;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rao.saikrishna.apps.credmgr.api.model.SystemCredentialRO;
import rao.saikrishna.apps.credmgr.api.service.ISystemCredentialService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CredentialResourceController {

    @Autowired
    private ISystemCredentialService systemCredentialService;

    @RequestMapping("/system-credentials")
    public ResponseEntity createCredential(@Valid @RequestBody SystemCredentialRO systemCredentialRO) {
        systemCredentialService.newSystemCredential(systemCredentialRO, getPrincipal());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/system-credentials/search")
    public ResponseEntity<List<SystemCredentialRO>> searchCredentials(@RequestParam("systemName") String systemName) {
        if (StringUtils.isNotEmpty(systemName) && StringUtils.length(systemName) > 3) {
            return ResponseEntity.ok().body(systemCredentialService.searchSystemCredentials(systemName, getPrincipal()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    protected String getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

package rao.saikrishna.apps.credmgr.api.resources;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(CredentialResourceController.class);

    @PostMapping("/system-credentials")
    public ResponseEntity createCredential(@Valid @RequestBody SystemCredentialRO systemCredentialRO) {
        systemCredentialService.newSystemCredential(systemCredentialRO, getPrincipal());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/system-credentials")
    public ResponseEntity<List<SystemCredentialRO>> searchCredentials(@RequestParam("systemName") String systemName) {
        if (StringUtils.isNotEmpty(systemName) && StringUtils.length(systemName) >= 3) {
            return ResponseEntity.ok().body(systemCredentialService.searchSystemCredentials(systemName, getPrincipal()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/system-credentials/{id}")
    public ResponseEntity getCredentials(@PathVariable("id") String systemId) {
        Long credentialId = Long.valueOf(systemId);

        if (!isUserTheRecordOwner(credentialId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(getPrincipal() + " is not authorized to Update this credential ");
        } else {
            return ResponseEntity.ok(systemCredentialService.getSystemRecordSingle(credentialId));
        }
    }


    @DeleteMapping("/system-credentials/{id}")
    public ResponseEntity deleteCredential(@PathVariable("id") String systemId) {

        ResponseEntity returnEntity = ResponseEntity.ok().build();
        Long credentialId = Long.valueOf(systemId);
        if (isUserTheRecordOwner(credentialId)) {
            systemCredentialService.removeSystemCredential(credentialId);
            logger.info("System Credential was DELETED by {} " + getPrincipal());
        } else {
            logger.info("{} is not authorized to delete this credential ", getPrincipal());
            returnEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).body(getPrincipal() + " is not authorized to Update this credential ");
        }
        return returnEntity;
    }

    @PutMapping("/system-credentials/{id}")
    public ResponseEntity updateCredential(@PathVariable("id") String systemId,
                                           @Valid @RequestBody SystemCredentialRO systemCredentialRO) {
        ResponseEntity returnEntity = ResponseEntity.ok().build();
        Long credentialId = Long.valueOf(systemId);
        if (isUserTheRecordOwner(credentialId)) {
            systemCredentialRO.setId(credentialId);
            systemCredentialService.updateSystemCredential(systemCredentialRO);
            logger.info("System Credential was {} was updated by {}", systemCredentialRO.getSystemName(), getPrincipal());
        } else {
            logger.info("{} is not authorized to Update this credential ", getPrincipal());

            returnEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).body(getPrincipal() + " is not authorized to Update this credential ");
        }

        return returnEntity;
    }

    protected String getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    protected boolean isUserTheRecordOwner(Long recordId) {
        String recordOwner = systemCredentialService.getSystemRecordOwner(recordId);
        String appUser = getPrincipal();
        return StringUtils.equals(recordOwner, appUser);
    }

}

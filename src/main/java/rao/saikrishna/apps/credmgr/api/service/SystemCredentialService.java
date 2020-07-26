package rao.saikrishna.apps.credmgr.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rao.saikrishna.apps.credmgr.api.exceptions.CredentialManagerRecordNotFoundException;
import rao.saikrishna.apps.credmgr.api.model.SystemCredentialRO;
import rao.saikrishna.apps.credmgr.api.model.core.SystemCredentials;
import rao.saikrishna.apps.credmgr.api.repository.ISystemCredentialRepository;
import rao.saikrishna.apps.credmgr.api.utils.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SystemCredentialService implements ISystemCredentialService {

    private final Logger logger = LoggerFactory.getLogger(SystemCredentialService.class);

    @Autowired
    private ISystemCredentialRepository systemCredentialRepository;

    @Autowired
    private ModelMapper modelMapper;


    public void newSystemCredential(SystemCredentialRO systemCredentialRO, String appUser) {
        SystemCredentials systemCredentialsEntity = modelMapper.toCoreModel(systemCredentialRO);
        systemCredentialsEntity.setAppUser(appUser);
        systemCredentialRepository.save(systemCredentialsEntity);
        logger.info("New System Credential {} was created by {}", systemCredentialRO.getSystemName(), appUser);
    }

    public List<SystemCredentialRO> searchSystemCredentials(String systemName, String appUser) {
        String searchTerm = new StringBuilder().append("%").append(systemName).append("%").toString();
        List<SystemCredentials> credentials = systemCredentialRepository.findByAppUserAndSystemNameLike(appUser, searchTerm);
        return credentials.stream().map(modelMapper::fromCoreModel).collect(Collectors.toList());
    }


    public void removeSystemCredential(Long credentialId) {
        systemCredentialRepository.deleteById(credentialId);
    }

    public void updateSystemCredential(SystemCredentialRO systemCredentialRO) {
        Long credId = systemCredentialRO.getId();
        Optional<SystemCredentials> systemCredentialsOptional = systemCredentialRepository.findById(credId);
        systemCredentialsOptional.orElseThrow(() -> {
            return new CredentialManagerRecordNotFoundException(" Unable to find record with  " + credId);
        });
        SystemCredentials systemCredentials = systemCredentialsOptional.get();
        modelMapper.copyValuesToModel(systemCredentialRO, systemCredentials);
        systemCredentialRepository.save(systemCredentials);
    }

    public String getSystemRecordOwner(Long id) {
        return getSystemCredentialSingle(id).getAppUser();
    }

    public SystemCredentialRO getSystemRecordSingle(Long byId) {
        SystemCredentials systemCredentials = getSystemCredentialSingle(byId);
        return modelMapper.fromCoreModel(systemCredentials);
    }

    protected SystemCredentials getSystemCredentialSingle(Long id) {
        Optional<SystemCredentials> systemCredentialsOptional = systemCredentialRepository.findById(id);

        systemCredentialsOptional.orElseThrow(() -> {
            return new CredentialManagerRecordNotFoundException("Cannot find SystemCredential with Id " + id);
        });

        return systemCredentialsOptional.get();
    }
}

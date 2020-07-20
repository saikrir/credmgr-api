package rao.saikrishna.apps.credmgr.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rao.saikrishna.apps.credmgr.api.model.SystemCredentialRO;
import rao.saikrishna.apps.credmgr.api.model.core.SystemCredentials;
import rao.saikrishna.apps.credmgr.api.repository.ISystemCredentialRepository;
import rao.saikrishna.apps.credmgr.api.utils.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemCredentialService implements ISystemCredentialService {

    @Autowired
    private ISystemCredentialRepository systemCredentialRepository;

    @Autowired
    private ModelMapper modelMapper;


    public void newSystemCredential(SystemCredentialRO systemCredentialRO, String appUser) {
        SystemCredentials systemCredentialsEntity = modelMapper.toCoreModel(systemCredentialRO);
        systemCredentialsEntity.setAppUser(appUser);
        systemCredentialRepository.save(systemCredentialsEntity);
    }

    public List<SystemCredentialRO> searchSystemCredentials(String systemName, String appUser) {
        String searchTerm = new StringBuilder().append("%").append(systemName).append("%").toString();
        List<SystemCredentials> credentials = systemCredentialRepository.findByAppUserAndSystemNameLike(appUser, searchTerm);
        return credentials.stream().map(modelMapper::fromCoreModel).collect(Collectors.toList());
    }

}

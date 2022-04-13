package com.booknet.api.authentication.startup;

import com.booknet.api.authentication.model.AppRole;
import com.booknet.api.authentication.model.EAppRole;
import com.booknet.api.authentication.repository.IAppRoleRepository;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRoleDataLoader implements ApplicationRunner {
    IAppRoleRepository appRoleRepository;

    @Autowired
    public AppRoleDataLoader(IAppRoleRepository repository) {
        this.appRoleRepository = repository;
    }

    public void run(ApplicationArguments args) {
        for (EAppRole role : EAppRole.values()) {
            Utils.log.print(this, "[Startup] add role to database", role.getClass().toString());
            if (appRoleRepository.findByName(role).isEmpty()) {
                appRoleRepository.save(new AppRole(role));
            }
            else {
                Utils.log.print(this, "[Startup] role has existed", role.getClass().toString());
            }
        }
    }
}

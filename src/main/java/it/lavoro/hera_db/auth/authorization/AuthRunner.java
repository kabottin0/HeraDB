package it.lavoro.hera_db.auth.authorization;

import it.lavoro.hera_db.auth.app_user.AppUser;
import it.lavoro.hera_db.auth.app_user.AppUserService;
import it.lavoro.hera_db.auth.app_user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Creazione dell'utente admin
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {
            appUserService.registerDefault("admin", "adminpwd", Set.of(Role.ROLE_ADMIN));
        }

        // Creazione dell'utente user
        Optional<AppUser> normalUser = appUserService.findByUsername("user");
        if (normalUser.isEmpty()) {
            appUserService.registerDefault("user", "userpwd", Set.of(Role.ROLE_USER));
        }

        // Creazione dell'utente location


    }
}

package com.board.basic.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void signUp(String username, String nickname, String password1) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setNickname(nickname);
        siteUser.setPassword(passwordEncoder.encode(password1));
        siteUser.setCreateDate(LocalDateTime.now());

        this.userRepository.save(siteUser);
    }

    public void login(String username, String password) {

    }
    public SiteUser getUser(String username){
       Optional<SiteUser> os =  this.userRepository.findByusername(username);
       if(os.isEmpty()){
           throw new RuntimeException();
       }
       return os.get();
    }
}

package com.streetdrop.security.provider;

import com.streetdrop.user.User;
import com.streetdrop.domains.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IdfvUserDetailsService {

    private UserService userService;

    public UserDetails loadUserByUsername(String idfv) {
        User user = userService.getOrCreateUserByIdfv(idfv);
        return new SecurityUserDetails(user);
    }
}

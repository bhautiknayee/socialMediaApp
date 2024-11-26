package com.Bhautik.socialMediaApp.service.serviceImpl;

import com.Bhautik.socialMediaApp.Repository.userRepository;
import com.Bhautik.socialMediaApp.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class userDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private userRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        user user = userRepository.findByUserName(userName);
        if(user != null){
            UserDetails userDetails = User.builder().username(user.getUserName()).password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])).build();
            return userDetails;
        }
        throw  new UsernameNotFoundException("UserName is not found with give UserName" + userName);
    }
}

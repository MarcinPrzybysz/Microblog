package com.przybysz.microblog.service;

import com.przybysz.microblog.entity.User;
import com.przybysz.microblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//@Service("userDetailsService")
public interface MyUserDetailsService extends UserDetailsService {



}
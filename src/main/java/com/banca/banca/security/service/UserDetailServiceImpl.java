package com.banca.banca.security.service;

import com.banca.banca.entity.CustomerData;
import com.banca.banca.entity.Role;
import com.banca.banca.repository.CustomerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private CustomerDataRepository customerDataRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomerData customerData = customerDataRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return new User(customerData.getUserName(), customerData.getPassword(), mapRolesToAuthorities(customerData.getRoles()));
    }

    /**
     * Metodo di utility che mi permette di convertire le role in GRANTED AUTHORITIES (autorità concesse)
     * perchènel metodo loadUserByUsername lo User ha come parametri del costruttore
     * User(String username, String password, Collection<GrantedAuthority> authorities)
     * quindi queste role andranno inserite nella Collection
     *
     * @param roles
     * @return Collection<GrantedAuthority>
     */
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

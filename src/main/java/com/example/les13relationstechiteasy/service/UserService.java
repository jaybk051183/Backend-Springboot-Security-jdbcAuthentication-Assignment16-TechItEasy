package com.example.les13relationstechiteasy.service;

import com.example.les13relationstechiteasy.dto.UserDto;
import com.example.les13relationstechiteasy.model.Authority;
import com.example.les13relationstechiteasy.model.User;
import com.example.les13relationstechiteasy.repository.UserRepository;
import com.example.les13relationstechiteasy.utils.RandomStringGenerator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*moest hier niet een annotatie?*/
@Service
public class UserService {
    /*inject de juiste repository*/

    private static UserRepository repos;

    public UserService(UserRepository repos) {
        this.repos = repos;
    }

    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = repos.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public static UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = repos.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return repos.existsById(username);
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User newUser = repos.save(toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        repos.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!repos.existsById(username)) throw new UsernameNotFoundException(username);
        User user = repos.findById(username).get();
        user.setPassword(newUser.getPassword());
        /*repo*/repos.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!repos.existsById(username)) throw new UsernameNotFoundException(username);
        User user = repos.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!repos.existsById(username)) throw new UsernameNotFoundException(username);
        User user = repos.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        repos.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!repos.existsById(username)) throw new UsernameNotFoundException(username);
        User user = repos.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        repos.save(user);
    }

    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }

}
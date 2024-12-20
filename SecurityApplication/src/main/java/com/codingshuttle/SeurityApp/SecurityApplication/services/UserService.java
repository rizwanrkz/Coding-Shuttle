package com.codingshuttle.SeurityApp.SecurityApplication.services;

import com.codingshuttle.SeurityApp.SecurityApplication.dto.LoginDTO;
import com.codingshuttle.SeurityApp.SecurityApplication.dto.SignUpDTO;
import com.codingshuttle.SeurityApp.SecurityApplication.dto.UserDTO;
import com.codingshuttle.SeurityApp.SecurityApplication.entities.UserEntity;
import com.codingshuttle.SeurityApp.SecurityApplication.exception.ResourceNotFoundException;
import com.codingshuttle.SeurityApp.SecurityApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("No User found with provided " + username));
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<UserEntity> user = userRepository.findByEmail(signUpDTO.getEmail());
        if(user.isPresent()) {
            throw new BadCredentialsException("User with email already exists " + signUpDTO.getEmail());
        }

        UserEntity toBeCreatedUser = modelMapper.map(signUpDTO, UserEntity.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
        UserEntity savedUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }


}

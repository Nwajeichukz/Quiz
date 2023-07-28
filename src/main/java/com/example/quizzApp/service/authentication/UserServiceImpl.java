package com.example.quizzApp.service.authentication;

import com.example.quizzApp.Exception.ApiException;
import com.example.quizzApp.dto.AuthenticationRequest;
import com.example.quizzApp.dto.QuizAppResponse;
import com.example.quizzApp.dto.RegisterRequest;
import com.example.quizzApp.entity.Role;
import com.example.quizzApp.entity.User;
import com.example.quizzApp.repository.RoleRepository;
import com.example.quizzApp.repository.UserRepository;
import com.example.quizzApp.service.JwtService;
import com.example.quizzApp.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final MyUserDetailsService myUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public QuizAppResponse<Map<String, Object>> createUser(RegisterRequest request) {
        boolean check = userRepository.existsByUsernameOrEmail(request.getUsername(), request.getEmail());

        if (check) throw new ApiException("User already exists, login to continue");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (!request.getPassword().equals(request.getConfirmPassword()))
            return new QuizAppResponse<>(-1, "passwords do not correspond");

        Role roles = roleRepository.findByName("USER").orElseThrow();
        user.setRoles(roles);

        userRepository.save(user);


        return  new QuizAppResponse<>(0,"User Successfully Saved", Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail()
        ));
    }

    public QuizAppResponse<?> getAllU(){
        List<User> user = userRepository.findAll();
        return new QuizAppResponse<>(0, "User Successfully saved", user);
    }

    public QuizAppResponse<Map<String, Object>> createAdmin(RegisterRequest request) {
        boolean check = userRepository.existsByUsernameOrEmail(request.getUsername(), request.getEmail());

        if (check) throw new ApiException("User already exists, login to continue");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (!request.getPassword().equals(request.getConfirmPassword()))
            return new QuizAppResponse<>(-1, "passwords do not correspond");

        Role roles = roleRepository.findByName("ADMIN").get();
        user.setRoles(roles);

        userRepository.save(user);

        return new QuizAppResponse<>(0, "Admin Successfully Saved",Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail()
        ));
    }

    public QuizAppResponse<String> login(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        var user = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        var jwtToken = jwtService.generateToken(user);

        return  new QuizAppResponse<>(0,"Successfully logged in", jwtToken);

    }
}

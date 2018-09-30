package com.aliniribeiro.admin.api.controller.user;

import com.aliniribeiro.admin.api.common.Response;
import com.aliniribeiro.admin.api.common.exceptions.ExceptionType;
import com.aliniribeiro.admin.api.common.util.Spring;
import com.aliniribeiro.admin.api.controller.user.contracts.UserByProfileDTO;
import com.aliniribeiro.admin.api.controller.user.contracts.UserDTO;
import com.aliniribeiro.admin.api.controller.APIPaths;
import com.aliniribeiro.admin.api.model.user.UserEntity;
import com.aliniribeiro.admin.api.model.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(APIPaths.USER)
@CrossOrigin(origins = "*")
public class UserController {


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<UserEntity>> createUser(HttpServletRequest request, @RequestBody UserEntity user, BindingResult result) {
        Response<UserEntity> response = new Response<UserEntity>();
        try {
            UserEntity userPersisted = Spring.bean(UserService.class).createUser(user.getEmail(), user.getPassword(), user.getName(), user.getProfile());
            response.setData(userPersisted);
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<UserDTO>> findById(@PathVariable("id") String id) {
        Response<UserDTO> response = new Response<UserDTO>();
        try {
            Optional<UserDTO> user = Spring.bean(UserService.class).findById(id);
            if (user.isPresent()){
                response.setData(user.get());
            } else {
                response.getErrors().add(ExceptionType.ENTITY_NOT_FOUND.name());
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<String>> delete(@PathVariable("id") UUID id) {
        Response<String> response = new Response<String>();
        UserEntity user = Spring.bean(UserRepository.class).findById(id);
        if (user == null) {
            response.getErrors().add("Registered id not found: " + id);
            return ResponseEntity.badRequest().body(response);
        }
        Spring.bean(UserRepository.class).delete(user);
        return ResponseEntity.ok(new Response<String>());
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<List<UserDTO>>> findAllUsers() {
        Response<List<UserDTO>> response = new Response<List<UserDTO>>();
        try {
            response.setData(Spring.bean(UserService.class).findAllUsers());
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/getTotalByProfile")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<List<UserByProfileDTO>>> getTotalByProfile() {
        Response<List<UserByProfileDTO>> response = new Response<List<UserByProfileDTO>>();
        try {
            response.setData(Spring.bean(UserService.class).getTotalByProfile());
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/totalUsers")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Response<String>> getTotalUsers() {
        Response<String> response = new Response<String>();
        try {
            Optional<String> serviceResponse = Spring.bean(UserService.class).getTotalUsers();
            if (serviceResponse.isPresent()) {
                response.setData(serviceResponse.get());
            }
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

}

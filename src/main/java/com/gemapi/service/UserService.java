package com.gemapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gemapi.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    void deleteUser(Long id);
    Page<UserDTO> findAll(Pageable pageable);
    UserDTO updateUserRole(Long id, String role);
} 
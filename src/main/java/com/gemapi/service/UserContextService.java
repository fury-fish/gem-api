package com.gemapi.service;

import com.gemapi.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Service
public class UserContextService {
    private static final String USER_ATTRIBUTE = "currentUser";

    public UserDTO getCurrentUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            HttpSession session = attr.getRequest().getSession(false);
            if (session != null) {
                return (UserDTO) session.getAttribute(USER_ATTRIBUTE);
            }
        }
        return null;
    }

    public void setCurrentUser(UserDTO user) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute(USER_ATTRIBUTE, user);
        }
    }
} 
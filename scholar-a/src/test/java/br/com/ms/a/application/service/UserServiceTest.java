package br.com.ms.a.application.service;

import br.com.ms.a.domain.model.user.User;
import br.com.ms.a.domain.model.user.UserResponse;
import br.com.ms.a.domain.model.userMicroserviceRole.UserMicroserviceRole;
import br.com.ms.a.infra.FeignCliente.UserMicroserviceRoleFeign;
import br.com.ms.a.infra.exceptions.UserMicroserviceRoleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @InjectMocks
    private UserService _userService;

    @Mock
    private UserMicroserviceRoleFeign _userMicroserviceRoleFeign;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindUserById() {
        UserMicroserviceRole userMicroserviceRole = new UserMicroserviceRole();
        User user = new User();
        user.setName("Test User");
        userMicroserviceRole.setUser(user);
        userMicroserviceRole.setRole("Test Role");

        when(_userMicroserviceRoleFeign.findById(1L, 1L)).thenReturn(userMicroserviceRole);

        UserResponse userResponse = _userService.findUserById(1L, 1L);

        assertEquals("Test User", userResponse.getName());
        assertEquals("Test Role", userResponse.getRole());
    }

        @Test
        public void testFindUserByIdException() {
            MockitoAnnotations.openMocks(this);

            when(_userMicroserviceRoleFeign.findById(1L, 1L)).thenThrow(new RuntimeException());

            assertThrows(UserMicroserviceRoleNotFoundException.class, () -> _userService.findUserById(1L, 1L));
        }
}


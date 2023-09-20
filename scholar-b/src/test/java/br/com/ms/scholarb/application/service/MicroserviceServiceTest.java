package br.com.ms.scholarb.application.service;

import br.com.ms.scholarb.domain.model.microsservice.Microservice;
import br.com.ms.scholarb.domain.model.microsservice.MicroserviceResponse;
import br.com.ms.scholarb.domain.model.user.UserResponse;
import br.com.ms.scholarb.infra.FeingClient.UserMicroserviceRoleFeign;
import br.com.ms.scholarb.infra.exceptions.UserMicroserviceRoleNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MicroserviceServiceTest {
    @InjectMocks
    private MicroserviceService _microserviceService;

    @Mock
    private UserMicroserviceRoleFeign _userMicroserviceRoleFeign;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetMicroserviceByIdAndUsersPermissions() {
        Long microserviceId = 1L;

        Microservice mockMicroservice = new Microservice();
        mockMicroservice.setName("Test Microservice");

        UserResponse mockUser = new UserResponse();
        mockUser.setName("Test User");

        List<UserResponse> mockUsers = new ArrayList<>();
        mockUsers.add(mockUser);

        when(_userMicroserviceRoleFeign.getMicroserviceByMicroserviceId(microserviceId)).thenReturn(mockMicroservice);
        when(_userMicroserviceRoleFeign.getUserByMicroserviceId(microserviceId)).thenReturn(mockUsers);

        MicroserviceResponse response = _microserviceService.getMicroserviceByIdAndUsersPermissions(microserviceId);

        assertEquals(microserviceId, response.getId());
        assertEquals(mockMicroservice.getName(), response.getName());
        assertEquals(mockUsers, response.getUsers());
    }
    @Test
    public void testGetMicroserviceByIdAndUsersPermissionsException() {
        Long microserviceId = 1L;

        when(_userMicroserviceRoleFeign.getMicroserviceByMicroserviceId(microserviceId)).thenThrow(new RuntimeException());

        assertThrows(UserMicroserviceRoleNotFoundException.class, () -> {
            _microserviceService.getMicroserviceByIdAndUsersPermissions(microserviceId);
        });
    }

}
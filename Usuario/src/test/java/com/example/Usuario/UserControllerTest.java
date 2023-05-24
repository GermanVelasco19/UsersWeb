package com.example.Usuario;

import com.example.Usuario.Controllers.UserController;
import com.example.Usuario.Entities.User;
import com.example.Usuario.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Arrange
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(userService.SearchAll()).thenReturn(users);

        // Act
        ArrayList<User> result = userController.getAll();

        // Assert
        verify(userService, times(1)).SearchAll();
        // Add additional assertions based on your requirements
    }

    @Test
    void testUsersPaginated() {
        // Arrange
        int page = 0;
        int size = 6;
        String order = "id";
        boolean asc = true;
        Page<User> usersPage = mock(Page.class);
        when(userService.paginateUser(any())).thenReturn(usersPage);

        // Act
        ResponseEntity<Page<User>> result = userController.usersPaginated(page, size, order, asc);

        // Assert
        verify(userService, times(1)).paginateUser(any());
        // Add additional assertions based on your requirements
    }

    @Test
    void testNewUser() {
        // Arrange
        User user = new User();

        // Act
        userController.NewUser(user);

        // Assert
        verify(userService, times(1)).Put(user);
        // Add additional assertions based on your requirements
    }

    @Test
    void testLogin_CorrectCredentials() {
        // Arrange
        String username = "user";
        String password = "password";
        ArrayList<User> users = new ArrayList<>();
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        users.add(u);
        when(userService.SearchAll()).thenReturn(users);

        // Act
        String result = userController.login(username, password);

        // Assert
        verify(userService, times(1)).SearchAll();
        // Add additional assertions based on your requirements
    }

    @Test
    void testLogin_IncorrectCredentials() {
        // Arrange
        String username = "user";
        String password = "password";
        ArrayList<User> users = new ArrayList<>();
        User au = new User();
        au.setUsername("anotheruser");
        au.setPassword("anotherpassword");
        users.add(au);
        when(userService.SearchAll()).thenReturn(users);

        // Act
        String result = userController.login(username, password);

        // Assert
        verify(userService, times(1)).SearchAll();
        // Add additional assertions based on your requirements
    }

    @Test
    void testUpdateUser() {
        // Arrange
        Integer id = 1;
        User updatedUser = new User();
        updatedUser.setName_user("Updated User");
        User existingUser = new User();
        existingUser.setName_user("User");
        when(userService.findOne(id)).thenReturn(existingUser);

        // Act
        userController.updateUser(updatedUser, id);

        // Assert
        verify(userService, times(1)).findOne(id);
        verify(userService, times(1)).UpdateUser(updatedUser);
        // Add additional assertions based on your requirements
    }

    @Test
    void testUpdateUser_NonExistingUser() {
        // Arrange
        Integer id = 1;
        User updatedUser = new User();
        updatedUser.setName_user("Updated User");
        when(userService.findOne(id)).thenReturn(null);

        // Act
        userController.updateUser(updatedUser, id);

        // Assert
        verify(userService, times(1)).findOne(id);
        verify(userService, never()).UpdateUser(any());
        // Add additional assertions based on your requirements
    }

    @Test
    void testDeleteUser() {
        // Arrange
        Integer id = 1;

        // Act
        userController.DeleteUser(id);

        // Assert
        verify(userService, times(1)).DeleteUser(id);
        // Add additional assertions based on your requirements
    }
}

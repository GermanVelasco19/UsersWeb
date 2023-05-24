package com.example.Usuario;

import com.example.Usuario.Entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserTest {

    @Test
    void testUserId() {
        // Arrange
        Integer userId = 1;
        User user = new User();
        user.setUser_id(userId);

        // Act
        Integer result = user.getUser_id();

        // Assert
        assertEquals(userId, result);
    }

    @Test
    void testNameUser() {
        // Arrange
        String nameUser = "John";
        User user = new User();
        user.setName_user(nameUser);

        // Act
        String result = user.getName_user();

        // Assert
        assertEquals(nameUser, result);
    }
}

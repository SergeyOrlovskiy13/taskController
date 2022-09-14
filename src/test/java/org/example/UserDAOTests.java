package org.example;

import org.example.controller.UserController;
import org.example.dao.UserDAO;
import org.example.models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserDAOTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserController userController;

    @Test
    public void test() throws Exception{
        assertThat(userController).isNotNull();
    }

    @Test
    void save() {
        User user = new User(1,"serg", "in", 1985,12,13,"test@mail.test", "kiev", 123);
        userDAO.save(user);
        User user1 = userDAO.show(1);

        assertEquals(user1, user );
    }

    @Test
    void getAllUsers(){
        User user = new User(3,"serg", "in", 1985,12,13,"test@mail.test", "kiev", 123);
        User user2 = new User(4,"ivan", "ivanow", 2000,12,13,"test@mail.test", "kiev", 123);
        userDAO.save(user);
        userDAO.save(user2);

        List<User> users= userDAO.getAllUsers();
        assertEquals(3,users.size());
    }

    @Test
    void show(){
        User user = new User(5,"serg", "in", 1985,12,13,"test@mail.test", "kiev", 123);
        userDAO.save(user);
        User user1 = userDAO.show(5);

        assertEquals(user, user1 );
    }

    @Test
    void update(){
        User user = new User(6,"serg", "in", 1985,12,13,"test@mail.test", "kiev", 123);
        User user2 = new User(6,"ivan", "ivanow", 2000,12,13,"test@mail.test", "kiev", 123);

        userDAO.save(user);
        userDAO.update(6,user2);

        User targetUser= userDAO.show(6);

        assertNotEquals(targetUser, user);
        assertEquals(user2,targetUser);
    }

    @Test
    void delete(){
        User user = new User(8,"serg", "in", 1985,12,13,"test@mail.test", "kiev", 123);
        userDAO.save(user);
        userDAO.delete(8);
        User target = userDAO.show(8);
        assertNull(target);
    }

    @Test
    void showBetweenYear(){
        User user = new User(9,"serg", "in", 1985,12,13,"test@mail.test", "kiev", 123);
        userDAO.save(user);
        User target = userDAO.showBetweenYear(1900,2000);
        assertEquals(user,target);
    }


}

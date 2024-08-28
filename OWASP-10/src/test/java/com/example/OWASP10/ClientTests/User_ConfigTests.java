package com.example.OWASP10.ClientTests;

import Client.User_Config;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class User_ConfigTests {

    @Test
    public void testUsernameIsNotNull(){
        User_Config user_config = new User_Config("Test name", "");
        assertNotEquals(null, user_config.getPassword());
    }
    @Test
    public void testUsername(){
        User_Config user_config = new User_Config("Test name", "");
        assertEquals("Test name", user_config.getUsername());
    }

    @Test
    public void testPasswordIsNotNull(){
        User_Config user_config = new User_Config("", "testPassword1");
        assertNotEquals(null, user_config.getPassword());
    }

    @Test
    public void testPassword(){
        User_Config user_config = new User_Config("", "testPassword1");
        assertEquals("testPassword1", user_config.getPassword());
    }

    @Test
    public void testProjectUrlIsNotNull(){
        User_Config user_config = new User_Config("", "","http://localhost:9000","");
        assertNotEquals(null, user_config.getProjectUrl());
    }

    @Test
    public void testProjectUrl(){
        User_Config user_config = new User_Config("", "","http://localhost:9000","");
        assertEquals("http://localhost:9000", user_config.getProjectUrl());
    }

    @Test
    public void testProjectKeyNotNull(){
        User_Config user_config = new User_Config("", "","","some-project-key");
        assertNotEquals(null, user_config.getProjectKey());
    }

    @Test
    public void testProjectKey(){
        User_Config user_config = new User_Config("", "","","some-project-key");
        assertEquals("some-project-key", user_config.getProjectKey());
    }
}

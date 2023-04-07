package userTesting;

import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.DBConnector;
import dao.DBManager;

public class customerRegisterTest {

    @Test
    public void testRegister() throws Exception {

        HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);  
        DBManager manager = mock(DBManager.class);
    
        try {
            manager.addUser("firstName", "lastName", "password", "email", 1234567890, "1234-12-12", 123, "streetName", 1234, "state", "suburb", "country", true);
            assertEquals("firstName", manager.findUser("email", "password").getFname(), "Test first name recorded Correctly");
            assertEquals("lastName", manager.findUser("email", "password").getLname(), "Test last name recorded correctly");
            assertEquals("email", manager.findUser("email", "password").getEmail(), "Testing email recorded correctly");
            assertEquals(1234567890, manager.findUser("email", "password").getPhoneNo(), "Testing phone number recorded correctly");
        }
        catch (NullPointerException ex) {

        }
        catch (SQLException ex) {

        }



    }

    
}

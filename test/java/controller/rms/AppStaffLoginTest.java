package controller.rms;

import static org.mockito.Mockito.*;

import dao.DBManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import model.AppStaff;
import org.junit.Test;

import java.sql.Statement;

public class AppStaffLoginTest {

    // Test for Correct Credentials
    @Test
    public void testCorrectCredentials() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        DBManager manager = mock(DBManager.class);
        AppStaff appStaff = manager.appStaffLogin("benzchua31@gmail.com", "ro0T!ro0T!");

        when(req.getSession()).thenReturn(mock(HttpSession.class));
        when(req.getSession().getAttribute("DBManager")).thenReturn(manager);
        when(req.getParameter("email")).thenReturn("benzchua31@gmail.com");
        when(req.getParameter("pass")).thenReturn("ro0T!ro0T!");
        when(req.getRequestDispatcher("index")).thenReturn(mock(RequestDispatcher.class));
        when(manager.appStaffLogin("benzchua31@gmail.com", "ro0T!ro0T!"))
                .thenReturn(appStaff);

        new AppStaffLoginServletPublic().doPost(req, res);

        // verify that session.setAttribute for appStaff
        verify(req.getSession(), times(1)).setAttribute("appStaff", appStaff);

        // verify that getRequestDispatcher("index") is called once
        verify(req, times(1)).getRequestDispatcher("index");
    }

    // Test for Incorrect Credentials
    @Test
    public void testIncorrectCredentials() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        DBManager manager = mock(DBManager.class);
        AppStaff appStaff = manager.appStaffLogin("benzchua@gmail.com", "rc0T!rc0B!");

        when(req.getSession()).thenReturn(mock(HttpSession.class));
        when(req.getSession().getAttribute("DBManager")).thenReturn(mock(DBManager.class));
        when(req.getParameter("email")).thenReturn("benzchua@gmail.com");
        when(req.getParameter("pass")).thenReturn("rc0T!rc0B!");
        when(req.getRequestDispatcher("index")).thenReturn(mock(RequestDispatcher.class));
        when(manager.appStaffLogin("benzchua@gmail.com", "rc0T!rc0B!"))
                .thenReturn(appStaff);

        new AppStaffLoginServletPublic().doPost(req, res);

        // verify that session.setAttribute for credentialsError
        verify(req.getSession(), times(1))
                .setAttribute("credentialsError", "Incorrect Credentials");

        // verify that getRequestDispatcher("index") is called once
        verify(req, times(1)).getRequestDispatcher("index");
    }

    // Test for Incorrect Format
    // (There's no need to test for SQLException because we have done server-side validation
    // before passing data to DBManager)
    @Test
    public void testIncorrectFormat() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);

        when(req.getSession()).thenReturn(mock(HttpSession.class));
        when(req.getParameter("email")).thenReturn("benzchuagmail.com");
        when(req.getParameter("pass")).thenReturn("abcdefghgh");
        when(req.getRequestDispatcher("appStaffLogin.jsp")).thenReturn(mock(RequestDispatcher.class));

        new AppStaffLoginServletPublic().doPost(req, res);

        // verify error attributes have been set
        verify(req.getSession(), times(1)).setAttribute("emailError", "Incorrect Email Format");
        verify(req.getSession(), times(1)).
                setAttribute("passwordError", "At least 1 Uppercase, " +
                        "1 Number, 1 Special Character and 8 characters long");

        // verify that getRequestDispatcher("appStaffLogin.jsp") is called once
        verify(req, times(1)).getRequestDispatcher("appStaffLogin.jsp");
    }

}

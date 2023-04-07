package controller.rms;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import model.Order;
import model.Delivery;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import controller.CreateUpdateDelivery;
import dao.DBManager;

public class CreateDeliveryTest extends Mockito {
    private CreateUpdateDelivery servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private DBManager manager;
    private Order order;
    private Delivery delivery;

    @Before
    public void setUp() {
        servlet = new CreateUpdateDelivery();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        manager = mock(DBManager.class);
        order = new Order(101010, 989898, 303030, null, 0, "Submitted", 0, null, null);
        delivery = new Delivery(454545, order.getOrderID(), 454545, "9 Delhi Road", "North Ryde", "NSW",
                "2113", 55.55, 0,
                "Testing", "", 0.0);
    }

    public void checkSession(int order, int street, int suburb, int state, int postal) {
        verify(session, times(order)).setAttribute("orderErr", "Missing or wrong order");
        verify(session, times(street)).setAttribute("streetErr", "Invalid street");
        verify(session, times(suburb)).setAttribute("suburbErr", "Invalid suburb");
        verify(session, times(state)).setAttribute("stateErr", "Invalid State, use state code with capital letters");
        verify(session, times(postal)).setAttribute("postalErr", "Invalid Postal Code, 4 digits");
    }

    public void setParameters(String orderType, String orderID, Order order, String street, String suburb, String state, String postal, String instructions){
        when(request.getParameter("order-type")).thenReturn(orderType);
        when(request.getParameter("orderID")).thenReturn(orderID);
        if (order != null){
            when(manager.getOrder(order.getOrderID())).thenReturn(order);
        }
        when(request.getParameter("street")).thenReturn(street);
        when(request.getParameter("suburb")).thenReturn(suburb);
        when(request.getParameter("state")).thenReturn(state);
        when(request.getParameter("postal")).thenReturn(postal);
        when(request.getParameter("instructions")).thenReturn(instructions);
    }

    // Test for Correct Information (valid orderID, state, postal code)
    @Test
    public void testCorrectInformation() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null, delivery);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", "North Ryde", "NSW", "2113", "Testing");
        when(request.getRequestDispatcher("deliveryStatus.jsp"))
            .thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        

        verify(manager, times(1)).createDelivery(ArgumentMatchers.refEq(new Delivery(order.getOrderID(), "9 Delhi Road", "North Ryde", "NSW", "2113", 55.55,
                            "Testing")));
        verify(request, times(1)).setAttribute("deliveryID", delivery.getDeliveryID());
        verify(request, times(1)).getRequestDispatcher("deliveryStatus.jsp");
        checkSession(0, 0, 0, 0, 0);
    }

    // Test for invalid orderID (null)
    @Test
    public void testNullOrderID() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", null, order, "9 Delhi Road", "North Ryde", "NSW", "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
;
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(1, 0, 0, 0, 0);
    }

    // Test for invalid orderID (6 letters)
    @Test
    public void testLetterOrderID() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", "abcdef", order, "9 Delhi Road", "North Ryde", "NSW", "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);

        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(1, 0, 0, 0, 0);
    }

    // Test for invalid orderID (order is not found in database)
    @Test
    public void testNotFoundOrder() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), null, "9 Delhi Road", "North Ryde", "NSW", "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(1, 0, 0, 0, 0);
    }

    // Test for invalid street (empty string)
    @Test
    public void testEmptyStreet() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "", "North Ryde", "NSW", "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        

        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 1, 0, 0, 0);
    }

    // Test for invalid street (null)
    @Test
    public void testNullStreet() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, null, "North Ryde", "NSW", "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 1, 0, 0, 0);
    }

    // Test for invalid suburb (empty string)
    @Test
    public void testEmptySuburb() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", "", "NSW", "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 0, 1, 0, 0);
    }

    // Test for invalid street (null)
    @Test
    public void testNullSuburb() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", null, "NSW", "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 0, 1, 0, 0);
    }

    // Test for invalid state (4 lowercase letters)
    @Test
    public void testLowerCaseState() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", "North Ryde", "ansl", "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 0, 0, 1, 0);
    }

    // Test for invalid state (6 uppercase letters)
    @Test
    public void testExceedLimitState() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", "North Ryde", "AAAAAA", "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 0, 0, 1, 0);
    }

    // Test for invalid state (empty string "")
    @Test
    public void testEmptyState() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", "North Ryde", "", "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 0, 0, 1, 0);
    }

    // Test for invalid state (null)
    @Test
    public void testNullState() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", "North Ryde", null, "2113", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 0, 0, 1, 0);
    }

    // Test for invalid postal (4 letters)
    @Test
    public void testLetterPostal() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", "North Ryde", "NSW", "abcd", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 0, 0, 0, 1);
    }

    // Test for invalid postal (6 numbers)
    @Test
    public void testExceedLimitPostal() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", "North Ryde", "NSW", "123456", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 0, 0, 0, 1);
    }

    // Test for invalid postal (empty string)
    @Test
    public void testEmptyPostal() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", "North Ryde", "NSW", "", "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 0, 0, 0, 1);
    }

    // Test for invalid postal (null)
    @Test
    public void testNullPostal() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("manager")).thenReturn(manager);

        setParameters("Delivery", Integer.toString(order.getOrderID()), order, "9 Delhi Road", "North Ryde", "NSW", null, "Testing");
        when(manager.getDeliveryByOrderID(order.getOrderID())).thenReturn(null);
        when(request.getRequestDispatcher("createUpdateDelivery.jsp")).thenReturn(mock(RequestDispatcher.class));

        servlet.doPost(request, response);
        
        verify(manager, times(0)).createDelivery(ArgumentMatchers.refEq(delivery));
        verify(request, times(1)).getRequestDispatcher("createUpdateDelivery.jsp");
        checkSession(0, 0, 0, 0, 1);
    }
}

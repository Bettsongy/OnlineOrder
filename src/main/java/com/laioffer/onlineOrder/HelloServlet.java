package com.laioffer.onlineOrder;
import com.laioffer.onlineOrder.entity.Customer;
import org.apache.commons.io.IOUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

// the value is the actual trigger
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello Servlet!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        // Hello
        Customer customer = new Customer();
        customer.setEmail("stefanlaioffer@gmail.com");
        customer.setFirstName("Roger");
        customer.setLastName("Federer");

        response.getWriter().print(new ObjectMapper().writeValueAsString(customer));

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Customer customer = new ObjectMapper().readValue(request.getReader(), Customer.class);

        // Read customer information from request body
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String email = jsonRequest.getString("email");
        String firstName = jsonRequest.getString("first_name");
        String lastName = jsonRequest.getString("last_name");
        int age = jsonRequest.getInt("age");
        // Print customer information to IDE console
        System.out.println("Email is: " + email);
        System.out.println("First name is: " + firstName);
        System.out.println("Last name is: " + lastName);
        System.out.println("Age is: " + age);
        // Return status = ok as response body to the client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);

    }

    public void destroy() {
    }
}
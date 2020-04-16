package ua.lviv.lgs.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ua.lviv.lgs.domain.Customer;
import ua.lviv.lgs.dto.CustomerLogin;
import ua.lviv.lgs.service.CustomerService;
import ua.lviv.lgs.service.impl.CustomerServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = CustomerServiceImpl.getCustomerService();  
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Customer customer = customerService.getCustomerByEmail(email);

		if (customer != null && customer.getPassword().equals(password)) {
			
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", customer.getId());
			
			CustomerLogin customerLogin = new CustomerLogin();
			customerLogin.destinationUrl = "cabinet.jsp";
			customerLogin.customerEmail = customer.getEmail();
			
			String json = new Gson().toJson(customerLogin);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		}
		
	}
}

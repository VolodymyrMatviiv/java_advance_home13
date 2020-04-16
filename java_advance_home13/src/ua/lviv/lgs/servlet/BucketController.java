package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import ua.lviv.lgs.domain.Book;
import ua.lviv.lgs.domain.BucketOrder;
import ua.lviv.lgs.domain.Customer;
import ua.lviv.lgs.service.BookService;
import ua.lviv.lgs.service.BucketOrderService;
import ua.lviv.lgs.service.CustomerService;
import ua.lviv.lgs.service.impl.BookServiceImpl;
import ua.lviv.lgs.service.impl.BucketOrderServiceImpl;
import ua.lviv.lgs.service.impl.CustomerServiceImpl;

@WebServlet("/bucket")
public class BucketController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BucketOrderService bucketService = BucketOrderServiceImpl.getBucketOrderService();
	private BookService bookService = BookServiceImpl.getBookService();
	private CustomerService customerService = CustomerServiceImpl.getCustomerService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		Book product = bookService.read(bookId);

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("customerId");
		Customer customer = customerService.read(userId);

		BucketOrder bucket = new BucketOrder();
		bucket.setId(UUID.randomUUID().toString());
		bucket.setProduct(product);
		bucket.setCustomer(customer);
		bucket.setPurchaseDate(new Date());

		bucketService.create(bucket);

		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bucketId = request.getParameter("bucketId");
		bucketService.delete(Integer.parseInt(bucketId));

		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

}

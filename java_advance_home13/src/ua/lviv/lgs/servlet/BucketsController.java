package ua.lviv.lgs.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ua.lviv.lgs.domain.Book;
import ua.lviv.lgs.domain.BucketOrder;
import ua.lviv.lgs.dto.BucketDto;
import ua.lviv.lgs.service.BookService;
import ua.lviv.lgs.service.BucketOrderService;
import ua.lviv.lgs.service.impl.BookServiceImpl;
import ua.lviv.lgs.service.impl.BucketOrderServiceImpl;


@WebServlet("/buckets")
public class BucketsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private	BucketOrderService bucketOrderService = BucketOrderServiceImpl.getBucketOrderService();
	private BookService productService = BookServiceImpl.getBookService();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BucketOrder> buckets = bucketOrderService.raedAll();
		Map<Integer, Book> idToProduct = productService.readAllMap();
		List<BucketDto> listOfBucketDtos = map(buckets, idToProduct);
		
		String json = new Gson().toJson(listOfBucketDtos);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	private List<BucketDto> map(List<BucketOrder> buckets, Map<Integer, Book> idToProduct) {
		
		return buckets.stream().map(bucket->{
			BucketDto bucketDto = new BucketDto();
			bucketDto.bucketId = bucket.getId();
			bucketDto.purchaseDate = bucket.getPurchaseDate();
		   
			Book product = idToProduct.get(bucket.getProduct().getId());
		    bucketDto.bookName = product.getBookName();
		    bucketDto.bookDescription = product.getBookDescription();
		    bucketDto.price = product.getPrice();
			
			return bucketDto;
		}).collect(Collectors.toList());
	}

	
}

package ru.sokolov.pricelist.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.sokolov.pricelist.dao.ProductDao;
import ru.sokolov.pricelist.dao.ReqParameters;
import ru.sokolov.pricelist.models.Product;
import ru.sokolov.pricelist.services.ProductService;

@WebServlet("/pricelist")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ProductService productService = new ProductService(new ProductDao("ImproveGroup"));
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Product> products = productService.findAll();
		request.setAttribute("products", products);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		ReqParameters parameters = new ReqParameters(request.getParameterMap());
		
		if (parameters.isEmpty()) {
			doGet(request, response);
			return;
		}
		
		request.setAttribute("parameters", parameters);
		
		List<Product> products = productService.findAppropriate(parameters);
		request.setAttribute("products", products);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
}

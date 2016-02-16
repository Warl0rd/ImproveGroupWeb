package ru.sokolov.pricelist.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.sokolov.pricelist.models.Product;

@WebServlet("/pricelist")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emfactory;
	EntityManager entitymanager;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		emfactory = Persistence.createEntityManagerFactory("ImproveGroupName");
		entitymanager = emfactory.createEntityManager();
		
		super.init(config);
	}
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Query query = entitymanager.createQuery("SELECT p FROM Product p");
		
		List<Product> products = (List<Product>) query.getResultList();
		
		request.setAttribute("products", products);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String priductIdString = request.getParameter("cathegory");
		String priductNameString = request.getParameter("name");
		String priductPriceMinString = request.getParameter("priceMin");
		String priductPriceMaxString = request.getParameter("priceMax");
		if (priductIdString.equals("") && priductNameString.equals("") && 
				priductPriceMinString.equals("") && priductPriceMaxString.equals("")) {
			doGet(request, response);
			return;
		}
		
		int priductId = Integer.parseInt(priductIdString);
		
		Product product = entitymanager.find(Product.class, priductId);
		
		List<Product> products = new ArrayList<>();
		products.add(product);
		
		request.setAttribute("products", products);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
	@Override
	public void destroy() {
		
		entitymanager.close();
		emfactory.close();
		
		super.destroy();
	}

	

}

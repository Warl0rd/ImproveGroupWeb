package ru.sokolov.pricelist.controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.sokolov.pricelist.models.Product;

@WebServlet("/list")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ImproveGroup");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		Product product = entitymanager.find(Product.class, 1);
		
		System.out.println(product);
		
		request.setAttribute("product", product);
		
		entitymanager.close();
		emfactory.close();
		
		request.getRequestDispatcher("home.jsp").forward(request, response);
		
	}

}

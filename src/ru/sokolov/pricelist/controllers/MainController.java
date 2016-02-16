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

		String sql = "SELECT p FROM Product p";
		
		Query query = entitymanager.createQuery(sql);
		
		List<Product> products = (List<Product>) query.getResultList();
		
		request.setAttribute("products", products);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String priductIdString = request.getParameter("cathegory");
		String priductNameString1 = request.getParameter("name");
		String priductNameString = new String(priductNameString1.getBytes("ISO-8859-1"), "windows-1251");
		System.out.println(priductNameString1);
		System.out.println(priductNameString);
		String priductPriceMinString = request.getParameter("priceMin");
		String priductPriceMaxString = request.getParameter("priceMax");
		
		StringBuilder sql = new StringBuilder("SELECT p FROM Product p");
		
		if (priductIdString.equals("") && priductNameString.equals("") && 
				priductPriceMinString.equals("") && priductPriceMaxString.equals("")) {
			doGet(request, response);
			return;
		}
		
		sql.append(" WHERE ");
		
//		int priductId = Integer.parseInt(priductIdString);
		
		if (!priductNameString.equals("")) {
			sql.append("p.name LIKE '"+priductNameString+"%'");
			if (!priductPriceMinString.equals("")) {
				int priductPriceMin = Integer.parseInt(priductPriceMinString);
				sql.append(" AND ");
				sql.append("p.price >= '"+priductPriceMin+"'");
				
				if (!priductPriceMaxString.equals("")) {
					int priductPriceMax = Integer.parseInt(priductPriceMaxString);
					sql.append(" AND ");
					sql.append("p.price <= '"+priductPriceMax+"'");
				}
			}
			else if (!priductPriceMaxString.equals("")) {
				int priductPriceMax = Integer.parseInt(priductPriceMaxString);
				sql.append(" AND ");
				sql.append("p.price <= '"+priductPriceMax+"'");
			}
		}
		else {
			if (!priductPriceMinString.equals("")) {
				int priductPriceMin = Integer.parseInt(priductPriceMinString);
				sql.append("p.price >= '"+priductPriceMin+"'");
				
				if (!priductPriceMaxString.equals("")) {
					int priductPriceMax = Integer.parseInt(priductPriceMaxString);
					sql.append(" AND ");
					sql.append("p.price <= '"+priductPriceMax+"'");
				}
			}
			else if (!priductPriceMaxString.equals("")) {
				int priductPriceMax = Integer.parseInt(priductPriceMaxString);
				sql.append("p.price <= '"+priductPriceMax+"'");
			}
		}
		
		
		Query query = entitymanager.createQuery(sql.toString());
		
		List<Product> products = (List<Product>) query.getResultList();
		
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

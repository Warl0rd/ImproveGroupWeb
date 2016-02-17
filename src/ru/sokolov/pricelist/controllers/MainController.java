package ru.sokolov.pricelist.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
		super.init(config);
		
		emfactory = Persistence.createEntityManagerFactory("ImproveGroup");
		entitymanager = emfactory.createEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sql = "SELECT p FROM Product p";
		
		Query query = entitymanager.createQuery(sql);
		
		List<Product> products = (List<Product>) query.getResultList();

		request.setAttribute("products", products);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Map<String, String[]> parameters = request.getParameterMap();
		
		String priductCatString = parameters.get("cathegory")[0];
		String priductNameString = parameters.get("name")[0];
		String priductPriceMinString = parameters.get("priceMin")[0];
		String priductPriceMaxString = parameters.get("priceMax")[0];
		
		request.setAttribute("cathegory", priductCatString);
		request.setAttribute("name", priductNameString);
		request.setAttribute("priceMin", priductPriceMinString);
		request.setAttribute("priceMax", priductPriceMaxString);
		
		StringBuilder sql = new StringBuilder("SELECT p FROM Product p");
		
		if (priductCatString.equals("") && priductNameString.equals("") && 
				priductPriceMinString.equals("") && priductPriceMaxString.equals("")) {
			doGet(request, response);
			return;
		}
		
		sql.append(" WHERE ");
		
		if (!priductCatString.equals("")) {
			sql.append("p.cat.name LIKE '"+priductCatString+"%'");
			if (!priductNameString.equals("")) {
				sql.append(" AND ");
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
		}
		else {
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

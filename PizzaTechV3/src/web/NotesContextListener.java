package web;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class NotesContextListener implements ServletContextListener {

	// Prepare the EntityManagerFactory & Enhance:
	public void contextInitialized(ServletContextEvent e) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("hello-world");
		e.getServletContext().setAttribute("emf", emf);
	}

	// Release the EntityManagerFactory:
	public void contextDestroyed(ServletContextEvent e) {
		EntityManagerFactory emf = (EntityManagerFactory) e.getServletContext()
				.getAttribute("emf");
		emf.close();
	}
}
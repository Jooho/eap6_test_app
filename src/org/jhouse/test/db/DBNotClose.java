package org.jhouse.test.db;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.jboss.logmanager.Logger;

/**
 * Servlet implementation class DBNotClose
 */
public class DBNotClose extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger("DBNotClose");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DBNotClose() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 logger.info("test:EC Log");
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:jboss/datasources/ExampleDS");
			System.out
					.println("<p>Successfully looked up DataSource named java:jboss/ExampleDS </p>");
			for (int i = 0; i < 50; i++) {
				NewGetDS t = new NewGetDS(ds, i);
				new Thread(t).start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class NewGetDS implements Runnable {
		private javax.sql.DataSource ds;
		private int count;

		NewGetDS(javax.sql.DataSource ds, int count) {
			this.ds = ds;
			this.count = count;
		}

		public void run() {

			try {
				Connection conn = null;
				conn = ds.getConnection();
				System.out.println("<p>Successfully getConnection " + count
						+ " </p>");
				Thread.sleep(10000);
				//conn.close();
				System.out.println("<p>Successfully Connection Closed " + count
						+ " </p>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

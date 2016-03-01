//package org.jhouse.test.log4j;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.logging.Level;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.hibernate.validator.util.LoggerFactory;
//import org.jboss.logmanager.Logger;
//import org.jgroups.logging.LogFactory;
//
///**
// * Servlet implementation class TestServletContextLogServlet
// */
//public class TestServletContextLogServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
////	Logger logger = Logger.getLogger(TestServletContextLogServlet.class);
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public TestServletContextLogServlet() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//
//		// Most logging APIs
//		Logger.getLogger(TestServletContextLogServlet.class).error("Message 1. Log4J logger");
//		LogFactory.getLog(TestServletContextLogServlet.class).error("Message 2. Commons Logger");
//		LoggerFactory.getLogger(TestServletContextLogServlet.class).info(
//				"Message 3. slf4j logger");
//		java.util.logging.Logger.getLogger(TestServletContextLogServlet.class.getCanonicalName())
//				.log(Level.SEVERE, "Message 4. java util logging");
//
//		// *******************************
//		// This line does not get logged
//		// This also relates to JSP compilation error not getting logged.
//		// If no log4j.xml file is present, this line will log.
//		// *******************************
//		getServletContext().log(
//				"Message 5. Message from getServletContext().log()");
////
//		Logger.getLogger(TestServletContextLogServlet.class).error(
//				"The end - check for all 5 log messages.");
//
////		response.getOutputStream().print(
////				"TestServletContextLogServlet Servlet - Please check log for all 5 log messages.");
//
//		PrintWriter pw = response.getWriter();
//		pw.println("this is servletContext.log() test of log4j");
//		pw.println("TestServletContextLogServlet Servlet - Please check log for servlet context log.");
//		pw.flush();
//	}
//
//}

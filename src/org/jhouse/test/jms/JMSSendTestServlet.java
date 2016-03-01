package org.jhouse.test.jms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JMSTestServlet
 */

public class JMSSendTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JMSSendTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Display the JMSTest HMTL form
		request.getRequestDispatcher("/testcase/resource/jms/jms_sender_form.html").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<h1>Sender Results of JMSTest</h1>");
		String factoryName = request.getParameter("factoryName");
		String  destName = request.getParameter("destName");
		Context context = null;		
		ConnectionFactory connectionFactory = null;
		Destination dest = null;
		out.println("<p>Looking up Connection Factory: " + factoryName + "</p>");
		try {
			context = new InitialContext();
			connectionFactory = (ConnectionFactory) context.lookup(factoryName);
		} catch (Exception e) {
			out.println("<p>Connection Factory lookup failed: " + e.getMessage() + "</p>");
			e.printStackTrace();
			return;
		}
		out.println("<p>Successfully found Connection Factory: " + factoryName + "</p><br/>");
		out.println("<p>Looking up Destination: " + destName + "</p>");
		try {
			dest = (Destination) context.lookup(destName);
		} catch(Exception e) {
			out.println("<p>Destination lookup failed: " + e.getMessage() + "</p>");
			e.printStackTrace();
			return;
		}
		out.println("<p>Successfully found Destination: " + destName + "</p>");
		//If we get this far, both the factory and destination have been found successfully, 
		//so let's send a few test messages
		int numOfTests = Integer.parseInt(request.getParameter("numOfTests"));
		if(numOfTests > 0) {
			out.println("<p>Attempting to send " + numOfTests + " test messages to destination...</p>");
			Connection connection = null;
			Session session;
			try {
				connection = connectionFactory.createConnection();
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				
				MessageProducer producer = session.createProducer(dest);
				TextMessage message = session.createTextMessage();
				for(int i = 1; i <= numOfTests; i++) {
					message.setText("This is test message " + i);
					out.println("<p>Sending test message: " + i + "</p>");
					producer.send(message);
				}
			} catch(Exception e) {
				out.println("<p>An exception occurred while sending test messages: " + e.getMessage() + "</p>");
			}finally {
	            if (context != null) {
	                try {
	                	context.close();
	                } catch (Exception e) {
	                    try {
							throw e;
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
	            }

	            // ALWAYS close your connection in a finally block to avoid leaks.
	            // Closing connection also takes care of closing its related objects
	            // e.g. sessions.
	            closeConnection(connection);
	        }
		}
	}

	 private void closeConnection(Connection con) {
	        try {
	            if (con != null) {
	                con.close();
	                System.out.println(" connection :" +con+ " closed.");
	            }
	        } catch (JMSException jmse) {
	            System.out.println("Could not close connection " + con + " exception was " + jmse);
	        }
	    }
}

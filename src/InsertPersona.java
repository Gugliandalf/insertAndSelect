import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InsertPersona
 */
@WebServlet("/InsertPersona")
public class InsertPersona extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection japan;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPersona() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * In realtà non usato se non ad inizio sviluppo
	 * può essere utile per debug
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Persona persona = new Persona();
		persona.setCognome(request.getParameter("cognome"));
		persona.setNome(request.getParameter("nome"));
		persona.setSalario(request.getParameter("salario"));
		response.getWriter().append("doGet: ");
		response.getWriter().append(persona.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * -	istanzia un'oggetto Persona
	 * -	legge le proprietà dal POST
	 * -	doInsert(Persona persona) per aggiungere il record al DB
	 * -	persone = getResult() per avere il recordset su un ArrayList
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Persona> persone;
		Persona persona = new Persona();
		HttpSession session;
		
		session = request.getSession();
		System.out.println( session.toString());
		
		persona.setCognome(request.getParameter("cognome"));
		persona.setNome(request.getParameter("nome"));
		persona.setSalario(request.getParameter("salario"));
		response.getWriter().append("doPost: ");
		response.getWriter().append(persona.toString());
		
		doInsert(persona);
		persone = getResulsetPersone();
		System.out.println(persone.toString());

		/**
		 * inserire chiamata a .jsp
		 * 
		 */
		session.setAttribute("persone", persone);
		
	}
	
	/**
	 * 
	 * @return persone: ArrayList(Persona) con il contenuto della tabella nel DB
	 * 
	 */
	private ArrayList<Persona> getResulsetPersone() {
		ArrayList<Persona> persone = new ArrayList<Persona>();
		String mySQL;
		Statement myQuery;
		ResultSet myResultset;

		try {
			//	2^ puntata: query di selezione
			mySQL = "SELECT cognome, nome, salario FROM persona ORDER BY cognome, nome";
			japan = DriverManager.getConnection("jdbc:mysql://localhost:3306/japan", "gandalf", "mellon");
			myQuery = japan.createStatement();
			myResultset = myQuery.executeQuery(mySQL);
			//	Fetch records in ArrayList
			while (myResultset.next()) {
				persone.add(new Persona(myResultset.getString(1), myResultset.getString(2), myResultset.getString(3)));
			}
			myResultset.close();
			myQuery.close();
			japan.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return persone;
	}
	/**
	 * 
	 * @param persona
	 */
	private void doInsert(Persona persona) {
		String mySQL;
		Statement myQuery;

		try {
			//	Creo la connessione
			japan = DriverManager.getConnection("jdbc:mysql://localhost:3306/japan", "gandalf", "mellon");
			//	Stringa SQL di INSERT
			mySQL = new String("INSERT INTO persona (cognome, nome, salario) VALUES ('" + persona.getCognome() + "', '" + persona.getNome() + "', " + persona.getSalario() + ");");
			//	eseguo query
			myQuery = japan.createStatement();
			myQuery.executeUpdate(mySQL);
			//	Chiudiamo tuttecose
			myQuery.close();
			japan.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

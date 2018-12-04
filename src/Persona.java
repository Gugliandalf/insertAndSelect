import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Persona
 */
@WebServlet("/Persona")
public class Persona extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String cognome;
	private String nome;
	private String salario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Persona() {
        super();
    }
    
    public Persona(String cognome, String nome, String salario) {
    	super();
    	this.setCognome(cognome);
    	this.setNome(nome);
    	this.setSalario(salario);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public String toString() {
		return "Persona [cognome=" + cognome + ", nome=" + nome + ", salario=" + salario + "]";
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String string) {
		this.salario = string;
	}
}

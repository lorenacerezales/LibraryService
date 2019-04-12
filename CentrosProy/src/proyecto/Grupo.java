package proyecto;

import java.util.TreeMap;
/**Esta clase contiene los grupos teóricos y prácticos de la clase asignaturas. Contiene información como el tipo de grupo, el id del grupo que nos permite identificarlos,
 * el aula donde se imparte, la hora en la que se imparte el grupo, el profesor que imparte dicho grupo...
 * 
 * @author Monica Loureiro Zunzunegui
 * @author Lorena Gil Cerezales
 *
 */
public class Grupo {
	/**Nos indica si es un grupo teórico(A) o práctico(B).*/
	private char tipoGrupo;
	/**Array en el que guardamoas todos los identificadores numericos de todos los grupos práctcicos o teóricos que tiene una asignatura. El identificador númerico es único para cada grupo.*/
	private String[] IdGrupo=new String[12];
	/**Hora a la que empieza a impartirse la clase de un determinado grupo*/
	private int horaini;
	/**Nos indica que dia se imparte el grupo */
	private char dia;
	/** Nos indica en que aula de la escuela se imparte el grupo.*/
	private String aula;
	/**El dni del profesor que imparte la docencia del grupo.*/
	private String DNIDocente="" ;
	/** La asignatura a la que pertenece los grupos teóricos y prácticos*/
	private String Asignatura;
	/** Contador en que le que guardamos en el número de grupos que un determinado profesor imparte */
	private int numGrupos=0;
	/**Contador que nos permite saber si el aula ya está ocupada por un grupo a una determinada hora*/
	private int ocupacion;
	/**Lista de los grupos que se imparten a lo largo de las horas del día, de 9 a 18 horas, en un aula. */
	private TreeMap<Integer,Grupo> horasgrupos=new TreeMap<Integer,Grupo>();
	
	/**Constructor que genera un nuevo grupo con todos los datos que aparecen a continuación.
	 * 
	 * @param tipo Nos indica si es un grupo teórico(A) o práctico(B).
	 * @param Id Identificador númerico único para cada grupo.
	 * @param horaini Hora a la que empieza a impartirse la clase de un determinado grupo.
	 * @param dia Nos indica que dia se imparte el grupo.
	 * @param aula Nos indica en que aula de la escuela se imparte el grupo.
	 */
	public Grupo(char tipo,String Id,int horaini,char dia,String aula) {
		
		this.setTipoGrupo(tipo);
		this.setIdGrupo(Id);
		this.setHoraini(horaini);
		this.setDia(dia);
		this.setAula(aula);

	}
	
	//** Constructor vacío*/
	public Grupo() {
		
	}
	/**Método empleado para obtener un identificador numérico de un grupo de un asignatura.
	 * 
	 * @return Identificador numérico del grupo.
	 */

    public String[] getIdGrupo() {
	return IdGrupo;
    }
    
    /**Método empleado para registrar un identificador numérico de un grupo de una asignatura. Además, aumentamos el contador en el que llevamos la cuenta
     * de cuantos grupos imparte un determinado profesor.
	 * 
	 * @param idGrupo Identificador numérico del grupo.
	 */
	
	public void setIdGrupo(String idGrupo) {
	
		this.IdGrupo[getNumGrupos()] = idGrupo;
		numGrupos++;//Aumentamos en 1 el numero de grupos
		
	}
	
	
	/**Método para obtener si es un grupo es teórico(A) o práctico(B).
	 * 
	 * @return Carácter A o B.
	 */

	public char getTipoGrupo() {
		return tipoGrupo;
	}
	
	
	/**Método para registrar si es un grupo es teórico(A) o práctico(B).
	 * 
	 * @param tipoGrupo Carácter A o B.
	 */

	public void setTipoGrupo(char tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}
	
	/**Método que nos devuelve la hora del día en la que se imparte la docencia del grupo.
	 * 
	 * @return Número entero entre 9 y 18.
	 */

	public int getHoraini() {
		return horaini;
	}
	
	/**Método que para introducir la hora del día en la que se imparte la docencia del grupo.
	 * 
	 * @param horaini Número entero entre 9 y 18.
	 */

	public void setHoraini(int horaini) {
		this.horaini = horaini;
	}
	
	/**Método para obtener el día de la semana en el que se imparte el grupo.
	 * 
	 * @return Día de la semana.
	 */

	public char getDia() {
		return dia;
	}
	
	/**Método para introducir el día de la semana en el que se imparte el grupo.
	 * 
	 * @param dia Día de la semana.
	 */

	public void setDia(char dia) {
		this.dia = dia;
	}
	
	/**Método para obtener el aula de la escuela en el que se imparte la docencia del grupo.
	 * 
	 * @return Aula.
	 */

	public String getAula() {
		return aula;
	}
	/**Método para introducir el aula de la escuela en el que se imparte la docencia del grupo.
	 * 
	 * @param aula Aula.
	 */


	public void setAula(String aula) {
		this.aula = aula;
	}
	
	/**Método para obtener el dni del profesor que imparte la docencia del grupo.
	 * 
	 * @return String con el dni del profesor.
	 */

	public String getDNIDocente() {
		return DNIDocente;
	}
	
	/**Método para registrar el dni del profesor que imparte la docencia del grupo.
	 * 
	 * @param dNIDocente String con el dni del profesor.
	 */

	public void setDNIDocente(String dNIDocente) {
		DNIDocente = dNIDocente;
	}
	
	/**Método que nos devuelve la asignatura a la que pertenece los grupos teóricos y prácticos.
	 * 
	 * @return String con le nombre de la asignatura.
	 */

	public String getAsignatura() {
		return Asignatura;
	}

	/**Método que nos permite introducir la asignatura a la que pertenece los grupos teóricos y prácticos.
	 * 
	 * @param asignatura String con le nombre de la asignatura.
	 */

	public void setAsignatura(String asignatura) {
		Asignatura = asignatura;
	}
	
	/**Método empleado para obtener el número total de grupos que imparte un profesor.
	 * 	 
	 * @return Entero con el número de grupos que imparte el profesor.
	 */


	public int getNumGrupos() {
		return numGrupos;
	}

	/**Método empleado para resgitrar el número total de grupos que imparte un profesor.
	 * 	 
	 * @param numGrupos Entero con el número de grupos que imparte el profesor.
	 */

	public void setNumGrupos(int numGrupos) {
		this.numGrupos=numGrupos;
	}
	
	/**Método para obtener un número entero que nos permitirá saber si un aula está ocupada a una determinada hora por un grupo.
	 * Si devuelve un cero es que está vacía, sino en ese aula ya se imparte la docencia de un grupo.
	 * 
	 * @return Entero que contabiliza cuantos grupos imparten su docencia en el aula a una determinada hora.
	 */
	
	public int getOcupacion() {
		return ocupacion;
	}
	
	/**Método para registrar un número entero que nos permitirá saber si un aula está ocupada a una determinada hora por un grupo.
	 * Si devuelve un cero es que está vacía, sino en ese aula ya se imparte la docencia de un grupo.
	 * 
	 */
	
	
	public void setOcupacion() {
		this.ocupacion++;
	}
	
	/**Método para obtener un listado de los grupos que se imparten en un aula ,ordenados por orden cronológico (de 9 a 18 horas), en un día determinado de la semana.
	 * 
	 * @return Listado de los grupos.
	 */
	public TreeMap<Integer,Grupo> getHorasgrupos() {
		return horasgrupos;
	}
	
	/**Método para registrar un listado de los grupos que se imparten en un aula ,ordenados por orden cronológico (de 9 a 18 horas), en un día determinado de la semana.
	 * @param tipo Nos indica si es un grupo teórico(A) o práctico(B).
	 * @param Id Identificador númerico único para cada grupo.
	 * @param horaini Hora a la que empieza a impartirse la clase de un determinado grupo.
	 * @param dia Nos indica que dia se imparte el grupo.
	 * @param aula Nos indica en que aula de la escuela se imparte el grupo.
	 * @param asignatura Asignatura de la que queremos obtener los grupos.
	 */
	public void setHorasGrupos(char tipo,String Id,int horaini,char dia,String aula, String asignatura) {
		horasgrupos.put(horaini,new Grupo(tipo,Id,horaini,dia,aula));
		Grupo aux=horasgrupos.get(horaini);
		aux.setAsignatura(asignatura);
	}


	

}

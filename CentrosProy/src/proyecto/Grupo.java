package proyecto;

import java.util.TreeMap;
/**Esta clase contiene los grupos te�ricos y pr�cticos de la clase asignaturas. Contiene informaci�n como el tipo de grupo, el id del grupo que nos permite identificarlos,
 * el aula donde se imparte, la hora en la que se imparte el grupo, el profesor que imparte dicho grupo...
 * 
 * @author Monica Loureiro Zunzunegui
 * @author Lorena Gil Cerezales
 *
 */
public class Grupo {
	/**Nos indica si es un grupo te�rico(A) o pr�ctico(B).*/
	private char tipoGrupo;
	/**Array en el que guardamoas todos los identificadores numericos de todos los grupos pr�ctcicos o te�ricos que tiene una asignatura. El identificador n�merico es �nico para cada grupo.*/
	private String[] IdGrupo=new String[12];
	/**Hora a la que empieza a impartirse la clase de un determinado grupo*/
	private int horaini;
	/**Nos indica que dia se imparte el grupo */
	private char dia;
	/** Nos indica en que aula de la escuela se imparte el grupo.*/
	private String aula;
	/**El dni del profesor que imparte la docencia del grupo.*/
	private String DNIDocente="" ;
	/** La asignatura a la que pertenece los grupos te�ricos y pr�cticos*/
	private String Asignatura;
	/** Contador en que le que guardamos en el n�mero de grupos que un determinado profesor imparte */
	private int numGrupos=0;
	/**Contador que nos permite saber si el aula ya est� ocupada por un grupo a una determinada hora*/
	private int ocupacion;
	/**Lista de los grupos que se imparten a lo largo de las horas del d�a, de 9 a 18 horas, en un aula. */
	private TreeMap<Integer,Grupo> horasgrupos=new TreeMap<Integer,Grupo>();
	
	/**Constructor que genera un nuevo grupo con todos los datos que aparecen a continuaci�n.
	 * 
	 * @param tipo Nos indica si es un grupo te�rico(A) o pr�ctico(B).
	 * @param Id Identificador n�merico �nico para cada grupo.
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
	
	//** Constructor vac�o*/
	public Grupo() {
		
	}
	/**M�todo empleado para obtener un identificador num�rico de un grupo de un asignatura.
	 * 
	 * @return Identificador num�rico del grupo.
	 */

    public String[] getIdGrupo() {
	return IdGrupo;
    }
    
    /**M�todo empleado para registrar un identificador num�rico de un grupo de una asignatura. Adem�s, aumentamos el contador en el que llevamos la cuenta
     * de cuantos grupos imparte un determinado profesor.
	 * 
	 * @param idGrupo Identificador num�rico del grupo.
	 */
	
	public void setIdGrupo(String idGrupo) {
	
		this.IdGrupo[getNumGrupos()] = idGrupo;
		numGrupos++;//Aumentamos en 1 el numero de grupos
		
	}
	
	
	/**M�todo para obtener si es un grupo es te�rico(A) o pr�ctico(B).
	 * 
	 * @return Car�cter A o B.
	 */

	public char getTipoGrupo() {
		return tipoGrupo;
	}
	
	
	/**M�todo para registrar si es un grupo es te�rico(A) o pr�ctico(B).
	 * 
	 * @param tipoGrupo Car�cter A o B.
	 */

	public void setTipoGrupo(char tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}
	
	/**M�todo que nos devuelve la hora del d�a en la que se imparte la docencia del grupo.
	 * 
	 * @return N�mero entero entre 9 y 18.
	 */

	public int getHoraini() {
		return horaini;
	}
	
	/**M�todo que para introducir la hora del d�a en la que se imparte la docencia del grupo.
	 * 
	 * @param horaini N�mero entero entre 9 y 18.
	 */

	public void setHoraini(int horaini) {
		this.horaini = horaini;
	}
	
	/**M�todo para obtener el d�a de la semana en el que se imparte el grupo.
	 * 
	 * @return D�a de la semana.
	 */

	public char getDia() {
		return dia;
	}
	
	/**M�todo para introducir el d�a de la semana en el que se imparte el grupo.
	 * 
	 * @param dia D�a de la semana.
	 */

	public void setDia(char dia) {
		this.dia = dia;
	}
	
	/**M�todo para obtener el aula de la escuela en el que se imparte la docencia del grupo.
	 * 
	 * @return Aula.
	 */

	public String getAula() {
		return aula;
	}
	/**M�todo para introducir el aula de la escuela en el que se imparte la docencia del grupo.
	 * 
	 * @param aula Aula.
	 */


	public void setAula(String aula) {
		this.aula = aula;
	}
	
	/**M�todo para obtener el dni del profesor que imparte la docencia del grupo.
	 * 
	 * @return String con el dni del profesor.
	 */

	public String getDNIDocente() {
		return DNIDocente;
	}
	
	/**M�todo para registrar el dni del profesor que imparte la docencia del grupo.
	 * 
	 * @param dNIDocente String con el dni del profesor.
	 */

	public void setDNIDocente(String dNIDocente) {
		DNIDocente = dNIDocente;
	}
	
	/**M�todo que nos devuelve la asignatura a la que pertenece los grupos te�ricos y pr�cticos.
	 * 
	 * @return String con le nombre de la asignatura.
	 */

	public String getAsignatura() {
		return Asignatura;
	}

	/**M�todo que nos permite introducir la asignatura a la que pertenece los grupos te�ricos y pr�cticos.
	 * 
	 * @param asignatura String con le nombre de la asignatura.
	 */

	public void setAsignatura(String asignatura) {
		Asignatura = asignatura;
	}
	
	/**M�todo empleado para obtener el n�mero total de grupos que imparte un profesor.
	 * 	 
	 * @return Entero con el n�mero de grupos que imparte el profesor.
	 */


	public int getNumGrupos() {
		return numGrupos;
	}

	/**M�todo empleado para resgitrar el n�mero total de grupos que imparte un profesor.
	 * 	 
	 * @param numGrupos Entero con el n�mero de grupos que imparte el profesor.
	 */

	public void setNumGrupos(int numGrupos) {
		this.numGrupos=numGrupos;
	}
	
	/**M�todo para obtener un n�mero entero que nos permitir� saber si un aula est� ocupada a una determinada hora por un grupo.
	 * Si devuelve un cero es que est� vac�a, sino en ese aula ya se imparte la docencia de un grupo.
	 * 
	 * @return Entero que contabiliza cuantos grupos imparten su docencia en el aula a una determinada hora.
	 */
	
	public int getOcupacion() {
		return ocupacion;
	}
	
	/**M�todo para registrar un n�mero entero que nos permitir� saber si un aula est� ocupada a una determinada hora por un grupo.
	 * Si devuelve un cero es que est� vac�a, sino en ese aula ya se imparte la docencia de un grupo.
	 * 
	 */
	
	
	public void setOcupacion() {
		this.ocupacion++;
	}
	
	/**M�todo para obtener un listado de los grupos que se imparten en un aula ,ordenados por orden cronol�gico (de 9 a 18 horas), en un d�a determinado de la semana.
	 * 
	 * @return Listado de los grupos.
	 */
	public TreeMap<Integer,Grupo> getHorasgrupos() {
		return horasgrupos;
	}
	
	/**M�todo para registrar un listado de los grupos que se imparten en un aula ,ordenados por orden cronol�gico (de 9 a 18 horas), en un d�a determinado de la semana.
	 * @param tipo Nos indica si es un grupo te�rico(A) o pr�ctico(B).
	 * @param Id Identificador n�merico �nico para cada grupo.
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

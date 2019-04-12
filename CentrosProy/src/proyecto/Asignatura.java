package proyecto;

import java.util.TreeMap;
/**Esta clase contiene las distintas asignaturas que ofrece la escuela. Son impartidas por el profesorado y a ellas asiste el alumnado. 
 * Las asignaturas se caracterizan por tener un nombre y unas siglas �nicas, que nos permite diferenciar cada asignatura.
 * Tambi�n contienen informaci�n sobre ella, como es el n�nmero del curso y el cuatrimestre en el que los alumnos estudiar�n dicha asignatura, el profesor coordinador, los pre-requisitos e informaci�n sobre sus grupos de pr�cticas y teor�a.
 * Con esta clase trabajamos en casi todos los m�todos presentes en este proyecto, como son 'asignar grupo',' matricular alumno', 'crear grupo de la asigantura', 'evaluar asignatura' y 'obtener expdeiente alumno', entre otros.   
 * 
 * @author Monica Loureiro Zunzunegui
 * @author Lorena Gil Cerezales
 *
 */

public class Asignatura implements Comparable<Object> {
	/**Siglas con las que identificamos la asignatura.*/
	private String Sigla;
	/**Nombre de la asignatura.*/
	private String Nombre;
	/**Curso en el que se imparte esa asignatura.*/
	private int Curso;
	/**Cuatrimestre en el que se imparte la asignatura.*/
	private int Cuatrimestre;
	/** Dni del profesor coordinador de la asignatura.*/
	private String Coordinador;
	/* Pre-requisitos, es decir, las asignaturas que tienen que haber superado los alumnos que deseen matricularse en la asignatura.*/
	private String [] Prerrequisitos;
	/** Indica la duraci�n de los grupos de teor�a de esa asignatura, puede ser 1 o 2 horas.*/
	private int DuracionGrupoA;
	/** Indica la duraci�n de los grupos de pr�ctica de esa asignatura, puede ser 1 o 2 horas.*/
	private int DuracionGrupoB;
	/** Lista de los grupos te�ricos de la asignatura.*/
	private TreeMap <Integer, Grupo> listaGruposA = new TreeMap<Integer, Grupo>();
	/** Lista de los grupos pr�cticos de la asignatura.*/
	private TreeMap <Integer, Grupo> listaGruposB = new TreeMap<Integer, Grupo>();
	/**Guardamos la nota del grupo A de un determinado alumno en una asignatura de la que queremos obtener el expediente. Es usada por el m�todo 'obtener expediente.*/
	private double nota;
	/**Guaradamos el curso acad�mico en el que un determinado alumno super� la asignatura.*/
	private String cursoacadem;
	
	/**Constructor que genera una nueva asignatura.
	 * 
	 * @param Siglas Siglas de la asignatura.
	 * @param nombre Nombre de la asignatura.
	 * @param curso Curso en el que se imparte esa asignatura.
	 * @param cuatrimestre Cuatrimestre en el que se imparte la asignatura.
	 * @param Coordinador  Dni del profesor coordinador de la asignatura.
	 * @param prerrequisitos Pre-requisitos, es decir, las asignaturas que tienen que haber superado los alumnos que deseen matricularse en la asignatura.
	 */
	public Asignatura(String Siglas, String nombre, int curso,int cuatrimestre, String Coordinador, String prerrequisitos){
		this.setSigla(Siglas);
		this.setNombre(nombre);
		this.setCurso(curso);
		this.setCuatrimestre(cuatrimestre);
		this.setCoordinador(Coordinador);
		this.setPrerrequisitos(prerrequisitos);
      

	}
	/**Este cosntructor solo es utilizado para almacenar las asignaturas superadas por un alumno.
	 * 
	 * @param Siglas Siglas de la asignatura.
	 * @param cursoacadem Curso acad�mico en el que se aprob� la asignatura
	 * @param nota Nota con la que el alumno super� la asignatura.
	 */
	//Para asignaturas superadas
	public Asignatura(String Siglas,String cursoacadem,double nota) {
		
		this.setSigla(Siglas);
		this.setCursoacadem(cursoacadem);
		this.setNota(nota);
	}

	/**M�todo empleado para registrar el curso en el que se imparte la asignatura.
	 * 
	 * @param curso2 curso en el que se imparte la asignatura.
	 */
	public void setCurso(int curso2) {
		this.Curso=curso2;
		
	}
	/** M�todo empleado para obtener el curso en el que se imparte la asignatura.
	 *
	 * @return curso en el que se imparte la asignatura.
	 */
public int  getCurso() {
	return Curso;
}

/**M�todo empleado para registrar el cuatrimestre en el que se imparte la asignatura.
 * 	
 * @param cuatrimestre2 cuatrimestre en el que se imparte la asignatura.
 */
	public void setCuatrimestre(int cuatrimestre2) {
		this.Cuatrimestre=cuatrimestre2;
		
	}
	public int getCuatrimestre() {
		return Cuatrimestre;
	}
/**M�todo empleado para extraer de una asignatura su listado de grupos te�ricos y trabajar con ellos.
 * 
 * @return lista de grupos te�ricos de la asignatura.
 */


	public TreeMap<Integer, Grupo> getListaGruposA(){
		return this.listaGruposA;
	}
	/**M�todo empleado para extraer de una asignatura su listado de grupos pr�cticos y trabajar con ellos.
	 * 
	 * @return lista de grupos pr�cticos de la asignatura.
	 */
	
	public TreeMap<Integer, Grupo> getListaGruposB(){
		return this.listaGruposB;
	}
	/**M�todo para registrar en una asignatura un listado de sus grupos de teor�a.
	 * 
	 * @param grupos lista de los grupos de teor�a de la asignatura.
	 */
	public void setListaGruposA(TreeMap <Integer,Grupo> grupos) {
		this.listaGruposA=grupos;
	}
	/**M�todo para registrar en una asignatura un listado de sus grupos de pr�cticas.
	 * 
	 * @param grupos lista de los grupos pr�cticos  de la asignatura.
	 */
	
	public void setListaGruposB(TreeMap <Integer,Grupo> grupos) {
		this.listaGruposB=grupos;
	}
	
	/**M�todo para obtener el nombre de la asignatura.
	 * 
	 * @return String con el nombre de la asignatura.
	 */

	public String getNombre(){
		return this.Nombre;
	}
	
	/**M�todos para obtener el dni del profesor encargadado de coordinar esa asignatura. Dicho profesor debe tener la categor�a de titular.
	 * 
	 * @return String con el dni del coordinador.
	 */
	
	public String getIDCoordinador(){
		return this.Coordinador;
	}
	/**M�todos para resgitrar el dni del profesor encargadado de coordinar esa asignatura. Dicho profesor debe tener la categor�a de titular.
	 * 
	 * @param coordinador String con el dni del coordinador.
	 */
	
	public void setCoordinador(String coordinador) {
		this.Coordinador=coordinador;
	}
	/**M�todo en el que obtenemos los pre-requisitos de la asignatura, es decir, las asignaturas que tiene que haber aprobado un alumno para poder cursar la asignatura.
	 * 
	 * @return los prerrequisitos necesarios.
	 */

	public String[] getPrerrequisitos(){
		return this.Prerrequisitos;
	}
	/**M�todos para resgitrar el nombre de la asignatura.
	 * 
	 * @param nombre String con el nombre de la asignatura.
	 */

	public void setNombre(String nombre){
		this.Nombre=nombre;
	}

	/**M�todo en el que registramos los pre-requisitos de la asignatura, es decir, las asignaturas que tiene que haber aprobado un alumno para poder cursar la asignatura.
	 * 
	 * @param prerrequisitos2 los prerrequisitos necesarios.
	 */

	
	public void setPrerrequisitos (String prerrequisitos2){
		this.Prerrequisitos=prerrequisitos2.trim().split(";");
	}
  

	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**M�todo con el que obtenemos las siglas con las que identificamos la asignatura.
	 * 
	 * @return siglas de la asignatura.
	 */

	public String getSigla() {
		return Sigla;
	}
	
	/**M�todo con el que registrammos las siglas con las que identificamos la asignatura.
	 * 
	 * @param sigla siglas de la asignatura.
	 */

	public void setSigla(String sigla) {
		this.Sigla = sigla;
	}

     public String cadena() {
		return String.format("%s/n%s/n%i/n%i/n%s/n",Sigla,Nombre,Curso,Cuatrimestre,Coordinador);
	}
    
     /**M�todo para obtener la nota que un alumno tiene en una asignatura, ya sea en la parte te�rica o pr�ctica. La nota olo puede ser de 0 a 5.
      * 
      * @return double con la nota.
      */



	public double getNota() {
		return nota;
	}
	
	/** M�todo para registrar la nota que un alumno tiene en una asignatura, ya sea en la parte te�rica o pr�ctica. La nota olo puede ser de 0 a 5.
	 * 
	 * @param nota nota del alumno
	 */

	public void setNota(double nota) {
		this.nota = nota;
	}
	
	/**M�todo para obtener el curso acad�mico en el que se evalu� una asignatura y ,en el caso de que el alumno la aprobase, es el curso en el que la aprob�.
	 * 
	 * @return Curso acad�mico.
	 */
	public String getCursoacadem() {
		return cursoacadem;
	}
	
	/**M�todo para registrar el curso acad�mico en el que se evalu� una asignatura y ,en el caso de que el alumno la aprobase, es el curso en el que la aprob�.
	 * 
	 * @param cursoacadem Curso acad�mico.
	 */

	public void setCursoacadem(String cursoacadem) {
		this.cursoacadem = cursoacadem;
	}
	/**M�todo para obtener la duraci�n de los grupos te�ricos de una asignatura. Puede ser una o dos horas.
	 * 
	 * @return Duraci�n de los grupos te�ricos de una asignatura. Devolver� un uno o un dos.
	 */
	public int getDuracionGrupoA() {
		return DuracionGrupoA;
	}
	/**M�todo para registrar la duraci�n de los grupos te�ricos de una asignatura. Puede ser una o dos horas.
	 * 
	 * @param duracionGrupoA Duraci�n de los grupos te�ricos de una asignatura.
	 */
	public void setDuracionGrupoA(int duracionGrupoA) {
		DuracionGrupoA = duracionGrupoA;
	}
	/**M�todo para obtener la duraci�n de los grupos pr�cticas de una asignatura. Puede ser una o dos horas.
	 * 
	 * @return Duraci�n de los grupos te�ricos de una asignatura. Devolver� un uno o un dos.
	 */
	public int getDuracionGrupoB() {
		return DuracionGrupoB;
	}
	/**M�todo para registrar la duraci�n de los grupos pr�cticos de una asignatura. Puede ser una o dos horas.
	 * 
	 * @param duracionGrupoB Duraci�n de los grupos te�ricos de una asignatura.
	 */
	
	public void setDuracionGrupoB(int duracionGrupoB) {
		DuracionGrupoB = duracionGrupoB;
	}

	



	

}

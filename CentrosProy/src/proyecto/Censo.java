package proyecto;

import java.util.TreeMap;
/**Clase utilizada como base central de datos,en ella se almacenar�n todos los datos necesarios para la
 * correcta ejecuci�n de la aplicaci�n,tales como las personas acad�micas(Profesores/alumnos), las aulas disponibles,
 * asignaturas impartidas en la escuela y e plan de organizaci�n docente.
 * 
 * @author Lorena Gil Cerezales
 * @author M�nica Loureiro Zunzunegui
 *
 */

public class Censo {
	/**Colecci�n de todas las asignaturas que se imparten en la escuela.
	 * 
	 */
	private TreeMap<String, Asignatura> asignaturas = new TreeMap<String, Asignatura>();
	/**Colecci�n de todas las aulas que dispone la escuela para impartir clases.
	 * 
	 */
	private TreeMap<String, Aula> aula=new TreeMap <String,Aula>();
	/**Coleccion de todos los alumnos matriculados en la escuela.
	 * 
	 */
	
	private TreeMap<String, Alumno> alumnos = new TreeMap<String, Alumno>();
	/**Coleccion de todos los profesores que imparten docencia en la escuela.
	 * 
	 */
	
	private TreeMap<String,Profesor> profesores=new TreeMap<String,Profesor>();
	/**Plan de organizacion docente del centro.
	 * 
	 */
	private TreeMap<String, Pod> pod=new TreeMap <String, Pod>();
	/**Curso acad�mico sobre el que queremos consultar informacion.
	 * 
	 */
	 private String curso="";
	 /**Semana de inicio del respectivo curso acad�mico.
	  * 
	  */
	 private String semanainicio="";
	 /**Constructor vac�o de la clase Censo.
	  * 
	  */
	
	public Censo(){

	}
	/**M�todo para la obtencion de las Asignaturas que se imparten en la escuela,con todos los datos que se necesitan sobre ellas.
	 * 
	 * @return Mapa de Asignaturas,cuya clave ser�n sus siglas.
	 */

	public TreeMap<String, Asignatura> getAsignaturas() {
		return asignaturas;
	}
	/**M�todo para guardar todas las asignaturas que se imparten en la escuela.En este proyecto no lo vamos a utilizar
	 * puesto que guardamos las asignaturas una por una.
	 * 
	 * @param asignaturas Mapa con las asignaturas.
	 */

	public void setAsignaturas(TreeMap<String, Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
	/**M�todo para obtener todos los alumnos que est�n matriculados en la escuela por orden alfab�tico.
	 * 
	 * @return Mapa con todos los alumnos con su dni como clave.
	 */

	public TreeMap<String, Alumno> getAlumnos() {
		return alumnos;
	}
	/**M�todo para guardar todos los alumnos que est�n matriculados en la escuela por orden alfab�tico.Este m�todo no ser� utilizado.
	 * 
	 * @param alumnos Mapa con todos los alumnos con su dni como clave.
	 */

	public void setAlumnos(TreeMap<String, Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	/**M�todo para obtener todos los datos de los docentes que imparten clase en la escuela.
	 * 
	 * @return Mapa de profesores con su dni como clave.
	 */

	public TreeMap<String,Profesor> getProfesores() {
		return profesores;
	}
	/**M�todo para guardar todos los datos de los docentes que imparten clase en la escuela.
	 * 
	 * @param profesores Mapa con todos los profesores.
	 */

	public void setProfesores(TreeMap<String,Profesor> profesores) {
		this.profesores = profesores;
	}
	/**M�todo para obtener las Aulas disponibles en el centro para la imparticion de la clases.
	 * 
	 * @return Mapa con todas las aulas y su identificador como clave.
	 */
	public TreeMap<String, Aula> getAula(){
		return aula;
	}
	/**M�todo para guardar las Aulas disponibles en el centro para la imparticion de la clases.
	 * 
	 * @param aula Mapa con todas las aulas.
	 */
	public void setAula(TreeMap<String,Aula> aula) {
		this.aula=aula;
		
	}
/**M�todo para obtener el plan de organizaci�n docente de la escuela.En el se encuentran todos los datos
 * sobre la ocupacion de los docentes.
 * 
 * @return Mapa con la organizaci�n docente.
 */

	public  TreeMap<String, Pod> getPod(){
		return pod;
	}
	/**M�todo para obtener el plan de organizaci�n docente de la escuela.En el se encuentran todos los datos
     * sobre la ocupacion de los docentes.
	 * 
	 * @param pod Mapa con todos los datos sobre la ocupacion de los docentes.La clave ser� el dni del docente.
	 */
	public void setPod(TreeMap<String, Pod> pod) {
		this.pod=pod;
	}
	
/**M�todo para obtener el curso acad�mico sobre el que se quiere obtener cierta informaci�n.Este ha sido obtenido del fichero
 * 'cursoAcademico.txt'
 * 
 * @return Curso en formato (aa/aa)
 */
	public String getCurso() {
		return curso;
	}
	/**M�todo para guardar el curso acad�mico.	 * 
	 * @param curso Curso en formato (aa/aa)
	 */

	public void setCurso(String curso) {
		this.curso = curso;
	}
/**M�todo para obtener la semana en la que empieza el curso acad�mico indicado en el fichero 'cursoAcademico.txt'
 * 
 * @return Semana de inicio del curso(String: aa/aa)
 */
	public String getSemanainicio() {
		return semanainicio;
	}
	/**M�todo para guardar la semana de inicio del curso acad�mico.
	 * 
	 * @param semanainicio Semana de inicio del curso(String).
	 */

	public void setSemanainicio(String semanainicio) {
		this.semanainicio = semanainicio;
	}

}


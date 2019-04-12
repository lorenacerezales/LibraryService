package proyecto;

import java.util.TreeMap;
/**En esta clase guardaremos toda la información relativa a los profesores, es decir, aquellas personas pertenecientes al centro que se encargan de impartir la docencia en la escuela.
 * Los profesores pueden ser de dos tipos: Titular o asociado. Dependiendo del tipo que se le asigne al profesor este tendrá más o menos horas en las que impartirá clase y, también, determinará si podrá ser o no coordinador de una asignatura.
 * 
 * @author Monica Loureiro Zunzunegui
 * @author Lorena Gil Cerezales
 *
 */

public class Profesor extends PersonaAcademica{
	/** Categoría del profesor con dos valore posibles: titular o asociado.*/
	private String categoria;
	/**Departamento de la escuela a la que pertenece el profesor.*/
	private String departamento;
	/**Listado de los grupos de teoría en los que el profesor imparte docencia.*/
	private TreeMap<String,Grupo>docenciaA=new TreeMap<String,Grupo>();
	/**Listado de los grupos prácticos en los que el profesor imparte docencia.*/
	private TreeMap<String,Grupo>docenciaB=new TreeMap<String,Grupo>();
	/** Listado de los grupos que se imparten en los distintos días de la semana, de lunes a viernes.*/
	private TreeMap<Character,Grupo> dias=new TreeMap<Character,Grupo>();

	/**Constructor que genera un profesor con todos sus datos.
	 * 
	 * @param dni Documento Nacional de Identidad del profesor que vamos a generar.
	 * @param nombre Nombre y apellidos del profesor, separados por una coma.
	 * @param fechaNacString Fecha de nacimiento del profesor.
	 * @param categ Categoría del profesor con dos valore posibles: titular o asociado.
	 * @param depart Departamento de la escuela a la que pertenece el profesor.
	 */
public Profesor(String dni, String nombre, String fechaNacString,String categ,String depart) {
		super(dni, nombre, fechaNacString);
		this.setDepartamento(depart);
		this.setCategoria(categ);
		
	}
  
/**Método para registrar un nuevo grupo teórico impartido por el profesor en el listado de docencia impartida del grupo teórico. El parametro introducido es un String con todos los datos seguidos separados por espacios,
    * por lo que antes de guardarlos, el método tendrá que separarlos.
    *
    * @param docenc String con todos los campos que deseamos guardar, separados por espacios.
    */

	public void setDocenciaA(String docenc) {
		String[]docencias=docenc.split("\\s+");
		if(docenciaA.containsKey(docencias[0])) {
			docenciaA.get(docencias[0]).setIdGrupo(docencias[2].trim());
			
		}else {
	docenciaA.put(docencias[0],new Grupo('A',docencias[2],0,'L',""));
		}
	
}
	
	/**Método para obtener el listado de todos los grupos teóricos que imparte un profesor.
	 * 
	 * @return TreeMap con el listado de los grupos teóricos que imparte el profesor.
	 */

public TreeMap<String,Grupo> getDocenciaA() {
	return docenciaA;
}

/**Método para registrar un nuevo grupo práctico impartido por el profesor en el listado de docencia impartida del grupo teórico. El parametro introducido es un String con todos los datos seguidos separados por espacios,
  * por lo que antes de guardarlos, el método tendrá que separarlos.
  *
  * @param docenc String con todos los campos que deseamos guardar, separados por espacios.
  */
public void setDocenciaB(String docenc) {

	String[]docencias=docenc.split("\\s+");
	if(docenciaB.containsKey(docencias[0])) {
		docenciaB.get(docencias[0]).setIdGrupo(docencias[2].trim());
		
	}else {

    docenciaB.put(docencias[0],new Grupo('B',docencias[2].trim(),0,'L',""));
	}

}

/**Método para obtener el listado de todos los grupos prácticos que imparte un profesor.
 * 
 * @return TreeMap con el listado de los grupos prácticos que imparte el profesor.
 */

public TreeMap<String,Grupo> getDocenciaB() {
return docenciaB;
}

    /**Método para obtener la categoría del profesor.
     * 
     * @return String con la categoría del profesor: será "titular" o "asociado".
     */

	public String getCategoria() {
		return categoria;
	}
	
	 /**Método para registrar la categoría del profesor.
     * 
     * @param categoria String con la categoría del profesor: será "titular" o "asociado".
     */
	

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	/**Método para obtener el departamento al que pertenece el profesor.
	 * 
	 * @return String que contiene el nombre del departamento al que pertenece el profesor.
	 */

	public String getDepartamento() {
		return departamento;
	}
	
	/**Método para resgitrar el departamento al que pertenece el profesor.
	 * 
	 * @param departamento String que contiene el nombre del departamento al que pertenece el profesor.
	 */

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	/**Método para obtener el listado con los grupos que se dan a lo largo de los dias de la semana.
	 * 
	 * @return TreeMap con el listado.
	 */

	public TreeMap<Character,Grupo> getDias() {
		return dias;
	}

	/**Método para registrar un grupo, teórico o práctico, en un día de la semana determinado.
	 * 
	 * @param tipo Char que nos dindica si el grupo es tipo A( teórico) o B (práctico).
	 * @param Id Identificador númerico del grupo.
	 * @param horaini Hora a la que empieza a impartirse la docencia de ese grupo.
	 * @param dia Dia de la semana en el que se imparte la docencia del grupo.
	 * @param aula Aula en la que se imparte la odcencia del grupo.
	 */

	public void setDias(char tipo,String Id,int horaini,char dia,String aula) {
		dias.put(dia,new Grupo(tipo,Id,horaini,dia,aula));
	}



}

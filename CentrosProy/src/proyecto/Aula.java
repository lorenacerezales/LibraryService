package proyecto;

import java.util.TreeMap;
/**La clase 'Aula' recoge los datos referentes a las estancias disponibles en la escuela para la imparticion de clases por un docente,tanto de 
 * grupos Teóricos como Prácticos.Está clase será especialmente utilizada para obtener la ocupación semanal de un aula en concreto.
 * 
 * @author Lorena Gil Cerezales
 * @author Mónica Loureiro Zunzunegui
 *
 */
public class Aula{
	/**Identificador del aula.
	 * 
	 */
	private String Sigla;
	/**Grupo para el que está diseñado el aula.
	 * 
	 */
	private String Grupo;
	/**Capacidad máxima de la aula.
	 * 
	 */
	private int Capacidad;
	/**Asignaturas que se van a impartir en ese aula.
	 * 
	 */
	private TreeMap<String,Grupo> asignAula=new TreeMap<String,Grupo>();
    /**Coleccion de asignaturas de grupo A que se imparten en el aula.
     * 
     */
	private TreeMap<String,Grupo>gruposA=new TreeMap<String,Grupo>();
	/**Coleccion de asignaturas de grupo B que se imparten en el aula.
	 * 
	 */
	private TreeMap<String,Grupo>gruposB=new TreeMap<String,Grupo>();
	/**Ocupacion del aula durante la semana.Las claves serán los identificadores de los dias.
	 * 
	 */
	private TreeMap<Character,Grupo>dias=new TreeMap<Character,Grupo>();
	
/**Método constructor utilizado a la hora de crear una instancia de la clase 'Aula'.
 * 	
 * @param sigla Identificador del aula específica.
 * @param grupo Tipo de grupo que se puede impartir en esa aula.
 * @param capacidad Número de alumnos que se pueden meter en el aula.
 */
	
	public Aula(String sigla, String grupo, int capacidad) {
		this.setSigla(sigla);
		this.setGrupo(grupo);
		this.setCapacidad(capacidad);
		
		
		
	}
	/**Método encargado de devolver el identificador del aula.
	 * 
	 * @return Identificador del aula.
	 */
	public String getSigla() {
		return Sigla;
	}
	/**Método encargado de guardar el identificador del aula.
	 * 
	 * @param sigla Identificador del aula.
	 */
	public void setSigla(String sigla) {
		Sigla = sigla;
	}
	/**Método para la obtención del tipo de grupo (A/B) para el que está diseñado
	 * el aula.
	 * 
	 * @return tipo de grupo.(String)
	 */
	
	public String getGrupo() {
		return Grupo;
	}
	/**Método utilizado para guardar el tipo de grupo para el que está diseñado
	 * el aula.
	 * 
	 * @param grupo(String) 
	 */
	
	public void setGrupo(String grupo) {
		Grupo = grupo;
	}
	
	/**Método utilizado para obtener la capacidad del aula específica.
	 * 
	 * @return Capacidad del aula.(int)
	 */
	public int getCapacidad() {
		return Capacidad;
	}
	/**Método utilizado para almacenar la capacidad del aula.
	 * 
	 * @param capacidad Capacidad del aula.(int)
	 */
	public void setCapacidad(int capacidad) {
		Capacidad = capacidad;
	}
	
	/**Método para la obtención del mapa que contiene las asignaturas que se dan en esa aula.La claves serán las
	 * siglas de la propia asignatura.
	 * 
	 * @return Mapa con las asignaturas.
	 * 
	 */
	public TreeMap<String,Grupo> getAsignAula() {
		return asignAula;
	}
	
	/**Método utilizado para guardar las asignaturas que se imparten.
	 * 
	 * @param asignAula Mapa.
	 */
	public void setAsignAula(TreeMap<String,Grupo> asignAula) {
		this.asignAula = asignAula;
	}
	/**Método encargado de la obtención de los grupos A que se imparten en la aula.
	 * 
	 * @return Mapa con los grupos Teóricos.
	 */
	public TreeMap<String,Grupo> getGruposA() {
		return gruposA;
	}
	/**Método para guardar en el mapa que contiene a los grupos teóricos un nuevo grupo.
	 * 
	 * @param grupo Tipo de grupo al que pertenece,en este caso siempre va a ser 'A'.(String)
	 * @param idgrupo Identificador del grupo(String)
	 * @param dia Dia en el que se necesita el aula para impartir el grupo.(String)
	 * @param Aula Identificador del aula.(String)
	 * @param hora Hora de inicio de la clase.(int: hh)
	 */
	public void setGruposA(String grupo,String idgrupo,String dia,String Aula,int hora) {
		gruposA.put(grupo, new Grupo('A',idgrupo,hora,dia.charAt(0),Aula));
	}
	/**Método para la obtención de los grupos 'B' que se imparten en la aula.
	 * 
	 * @return Mapa de los grupos Prácticos('B').
	 */
	public TreeMap<String,Grupo> getGruposB() {
		return gruposB;
	}
	/**Método para guardar en el mapa que contiene a los grupos Prácticos un nuevo grupo.
	 * 
	 * @param grupo Tipo de grupo al que pertenece,en este caso siempre va a ser 'B'.(String)
	 * @param idgrupo Identificador del grupo(String)
	 * @param dia Dia en el que se necesita el aula para impartir el grupo.(String)
	 * @param Aula Identificador del aula.(String)
	 * @param hora Hora de inicio de la clase.(int: hh)
	 */
	
	
	public void setGruposB(String grupo,String idgrupo,String dia,String Aula,int hora) {
		gruposB.put(grupo, new Grupo('B',idgrupo,hora,dia.charAt(0),Aula));
	}
	/**Método para la obtención de la ocupación del aula durante los días laborables de la semana.
	 * 
	 * @return Mapa con los dias como clave.
	 */
	public TreeMap<Character,Grupo> getDias() {
		return dias;
	}
	/**Método para guardar la ocupacion en cada día.
	 * 
	 * @param IdGrupo Identificador del grupo (String).
	 * @param dia Dia que nos servirá como clave del mapa.
	 * @param horaini Hora de inicio de la clase(int: hh).
	 * @param Aula Identificador del aula.(String)
	 * @param tipogrupo Tipo de grupo al que pertenece (A/B).(character)
	 */
	public void setDias(String IdGrupo,char dia,int horaini,String Aula,char tipogrupo) {
		dias.put(dia, new Grupo(tipogrupo,IdGrupo,horaini,dia,Aula));
	}
	
	

}

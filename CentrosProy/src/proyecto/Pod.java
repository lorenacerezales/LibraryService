package proyecto;

import java.util.TreeMap;
/**Esta clase contiene la información sobre el Plan de Organización Docente(POD) que debe impartir cada profesor. Será distinto para cada profesor.
 * 
 * @author Monica Loureiro Zunzunegui
 * @author Lorena Gil Cerezales
 *
 */

public class Pod {
	/***Documento Nacional de Identidad del profesor que debe impartir el POD.*/
	private String dni;
	/**Listado de los grupos teóricos que debe impartir el profesor.*/
	private TreeMap<String,Grupo> listaGruposA=new TreeMap<String,Grupo>();
	/**Listado de los grupos prácticos que debe impartir el profesor.*/
	private TreeMap<String,Grupo> listaGruposB=new TreeMap<String,Grupo>();
	/**
	 * Constructor que genera un nuevo POD.
	 * @param dni Dni del profesor que impartirá el POD.
	 */
	public Pod(String dni){
		this.setDni(dni);
		
	}
	/**Método para registrar el dni del profesor que imparte el POD.
	 * 
	 * @param dni2 Documento Nacional de Identidad del profesor.
	 */

	public void setDni(String dni2) {
		this.dni=dni2;
		
	}
	/**Método para obtener el dni del profesor que imparte el POD.
	 * 
	 * @return dni2 Documento Nacional de Identidad del profesor.
	 */
	public String getDni() {
		return dni;
			
	}
	
	/**Método para obtener el listado que contiene la información de los grupos teóricos que debe impartir el profesor.
	 * 
	 * @return Listado de los grupos teóricos.
	 */

	public TreeMap<String,Grupo> getListaGruposA() {
		return listaGruposA;
	}
	
	/**Método para registrar el listado de los grupos teóricos que debe impartir el profesor.
	 * 
	 * @param asignatura Identificador de la asignatura
	 *@param tipo Tipo de grupo(A/B).
	 *@param num Numero del grupo.
	 */

	public void setListaGruposA(String asignatura,String tipo,String num) {
		TreeMap<String,Grupo> listaGruposA=getListaGruposA();
		if(!listaGruposA.containsKey(asignatura)) {		
		
	
		listaGruposA.put(asignatura,new Grupo());
		listaGruposA.get(asignatura).setNumGrupos(Integer.parseInt(num));
		}else {
	
		Grupo grupoaux=listaGruposA.get(asignatura);
		grupoaux.setNumGrupos(Integer.parseInt(num));
		}
	
	}
	public void aumentarPodA() {
		
		
	}
	public void aumentarPodB() {
		
	}
	
	/**Método para obtener el listado que contiene la información de los grupos prácticos que debe impartir el profesor.
	 * 
	 * @return Listado de los grupos prácticos.
	 */

	public TreeMap<String,Grupo> getListaGruposB() {
		return listaGruposB;
	}
	
	/**Método para registrar el listado de los grupos prácticos que debe impartir el profesor.
	  * @param asignatura1 Identificador de la asignatura
	 *@param tipo Tipo de grupo(A/B).
	 *@param num1 Numero del grupo.
	 */
	public void setListaGruposB(String asignatura1,String tipo,String num1) {
		TreeMap<String,Grupo> listaGruposB=getListaGruposB();
		if(!listaGruposB.containsKey(asignatura1)) {		
		
	
		listaGruposB.put(asignatura1,new Grupo());
		listaGruposB.get(asignatura1).setNumGrupos(Integer.parseInt(num1));
		}else {
	
		Grupo grupoaux=listaGruposB.get(asignatura1);
		grupoaux.setNumGrupos(Integer.parseInt(num1));
		}
	}

}
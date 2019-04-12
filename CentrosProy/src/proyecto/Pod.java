package proyecto;

import java.util.TreeMap;
/**Esta clase contiene la informaci�n sobre el Plan de Organizaci�n Docente(POD) que debe impartir cada profesor. Ser� distinto para cada profesor.
 * 
 * @author Monica Loureiro Zunzunegui
 * @author Lorena Gil Cerezales
 *
 */

public class Pod {
	/***Documento Nacional de Identidad del profesor que debe impartir el POD.*/
	private String dni;
	/**Listado de los grupos te�ricos que debe impartir el profesor.*/
	private TreeMap<String,Grupo> listaGruposA=new TreeMap<String,Grupo>();
	/**Listado de los grupos pr�cticos que debe impartir el profesor.*/
	private TreeMap<String,Grupo> listaGruposB=new TreeMap<String,Grupo>();
	/**
	 * Constructor que genera un nuevo POD.
	 * @param dni Dni del profesor que impartir� el POD.
	 */
	public Pod(String dni){
		this.setDni(dni);
		
	}
	/**M�todo para registrar el dni del profesor que imparte el POD.
	 * 
	 * @param dni2 Documento Nacional de Identidad del profesor.
	 */

	public void setDni(String dni2) {
		this.dni=dni2;
		
	}
	/**M�todo para obtener el dni del profesor que imparte el POD.
	 * 
	 * @return dni2 Documento Nacional de Identidad del profesor.
	 */
	public String getDni() {
		return dni;
			
	}
	
	/**M�todo para obtener el listado que contiene la informaci�n de los grupos te�ricos que debe impartir el profesor.
	 * 
	 * @return Listado de los grupos te�ricos.
	 */

	public TreeMap<String,Grupo> getListaGruposA() {
		return listaGruposA;
	}
	
	/**M�todo para registrar el listado de los grupos te�ricos que debe impartir el profesor.
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
	
	/**M�todo para obtener el listado que contiene la informaci�n de los grupos pr�cticos que debe impartir el profesor.
	 * 
	 * @return Listado de los grupos pr�cticos.
	 */

	public TreeMap<String,Grupo> getListaGruposB() {
		return listaGruposB;
	}
	
	/**M�todo para registrar el listado de los grupos pr�cticos que debe impartir el profesor.
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
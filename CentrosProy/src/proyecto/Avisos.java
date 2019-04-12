package proyecto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**La clase 'Avisos' se encargar� de realizar la tarea de evaluar los posibles errores introducidos en el fichero
 * de ejecuci�n, imprimiendo consecuentemente en un fichero ('avisos.txt') los errores detectados.
 * Esta clase contiene varios constructores, los cuales se utilizar�n dependiendo de los par�metros que se le pasen
 * pues cada aviso debe contener diferente informaci�n.
 * 
 * @author Lorena Gil Cerezales
 * @author M�nica Loureiro Zunzunegui
 *
 */
public class Avisos {
	/**M�todo constructor utilizado en los comandos en los que no necesitamos saber que l�nea del fichero especificamente
	 * ha fallado; solo el comando que ha generado el aviso.
	 * 
	 * @param error Nombre de la funcionalidad que ha generado el error(String)
	 * @param Numerror Identificador del tipo de error generado(int)
	 */
	public Avisos(String error,int Numerror) {
		if(error.equals("Insertar Persona")) {
			
			switch(Numerror){
			
			case 0:{
				escribeFichero("IP -- Dni incorrecto");				
				break;
			}
			case 1:{
				escribeFichero("IP -- Fecha incorrecta");
				break;
			}
			case 2:{
				escribeFichero("IP -- Fecha de ingreso incorrecta");
				break;
			}
			case 3:{
				escribeFichero("IP -- Profesor ya existente");
				break;
			}
			case 4:{
				escribeFichero("IP -- Alumno ya existente");
				break;
			}
			case 5:{
				escribeFichero("IP -- Numero de argumentos incorrecto");
				break;
				
				
			}
			
			}
		}
		if(error.equals("Asignar Grupo")) {
			switch(Numerror) {
			
			case 0:{
				escribeFichero("AGRUPO -- Alumno/profesor inexistente");
				break;
			}
			case 1:{
				escribeFichero("AGRUPO -- Asignatura inexistente");
				break;
			}
			case 2:{
				escribeFichero("AGRUPO -- Tipo de grupo incorrecto");
				break;
			}
			case 3:{
				escribeFichero("AGRUPO -- Grupo inexistente");
				break;
			}
			case 4:{
				escribeFichero("AGRUPO -- Grupo ya asignado");
				break;
			}
			case 5:{
				escribeFichero("AGRUPO -- Alumno no matriculado");
				break;
			}
			case 6:{
				escribeFichero("AGRUPO -- Asignatura/tipo-grupo no presente en POD del profesor");
				break;
			}
			case 7:{
				escribeFichero("AGRUPO -- Numero de grupos superior al contemplado en POD");
				break;
			}
			case 8:{
				escribeFichero("AGRUPO -- Solape alumno");
				break;
			}
			case 9:{
				escribeFichero("AGRUPO -- Solape profesor");
				break;
			}
			case 10:{
				escribeFichero("AGRUPO -- Aula completa");
				break;
			}
			
			}
		}
		if(error.equals("Matricular Alumno")) {
			switch(Numerror) {
			
			case 0:{
				escribeFichero("MAT -- Alumno inexistente");
				break;
			}
			case 1:{
				escribeFichero("MAT -- Asignatura inexistente");
				break;
			}
			case 2:{
				escribeFichero("MAT -- Ya es alumno de la asignatura indicada");
				break;
			}
			case 3:{
				escribeFichero("MAT -- No cumple requisitos");
				break;
			}
			}
		}
		if(error.equals("Crear Grupo Asignatura")) {
			switch(Numerror) {
			
			case 0:{
				escribeFichero("CGA -- Asignatura inexistente");
				break;
			}
			case 1:{
				escribeFichero("CGA -- Tipo de grupo incorrecto");
				break;
			}
			case 2:{
				escribeFichero("CGA -- Grupo ya existente");
				break;
			}
			case 3:{
				escribeFichero("CGA -- Dia incorrecto");
				break;
			}
			case 4:{
				escribeFichero("CGA -- Aula no existente");
				break;
			}
			case 5:{
				escribeFichero("CGA -- Solape de Aula");
				break;
			}
			}
		}
		if(error.equals("Obtener Expediente Alumno")) {
			escribeFichero("EXP -- Alumno inexistente");	
		}
		if(error.equals("ComandoIncorrecto")) {

			escribeFichero("Comando Incorrecto");	
		}
		if(error.equals("ObtenerCalendProfesor")) {
			escribeFichero("OCP -- Profesor Inexistente");
		}
		
	}
	/**Constructor que utilizaremos cuando queramos saber el comando que ha generado el error dentro del fichero
	 * 'ejecucion.txt',el error espec�fico, el dni de la persona que contiene ese comando y la l�nea del fichero
	 * que ha generado error(pasado por par�metro su nombre).
	 * 
	 * @param error String que identifica la funci�n que ha gemerado error.
	 * @param Numerror Identificador del tipo de error.
	 * @param linea Linea del fichero.
	 * @param dni Identificador de la persona acad�mica.
	 */
		public Avisos(String error,int Numerror,int linea,String dni) {
		
		switch(Numerror) {
		
		case 0:{
			escribeFichero("EVALUA -- Asignatura inexistente");
			break;
		}
		case 1:{
			escribeFichero("EVALUA -- Asignatura ya evaluada en ese curso acad�mico");
			break;
		}
		case 2:{
			escribeFichero("EVALUA -- Error en linea"+linea+":Alumno inexistente: "+dni);
			break;
		}
		case 3:{
			escribeFichero("EVALUA -- Error en linea"+linea+":Alumno no matriculado: "+dni);
			break;
		}
		case 4:{
			escribeFichero("EVALUA -- Error en linea"+linea+":Nota grupo A/B incorrecta");
			break;
		}
		
		
		}
		
	}
		/**Funci�n que se encargar� de escribir en el fichero espec�ficado el error generado en ese momento.
		 * 
		 * @param aviso Linea ya lista para escribir en el fichero.
		 */
	
	public static void escribeFichero(String aviso){

		File fichero=new File("avisos.txt");
		
		if(fichero.exists()){
			try{
				FileWriter punteroEscritura=new FileWriter("Avisos.txt",true);
				punteroEscritura.write(System.getProperty("line.separator") + aviso);
				punteroEscritura.close();	
			}catch(IOException e){
				
			}
		}else{
			try{
			
			FileWriter punteroEscritura=new FileWriter(fichero);
			punteroEscritura.write(aviso);
			punteroEscritura.close();
			}catch(IOException e)
				{
					
				}
			}
			
		}


}

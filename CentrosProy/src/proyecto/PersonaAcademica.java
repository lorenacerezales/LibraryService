package proyecto;

import java.util.GregorianCalendar;
/**La clase persona es una generalizaci�n de todos los individuos que forman parte de la escuela. Lo definimos como una superclase, que tendr� como subclases 'Alumno' y 'Profesor'.
 * La informaci�n que contiene es el dni, el nombre y la fecha de nacimiento del individuo.
 * 
 * @author Monica Loureiro Zunzunegui
 * @author Lorena Gi Cerezales
 *
 */

public class PersonaAcademica {
	/**Nombre y apellidos de la persona, separados por unca coma.*/
	private String nombre;
	/**Documento Nacional de Identidad de la persona. Es un identificador �nico de cada persona, por lo que nos permitir� saber en todo momento de que persona se trata.*/
	private String DNI;
	/** Fecha de nacimiento de la persona. debe tener el formato dd/mm/aaaa.*/
	private GregorianCalendar fechaNac;
	
/**Constructor de la superclase que genera una nueva persona acad�mica con la informaci�n que aparece a continuaci�n.
 * 
 * @param dni Documento Nacional de Identidad de la persona. Es un identificador �nico de cada persona, por lo que nos permitir� saber en todo momento de que persona se trata.
 * @param nombre Nombre y apellidos de la persona, separados por unca coma.
 * @param fechaString Fecha de nacimiento de la persona. debe tener el formato dd/mm/aaaa.
 */

public PersonaAcademica(String dni,String nombre,String fechaString) {
	this.setNombre(nombre);
	this.setDNI(dni);
	this.setFechaNac(formatofech(fechaString));
}

/** Constructor vac�o usado por la subclase 'Alumno'.
 * 
 */

public PersonaAcademica() {
	
}
/**M�todo en el que introducido un String ,que contiene una fecha con el formato dd/mm/aaa, nos separa dicha fecha y nos guarda por separado, en nuevas variables,
 * el dia, el a�o y el mes.
 * 
 * @param cadena String que contiene la fecha que queremos separar, con el formato dd/mm/aaaa.
 * @return Gregorian Calendar con el dia, mes y a�o separados.
 */

public static GregorianCalendar formatofech(String cadena) {
	
	char  sep = '/';//Separador en el fichero
	String exp= "["+sep+"]";
    String fechasep[] = cadena.split(exp);
	
	  final int dia=Integer.parseInt(fechasep[0]);		  
	  int mes=Integer.parseInt(fechasep[1]);
	  int anho=Integer.parseInt(fechasep[2]);
		
	GregorianCalendar separada= new GregorianCalendar(anho,mes-1,dia);
	
	  return separada;
}

/**M�todo para obtener el nombre y los apellidos de la persona acad�mica.
 * 
 * @return String con el nombre y los apellidos.
 */
public String getNombre() {
	return nombre;
}

/**M�todo para registrar el nombre y los apellidos de la persona acad�mica.
 * @param nombre nombre y apellidos.
 * 
 */
public void setNombre(String nombre) {
	this.nombre = nombre;
}

/**M�todo para obtener el dni de la persona acad�mica.
 * 
 * @return String con el dni.
 */
public String getDNI() {
	return DNI;
}

/**M�todo para registrar el dni de la persona acad�mica.
 * @param dNI String con el identificador.
 * 
 */
public void setDNI(String dNI) {
	DNI = dNI;
}

/**M�todo para obtener la fecha de nacimiento de la persona acad�mica.
 * 
 * @return Fecha de nacimiento de la persona acad�mica.
 */
public GregorianCalendar getFechaNac() {
	return fechaNac;
}
/**M�todo para resgitrar la fecha de nacimiento de la persona acad�mica.
 * 
 * @param fechaNac Fecha de nacimiento de la persona acad�mica.
 */
public void setFechaNac(GregorianCalendar fechaNac) {
	this.fechaNac = fechaNac;
}

	

}
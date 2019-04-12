package proyecto;

import java.util.GregorianCalendar;
/**La clase persona es una generalización de todos los individuos que forman parte de la escuela. Lo definimos como una superclase, que tendrá como subclases 'Alumno' y 'Profesor'.
 * La información que contiene es el dni, el nombre y la fecha de nacimiento del individuo.
 * 
 * @author Monica Loureiro Zunzunegui
 * @author Lorena Gi Cerezales
 *
 */

public class PersonaAcademica {
	/**Nombre y apellidos de la persona, separados por unca coma.*/
	private String nombre;
	/**Documento Nacional de Identidad de la persona. Es un identificador único de cada persona, por lo que nos permitirá saber en todo momento de que persona se trata.*/
	private String DNI;
	/** Fecha de nacimiento de la persona. debe tener el formato dd/mm/aaaa.*/
	private GregorianCalendar fechaNac;
	
/**Constructor de la superclase que genera una nueva persona académica con la información que aparece a continuación.
 * 
 * @param dni Documento Nacional de Identidad de la persona. Es un identificador único de cada persona, por lo que nos permitirá saber en todo momento de que persona se trata.
 * @param nombre Nombre y apellidos de la persona, separados por unca coma.
 * @param fechaString Fecha de nacimiento de la persona. debe tener el formato dd/mm/aaaa.
 */

public PersonaAcademica(String dni,String nombre,String fechaString) {
	this.setNombre(nombre);
	this.setDNI(dni);
	this.setFechaNac(formatofech(fechaString));
}

/** Constructor vacío usado por la subclase 'Alumno'.
 * 
 */

public PersonaAcademica() {
	
}
/**Método en el que introducido un String ,que contiene una fecha con el formato dd/mm/aaa, nos separa dicha fecha y nos guarda por separado, en nuevas variables,
 * el dia, el año y el mes.
 * 
 * @param cadena String que contiene la fecha que queremos separar, con el formato dd/mm/aaaa.
 * @return Gregorian Calendar con el dia, mes y año separados.
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

/**Método para obtener el nombre y los apellidos de la persona académica.
 * 
 * @return String con el nombre y los apellidos.
 */
public String getNombre() {
	return nombre;
}

/**Método para registrar el nombre y los apellidos de la persona académica.
 * @param nombre nombre y apellidos.
 * 
 */
public void setNombre(String nombre) {
	this.nombre = nombre;
}

/**Método para obtener el dni de la persona académica.
 * 
 * @return String con el dni.
 */
public String getDNI() {
	return DNI;
}

/**Método para registrar el dni de la persona académica.
 * @param dNI String con el identificador.
 * 
 */
public void setDNI(String dNI) {
	DNI = dNI;
}

/**Método para obtener la fecha de nacimiento de la persona académica.
 * 
 * @return Fecha de nacimiento de la persona académica.
 */
public GregorianCalendar getFechaNac() {
	return fechaNac;
}
/**Método para resgitrar la fecha de nacimiento de la persona académica.
 * 
 * @param fechaNac Fecha de nacimiento de la persona académica.
 */
public void setFechaNac(GregorianCalendar fechaNac) {
	this.fechaNac = fechaNac;
}

	

}
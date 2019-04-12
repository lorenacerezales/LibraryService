package proyecto;

import java.util.GregorianCalendar;
import java.util.TreeMap;

/**Los alumnos son un tipo de persona Acad�mica,de la cual podemos obtener su identificardor,su nombre,su expediente
 * y las clases a las que asiste.Sobre este tipo de colectivo podemos realizar distintas modificaciones, como matricularlo en
 * m�s asignaturas y asignar una nota.
 * 
 * @author Lorena Gil Cerezales
 * @author M�nica Loureiro Zunzunegui
 *
 */
public class Alumno extends PersonaAcademica {
/**Fecha en la que el alumno ha entrado a formar parte del alumnado de la escuela,ya en formato GregorianCalendar. 
 */
private GregorianCalendar fechaIngreso;
/**Coleccion de las asignaturas que ya ha superado el alumno.
 */
private TreeMap<String,Asignatura> asigsuperadas=new TreeMap<String,Asignatura>();
/**Correo de contacto del alumno.
 * 
 */
private String email;
/**Coleccion de asignaturas en las que el alumno se ha matriculado pero de las que a�n no tiene grupo.
 * 
 */
private TreeMap <String,Grupo>docencrec=new TreeMap<String,Grupo>();
/**Coleccion de asignaturas te�ricas(A) en las que el alumno est� matriculado.
 * 
 */
private TreeMap <String,Grupo>docencrecA=new TreeMap<String,Grupo>();
/**Coleccion de asignaturas pr�cticas(B) en las que el alumno est� matriculado.
 * 
 */
private TreeMap <String,Grupo>docencrecB=new TreeMap<String,Grupo>();
/**Ocupaci�n del alumnode lunes a viernes,la clave ser� el identificador deld�a.
 * 
 */
private TreeMap<Character,Grupo> dias=new TreeMap<Character,Grupo>();

/**Nota de una asignatura especifica de grupo A.Solo la utilizaremos en la funcionalidad Evaluar Asignatura.
 */
private double notaA;
/**Nota de una asignatura especifica de grupo A.Solo la utilizaremos en la funcionalidad Evaluar Asignatura.
 */
private double notaB;
  /**M�todo constructor encargado de guardar de manera g�nerica los datos referentes al alumno,no todos tienen por qu� existir.
   * 
   * @param dni Contiene el documento nacional de identidad de la persona a introducir.Formato(8 d�gitos y una letra may�scula)
   * @param nombre nombre y apellidos del alumno.
   * @param email direcci�n de correo electr�nico donde el alumno recibir� las notificaciones de la escuela.
   * @param fechaNacString Fecha de nacimiento del alumno en formato dd/mm/aaaa.El alumno debe de ser mayor de 16 a�os.
   * @param fechaIngString Fecha de Ingreso en la escuela en formato dd/mm/aaaa.
   */
	public Alumno(String dni, String nombre,String email, String fechaNacString,String fechaIngString) {
		super(dni, nombre, fechaNacString);
		this.setFechaIngreso(formatofech(fechaIngString));
		this.setEmail(email);
		
	}
	/**Constructor expecificamente creado para su utilizaci�n en la funcionalidad 'Evaluar asignatura'.Pues en ella necesitamos guardar 
	 * de forma separada las notas te�ricas y pr�cticas de una asignatura en concreto,para despu�s sumarlas 
	 * y conseguir la nota total de la asignatura
	 * 
	 * @param notaA Nota obtenida por el alumno en la parte Te�rica de una asignatura en espec�fico
	 * @param notaB Nota obtenida por el alumno en la parte Pr�ctica de una asignatura en espec�fico
	 */
	public Alumno(double notaA,double notaB) {
		this.setNotaA(notaA);
		this.setNotaB(notaB);
	}
	
	
/**Funci�n encargada de convertir una cadena en una fecha.
 * 
 * @param cadena fecha en formato dd/mm/aaaa.
 * @return devuelve la instancia de GregorianCalendar creada a partir de la fecha introducida en cadena.
 */

	public static GregorianCalendar formatofech(String cadena) {
		
		char  sep = '/';//Separador en el fichero
		String exp= "["+sep+"]";
	 String fechasep[] = cadena.trim().split(exp);
		
		  int dia=Integer.parseInt(fechasep[0]);		  
		  int mes=Integer.parseInt(fechasep[1]);
		  int anho=Integer.parseInt(fechasep[2]);
			
		GregorianCalendar separada= new GregorianCalendar(anho,mes-1,dia);
		 
		  return separada;
	}
	/**M�todo para obtener la fecha de ingreso de un alumno en la escuela.
	 * 
	 * @return La fecha instanciada.
	 */

	public GregorianCalendar getFechaIngreso() {
		return fechaIngreso;
	}
	/**M�todo encargado de guardar la fecha de ingreso del alumno
	 * 
	 * @param fechaIngreso Fecha en la que el alumno ha entrado en la escuela.En formato GregorianCalendar.
	 */

	public void setFechaIngreso(GregorianCalendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
/**M�todo para obtener las asignaturas y grupos Te�ricos(A) en los que est� matriculado el alumno
 * 
 * @return Mapa de todas las asignaturas(A) en las que est� matriculado el alumno hasta ahora.
 */
	public TreeMap <String,Grupo> getDocencrecA() {
		return docencrecA;
	}
	/**M�todo para guardar las asignaturas Te�ricas y el grupo en el que est� matriculado el alumno.
	 * El propio m�todo ser� en encargado de sacar los datos pertinentes de una cadena previamente pasada.
	 * 
	 * @param docenc Cadena que contiene la sigla de la asignatura, el tipo de grupo y el identificador del grupo en el que est� el alumno.
	 */

	public void setDocencrecA(String docenc) {
		String[]docencias=docenc.split("\\s+");
		if(!docencrecA.containsKey(docencias[0])) {
		docencrecA.put(docencias[0],new Grupo('A',docencias[2],0,'L',""));
		}else {
			docencrecA.get(docencias[0]).setIdGrupo(docencias[2]);
		}
		if(docencrec.containsKey(docencias[0])) {
			docencrec.remove(docencias[0]);
		}
	}
	/**M�todo para obtener las asignaturas y grupos Pr�cticos(B) en los que est� matriculado el alumno
	 * 
	 * @return Mapa de todas las asignaturas(B) en las que est� matriculado el alumno hasta ahora.
	 */
	public TreeMap <String,Grupo> getDocencrecB() {
		return docencrecB;
	}
	/**M�todo para guardar las asignaturas Pr�cticas y el grupo en el que est� matriculado el alumno.
	 * El propio m�todo ser� en encargado de sacar los datos pertinentes de una cadena previamente pasada.
	 * 
	 * @param docenc Cadena que contiene la sigla de la asignatura, el tipo de grupo y el identificador del grupo en el que est� el alumno.
	 */
	public void setDocencrecB(String docenc) {
		String[]docencias=docenc.split("\\s+");
		if(!docencrecB.containsKey(docencias[0])) {
			docencrecB.put(docencias[0],new Grupo('B',docencias[2],0,'L',""));
			}else {
				docencrecB.get(docencias[0]).setIdGrupo(docencias[2]);
			}
		if(docencrec.containsKey(docencias[0])) {
			docencrec.remove(docencias[0]);
		}
		
	}
/**M�todo para obtener las asignaturas evaluadas y superadas de un alumno.
 * 
 * @return Mapa con las aignaturas superadas,sus siglas ser�n las claves.
 */
	public TreeMap<String,Asignatura> getAsigsuperadas() {
		return asigsuperadas;
	}
/**M�todo encargado de a�adir una asignatura superada a las ya existentes.
 * 
 * @param superada Cadena que contiene las siglas de la asignatura,el curso academico en el quese super� y la nota obtenida.
 */
	public void setAsigsuperadas(String superada) {
		String[]superadas=superada.trim().split("\\s+");
		asigsuperadas.put(superadas[0].trim(),new Asignatura(superadas[0].trim(),superadas[1].trim(),Double.parseDouble(superadas[2].trim())));

	}



/**M�todo para obtener el correo electr�nico del alumno.
 * 
 * @return email del alumno.
 */
	public String getEmail() {
		return email;
	}



/**M�todo para guardar el correo del alumno.
 * 
 * @param email Correo electr�nico personal del alumno.
 */
	public void setEmail(String email) {
		this.email = email;
	}


/**M�todo para obtener las asignaturas en las que el alumno est� matriculado pero a�n no tiene grupo asignado,ni te�rico ni pr�ctico.
 * 
 * @return Mapa con las asignaturas.
 */

	public TreeMap <String,Grupo> getDocencrec() {
		return docencrec;
	}

/**M�todo para introducir asignaturas en las que a�n no tiene grupo asignado pero si est� matriculado.
 * 
 * @param sigla Identificador de la asignatura.
 */


	public void setDocencrec(String sigla) {
		docencrec.put(sigla, new Grupo());
	}

/**M�todo para obtener la ocupaci�n del alumno por dias
 * 
 * @return Mapa con la ocupaci�n de la semana,las claves ser�n los d�as como caracteres.
 */


	public TreeMap<Character,Grupo> getDias() {
		return dias;
	}

/**M�todo para guardar en un d�a especifico una ocupacion.
 * 
 * @param tipo Tipo de Grupo(A/B)
 * @param Id Identificador del grupo(String)
 * @param horaini Hora de inicio de ese grupo.
 * @param dia Dia en el que debe asistir al grupo de la asignatura.
 * @param aula aula en la que se imparte el grupo.
 */


	public void setDias(char tipo,String Id,int horaini,char dia,String aula) {
		dias.put(dia,new Grupo(tipo,Id,horaini,dia,aula));
	}

/**M�todo para obtener la nota de la parte te�rica de la asignatura.Solo la utilizamos en la funcionalidad 'Evaluar asignatura'.
 * 
 * @return nota de la parte Te�rica.
 */

	public double getNotaA() {
		return notaA;
	}


/**M�todo para guardar la nota de la parte Te�rica de la asignatura.
 * 
 * @param notaA Nota en formato doble.
 */

	public void setNotaA(double notaA) {
		this.notaA = notaA;
	}



	/**M�todo para obtener la nota de la parte pr�ctica de la asignatura.Solo la utilizamos en la funcionalidad 'Evaluar asignatura'.
	 * 
	 * @return nota de la parte Pr�ctica.
	 */
	public double getNotaB() {
		return notaB;
	}

	/**M�todo para guardar la nota de la parte Pr�ctica de la asignatura.
	 * 
	 * @param notaB Nota en formato doble.
	 */


	public void setNotaB(double notaB) {
		this.notaB = notaB;
	}




	


}

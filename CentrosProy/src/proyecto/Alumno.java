package proyecto;

import java.util.GregorianCalendar;
import java.util.TreeMap;

/**Los alumnos son un tipo de persona Académica,de la cual podemos obtener su identificardor,su nombre,su expediente
 * y las clases a las que asiste.Sobre este tipo de colectivo podemos realizar distintas modificaciones, como matricularlo en
 * más asignaturas y asignar una nota.
 * 
 * @author Lorena Gil Cerezales
 * @author Mónica Loureiro Zunzunegui
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
/**Coleccion de asignaturas en las que el alumno se ha matriculado pero de las que aún no tiene grupo.
 * 
 */
private TreeMap <String,Grupo>docencrec=new TreeMap<String,Grupo>();
/**Coleccion de asignaturas teóricas(A) en las que el alumno está matriculado.
 * 
 */
private TreeMap <String,Grupo>docencrecA=new TreeMap<String,Grupo>();
/**Coleccion de asignaturas prácticas(B) en las que el alumno está matriculado.
 * 
 */
private TreeMap <String,Grupo>docencrecB=new TreeMap<String,Grupo>();
/**Ocupación del alumnode lunes a viernes,la clave será el identificador deldía.
 * 
 */
private TreeMap<Character,Grupo> dias=new TreeMap<Character,Grupo>();

/**Nota de una asignatura especifica de grupo A.Solo la utilizaremos en la funcionalidad Evaluar Asignatura.
 */
private double notaA;
/**Nota de una asignatura especifica de grupo A.Solo la utilizaremos en la funcionalidad Evaluar Asignatura.
 */
private double notaB;
  /**Método constructor encargado de guardar de manera génerica los datos referentes al alumno,no todos tienen por qué existir.
   * 
   * @param dni Contiene el documento nacional de identidad de la persona a introducir.Formato(8 dígitos y una letra mayúscula)
   * @param nombre nombre y apellidos del alumno.
   * @param email dirección de correo electrónico donde el alumno recibirá las notificaciones de la escuela.
   * @param fechaNacString Fecha de nacimiento del alumno en formato dd/mm/aaaa.El alumno debe de ser mayor de 16 años.
   * @param fechaIngString Fecha de Ingreso en la escuela en formato dd/mm/aaaa.
   */
	public Alumno(String dni, String nombre,String email, String fechaNacString,String fechaIngString) {
		super(dni, nombre, fechaNacString);
		this.setFechaIngreso(formatofech(fechaIngString));
		this.setEmail(email);
		
	}
	/**Constructor expecificamente creado para su utilización en la funcionalidad 'Evaluar asignatura'.Pues en ella necesitamos guardar 
	 * de forma separada las notas teóricas y prácticas de una asignatura en concreto,para después sumarlas 
	 * y conseguir la nota total de la asignatura
	 * 
	 * @param notaA Nota obtenida por el alumno en la parte Teórica de una asignatura en específico
	 * @param notaB Nota obtenida por el alumno en la parte Práctica de una asignatura en específico
	 */
	public Alumno(double notaA,double notaB) {
		this.setNotaA(notaA);
		this.setNotaB(notaB);
	}
	
	
/**Función encargada de convertir una cadena en una fecha.
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
	/**Método para obtener la fecha de ingreso de un alumno en la escuela.
	 * 
	 * @return La fecha instanciada.
	 */

	public GregorianCalendar getFechaIngreso() {
		return fechaIngreso;
	}
	/**Método encargado de guardar la fecha de ingreso del alumno
	 * 
	 * @param fechaIngreso Fecha en la que el alumno ha entrado en la escuela.En formato GregorianCalendar.
	 */

	public void setFechaIngreso(GregorianCalendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
/**Método para obtener las asignaturas y grupos Teóricos(A) en los que está matriculado el alumno
 * 
 * @return Mapa de todas las asignaturas(A) en las que está matriculado el alumno hasta ahora.
 */
	public TreeMap <String,Grupo> getDocencrecA() {
		return docencrecA;
	}
	/**Método para guardar las asignaturas Teóricas y el grupo en el que está matriculado el alumno.
	 * El propio método será en encargado de sacar los datos pertinentes de una cadena previamente pasada.
	 * 
	 * @param docenc Cadena que contiene la sigla de la asignatura, el tipo de grupo y el identificador del grupo en el que está el alumno.
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
	/**Método para obtener las asignaturas y grupos Prácticos(B) en los que está matriculado el alumno
	 * 
	 * @return Mapa de todas las asignaturas(B) en las que está matriculado el alumno hasta ahora.
	 */
	public TreeMap <String,Grupo> getDocencrecB() {
		return docencrecB;
	}
	/**Método para guardar las asignaturas Prácticas y el grupo en el que está matriculado el alumno.
	 * El propio método será en encargado de sacar los datos pertinentes de una cadena previamente pasada.
	 * 
	 * @param docenc Cadena que contiene la sigla de la asignatura, el tipo de grupo y el identificador del grupo en el que está el alumno.
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
/**Método para obtener las asignaturas evaluadas y superadas de un alumno.
 * 
 * @return Mapa con las aignaturas superadas,sus siglas serán las claves.
 */
	public TreeMap<String,Asignatura> getAsigsuperadas() {
		return asigsuperadas;
	}
/**Método encargado de añadir una asignatura superada a las ya existentes.
 * 
 * @param superada Cadena que contiene las siglas de la asignatura,el curso academico en el quese superó y la nota obtenida.
 */
	public void setAsigsuperadas(String superada) {
		String[]superadas=superada.trim().split("\\s+");
		asigsuperadas.put(superadas[0].trim(),new Asignatura(superadas[0].trim(),superadas[1].trim(),Double.parseDouble(superadas[2].trim())));

	}



/**Método para obtener el correo electrónico del alumno.
 * 
 * @return email del alumno.
 */
	public String getEmail() {
		return email;
	}



/**Método para guardar el correo del alumno.
 * 
 * @param email Correo electrónico personal del alumno.
 */
	public void setEmail(String email) {
		this.email = email;
	}


/**Método para obtener las asignaturas en las que el alumno está matriculado pero aún no tiene grupo asignado,ni teórico ni práctico.
 * 
 * @return Mapa con las asignaturas.
 */

	public TreeMap <String,Grupo> getDocencrec() {
		return docencrec;
	}

/**Método para introducir asignaturas en las que aún no tiene grupo asignado pero si está matriculado.
 * 
 * @param sigla Identificador de la asignatura.
 */


	public void setDocencrec(String sigla) {
		docencrec.put(sigla, new Grupo());
	}

/**Método para obtener la ocupación del alumno por dias
 * 
 * @return Mapa con la ocupación de la semana,las claves serán los días como caracteres.
 */


	public TreeMap<Character,Grupo> getDias() {
		return dias;
	}

/**Método para guardar en un día especifico una ocupacion.
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

/**Método para obtener la nota de la parte teórica de la asignatura.Solo la utilizamos en la funcionalidad 'Evaluar asignatura'.
 * 
 * @return nota de la parte Teórica.
 */

	public double getNotaA() {
		return notaA;
	}


/**Método para guardar la nota de la parte Teórica de la asignatura.
 * 
 * @param notaA Nota en formato doble.
 */

	public void setNotaA(double notaA) {
		this.notaA = notaA;
	}



	/**Método para obtener la nota de la parte práctica de la asignatura.Solo la utilizamos en la funcionalidad 'Evaluar asignatura'.
	 * 
	 * @return nota de la parte Práctica.
	 */
	public double getNotaB() {
		return notaB;
	}

	/**Método para guardar la nota de la parte Práctica de la asignatura.
	 * 
	 * @param notaB Nota en formato doble.
	 */


	public void setNotaB(double notaB) {
		this.notaB = notaB;
	}




	


}

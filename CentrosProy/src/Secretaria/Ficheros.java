package Secretaria;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;

import proyecto.Alumno;
import proyecto.Asignatura;
import proyecto.Aula;
import proyecto.Avisos;
import proyecto.Censo;
import proyecto.Grupo;
import proyecto.Pod;
import proyecto.Profesor;

import java.util.Iterator;


/**La clase 'Ficheros' será la encargada de realizar toda la lectura y el procesado de los datos de una escuela especifica,
 * incluyendo profesores,alumnos,aulas,asignaturas y realizar modificaciones sobre los datos a partir de un fichero de ejecucion.
 * 
 * 
 * @author Lorena Gil Cerezales
 * @author Monica Loureiro Zunzunegui
 *
 */


public class Ficheros {
	
	static String[] datos= new String[13];
	public static void main (String[] args)throws IOException{
		
	Censo censo = new Censo ();
	leerAula(censo);
	leerAsignaturas(censo);
	leerProfesores(censo);
	leerAlumnos(censo);
	leerPod(censo);
	leerCursoAcademico(censo);
	Ejecucion(censo);
	guardarProfesores(censo);
	guardarAsignaturas(censo);	
	guardarAlumnos(censo);
	OrdenaProfXPod(censo);
	
		}
	/**Método encargado de llevar a cabo la tarea de lectura y procesado de datos existentes en un fichero('profesores.txt'),el cual contiene informacion relativa a 
	 * la ocupacion docente de los profesores en la escuela y datos personales.
	 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
	 * @throws IOException Error si no existe el fichero.
	 */
	
	
public static void leerProfesores (Censo censo) throws IOException{
			
			TreeMap<String, Profesor> profesores = censo.getProfesores();
			TreeMap <String, Asignatura> asignaturas = censo.getAsignaturas();
			TreeMap<String, Aula> aulas=censo.getAula();
			try {	
				
				FileReader fr= new FileReader("profesores.txt");
				BufferedReader br = new BufferedReader(fr);
				String linea="";//Vamos a ir guardando lo leido provisionalmente
				
			do {

				linea="";
			for(int i=0; i<=6&&linea!=null ;i++) {
				linea="";
				linea=br.readLine();
				
			    datos[i]=linea;	
			}

			profesores.put(datos[0].trim(),new Profesor(datos[0].trim(),datos[1].trim(),datos[2].trim(),datos[3].trim(),datos[4].trim()));
			
			if(!datos[5].equals("\n")){
				Profesor profesor = (Profesor)profesores.get(datos[0]);
				
				
				String datosAsignaturas[] = datos[5].split(";");

				for (int i=0; i<datosAsignaturas.length; i++){
	
		
					String datgrupo[] = datosAsignaturas[i].trim().split("\\s+");
					String sigla=datgrupo[0].trim();
					Asignatura asignatura=asignaturas.get(sigla);
					if(datgrupo[1].trim().charAt(0) == 'A') {
						
						TreeMap <String, Grupo> gruposA=profesor.getDocenciaA();
						if(gruposA.containsKey(datgrupo[0].trim())) {
							Grupo asigespecifica=gruposA.get(datgrupo[0].trim());
							asigespecifica.setIdGrupo(datgrupo[2].trim());
							
						
						}else {					
						profesor.setDocenciaA(datosAsignaturas[i].trim());
						
						
					}
						
						TreeMap<Integer,Grupo> GruposA=asignatura.getListaGruposA();
						Grupo grupoespecifico=GruposA.get(Integer.parseInt(datgrupo[2].trim()));
						int duracionA=asignatura.getDuracionGrupoA();
						
						Integer horasocupadas[]=new Integer[duracionA];
						horasocupadas[0]=grupoespecifico.getHoraini();
						for(int j=1;j<duracionA;j++) {
							horasocupadas[j]=grupoespecifico.getHoraini()+1;						
						}
						//Buscamos el dia en el que se imparte ese grupo
						char dia=grupoespecifico.getDia();
						profesor.setDias('A', datgrupo[2].trim(),horasocupadas[0] ,dia, grupoespecifico.getAula());
						
						TreeMap<Character,Grupo> dias=profesor.getDias();
						Grupo grupodia=dias.get(dia);
						for(int j=0;j<horasocupadas.length;j++) {
						grupodia.setHorasGrupos('A', datgrupo[2].trim(),horasocupadas[j] ,dia, grupoespecifico.getAula(),sigla);
						
						}
						
						//Asignamos el profesor en el aula en la que se imparte
						Aula aula=aulas.get(grupoespecifico.getAula());
						TreeMap<Character,Grupo> D=aula.getDias();
						Grupo diaespec=D.get(dia);
						TreeMap<Integer,Grupo> horasespecif=diaespec.getHorasgrupos();
						for(int j=0;j<horasocupadas.length;j++) {
							Grupo Hespecif=horasespecif.get(horasocupadas[j]);
							Hespecif.setDNIDocente(datos[0].trim());
							
							}
						
						
						
						
		
					}else {
						
							TreeMap <String, Grupo> gruposB=profesor.getDocenciaB();
							if(gruposB.containsKey(datgrupo[0].trim())) {
								Grupo asigespecifica=gruposB.get(datgrupo[0].trim());
								asigespecifica.setIdGrupo(datgrupo[2].trim());
								
								//System.out.println(asigespecifica.getNumGrupos()+" No existia "+datosAsignaturas[i]);	
							}else {
								profesor.setDocenciaB(datosAsignaturas[i].trim());	
							}
							TreeMap<Integer,Grupo> GruposB=asignatura.getListaGruposB();
							Grupo grupoespecifico=GruposB.get(Integer.parseInt(datgrupo[2].trim()));
							int duracionB=asignatura.getDuracionGrupoB();
							//System.out.println(grupoespecifico.getHoraini());
							Integer horasocupadas[]=new Integer[duracionB];
							horasocupadas[0]=grupoespecifico.getHoraini();
							for(int j=1;j<duracionB;j++) {
								horasocupadas[j]=grupoespecifico.getHoraini()+1;						
							}
							//Buscamos el dia en el que se imparte ese grupo
							char dia=grupoespecifico.getDia();
							profesor.setDias('B', datgrupo[2].trim(),horasocupadas[0] ,dia, grupoespecifico.getAula());
							
							TreeMap<Character,Grupo> dias=profesor.getDias();
							Grupo grupodia=dias.get(dia);
							for(int j=0;j<horasocupadas.length;j++) {
							grupodia.setHorasGrupos('B', datgrupo[2].trim(),horasocupadas[j] ,dia, grupoespecifico.getAula(),sigla);
							
						
							}
							
							
							//Asignamos el profesor en el aula en la que se imparte
							Aula aula=aulas.get(grupoespecifico.getAula());
							TreeMap<Character,Grupo> D=aula.getDias();
							Grupo diaespec=D.get(dia);
							TreeMap<Integer,Grupo> horasespecif=diaespec.getHorasgrupos();
							for(int j=0;j<horasocupadas.length;j++) {
								Grupo Hespecif=horasespecif.get(horasocupadas[j]);
								Hespecif.setDNIDocente(datos[0].trim());
								
								}
					}
					
					
                    
					String IDAsignatura = datgrupo[0];
													
						asignatura = asignaturas.get(IDAsignatura);
						String tipoGrupo = datgrupo[1].trim();
						
						if (tipoGrupo.charAt(0) == 'A'){
							
							TreeMap <Integer, Grupo> listaGruposA = asignatura.getListaGruposA();
							
							if(listaGruposA.containsKey(Integer.parseInt(datgrupo[2].trim()))){
								
								Grupo grupo = listaGruposA.get(Integer.parseInt(datgrupo[2].trim()));
								grupo.setDNIDocente(datos[0].trim());
								

								

							} else {
								Grupo grupo= new Grupo(tipoGrupo.charAt(0),datgrupo[2].trim(),0,'J',"");		
								listaGruposA.put(new Integer(datgrupo[2].trim()),grupo);
								grupo.setDNIDocente(datos[0].trim());
								grupo.setAsignatura(IDAsignatura);
								
								/*TreeMap <Integer, Grupo> listaGruposAsignados = profesor.getListaGruposAsignados();
								listaGruposAsignados.put(new Integer (listaGruposAsignados.size() +1), grupo); */
								
								
							}
						}
						
						if (tipoGrupo.charAt(0)=='B'){
							
                        TreeMap <Integer, Grupo> listaGruposB = asignatura.getListaGruposB();
							
							if(listaGruposB.containsKey(Integer.parseInt(datgrupo[2].trim()))){
							
								Grupo grupo = listaGruposB.get(Integer.parseInt(datgrupo[2].trim()));
								grupo.setDNIDocente(datos[0].trim());
								
								
								//TreeMap <String, Grupo> listaGruposAsignados = profesor.getDocencia();
								//profesor.listaDocencia.put(new Integer(asignatura.getID()), asignatura);
								//listaGruposAsignados.put(new Integer (listaGruposAsignados.size() +1), grupo); 
								

							} else {
								Grupo grupo= new Grupo(tipoGrupo.charAt(0),datgrupo[2].trim(),0,'J',"");		
								listaGruposB.put(new Integer(datgrupo[2].trim()),grupo);
								grupo.setDNIDocente(datos[0].trim());
								grupo.setAsignatura(IDAsignatura);
								//profesor.listaDocencia.put(new Integer(asignatura.getID()), asignatura);
								/*TreeMap <Integer, Grupo> listaGruposAsignados = profesor.getListaGruposAsignados();
								listaGruposAsignados.put(new Integer (listaGruposAsignados.size() +1), grupo); */
								
								
							}
							
						}
						
				
			}

			}
			
	
			}while(linea!=null);
			
			    fr.close();
					 
			} catch(FileNotFoundException e) {
				   System.out.println("Fichero inexistente:'profesores.txt'");
				   System.exit(1);
				}
		
		
		
}
/**Método encargado de llevar a cabo la tarea de lectura y procesado de datos existentes en un fichero('alumnos.txt'),el cual contiene informacion relativa a 
 * la ocupacion académica del alumno de la escuela y sus datos personales.
 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios
 * @throws IOException Error si no existe el fichero.
 */
		
		
public static void leerAlumnos (Censo censo) throws IOException{
			
			TreeMap<String, Alumno> alumnos = censo.getAlumnos();
			TreeMap<String,Asignatura> asignaturas=censo.getAsignaturas();
			TreeMap<String,Aula> aulas=censo.getAula();
			try {	
				
				FileReader fr= new FileReader("alumnos.txt");
				BufferedReader br = new BufferedReader(fr);
				String linea="";//Vamos a ir guardando lo leido provisionalmente
				
			while(linea!=null) {
				
				
			for(int i=0; i<=7&&linea!=null ;i++) {
				
				linea=br.readLine();
				
			    datos[i]=linea;	
			}
			
			
			alumnos.put(datos[0].trim(),new Alumno(datos[0].trim(),datos[1].trim(),datos[2].trim(),datos[3].trim(),datos[4].trim()));
			
			Alumno alumno = (Alumno)alumnos.get(datos[0].trim());
			//Asignaturas superadas
			String asigsuperadas[]=datos[5].split(";");
			for (int i=0; i<asigsuperadas.length; i++){
				alumno.setAsigsuperadas(asigsuperadas[i].trim());
		
			}
			
			//Asignaturas matriculado
			String datosAsignaturas[] = datos[6].trim().split(";");
			
			
			for (int i=0; i<datosAsignaturas.length; i++){
		
			
				String datgrupo[] = datosAsignaturas[i].trim().split("\\s+");
			    String sigla=datgrupo[0].trim();
			    Asignatura asignatura=asignaturas.get(sigla);
			   
			    //Alumno matriculado en una asignatura,pero sin asignar un grupo
			    if(datgrupo.length==1) {
			    	alumno.setDocencrec(datgrupo[0].trim());
			    	
			    	
			    }else {
			    
				
				if(datgrupo[1].trim().charAt(0) == 'A') {
					//Guardamos las horas que tiene ocupadas el alumno
				    TreeMap<Integer,Grupo> gruposA=asignatura.getListaGruposA();
					Grupo grupoespecifico=gruposA.get(Integer.parseInt(datgrupo[2].trim()));
					int duracionA=asignatura.getDuracionGrupoA();
					Integer horasocupadas[]=new Integer[duracionA];
					horasocupadas[0]=grupoespecifico.getHoraini();
					for(int j=1;j<duracionA;j++) {
						horasocupadas[j]=grupoespecifico.getHoraini()+1;						
					}
					//Buscamos el dia en el que se imparte ese grupo
					char dia=grupoespecifico.getDia();
					alumno.setDias('A', datgrupo[2].trim(),horasocupadas[0] ,dia, grupoespecifico.getAula());
					
					TreeMap<Character,Grupo> dias=alumno.getDias();
					Grupo grupodia=dias.get(dia);
					for(int j=0;j<horasocupadas.length;j++) {
					grupodia.setHorasGrupos('A', datgrupo[2].trim(),horasocupadas[j] ,dia, grupoespecifico.getAula(),sigla);
					
					}
					
								
					alumno.setDocencrecA(datosAsignaturas[i].trim());
					//Buscamos el aula donde se imparte la asignatura					
					String numAula=grupoespecifico.getAula();
					//Aumentamos la ocupacion del Aula
					Aula aula=aulas.get(numAula);
					TreeMap<String,Grupo> gruposimpA=aula.getGruposA();
					Grupo grupo=gruposimpA.get(datosAsignaturas[i].trim());
					grupo.setOcupacion();
					
					//System.out.println(grupo.getOcupacion());

				}else {
					
					
	                TreeMap<Integer,Grupo> gruposB=asignatura.getListaGruposB();
					Grupo grupoespecifico=gruposB.get(Integer.parseInt(datgrupo[2].trim()));
					int duracionB=asignatura.getDuracionGrupoB();
					Integer horasocupadas[]=new Integer[duracionB];
					horasocupadas[0]=grupoespecifico.getHoraini();
					for(int j=1;j<duracionB;j++) {
						horasocupadas[j]=grupoespecifico.getHoraini()+1;						
					}
					//Buscamos el dia en el que se imparte ese grupo
					char dia=grupoespecifico.getDia();
					alumno.setDias('B', datgrupo[2].trim(),horasocupadas[0] ,dia, grupoespecifico.getAula());
					
					TreeMap<Character,Grupo> dias=alumno.getDias();
					Grupo grupodia=dias.get(dia);
					for(int j=0;j<horasocupadas.length;j++) {
					grupodia.setHorasGrupos('B', datgrupo[2].trim(),horasocupadas[j] ,dia, grupoespecifico.getAula(),sigla);
					
					}
					
					alumno.setDocencrecB(datosAsignaturas[i].trim());
					
					//Buscamos el aula donde se imparte la asignatura
					
					String numAula=grupoespecifico.getAula();
					
					//Aumentamos la ocupacion del Aula
					Aula aula=aulas.get(numAula);
					TreeMap<String,Grupo> gruposimpB=aula.getGruposB();
					Grupo grupo=gruposimpB.get(datosAsignaturas[i].trim());
					grupo.setOcupacion();
					
					
					}
			    }
		
				}
			
			
			
			}
			
			    fr.close();
					 
			} catch(FileNotFoundException e) {
				   System.out.println("Fichero inexistente:'alumnos.txt'");
				   System.exit(1);
				}
		
}
/**Método encargado de la lectura y procesamiento de los datos contenidos en el fichero ('asignaturas.txt'),el cual
 * contiene los datos relativos a las asignaturas impartidas en la escuela,para su posterior tratamiento.
 * 
 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
 * @throws IOException Error si no existe el fichero.
 */
		
	public static void leerAsignaturas (Censo censo) throws IOException{
			
			TreeMap<String, Asignatura> asignaturas = censo.getAsignaturas();
		 	TreeMap<String,Aula> aulas=censo.getAula();
			
		try {	
				
				FileReader fr= new FileReader("asignaturas.txt");
				BufferedReader br = new BufferedReader(fr);
				String linea="";//Vamos a ir guardando lo leido provisionalmente
				
			do {
				
				
				linea="";
			for(int i=0; i<=10 ;i++) {
				
				linea=br.readLine();
				//System.out.println(linea);
			    datos[i]=linea;	
			    
			}
			
				//Hay campos que pueden estar vacios
				if(datos[4].trim().equals("/n")){//Coordinador
					datos[4] = "0";
				}
	
		
					asignaturas.put(new String(datos[0].trim()), new Asignatura(datos[0].trim(), datos[1].trim(), Integer.parseInt(datos[2].trim()), Integer.parseInt(datos[3].trim()), datos[4].trim(),datos[5].trim()));
		
				
				Asignatura asignatura=	asignaturas.get(datos[0].trim());
				asignatura.setDuracionGrupoA(Integer.parseInt(datos[6].trim()));
				asignatura.setDuracionGrupoB(Integer.parseInt(datos[7].trim()));
				TreeMap<Character,Grupo> dias=null;
				Grupo dia=null;
				
				//DATOS DE LOS GRUPOS TIPO A
				if(!datos[8].trim().equals("/n")){
				
				String GruposA[]=datos[8].trim().split(";");
				TreeMap<Integer,Grupo> GruposTipoA=asignatura.getListaGruposA();
				
				//Separamos los datos leidos en diferentes grupos	
				for(int i=0;i<GruposA.length;i++) {
					String DatosGrupoA[]=GruposA[i].trim().split("\\s+");

					GruposTipoA.put(Integer.parseInt(DatosGrupoA[0]), new Grupo('A',DatosGrupoA[0],Integer.parseInt(DatosGrupoA[2]),DatosGrupoA[1].trim().charAt(0),DatosGrupoA[3].trim()));
						
					
					//Guardamos en la ocupacion del aula
					Aula aula=aulas.get(DatosGrupoA[3].trim());
					aula.setGruposA(datos[0].trim()+" A "+DatosGrupoA[0].trim(),DatosGrupoA[0].trim(),DatosGrupoA[1].trim(),DatosGrupoA[3].trim(),Integer.parseInt(DatosGrupoA[2].trim()));
					dias=aula.getDias();
					if(!dias.containsKey(DatosGrupoA[1].trim().charAt(0))) {
						aula.setDias(DatosGrupoA[0].trim(),DatosGrupoA[1].trim().charAt(0),Integer.parseInt(DatosGrupoA[2].trim()),DatosGrupoA[3].trim(),'A');
					}
					
					dia=dias.get(DatosGrupoA[1].trim().charAt(0));
					int duracion=Integer.parseInt(datos[6].trim());
					Integer[]horasnecesarias=new Integer[duracion];				
					horasnecesarias[0]=Integer.parseInt(DatosGrupoA[2].trim());
					for(int j=1;j<duracion;j++) {
						horasnecesarias[j]=Integer.parseInt(DatosGrupoA[2].trim())+j;					
					}
					for(int j=0;j<horasnecesarias.length;j++) {
						dia.setHorasGrupos('A', DatosGrupoA[0].trim(), horasnecesarias[j],DatosGrupoA[1].trim().charAt(0),DatosGrupoA[3].trim(),datos[0].trim());
				
					}	
					
				}//Final del for para Grupos de tipo A
				
		
				}
				
				
				//DATOS DE LOS GRUPOS TIPO B
                if(!datos[9].trim().equals("/n")){
                	
    				String GruposB[]=datos[9].trim().split(";");
    				TreeMap<Integer,Grupo> GruposTipoB=asignatura.getListaGruposB();
    				
    				//Separamos los datos leidos en diferentes grupos	
    				for(int i=0;i<GruposB.length;i++) {
    					
    					String DatosGrupoB[]=GruposB[i].trim().split("\\s+");
    					
    					GruposTipoB.put(Integer.parseInt(DatosGrupoB[0]), new Grupo('B',DatosGrupoB[0],Integer.parseInt(DatosGrupoB[2]),DatosGrupoB[1].trim().charAt(0),DatosGrupoB[3].trim()));
    						
    					
    					Aula aula=aulas.get(DatosGrupoB[3].trim());
    					//System.out.println(DatosGrupoB[3].trim());
    					dias=aula.getDias();
    					aula.setGruposB(datos[0].trim()+" B "+DatosGrupoB[0].trim(),DatosGrupoB[0].trim(),DatosGrupoB[1].trim(),DatosGrupoB[3].trim(),Integer.parseInt(DatosGrupoB[2].trim()));
    					if(!dias.containsKey(DatosGrupoB[1].trim().charAt(0))) {
    					aula.setDias(DatosGrupoB[0].trim(),DatosGrupoB[1].trim().charAt(0),Integer.parseInt(DatosGrupoB[2].trim()),DatosGrupoB[3].trim(),'B');
    					}
    					dia=dias.get(DatosGrupoB[1].trim().charAt(0));
    					int duracion=Integer.parseInt(datos[7].trim());
    					Integer[]horasnecesarias=new Integer[duracion];				
    					horasnecesarias[0]=Integer.parseInt(DatosGrupoB[2].trim());
    					for(int j=1;j<duracion;j++) {
    						horasnecesarias[j]=Integer.parseInt(DatosGrupoB[2].trim())+j;					
    					}
    					for(int j=0;j<horasnecesarias.length;j++) {
    						dia.setHorasGrupos('B', DatosGrupoB[0].trim(), horasnecesarias[j],DatosGrupoB[1].trim().charAt(0),DatosGrupoB[3].trim(),datos[0].trim());
    				
    					}	
    					
    				}	
    				
					
				}//Final de lectura de los datos de los grupos B
				
			
			}while(linea!=null);
			
			    fr.close();
					 
			} catch(FileNotFoundException e) {
				   System.out.println("Fichero inexistente:'asignaturas.txt'");
				   System.exit(1);
				}
			
		}
	/**Método encargado de la lectura y el procesamiento de los datos relativos a las Aulas disponibles en la escuela para
	 * la imparticion de las clases,tanto de grupos A(Teoricos) como de grupos B(Prácticos).La información la obtenemos del fichero suministrado
	 * 'aulas.txt'.
	 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
	 * @throws IOException Error si no existe el fichero.
	 */

		public static void leerAula( Censo censo) throws IOException{
			TreeMap<String, Aula> aula=censo.getAula();
			try {	
				
				FileReader fr= new FileReader("aulas.txt");
				BufferedReader br = new BufferedReader(fr);
				String linea="";//Vamos a ir guardando lo leido provisionalmente
				
			do {
				
				
				linea="";
			for(int i=0; i<=3&&linea!=null ;i++) {
				
				linea=br.readLine();
				//System.out.println(linea);
			    datos[i]=linea;	
			}
			
				aula.put(datos[0].trim(),new Aula(datos[0].trim(),datos[1].trim(), Integer.parseInt(datos[2].trim())));
			
			}while(linea!=null);
				
				fr.close();
				 
			} catch(FileNotFoundException e) {
				   System.out.println("Fichero inexistente:'aulas.txt'");
				   System.exit(1);
				}
		}
		
		/**Método encargado de la lectura y procesamiento del fichero 'pod.txt'.En el se encuentran los datos relativos a 
		 * la organizacion docente de la escuela.Gracias a el podemos saber los grupos que puede impartir un profesor.
		 * 
		 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
		 * @throws IOException Error si no existe el fichero.
		 */
	
	public static void leerPod(Censo censo) throws IOException{
		TreeMap<String, Pod> pod=censo.getPod();
		try {	
			
			FileReader fr= new FileReader("pod.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea="";//Vamos a ir guardando lo leido provisionalmente
			
		do {
			
			
			linea="";
		for(int i=0; i<=2 ;i++) {
			
			linea=br.readLine();
			
		    datos[i]=linea;	
		}
		
		
			
			pod.put(datos[0].trim(),new Pod(datos[0].trim()));
						
			Pod profesor = pod.get(datos[0]);
				
				
			String datosAsignaturas[] = datos[1].split(";");
			//System.out.println(datosAsignaturas.length);
			for (int i=0; i<datosAsignaturas.length; i++){
				String[]datosaux=datosAsignaturas[i].trim().split("\\s+");
				
				 if(datosaux[1].trim().equals("A")) {
					 profesor.setListaGruposA(datosaux[0].trim(),datosaux[1].trim(),datosaux[2].trim());
	 
				 }
				 if(datosaux[1].trim().equals("B")) {
					 profesor.setListaGruposB(datosaux[0].trim(),datosaux[1].trim(),datosaux[2].trim());
	 
				 }
					
				}//Final del for
			
	
			}while(linea!=null);
		fr.close();
			 
		} catch(FileNotFoundException e) {
			   System.out.println("Fichero inexistente:'pod.txt'");
			   System.exit(1);
			}
		
	}
	/**Método encargado de la lectura y procesamiento del fichero 'cursoAcademico.txt' en el que se encuentra la informacion relativa
	 * al curso académico sobre el que queremos obtener cierta información y la semana de inicio de dicho curso.
	 * 
	 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
	 * @throws IOException Error si no existe el fichero.
	 */
	
	
	public static void leerCursoAcademico (Censo censo) throws IOException{
		
		
		String dato[]=new String[2];
		try {	
			
			FileReader fr= new FileReader("cursoAcademico.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea="";//Vamos a ir guardando lo leido provisionalmente
			
	
			
			
		for(int i=0; i<=1 ;i++) {
			linea=br.readLine();
		    dato[i]=linea;
		    
		    
		}

		
		
		
		    fr.close();
				 
		} catch(FileNotFoundException e) {
			   System.out.println("Fichero inexistente:'cursoAcademico.txt'");
			   System.exit(1);
			}
	censo.setCurso(dato[0].trim());
	censo.setSemanainicio(dato[1].trim());
		
	}
	
	/**Método encargado de realizar la funcionalida 'Insertar Persona' la cual nos permite introducir una persona académica(Alumno/Profesor)
	 * junto con sus datos específicos en la base de datos de la escuela.
	 * 
	 * 
	 * @param perfil Especifica si queremos introducir un alumno o un profesor.Cualquier otro será invalido.
	 * @param dni Contiene el documento nacional de identidad de la persona a introducir.Formato(8 dígitos y una letra mayúscula)
	 * @param nombre Apellidos y nombre de la persona académica.Los apellidos deberán ir separados del nombre por una coma.
	 * @param fechaNac Fecha de nacimiento en formato dd/mm/aaaa.
	 * @param fechaIng Fecha de Ingreso en la escuela en formato dd/mm/aaaa.Solo si la persona académica pertenece al colectivo de los alumnos.
	 * @param categoria Titular/Asociado.Solo si la persona académica pertenece al colectivo de los profesores.
	 * @param departamento Nombre del departamente en el que se encuentra adscrito el profesor.
	 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
	 */
			
		
		public static void InsertaPersona(String perfil,String dni,String nombre,String fechaNac,String fechaIng,String categoria,String departamento,Censo censo) {
			
			boolean avisoGenerado=false;
			
			TreeMap<String, Profesor> profesor=censo.getProfesores();	
			TreeMap<String, Alumno> alumno=censo.getAlumnos();						
			GregorianCalendar FechaNacimiento=new GregorianCalendar();
			TreeMap<String,Pod> pod=censo.getPod();
			
			
			
			 if (dni.length()!=9) {
				 avisoGenerado=true;
				 new Avisos("Insertar Persona",0);
			 }else {
				 
			 for (int i = 0; i < dni.length()-1; i++){
			     char digito = dni.charAt(i);
			     if (!Character.isDigit(digito)) {
			    	 avisoGenerado=true;
			    	 new Avisos("Insertar Persona",0);
			    	 
			     }
			  
			 }
			 //Tratamiento del caracter
			 if(!avisoGenerado) {
			 char letra=dni.charAt(8);
			 if(!Character.isLetter(letra)) {
				 avisoGenerado=true;
				 new Avisos("Insertar Persona",0);
			 }
			 }
			 }//final del else
			 
			//Comprobamos fechaNac
			
				 if(!avisoGenerado){
					 
					 FechaNacimiento=Alumno.formatofech(fechaNac);
					
					char  sep = '/';//Separador en el fichero
					String exp= "["+sep+"]";
					String fechasep[] = fechaNac.split(exp);
					int dia=Integer.parseInt(fechasep[0]);
					int mes=Integer.parseInt(fechasep[1]);
					int anho=Integer.parseInt(fechasep[2]);
						if(comprob(dia,mes,anho)) {
							new Avisos("Insertar Persona",1);
							avisoGenerado = true;	
						}
						if(!avisoGenerado) {
					 if(FechaNacimiento.before(new GregorianCalendar(1960,01,01)) || (FechaNacimiento.after(new GregorianCalendar(2018, 01,01)))){
							
							new Avisos("Insertar Persona",1);
							avisoGenerado = true;
							
						 } 
						}
				 }
				 
			
			if(!avisoGenerado) {
	
			if(profesor.containsKey(dni)) {//
				new Avisos("Insertar Persona",3);
				
				avisoGenerado=true;				
			}
			if(alumno.containsKey(dni)) {
				new Avisos("Insertar Persona",4);
				avisoGenerado=true;
				
			}
			}
			//TRATAMIENTO DEL NOMBRE,quitamos las comillas
			
			if(!avisoGenerado) {
		
			nombre=nombre.replaceAll("\"", "");

			
			if(perfil.equals("profesor")){
				
				profesor.put(dni, new Profesor(dni,nombre,fechaNac,categoria,departamento));
				
				pod.put(dni,new Pod(dni));
				
				Pod prof= pod.get(dni);
				
	
				return;
			}
			
			if(perfil.equals("alumno")) {
				 if(!avisoGenerado){
				
				GregorianCalendar FechaIngreso=Alumno.formatofech(fechaIng);
				 if(!avisoGenerado) {
						
						char  sep = '/';//Separador en el fichero
						String exp= "["+sep+"]";
						String fechasep[] = fechaIng.split(exp);
						int dia=Integer.parseInt(fechasep[0]);
						int mes=Integer.parseInt(fechasep[1]);
						int anho=Integer.parseInt(fechasep[2]);
							if(comprob(dia,mes,anho)) {
								new Avisos("Insertar Persona",1);
								avisoGenerado = true;	
							}
				
					 if(!avisoGenerado){
						 if(FechaIngreso.before(new GregorianCalendar(1960,01,01)) || (FechaIngreso.after(new GregorianCalendar(2018, 01,01)))){
								
								new Avisos("Insertar Persona",1);
								avisoGenerado = true;
							 } 
					 }
				 }
				 
				 
				
				//Comprobamos que el alumno tiene la edad adecuada para matricularse y el formato de fecha está bien
				
					if(!avisoGenerado) {
				int Difanhos= -1;
				if (FechaNacimiento.equals(FechaIngreso)) Difanhos = 0;
				else {
				    while (FechaNacimiento.before(FechaIngreso)) {
						FechaNacimiento.add(Calendar.YEAR, 1);
						Difanhos++;
					}
					//FechaNacimiento.add(Calendar.YEAR, -1);		
				}//Final del else
				if(Difanhos<16||Difanhos>60) {
					avisoGenerado=true;
					new Avisos("Insertar Persona",2);
				}
				 }
				 }
			}
				
			if(!avisoGenerado)	{
				alumno.put(dni, new Alumno(dni,nombre,"",fechaNac,fechaIng));
			}
			}
			}
	/**Método encargado de comprobar si una fecha es correcta
	 * 		
	 * @param dia Dia del mes.
	 * @param mes Mes del anho.
	 * @param anho Anho especifico.
	 * @return True/False si la fecha es correcta/incorrecta.
	 */
		
		
		public static boolean comprob(int dia, int mes, int anho) {
			Calendar fecha= new GregorianCalendar(anho,mes-1,dia);
			
			if (dia != fecha.get(Calendar.DAY_OF_MONTH) || (mes-1) != fecha.get(Calendar.MONTH)
					|| anho != fecha.get(Calendar.YEAR)) {				
				return true;//Forzamos la salida
			}
			return false;
		}
		/**Método encargado de llevar a cabo la funcionalidad 'Asignar un Grupo' la cual asignará un grupo (Teórico o Práctico)
		 * a una persona académica. Si se trata de un profesor se encargará de la impartición.En el caso de un alumno asistirá a la clase.
		 * 
		 * 
		 * @param perfil Especifica si queremos introducir un alumno o un profesor.Cualquier otro será invalido.
	     * @param dni Contiene el documento nacional de identidad de la persona a introducir.Formato(8 dígitos y una letra mayúscula).
		 * @param asignatura Contiene la asignatura en a cual vamos a asignar el grupo a la persona.
		 * @param tipogrupo Grupo Teórico o Práctico
		 * @param grupo Identificador del grupo que queremos asignarle,debe pertenecer a la asignatura especificada.
		 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
		 */
		
		
		
		public static void AsignarGrupo(String perfil,String dni,String asignatura,char tipogrupo,int grupo,Censo censo) {
			
			TreeMap<String,Alumno>alumnos=censo.getAlumnos();
			TreeMap<String,Aula> aulas=censo.getAula();
			TreeMap<String,Profesor>profesores=censo.getProfesores();
			TreeMap<String,Asignatura>asignatur=censo.getAsignaturas();
			TreeMap<String,Pod> pod=censo.getPod();
			Asignatura asignaturas=null;
			boolean error=false;
			
				if(!alumnos.containsKey(dni)&&!profesores.containsKey(dni)) {
					new Avisos("Asignar Grupo",0);
					error=true;
				}
			
			   if(!error) {
				if(!asignatur.containsKey(asignatura)) {
			    	new Avisos("Asignar Grupo",1);
			    	error=true;
			    }
			   }
			   if(!error) {

			    if(tipogrupo!='A'&&tipogrupo!='B') {
			    	new Avisos("Asignar Grupo",2);
			    	error=true;
			    }
			   }
			   
			if(!error) {
				asignaturas=asignatur.get(asignatura);
				//Sacamos los grupos de las asignaturas
				if(tipogrupo=='A') {				
				    TreeMap<Integer,Grupo> gruposA=asignaturas.getListaGruposA();
				    if(!gruposA.containsKey(grupo)) {
				    	new Avisos("Asignar Grupo",3);
				    	error=true;
				    }
				
				}
				
				if(tipogrupo=='B') {				
					TreeMap<Integer,Grupo> gruposB=asignaturas.getListaGruposB();
					if(!gruposB.containsKey(grupo)) {
					    new Avisos("Asignar Grupo",3);
					    error=true;
					}
					
				}
			}
				if(!error) {
				if(perfil.equals("profesor")){
					
						if(tipogrupo=='A')	{
							 TreeMap<Integer,Grupo> gruposA=asignaturas.getListaGruposA();
							 
							 Grupo grupoMap=gruposA.get(grupo);
							 if(!grupoMap.getDNIDocente().equals("")) {//Si ya hay un docente asignado
								 
								 new Avisos("Asignar Grupo",4);
								 error=true;			
					         }
						}
						else{
							TreeMap<Integer,Grupo> gruposB=asignaturas.getListaGruposB();
								 
							Grupo grupoMap=gruposB.get(grupo);
							
							if(!grupoMap.getDNIDocente().equals("")) {//Si ya hay un docente asignado
									 
								 new Avisos("Asignar Grupo",4);
								 error=true;			
						     }
						}
						//Sacamos los datos que vamos a necesitar
						    Pod podaux=pod.get(dni);
						    
							TreeMap <String,Grupo> listgrupA=podaux.getListaGruposA();
							TreeMap <String,Grupo> listgrupB=podaux.getListaGruposB();
							//Informacion del profesor
							Profesor profesor=null;
							TreeMap <String,Grupo> docenciaA=null;
							TreeMap <String,Grupo> docenciaB=null;
							Grupo asignaturaA=null;
							Grupo asignaturaB=null;
							Grupo gruposA=null;
							Grupo gruposB=null;
							int numMaxA=0;
							int numMaxB=0;
							int numA=0;
							int numB=0;
							
							//Informacion del POD
				
						if(!error) {//Asignatura no presente en el POD
							
							
							if(!listgrupA.containsKey(asignatura)&&!listgrupB.containsKey(asignatura)) {
								
							new Avisos("Asignar Grupo",6);
							error=true;
								
							}							
						}
						if(!error) {//No exceder el numero de grupos maximo para ese profesor
							profesor=profesores.get(dni);
							docenciaA=profesor.getDocenciaA();
							docenciaB=profesor.getDocenciaB();
							if(docenciaA.containsKey(asignatura)) {
								asignaturaA=docenciaA.get(asignatura);
								numA=asignaturaA.getNumGrupos();
								
							}
							if(docenciaB.containsKey(asignatura)) {
								asignaturaB=docenciaB.get(asignatura);
								numB=asignaturaB.getNumGrupos();
								
							}
													
							
							if(listgrupA.containsKey(asignatura)) {
								gruposA=listgrupA.get(asignatura);
								numMaxA=gruposA.getNumGrupos();
								
							}
							if(listgrupB.containsKey(asignatura)) {
								gruposB=listgrupB.get(asignatura);
								numMaxB=gruposB.getNumGrupos();
								
							}
							
						
							
						if(tipogrupo=='A'){	
							if(!(numA<numMaxA)) {
								new Avisos("Asignar Grupo",7);
								error=true;
							}
						}
							
						
						if(tipogrupo=='B') {
							if(!(numB<numMaxB)) {
								new Avisos("Asignar Grupo",7);
								error=true;
							}
	
						}
						
						}
						if(!error) {
							TreeMap<Character,Grupo> dias=profesor.getDias();
							asignaturas=asignatur.get(asignatura);
							char dia;
							int horaini=0;
							String aula="";
							Grupo grupoespecifico=null;
							TreeMap <Integer,Grupo> GruposA=asignaturas.getListaGruposA();
							TreeMap <Integer,Grupo> GruposB=asignaturas.getListaGruposB();
							if(tipogrupo=='A') {
								 grupoespecifico=GruposA.get(grupo);
								 dia=grupoespecifico.getDia();
								 horaini=grupoespecifico.getHoraini();
								 aula=grupoespecifico.getAula();
							}else {
							    grupoespecifico=GruposB.get(grupo);
								dia=grupoespecifico.getDia();
								horaini=grupoespecifico.getHoraini();
								aula=grupoespecifico.getAula();
							}
							
							Grupo diadelgrupo=dias.get(dia);
							TreeMap<Integer,Grupo> horas=diadelgrupo.getHorasgrupos();
							if(horas.containsKey(horaini)) {
								new Avisos("Asignar Grupo",9);
								error=true;	
							}
							//Asignamos el grupo 
							if(!error) {
								

								Asignatura asig=asignatur.get(asignatura);
								int duracion=0;
								if(tipogrupo=='A') {
									duracion=asig.getDuracionGrupoA();
								}else duracion=asig.getDuracionGrupoB();
								profesor.setDias(tipogrupo,Integer.toString(grupo), horaini, dia, aula);
								dias=profesor.getDias();
								diadelgrupo=dias.get(dia);
								int[]horasocupadas=new int[3];
								for(int i=0;i<duracion;i++) {
									
									horasocupadas[i]=horaini+i;
								diadelgrupo.setHorasGrupos(tipogrupo, Integer.toString(grupo), horasocupadas[i], dia, aula,asignatura);
								}
								if(tipogrupo=='A') {
									profesor.setDocenciaA(asignatura+" A "+grupo);
								}else {
									
									profesor.setDocenciaB(asignatura+" B "+grupo);
									
									
								}
								//Guardamos en el POD el nuevo grupo del profesor;
							/*	if(tipogrupo=='A') {
								podaux.setListaGruposA(asignatura, "A", Integer.toString(grupo));
								}else {
									podaux.setListaGruposB(asignatura, "B", Integer.toString(grupo));
								}*/
								//Guardamos en el aula el profesor que va a impartir el grupo
								
								Aula Aula=aulas.get(aula);
								TreeMap<Character,Grupo> D=Aula.getDias();
								Grupo diaespec=D.get(dia);
								
								for(int j=0;j<horasocupadas.length;j++) {
									diaespec.setHorasGrupos('B', Integer.toString(grupo),horasocupadas[j] ,dia, grupoespecifico.getAula(),asignatura);
									
								
									}
								TreeMap<Integer,Grupo> horasespecif=diaespec.getHorasgrupos();
								for(int j=0;j<horasocupadas.length;j++) {
									Grupo Hespecif=horasespecif.get(horasocupadas[j]);
									Hespecif.setDNIDocente(dni);
									
									}
								//Guardamos en asignaturas el nuevo docente que imparte el grupo
								
								TreeMap<Integer,Grupo> listA=asig.getListaGruposA();
								TreeMap<Integer,Grupo> listB=asig.getListaGruposB();
								if(tipogrupo=='A') {
									Grupo GA=listA.get(grupo);
									GA.setDNIDocente(dni);
									
								}
								if(tipogrupo=='B') {
									Grupo GB=listB.get(grupo);
									GB.setDNIDocente(dni);
									
								}
				
							}
						}
						
				
				}//Cierre tratamiento perfil=profesor	
				}
							        
			
			//PARTE ESPECIFICA DEL ALUMNO
			//sacamos la informacion que tenemos sobre el alumno
			if(!error) {
				if(perfil.equals("alumno")) {
		    Alumno alumno=alumnos.get(dni);
			TreeMap <String,Grupo> docencrecA=alumno.getDocencrecA();
			TreeMap <String,Grupo> docencrecB=alumno.getDocencrecB();
			TreeMap<String,Grupo> docenrec=alumno.getDocencrec();
			
				if(!docenrec.containsKey(asignatura)) {					
				error=true;
				
			}
				if(error) {
	
			if(tipogrupo=='A') {//Alumno no matriculado
				if(!docencrecA.containsKey(asignatura)) {
					error=true;
					
				}else error=false;
			}
				
			if(tipogrupo=='B') {
				if(!docencrecB.containsKey(asignatura)) {					
					error=true;
				}else error=false;
			
				
			}
				}
				if(error) {
					new Avisos ("Asignar Grupo",5);
					error=true;
				}
			
		
			
			
			
			
			//Aula completa
			if(!error) {
			asignaturas=asignatur.get(asignatura);
			TreeMap<Integer,Grupo> gruposA=null;
			TreeMap<Integer,Grupo> gruposB=null;
			Grupo grupoespecificoA=null;
			Grupo grupoespecificoB=null;
			String numAulaA="";
			String numAulaB="";
			Aula aulaA=null;
			Aula aulaB=null;
			TreeMap<String,Grupo>GruposA=null;
			TreeMap<String,Grupo>GruposB=null;
			int capacidadA=0;
			int capacidadB=0;
			Grupo grup=null;
			
			if(tipogrupo=='A') {
				gruposA=asignaturas.getListaGruposA();
				grupoespecificoA=gruposA.get(grupo);
				numAulaA=grupoespecificoA.getAula();
				aulaA=aulas.get(numAulaA);
				GruposA=aulaA.getGruposA();
				grup=GruposA.get(asignatura+" A "+Integer.toString(grupo));
				int ocupacion=grup.getOcupacion();
				capacidadA=aulaA.getCapacidad();
				 if(!(ocupacion<capacidadA)) {
					 
					 new Avisos("Asignar Grupo",10);
					 error=true;
				 }
		
			}
			if(tipogrupo=='B') {
				gruposB=asignaturas.getListaGruposB();
				grupoespecificoB=gruposB.get(grupo);
				numAulaB=grupoespecificoB.getAula();
				aulaB=aulas.get(numAulaB);
				GruposB=aulaB.getGruposB();
				grup=GruposB.get(asignatura+" B "+Integer.toString(grupo));
				int ocupacion=grup.getOcupacion();
				capacidadB=aulaB.getCapacidad();
				 if(!(ocupacion<capacidadB)) {
					 
					 new Avisos("Asignar Grupo",10);
					 error=true;
				 }
		
			}
			
			
			//Solape del alumno
			if(!error) {
				TreeMap<Character,Grupo> dias=alumno.getDias();
				asignaturas=asignatur.get(asignatura);
				char dia;
				int horaini=0;
				String aula="";
				Grupo grupoespecifico=null;
				Grupo diadelgrupo=null;
				TreeMap<Integer,Grupo> horas=null;
				if(tipogrupo=='A') {
					 grupoespecifico=gruposA.get(grupo);
					 dia=grupoespecifico.getDia();
					 
					 horaini=grupoespecifico.getHoraini();
					 aula=grupoespecifico.getAula();
				}else {
				    grupoespecifico=gruposB.get(grupo);
					dia=grupoespecifico.getDia();
					horaini=grupoespecifico.getHoraini();
					aula=grupoespecifico.getAula();
				}
				if(dias.containsKey(dia)) {
				diadelgrupo=dias.get(dia);
				horas=diadelgrupo.getHorasgrupos();
				
				if(horas.containsKey(horaini)) {
					new Avisos("Asignar Grupo",8);
					error=true;	
				}
				}else {
					error=false;
				}
				//Asignamos el grupo 
				if(!error) {
					
					alumno.setDias(tipogrupo,Integer.toString(grupo), horaini, dia, aula);					
					dias=alumno.getDias();
					diadelgrupo=dias.get(dia);
					diadelgrupo.setHorasGrupos(tipogrupo, Integer.toString(grupo), horaini, dia, aula,asignatura);
					if(tipogrupo=='A') {
						alumno.setDocencrecA(asignatura+" A "+grupo);
					}else {
						alumno.setDocencrecB(asignatura+" B "+grupo);
						
						
					}
					//Aumentamos la ocupacion del aula
					grup.setOcupacion();
				}
				
			}
			}
			}
			}
			
		}
		/**Método encargado de llevar a cabo la funcionalidad 'Matricular Alumno' la cual debe enrolar a un alumno en una asignatura
		 * en la que no esté previamente ya matriculado.
		 * 
		 * @param dni Contiene el documento nacional de identidad de la persona a introducir.Formato(8 dígitos y una letra mayúscula)
		 * @param asignatura Identificador (Siglas) de la asignatura en la que se quiere matricular al alumno.
		 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
		 */
		
		
		public static void MatricularAlumno(String dni,String asignatura,Censo censo) {
			TreeMap<String,Alumno> alumnos=censo.getAlumnos();
			TreeMap<String,Asignatura>asignaturas=censo.getAsignaturas();
			boolean error=false;
			
			//Comprobamos si existe el alumno en la base de datos
			
			if(!alumnos.containsKey(dni)) {
				new Avisos("Matricular Alumno",0);
				error=true;
			}
			
			if(!error) {
				
				if(!asignaturas.containsKey(asignatura)) {
					new Avisos("Matricular Alumno",1);
					error=true;
				}
			}
			if(!error) {
				Alumno alumno=alumnos.get(dni);
				TreeMap<String,Grupo> docencrecA=alumno.getDocencrecA();
				TreeMap<String,Grupo> docencrecB=alumno.getDocencrecB();
				TreeMap<String,Grupo> docencrec=alumno.getDocencrec();
				if(docencrecA.containsKey(asignatura)||docencrecB.containsKey(asignatura)||docencrec.containsKey(asignatura)) {//Alumno ya matriculado					
					new Avisos("Matricular Alumno",2);
					error=true;
					
				}
		
			}
			if(!error) {
				Asignatura asign=asignaturas.get(asignatura);
				String[] datos=asign.getPrerrequisitos();
				int contador=0;
				
				Alumno alum=alumnos.get(dni);
				TreeMap<String,Asignatura> superadas=alum.getAsigsuperadas();				
				
				for(int i=0;i<datos.length;i++) {
					
					if(superadas.containsKey(datos[i])) {
						contador++;						
					}

				}
				if(contador!=datos.length) {
					new Avisos("Matricular Alumno",3);
					error=true;					
				}
		
			}
			//Matriculamos al alumno
			if(!error) {
				Alumno alumno=alumnos.get(dni);
				alumno.setDocencrec(asignatura);
				
	
			}
			
			
			
		}
		/**Método encargado de realizar la funcionalidad 'Crear grupo asignatura' la cual es capaz de crear un grupo inexistente hasta el momento.
		 * El grupo debe de ser de tipo Teórico(A) o Práctico(B) y pertenecer a una asignatura.
		 * 
		 * @param siglas Identificador de la asignatura a la cual queremos anhadir un grupo.
		 * @param tipogrupo Teórico(A) o Práctico(B).
		 * @param IdGrupo Identificador del grupo que queremos crear.
		 * @param dia Día de la semana en la que se impartirá el grupo creado. Éste será un
		 * @param HoraIni Hora de Inicio del grupo.
		 * @param Aula Aula en la que se quiere impartir el grupo.
		 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
		 */
		
		
		public static void CrearGrupo(String siglas,char tipogrupo,String IdGrupo,String dia,int HoraIni,String Aula,Censo censo) {
			boolean error=false;
			TreeMap<String,Asignatura> asignaturas=censo.getAsignaturas();
			TreeMap<String,Aula> aulas=censo.getAula();
			Aula aula=null;
			TreeMap<Character,Grupo> dias=null;
			Grupo Dia=null;
			TreeMap<Integer,Grupo> horasGrupos=null;
			Asignatura asignatura=null;
			TreeMap<Integer,Grupo> grupotipoA=null;
			TreeMap<Integer,Grupo> grupotipoB=null;
			
			//Comprobamos si existe la asignatura
			if(!asignaturas.containsKey(siglas)) {
				new Avisos("Crear Grupo Asignatura",0);
				error=true;
			}
			//Tipo de grupo correcto
			if(!error) {
				if(tipogrupo!='A' && tipogrupo!='B') {
					new Avisos("Crear Grupo Asignatura",1);
					error=true;
				}			
			}
			//Existencia del grupo
			
			if(!error) {
				asignatura=asignaturas.get(siglas);
				grupotipoA=asignatura.getListaGruposA();
				grupotipoB=asignatura.getListaGruposB();
				
				if(tipogrupo=='A') {
					if(grupotipoA.containsKey(Integer.parseInt(IdGrupo))) {
						new Avisos("Crear Grupo Asignatura",2);
						error=true;
					}
					
				}
				if(tipogrupo=='B') {
					if(grupotipoB.containsKey(Integer.parseInt(IdGrupo))) {
						new Avisos("Crear Grupo Asignatura",2);
						error=true;
					}
					
				}
				
			}
			//dia válido
			if(!error) {
			if(!dia.equals("L")&&!dia.equals("M")&&!dia.equals("X")&&!dia.equals("J")&&!dia.equals("V")) {
				new Avisos("Crear Grupo Asignatura",3);
				error=true;
			}
			
			}
			//Existencia de la aula
			if(!error) {
				if(!aulas.containsKey(Aula)) {
					new Avisos("Crear Grupo Asignatura",4);
					error=true;
				}	
			}
			//Solape de la Aula
			if(!error) {
				//Buscamos la duracion de este tipo de grupo en la asignatura especificada
				int duracion=0;
				if(tipogrupo=='A') {
					asignatura=asignaturas.get(siglas);
					duracion=asignatura.getDuracionGrupoA();					
				}else {
					duracion=asignatura.getDuracionGrupoB();
				}
				Integer[]horasnecesarias=new Integer[duracion];				
				horasnecesarias[0]=HoraIni;
				for(int j=1;j<duracion;j++) {
					horasnecesarias[j]=HoraIni+j;					
				}
				
				
				//Comprobamos la ocupacion del aula
				
				aula=aulas.get(Aula);
				dias=aula.getDias();
				if(dias.containsKey(dia.charAt(0))) {
				Dia=dias.get(dia.charAt(0));	
				horasGrupos=Dia.getHorasgrupos();				
				for(int i=0;i<horasnecesarias.length&&error==false;i++) {
				if(horasGrupos.containsKey(horasnecesarias[i])) {
					
					new Avisos ("Crear Grupo Asignatura",5);
					error=true;
					
				}
				}
				}
				
				
			
			

			//CREAMOS EL GRUPO
			if(!error) {
				asignatura=asignaturas.get(siglas);
				grupotipoA=asignatura.getListaGruposA();
				grupotipoB=asignatura.getListaGruposB();
				if(tipogrupo=='A') {
					grupotipoA.put(Integer.parseInt(IdGrupo), new Grupo(tipogrupo,IdGrupo,HoraIni,dia.charAt(0),Aula));
				}
				if(tipogrupo=='B') {
					grupotipoB.put(Integer.parseInt(IdGrupo), new Grupo(tipogrupo,IdGrupo,HoraIni,dia.charAt(0),Aula));
				}
				//Guardamos en la ocupacion del Aula
				if(dias.containsKey(dia.charAt(0))) {//Si existe el dia
				for(int i=0;i<horasnecesarias.length;i++) {
				Dia.setHorasGrupos(tipogrupo, IdGrupo, horasnecesarias[i], dia.charAt(0), Aula,siglas);
		
			}
			}else {
				aula.setDias(IdGrupo,dia.charAt(0), HoraIni, Aula, tipogrupo);
				for(int i=0;i<horasnecesarias.length;i++) {
					Dia=dias.get(dia.charAt(0));
					Dia.setHorasGrupos(tipogrupo, IdGrupo, horasnecesarias[i], dia.charAt(0), Aula,siglas);
				}
			}
				
			}
			}
		}
		/**Método que nos permite evaluar si un alumno ha superado o no una determinada asignatura 
		 * y modificar consecuentemente toda la información relacionada en la base de datos de la escuela.
		 * 
		 * @param asignatura se corresponde con las siglas de la asignatura que queremos evaluar. Debe ser una asignatura existente en la base de datos y que no haya sido evaluda todavía durante el curso académico indicado.
		 * @param ficheroA es el nombre del fichero en el que encontraremos los resultados de la evaluación de la asignatura del grupo A en ese curso académico. 
		 * El fichero debe contener unicamete dos valores: el primero será el dni del alumno al que deseamos evaluar de la asiganatura y en segudno lugar la nota del grupo teórico comprendida entre 0 y 5.
		 * @param ficheroB es el nombre del fichero en el que encontraremos los resultados de la evaluación de la asignatura del grupo B en ese curso académico. 
		 * El fichero debe contener unicamete dos valores: el primero será el dni del alumno al que deseamos evaluar de la asiganatura y en segudno lugar la nota del grupo práctico comprendida entre 0 y 5.
		 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
		 * @throws IOException Error si no existe el fichero.
		 */
		
		
		
		public static void EvaluarAsignatura(String asignatura,String ficheroA,String ficheroB,Censo censo) throws IOException {
			TreeMap<String,Asignatura> asignaturas=censo.getAsignaturas();
		//TreeMap<String,profesores> profesores=censo.getProfesores();
			TreeMap<String,Alumno> alumnos=censo.getAlumnos();
			TreeMap<String,Asignatura> asignaturasuperadas=null;
			TreeMap<String,Alumno> notasalumno=new TreeMap<String,Alumno>();
			Alumno alumno=null;
			Asignatura asignatur=null;
			
			
		
			boolean error=false;
			boolean encontrada=false;
			
			//Existencia de la asignatura
			if(!asignaturas.containsKey(asignatura)) {
				new Avisos("Evaluar Asignatura",0,0,"");
				error=true;	
				
			}
			//Asignatura ya evaluada
			if (!error) {

				Iterator<String> it =alumnos.keySet().iterator();

				String n=null;

				while (it.hasNext()) {

				n=it.next(); //en n tenemos la clave
				alumno=alumnos.get(n);
				asignaturasuperadas=alumno.getAsigsuperadas();
				
			   Iterator<String>iterador=asignaturasuperadas.keySet().iterator();
			   
			   String asig=null;
			   
			   while (iterador.hasNext()&&encontrada==false) {

				   asig=iterador.next(); //en n tenemos la clave
				   if(asig.equals(asignatura)) {
					   encontrada=true;
					   asignatur=asignaturasuperadas.get(asig);
  
					   break;
				   }
			   }
				

				}
				if(encontrada) {
					if(asignatur.getCursoacadem().equals(censo.getCurso())) {
					
					new Avisos("Evaluar Asignatura",1,0,"");
					error=true;
					
				}	
				}
				
			}
			
			int lineasleidas=0;
			boolean fallo=false;
			//Fichero de las notas del grupo A
			if(!error) {
	        try {	
				
						FileReader fr= new FileReader(ficheroA+".txt");
						BufferedReader br = new BufferedReader(fr);
						String linea="";//Vamos a ir guardando lo leido provisionalmente
						
						  while((linea=br.readLine())!=null) {
							  fallo=false;
							  lineasleidas++;
						String Datos[]=linea.trim().split("\\s+");
						String dni=Datos[0].trim();
						Double nota=Double.parseDouble(Datos[1].trim());
				
						if(!alumnos.containsKey(dni)) {
							new Avisos("Evaluar Asignatura",2,lineasleidas,dni);
							fallo=true;			
						}else {

						alumno=alumnos.get(dni);
						TreeMap<String,Grupo> docencrecA=alumno.getDocencrecA();
						
						TreeMap<String,Grupo> docencrecB=alumno.getDocencrecB();
						TreeMap<String,Grupo> docencrec=alumno.getDocencrec();
						//System.out.println(asignatura);
						if(!docencrecA.containsKey(asignatura)&&!docencrecB.containsKey(asignatura)&&!docencrec.containsKey(asignatura)) {//Alumno no matriculado					
							
							new Avisos("Evaluar Asignatura",3,lineasleidas,dni);
							fallo=true;
							
						}
						if(!fallo) {
							if(nota>5.0||nota<0) {
								new Avisos("Evaluar Asignatura",4,lineasleidas,dni);
								fallo=true;
	
							}	
						}
						if(!fallo&&!error) {
							notasalumno.put(dni, new Alumno(nota,0));	
						}

						}//Final si existe el alumno
						}
					    fr.close();
					  
							 
				} catch(FileNotFoundException e) {
						   System.out.println("Fichero inexistente:"+ficheroA+".txt");
						   System.exit(1);
				     }
	        
	        //Leemos el fichero de los grupos B
	        
	        boolean nuevofallo=false;
	        lineasleidas=0;
	        if(!fallo) {
	        try {	
				
				FileReader fr= new FileReader(ficheroB+".txt");
				BufferedReader br = new BufferedReader(fr);
				String linea="";//Vamos a ir guardando lo leido provisionalmente
				
				  while((linea=br.readLine())!=null) {
					  nuevofallo=false;
					  lineasleidas++;
				String Datos[]=linea.trim().split("\\s+");
				String dni=Datos[0].trim();
				Double nota=Double.parseDouble(Datos[1].trim());
		
				
                if(notasalumno.containsKey(dni)) {//Si no contiene el dni ha fallado en la lectura del fichero A
                	
				alumno=alumnos.get(dni);
				TreeMap<String,Grupo> docencrecA=alumno.getDocencrecA();
				TreeMap<String,Grupo> docencrecB=alumno.getDocencrecB();
				TreeMap<String,Grupo> docencrec=alumno.getDocencrec();
				
				if(!nuevofallo) {
					
					if(nota>5||nota<0) {
						
						new Avisos("Evaluar Asignatura",4,lineasleidas,dni);
						nuevofallo=true;

					}	
				}
				
				if(!nuevofallo) {
					
					Alumno alum=notasalumno.get(dni);
					
					alum.setNotaB(nota);
					
					double Notatotal=alum.getNotaA()+nota;
					
					
					if(Notatotal>=5.0) {
						
						alumno.setAsigsuperadas(asignatura+" "+censo.getCurso()+" "+Double.toString(Notatotal));
	
					}
							
						if(docencrec.containsKey(asignatura)) {
							docencrec.remove(asignatura);
							
						}
                        if(docencrecA.containsKey(asignatura)) {
                        	docencrecA.remove(asignatura);
							
						}
                        if(docencrecB.containsKey(asignatura)) {
                        	docencrecB.remove(asignatura);
	
                        }
              	
				}
				
				}

				}//Final si existe el alumno
				
			    fr.close();
			  
					 
		} catch(FileNotFoundException e) {
				   System.out.println("Fichero inexistente:"+ficheroB+".txt");
				   System.exit(1);
		     }
	        }
	        
			
			
			
			}	
			
			
			
		}
		/** Método en el cual generaremos el expediente de un alumno. El expediente es una relación de todas las asignaturas aprobadas por ese alumno, ordenadas por curso y asignatura.
		 * @param dni es el documento nacional de identidad con el que identificamos al alumno del cual queremos obtener el expediente.
		 * @param salida se correponde con el nombre del fichero en el que debemos guardar el expediente de dicho alumno. Este fichero debe contener una única línea por cada asignatura aprobada y cada campo introucido debe separarse por ;.
		 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
		 */
		
		public static void Expediente(String dni,String salida,Censo censo) {
			TreeMap<String,Alumno> alumnos=censo.getAlumnos();
			TreeMap<String,Asignatura> asignaturas=censo.getAsignaturas();
			
			boolean error=false;
			int contador=0;//asignaturas para la media
			double notasum=0.0;
			
			
			//Comprobamos si existe el alumno
			if(!alumnos.containsKey(dni)) {
				new Avisos("Obtener Expediente Alumno",0);
				error=true;
	
			}
			
			if(!error) {
				Alumno alumno=alumnos.get(dni);
				TreeMap<String,Asignatura> asigsuperadas=alumno.getAsigsuperadas();
				Asignatura asignatur=null;
				if(!asigsuperadas.isEmpty()) {
				
				for(int i=1;i<=4;i++) {
				final Iterator<String> it = asigsuperadas.keySet().iterator();
				
				String asig=null;
				
				while (it.hasNext()) {
				
				asig=it.next(); //en n tenemos la clave
				asignatur=asignaturas.get(asig);
				int curso=asignatur.getCurso();
				if(curso==i) {
					Asignatura asignaturaespecifica=asigsuperadas.get(asig);
					double nota=asignaturaespecifica.getNota();
					notasum=notasum+nota;
					String cursoacademico=asignaturaespecifica.getCursoacadem();
					escribeFichero(curso+"; "+asig+"; "+nota+"; "+cursoacademico,salida);
					contador++;
				}
								
				}

				}
				
				//Calculamos la nota media del alumno
				double notamedia=notasum/contador;
				
				escribeFichero(Double.toString(notamedia),salida);
				
				
				
			}
			}else {//El alumno no tiene asignaturas superadas
				escribeFichero("",salida);
			}
	
		}
		/**Método que nos permite generar el calendario de ocupación semanal del aula, es decir, nos permite escribir por pantalla un horario de toda la semana en el que podemos ver a que horas está libre ese aula y a que horas está ocupada, 
		 * indicándonos la asignatura, el tipo de grupo(teórico o practico), el id que identifica ese grupo y el profesor que imparte ese grupo.
		 * 
		 * @param Aula nos indica de que aula tenemos que generar la ocupación semanal. Si en vez de un aula es un '*' debemos sacar por pantalla la ocupación de todas las aulas ordenadas alfabéicamente.
		 * @param censo  Base de datos donde se encuentran almacenados todos los datos necesarios.
		 */
		
		public static void Ocupacion(String Aula,Censo censo) {
			
			TreeMap<String,Aula> aulas=censo.getAula();
			Aula A=null;
			
			if(Aula.equals("*")){
               final Iterator<String> it = aulas.keySet().iterator();
				
				String aula=null;
				
				while (it.hasNext()) {
				
				aula=it.next(); //en n tenemos la clave
				
				A=aulas.get(aula);
				imprimirHorario(A,censo);
				
				}
				
			}else {
				A=aulas.get(Aula);
				imprimirHorario(A,censo);
			}
			
	
		}
		
		
		/**Método encargado de leer el fichero 'ejecucion.txt' el cual contiene las funciones que queremos poner a prueba
		 * de la aplicación.Este fichero puede contener errores,los cuales deben evaluarse.
		 * 
		 * @param censo  Base de datos donde se encuentran almacenados todos los datos necesarios.
		 * @throws IOException Error si no existe el fichero.
		 */
		public static void Ejecucion(Censo censo) throws IOException {
			
	        try {	
					
					FileReader fr= new FileReader("ejecucion.txt");
					BufferedReader br = new BufferedReader(fr);
					String linea;//Vamos a ir guardando lo leido provisionalmente
					
				  while((linea=br.readLine())!=null) {
					
					  if(linea.charAt(0)!='*') {						  
						  comando(linea,censo);
					  }
				  }	
				
				    fr.close();
				  
						 
			} catch(FileNotFoundException e) {
					   System.out.println("Fichero inexistente:'ejecucion.txt'");
					   System.exit(1);
			     }
			}
		/**Funcion encargada de seleccionar la funcionalidad correcta(llamando a su método) a partir de los leído del fichero 'ejecucion.txt'.
		 * 
		 * @param linea Línea con toda la información necesaria para ejecutar el método.
		 * @param censo  Base de datos donde se encuentran almacenados todos los datos necesarios.
		 * @throws IOException
		 */
			private static void comando(String linea,Censo censo) throws IOException {
				
				String[] lineasep=linea.trim().split("\\s+");
				
				String comando=lineasep[1].trim();
				
				switch(comando) {
				
				case "InsertaPersona":{
					if(lineasep[2].trim().equals("profesor")) {
						
						if(lineasep.length<10) {
							new Avisos("Insertar Persona",5);
							break;
						}
					
					
					InsertaPersona(lineasep[2].trim(),lineasep[3].trim(),(lineasep[4].trim()+" "+lineasep[5].trim()+" "+lineasep[6].trim()),lineasep[7].trim(),"",lineasep[8].trim(),lineasep[9].trim(),censo);
					break;
					}
					
				
				if(lineasep[2].trim().equals("alumno")) {
					if(lineasep.length<9) {
						new Avisos("Insertar Persona",5);
						break;
					}
					
					InsertaPersona(lineasep[2].trim(),lineasep[3].trim(),(lineasep[4].trim()+" "+lineasep[5].trim()+" "+lineasep[6].trim()),lineasep[7].trim(),lineasep[8].trim(),"","",censo);
				
					break;
	
				}
				}
				
				case "AsignaGrupo":{
					
					AsignarGrupo(lineasep[2].trim(),lineasep[3].trim(),lineasep[4].trim(),lineasep[5].trim().charAt(0),Integer.parseInt(lineasep[6].trim()),censo);
					break;
					
				}
				case "Matricula":{
					MatricularAlumno(lineasep[2].trim(),lineasep[3].trim(),censo);
					break;
				}
				case "CreaGrupoAsig":{
					CrearGrupo(lineasep[2].trim(),lineasep[3].trim().charAt(0),lineasep[4].trim(),lineasep[5].trim(),Integer.parseInt(lineasep[6].trim()),lineasep[7].trim(),censo);
					break;
				}
				case "Evalua":{
					EvaluarAsignatura(lineasep[2].trim(),lineasep[3].trim(),lineasep[4].trim(),censo);
					break;
				}
				case "Expediente":{
					Expediente(lineasep[2].trim(),lineasep[3].trim(),censo);
					break;
				}
				case "OcupacionAula":{
					Ocupacion(lineasep[2].trim(),censo);
					break;
				}
				case "ObtenerCalendarioClases":{
					CalendarioProfesor(lineasep[2].trim(),censo);
					break;
				}
				
				default:
					new Avisos("ComandoIncorrecto",0);
			}
			}
			/**Método encargado de escribir en un fichero específico los datos pasados.Este método se usará
			 * a la hora de escribir un expediente de un alumno enel método que implementa la funcionalidad
			 * 'Obtener Expediente Alumno'.
			 * 
			 * @param linea Contiene el curso,la sigla de la asignatura, la nota obtenida y el curso académico en el cual se cursó dicha asignatura; todo ello separado por ';'.
			 * @param salida Fichero en el cual queremos guardar el expediente obtenido del alumno.
			 */
			
public static void escribeFichero(String linea,String salida){

	File fichero=new File(salida);
	
	if(fichero.exists()){
		try{
			FileWriter punteroEscritura=new FileWriter(salida,true);
			punteroEscritura.write(System.getProperty("line.separator") + linea);
			punteroEscritura.close();	
		}catch(IOException e){
			
		}
	}else{
		try{
		
		FileWriter punteroEscritura=new FileWriter(fichero);
		punteroEscritura.write(linea);
		punteroEscritura.close();
		}catch(IOException e)
			{
				
			}
		}
		
	}
/**Este método es llamado por obtener calendario para que escriba por pantalla de manera ordenada, tabulada y en forma de horario la ocupación semanal de un aula.
 * 
 * @param aula es el aula del que queremos obtener el calendario de ocupación.
 * @param censo Base de datos donde se encuentran almacenados todos los datos necesarios.
 */
public static void imprimirHorario(Aula aula,Censo censo) {
	
	System.out.printf("\nAULA:%-30s\t\n", aula.getSigla());
	
	System.out.printf("\n%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n\n\n", "Hora", "L", "M", "X",
			"J","V");
	char[]dias= {'L','M','X','J','V'};
	TreeMap<Character,Grupo> diasAula=aula.getDias();
	TreeMap<Character,Grupo> ocupaciondias=new TreeMap<Character,Grupo>();
	
	
	for(int i=9;i<=18;i++) {		
		
			for(int j=0;j<dias.length;j++) {
			ocupaciondias.put(dias[j], new Grupo());
			Grupo mapa=ocupaciondias.get(dias[j]);	
				
			if(diasAula.containsKey(dias[j])) {
			Grupo grupo=diasAula.get(dias[j]);			
			TreeMap<Integer,Grupo> horas=grupo.getHorasgrupos();
			
	
			
			if(horas.containsKey(i)) {
			Grupo hora=horas.get(i);//Informacion de la ocupacion del aula
			mapa.setAsignatura(hora.getAsignatura());
			mapa.setTipoGrupo(hora.getTipoGrupo());
			String id[]=hora.getIdGrupo();
			mapa.setIdGrupo(id[0]);
			mapa.setDNIDocente(hora.getDNIDocente());
		
			
			}else {//hora libre
				mapa.setAsignatura("");
				mapa.setTipoGrupo(' ');				
				mapa.setIdGrupo("");	
				mapa.setDNIDocente("");
				
				
			}
				}else {//dia libre
					mapa.setAsignatura("");
					mapa.setTipoGrupo(' ');				
					mapa.setIdGrupo("");	
					mapa.setDNIDocente("");					
					
				}

	       }
	
			
		//System.out.println("%-25s\t%-25s\t%-25s\t%-25s\t%-25s",(i+"-"+(i+1),,DNI,nome,fechstring);
	//lunes
		Grupo lunes=ocupaciondias.get('L');
		String idL[]=lunes.getIdGrupo();
		String L=lunes.getAsignatura()+"-"+lunes.getTipoGrupo()+idL[0]/*+"\n"+lunes.getDNIDocente()+"\t"*/;
		
		String PL=Siglas(lunes.getDNIDocente(),censo);
		
	//Martes
		Grupo martes=ocupaciondias.get('M');
		String idM[]=martes.getIdGrupo();
		String M=martes.getAsignatura()+"-"+martes.getTipoGrupo()+idM[0]/*+"\n\t"+martes.getDNIDocente()*/;
		
		String PM=Siglas(martes.getDNIDocente(),censo);
		
	//Miercoles
		Grupo miercoles=ocupaciondias.get('X');
		String idX[]=miercoles.getIdGrupo();
		String X=miercoles.getAsignatura()+"-"+miercoles.getTipoGrupo()+idX[0]/*+"\n\t"+miercoles.getDNIDocente()*/;
		
		String PX=Siglas(miercoles.getDNIDocente(),censo);
	//Jueves
		Grupo jueves=ocupaciondias.get('J');
		String idJ[]=jueves.getIdGrupo();
		String J=jueves.getAsignatura()+"-"+jueves.getTipoGrupo()+idJ[0]/*+"\n\t"+jueves.getDNIDocente(*/;
		String PJ=Siglas(jueves.getDNIDocente(),censo);
	//Viernes
		Grupo viernes=ocupaciondias.get('V');
		String idV[]=viernes.getIdGrupo();
		String V=viernes.getAsignatura()+"-"+viernes.getTipoGrupo()+idV[0]/*+"\n\t"+viernes.getDNIDocente()*/;
		String PV=Siglas(viernes.getDNIDocente(),censo);
		
		if(i==14) {
			System.out.printf("\n%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n",i+"-"+(i+1),"XXXXXX","XXXXXX","XXXXXX","XXXXXX","XXXXXX");
		}else {
		System.out.printf("\n%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n",i+"-"+(i+1) , L, M, X,
				J,V);
		
		System.out.printf("\n%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n","",PL,PM,PX,PJ,PV);
		
		}

	}//Cierre del for principal

}
/**Método encargado de obtener la ocupación semanal de un profesor ,o de todos a la vez,
 * e imprimirlo por pantalla.
 * 
 * @param dni identificador personal del profesoro '*' en el caso de que se quiera obtener todos los profesores.
 * @param censo Base de datos donde se encuentra toda la información relativa al centro.
 */

public static void CalendarioProfesor(String dni,Censo censo) {
	TreeMap<String,Profesor> profesores=censo.getProfesores();
	Profesor A=null;
	boolean error=false;
	
	if(dni.equals("*")){
       final Iterator<String> it = profesores.keySet().iterator();
		
		String prof=null;
		
		while (it.hasNext()) {
		
		prof=it.next(); //en prof tenemos la clave
		
		A=profesores.get(prof);
		imprimirHorarioP(A,censo);
		
		}
		
	}else {
		if(!profesores.containsKey(dni)) {
			new Avisos("ObtenerCalendProfesor",0);
			error=true;
		}
		if(!error) {
		A=profesores.get(dni);
		imprimirHorarioP(A,censo);
		}
	}
	
	
	
	
	
	
	
}
/**Método encargado de imprimir en Horario del profesor en el formato especificado.Esté método es utilizado en la
 * funcionalidad Obtener Calendario Profesor.
 * 
 * @param profesor Instancia de Profesor con todos los datos relativos a un profesor específico.
 * @param censo Base de datos con toda la información relativa a la escuela.
 */

private static void imprimirHorarioP(Profesor profesor, Censo censo) {
System.out.printf("\nPROFESOR:%-30s\t\n", profesor.getNombre());
	
	System.out.printf("\n%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n\n\n", "Hora", "L", "M", "X",
			"J","V");
	char[]dias= {'L','M','X','J','V'};
	TreeMap<Character,Grupo> diasAula=profesor.getDias();
	TreeMap<Character,Grupo> ocupaciondias=new TreeMap<Character,Grupo>();
	
	
	for(int i=9;i<=18;i++) {		
		
			for(int j=0;j<dias.length;j++) {
			ocupaciondias.put(dias[j], new Grupo());
			Grupo mapa=ocupaciondias.get(dias[j]);	
				
			if(diasAula.containsKey(dias[j])) {
			Grupo grupo=diasAula.get(dias[j]);			
			TreeMap<Integer,Grupo> horas=grupo.getHorasgrupos();
			
	
			
			if(horas.containsKey(i)) {
			Grupo hora=horas.get(i);//Informacion de la ocupacion del aula
			mapa.setAsignatura(hora.getAsignatura());
			mapa.setTipoGrupo(hora.getTipoGrupo());
			String id[]=hora.getIdGrupo();
			mapa.setIdGrupo(id[0]);
			
		
			
			}else {//hora libre
				mapa.setAsignatura("");
				mapa.setTipoGrupo(' ');				
				mapa.setIdGrupo("");	
				
				
				
			}
				}else {//dia libre
					mapa.setAsignatura("");
					mapa.setTipoGrupo(' ');				
					mapa.setIdGrupo("");	
									
					
				}

	       }
	
			
		//System.out.println("%-25s\t%-25s\t%-25s\t%-25s\t%-25s",(i+"-"+(i+1),,DNI,nome,fechstring);
	//lunes
		Grupo lunes=ocupaciondias.get('L');
		String idL[]=lunes.getIdGrupo();
		String L=lunes.getAsignatura()+"-"+lunes.getTipoGrupo()+idL[0]/*+"\n"+lunes.getDNIDocente()+"\t"*/;
		
		
		
	//Martes
		Grupo martes=ocupaciondias.get('M');
		String idM[]=martes.getIdGrupo();
		String M=martes.getAsignatura()+"-"+martes.getTipoGrupo()+idM[0]/*+"\n\t"+martes.getDNIDocente()*/;
		
	//Miercoles
		Grupo miercoles=ocupaciondias.get('X');
		String idX[]=miercoles.getIdGrupo();
		String X=miercoles.getAsignatura()+"-"+miercoles.getTipoGrupo()+idX[0]/*+"\n\t"+miercoles.getDNIDocente()*/;
		
	//Jueves
		Grupo jueves=ocupaciondias.get('J');
		String idJ[]=jueves.getIdGrupo();
		String J=jueves.getAsignatura()+"-"+jueves.getTipoGrupo()+idJ[0]/*+"\n\t"+jueves.getDNIDocente(*/;
		
	//Viernes
		Grupo viernes=ocupaciondias.get('V');
		String idV[]=viernes.getIdGrupo();
		String V=viernes.getAsignatura()+"-"+viernes.getTipoGrupo()+idV[0]/*+"\n\t"+viernes.getDNIDocente()*/;
		
		
		if(i==14) {
			System.out.printf("\n%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n",i+"-"+(i+1),"XXXXXX","XXXXXX","XXXXXX","XXXXXX","XXXXXX");
		}else {
		System.out.printf("\n%-15s\t%-15s\t%-15s\t%-15s\t%-15s\t%-15s\n",i+"-"+(i+1) , L, M, X,
				J,V);
		
		
		
		}

	}//Cierre del for principal
	
	
	
	
	
	
	
	
	
}
/**Este método es llamado por 'obtener calendario' para obtener las iniciales del nombre y apellidos del profesor a partir del dni que le facilitamos.
 * 
 * @param dni es el documento nacional de identidad del profesor del que queremos obtener las inciales de su nombre y apellidos.
 * @param censo  Base de datos donde se encuentran almacenados todos los datos necesarios.
 * @return Un string con las iniciales del profesor.
 */
public static String Siglas(String dni,Censo censo) {
	
	if(!dni.equals("")) {
	String[]siglas=new String[4];
	
	TreeMap<String,Profesor> profesores=censo.getProfesores();
	Profesor profesor=profesores.get(dni);
	String nombre=profesor.getNombre();
	
	int contador=0;
	for(int i=0;i<nombre.length();i++) {
		if(Character.isUpperCase(nombre.charAt(i))) {
			siglas[contador]=Character.toString(nombre.charAt(i));
			contador++;
		}	
	}
	if(siglas[3]==null) {
		
		return siglas[0]+siglas[1]+siglas[2];
	}

	return siglas[0]+siglas[1]+siglas[2]+siglas[3];
	}else return "";
	
}
     
public static void guardarProfesores(Censo censo) throws IOException {
	TreeMap<String, Profesor> profesores = censo.getProfesores();
	

	Iterator<String> it = profesores.keySet().iterator();
	while(it.hasNext()){
	  String key = (String) it.next();
	  Profesor prof=profesores.get(key);
	  GregorianCalendar aux=prof.getFechaNac();
	String fecha=Integer.toString(aux.get(Calendar.DATE))+"/"+Integer.toString(aux.get(Calendar.MONTH))+"/"+Integer.toString(aux.get(Calendar.YEAR));
	String grupos="";
	TreeMap<String,Grupo> gruposA=prof.getDocenciaA();
	TreeMap<String,Grupo> gruposB=prof.getDocenciaB();
	Iterator<String> A=gruposA.keySet().iterator();
	Iterator<String> B=gruposB.keySet().iterator();
	while(A.hasNext()) {
		String asig = (String) A.next();
		Grupo grupo=gruposA.get(asig);
		String[] IdGrupo=grupo.getIdGrupo();
		for(int l=0;l<IdGrupo.length;l++) {
			if(IdGrupo[l]==null)break;
			grupos= grupos+asig+" A "+IdGrupo[l]+"; ";
			
			}
	}
	while(B.hasNext()) {
		String asig = (String) B.next();
		Grupo grupo=gruposB.get(asig);
		String[] IdGrupo=grupo.getIdGrupo();
		
		for(int l=0;l<IdGrupo.length;l++) {
			if(IdGrupo[l]==null)break;
		grupos= grupos+asig+" B "+IdGrupo[l]+"; ";
		
		}
	}
	
	  
	  escribeFichero(prof.getDNI()+"\n"+prof.getNombre()+"\n"+fecha+"\n"+prof.getCategoria()+"\n"+prof.getDepartamento()+"\n"+grupos+"\n*","profesores1.txt");
	}
	
}
private static void guardarAsignaturas(Censo censo) {
	TreeMap<String, Asignatura> asignaturas = censo.getAsignaturas();
 	
 	
 	Iterator<String> it = asignaturas.keySet().iterator();
	while(it.hasNext()){
	  String key = (String) it.next();
	  Asignatura asig=asignaturas.get(key);
	  //Prerrequisitos
	  String pre[]=asig.getPrerrequisitos();
	  String conc="";
	  
	  for (int k=0; k < pre.length;k++) {
		  if(k!=pre.length -1) {
		  conc=conc+pre[k]+";";}
		  else {
			  conc=conc+pre[k];
		  }
		  
	  }
	  //Grupos A
	  TreeMap <Integer, Grupo> listaGruposA=asig.getListaGruposA();
	  String gruposA="";
	  Iterator<Integer> A = listaGruposA.keySet().iterator();
	  while(A.hasNext()) {
		  Integer k2 = (Integer) A.next();
		  Grupo grupo=listaGruposA.get(k2);
		  gruposA=gruposA+" "+ k2+" "+ grupo.getDia()+" "+grupo.getHoraini()+" "+grupo.getAula() ;
		  if(A.hasNext()) { gruposA=gruposA+";";}
			  
		
		  
	  }
	
	  
	  //Grupos B
	  TreeMap <Integer, Grupo> listaGruposB=asig.getListaGruposB();
	  String gruposB="";
	  Iterator<Integer> B = listaGruposB.keySet().iterator();
	  while(B.hasNext()) {
		  Integer k3 = (Integer) B.next();
		  Grupo grupo=listaGruposB.get(k3);
		  gruposB=gruposB+" "+ k3+" "+ grupo.getDia()+" "+grupo.getHoraini()+" "+grupo.getAula();
		  if(B.hasNext()) { gruposB=gruposB+";";}
		  
	  }
	  
	  
	  escribeFichero(asig.getSigla()+"\n"+asig.getNombre()+"\n"+asig.getCurso()+"\n"+asig.getCuatrimestre()+"\n"+asig.getIDCoordinador()+"\n"+conc+"\n"+asig.getDuracionGrupoA()+"\n"+asig.getDuracionGrupoB()+"\n"+gruposA+"\n"+gruposB+"\n************************************************************************","asignaturas1.txt");
	}
	
	
 	
	
	
}
public static void guardarPod(Censo censo) {
	TreeMap<String, Pod> pod=censo.getPod();
	Iterator<String> it=pod.keySet().iterator();
	
	while(it.hasNext()) {
		String k4=(String) it.next();
		System.out.println(k4);
		TreeMap<String,Grupo> listaGruposA=pod.get(k4).getListaGruposA();
		TreeMap<String,Grupo> listaGruposB=pod.get(k4).getListaGruposB();
		//Agrupamos los grupos
		String gruposA="";
		String gruposB="";
		Iterator<String> A=listaGruposA.keySet().iterator();
		Iterator<String> B=listaGruposB.keySet().iterator();
		while(A.hasNext()) {
			String k5=(String) A.next();
			for(int ñ=0;ñ<listaGruposA.get(k5).getNumGrupos();ñ++) {
		
			gruposA= gruposA+ k5+" A "+ listaGruposA.get(k5).getIdGrupo()[ñ]+"; ";
			}
			
		}
		while(B.hasNext()) {
			String k6=(String) B.next();
			for(int ñ=0;ñ<listaGruposB.get(k6).getNumGrupos();ñ++) {
			
			gruposB= gruposB+ k6+" B "+ listaGruposB.get(k6).getIdGrupo()[ñ]+";";
			}	
		}
		
		if(gruposB.equals(";")) {
			gruposB="";
		}
		if(gruposA.equals(";")) {
			gruposA="";
		}
		System.out.println("33");
		escribeFichero(k4+"\n"+gruposA+gruposB+"\n*\n","pod1.txt");
		}
	}
	
	



public static void guardarAlumnos(Censo censo){
	TreeMap<String, Alumno> alumnos = censo.getAlumnos();
	Iterator<String> it = alumnos.keySet().iterator();

	while(it.hasNext()){
		  String key = (String) it.next();
		  Alumno al=alumnos.get(key);
		  GregorianCalendar nac=al.getFechaNac();
		String fechanac=Integer.toString(nac.get(Calendar.DATE))+"/"+Integer.toString(nac.get(Calendar.MONTH)+1)+"/"+Integer.toString(nac.get(Calendar.YEAR));
		GregorianCalendar ing=al.getFechaIngreso();
		String fechaing=Integer.toString(ing.get(Calendar.DATE))+"/"+Integer.toString(ing.get(Calendar.MONTH)+1)+"/"+Integer.toString(ing.get(Calendar.YEAR));
		String grupos="";
		String superadas="";
		

		TreeMap<String,Grupo> docenrec=al.getDocencrec();
		TreeMap<String,Grupo> docenA=al.getDocencrecA();
		TreeMap<String,Grupo> docenB=al.getDocencrecB();
		TreeMap<String,Asignatura> asigsup=al.getAsigsuperadas(); 
		Iterator<String> A=docenA.keySet().iterator();
		Iterator<String> B=docenB.keySet().iterator();
		Iterator<String> X=docenrec.keySet().iterator();
		Iterator<String> aux=asigsup.keySet().iterator();
		
		
		while(aux.hasNext()) {
			String sup=(String)aux.next();
			Asignatura superada=asigsup.get(sup);
			superadas=superadas+sup+" "+superada.getCursoacadem()+" "+superada.getNota()+"; ";
			
		}
		
		
		while(A.hasNext()) {
			String asig = (String) A.next();
			Grupo docen=docenA.get(asig);
			String[] IdGrupo=docen.getIdGrupo();
			for(int l=0;l<docen.getNumGrupos();l++) {
				
				if(IdGrupo[l]==null)break;
				grupos= grupos+asig+" A "+IdGrupo[l]+";";
				}
		}
		while(B.hasNext()) {
			String asig = (String) B.next();
			Grupo docen=docenB.get(asig);
			String[] IdGrupo=docen.getIdGrupo();
			
			for(int l=0;l<docen.getNumGrupos();l++) {
				if(IdGrupo[l]==null)break;
			grupos= grupos+asig+" B "+IdGrupo[l]+";";

			
			}
	}
			
		while(X.hasNext()) {
			String asig = (String) X.next();
			Grupo docen=docenrec.get(asig);
				grupos= grupos+asig+"; ";
				
		    }

		escribeFichero(al.getDNI()+"\n"+al.getNombre()+"\n"+al.getEmail()+"\n"+fechanac+"\n"+fechaing+"\n"+superadas+"\n"+grupos+"\n*","alumnos1.txt");
	}
}

 public static void OrdenaProfXPod(Censo censo) {
	
	 TreeMap<String, Pod> pod=censo.getPod();
	 TreeMap<String, String> o0=new TreeMap<String,String>();
	 TreeMap<String, String> o2=new TreeMap<String,String>();
	 TreeMap<String, String> o4=new TreeMap<String,String>();
	 TreeMap<String, String> o6=new TreeMap<String,String>();
	 TreeMap<String, String> o8=new TreeMap<String,String>();
	 TreeMap<String, String> o10=new TreeMap<String,String>();
	 TreeMap<String, String> o12=new TreeMap<String,String>();
	 TreeMap<String, String> o14=new TreeMap<String,String>();
	 TreeMap<String, String> o16=new TreeMap<String,String>();
	 TreeMap<String, String> o18=new TreeMap<String,String>();
	 TreeMap<String,Profesor> profesores=censo.getProfesores();
     int contador=0;
		Iterator<String> it = pod.keySet().iterator();
		
		while(it.hasNext()){
			 String key = (String) it.next();
			  Pod prof=pod.get(key);
			  
			 
			  TreeMap<String,Grupo> gruposA=prof.getListaGruposA();
			  TreeMap<String,Grupo> gruposB=prof.getListaGruposB();
			  
			  Iterator<String> A =gruposA.keySet().iterator();
			  Iterator<String> B = gruposB.keySet().iterator();
			  
			  int contadorA=0;
			  int contadorB=0;
			 
			while(A.hasNext()) {
				String itA=(String) A.next();
				contadorA= contadorA+gruposA.get(itA).getNumGrupos()*2;

				
			}
			
			while(B.hasNext()) {
				String itB=(String) B.next();
				contadorB=contadorB+ gruposB.get(itB).getNumGrupos()*2;
			}
			
			contador=contadorA+contadorB;
			System.out.println(contador);
			String nombre=profesores.get(key).getNombre();
			switch(contador) {
			case 0:{
				o0.put(nombre,nombre+";\n  "+prof.getDni()+";\n  "+contador+"\n******\n");
				break;
			}
			case 2:{
				o2.put(nombre,nombre+";\n  "+prof.getDni()+";\n  "+contador+"\n******\n");
				break;
			}
			
			case 4:{
				o4.put(nombre, nombre+";\n  "+prof.getDni()+";\n  "+contador+"\n******\n");
				break;
			}
			case 6:{
				o6.put(nombre, nombre+";\n  "+prof.getDni()+";\n  "+contador+"\n******\n");
				break;
			}
			
			case 8:{
				o8.put(nombre, nombre+";\n  "+prof.getDni()+";\n  "+contador+"\n******\n");
				break;
			}
			case 10:{
				o10.put(nombre, nombre+";\n  "+prof.getDni()+";\n  "+contador+"\n******\n");
				break;
			}
			case 12:{
				o12.put(nombre, nombre+";\n  "+prof.getDni()+";\n  "+contador+"\n******\n");
				break;
			}
			case 14:{
				o12.put(nombre, nombre+";\n  "+prof.getDni()+";\n  "+contador+"\n******\n");
				break;
			}
			case 16:{
				o12.put(nombre, nombre+";\n  "+prof.getDni()+";\n  "+contador+"\n******\n");
				break;
			}
			case 18:{
				o12.put(nombre, nombre+";\n  "+prof.getDni()+";\n  "+contador+"\n******\n");
				break;
			}
	
			}

			}
		Iterator<String> iter0 = o0.keySet().iterator();
		while(iter0.hasNext()){
			String k2= (String) iter0.next();
			String cadena;
			cadena=o0.get(k2);
			escribeFichero(cadena,"podOrdenado.txt");
		
		}
		
		Iterator<String> iter2 = o2.keySet().iterator();
		while(iter2.hasNext()){
			String k2= (String) iter2.next();
			String cadena;
			cadena=o2.get(k2);
			escribeFichero(cadena,"podOrdenado.txt");
		
		}
		
		Iterator<String> iter4 = o4.keySet().iterator();
		while(iter4.hasNext()){
			String k4= (String) iter4.next();
			String cadena;
			cadena=o4.get(k4);
			escribeFichero(cadena,"podOrdenado.txt");
		
		}
		Iterator<String> iter6 = o6.keySet().iterator();
		while(iter6.hasNext()){
			String k6= (String) iter6.next();
			String cadena;
			cadena=o6.get(k6);
			escribeFichero(cadena,"podOrdenado.txt");
		}
		Iterator<String> iter8 = o8.keySet().iterator();
		while(iter8.hasNext()){
			String k8= (String) iter8.next();
			String cadena;
			cadena=o8.get(k8);
			escribeFichero(cadena,"podOrdenado.txt");
		}
		Iterator<String> iter10 = o10.keySet().iterator();
		while(iter10.hasNext()){
			String k10= (String) iter10.next();
			String cadena;
			cadena=o10.get(k10);
			escribeFichero(cadena,"podOrdenado.txt");
		}
		Iterator<String> iter12 = o12.keySet().iterator();
		while(iter12.hasNext()){
			String k12= (String) iter12.next();
			String cadena;
			cadena=o12.get(k12);
			escribeFichero(cadena,"podOrdenado.txt");
		}
		Iterator<String> iter14 = o14.keySet().iterator();
		while(iter14.hasNext()){
			String k14= (String) iter14.next();
			String cadena;
			cadena=o14.get(k14);
			escribeFichero(cadena,"podOrdenado.txt");
		}
		Iterator<String> iter16 = o16.keySet().iterator();
		while(iter16.hasNext()){
			String k16= (String) iter16.next();
			String cadena;
			cadena=o16.get(k16);
			escribeFichero(cadena,"podOrdenado.txt");
		}
		Iterator<String> iter18 = o18.keySet().iterator();
		while(iter18.hasNext()){
			String k18= (String) iter18.next();
			String cadena;
			cadena=o18.get(k18);
			escribeFichero(cadena,"podOrdenado.txt");
		}
		
			
	 
	 
	 
 }

}





package clinica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GestorCita {
	
private ArrayList<Cita> citas;

//Referencias a las instancias de los gestores de doctores y pacientes
private GestorDoctor doctores;
private GestorPacientes pacientes;

public void crearCSV() {
	
	File archivo = new File("C:/csv/citas.csv");
	
	try {
		//Si no existe archivo, intentamos crearlo
		if (!archivo.exists()) {
			File carpeta = archivo.getParentFile();
			carpeta.mkdirs();
			archivo.createNewFile();
		}
		
		FileWriter escritor = new FileWriter(archivo);
		//Cabecera para datos del CSV
		escritor.append("#ID,Fecha,Hora,Motivo,iDdoctor,iDpaciente\n");
		
		//Datos de las Citas
		for (Cita doc: citas)
			escritor.append(doc.generaLineaCSV());//Insertamos linea CSV
		
		escritor.close();
	} catch (IOException e) {
		System.out.println("Error de acceso a: " + archivo.getAbsolutePath());
	}
}
	
	public GestorCita(GestorDoctor gestDoc, GestorPacientes gestPaci) {
		citas = new ArrayList<Cita>();
		doctores = gestDoc;
	    pacientes = gestPaci;
	}
	
	/**
	 * Crea y registra una nueva Cita.
	 * @return True si se pudo registrar con éxito la nueva cita.
	 * False si no fue posible registrarla.
	 */
	public boolean nuevoCita() {
		String id = JOptionPane.showInputDialog(null, "Introduzca Identificador:", "Nuevo Cita", JOptionPane.QUESTION_MESSAGE);
		String fecha = JOptionPane.showInputDialog(null, "Fecha:", "Nuevo Fecha", JOptionPane.QUESTION_MESSAGE);
		String hora = JOptionPane.showInputDialog(null, "Hora:", "Nuevo Hora", JOptionPane.QUESTION_MESSAGE);
		String motivo = JOptionPane.showInputDialog(null, "Motivo:", "Nuevo Motivo", JOptionPane.QUESTION_MESSAGE);
		String iDdoctor = JOptionPane.showInputDialog(null, "ID del Doctor:", "Nuevo Doctor", JOptionPane.QUESTION_MESSAGE);
	    String iDpaciente = JOptionPane.showInputDialog(null, "ID del Paciente:", "Nuevo Paciente", JOptionPane.QUESTION_MESSAGE);
		
	    
	  //Pedimos los datos a los gestores que tenemos referenciados
	      Doctor doctor = doctores.getDoctor(iDdoctor);
	      Paciente paciente = pacientes.getPaciente(iDpaciente);
	    
	    Cita nuevoCita = new Cita(id, fecha, hora, motivo);
		
		
	        
		return citas.add(nuevoCita);
			//Devuelve TRUE si se insertó correctamente, FALSE si no se pudo insertar
		}
	

	
	/**
	 * Lista por pantalla los datos de todas las citas registradas.
	 */
	public void mostrarCitas() {
		for (Cita p: citas)
			p.mostrar();
	}
	
	/**
	 * Modifica el Cita que el usuario solicite mediante el ID de Cita.
	 * @return True al finalizar la modificacion correctamente.
	 * False si no se encontró ninguna cita con el ID indicado.
	 */
	public boolean modificarCita() {
		String id = JOptionPane.showInputDialog(null, "Introduzca Identificador del cita a modificar:",
				"Modificar Cita", JOptionPane.QUESTION_MESSAGE);
		/*
		 * Ahora buscaremos el doctor en el ArrayList y si lo encontramos lo referenciaremos
		 * a otro objeto de la clase Paciente que incialmente tiene valor null.
		 * Si tras finalizar la búsqueda, este objeto sigue valiendo null significa que no hemos
		 * encontrado el Doctor que nos han pedido e informaremos al usuario.
		 */
		Cita cita = null;
		for (int i = 0; i < citas.size(); i++){
			if (citas.get(i).getId().equals(id)) {
				cita = citas.get(i);//Referenciamos a otro nombre de objeto
				break;//No hace falta seguir buscando, "rompemos" el bucle for
			}
		}
		
		if (cita == null) {
			JOptionPane.showMessageDialog(null, "No se encuentra Cita con el ID:\n" + id,
					"Cita no encontrada", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			//Comienza el proceso de modificacion
			cita.setId(JOptionPane.showInputDialog(null, "ID actual: " + cita.getId()
				+ "\nIntroduzca nuevo identificador:", "Modificar Id", JOptionPane.QUESTION_MESSAGE));
			cita.setFecha(JOptionPane.showInputDialog(null, "Fecha actual: " + cita.getFecha()
				+ "\nIntroduzca nuevo fecha:", "Modificar Fecha", JOptionPane.QUESTION_MESSAGE));
			cita.setHora(JOptionPane.showInputDialog(null, "Hora actual: " + cita.getHora()
				+ "\nIntroduzca nuevos hora:", "Modificar Hora", JOptionPane.QUESTION_MESSAGE));
			cita.setMotivo(JOptionPane.showInputDialog(null, "Motivo actual: " + cita.getMotivo()
				+ "\nIntroduzca nuevo motivo:", "Modificar Motivo", JOptionPane.QUESTION_MESSAGE));
			
			}
			return true;
		}
	
	
	/**
	 * Elimina la Cita indicado por el usuario mediante el ID de la Cita.
	 * @return True si tuvo éxito la eliminacion.
	 * False si no se encontró la Cita indicada.
	 */
	public boolean borrarCita() {
		String id = JOptionPane.showInputDialog(null, "Introduzca Identificador del doctor a borrar:",
				"Borrar Cita", JOptionPane.QUESTION_MESSAGE);
		
		Cita cita = null;
		for (int i = 0; i < citas.size(); i++){
			if (citas.get(i).getId().equals(id))
				cita = citas.remove(i);//Al eliminar el objeto, el ArrayList nos lo devuelve y lo recogemos en "doctor"
		}
		
		if (cita == null) {
			JOptionPane.showMessageDialog(null, "No se encuentra Cita con el ID:\n" + id,
					"Cita no encontrado", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			JOptionPane.showMessageDialog(null, "Se elimino el cita con ID:\n" + id,
					"Cita Eliminada", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

}

package clinica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GestorDoctor {
	
private ArrayList<Doctor> doctores;

public Doctor getDoctor(String id) {
	for (Doctor doc: doctores)
		if (doc.getId().equals(id))
			return doc; //Doctor encontrado
	//Si el bucle no ha retornado ningún Doctor, es que no existe ese ID
	return null;
}

public void crearCSV() {
	
	File archivo = new File("C:/csv/doctores.csv");
	
	try {
		//Si no existe archivo, intentamos crearlo
		if (!archivo.exists()) {
			File carpeta = archivo.getParentFile();
			carpeta.mkdirs();
			archivo.createNewFile();
		}
		
		FileWriter escritor = new FileWriter(archivo);
		//Cabecera para datos del CSV
		escritor.append("#ID,Nombre,Apellidos,Especialidad\n");
		
		//Datos de los doctores
		for (Doctor doc: doctores)
			escritor.append(doc.generaLineaCSV());//Insertamos linea CSV
		
		escritor.close();
	} catch (IOException e) {
		System.out.println("Error de acceso a: " + archivo.getAbsolutePath());
	}
}
	
	public GestorDoctor() {
		doctores = new ArrayList<Doctor>();
	}
	
	/**
	 * Crea y registra un nuevo doctor.
	 * @return True si se pudo registrar con éxito el nuevo doctor.
	 * False si no fue posible registrarlo.
	 */
	public boolean nuevoDoctor() {
		String id = JOptionPane.showInputDialog(null, "Introduzca Identificador:", "Nuevo Doctor", JOptionPane.QUESTION_MESSAGE);
		String nombre = JOptionPane.showInputDialog(null, "Nombre:", "Nuevo Doctor", JOptionPane.QUESTION_MESSAGE);
		String apellidos = JOptionPane.showInputDialog(null, "Apellidos:", "Nuevo Doctor", JOptionPane.QUESTION_MESSAGE);
		String especialidad = JOptionPane.showInputDialog(null, "Especialidad:", "Nuevo Doctor", JOptionPane.QUESTION_MESSAGE);
		
		
		Doctor nuevoDoctor = new Doctor(id, nombre, apellidos, especialidad);
		return doctores.add(nuevoDoctor);
		//Devuelve TRUE si se insertó correctamente, FALSE si no se pudo insertar
		
		}
	

	
	/**
	 * Lista por pantalla los datos de todos los doctores registrados.
	 */
	public void mostrarDoctores() {
		for (Doctor p: doctores)
			p.mostrar();
	}
	
	/**
	 * Modifica el Doctor que el usuario solicite mediante el ID de Doctor.
	 * @return True al finalizar la modificacion correctamente.
	 * False si no se encontró ningun doctor con el ID indicado.
	 */
	public boolean modificarDoctor() {
		String id = JOptionPane.showInputDialog(null, "Introduzca Identificador del doctor a modificar:",
				"Modificar Doctor", JOptionPane.QUESTION_MESSAGE);
		/*
		 * Ahora buscaremos el doctor en el ArrayList y si lo encontramos lo referenciaremos
		 * a otro objeto de la clase Paciente que incialmente tiene valor null.
		 * Si tras finalizar la búsqueda, este objeto sigue valiendo null significa que no hemos
		 * encontrado el Doctor que nos han pedido e informaremos al usuario.
		 */
		Doctor doctor = null;
		for (int i = 0; i < doctores.size(); i++){
			if (doctores.get(i).getId().equals(id)) {
				doctor = doctores.get(i);//Referenciamos a otro nombre de objeto
				break;//No hace falta seguir buscando, "rompemos" el bucle for
			}
		}
		
		if (doctor == null) {
			JOptionPane.showMessageDialog(null, "No se encuentra Doctor con el ID:\n" + id,
					"Doctor no encontrado", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			//Comienza el proceso de modificacion
			doctor.setId(JOptionPane.showInputDialog(null, "ID actual: " + doctor.getId()
				+ "\nIntroduzca nuevo identificador:", "Modificar Doctor", JOptionPane.QUESTION_MESSAGE));
			doctor.setNombre(JOptionPane.showInputDialog(null, "Nombre actual: " + doctor.getNombre()
				+ "\nIntroduzca nuevo nombre:", "Modificar Doctor", JOptionPane.QUESTION_MESSAGE));
			doctor.setApellidos(JOptionPane.showInputDialog(null, "Apellidos actuales: " + doctor.getApellidos()
				+ "\nIntroduzca nuevos apellidos:", "Modificar Doctor", JOptionPane.QUESTION_MESSAGE));
			doctor.setEspecialidad(JOptionPane.showInputDialog(null, "Especialidad actual: " + doctor.getEspecialidad()
				+ "\nIntroduzca nuevo genero:", "Modificar Doctor", JOptionPane.QUESTION_MESSAGE));
			
			}
			return true;
		}
	
	
	/**
	 * Elimina el Doctor indicado por el usuario mediante el ID del Doctor.
	 * @return True si tuvo éxito la eliminacion.
	 * False si no se encontró el Doctor indicado.
	 */
	public boolean borrarDoctor() {
		String id = JOptionPane.showInputDialog(null, "Introduzca Identificador del doctor a borrar:",
				"Borrar Doctor", JOptionPane.QUESTION_MESSAGE);
		
		Doctor doctor = null;
		for (int i = 0; i < doctores.size(); i++){
			if (doctores.get(i).getId().equals(id))
				doctor = doctores.remove(i);//Al eliminar el objeto, el ArrayList nos lo devuelve y lo recogemos en "doctor"
		}
		
		if (doctor == null) {
			JOptionPane.showMessageDialog(null, "No se encuentra Doctor con el ID:\n" + id,
					"Doctor no encontrado", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			JOptionPane.showMessageDialog(null, "Se elimino el doctor con ID:\n" + id,
					"Doctor Eliminado", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

}



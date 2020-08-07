package clinica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public final class GestorPacientes {
	
	private ArrayList<Paciente> pacientes;
	
	public Paciente getPaciente(String id) {
		for (Paciente doc: pacientes)
			if (doc.getId().equals(id))
				return doc; //Paciente encontrado
		//Si el bucle no ha retornado ningún Doctor, es que no existe ese ID
		return null;
	}
	
public void crearCSV() {
		
		File archivo = new File("C:/csv/pacientes.csv");
		
		try {
			//Si no existe archivo, intentamos crearlo
			if (!archivo.exists()) {
				File carpeta = archivo.getParentFile();
				carpeta.mkdirs();
				archivo.createNewFile();
			}
			
			FileWriter escritor = new FileWriter(archivo);
			//Cabecera para datos del CSV
			escritor.append("#ID,Nombre,Apellidos\n");
			
			//Datos de los pacientes
			for (Paciente doc: pacientes)
				escritor.append(doc.generaLineaCSV());//Insertamos linea CSV
			
			escritor.close();
		} catch (IOException e) {
			System.out.println("Error de acceso a: " + archivo.getAbsolutePath());
		}
	}
	
	public GestorPacientes() {
		pacientes = new ArrayList<Paciente>();
	}
	
	/**
	 * Crea y registra un nuevo paciente.
	 * @return True si se pudo registrar con éxito el nuevo paciente.
	 * False si no fue posible registrarlo.
	 */
	public boolean nuevoPaciente() {
		String id = JOptionPane.showInputDialog(null, "Introduzca Identificador:", "Nuevo Paciente", JOptionPane.QUESTION_MESSAGE);
		String nombre = JOptionPane.showInputDialog(null, "Nombre:", "Nuevo Nombre", JOptionPane.QUESTION_MESSAGE);
		String apellidos = JOptionPane.showInputDialog(null, "Apellidos:", "Nuevos Apellidos", JOptionPane.QUESTION_MESSAGE);
				
		
		Paciente nuevoPaciente = new Paciente(id, nombre, apellidos);
		return pacientes.add(nuevoPaciente);
		//Devuelve TRUE si se insertó correctamente, FALSE si no se pudo insertar
	}
	
	/**
	 * Lista por pantalla los datos de todos los pacientes registrados.
	 */
	public void mostrarPacientes() {
		for (Paciente p: pacientes)
			p.mostrar();
	}
	
	/**
	 * Modifica el Paciente que el usuario solicite mediante el ID de Paciente.
	 * @return True al finalizar la modificacion correctamente.
	 * False si no se encontró ningun paciente con el ID indicado.
	 */
	public boolean modificarPaciente() {
		String id = JOptionPane.showInputDialog(null, "Introduzca Identificador del paciente a modificar:",
				"Modificar Paciente", JOptionPane.QUESTION_MESSAGE);
		/*
		 * Ahora buscaremos el paciente en el ArrayList y si lo encontramos lo referenciaremos
		 * a otro objeto de la clase Paciente que incialmente tiene valor null.
		 * Si tras finalizar la búsqueda, este objeto sigue valiendo null significa que no hemos
		 * encontrado el Paciente que nos han pedido e informaremos al usuario.
		 */
		Paciente paciente = null;
		for (int i = 0; i < pacientes.size(); i++){
			if (pacientes.get(i).getId().equals(id)) {
				paciente = pacientes.get(i);//Referenciamos a otro nombre de objeto
				break;//No hace falta seguir buscando, "rompemos" el bucle for
			}
		}
		
		if (paciente == null) {
			JOptionPane.showMessageDialog(null, "No se encuentra Paciente con el ID:\n" + id,
					"Paciente no encontrado", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			//Comienza el proceso de modificacion
			paciente.setId(JOptionPane.showInputDialog(null, "ID actual: " + paciente.getId()
				+ "\nIntroduzca nuevo identificador:", "Modificar Paciente", JOptionPane.QUESTION_MESSAGE));
			paciente.setNombre(JOptionPane.showInputDialog(null, "Nombre actual: " + paciente.getNombre()
				+ "\nIntroduzca nuevo nombre:", "Modificar Paciente", JOptionPane.QUESTION_MESSAGE));
			paciente.setApellidos(JOptionPane.showInputDialog(null, "Apellidos actuales: " + paciente.getApellidos()
				+ "\nIntroduzca nuevos apellidos:", "Modificar Paciente", JOptionPane.QUESTION_MESSAGE));
			
			return true;
		}
	}
	
	/**
	 * Elimina el Paciente indicado por el usuario mediante el ID del Paciente.
	 * @return True si tuvo éxito la eliminacion.
	 * False si no se encontró el Paciente indicado.
	 */
	public boolean borrarPaciente() {
		String id = JOptionPane.showInputDialog(null, "Introduzca Identificador del paciente a borrar:",
				"Borrar Paciente", JOptionPane.QUESTION_MESSAGE);
		
		Paciente paciente = null;
		for (int i = 0; i < pacientes.size(); i++){
			if (pacientes.get(i).getId().equals(id))
				paciente = pacientes.remove(i);//Al eliminar el objeto, el ArrayList nos lo devuelve y lo recogemos en "paciente"
		}
		
		if (paciente == null) {
			JOptionPane.showMessageDialog(null, "No se encuentra Paciente con el ID:\n" + id,
					"Paciente no encontrado", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else {
			JOptionPane.showMessageDialog(null, "Se elimino el paciente con ID:\n" + id,
					"Paciente Eliminado", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

}

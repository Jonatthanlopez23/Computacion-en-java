package clinica;

import java.util.ArrayList;
import java.util.Scanner;

public final class TestClinica {
	
	private static ArrayList<Administrador> administradores = new ArrayList<Administrador>();
	private static Scanner teclado = new Scanner(System.in);
	static GestorPacientes pacientes = new GestorPacientes();
	static GestorDoctor doctores = new GestorDoctor();
	private static GestorCita citas = new GestorCita(doctores, pacientes);//Pasamos referencias
	
	

	public static void main(String[] args) {
		
		crearAdmins();
		
		if (validarAcceso()) {
	         System.out.println("\nUsuario autorizado\n");
	         doctores.nuevoDoctor();
	         doctores.modificarDoctor();
	         doctores.mostrarDoctores();
	         pacientes.nuevoPaciente();
	         pacientes.mostrarPacientes();
	         pacientes.modificarPaciente();
	         pacientes.borrarPaciente();
	         citas.nuevoCita();
	         citas.mostrarCitas();
	         doctores.crearCSV();
	         citas.crearCSV();
	         pacientes.crearCSV();
	      }
	      else
	         System.out.println("\nUsuario no autorizado.");

	      System.out.println("\t\tFIN DE PROGRAMA");

	   }
	
	private static void crearAdmins() {
		administradores.add(new Administrador("Kabuto", "1234"));
		administradores.add(new Administrador("jonatthan23", "5678"));
		administradores.add(new Administrador("Ogramar", "0000"));
	}
	
	private static boolean validarAcceso( ) {
		
		System.out.println("ACCESO AL SISTEMA");
		System.out.println("------ -- -------\n");
		System.out.print("Nombre: ");
		String nombre = teclado.nextLine();
		System.out.print("Password: ");
		String password = teclado.nextLine();
		
		Administrador admin = new Administrador(nombre, password);
		
		return administradores.contains(admin);
	}

}
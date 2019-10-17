package ALEATORIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import utiles.trycatch;

public class ejercicio1 {

	static ArrayList<Integer> listaid = new ArrayList<Integer>();





	public static void main(String[] args) throws IOException {
		trycatch u = new trycatch();
		Empleado e = new Empleado();
		int n = 0;
		int id;
		
		while (n!=6) {
			 System.out.println("1 Consultar Empleado");
		        System.out.println("2 Crear Empleado ");
				System.out.println("3 Modificar Salario");
				System.out.println("4 Eliminar Empleado");
				System.out.println("5 leer fichero");
				System.out.println("6 salir");
			n=u.try_int();
			
				switch(n) {
				case 1: 
					System.out.println("Introduce la id del Empleado");
					id=u.try_int();
					e.consultaEmpleado(id);		
				try {
					System.out.println(e.consultaEmpleado(id).toString());
					
				}catch(NullPointerException l) {
					System.out.println("Ese Empleado No existe");
			}
					break;
				case 2: e.crearEmpleado(e); 
				break;
				case 3:
					
					Double saldo;
					System.out.println("Introduce la id de Empleado");
					id=u.try_int();
					System.out.println("Introduce el saldo");
					saldo=u.try_double();
					
						
					if(e.GuardarSaldo(id)==null) {
						System.out.println("Ese Empleado no existe");
					}else {
						System.out.println("El antiguo salario es de "+e.GuardarSaldo(id));
						e.actualizarsaldo(id, saldo);
						System.out.println("El nuevo salario es de: "+e.GuardarSaldo(id));
					}
						  
			
					break;
				case 4:
					
					System.out.println("Introduce el indentificador del empleado a eliminar");
					id=u.try_int();
					 e.EliminarEmpleado(id);
					break;
				case 5: e.leerFichero();break;
				case 6: break;
				default: 
					System.out.println("Error, elige otra opcion");break;
		}
	 
		}
	



	
	
		
	}


}

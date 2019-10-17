package BINARIO;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;

import utiles.trycatch;

public class Departamento implements Serializable {
int Ndepartamento;
String Nombre;
String localidad;
FileOutputStream fileout;
FileInputStream filein;
HashMap<Integer, Departamento> departamento = new HashMap<Integer, Departamento>();



public void Inicio() throws  IOException, ClassNotFoundException {
	trycatch u = new trycatch();
	FileInputStream fileint = null;

	ObjectInputStream out = null;
	boolean f = false;
	do {
		
	try {
		
	 fileint=new FileInputStream("departamento.dat");
	  out = new ObjectInputStream( fileint);
	this.departamento=(HashMap<Integer, Departamento>) out.readObject();
	
	out.close();
	fileint.close();
	
	f=true;
	}catch(FileNotFoundException e) {
		File fichero = new File("departamento.dat");
		PrintWriter inicializador = new PrintWriter(new FileWriter(fichero));
		inicializador.close();
		
		f=false;
	
	}catch(EOFException e) {
		
		fileint.close();
		
		break;
	}

	}while(f==false);
	


	
	BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
	
	int n = 0;
	
	while(n!=4) {
		System.out.println("1- Crea un nuevo departamento");
		System.out.println("2- Modifica un departamento");
		System.out.println("3 Elimina un departamento");
		System.out.println("4 Salir");
		n=u.try_int();
		switch(n) {
		case 1: 
			IntroducirDepartamento();
			break;
		case 2:
			System.out.println("Introduce el numero del departamento a modificar");
			this.modificarDepartamento(u.try_int()); 
			break;
			
		case 3: 
			System.out.println("Introduce el número del departamento a eliminar");
			this.eliminarDepartamento(u.try_int());
			break;
			
		}
	}
	
	
}
public void escribirfichero() throws IOException {

	FileOutputStream fileout=new FileOutputStream("departamento.dat");
	ObjectOutputStream out = new ObjectOutputStream( fileout);
	out.writeObject( departamento);
	out.close();
	fileout.close();

	System.out.println("Departamento Introducido con exito");
}

public void IntroducirDepartamento() throws IOException {
	trycatch u = new trycatch();
	boolean f=false;
	Departamento d = new Departamento();
	do {
		System.out.println("Introduce el numero de departamento");
		Ndepartamento=u.try_int(); f=false;
		if(departamento.containsKey(Ndepartamento)) {
			System.out.println("Ese numero no esta disponible");
			f=true;
		}	
	}while(f==true);
	
	
	System.out.println("Introduce el nombre");
	d.setNombre(u.try_String());
	System.out.println("Introduce Localidad");
	d.setLocalidad(u.try_String());
	d.setNdepartamento(Ndepartamento);
	 departamento.put(Ndepartamento, d);
	escribirfichero();

	
}
public void modificarDepartamento(int id) throws NumberFormatException, IOException {
	trycatch u = new trycatch();
	boolean t=false;

	if(	departamento.containsKey(id)) {
		while(t==true) {
			System.out.println("Introduce el numero del departamento");
			Ndepartamento=u.try_int();
			
			if(departamento.containsKey(Ndepartamento)) {
				System.out.println("No puede poner ese numero porque ya esta utilizado");
				t=true;
			}else {
				t=false;
			}
		}
	
		System.out.println("Introduce El nombre");
		Nombre=u.try_String();
		System.out.println("Introduce la localidad");
		localidad=u.try_String();
				
		System.out.println("DATOS ANTIGUOS");
		System.out.println("Numero Departamento: "+departamento.get(id).getNdepartamento()+" Nombre: "+departamento.get(id ).getNombre()+" Localidad: "+departamento.get(id).getLocalidad());
		System.out.println("\n");
		System.out.println("DATOS MODIFICADOS");
		System.out.println("\n");
		departamento.get(id).setNdepartamento(id);
		
		departamento.get(id).setNombre(Nombre);
		departamento.get(id).setLocalidad(localidad);
		System.out.println("Numero Departamento: "+departamento.get(id).getNdepartamento()+" Nombre: "+departamento.get(id ).getNombre()+" Localidad: "+departamento.get(id).getLocalidad());

	}else {
		
System.out.println("Ese departamento no existe");
	}

}







public void eliminarDepartamento(int id) {
	int cont=0;
	if(departamento.size()==0) {
		System.out.println("No se puede eliminar nada ya que no existe ningun departamento");
	}else if(departamento.containsKey(id)) {
		
		departamento.remove(id);
		System.out.println("El departamento: "+id+" Ha sido eliminado");
	
	}else {
		System.out.println("Ese número no existe");
		
	}
	
	System.out.println("Hay "+departamento.size()+" departamentos guardados");
}


public Departamento() {
	super();
}

public int getNdepartamento() {
	return Ndepartamento;
}
public void setNdepartamento(int ndepartamento) {
	Ndepartamento = ndepartamento;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public String getLocalidad() {
	return localidad;
}
public void setLocalidad(String localidad) {
	this.localidad = localidad;
}




}

package ALEATORIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import utiles.trycatch;

public class Empleado {
	trycatch u = new trycatch();
int id;
String apellidos;
int departamento;
double salario;
	ejercicio1 a;
public void crearEmpleado(Empleado e) throws IOException {
	int id;

	System.out.println("Introduce el identificador");
	id=u.try_int();
	
	
if(this.comprobarid(id)==true) {
	e.setId(id);
	System.out.println("Apellidos");
	e.setApellidos(u.try_String());

	System.out.println("Departamento");
e.setDepartamento(u.try_int());
	System.out.println("Salario");
	e.setSalario(u.try_double());

	insertarEmpleado(e.getId(), e.getApellidos(),e.getDepartamento(), e.getSalario());
}else {
	System.out.println("Esa id no está disponible");	
}
	
	
}

public void insertarEmpleado(int id, String apellidos, int departamento, double salario) throws IOException {
	File fichero=new File("empleado.txt");
	RandomAccessFile file= new RandomAccessFile(fichero, "rw");
	long posicion=file.length();
	file.seek(posicion);
	file.writeInt(id);
	StringBuffer buffer = new StringBuffer(apellidos);
	buffer.setLength(12);
	file.writeChars(buffer.toString());
	file.writeInt(departamento);
	file.writeDouble(salario);
	
	file.close();
}

public boolean comprobarid (int id) throws IOException {
	File fichero=new File("empleado.txt");
	RandomAccessFile file= new RandomAccessFile(fichero, "r");
	long posicion=0;
	while(true) {
		file.seek(posicion);
		if(file.getFilePointer()== file.length())
			break;
		

		if(id==file.readInt()) {
	
			return false;
			}
		posicion+=40;
	}
	return true;
}


public void leerFichero() throws IOException {
	File fichero=new File("empleado.txt");
	RandomAccessFile file= new RandomAccessFile(fichero, "r");
	long posicion=0;
	
	while(true) {
		file.seek(posicion); 
		if(file.getFilePointer()== file.length())break;
		
		System.out.println(file.readInt());
		char[] apellidoAux=new char[12]; 
		char aux;
		for(int i =0; i<apellidoAux.length; i++) {
			aux=file.readChar( );
			apellidoAux[i]=aux;
		}
		String apellido = new String (apellidoAux);
		System.out.println(apellido);
		System.out.println(file.readInt());
		System.out.println(file.readDouble());
		posicion+=40;
	}
	file.close();
	
}

public Empleado consultaEmpleado(int id) throws IOException {
Empleado en = new Empleado();
	File fichero=new File("empleado.txt");
	RandomAccessFile file= new RandomAccessFile(fichero, "r");
	long posicion=0;
	while(true) {
		file.seek(posicion);
		if(file.getFilePointer()== file.length())
			break;
		

		if(id==file.readInt()) {
			
				en.setId(id);
		
			char[] apellidoAux=new char[12]; 
			char aux;
			for(int i =0; i<apellidoAux.length; i++) {
				aux=file.readChar( );
				apellidoAux[i]=aux;			
			}			
			String apellido = new String (apellidoAux);
		en.setApellidos(apellido);
		en.setDepartamento(file.readInt());
		en.setSalario(file.readDouble());
	
return en;			
		}
		posicion+=40;			
	}
	file.close();	
	return null;	
}



public Double GuardarSaldo(int id) throws IOException {
	File fichero=new File("empleado.txt");
	RandomAccessFile file= new RandomAccessFile(fichero, "r");
	long posicion=0;
	Double saldo;
	while(true) {
		file.seek(posicion);
		if(file.getFilePointer()== file.length())
			break;
		

		if(id==file.readInt()) {
			
					file.seek(posicion+32);
			
		saldo=file.readDouble();

		return saldo;
			}			
		posicion+=40;		
		}
	
	
		return null;
	}
	

public void actualizarsaldo(int id, Double salario) throws IOException {
	File fichero=new File("empleado.txt");
	RandomAccessFile file= new RandomAccessFile(fichero, "rw");
	long posicion=0;
	Double saldo;
	while(true) {
		file.seek(posicion);
		
		
		
		if(file.getFilePointer()== file.length())
			break;
		

		if(id==file.readInt()) {
	
			char[] apellidoAux=new char[12]; 
			char aux;
			for(int i =0; i<apellidoAux.length; i++) {
				aux=file.readChar( );
				apellidoAux[i]=aux;
			}
			String apellido = new String (apellidoAux);
			System.out.println("Apellido:"+apellido);
		file.readInt();
						file.writeDouble(this.GuardarSaldo(id)+salario);
						
						break;
			}			
		posicion+=40;		
		}
	
	
		
}

public void EliminarEmpleado(int id) throws IOException {
	
	File fichero=new File("empleado.txt");
	RandomAccessFile file= new RandomAccessFile(fichero, "rw");
	long posicion=0;

	while(true) {
		
		if(this.comprobarid(id)==true) {
			System.out.println("Ese empleado no se encuentra");break;
		}
		file.seek(posicion);
		
		
		
		if(file.getFilePointer()== file.length())
			break;
	

		if(id==file.readInt()) {
			file.seek(file.getFilePointer()-4);
				file.writeInt(-1);
				StringBuffer buffer = new StringBuffer(Integer.toString(id));
				buffer.setLength(12);
				file.writeChars(buffer.toString());
		
			file.writeInt(0);
			file.writeDouble(0);
			System.out.println("Empleado con id "+id+" ha sido  eliminado");
			
			break;
		}
		
				
		}
	
	file.close();
		posicion+=40;	
}





public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getApellidos() {
	return apellidos;
}

public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}

public int getDepartamento() {
	return departamento;
}

public void setDepartamento(int departamento) {
	this.departamento = departamento;
}

public double getSalario() {
	return salario;
}

public void setSalario(double salario) {
	this.salario = salario;
}

public String toString()
{  
  return "Id: "+id+" Apellido "+apellidos+" Departamento "+departamento+" Salario "+salario;
 }


}

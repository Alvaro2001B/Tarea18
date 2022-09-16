import java.sql.*;
/**
 * TAREA 18 EJERICIO 6
 * @author Alvaro Benitez Carmona
 *
 */
public class MainSQL {
	static String bd = "java";
	static String login = "root";
	static String password = "Alvaro123-456-789";
	static String url = "jdbc:mysql://localhost:3306/" + bd;

	public static void main(String[] args) throws Exception {
// crear conexión
		Connection conn = null;
		try {
//Class.forName("com.mysql.jdbc.Driver");
//conn = DriverManager.getConnection(url, login, password);
			String sURL = url;
			conn = DriverManager.getConnection(sURL, login, password);
			if (conn != null) {
				System.out.println("-Abierta base de datos " + url + " - Ok");
				Statement st1 = conn.createStatement();// Permite comandos SQL
				// Borrar tabla
				st1.executeUpdate("DROP TABLE Suministra");
				st1.executeUpdate("DROP TABLE Piezas");
				st1.executeUpdate("DROP TABLE Proveedores");
				
				System.out.println("-Borrar todas las tablas - Ok");
// Crear tabla piezas
				
				st1.executeUpdate("create table Piezas(\r\n"
						+ "codigo int auto_increment,\r\n"
						+ "nombre nvarchar(100),\r\n"
						+ "primary key(codigo)\r\n"
						+ ");");
				System.out.println("-Creada tabla (piezas) - Ok");

// Crear tabla proveedores
			
				st1.executeUpdate("create table Proveedores(\r\n"
						+ "id char(4),\r\n"
						+ "nombre nvarchar(100),\r\n"
						+ "primary key(id)\r\n"
						+ ");");
				System.out.println("-Creada tabla (proveedores) - Ok");
// Crear tabla suministra
				
				st1.executeUpdate("create table Suministra(\r\n"
						+ "codigoPieza int not null,\r\n"
						+ "idProveedor char(4) not null,\r\n"
						+ "precio int,\r\n"
						+ "primary key(codigoPieza,idProveedor),\r\n"
						+ "foreign key(codigoPieza) references Piezas(codigo),\r\n"
						+ "foreign key(idProveedor) references Proveedores(id)\r\n"
						+ ");");
				System.out.println("-Creada tabla (suministra) - Ok");
				
				// Insertar datos a la tabla piezas				
				st1.executeUpdate("insert into Piezas(nombre) values ('Alvaro');");
				st1.executeUpdate("insert into Piezas(nombre) values ('Asmita');");
				st1.executeUpdate("insert into Piezas(nombre) values ('Andrea');");
				st1.executeUpdate("insert into Piezas(nombre) values ('Lucia');");
				st1.executeUpdate("insert into Piezas(nombre) values ('Joan');");
				// Insertar datos a la tabla proveedores			
				st1.executeUpdate("insert into Proveedores(id,nombre) values(5362,\"Alvaro\");");
				st1.executeUpdate("insert into Proveedores(id,nombre) values(9562,\"Asmita\");");
				st1.executeUpdate("insert into Proveedores(id,nombre) values(6231,\"Andrea\");");
				st1.executeUpdate("insert into Proveedores(id,nombre) values(7458,\"Lucia\");");
				st1.executeUpdate("insert into Proveedores(id,nombre) values(4136,\"Joan\");");
				// Insertar datos a la tabla suministra				
				st1.executeUpdate("insert into Suministra(codigoPieza,idProveedor,precio) values(1,5362,550);");
				st1.executeUpdate("insert into Suministra(codigoPieza,idProveedor,precio) values(2,9562,550);");
				st1.executeUpdate("insert into Suministra(codigoPieza,idProveedor,precio) values(3,6231,550);");
				st1.executeUpdate("insert into Suministra(codigoPieza,idProveedor,precio) values(4,7458,550);");
				st1.executeUpdate("insert into Suministra(codigoPieza,idProveedor,precio) values(5,4136,550);");
				System.out.println("-Añadir registros a la tabla - Ok");
				
				//Consulta de datos 
				
				System.out.println("-Consultar registros piezas:"); 
				ResultSet rs1 = st1.executeQuery("select * from piezas"); 
				int i=0;
				while (rs1.next()) {
					
						  System.out.println( rs1.getString(1) + " " + rs1.getString(2) + " "/* +
						  rs1.getString(3) + " " + rs1.getString(4)+" "+ rs1.getString(5)*/); 
						 }
				System.out.println("-Consultar registros proveedores:"); 
				ResultSet rs2 = st1.executeQuery("select * from proveedores"); 
				while (rs2.next()) {
						  System.out.println( rs2.getString(1) + " " + rs2.getString(2) + " " 
						 /* rs2.getString(3) + " " + rs2.getString(4)+" " +rs1.getString(5)*/); 
						 }
				System.out.println("-Consultar registros suministra:"); 
				ResultSet rs3 = st1.executeQuery("select * from suministra"); 
				while (rs3.next()) {
						  System.out.println( rs3.getString(1) + " " + rs3.getString(2) + " " 
						  /*rs3.getString(3) + " " + rs3.getString(4)+ " "+ rs1.getString(5)*/); 
						 }
				conn.close();
				// Cerrar base de datos 
				System.out.println("-Cerrar base de datos " + url + " - Ok"); 
				
				/*
				 *  // Consulta de datos
				 * System.out.println("-Consultar registros:"); ResultSet rs =
				 * st.executeQuery("select * from contacto"); while (rs.next()) {
				 * System.out.println( rs.getString(1) + " " + rs.getString(2) + " " +
				 * rs.getString(3) + " " + rs.getString(4)); } // Borrar tabla
				 * st.executeUpdate("DROP TABLE contacto");
				 * System.out.println("-Borrar tabla contacto - Ok"); conn.close();// Cerrar
				 * base de datos System.out.println("-Cerrar base de datos " + url + " - Ok"); }
				 */
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

}

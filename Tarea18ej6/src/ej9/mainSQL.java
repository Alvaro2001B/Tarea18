package ej9;

import java.sql.*;

/**
 * TAREA 18 EJERICIO 9
 * @author Alvaro Benitez Carmona
 *
 */
public class mainSQL {
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
				st1.executeUpdate("DROP TABLE if exists reserva");
				st1.executeUpdate("DROP TABLE if exists investigadores");
				st1.executeUpdate("DROP TABLE if exists equipos");
				st1.executeUpdate("DROP TABLE if exists facultad");
				
				System.out.println("-Borrar todas las tablas - Ok");
// Crear tabla facultad
				
				st1.executeUpdate("create table facultad(\r\n"
						+ "codigo int,\r\n"
						+ "nombre nvarchar(100),\r\n"
						+ "primary key(codigo)\r\n"
						+ ");");
				System.out.println("-Creada tabla (piezas) - Ok");

// Crear tabla equipos
			
				st1.executeUpdate("create table equipos(\r\n"
						+ "numSeries char(4),\r\n"
						+ "nombre nvarchar(4),\r\n"
						+ "facultad int,\r\n"
						+ "primary key(numSeries),\r\n"
						+ "foreign key(facultad) references Facultad(codigo)\r\n"
						+ ");");
				System.out.println("-Creada tabla (proveedores) - Ok");
// Crear tabla investigadores
				
				st1.executeUpdate("create table investigadores(\r\n"
						+ "DNI varchar(8),\r\n"
						+ "nomApels nvarchar(255),\r\n"
						+ "facultad int,\r\n"
						+ "primary key (DNI),\r\n"
						+ "foreign key(facultad) references Facultad(codigo)\r\n"
						+ ");");
// Crear tabla reserva
				
				st1.executeUpdate("create table reserva(\r\n"
						+ "DNI varchar(8),\r\n"
						+ "numSeries char(4), \r\n"
						+ "comienzo datetime,\r\n"
						+ "fin datetime,\r\n"
						+ "primary key (DNI,numSeries),\r\n"
						+ "foreign key(DNI) references Investigadores(DNI),\r\n"
						+ "foreign key(numSeries) references Equipos(numSeries)\r\n"
						+ ");");
				System.out.println("-Creada tabla (suministra) - Ok");
				
				// Insertar datos a la tabla facultad				
				st1.executeUpdate("insert into facultad(codigo, nombre) values(255,\"Quimica\");");
				st1.executeUpdate("insert into facultad(codigo, nombre) values(127,\"Mates\");");
				st1.executeUpdate("insert into facultad(codigo, nombre) values(584,\"Programacion\");");
				st1.executeUpdate("insert into facultad(codigo, nombre) values(745,\"Fisica\");");
				st1.executeUpdate("insert into facultad(codigo, nombre) values(632,\"ADE\");");
				// Insertar datos a la tabla investigadores			
				st1.executeUpdate("insert into investigadores(DNI, nomApels,facultad) values (\"48137564\",\"qui255\",\"255\");");
				st1.executeUpdate("insert into investigadores(DNI, nomApels,facultad) values (\"12345685\",\"mat127\",\"127\");");
				st1.executeUpdate("insert into investigadores(DNI, nomApels,facultad) values (\"74586958\",\"pro584\",\"584\");");
				st1.executeUpdate("insert into investigadores(DNI, nomApels,facultad) values (\"41253689\",\"fis745\",\"745\");");
				st1.executeUpdate("insert into investigadores(DNI, nomApels,facultad) values (\"78546924\",\"ade632\",\"632\");");
				// Insertar datos a la tabla equipos				
				st1.executeUpdate("insert into equipos(numSeries,nombre,facultad) values (\"1234\",\"GESS\", 255);");
				st1.executeUpdate("insert into equipos(numSeries,nombre,facultad) values (\"7894\",\"ASEF\", 127);");
				st1.executeUpdate("insert into equipos(numSeries,nombre,facultad) values (\"1596\",\"QWRE\", 584);");
				st1.executeUpdate("insert into equipos(numSeries,nombre,facultad) values (\"7532\",\"ERTE\", 745);");
				st1.executeUpdate("insert into equipos(numSeries,nombre,facultad) values (\"7426\",\"KJGH\", 632);");
				// Insertar datos a la tabla reserva				
				st1.executeUpdate("insert into reserva(DNI,numSeries,comienzo,fin) values (\"48137564\",1234,\"2022-12-25\", \"2022-12-30\");\r\n");
				st1.executeUpdate("insert into reserva(DNI,numSeries,comienzo,fin) values (\"12345685\",7894,\"2022-12-27\",\"2022-12-30\");");
				st1.executeUpdate("insert into reserva(DNI,numSeries,comienzo,fin) values (\"74586958\",1596,\"2022-12-28\",\"2022-12-30\");");
				st1.executeUpdate("insert into reserva(DNI,numSeries,comienzo,fin) values (\"41253689\",7532,\"2022-12-29\",\"2022-12-30\");");
				st1.executeUpdate("insert into reserva(DNI,numSeries,comienzo,fin) values (\"78546924\",7426,\"2022-12-14\",\"2022-12-30\");");
				System.out.println("-Añadir registros a la tabla - Ok");
				
				//Consulta de datos 
				
				System.out.println("-Consultar registros facultad:"); 
				ResultSet rs1 = st1.executeQuery("select * from facultad"); 
				int i=0;
				while (rs1.next()) {
					
						  System.out.println( rs1.getString(1) + " " + rs1.getString(2) + " "/* +
						  rs1.getString(3) + " " + rs1.getString(4)+" "+ rs1.getString(5)*/); 
						 }
				System.out.println("-Consultar registros investigadores:"); 
				ResultSet rs2 = st1.executeQuery("select * from investigadores"); 
				while (rs2.next()) {
						  System.out.println( rs2.getString(1) + " " + rs2.getString(2) + " " +
						  rs2.getString(3) /*+ " " + rs2.getString(4)+" " +rs1.getString(5)*/); 
						 }
				System.out.println("-Consultar registros equipos:"); 
				ResultSet rs3 = st1.executeQuery("select * from equipos"); 
				while (rs3.next()) {
						  System.out.println( rs3.getString(1) + " " + rs3.getString(2) + " " +
						  rs3.getString(3)/* + " " + rs3.getString(4)+ " "+ rs1.getString(5)*/); 
						 }
				System.out.println("-Consultar registros reserva:"); 
				ResultSet rs4 = st1.executeQuery("select * from reserva"); 
				while (rs4.next()) {
						  System.out.println( rs4.getString(1) + " " + rs4.getString(2) + " " +
						  rs4.getString(3) + " " + rs4.getString(4)/*+ " "+ rs1.getString(5)*/); 
						 }
				conn.close();
				// Cerrar base de datos 
				System.out.println("-Cerrar base de datos " + url + " - Ok"); 
				
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

}

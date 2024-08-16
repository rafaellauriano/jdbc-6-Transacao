package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import db.DB;
import db.DbException;

public class Programa {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int linha1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2000 WHERE DepartmentId = 1");
			
			//int x = 1;
			//if(x < 2) {
			//	throw new SQLException("Erro falso");
			//}
			
			int linha2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3000 WHERE DepartmentId = 2");
			
			conn.commit();
			
			System.out.println("Linha 1 " + linha1);
			System.out.println("Linha 2 " + linha2);
			
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transação não realizada, voltada por: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro ao vltar a trasação! causada por: " + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}

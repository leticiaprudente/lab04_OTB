package main.java.lab04_garcia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws SQLException {
		hardcoded();
		softcoded();
	}
	
	public static void hardcoded() throws SQLException {
		Connection con = Conexao.getConnection(); 
		Long tempoInicio = System.currentTimeMillis() ;
 		String query ; 
		Statement s = con.createStatement() ; 
		ResultSet r ;
		Long cont ;
		String data_hora; 
		
		for (cont = (long) 1 ; cont <= (long) 100000 ; cont++) { 
			query="select data_hora from lab04_otb where id=" + Long.toString(cont) ; 
			r = s.executeQuery(query) ;
			
			if(r.next()) {
				data_hora = r.getString(1) ;
			}
			 
			
			r.close() ;
		}
		s.close() ;
		
		Long tempoFim = System.currentTimeMillis() ;
		Long tempoExec = tempoFim - tempoInicio;
		
		String tempoTotal = String.format("%d min, %d seg", 
			    TimeUnit.MILLISECONDS.toMinutes(tempoExec),
			    TimeUnit.MILLISECONDS.toSeconds(tempoExec) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tempoExec))
			);

		System.out.println("Tempo execucao - Hardcoded: " +tempoTotal);
		
		
		
	}
	
	public static void softcoded() throws SQLException {
		Connection con = Conexao.getConnection(); 
		Long tempoInicio = System.currentTimeMillis() ;
		Long cont ;
		String data_hora ; 
		PreparedStatement s = con.prepareStatement("select data_hora from lab04_otb where id = ?") ;
		ResultSet r ; 
		
		for(cont = (long) 1 ; cont <= (long) 100000 ; cont++) { 
			s.setLong(1, cont);
			r = s.executeQuery();
			if(r.next()) {
				data_hora = r.getString(1) ; 
				r.close();
			}
		}
		s.close() ;
		Long tempoFim = System.currentTimeMillis() ;
		Long tempoExec = tempoFim - tempoInicio;
		
		String tempoTotal = String.format("%d min, %d seg", 
			    TimeUnit.MILLISECONDS.toMinutes(tempoExec),
			    TimeUnit.MILLISECONDS.toSeconds(tempoExec) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tempoExec))
			);

		System.out.println("Tempo execucao - Softcoded: " +tempoTotal);
	}

}

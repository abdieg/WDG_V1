package org.webdriver.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseUtility {

	String db_driver, db_url, db_user, db_password;
	
	PrintStream stdout = new PrintStream(new FileOutputStream(FileDescriptor.out));

	public DatabaseUtility(Properties databaseConnectionProperties) {
		this.db_driver = databaseConnectionProperties.getDbDriver();
		this.db_url = databaseConnectionProperties.getDbUrl();
		this.db_user = databaseConnectionProperties.getDbUserName();
		this.db_password = databaseConnectionProperties.getDbPassword();
	}
	
	public Connection getConnection(String db_driver, String db_url, String db_user, String db_password) throws ClassNotFoundException, SQLException {
		Class.forName(db_driver);
		return DriverManager.getConnection(db_url, db_user, db_password);
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = this.getConnection(db_driver, db_url, db_user, db_password);
		return conn;
	}
	
//	public void closeQuietly(Connection conn, Statement stmt, ResultSet rs) {
//		try{
//            if(rs != null) {
//            	rs.close();
//            }
//			if(stmt != null) {
//            	stmt.close();
//            }
//			if(conn != null) {
//				conn.close();
//			}
//		}catch(SQLException ex) {
//			stdout.println(ex);
//		}
//	}	
	
	public void closeQuietly(Connection conn, Statement stmt) {
		try{
            if(stmt != null) {
            	stmt.close();
            }
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException ex) {
			stdout.println(ex);
		}
	}
	
	/**
	 * Used when UPDATE, DELETE, INSERT
	 * EXAMPLE: String query = "UPDATE users SET username='john' WHERE id_users='2';";
	 * EXAMPLE: executeQuery(query);
	 * @param query
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void executeQuery(String query) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			stdout.println(e);
		} finally {
			this.closeQuietly(conn, stmt);
		}
	}
	
	/**
	 * Opens a file to extact the queries that exist in there
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String[] getSQLQueries(String path) throws IOException {
		FileReader fr = new FileReader(new File(path));
		BufferedReader br = new BufferedReader(fr);

		StringBuffer sb = new StringBuffer();
		String s = new String();

		while ((s = br.readLine()) != null) {
			if (s.trim().startsWith("--")) {
				continue;
			}
			sb.append(s);
			sb.append("\n");
		}
		br.close();

		return sb.toString().split(";");
	}

	/**
	 * Execute each query
	 * @param conn
	 * @param stmt
	 * @param sqlQueries
	 * @throws SQLException
	 */
	public void executeSQLQueries(Connection conn, Statement stmt, String[] sqlQueries) throws SQLException {
		for (String sqlQuery : sqlQueries) {
			if (!sqlQuery.trim().equals("")) {
				stmt.execute(sqlQuery);
			}
		}
	}
	
	/**
	 * Execute all queries inside a file
	 * EXAMPLE: executeQueries("src/test/resources/sql/dropConstraints.sql")
	 * @param queryFilePath
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void executeQueries(String queryFilePath) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			String[] query = this.getSQLQueries(queryFilePath);
			executeSQLQueries(conn, stmt, query);
		} catch (Exception ex) {
			stdout.println(ex);
		} finally {
			this.closeQuietly(conn, stmt);
		}
	}
	
//	public String getSQLProcedure(String path) throws IOException {
//		FileReader fr = new FileReader(new File(path));
//		BufferedReader br = new BufferedReader(fr);
//
//		StringBuffer sb = new StringBuffer();
//		String s = new String();
//		while ((s = br.readLine()) != null) {
//			sb.append(s);
//			sb.append("\n");
//		}
//		br.close();
//		return sb.toString();
//	}
	
	/**
	 * Used when SELECT
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ResultSet executeQueryWithResultSet(String query) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			stdout.println(e);
		} finally {
			this.closeQuietly(conn, stmt);
		}
		return rs;
	}
	
	/**
	 * Returns the result of a SELECT query in an ArrayList
	 * @param query
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<String> getSelectQueryValues(String query) throws ClassNotFoundException, SQLException {
		ResultSet resultset = executeQueryWithResultSet(query);
		ArrayList<String> arrayList = new ArrayList<String>();
		
		ResultSetMetaData metadata = resultset.getMetaData();
		int numberOfColumns = metadata.getColumnCount();
		
		while (resultset.next()) {              
	        int i = 1;
	        while(i <= numberOfColumns) {
	            arrayList.add(resultset.getString(i++));
	        }
		}
		return arrayList;
	}
}

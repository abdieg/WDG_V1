package org.application.common;

import java.sql.SQLException;

import org.webdriver.common.DatabaseUtility;
import org.webdriver.common.Properties;

public class ApplicationDatabase extends DatabaseUtility {
	
	public ApplicationDatabase(Properties databaseConnectionProperties) {
		super(databaseConnectionProperties);
	}
	
	public void nose() throws SQLException, ClassNotFoundException {
		String query = "UPDATE users SET username='pilarica' WHERE id_users='2';";
		executeQuery(query);
	}
	
	public void nel() throws ClassNotFoundException, SQLException {
//		String query = "SELECT * FROM users WHERE id_users='1';";
		String query = "SELECT * FROM users;";
		getSelectQueryValues(query);
		
		System.out.println(getSelectQueryValues(query));
	}
	
//	/**
//	 * EXAMPLE FOR EXECUTEQUERY WITH A SQLPROCEDURE
//	 * @throws Exception
//	 */
//	public void updateEditWorklogChanges() throws Exception {
//		stdout.println("Adding editWorklog BA as child to ListAllSubjects");
//		String query = this.getSQLProcedure("src/test/resources/sql/editWorklogUpdationScript.sql");
//		executeQuery(query);
//	}
//	/**
//	 * EXAMPLE FOR SQLPROCEDURE
//	 * @throws Exception
//	 */
//	public void addUserToLMicroGroup() throws Exception {
//		Connection conn = this.getConnection(driver, url, userName, password);
//		Statement stmt = conn.createStatement();
//		try {
//			stdout.println("Adding user to LMicro group");
//			String query = this.getSQLProcedure("src/test/resources/sql/add_user_to_lmicroBillingGroup.sql");
//			stmt.executeUpdate(query);
//		} catch(Exception ex) {
//			stdout.print("Error occured when Adding user to LMicro group: " + ex.getMessage());
//			stdout.print(ex);
//		} finally {
//			stdout.println("Finished Adding user to LMicro group");
//			stmt.close();
//			conn.close();
//		}
//	}
//	
//	/**
//	 * Example for EXECUTESQLQUIERIES
//	 * @throws Exception
//	 */
//	public void revertModifySharedResourceForLMicro() throws Exception {
//		Connection conn = this.getConnection(driver, url, userName, password);
//		Statement stmt = conn.createStatement();
//		try {
//			stdout.println("Revert modifying Shared Resource for LMicro Billing");
//			String[] query = this.getSQLQueries("src/test/resources/sql/revertModifySharedResourceForLMicro.sql");
//			executeSQLQueries(conn, stmt, query);
//		} catch(Exception ex) {
//			stdout.print("Error occured when reverting Shared Resource modification for LMicro Billing: " + ex.getMessage());
//			stdout.print(ex);
//		} finally {
//			stdout.println("Finished reverting Shared Resource modification for LMicro Billing");
//			stmt.close();
//			conn.close();
//		}
//	}
//	
//	public Long getCurrentServiceId(String serviceName) throws Exception {
//		Long serviceId = 0L;
//		Connection conn = null;
//		Statement stmt = null;
//		String query = "select service_id from services where service_name='" + serviceName + "'";
//		ResultSet rset = this.executeQueryWithResultSet(query, conn, stmt);
//		while (rset.next()) {
//			serviceId = rset.getLong(1);
//		}
//		this.closeQuietly(conn, stmt, rset);
//
//		return serviceId;
//	}
//
//	public Long getTotalUsers() throws Exception {
//		Long userCount = 0L;
//		Connection conn = null;
//		Statement stmt = null;
//		String query = "select count(*) from users";
//		ResultSet rset = this.executeQueryWithResultSet(query, conn, stmt);
//		while (rset.next()) {
//			userCount = rset.getLong(1);
//		}
//		this.closeQuietly(conn, stmt, rset);
//		return userCount;
//	}
//
//	public String getStringValue(String subjectId) throws ClassNotFoundException, SQLException {
//       Connection conn = null;
//       Statement stmt = null;
//		String query = "select stringvalue from view_eav_objects where "
//				+ "eav_object_id=(select eav_object_id from subjects where subject_id= "
//				+ subjectId
//				+ " ) and "
//				+ "meta_attribute_id=(select meta_attribute_id from meta_attributes where meta_attribute_name='calendarId')";
//		ResultSet rs = executeQueryWithResultSet(query, conn, stmt);
//		rs.next();
//		String srtValue = rs.getString(1);
//		closeQuietly(conn, stmt, rs);
//		return srtValue;
//	}


}

package pl.calinotheca.service;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class RequestExecutorService {

	// connection settings
	private final String URL = "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl";
	private final String USER = "jkalino1";
	private final String PASS = "jkalino1";
	private Statement statement = null;

	public int execSqlRequest(String query) {
		// create a connection and execute the query
		try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
			statement = connection.createStatement();
			statement.execute(query);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		try {
			if (statement != null)
				statement.close();
			return 0; // if success
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return 1; // if error
		}
	}

	public Long execSqlRequest(String query, String mode) {

		Long result = 0L;

		// create a connection and execute the query
		try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
			statement = connection.createStatement();
			connection.getAutoCommit();

			ResultSet resultSet = statement.executeQuery(query);
			if (mode.equals("R")) {
				while (resultSet.next()) {
					System.out.println(resultSet.getString(1) + ". "
							+ (resultSet.getMetaData().getColumnCount() >= 2 ? resultSet.getString(2) : "") + " "
							+ (resultSet.getMetaData().getColumnCount() >= 3 ? resultSet.getString(3) : ""));
				}
			} else if (mode.equals("C")) {
				while (resultSet.next()) {
					result = resultSet.getLong(1);
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();

			}
		}

		return result;
	}
}
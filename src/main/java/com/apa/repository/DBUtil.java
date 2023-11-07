package com.apa.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	private static Connection conn;

	public static Connection open() {
		String url = "jdbc:oracle:this:@localhost:1521:xe";
		String id = "eeunha";
		String pw = "java1234";

		try {
			Class.forName("oracle.jdbc.drivaer.OracleDriver");

			conn = DriverManager.getConnection(url, id, pw);

			return conn;
		} catch (Exception e) {
			System.out.println("DBUtil.open()");
			e.printStackTrace();
		}

		return null;
	}
}

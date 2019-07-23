import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleInsert {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "jbedu";
		String password = "jbedu1234";

		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle connection success.\n");
			Statement stmt = conn.createStatement();

			String sql = "SELECT max(deptno) from dept";
			ResultSet rs = stmt.executeQuery(sql);
			String maxnum = "";
			while (rs.next()) {
				maxnum = rs.getString("max(deptno)");
			}
			System.out.println(Integer.parseInt(maxnum) + 10);

			int ideptno = Integer.parseInt(maxnum) + 10;
			String sdname = "IT", sloc = "SEOUL";
			String sql2 = "INSERT INTO dept VALUES ('" + ideptno + "','" + sdname + "','" + sloc + "')";
			boolean b = stmt.execute(sql2);
			if (!b) {
				System.out.println("Insert success.\n");
			} else {
				System.out.println("Insert fail.\n");
			}

			String sql3 = "SELECT * FROM dept";
			ResultSet rs2 = stmt.executeQuery(sql3);
			while (rs2.next()) {
				System.out.print(rs2.getString("deptno") + " ");
				System.out.print(rs2.getString("dname") + " ");
				System.out.println(rs2.getString("loc") + " ");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver loading fail.");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}

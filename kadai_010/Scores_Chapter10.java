package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		try {
			//データベースに接続する
			con = DriverManager.getConnection(
				"jdbc:mysql://localhost/challenge_java",
				"root",
				"saayalove16"
				);
			
			System.out.println("データベース接続成功:" + con );
			
			//SQLクエリを準備する
			statement = con.createStatement();
			String sqlupdate = "UPDATE score SET score_math = 95, score_english = 80 WHERE id = 5;";
			
			//点数データを更新する
			System.out.println("レコード更新を実行します");
			int rowCnt = statement.executeUpdate(sqlupdate);
			System.out.println( rowCnt + "件のレコードが更新されました");
			
			//点数順に並べる
			 
			String sqlselect = "SELECT * FROM score ORDER BY "
					+ "score_math + score_english DESC,"
					+ "score_math DESC,"
					+ "score_english DESC";
			System.out.println("数学・英語の点数が高い順に並べ替えました");
			
			//並べ替え結果を表示する
			ResultSet result = statement.executeQuery(sqlselect);
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int score_math = result.getInt("score_math");
				int score_english = result.getInt("score_english");
				System.out.println(result.getRow() + "件目:ID=" + id + "／氏名=" + name + "／数学=" + score_math + "／英語=" + score_english);
			} 
		}catch(SQLException e) {
	            System.out.println("エラー発生：" + e.getMessage());
	        } finally {
	            if( statement != null ) {
	                try { statement.close(); } catch(SQLException ignore) {}
	            }
	            if( con != null ) {
	                try { con.close(); } catch(SQLException ignore) {}
	            }
	        }
	

	}

}

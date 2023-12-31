package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean;

/**
 * itemテーブルにアクセスするDAOクラス
 */
public class ItemDAO {

	/**
	 * クラス定数
	 */
	// データベース接続情報文字列定数群
	private static final String DB_DRIVER   = "org.postgresql.Driver";
	private static final String DB_URL      = "jdbc:postgresql:sample";
	private static final String DB_USER     = "student";
	private static final String DB_PASSWORD = "himitu";
	
	/**
	 * フィールド：データベース接続オブジェクト
	 */
	private Connection conn;
	
	/**
	 * コンストラクタ
	 * @throws DAOException 
	 */
	public ItemDAO() throws DAOException {
		try {
			Class.forName(DB_DRIVER);
			this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DAOException("JDBCドライバの読込みに失敗しました。");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("データベースへの接続に失敗しました。");
		}
	}

	/**
	 * 全商品を取得する
	 * @return List<ItemBean> 全商品のリスト
	 * @throws DAOException
	 */
	public List<ItemBean> findAll() throws DAOException {
		// 実行するSQLを設定
		String sql = "SELECT * FROM item ORDER BY code";
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);
			 // SQLの実行と結果セットの取得
			 ResultSet rs = pstmt.executeQuery();
			) {
			
			// 結果セットから商品リストへの詰替え
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				// 取得したフィールドから商品クラスをインスタンス化
				ItemBean bean = new ItemBean();
				bean.setCode(rs.getInt("code"));
				bean.setCategoryCode(rs.getInt("category_code"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getInt("price"));
				// 商品リストに追加
				list.add(bean);
			}
			// 商品リストを返却
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		
	}

	/**
	 * 商品番号で商品を取得する
	 * @param code 商品番号
	 * @return ItemBean 商品のインスタンス
	 * @throws DAOException
	 */
	public ItemBean findByCode(int code) throws DAOException {
		// 実行するSQLを設定
		String sql = "SELECT * FROM item WHERE code = ?";
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// プレースホルダにデータをバインド
			pstmt.setInt(1, code);
			try (// SQLの実行と結果セットの取得
				 ResultSet rs = pstmt.executeQuery();) {
				// 結果セットから商品のインスタンスを取得
				ItemBean bean = new ItemBean();
				if (rs.next()) {
					bean.setCode(code);
					bean.setCategoryCode(rs.getInt("category_code"));
					bean.setName(rs.getString("name"));
					bean.setPrice(rs.getInt("price"));
				}
				return bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}
	
	/**
	 * 新規の商品を登録する
	 * @param categoryCode カテゴリーコード
	 * @param name         商品名
	 * @param price        価格
	 * @throws DAOException
	 */
	public void add(int categoryCode, String name, int price) throws DAOException {
		// 実行するSQLの設定
		String sql = "INSERT INTO item (category_code, name, price) VALUES (?, ?, ?)";
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// プレースホルダにデータをバインド
			pstmt.setInt(1, categoryCode);
			pstmt.setString(2, name);
			pstmt.setInt(3, price);
			// SQLの実行
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
		
	}

	/**
	 * 商品を更新する
	 * @param code         商品番号
	 * @param categoryCode カテゴリーコード
	 * @param name         商品名
	 * @param price        価格
	 * @throws DAOException 
	 */
	public void update(int code, int categoryCode, String name, int price) throws DAOException {
		// 実行するSQLを設定
		String sql = "UPDATE item SET category_code = ?, name = ?, price = ? WHERE code = ?";
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// プレースホルダにデータをバインド
			pstmt.setInt(1, categoryCode);
			pstmt.setString(2, name);
			pstmt.setInt(3, price);
			pstmt.setInt(4, code);
			// SQLを実行
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードのっ更新に失敗しました。");
		}
	}

	/**
	 * 指定された商品番号の商品を削除する
	 * @param code 削除対象商品の商品番号
	 * @throws DAOException
	 */
	public void delete(int code) throws DAOException {
		// 実行するSQLの設定
		String sql = "DELETE FROM item WHERE code = ?";
		try (// SQL実行オブジェクトを取得
			 PreparedStatement pstmt = this.conn.prepareStatement(sql);) {
			// プレースホルダにデータをバインド
			pstmt.setInt(1, code);
			// SQLの実行
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの削除に失敗しました。");
		}
		
	}

}

package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードの設定
		request.setCharacterEncoding("utf-8");
		// 処理分岐用パラメータactionキーを取得
		String action = request.getParameter("action");
		// 取得したactionキーによって処理を分岐
		if (action == null || action.isEmpty()) {
			// アクションキーが送信されていない場合：全商品の検索結果を表示
			ItemDAO dao;
			try {
				dao = new ItemDAO();
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				this.gotoPage(request, response, "/list.jsp");
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("messagae", "内部エラーが発生しました。");
				this.gotoPage(request, response, "/error.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 指定されたURLに遷移する
	 * @param request  HttpServletRequest
	 * @param response HttpServletResponse
	 * @param nextURL  遷移先URL
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String nextURL) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextURL);
		dispatcher.forward(request, response);
	}

}

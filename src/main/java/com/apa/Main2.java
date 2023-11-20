/*
 * package com.apa;
 * 
 * import java.io.IOException; import java.io.PrintWriter;
 * 
 * import javax.servlet.RequestDispatcher; import
 * javax.servlet.ServletException; import javax.servlet.annotation.WebServlet;
 * import javax.servlet.http.HttpServlet; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import com.apa.pharmacy.model.PharmacyDTO; import
 * com.apa.pharmacy.repository.DispenseDAO;
 * 
 * @WebServlet("/main2.do") public class Main2 extends HttpServlet {
 * 
 * @Override protected void doGet(HttpServletRequest req, HttpServletResponse
 * resp) throws ServletException, IOException {
 * 
 * RequestDispatcher dispatcher =
 * req.getRequestDispatcher("/WEB-INF/views/pharmacy/info/view.jsp");
 * dispatcher.forward(req, resp); } protected void doPost(HttpServletRequest
 * req, HttpServletResponse resp) throws ServletException, IOException {
 * 
 * String pharmacyid = req.getParameter("id"); String pharmacypw =
 * req.getParameter("pw");
 * 
 * DispenseDAO dao = new DispenseDAO();
 * 
 * PharmacyDTO dto = new PharmacyDTO(); dto.setPharmacyId(pharmacyid);
 * dto.setPharmacyPw(pharmacypw);
 * 
 * PharmacyDTO result = dao.login(dto);
 * 
 * if(result != null){ req.getSession().setAttribute("id", pharmacyid);
 * req.getSession().setAttribute("Name", result.getPharmacyName());
 * //req.getSession().setAttribute("lv", "약국");
 * 
 * resp.sendRedirect("/apa/main2.do"); }else{ PrintWriter writer =
 * resp.getWriter();
 * writer.print("<script>alert('failed');history.back();</script>");
 * writer.close(); } }
 * 
 * }
 */
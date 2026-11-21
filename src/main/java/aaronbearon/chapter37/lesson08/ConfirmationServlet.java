package aaronbearon.chapter37.lesson08;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/confirmation")
public class ConfirmationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\"");
        out.println("<head><title>Saved Registration</title></head>");
        out.println("<body>");

        if (session == null || session.getAttribute("studentName") == null) {
            out.println("<h1>No Registration Found</h1>");
            out.println("<p>Please register first!</p>");
        } else {
            String studentName = (String) session.getAttribute("studentName");
            String studentID = (String) session.getAttribute("studentID");
            String course = (String) session.getAttribute("course");
            out.println("<h1>Saves Registration</h1>");
            out.println("<p>Student Name: " + studentName + "</p>");
            out.println("<p>Student ID: " + studentID + "</p>");
            out.println("<p>Student Name: " + course + "</p>");
        }

        out.println("</body> </html>");
    }
}

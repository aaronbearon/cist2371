package aaronbearon.chapter37.lesson08;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<DOCTYPE html>\n" +
                "<html lang=\"en\"\n" +
                "<head>\n" +
                "    <title>Course Registration</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Student Course Registration</h1>\n" +
                "\n" +
                "    <form action=\"register\" method=\"post\">\n" +
                "\n" +
                "        <label>Student Name:</label>\n" +
                "        <input type=\"text\" name=\"studentName\" required>\n" +
                "        <br><br>\n" +
                "        <label>Student ID:</label>\n" +
                "        <input type=\"text\" name=\"studentID\" required>\n" +
                "        <br><br>\n" +
                "        <label>Select a Course:</label>\n" +
                "        <select name=\"course\">\n" +
                "            <option value=\"Java Programming III\">Java Programming III</option>\n" +
                "            <option value=\"Database Programming\">Database Programming</option>\n" +
                "            <option value=\"Web Development\">Web Development</option>\n" +
                "        </select>\n" +
                "        <br><br>\n" +
                "        <button type=\"submit\">Register</button>\n" +
                "\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>\n");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String studentName = request.getParameter("studentName");
        String studentId = request.getParameter("studentID");
        String course = request.getParameter("course");

        HttpSession session = request.getSession();

        session.setAttribute("studentName", studentName);
        session.setAttribute("studentID", studentId);
        session.setAttribute("course", course);

        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<DOCTYPE html>\n" +
                "<html lang=\"en\"\n" +
                "<head>\n" +
                "    <title>Registration Confirmation</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Registration Successful</h1>\n" +
                "\n" +
                "    <p>Student Name: " +
                studentName +
                "    </p>" +
                "\n" +
                "    <p>Student ID: " +
                studentId +
                "    </p>" +
                "\n" +
                "    <p>Course: " +
                course +
                "    </p>" +
                "\n" +
                "    <form action=\"confirmation\" method=\"get\">\n" +
                "\n" +
                "        <button type=\"submit\">View Saves Registration</button>\n" +
                "\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>\n");
    }
}

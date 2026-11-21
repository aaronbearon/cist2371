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

        out.println("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <title>Course Registration</title>
                </head>
                <body>
                    <h1>Student Course Registration</h1>
                
                    <form action="register" method="post">
                
                        <label>Student Name:</label>
                        <input type="text" name="studentName" required>
                        <br><br>
                        <label>Student ID:</label>
                        <input type="text" name="studentID" required>
                        <br><br>
                        <label>Select a Course:</label>
                        <select name="course">
                            <option value="Java Programming III">Java Programming III</option>
                            <option value="Database Programming">Database Programming</option>
                            <option value="Web Development">Web Development</option>
                        </select>
                        <br><br>
                        <button type="submit">Register</button>
                
                    </form>
                </body>
                </html>
                """);
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
        out.println("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <title>Registration Confirmation</title>
                </head>
                <body>
                    <h1>Registration Successful</h1>

                    <p>Student Name: %s</p>
                    <p>Student ID: %s</p>
                    <p>Course: %s</p>

                    <form action="confirmation" method="get">

                        <button type="submit">View Saved Registration</button>

                    </form>
                </body>
                </html>
                """.formatted(studentName, studentId, course));
    }
}

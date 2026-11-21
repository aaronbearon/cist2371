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

        String content;
        if (session == null || session.getAttribute("studentName") == null) {
            content = """
                    <h1>No Registration Found</h1>
                    <p>Please register first!</p>
                    """;
        } else {
            String studentName = (String) session.getAttribute("studentName");
            String studentID = (String) session.getAttribute("studentID");
            String course = (String) session.getAttribute("course");
            content = """
                    <h1>Saved Registration</h1>
                    <p>Student Name: %s</p>
                    <p>Student ID: %s</p>
                    <p>Course: %s</p>
                    """.formatted(studentName, studentID, course);
        }

        out.println("""
                <!DOCTYPE html>
                <html lang="en">
                <head><title>Saved Registration</title></head>
                <body>
                %s
                </body>
                </html>
                """.formatted(content));
    }
}

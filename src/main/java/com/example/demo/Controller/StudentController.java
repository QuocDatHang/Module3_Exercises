package com.example.demo.Controller;

import com.example.demo.Enum.EGender;
import com.example.demo.Models.Student;
import com.example.demo.Service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends HttpServlet {
    private static StudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreatePage(req, resp);
                break;
            case "edit":
                showEditPage(req, resp);
            default:
                showList(req, resp);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                create(req, resp);
                break;
            case "edit":
                edit(req, resp);
        }

    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("gender");
        studentService.addStudent(name, dob, gender);
        resp.sendRedirect("/student?message=Created");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String dob = req.getParameter("dob");
        String gender = req.getParameter("gender");
        studentService.updateStudent(id, name, dob, gender);
        resp.sendRedirect("/student?message=Edited");
    }

    private void showCreatePage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        req.setAttribute("genders", EGender.values());
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    private void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        req.setAttribute("genders", EGender.values());
        int id = Integer.parseInt(req.getParameter("id"));
        Student st1 = studentService.findStudentById(id);
        req.setAttribute("StudentEdit", st1);
        req.getRequestDispatcher("edit.jsp").forward(req, resp);
    }

    protected void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", studentService.getStudents());
        req.setAttribute("message", req.getParameter("message"));
        req.getRequestDispatcher("student.jsp").forward(req, resp);
    }
}

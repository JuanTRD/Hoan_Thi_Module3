package Controller;

import Model.Department;
import Model.Staff;
import Service.DepartmentService;
import Service.StaffService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "staffController", value = "/staff")
public class StaffController extends HttpServlet {
    private StaffService staffService = new StaffService();
    private DepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "home":
                showHomeForm(req, resp);
                break;
            case "add":
                showAddForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteStaff(req, resp);
                break;
            case "searchName":
                showSearchByName(req, resp);
                break;
        }
    }

    private void showSearchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputName = req.getParameter("inputName");
        List<Staff> searchStaffList = staffService.searchByName(inputName);
        System.out.println(searchStaffList.size());
        req.setAttribute("staffList", searchStaffList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Staff/searchByName.jsp");
        dispatcher.forward(req, resp);
    }

    private void deleteStaff(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idDelete = Integer.parseInt(req.getParameter("idDelete"));
        staffService.deleteStaff(idDelete);
        resp.sendRedirect("http://localhost:8080/staff?action=home");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEdit = Integer.parseInt(req.getParameter("idEdit"));
        req.setAttribute("idEdit", idEdit);
        Staff staffEdit = staffService.findById(idEdit);
        req.setAttribute("staffEdit", staffEdit);
        List<Department> list = this.departmentService.viewAll();
        req.setAttribute("list",list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Staff/edit.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> list = this.departmentService.viewAll();
        req.setAttribute("list",list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Staff/add.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHomeForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Staff> staffList = staffService.viewAll();
        req.setAttribute("staffList", staffList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Staff/home.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addStaffForm(req, resp);
                break;
            case "edit":
                editStaffForm(req, resp);
                break;
        }
    }

    private void editStaffForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int idDepartment = Integer.parseInt(req.getParameter("idDepartment"));
        Department department = new Department(idDepartment);
        Staff newStaff = new Staff(name, email, address, phone, salary, department);
        staffService.editStaff(id, newStaff);
        resp.sendRedirect("/staff?action=home");
    }

    private void addStaffForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int idDepartment = Integer.parseInt(req.getParameter("idDepartment"));
        Department department = new Department(idDepartment);
        Staff newStaff = new Staff(name, email, address, phone, salary, department);
        staffService.addStaff(newStaff);
        resp.sendRedirect("/staff?action=home");
    }

}

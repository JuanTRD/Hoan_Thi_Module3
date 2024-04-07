package Service;

import Model.Department;
import Model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffService {
    public StaffService(){};
    private DepartmentService departmentService = new DepartmentService();
    private Connection connection = ConnectToMySQL.getConnection();
    public List<Staff> viewAll(){
        String sql = "select staff.*,d.name as 'nameDepartment' from staff join department d on d.id = staff.idDepartment order by id asc;";
        List<Staff> staffs = new ArrayList<Staff>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                double salary = resultSet.getDouble("salary");
                int idDepartment = resultSet.getInt("idDepartment");
                String nameDepartment = resultSet.getString("nameDepartment");
                Department department = new Department(idDepartment, nameDepartment);
                Staff staff = new Staff(id,name,email,address,phone,salary,department);
                staffs.add(staff);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staffs;
    }
    public void addStaff(Staff staff){
        String sql = "insert into staff (name, email, address, phone, salary, idDepartment) values (?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,staff.getName());
            preparedStatement.setString(2,staff.getEmail());
            preparedStatement.setString(3,staff.getAddress());
            preparedStatement.setString(4,staff.getPhone());
            preparedStatement.setDouble(5,staff.getSalary());
            preparedStatement.setInt(6,staff.getDepartment().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void editStaff(int id,Staff staff){
        String sql = "update staff set name = ?, email = ?, address = ?, phone = ?, salary = ?, idDepartment = ? where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getEmail());
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, staff.getPhone());
            preparedStatement.setDouble(5, staff.getSalary());
            preparedStatement.setInt(6, staff.getDepartment().getId());
            preparedStatement.setInt(7,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteStaff(int id){
        String sql = "delete from staff where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Staff findById(int id) {
        String sql = "select staff.*, d.name as 'nameDepartment' from staff join department d on staff.idDepartment = d.id where staff.id = ?;";
        Staff staff = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                double salary = resultSet.getDouble("salary");
                int idDepartment = resultSet.getInt("idDepartment");
                String nameDepartment = resultSet.getString("nameDepartment");
                Department department = new Department(idDepartment, nameDepartment);
                staff = new Staff(id,name,email,address,phone,salary,department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staff;
    }
    public List<Staff> searchByName(String str) {
        List<Staff> list = new ArrayList<Staff>();
        String sql = "select staff.*, d.name as 'nameDepartment' from staff join department d on staff.idDepartment = d.id where staff.name like ? order by id asc;";
        String str2 = "%" + str + "%";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, str2);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                double salary = resultSet.getDouble("salary");
                int idDepartment = resultSet.getInt("idDepartment");
                String nameDepartment = resultSet.getString("nameDepartment");
                Department department = new Department(idDepartment, nameDepartment);
                Staff staff = new Staff(id, name, email, address, phone, salary, department);
                list.add(staff);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}

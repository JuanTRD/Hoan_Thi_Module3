package Service;

import Model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    private Connection connection = ConnectToMySQL.getConnection();
    private List<Department> departments = new ArrayList<>();
    public DepartmentService(){};
    public List<Department> viewAll(){
        String sql = "SELECT * FROM department;";
        List<Department> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Department newDepartment = new Department(id, name);
                list.add(newDepartment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public void addDepartment(Department department){
        departments.add(department);
    }
    public void removeDepartment(int id){
        int index = findIndexById(id);
        departments.remove(index);
    }
    public void editDepartment(int id,Department department){
        int index = findIndexById(id);
        departments.set(index, department);
    }
    public int findIndexById(int id) {
        for(int i = 0; i < departments.size(); i++){
            if(departments.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

}

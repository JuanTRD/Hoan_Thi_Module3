<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/5/2024
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm nhân viên</title>
    <style>
        input {
            width: 200px;
            height: 30px;
        }
        button{
            width: 100px;
            height: 30px;
        }
        select{
            width: 200px;
            height: 30px;
        }
    </style>
</head>
<body>
Thêm nhân viên
<form method="post" action="http://localhost:8080/staff?action=add">
    <h4>Name</h4>
    <input type="text" name="name" placeholder="NAME"></input>
    <h4>Email</h4>
    <input type="text" name="email" placeholder="EMAIL"></input>
    <h4>Address</h4>
    <input type="text" name="address" placeholder="ADDRESS"></input>
    <h4>Phone Number</h4>
    <input type="text" name="phone" placeholder="PHONE"></input>
    <h4>Salary</h4>
    <input type="number" name="salary" placeholder="SALARY"></input>
    <h4>Department</h4>
    <select name="idDepartment">
        <c:forEach var="item" items="${list}">
            <option value="${item.id}">${item.name}</option>
        </c:forEach>
    </select><br>
    <br>
    <button>Submit</button>
</form>
</body>
</html>

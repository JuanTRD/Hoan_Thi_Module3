<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/5/2024
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thay đổi</title>
    <style>
        input {
            width: 200px;
            height: 30px;
        }
        select{
            width: 200px;
            height: 30px;
        }
        button{
            width: 200px;
            height: 30px;
        }
    </style>
</head>
<body>
<h1>Thay đổi nhân viên ${idEdit} </h1>
<form method="post" action="http://localhost:8080/staff?action=edit">
    <h4>ID</h4>
    <input type="number" name="id" placeholder="ID" value="${idEdit}" readonly></input>
    <h4>Name</h4>
    <input type="text" name="name" placeholder="NAME" value="${staffEdit.name}"></input>
    <h4>Email</h4>
    <input type="text" name="email" placeholder="EMAIL@EMAIL.COM" value="${staffEdit.email}"></input>
    <h4>Address</h4>
    <input type="text" name="address" placeholder="ADDRESS" value="${staffEdit.address}"></input>
    <h4>Phone Number</h4>
    <input type="number" name="phone" placeholder="PHONE" value="${staffEdit.phone}"></input>
    <h4>Salary</h4>
    <input type="number" name="salary" placeholder="SALARY" value="${staffEdit.salary}"></input>
    <h4>Department</h4>
    <select name="idDepartment">
        <c:forEach var="item" items="${list}">
            <option value="${item.id}">${item.name}</option>
        </c:forEach>
    </select><br>

    <button>Save</button>
</form>
</body>
</html>

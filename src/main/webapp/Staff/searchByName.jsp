<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/6/2024
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tim nhan vien theo ten</title>
    <style>
        .bang{
            width: 1000px;
            text-align: center;
        }
        .bang tr{
            width: 100%;
            height:40px;
        }
        .bang tr:nth-child(odd){
            background-color:white;
        }
        .bang tr:nth-child(odd){
            background-color: lightcyan;
        }
        .bang tr:nth-child(1){
            background-color: green;
            color: white;
            height: 50px;
            font-weight: bolder;
        }

    </style>
</head>
<body>
<h3><a href="http://localhost:8080/staff?action=home">Home</a></h3>
<h1>Search Student</h1>
<h3>Search name</h3>
<form method="get" action="http://localhost:8080/staff">
    <input type="hidden" name="action" value="searchName">
    <input type="text" name="inputName" placeholder="inputName">
    <button type="submit">Submit</button>
</form>

<table border="1" class="bang">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Email</td>
        <td>Address</td>
        <td>Phone</td>
        <td>Salary</td>
        <td>Department</td>
    </tr>
    <c:forEach var="item" items="${staffList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.email}</td>
            <td>${item.address}</td>
            <td>${item.phone}</td>
            <td>${item.salary}</td>
            <td>${item.department.name}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>

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
    <title>Home</title>
    <style>
        .bang{
            width:100%;
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
        .add{
            position: relative;
            float: left;
            display: flex;
            justify-content: left;
            flex-direction: row;
            text-align: center;
            align-items:center;

        }
        a{
            text-decoration: none;
        }
        .Search{
            position: relative;
            float: right;
            display: flex;
        }

    </style>

</head>
<body>
<div class="add">
    <button><a href="http://localhost:8080/staff?action=add">Thêm mới</a></button>
</div>

<div class="Search">

    <form method="get" action="http://localhost:8080/staff" name="Search name">
        <input type="hidden" name="action" value="searchName">
        <input type="text" name="inputName" placeholder="Name">
        <button type="submit" >Search</button>
    </form>
</div>

<table border="1" class="bang">
    <tr>
        <td>#</td>
        <td>Name</td>
        <td>Email</td>
        <td>Address</td>
        <td>Phone</td>
        <td>Salary</td>
        <td>Department</td>
        <td colspan="2">Action</td>
    </tr>
    <c:forEach var="item" items="${staffList}">
        <tr class="row-body">
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.email}</td>
            <td>${item.address}</td>
            <td>${item.phone}</td>
            <td>${item.salary}</td>
            <td>${item.department.name}</td>
            <td>
                <button><a href="http://localhost:8080/staff?action=edit&idEdit=${item.id}">Edit</a></button>
            </td>
            <td>
                <button onclick="deleteStaff(${item.id})">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<script>
    function deleteStaff(id){
        let isConfirm = confirm("Are you sure you want to delete")
        if (isConfirm){
            window.location.href = "http://localhost:8080/staff?action=delete&idDelete="+id
        }
    }
</script>
</body>
</html>

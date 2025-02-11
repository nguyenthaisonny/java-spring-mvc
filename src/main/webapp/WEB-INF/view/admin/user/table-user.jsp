<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Table Users</title>
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <!-- <link href="/css/demo.css" rel="stylesheet"> -->
            </head>

            <body>
                <div class="container mt-5">
                    <div class="d-flex justify-content-between align-items-center">
                        <h3>Table Users</h3>
                        <a href="/admin/user/create">
                            <button class="btn btn-success">
                                Create User
                            </button>
                        </a>
                    </div>
                    <hr />
                    <table class="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Full Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.fullName}</td>
                                    <td>${user.email}</td>
                                    <td>
                                        <div class="d-flex justify-content-start gap-2">
                                            <a href="/admin/user/${user.id}" class="btn btn-info">View</a>
                                            <a href="/admin/user/update/${user.id}" class="btn btn-warning">Update</a>
                                            <a href="/admin/user/delete/${user.id}" class="btn btn-danger">Delete</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>


                        </tbody>
                    </table>
            </body>

            </html>
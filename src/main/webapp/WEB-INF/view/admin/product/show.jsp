<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="nguyenthaisonny - Dự án laptopshop" />
            <meta name="author" content="nguyenthaisonny" />
            <title>Dashboard - nguyenthaisonny</title>
            <link href="/css/styles.css" rel="stylesheet" />
            <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        </head>

        <body class="sb-nav-fixed">
            <jsp:include page="../layout/header.jsp" />
            <div id="layoutSidenav">
                <jsp:include page="../layout/sidebar.jsp" />

                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <h1 class="mt-4">Manage Products</h1>
                            <ol class="breadcrumb mb-4">
                                <li>

                                    <a href="/admin">Dashboard </a>
                                    <span> </span>
                                </li>
                                <li class="breadcrumb-item active">/ <span></span> Products</li>
                            </ol>
                            <div>
                                <div class="d-flex justify-content-between align-items-center">
                                    <h3>Table Products</h3>
                                    <a href="/admin/product/create">
                                        <button class="btn btn-success">
                                            Create Product
                                        </button>
                                    </a>
                                </div>
                                <hr />
                                <table class="table table-hover table-bordered">
                                    <table class=" table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Price</th>
                                                <th>Factory</th>
                                                <th>Quantity</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="product" items="${products}">
                                                <tr>
                                                    <td>${product.id}</td>
                                                    <td>${product.name}</td>
                                                    <td>${product.price}</td>
                                                    <td>${product.factory}</td>
                                                    <td>${product.quantity}</td>
                                                    <td>
                                                        <div class="d-flex justify-content-start gap-2">
                                                            <a href="/admin/product/${product.id}"
                                                                class="btn btn-info">View</a>
                                                            <a href="/admin/product/update/${product.id}"
                                                                class="btn btn-warning">Update</a>
                                                            <a href="/admin/product/delete/${product.id}"
                                                                class="btn btn-danger">Delete</a>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </main>
                    <jsp:include page="../layout/footer.jsp" />

                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>
            <script src="/js/scripts.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
                crossorigin="anonymous"></script>

        </body>

        </html>
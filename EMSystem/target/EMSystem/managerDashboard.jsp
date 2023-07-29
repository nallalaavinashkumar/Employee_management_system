<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Manager Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>

    <div class="container mt-5">
        <div class="card shadow p-3 mb-5 bg-white rounded">
            <div class="card-body">
                <h1 class="card-title">Welcome, Manager!</h1>

                <h2>Add Employee</h2>
                <form action="addEmployee" method="post">
                    Name: <input type="text" name="name" required class="form-control">
                    Email: <input type="email" name="email" required class="form-control">
                    Password: <input type="password" name="password" required class="form-control">
                    Role: <input type="text" name="role" required class="form-control">
                    Salary: <input type="number" name="salary" step="0.01" required class="form-control">
                    <input type="submit" value="Add Employee" class="btn btn-success">
                </form>

                <h2>Delete Employee</h2>
                <form action="deleteEmployee" method="post">
                    Email: <input type="email" name="email" required class="form-control">
                    <input type="submit" value="Delete Employee" class="btn btn-danger">
                </form>

                <h2>Leave Applications</h2>
                <table class="table table-striped">
                    <tr>
                        <th>Employee Name</th>
                        <th>From Date</th>
                        <th>To Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach items="${leaveApplications}" var="application">
                        <tr>
                            <td><c:out value="${application.employeeName}" /></td>
                            <td><c:out value="${application.fromDate}" /></td>
                            <td><c:out value="${application.toDate}" /></td>
                            <td><c:out value="${application.status}" /></td>
                            <td>
                                <form action="approveLeave" method="post">
                                    <input type="hidden" name="applicationId" value="${application.id}">
                                    <input type="submit" value="Approve" class="btn btn-success">
                                </form>
                                <form action="rejectLeave" method="post">
                                    <input type="hidden" name="applicationId" value="${application.id}">
                                    <input type="submit" value="Reject" class="btn btn-danger">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <form action="logout" method="post">
                    <input type="submit" value="Log Out" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>

</body>

</html>

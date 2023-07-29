<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        body {
            background: #f8f9fa;
        }
        .card {
            border-radius: 20px;
        }
        .btn {
            border-radius: 20px;
        }
        .form-control {
            border-radius: 20px;
        }
        .table {
            border-radius: 20px;
            box-shadow: 0px 0px 30px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>

<body>

    <div class="container mt-5">
        <div class="card shadow p-4 bg-white rounded">
            <div class="card-body">
                <h1 class="card-title mb-4">Welcome, <c:out value="${employee.name}" />!</h1>
                <h2 class="mb-4">Your Details</h2>
                <table class="table table-striped table-responsive-md">
                    <thead class="thead-dark">
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Salary</th>
                    </tr>
                    </thead>

                    <tr>
                        <td><c:out value="${employee.name}" /></td>
                        <td><c:out value="${employee.email}" /></td>
                        <td><c:out value="${employee.role}" /></td>
                        <td><c:out value="${employee.salary}" /></td>
                    </tr>
                </table>

                <form action="clockin" method="post" class="mb-3">
                    <input type="submit" value="Clock In" class="btn btn-success">
                </form>

                <form action="clockout" method="post" class="mb-3">
                    <input type="submit" value="Clock Out" class="btn btn-danger">
                </form>

                <h2 class="mb-4">Apply for Leave</h2>
                <form action="applyLeave" method="post" class="mb-3">
                    <label class="form-label">From Date:</label>
                    <input type="date" name="fromDate" required class="form-control mb-3">
                    <label class="form-label">To Date:</label>
                    <input type="date" name="toDate" required class="form-control mb-3">
                    <input type="submit" value="Apply for Leave" class="btn btn-warning">
                </form>

                <form action="logout" method="post">
                    <input type="submit" value="Log Out" class="btn btn-primary">
                </form>
            </div>
        </div>
    </div>

</body>

</html>

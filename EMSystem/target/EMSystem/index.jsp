<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        .login-container {
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        #employeeForm, #managerForm {
            margin-top: -100px;
        }
    </style>
    <script>
    function toggleForms() {
        const employeeForm = document.getElementById('employeeForm');
        const managerForm = document.getElementById('managerForm');
        employeeForm.style.display = employeeForm.style.display === 'none' ? 'block' : 'none';
        managerForm.style.display = managerForm.style.display === 'none' ? 'block' : 'none';
    }
    </script>
</head>

<body>

    <div class="container login-container">

        <div id="employeeForm">
            <div class="card shadow p-3 mb-5 bg-white rounded">
                <div class="card-body">
                    <h5 class="card-title text-center">Employee Login</h5>
                    <form action="login" method="post">
                        <label for="email">Email:</label>
                        <input type="email" name="email" required class="form-control">
                        <br>
                        <label for="password">Password:</label>
                        <input type="password" name="password" required class="form-control">
                        <br>
                        <input type="hidden" name="userType" value="employee">
                        <input type="submit" value="Login" class="btn btn-primary">
                    </form>
                    <p class="text-center mt-3">
                        <a href="#" onclick="toggleForms()">Login as Manager</a>
                    </p>
                </div>
            </div>
        </div>

        <div id="managerForm" style="display: none;">
            <div class="card shadow p-3 mb-5 bg-white rounded">
                <div class="card-body">
                    <h5 class="card-title text-center">Manager Login</h5>
                    <form action="login" method="post">
                        <label for="email">Email:</label>
                        <input type="email" name="email" required class="form-control">
                        <br>
                        <label for="password">Password:</label>
                        <input type="password" name="password" required class="form-control">
                        <br>
                        <input type="hidden" name="userType" value="manager">
                        <input type="submit" value="Login" class="btn btn-primary">
                    </form>
                    <p class="text-center mt-3">
                        <a href="#" onclick="toggleForms()">Login as Employee</a>
                    </p>
                </div>
            </div>
        </div>

    </div>

</body>

</html>

<html>
<head>
    <title>Login | ChatApp</title>
</head>
<body>
    <h2>Chat App</h2>
    <div>${error}</div>
    <form method="POST" >
        <Label>User Name:</Label>
        <input name="userName" placeholder="Username" required />
        
        <Label>Password:</Label>
        <input type="password" name="password" placeholder="password" required />
        
        <input type="submit" value="Login" />
    </form>
    
</body>
</html>
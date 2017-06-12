<html>
    <head>
    	<title>Add User</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="login.js"></script>
     </head>
     
    <body>
        <h1>User Login</h1>
        <div id="error"></div>
        <form method="post">
          <table>
          	<tr><td>User Name</td><td><input type="text" name="username" id="username"/></td></tr>
          	<tr><td>Password</td><td><input type="password" name="password" id="password"/></td></tr>
          	<tr><td colspan="2"><input type="button" value="Login" onclick="loginUser()" /></td></tr>
          </table>
       </form>
   </body>
</html>
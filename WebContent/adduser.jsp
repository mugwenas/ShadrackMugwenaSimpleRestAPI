<html>
    <head>
    	<title>Add User</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="adduser.js"></script>
     </head>
     
    <body>
        <h1>Add User</h1>
        <form method="post">
          <table>
          	<tr><td>User Name</td><td><input type="text" name="username" id="username"/></td></tr>
          	<tr><td>Password</td><td><input type="password" name="password" id="password"/></td></tr>
          	<tr><td>Phone</td><td><input type="text" name="phone" id="phone"/></td></tr>
          	<tr><td colspan="2"><input type="button" value="Add User" onclick="createUser()" /></td></tr>
          </table>
       </form>
   </body>
</html>
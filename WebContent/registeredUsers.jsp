<html>
    <head>
    	
    	<title>Registered User</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="registeredUsers.js"></script>
        <script>
        $(document).ready(function() {
        	if (!window.sessionStorage.token) {
        		$(location).attr('href','http://localhost:8080/ShadrackMugwena/login.jsp');
        	} else {
        		getUsers();
        	} 
	      });
	    </script>
     </head>
     
    <body>
        <h1>Registered Users</h1>
        <div id="registeredUsersId"></div>
    </body>
</html>
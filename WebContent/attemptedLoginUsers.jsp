<html>
    <head>
    	<title>Last login User</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="attemptedLoginUsers.js"></script>
        <script>
        $(document).ready(function() {
        	if (!window.sessionStorage.token) {
        		$(location).attr('href','http://localhost:8080/ShadrackMugwena/login.jsp');
        	} else {
        		getUsersDuration();
        	} 
	      });
	    </script>
     </head>
     
    <body>
        <h1>Users Logged in last five minutes</h1>
        <div id="registeredUsersDurationId"></div>
    </body>
</html>
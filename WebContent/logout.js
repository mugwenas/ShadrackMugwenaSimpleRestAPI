function logOutUser() {
	var logoutToken = new Object();
	logoutToken.id = window.sessionStorage.getItem("id");
   	logoutToken.token =  window.sessionStorage.getItem("token");
   	logOut(logoutToken);
	
};

function logOut (logoutToken) {
    jQuery.ajax({
        type: "POST",
        url: "http://localhost:8080/ShadrackMugwena/resources/users/user/logout/" + logoutToken.id,
        data: JSON.stringify(logoutToken),
        contentType: "application/json",
        dataType: "json",
        success: function (data, status, jqXHR) {
        	window.sessionStorage.removeItem("id");
       	 	window.sessionStorage.removeItem("token");
             $(location).attr('href','http://localhost:8080/ShadrackMugwena/login.jsp');
        },
         error: function (jqXHR, status) {
        	 window.sessionStorage.removeItem("id");
        	 window.sessionStorage.removeItem("token");
        	 $(location).attr('href','http://localhost:8080/ShadrackMugwena/login.jsp');
        }

    });
}
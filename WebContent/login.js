function loginUser() {
	var user = new Object();
	user.username = document.getElementById("username").value;
	user.password = document.getElementById("password").value;
	login(user)
	
};

function login (user) {
    jQuery.ajax({
        type: "POST",
        url: "http://localhost:8080/ShadrackMugwena/resources/users/user/login",
        data: JSON.stringify(user),
        contentType: "application/json",
        dataType: "json",
        success: function (data, status, jqXHR) {
        	 window.sessionStorage.setItem("id", data.id);
             window.sessionStorage.setItem("token", data.token);
             $(location).attr('href','http://localhost:8080/ShadrackMugwena/restrictedPages.jsp');
        },
         error: function (jqXHR, status) {
        	 window.sessionStorage.removeItem("id");
        	 window.sessionStorage.removeItem("token");
        	 document.getElementById("error").innerHTML = "<h2><span style='color:red'>UNAUTHORIZED_USER!!</span></h2>";
        }

    });
}
function getUsers() {
	getAll();
	setTimeout(function() {
		getUsers();
	}, 5000);
};

function getAll() {
	
    jQuery.ajax({
        type: "GET",
        url: "http://localhost:8080/ShadrackMugwena/resources/users",
        dataType: "json",
        beforeSend : function( xhr ) {
        	if (window.sessionStorage.token) {
                xhr.setRequestHeader("Authorization", "Bearer " +  window.sessionStorage.token);
            }
        },
        success: function (data, status, jqXHR) {
        	var str = "<ul>" ;
            $.each(data , function(i, item) {
            	str += "<li> id: " + item.id + " phone: " + item.phone +  "</li>";
			});
            str += "</ul>";
            document.getElementById("registeredUsersId").innerHTML = str;
            
        },
         error: function (jqXHR, status) {            
        	 status
        }

    });
    
}
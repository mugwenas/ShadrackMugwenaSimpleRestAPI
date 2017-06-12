function getUsersDuration() {
	getAllDuration();
	setTimeout(function() {
		getUsersDuration();
	}, 5000);
};

function getAllDuration() {
	
    jQuery.ajax({
        type: "GET",
        url: "http://localhost:8080/ShadrackMugwena/resources/users/duration",
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
            document.getElementById("registeredUsersDurationId").innerHTML = str;
            
        },
         error: function (jqXHR, status) {            
        	 status
        }

    });
    
}
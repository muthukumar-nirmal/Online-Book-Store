/**
 * 
 */
var USER_BASE_URL = 'http://localhost:8080/api/user/';

$(document).ready(function() {
	$('#signinForm').show();
});

function login() {
	var email = $('#inputEmail').val();
	var password = $('#inputPassword').val();

	if (email == null || email == '' && !isValidEmailAddress) {
		alert("Invalid Email");
		$('#inputEmail').focus();
	} else if (password == null || email == '') {
		alert("Invalid Password");
		$('#inputPassword').focus();
	} else {
		$.ajax({
			type : 'GET',
			url : USER_BASE_URL + 'validateLogin',
			dataType : 'json',
			data : {
				'email' : email,
				'password' : password
			},
			async : false,
			success : function(data) {
				if(data != null){
					$('#signinForm').hide();
					$('#headerInfo').show();
					$('#name').html("Welcome : " + data.firstName + data.lastName);
					if(data.role == 'ADMIN'){
						$('#user').show();
						$('#books').show();
						$('#category').show();
						$('#aboutUs').show();
						$('#contactUs').show();
					}else{
						$('#user').hide();
						$('#books').hide();
						$('#category').hide();
						$('#aboutUs').show();
						$('#contactUs').show();
					}
				}
			}
		});
	}
}

function listUser(){
	$('#signinForm').hide();
	$('#userTableInfo').show();
	$.ajax({
		type : 'GET',
		url : USER_BASE_URL + 'list',
		dataType : 'json',
		async : false,
		success : function(data){
			console.log(data);
			if(data != null){
				var userInfo = "";
				for(var index = 1; index < data.length; index++){
					userInfo += '<tr><td>' + data.firstName + " " + data.lastName + '</td><td>' + data.email + '</td><td>' + data.role + '</td><td><a href="#" class="btn btn-primary" onClick="viewUser(\''+ data.id +'\')" style="padding: 3px 7px;">View</a></td></tr>';
				}
				$('#userInfo').append(userInfo);
				
				 // Display data in the dataTable
				//  "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
                var table = $('#userTableInfo').DataTable({
                    "bDestroy": true,
                    "bAutoWidth": false,
                    "bFilter": true,
                    "bSort": true,
                    "pageLength": 5,
                    "aaSorting": [[0]],
                    "aoColumns": [
                        { "sWidth": '25%', "bSortable": true },
                        { "sWidth": '25%', "bSortable": true },
                        { "sWidth": '25%', "bSortable": true },
                        { "sWidth": '3%', "bSortable": false }
                    ]
                });
			}
		}
	});
}
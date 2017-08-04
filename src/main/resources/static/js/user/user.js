/**
 * 
 */
var USER_BASE_URL = 'http://localhost:8080/api/user/';

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
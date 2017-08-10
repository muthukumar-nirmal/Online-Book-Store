/**
 * 
 */

function login() {
	var email = $('#inputEmail').val();
	var password = $('#inputPassword').val();

	if (email == null || email == '' || !isValidEmailAddress) {
		alert("Invalid Email");
		$('#inputEmail').focus();
	} else if (password == null || email == '') {
		alert("Invalid Password");
		$('#inputPassword').focus();
	} else {
		$.ajax({
			type : 'GET',
			url : BASE_URL + 'user/validateLogin',
			dataType : 'json',
			data : {
				'email' : email,
				'password' : password
			},
			success : function(data) {
				if (data != null) {
					$('#signinForm').hide();
					$('#headerInfo').show();
					removeActiveClass();
					$('#home').addClass('active');
					$('#adminDashboard').show();
					$.cookie('emailAddress', data.email);
					$('#name').html(data.firstName + " " + data.lastName);
					if (data.role == 'ADMIN') {
						$('#user').show();
						$('#books').show();
						$('#category').show();
						$('#aboutUs').show();
						$('#contactUs').show();
					} else {
						$('#user').hide();
						$('#books').hide();
						$('#category').hide();
						$('#aboutUs').show();
						$('#contactUs').show();
					}
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
		        console.log("Status: " + textStatus); 
		        console.log("Error: " + errorThrown); 
		    }  
		});
	}
}

function logout(){
	var email = $.cookie('emailAddress');
	$.ajax({
		type : 'GET',
		url : BASE_URL + 'user/logout',
		dataType : 'json',
		data : {
			'email' : email
		},
		success : function(data) {
			if(data == true){
				$.removeCookie('emailAddress');
				$('#headerInfo').hide();
				hideAndShowTable();
				$('#signinForm').show();
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
	        console.log("Status: " + textStatus); 
	        console.log("Error: " + errorThrown); 
	    }  
	});
}

function listUser() {
	$('#signinForm').hide();
	removeActiveClass();
	$('#user').addClass('active');
	$.ajax({
		type : 'GET',
		url : BASE_URL + 'user/list',
		dataType : 'json',
		success : function(data) {
			console.log(data);
			if (data != null) {
				var userInfo = "";
				for (var index = 0; index < data.length; index++) {
					userInfo += '<tr><td>' + data[index].firstName + " " + data[index].lastName + '</td><td>' + data[index].email
							+ '</td><td>' + data[index].role + '</td><td>' + data[index].address.houseNumber + "," + data[index].address.boxNumber + "," + data[index].address.street +'</td>'
							+ '<td>' + data[index].address.postalCode + '</td><td>' + data[index].address.city + '</td><td>' + data[index].address.country + '</td><td><a href="#" onClick="editUser(\'' + data[index].id + '\')">'
							+ '<img src="images/edit.png" alt="Edit User" title="Edit User" width="24px"></a>&nbsp;'
							+ '<a href="#" class="trash" onClick="deleteUser(\'' + data[index].id + '\')">'
							+ '<img src="images/delete.png" alt="Delete User" title="Delete User" width="24px"></a>&nbsp;'
							+ '<a href="#" class="view" onClick="viewUser(\'' + data[index].id + '\')">'
							+ '<img src="images/view.png" alt="View User" title="View User" width="24px"></a>'
							+ '</td></tr>';
				}
				$('#userInfo').append(userInfo);
			}
			// Display data in the dataTable
			// "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50,
			// "All"]],
			var table = $('#userTableInfo').DataTable({
				"bDestroy" : true,
				"bAutoWidth" : false,
				"bFilter" : true,
				"bSort" : true,
				"aaSorting" : [ [ 0 ] ],
				"aoColumns" : [ {
					"sWidth" : '15%',
					"bSortable" : true
				}, {
					"sWidth" : '15%',
					"bSortable" : true
				}, {
					"sWidth" : '10%',
					"bSortable" : true
				}, {
					"sWidth" : '20%',
					"bSortable" : true
				}, {
					"sWidth" : '10%',
					"bSortable" : true
				}, {
					"sWidth" : '5%',
					"bSortable" : true
				}, {
					"sWidth" : '10%',
					"bSortable" : true
				}, {
					"sWidth" : '15%',
					"bSortable" : false
				} ]
			});
			hideAndShowTable();
			$('#userInformation').show();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
	        console.log("Status: " + textStatus); 
	        console.log("Error: " + errorThrown); 
	    }    
	});
}

function loginHistory(){
	$('#signinForm').hide();
	removeActiveClass();
	$('#history').addClass('active');
	$.ajax({
		type : 'GET',
		url : BASE_URL + 'loginInfo/list',
		dataType : 'json',
		success : function(data){
			if(data != null){
				var loginInfo = "";
				for(var index = 0; index < data.length; index++){
					loginInfo += '<tr><td>' + data[index].email +'</td><td>' + data[index].loginTime + '</td><td>'+ data[index].logoutTime +'</td></tr>';
				}
				$('#loginHistoryInfo').append(loginInfo);
			}
            var table = $('#loginHistoryTableInfo').DataTable({
                "bDestroy": true,
                "bAutoWidth": false,
                "bFilter": true,
                "bSort": true,
                "aaSorting": [[0]],
                "aoColumns": [
                    { "sWidth": '25%', "bSortable": true },
                    { "sWidth": '25%', "bSortable": true },
                    { "sWidth": '25%', "bSortable": false }
                ]
            });
            hideAndShowTable();
			$('#loginHistoryInformation').show();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
	        console.log("Status: " + textStatus); 
	        console.log("Error: " + errorThrown); 
	    } 
	});
}
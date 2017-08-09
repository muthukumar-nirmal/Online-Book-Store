/**
 * 
 */

var BASE_URL = 'http://localhost:8080/api/';

function isValidEmailAddress(emailAddress) {
	var pattern = new RegExp(/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/);
	return pattern.test(emailAddress);
};

function removeActiveClass(){
	$('#home').removeClass('active');
	$('#user').removeClass('active');
	$('#category').removeClass('active');
	$('#books').removeClass('active');
	$('#aboutUs').removeClass('active');
	$('#contactUs').removeClass('active');
}

function hideAndShowTable(){
	$('#userInformation').hide();
	$('#categoryInformation').hide();
	$('#adminDashboard').hide();
}

function loadHome(){
	removeActiveClass();
	$('#home').addClass('active');
	hideAndShowTable();
	$('#adminDashboard').show();
}

function loadContactUs(){
	removeActiveClass();
	$('#contactUs').addClass('active');
	hideAndShowTable();
}

function loadAboutUs(){
	removeActiveClass();
	$('#aboutUs').addClass('active');
	hideAndShowTable();
}
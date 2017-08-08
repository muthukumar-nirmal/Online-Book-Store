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
}

function hideAndShowTable(){
	$('#userInformation').hide();
	$('#categoryInformation').hide();
}
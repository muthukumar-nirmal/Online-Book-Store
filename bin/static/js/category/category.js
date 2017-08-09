/**
 * 
 */


function listCategory(){
	$('#signinForm').hide();
	removeActiveClass();
	$('#category').addClass('active');
	$.ajax({
		type : 'GET',
		url : BASE_URL + 'category/list',
		dataType : 'json',
		success : function(data){
			if(data != null){
				var categoryInfo = "";
				for(var index = 0; index < data.length; index++){
					categoryInfo += '<tr><td>' + data[index].name +'</td><td>' + data[index].description + '</td><td><a href="#" class="btn btn-primary" onClick="viewCategory(\''+ data[index].categoryId +'\')" style="padding: 3px 7px;">View</a></td></tr>';
				}
				$('#categoryInfo').append(categoryInfo);
			}
			 // Display data in the dataTable
			//  "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]],
            var table = $('#categoryTableInfo').DataTable({
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
			$('#categoryInformation').show();
		}
	});
}
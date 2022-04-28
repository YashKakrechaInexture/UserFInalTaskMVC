$(document).ready( function () {
    $('#usertable').DataTable({
		dom: 'Bfrtip',
		buttons: [
			
            {
                extend: 'excel',
                exportOptions: {
                    columns: [0,1,2,3,4,5,6,7]
                },
            },
            {
                extend: 'pdf',
                exportOptions: {
                    columns: [0,1,2,3,4,5,6,7]
                },
            },
            {
                extend: 'print',
                exportOptions: {
	                columns: [0,1,2,3,4,5,6,7]
	            }
            },
        ]
        
	});
});
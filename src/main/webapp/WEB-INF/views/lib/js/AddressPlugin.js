$(document).ready(function(){
	$('a#add-more').cloneData({

		  // container to hold the dulicated form fields
		  mainContainerId: 'main-container',

		  // Which you want to clone
		  cloneContainer: 'container-item',

		  // CSS lcass of remove button
		  removeButtonClass: 'remove-item',
		  
		  // Maximum limit of clone
		  maxLimit: 0, //0 = unlimited

		  // Minimum limit of clone
		  minLimit: 1,
		  
		  // Remove alert notification
		  removeConfirm: true, 
		  removeConfirmMessage: 'Are you sure want to delete?',
		  
		  //removing remove button
		  afterRemove: function(){
			var buttons = document.querySelectorAll('.remove-item');
			console.log(buttons.length);
			if(buttons.length <2){
				$(".remove-item").hide();
			}else{
				$(".remove-item").show();
			}
		}
		  
	});
});

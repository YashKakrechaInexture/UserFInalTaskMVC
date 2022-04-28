$(document).ready(function(){
	$('#submit-btn').click(function() {
      checked = $("input[type=checkbox]:checked").length;

      if(!checked) {
        alert("Please select one checkbox to submit.");
        return false;
      }

    });
	$('#profilepic').change(function(){
		
		const file = this.files[0];
		
		if (file){
			let reader = new FileReader();
			reader.onload = function(event){
				
				$('#imgPreview').attr('src', event.target.result);
				
			}
			reader.readAsDataURL(file);
		}else{
			$("#imgPreview").attr('src', "");
		}
	});
	
	function hideButton(){
		var buttons = document.querySelectorAll('.remove-item');
		console.log(buttons.length);
		if(buttons.length <2){
			$(".remove-item").hide();
		}else{
			$(".remove-item").show();
		}
	}
	
	hideButton();
	
	$("#add-more").on("click",function(){
		hideButton();
	});
	
	
	$('#msg').hide();
	$('#email').on("keyup",function(){
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/UserFinalTask/AuthEmailServlet",
			data: $('#submitform').serialize(),
			dataType: "html",
			cache: false,
			success: function(msg){
				$('#msg').show();
				$("#msg").html(msg);
				if(msg=="<span style=\"color:red;\">Email Already Taken.</span>"){
					$("#submit-btn").prop('disabled', true);
				}else{
					$("#submit-btn").prop('disabled', false);
				}
			},
			error: function(jqXHR, textStatus, errorThrown){
				$('#msg').show();
				$("#msg").html(jqXHR+" "+textStatus+" "+errorThrown);
			}
		});
	});
});
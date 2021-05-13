//hide alert
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#ID").val("");
	$("#PRODUCT")[0].reset();
});

$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateProductForm();
	if (status != true) { 
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#ID").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "ProductAPI",
		type : type,
		data : $("#PRODUCT").serialize(),
		dataType : "text",
		complete : function(response, status) {
		console.log(status);
			onProductSaveComplete(response.responseText, status);
		//window.location.reload(true);
		}
	});

});

function onProductSaveComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#ProductGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#ID").val("");
	$("#PRODUCT")[0].reset();
}

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "ProductAPI",
 type : "DELETE",
 data : "ID=" + $(this).data("itemid"),
 dataType : "text",
 complete : function(response, status)
 {
 onProductDeleteComplete(response.responseText, status);
//window.location.reload(true);
 }
 });
});


function onProductDeleteComplete(response, status) {
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#ProductGrid").html(resultSet.data);
			
		} else if (resultSet.status.trim() == "error") {
			
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
		
	} else if (status == "error") {
		
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
		
	} else {
		
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event)
		{
			$("#ID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#ProID").val($(this).closest("tr").find('td:eq(1)').text());
			$("#ProName").val($(this).closest("tr").find('td:eq(2)').text());
			$("#ProGrade").val($(this).closest("tr").find('td:eq(3)').text());
			$("#ProPrice").val($(this).closest("tr").find('td:eq(4)').text());
			
		});


// CLIENTMODEL=========================================================================
function validateProductForm() {
	
	
	if ($("#ProID").val().trim() == "") {
		return "Please insert Product ID.";
	}
	

	if ($("#ProName").val().trim() == "") {
		return "Please insert Product Name.";
	}
	
	
	if ($("#ProGrade").val().trim() == "") {
		return "Please insert Product Category.";
	}
	

	if ($("#ProPrice").val().trim() == "") {
		return "Please insert Product Price.";
	}
	
	
	
	return true;
}

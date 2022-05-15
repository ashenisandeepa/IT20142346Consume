 $(document).ready(function()
{

	$("#alertSuccess").hide();
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidcidSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "consAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 });   
 


});
function onItemSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 

 $("#hidcidSave").val(""); 
 $("#formItem")[0].reset(); 
}  

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidcidSave").val($(this).data("cid")); 
 $("#name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#address").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#phone").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#email").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#description").val($(this).closest("tr").find('td:eq(4)').text()); 
});
 // DELETE==========================================
 $(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "consAPI", 
 type : "DELETE", 
 data : "cid=" + $(this).data("cid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
 });
 function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
 
 
// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// CODE
if ($("#name").val().trim() == "") 
 { 
 return "Insert UserName."; 
 } 
// NAME
if ($("#address").val().trim() == "") 
 { 
 return "Insert the Address ."; 
 } 
 
// PRICE-------------------------------
if ($("#phone").val().trim() == "") 
 { 
 return "Insert Contact Number."; 
 } 
 
// DESCRIPTION------------------------
if ($("#email").val().trim() == "") 
 { 
 return "Insert the Email."; 
 } 
 
 if ($("#description").val().trim() == "") 
 { 
 return "Insert the Description."; 
 } 
return true; 
}

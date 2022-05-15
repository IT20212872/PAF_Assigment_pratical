$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});



$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();
// Form validation-------------------
var status = validateCustomerForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid------------------------
var type = ($("#hididSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "CustomersAPI",
type : type,
data : $("#formCustomer").serialize(),
dataType : "text",
complete : function(response, status)
{
onCustomerSaveComplete(response.responseText, status);
}
});
});



function onCustomerSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divCustomersGrid").html(resultSet.data);
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
$("#hididSave").val("");
$("#formCustomer")[0].reset();
}

$(document).on("click", ".btnUpdate", function(event)
{
$("#hididSave").val($(this).data("id"));
$("#first_name").val($(this).closest("tr").find('td:eq(0)').text());
$("#last_name").val($(this).closest("tr").find('td:eq(1)').text());
$("#address").val($(this).closest("tr").find('td:eq(2)').text());
$("#account_number").val($(this).closest("tr").find('td:eq(3)').text());
$("#phone_number").val($(this).closest("tr").find('td:eq(3)').text());
});


$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "CustomersAPI",
type : "DELETE",
data : "id=" + $(this).data("id"),
dataType : "text",
complete : function(response, status)
{
onCustomerDeleteComplete(response.responseText, status);
}
});
});


function onCustomerDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divCustomersGrid").html(resultSet.data);
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

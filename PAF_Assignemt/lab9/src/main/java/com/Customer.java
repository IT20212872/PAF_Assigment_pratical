package com;
import java.sql.*;
public class Item
{
private Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.jdbc.Driver");
con =
DriverManager.getConnection(
"jdbc:mysql://127.0.0.1:3310/paf_lab", "root", "");
}
catch (Exception e)
{
e.printStackTrace();
}
return con;
}
public String readCustomers()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for reading.";
}
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>First Name</th><th>Last Name</th><th>Address</th>"+ "<th>Account Number</th>"+"<th>Phone Number</th><th>Update</th><th>Remove</th></tr>";
String query = "select * from customers";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String id = Integer.toString(rs.getInt("id"));
String first_name = rs.getString("first_name");
String last_name = rs.getString("last_name");
String address = rs.getString("address");
String account_number = rs.getString("account_number");
String phone_number = rs.getString("phone_number");
// Add into the html table
output += "<tr><td><input id='hidCustomerIDUpdate'name='hidCustomerIDUpdate'type='hidden' value='" + id+ "'>" + account_number + "</td>";
output += "<td>" + first_name + "</td>";
output += "<td>" + last_name + "</td>";
output += "<td>" + address + "</td>";
output += "<td>" + account_number + "</td>";
output += "<td>" + phone_number + "</td>";
// buttons
output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"
+ id + "'>" + "</td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the items.";
System.err.println(e.getMessage());
}
return output;
}




public String insertItem(String firstname, String lastname,String address, String accountnumber, String phonenumber)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for inserting.";
}
// create a prepared statement
String query = " insert into customerss(`id`,`first_name`,`last_name`,`address`,`account_number`,`phone_number`) + values (?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, firstname);
preparedStmt.setString(3, lastname);
preparedStmt.setDouble(4, address);
preparedStmt.setString(5, accountnumber);
preparedStmt.setString(6, phonenumber);
// execute the statement
preparedStmt.execute();
con.close();
String newCustomers = readCustomers();
output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while inserting the customer.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String updateCustomer(String id, String firstname, String lastname,String address, String accountnumber, String phonenumber)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for updating.";
}
// create a prepared statement
String query = "UPDATE cudtomers SET firstname=?,lastname=?,address=?,accountnumber=?,phonenumber=? WHERE id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, firstname);
preparedStmt.setString(2, lastname);
preparedStmt.setDouble(3, address);
preparedStmt.setString(4, accountnumber);
preparedStmt.setString(4, phonenumber);
preparedStmt.setInt(5, Integer.parseInt(id));
//execute the statement
preparedStmt.execute();
con.close();
String newCustomers = readCustomers();
output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while updating the customer.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String deleteCustomer(String id)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for deleting.";
}
//create a prepared statement
String query = "delete from customers where id=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
//binding values
preparedStmt.setInt(1, Integer.parseInt(id));
//execute the statement
preparedStmt.execute();
con.close();
String newCustomers = readCustomers();
output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while deleting the customer.\"}";
System.err.println(e.getMessage());
}
return output;
}
}

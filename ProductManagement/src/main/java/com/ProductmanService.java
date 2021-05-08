package com;

import model.Productman;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Productman")

public class ProductmanService
{
Productman productmanObj = new Productman();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readProductman()
{
	return productmanObj.readProductman();
}
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertProductman(@FormParam("ProID") String ProID,
@FormParam("ProName") String ProName,
@FormParam("ProGrade") String ProGrade,
@FormParam("ProPrice") String ProPrice )
{
String output = productmanObj.insertProductman(ProID, ProName, ProGrade, ProGrade);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateProductman(String productmanData)
{
//Convert the input string to a JSON object
JsonObject productmanObject = new JsonParser().parse(productmanData).getAsJsonObject();
//Read the values from the JSON object
String ID = productmanObject.get("ID").getAsString();
String ProID = productmanObject.get("ProID").getAsString();
String ProName = productmanObject.get("ProName").getAsString();
String ProGrade = productmanObject.get("ProGrade").getAsString();
String ProPrice = productmanObject.get("ProPrice").getAsString();

String output = productmanObj.updateProductman( ID, ProID, ProName , ProGrade , ProGrade);
return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteProductman(String productmanData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(productmanData, "", Parser.xmlParser());

String ID = doc.select("ID").text();
String output = productmanObj.deleteProductman(ID);
return output;
}
}
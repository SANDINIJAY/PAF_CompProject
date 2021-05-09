package com;

import model.Product;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Product")

public class ProductService {
	Product productObj = new Product();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProduct() {
		return productObj.readProduct();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProduct(@FormParam("ProID") String ProID, @FormParam("ProName") String ProName,
			@FormParam("ProGrade") String ProGrade, @FormParam("ProPrice") String ProPrice) {
		String output = productObj.insertProduct(ProID, ProName, ProGrade, ProPrice);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProduct(String productData) {
//Convert the input string to a JSON object
		JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject();
//Read the values from the JSON object
		String ID = productObject.get("ID").getAsString();
		String ProID = productObject.get("ProID").getAsString();
		String ProName = productObject.get("ProName").getAsString();
		String ProGrade = productObject.get("ProGrade").getAsString();
		String ProPrice = productObject.get("ProPrice").getAsString();

		String output = productObj.updateProduct(ID, ProID, ProName, ProGrade, ProPrice);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProduct(String productData) {
//Convert the input string to an XML document
		Document doc = Jsoup.parse(productData, "", Parser.xmlParser());

		String ID = doc.select("ID").text();
		String output = productObj.deleteProduct(ID);
		return output;
	}
}
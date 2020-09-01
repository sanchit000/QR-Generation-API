package com.example.repeat4;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
class QrGenAppApplicationTests {
	protected MockMvc mvc;
	   @Autowired
	   WebApplicationContext webApplicationContext;
	@Test
	void contextLoads() {
		/*CheckClass obj1=new CheckClass();
		int size = 500;
		Hashtable<EncodeHintType, Object> hashmap= new Hashtable<>();
		hashmap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);*/
		//hashmap.put(EncodeHintType.CHARACTER_SET,"ANSI");
		/*QRCodeWriter qrCodeWriter = new QRCodeWriter();
		try
		{
		//BitMatrix byteMatrix = qrCodeWriter.encode("This is txt", BarcodeFormat.QR_CODE, size, size, hashmap);
		Random_Key r=new Random_Key();
		r.setKey_gen(1234);
		Send_Data send_data=new Send_Data();
		send_data.setClient_id(1234);
		send_data.setProduct("prd");
		send_data.setType("type");
		EncodeInfo encodeinfo[]=new EncodeInfo[1];
		encodeinfo[0]=new EncodeInfo();
		encodeinfo[0].setKey("key");
		encodeinfo[0].setValue("value");
		send_data.setEncodeinfo(encodeinfo);
		User_Data user_data=new User_Data();
		user_data.setClient_id(1234);
		user_data.setEmail("XYZ@gmail.com");
		user_data.setKey_gen(1234);
		assertEquals("successful", obj1.sendmail(user_data));
		}
		catch(Exception e) {
			
		}*/
		String uri = "localhost:8080/push";
		   Send_Data send_data=new Send_Data();
		   //send_data.setClient_id(1234);
			send_data.setProduct("prd");
			send_data.setType("type");
			EncodeInfo encodeinfo[]=new EncodeInfo[1];
			encodeinfo[0]=new EncodeInfo();
			encodeinfo[0].setKey("key");
			encodeinfo[0].setValue("value");
			send_data.setEncodeinfo(encodeinfo);
		   ObjectMapper object_mapper=new ObjectMapper();
		   
		   try
		   {
		   String s=object_mapper.writeValueAsString(send_data);
		   System.out.println(s);
		    mvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(s)).andReturn();
		   
		   int status = mvcResult.getResponse().getStatus();
		   String ans=mvcResult.getResponse().getContentAsString();
		   System.out.println(ans+"check");
		   assertEquals(200, status);
		   String content = mvcResult.getResponse().getContentAsString();
		   }
		   catch(Exception e)
		   {
			   
		   }
		   //assertEquals(content, "Product is updated successsfully");*/
	}
	/*
	@Test
	public void updateProduct() throws Exception {
	   String uri = "http://localhost:8080/push";
	   //Product product = new Product();
	   //product.setName("Lemon");
	   Send_Data send_data=new Send_Data();
	   send_data.setClient_id(1234);
		send_data.setProduct("prd");
		send_data.setType("type");
		EncodeInfo encodeinfo[]=new EncodeInfo[1];
		encodeinfo[0]=new EncodeInfo();
		encodeinfo[0].setKey("key");
		encodeinfo[0].setValue("value");
		send_data.setEncodeinfo(encodeinfo);
	   ObjectMapper object_mapper=new ObjectMapper();
	   String s=object_mapper.writeValueAsString(send_data);
	    mvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(s)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "Product is updated successsfully");
	}*/

}

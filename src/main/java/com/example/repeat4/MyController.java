package com.example.repeat4;
import java.util.Properties;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.zxing.common.BitMatrix;
import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.Transport;


@Controller
public class MyController {
	
	
	
	
	@Autowired
	JSON_Store_Repo json_store_repo;
	@PostMapping(value="qrcode")
	   @ResponseBody
		public Random_Key value(@RequestHeader(value="client_id") long client_id, @RequestBody Send_Data send_data)
		{  	   
		byte [] data=null;
		System.out.println(client_id);
		//send_data.setClient_id(client_id);
		
		
		Random r=new Random();
		long l=Math.abs(r.nextInt(1000000)+1000000);
		String encode=Long.toString(l)+Long.toString(client_id);   
		try
		{
			QRGeneration obj=new QRGeneration();
			BitMatrix matrix=obj.createQRImage(encode);
			int width=matrix.getWidth();
			BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
				
			   
			   
			   /* the below code
				 * snippet convert a BitMatrix object into 
				 * bufferedImage class object 
				 */
			
			for(int i=0;i<width;i++)
				{
					for(int j=0;j<width;j++)
					   	{
						   if(matrix.get(i, j))
						   		{
						   			image.setRGB(i, j, 0);//0 is for black
							
						   		}
						   	else
						   		{
						   		image.setRGB(i, j, 16777215);//16777215 is for white
						   		}
					   	}
				   }
			
			
			
			//converting BufferedImage to byte array stream
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", bos );
		    data = bos.toByteArray();//byte array object
		    
		    //crosschecking by converting byte array stream back to bufferedimage
		    File  outputfile = new File("image_output.jpg");	   
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			BufferedImage image2 = ImageIO.read(bis);
			ImageIO.write(image2, "jpg", outputfile);
		}
		
		
		
	    catch(Exception e)
		   {
			   //will output some error
		   }
		
		
		ObjectMapper mapper=new ObjectMapper();
		
		
		
		String json_str="";
		try
		   {
				json_str=mapper.writeValueAsString(send_data);
				System.out.println(json_str);
		   }
		catch(Exception e)
		   {
			   
		   }
		
		
		
		Store_JSON store_json=new Store_JSON();
		store_json.setClient_id(client_id);
		store_json.setKey_gen(l);
		store_json.setJson_data(json_str);
		store_json.setQrcode(data);
		json_store_repo.save(store_json);
		  
		Random_Key key=new Random_Key();
		key.setKey_gen(store_json.getKey_gen());
		return key;
}
		
	
	
	
	//to fetch the byte array for a specific random key
@RequestMapping(value="qrcode/{get_id}a{key_gen}",produces="application/json" )
@ResponseBody
public Byte_Object give_QR(@PathVariable("get_id") long get_id,@PathVariable("key_gen") long key_gen)
{   
	   
	   Store_JSON obj=json_store_repo.findByIDandKey(get_id, key_gen);
	  
	   
	   if(obj==null)
	   {
		   byte[] qrcode_byte_stream=null;
		   Byte_Object qrcode_byte_obj=new Byte_Object();
		   qrcode_byte_obj.setQrcode(qrcode_byte_stream);
		   return qrcode_byte_obj;
	   }
	   else
	   {
		     File  outputfile = new File("image_output2.jpg");
			 ByteArrayInputStream bis = new ByteArrayInputStream(obj.getQrcode());
			 try
			 {
		      BufferedImage image2 = ImageIO.read(bis);
		      ImageIO.write(image2, "jpg", outputfile);
		      //saving the image of QRCode to verify
			 }
			 catch(IOException e)
			 {
				 //some errors
			 }
			 
			 Byte_Object byte_obj=new Byte_Object();

			 byte_obj.setQrcode(obj.getQrcode());
			 return byte_obj;
			 //return obj.getArray_name();
	   //retruning the byte array for the qrcode
	   
	   }
}
  
  
  
@PostMapping("notify/mail")
@ResponseBody
public String sendmail(@RequestBody User_Data user_data)
   {
	   Store_JSON store_json=json_store_repo.findByIDandKey(user_data.getClient_id(),user_data.getKey_gen());
	   String result=""; 
	   if(store_json!=null)
	   {
		   byte [] qrcode_byte_stream=store_json.getQrcode();
		   ByteArrayDataSource bds = new ByteArrayDataSource(qrcode_byte_stream, "image/png"); 
		   final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		   // Get a Properties object
		      Properties props = new Properties();
		      props.put("mail.smtp.host", "smtp.gmail.com");
		      props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		      props.put("mail.smtp.socketFactory.fallback", "false");
		      props.put("mail.smtp.port", "465");
		      props.put("mail.smtp.socketFactory.port", "465");
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.debug", "true");
		      props.put("mail.store.protocol", "pop3");
		      props.put("mail.transport.protocol", "smtp");
		      final String username = "XYZ@gmail.com";
		      final String password = "Password";
		      try {
		     	 Session session = Session.getDefaultInstance(props, 
		                  new Authenticator(){
		                     protected PasswordAuthentication getPasswordAuthentication() {
		                        return new PasswordAuthentication(username, password);
		                     }});

		      

		          Message message = new MimeMessage(session);
		          message.setFrom(new InternetAddress(username));
		          message.setRecipients(Message.RecipientType.TO,
		             InternetAddress.parse(user_data.getEmail()));
		          InternetAddress internetAddress = new InternetAddress(user_data.getEmail());
		            internetAddress.validate();
		            
		           
		          message.setSubject("QRCodeImage");
		          MimeMultipart multipart = new MimeMultipart("related");
		          BodyPart messageBodyPart = new MimeBodyPart();  
		          messageBodyPart.setDataHandler(new DataHandler(bds));
		          messageBodyPart.setHeader("Content-ID", "<image>");
		          messageBodyPart.setFileName("QRCodeImage");
		          multipart.addBodyPart(messageBodyPart);
		          message.setContent(multipart);
		          Transport.send(message);
		          System.out.println("Sent message successfully....");

		       } 
		      catch (MessagingException e) 
		      	  {
		          //throw new RuntimeException(e);
		    	   result="check your supplied email address";
		    	   return result;
		      	  }
	   }
	   
	   else
	   {
		   result="Check your client_id or Key";
		   return result;
	   }
	   
	   
	   System.out.println(store_json);
	   return "Successful";
}

   
   
   
   
   
   
   
   
   
   
   
   
   
   
@RequestMapping(value="{key_gen}and{client_id}")
public ModelAndView fun(@PathVariable("key_gen") long key_gen,@PathVariable("client_id") long client_id)
   {   
	
	
	   //System.out.println("hello");
	   //System.out.println(key_gen);
	   ModelAndView mv=new ModelAndView("hello.jsp");
	   Store_JSON store_json=new Store_JSON();
	   store_json=json_store_repo.findByIDandKey(client_id,key_gen);
	   if(store_json!=null)
	   {
	   System.out.println(store_json);
	   System.out.println(store_json.getJson_data());
	   String s=store_json.getJson_data();
	   Gson gson = new Gson(); // Or use new GsonBuilder().create();
	   Send_Data send_data = gson.fromJson(s, Send_Data.class); // deserializes json into target2
	   //System.out.println(target2.getClient_id());
	   System.out.println(send_data.getProduct());
	   System.out.println(send_data.getType());
	   EncodeInfo [] encodeinfo=send_data.getEncodeinfo();
	   /*for(int i=0;i<encodeinfo.length;i++)
	   {
		   System.out.println(encodeinfo[i].getKey());
		   System.out.println(encodeinfo[i].getValue());
		   
	   }*/
	   System.out.println(encodeinfo);
	   System.out.println(client_id);
	   mv.addObject("encodeinfo", encodeinfo);
	   return mv;
	   }
	   else
		   return mv;
   }
   
 
}

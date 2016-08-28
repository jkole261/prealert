package prealert30;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	static TwitterAlert twit;
	static StringBuilder st = new StringBuilder("\n");
	final static String username = "firepage12";
	final static String password = "station12";
	static int sta = 0;
	
	public static void sendMessage(String[] parsed) throws IOException {
		// TODO Auto-generated method stub
		for(int i = 0; i < parsed.length; i ++){
			st.append(parsed[i]+" ");
		}
		sta = (int)Double.parseDouble(parsed[parsed.length - 1]);
		System.out.println(st.toString());
		send(st.toString());
		st = new StringBuilder("\n");
	}
	
	public static void sendFire12(String[] parsed) throws IOException {
		// TODO Auto-generated method stub
		for(int i = 0; i < parsed.length; i ++){
			st.append(parsed[i]+" ");
		}
		sta = (int)Double.parseDouble(parsed[parsed.length - 1]);
		System.out.println(st.toString());
		sendFire12(st.toString());
		st = new StringBuilder("\n");
	}
	public static void sendFire26(String[] parsed) throws IOException {
		// TODO Auto-generated method stub
		for(int i = 0; i < parsed.length; i ++){
			st.append(parsed[i]+" ");
		}
		sta = (int)Double.parseDouble(parsed[parsed.length - 1]);
		System.out.println(st.toString());
		sendFire26(st.toString());
		st = new StringBuilder("\n");
	}
	public static void sendCountyBox(String[] parsed) throws IOException {
		// TODO Auto-generated method stub
		for(int i = 0; i < parsed.length; i ++){
			st.append(parsed[i]+" ");
		}
		sta = (int)Double.parseDouble(parsed[parsed.length - 1]);
		System.out.println(st.toString());
		sendCountyBox(st.toString());
		st = new StringBuilder("\n");
	}
	
	public static void sendems12(String[] parsed) throws IOException {
		// TODO Auto-generated method stub
		for(int i = 0; i < parsed.length; i ++){
			st.append(parsed[i]+" ");
		}
		sta = (int)Double.parseDouble(parsed[parsed.length - 1]);
		System.out.println(st.toString());
		sendems12(st.toString());
		st = new StringBuilder("\n");
	}
	public static void sendems26(String[] parsed) throws IOException {
		// TODO Auto-generated method stub
		for(int i = 0; i < parsed.length; i ++){
			st.append(parsed[i]+" ");
		}
		sta = (int)Double.parseDouble(parsed[parsed.length - 1]);
		System.out.println(st.toString());
		sendems26(st.toString());
		st = new StringBuilder("\n");
	}
	public static void send(String str) throws IOException{
		sendCall(getEmails(),
				"PREALERT " + sta + " BOX", str);
		System.out.println("LOCAL BOX ALARM ALERT SENT");
	}
	
	public static void sendFire12(String str) throws IOException{
		sendCall("UV165-9tztZs3mQckBmgve@alert.active911.com",
					"PREALERT " + sta + " RCVD", str);		
		System.out.println("DISPATCH ALERT SENT TO FIRE 12");

	}
	
	public static void sendFire26(String str) throws IOException{
		sendCall("HT165-9tztZs3mQckBmgve@alert.active911.com",
					"PREALERT " + sta + " PAGED", str);		
		System.out.println("DISPATCH ALERT SENT TO FIRE 26");
	}
	public static void sendCountyBox(String str) throws IOException{
			sendCall("SH165-9tztZs3mQckBmgve@alert.active911.com",
					"PREALERT " + sta + " BOX", str);		
			System.out.println("DISPATCH ALERT SENT TO COUNTY BOX");
	}	
	
	public static void sendems12(String str) throws IOException{
			sendCall("RA165-9tztZs3mQckBmgve@alert.active911.com",
					"PREALERT " + sta + " PAGED", str);			
			System.out.println("DISPATCH ALERT SENT TO EMS 12");
	}
	
	public static void sendems26(String str) throws IOException{
			sendCall("AJ165-9tztZs3mQckBmgve@alert.active911.com",
					"PREALERT " + sta + " PAGED", str);		
			System.out.println("DISPATCH ALERT SENT TO EMS 26");
	}
	
	public static void sendCall(String email, String subject, String info){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jkole261@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(info);
			Transport.send(message);
			
			//System.out.println("DISPATCH ALERT SENT TO EMS 12");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static String getEmails() throws IOException {
		// TODO Auto-generated method stub
		String temp = "";
		
		FileReader input;
		try {
			input = new FileReader("txtAddr.txt");
			BufferedReader bufRead = new BufferedReader(input);
			String myLine;
			while ( (myLine = bufRead.readLine()) != null)
			{    
			    temp += myLine +", ";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return temp;
	}
	
}
package prealert30;

import java.io.IOException;
//import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.PatternSyntaxException;


import jpcap.JpcapCaptor;
import jpcap.PacketReceiver;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;



public class Reciever extends Thread implements PacketReceiver {

	public JpcapCaptor jpcap;
	public enum PacketPart {
		ID, DATA, OTHER
	};

	private Map<String, String> calls = new HashMap<String, String>();
	public HashSet units;

	public void receivePacket(Packet packet) {

		if ((((IPPacket) packet).dst_ip.toString().equals("/10.208.26.67")) && (((IPPacket) packet).src_ip.toString().equals("/10.209.0.159"))) {

			String data1 = new String(packet.data, Charset.forName("UTF-8"));
			data1 = data1.replaceAll ("[^A-Za-z0-9 +-:#/n/r]", "");
			
            String data = data1;
            String beforemessage = "";
            String beforemessage2 = "";
            String beforemessage3 = "";
            String beforemessage4 = "";
            String beforemessage5 = "";
            String beforemessage6 = "";
            String beforemessage7 = "";
            String beforemessage8 = "";
            String beforemessage9 = "";
            String beforemessage10 = "";
            String beforemessage11 = "";
            String beforemessage12 = "";
            String beforemessage13 = "";
          //System.out.println(data1 + "\n---------\n"); 
    //        if(data.matches("(?s).\\d{2}/\\d{2}/\\d{2}")){
    //        	System.out.println("! "+data);
     //       }
               if(data.contains("FIRE - STRUCT") && data.contains("lfe")  &&(!data.contains("ACT"))) {
                        	beforemessage = getId(data);
                        		//beforemessage = beforemessage.replaceAll("\\s","");
                        		//System.out.println(beforemessage);
	                        	try {
	                        	    String[] parsed = beforemessage.split("\\s+");
	                        	    //store call id hashmap

	                        	    if(CheckCall(parsed[0]) && CallType(parsed[3])){
	                        	    	//System.out.println(Arrays.toString(parsed));
	                        	    	//new AudioPlayer("sounds/" + "dooralert.wav").start();
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	Mail.sendCountyBox(parsed);
	                        	    	
	                        	    	
	                        	    	if(CheckDist(parsed[parsed.length-1])){ //Local Box
		                        	    	//System.out.println(Arrays.toString(parsed));
		                        	    	new AudioPlayer("sounds/" + "dooralert.wav").start();
		                        	    	//System.out.println("local box");
		                        	    	Mail.sendMessage(parsed);
		                        	
		                        	    }
	                        	     }
		                        	} catch (PatternSyntaxException ex) {
		                        	    // 
		                        	} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}	 
                        	}
                        if(data.contains("BASIC") && data.contains("le") &&(!data.contains("ACT")) ) {
                        	//JOptionPane.showMessageDialog(null, getId(data));
                        	beforemessage2 = getIdBLS(data);
                        	
	                        	try {
	                        	    String[] parsed2 = beforemessage2.split("\\s+");

	                        	    if(CheckCall(parsed2[0]) && CheckDist(parsed2[parsed2.length-1])){
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	//System.out.println(Arrays.toString(parsed2));
	                        	    	//Mail.sendGeneral(parsed2);
	                        	    
		                        	    if (Check12(parsed2[parsed2.length-1])){
		                        	    	//System.out.println(Arrays.toString(parsed2));
		                        	    	Mail.sendems12(parsed2);
		                        	    }
		                        	    if (Check26(parsed2[parsed2.length-1])){
		                        	    	//System.out.println(Arrays.toString(parsed2));
		                        	    	Mail.sendems26(parsed2);
		                        	    }
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 
                        	}
                        if(data.contains("ALS") && data.contains("le") &&(!data.contains("ACT")) ) {
                        	//JOptionPane.showMessageDialog(null, getId(data));
                        	beforemessage3 = getIdALS(data);
                        	
	                        	try {
	                        	    String[] parsed3 = beforemessage3.split("\\s+");

	                        	    if(CheckCall(parsed3[0]) && CheckDist(parsed3[parsed3.length-1])){
	                        	    	//System.out.println(Arrays.toString(parsed3));
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	//Mail.sendGeneral(parsed3);
	                        	    
			                        	    if (Check12(parsed3[parsed3.length-1])){
			                        	    	Mail.sendems12(parsed3);
			                        	    }
			                        	    if (Check26(parsed3[parsed3.length-1])){
			                        	    	Mail.sendems26(parsed3);
			                        	    }
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 
                        	}
           				
                        if(data.contains("MVA") && (!data.contains("ACT")) ) {
                        	beforemessage4 = getIdMVA(data);
                        	
	                        	try {
	                        	    String[] parsed4 = beforemessage4.split("\\s+");

	                        	    if(CheckCall(parsed4[0]) && CheckDist(parsed4[parsed4.length-1])){
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	//Mail.sendGeneral(parsed4);
	                        	    
		                        	    if (Check12(parsed4[parsed4.length-1]) && CallTypeEMS(parsed4[3])){
		                        	    	Mail.sendems12(parsed4);
		                        	    }
		                        	    if (Check26(parsed4[parsed4.length-1]) && CallTypeEMS(parsed4[3])){
		                        	    	Mail.sendems26(parsed4);
		                        	    }
		                        	    if (Check12(parsed4[parsed4.length-1]) && CallTypeFire(parsed4[3])){
		                        	    	Mail.sendFire12(parsed4);
		                        	    }
		                        	    if (Check26(parsed4[parsed4.length-1]) && CallTypeFire(parsed4[3])){
		                        	    	Mail.sendFire26(parsed4);
		                        	    }
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 
                 
                        if(data.contains("VEHICL") && (!data.contains("ACT")) ) {
                        	beforemessage5 = getIdFireV(data);
                        	//System.out.println(beforemessage5);
	                        	try {
	                        	    String[] parsed5 = beforemessage5.split("\\s+");

	                        	    if(CheckCall(parsed5[0]) && CheckDist(parsed5[parsed5.length-1])){
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	//Mail.sendGeneral(parsed5);
	                               	    
		                        	    if (Check12(parsed5[parsed5.length-1]) && CallTypeEMS(parsed5[3])){
		                        	    	Mail.sendems12(parsed5);
		                        	    }
		                        	    if (Check26(parsed5[parsed5.length-1]) && CallTypeEMS(parsed5[3])){
		                        	    	Mail.sendems26(parsed5);
		                        	    }
		                        	    if (Check12(parsed5[parsed5.length-1]) && CallTypeFire(parsed5[3])){
		                        	    	Mail.sendFire12(parsed5);
		                        	    }
		                        	    if (Check26(parsed5[parsed5.length-1]) && CallTypeFire(parsed5[3])){
		                        	    	Mail.sendFire26(parsed5);
		                        	    }
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 
                        	} // end if
                        	}
                        if(data.contains("FIRE - ") && (!data.contains("ACT")) ) {
                        	beforemessage6 = getIdFireA(data);
	                        	try {
	                        	    String[] parsed6 = beforemessage6.split("\\s+");

	                        	    if(CheckCall(parsed6[0]) && CheckDist(parsed6[parsed6.length-1])){
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	//Mail.sendGeneral(parsed6);
	                        	    
		                        	    if (Check12(parsed6[parsed6.length-1]) && CallTypeEMS(parsed6[3])){
		                        	    	Mail.sendems12(parsed6);
		                        	    }
		                        	    if (Check26(parsed6[parsed6.length-1]) && CallTypeEMS(parsed6[3])){
		                        	    	Mail.sendems26(parsed6);
		                        	    }
		                        	    if (Check12(parsed6[parsed6.length-1]) && CallTypeFire(parsed6[3])){
		                        	    	Mail.sendFire12(parsed6);
		                        	    }
		                        	    if (Check26(parsed6[parsed6.length-1]) && CallTypeFire(parsed6[3])){
		                        	    	Mail.sendFire26(parsed6);
		                        	    }
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 
                        	} // end if
                        if(data.contains("MUTUAL AID -") && (!data.contains("ACT")) ) {
                  
                        	beforemessage7 = getIdFireM(data);
	                        	try {
	                        	    String[] parsed7 = beforemessage7.split("\\s+");

	                        	    if(CheckCall(parsed7[0]) && CallType(parsed7[3]) ){
	                        	    	if (CheckDist(parsed7[parsed7.length-1])) {
	                        	    		new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	}
	                        	    
		                        	    if (Check12(parsed7[parsed7.length-1]) && CallTypeEMS(parsed7[3])){
		                        	    	Mail.sendems12(parsed7);
		                        	    }
		                        	    if (Check26(parsed7[parsed7.length-1]) && CallTypeEMS(parsed7[3])){
		                        	    	Mail.sendems26(parsed7);
		                        	    }
		                        	    if (CheckDist(parsed7[parsed7.length-1]) && CallTypeFire(parsed7[3])){
		                        	    	Mail.sendCountyBox(parsed7);
		                        	    }
		                        	    if (Check12(parsed7[parsed7.length-1]) && CallTypeFire(parsed7[3])){
		                        	    	Mail.sendFire12(parsed7);
		                        	    }
		                        	    if (Check26(parsed7[parsed7.length-1]) && CallTypeFire(parsed7[3])){
		                        	    	Mail.sendFire26(parsed7);
		                        	    }
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 
                        	} // end if
                       
                        if(data.contains("ODOR/LEAK") && (!data.contains("ACT")) ) {
                            
                        	beforemessage8 = getIdGas(data);
	                        	try {
	                        	    String[] parsed8 = beforemessage8.split("\\s+");

	                        	    if(CheckCall(parsed8[0]) && CheckDist(parsed8[parsed8.length-1])){
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	//Mail.sendGeneral(parsed8);
			                        	    if (Check12(parsed8[parsed8.length-1]) && CallTypeEMS(parsed8[3])){
			                        	    	Mail.sendems12(parsed8);
			                        	    }
			                        	    if (Check26(parsed8[parsed8.length-1]) && CallTypeEMS(parsed8[3])){
			                        	    	Mail.sendems26(parsed8);
			                        	    }
			                        	    if (Check12(parsed8[parsed8.length-1]) && CallTypeFire(parsed8[3])){
			                        	    	Mail.sendFire12(parsed8);
			                        	    }
			                        	    if (Check26(parsed8[parsed8.length-1]) && CallTypeFire(parsed8[3])){
			                        	    	Mail.sendFire26(parsed8);
			                        	    }
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 

                        	} // end if
                        	
                        if(data.contains("FIRE - OTHER") && (!data.contains("ACT")) ) {
  
                        	beforemessage9 = getIdfother(data);
	                        	try {
	                        	    String[] parsed9 = beforemessage9.split("\\s+");
	                        	    //System.out.println(Arrays.toString(parsed9));
	                        	    
	                        	    if(CheckCall(parsed9[0]) && CheckDist(parsed9[parsed9.length-1])){
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	//Mail.sendGeneral(parsed9);
	                        	    
		                        	    if (Check12(parsed9[parsed9.length-1]) && CallTypeEMS(parsed9[3])){
		                        	    	Mail.sendems12(parsed9);
		                        	    }
		                        	    if (Check26(parsed9[parsed9.length-1]) && CallTypeEMS(parsed9[3])){
		                        	    	Mail.sendems26(parsed9);
		                        	    }
		                        	    if (Check12(parsed9[parsed9.length-1]) && CallTypeFire(parsed9[3])){
		                        	    	Mail.sendFire12(parsed9);
		                        	    }
		                        	    if (Check26(parsed9[parsed9.length-1]) && CallTypeFire(parsed9[3])){
		                        	    	Mail.sendFire26(parsed9);
		                        	    }
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 

                        	} // end if
                         
                        if(data.contains("FIRE - RIT") && (!data.contains("ACT")) ) {
                        	  
                        	beforemessage10 = getIdRIT(data);
	                        	try {
	                        	    String[] parsed10 = beforemessage10.split("\\s+");
	                        	    //System.out.println(Arrays.toString(parsed10));
	                        	    
	                        	    if(CheckCall(parsed10[0]) && CheckDist(parsed10[parsed10.length-1])){
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	//Mail.sendGeneral(parsed10);
	                        	    
		                        	    if (Check12(parsed10[parsed10.length-1]) && CallTypeEMS(parsed10[3])){
		                        	    	Mail.sendems12(parsed10);
		                        	    }
		                        	    if (Check26(parsed10[parsed10.length-1]) && CallTypeEMS(parsed10[3])){
		                        	    	Mail.sendems26(parsed10);
		                        	    }
		                        	    if (Check12(parsed10[parsed10.length-1]) && CallTypeFire(parsed10[3])){
		                        	    	Mail.sendFire12(parsed10);
		                        	    }
		                        	    if (Check26(parsed10[parsed10.length-1]) && CallTypeFire(parsed10[3])){
		                        	    	Mail.sendFire26(parsed10);
		                        	    }
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 

                        	} // end if
                         
                        if(data.contains("COVR A") && (!data.contains("ACT")) ) {
                      	  
                        	beforemessage11 = getIdcover(data);
	                        	try {
	                        	    String[] parsed11 = beforemessage11.split("\\s+");
	                        	    //System.out.println(Arrays.toString(parsed11));
	                        	    
	                        	    if(CheckCall(parsed11[0]) && CheckDist(parsed11[parsed11.length-1])){
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	//Mail.sendGeneral(parsed11);
	                        	    
			                        	    if (Check12(parsed11[parsed11.length-1]) && CallTypeEMS(parsed11[3])){
			                        	    	Mail.sendems12(parsed11);
			                        	    }
			                        	    if (Check26(parsed11[parsed11.length-1]) && CallTypeEMS(parsed11[3])){
			                        	    	Mail.sendems26(parsed11);
			                        	    }
			                        	    if (CheckDist(parsed11[parsed11.length-1]) && CallTypeFire(parsed11[3])){
			                        	    	Mail.sendCountyBox(parsed11);
			                        	    }
			                        	    if (Check12(parsed11[parsed11.length-1]) && CallTypeFire(parsed11[3])){
			                        	    	Mail.sendFire12(parsed11);
			                        	    }
			                        	    if (Check26(parsed11[parsed11.length-1]) && CallTypeFire(parsed11[3])){
			                        	    	Mail.sendFire26(parsed11);
			                        	    }
	                        		}
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 

                        	} // end if
                        
                        if(data.contains("HAZMAT") && (!data.contains("ACT")) ) {
                        	  
                        	beforemessage12 = getIdhazmat(data);
	                        	try {
	                        	    String[] parsed12 = beforemessage12.split("\\s+");
	                        	    //System.out.println(Arrays.toString(parsed12));
	                        	    
	                        	    if(CheckCall(parsed12[0]) && CheckDist(parsed12[parsed12.length-1])){
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	
		                        	    if (Check12(parsed12[parsed12.length-1]) && CallTypeEMS(parsed12[3])){
		                        	    	Mail.sendems12(parsed12);
		                        	    }
		                        	    if (Check26(parsed12[parsed12.length-1]) && CallTypeEMS(parsed12[3])){
		                        	    	Mail.sendems26(parsed12);
		                        	    }
		                        	    if (Check12(parsed12[parsed12.length-1]) && CallTypeFire(parsed12[3])){
		                        	    	Mail.sendFire12(parsed12);
		                        	    }
		                        	    if (Check26(parsed12[parsed12.length-1]) && CallTypeFire(parsed12[3])){
		                        	    	Mail.sendFire26(parsed12);
		                        	    } 
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 

                        	} // end if
                        
                        if(data.contains("FIRE - WOODS") && (!data.contains("ACT")) ) {
                        	  
                        	beforemessage13 = getIdwoods(data);
	                        	try {
	                        	    String[] parsed13 = beforemessage12.split("\\s+");
	                        	    //System.out.println(Arrays.toString(parsed13));
	                        	    
	                        	    if(CheckCall(parsed13[0]) && CheckDist(parsed13[parsed13.length-1])){
	                        	    	new AudioPlayer("sounds/" + "newcall.wav").start();
	                        	    	
		                        	    if (Check12(parsed13[parsed13.length-1]) && CallTypeEMS(parsed13[3])){
		                        	    	Mail.sendems12(parsed13);
		                        	    }
		                        	    if (Check26(parsed13[parsed13.length-1]) && CallTypeEMS(parsed13[3])){
		                        	    	Mail.sendems26(parsed13);
		                        	    }
		                        	    if (Check12(parsed13[parsed13.length-1]) && CallTypeFire(parsed13[3])){
		                        	    	Mail.sendFire12(parsed13);
		                        	    }
		                        	    if (Check26(parsed13[parsed13.length-1]) && CallTypeFire(parsed13[3])){
		                        	    	Mail.sendFire26(parsed13);
		                        	    } 
	                        	    }
	                        	} catch (PatternSyntaxException ex) {
	                        	    // 
	                        	} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	 

                        	} // end if
                       } //end if contains        
			}	//end function


	private boolean CheckDist(String string) {
		//RETURN TRUE IF IN SOUTH"12, 26, 16, 15, 28, 32, 41")
		if(string.equalsIgnoreCase("12") || string.equalsIgnoreCase("26") || string.equalsIgnoreCase("16") 
				|| string.equalsIgnoreCase("15") || string.equalsIgnoreCase("28") || string.equalsIgnoreCase("32") 
				||string.equalsIgnoreCase("41")){
			//System.out.println("heredist" + string);
		return true;
		}
		else {
			//System.out.println("notdist");
			return false;
		}
	}
	private boolean Check12(String string) {
		//RETURN TRUE IF IN SOUTH 12
		if(string.equalsIgnoreCase("12") ){
			//System.out.println("heredist" + string);
		return true;
		}
		else {
			//System.out.println("notdist");
			return false;
		}
	}
	private boolean Check26(String string) {
		//RETURN TRUE IF IN SOUTH 12
		if(string.equalsIgnoreCase("26") ){
			//System.out.println("heredist" + string);
		return true;
		}
		else {
			//System.out.println("notdist");
			return false;
		}
	}
	private boolean Check32(String string) {
		//RETURN TRUE IF IN SOUTH 12
		if(string.equalsIgnoreCase("32") ){
			//System.out.println("heredist" + string);
		return true;
		}
		else {
			//System.out.println("notdist");
			return false;
		}
	}
	private boolean Check16(String string) {
		//RETURN TRUE IF IN SOUTH 12
		if(string.equalsIgnoreCase("16") ){
			//System.out.println("heredist" + string);
		return true;
		}
		else {
			//System.out.println("notdist");
			return false;
		}
	}
	private boolean Check15(String string) {
		//RETURN TRUE IF IN SOUTH 12
		if(string.equalsIgnoreCase("15") ){
			//System.out.println("heredist" + string);
		return true;
		}
		else {
			//System.out.println("notdist");
			return false;
		}
	}
	
	
	
	private boolean CheckCall(String callid) {
		//RETURN TRUE IF CALL DOES NOT EXIST
		if(calls.containsValue(callid)){
			//System.out.println("call dup" + callid);
			return false;
		}
		else{
			calls.put(callid, callid);
			return true;
		}
	}
	private boolean CallType(String calltype) {
		//RETURN TRUE IF fire CALL DOES NOT EXIST
		if(calltype.contains("f") || calltype.contains("e")){
			return true;
		}
		else{
		
			return false;
		}
	}
	private boolean CallTypeFire(String calltype) {
		//RETURN TRUE IF fire CALL DOES NOT EXIST
		if(calltype.contains("f")){
			return true;
		}
		else{
		
			return false;
		}
	}
	
	private boolean CallTypeEMS(String calltype) {
		//RETURN TRUE IF fire CALL DOES NOT EXIST
		if(calltype.contains("e")){
			return true;
		}
		else{
		
			return false;
		}
	}

	public static String getId(String data) {
		int index = data.indexOf("STRUCT");
		//System.out.println(index);
		return data.substring(index - 38, index + 35);
	}

	public static String getIdBLS(String data) {
		int index = data.indexOf("BASIC");
		
		return data.substring(index - 37, index + 36);
	}
	public static String getIdALS(String data) {
		int index = data.indexOf("ALS");
		
		return data.substring(index - 37, index + 36);
	}
	public static String getIdMVA(String data) {
		int index = data.indexOf("MVA");
		
		return data.substring(index - 31, index + 42);
	}
	public static String getIdFireV(String data) {
		int index = data.indexOf("VEHICL");
		return data.substring(index - 38, index + 35);
	}
	public static String getIdFireA(String data) {
		int index = data.indexOf("FIRE");
		return data.substring(index - 31, index + 42);
	}
	
	public static String getIdFireM(String data) {
		int index = data.indexOf("MUTUAL AID -");
		return data.substring(index - 31, index + 42);
	}
	public static String getIdGas(String data) {
		int index = data.indexOf("ODOR/LEAK");
		return data.substring(index - 35, index + 38);
	}
	public static String getIdfother(String data) {
		int index = data.indexOf("FIRE - OTHER");
		return data.substring(index - 31, index + 42);
	}
	public static String getIdwoods(String data) {
		int index = data.indexOf("FIRE - WOODS");
		return data.substring(index - 31, index + 42);
	}
	
	public static String getIdRIT(String data) {
		int index = data.indexOf("FIRE - RIT");
		return data.substring(index - 31, index + 42);
	}
	public static String getIdcover(String data) {
		int index = data.indexOf("COVR A");
		return data.substring(index - 31, index + 42);
	}
	public static String getIdhazmat(String data) {
		int index = data.indexOf("HAZMAT");
		return data.substring(index - 31, index + 42);
	}
	public void run() {
		try {
			this.jpcap.setFilter("tcp", true);
			calls = new HashMap<String, String>();
		} catch (Exception e) {
			System.out.println("error");
		}
		this.jpcap.loopPacket(-1, new Reciever());
	}
}


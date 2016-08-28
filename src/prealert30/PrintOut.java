package prealert30;

import java.util.HashSet;
import java.util.Set;
import java.util.LinkedHashSet;

public class PrintOut {

	public long timeSec;
	private int sign = 0;
        public HashSet units;
        public LinkedHashSet station1;
        public LinkedHashSet type1;
        public LinkedHashSet location1;

	public String data = "";
        private static String betaString1;
	public static String betaString;
	public static String colorString;

	public static final int ENGINEBOX = 1;
    public static final int streetalarm = 2;
	public static final int FIRSTDUE = 3;
	public static final int engine = 4;
	public static final int ambo = 5;
	public static final int medic = 6;
	public static String station = "";
	public static String location = "";
	public static String type = "";
	public String ID = "";
	public String map = "";


	public PrintOut(String header) {
		ID = Reciever.getId(header);
		this.data += header;
		System.out.println(ID);
	}

	public void addPart(String dataIn) {

		System.out.println("Adding part to id: " + ID);
		this.data += dataIn;
                
		if (data.contains("Station:")) {
			int temp = data.indexOf("Station:");
			station = new String(data.substring(temp + 8,(temp + 8) + 2));
			station += ".";
                        System.out.println("Station :" + station);
		}
		if (data.contains("Final Type:")) {
			int temp = data.indexOf("Final Type:");
			type = new String(data.substring(temp + 11,(temp +11) + 6));
			type += ".";
                           System.out.println("Type :" + type);
		}
		if (data.contains("Location:")) {
			int temp = data.indexOf("Location:");
			location = new String(data.substring(temp + 9,(temp +9) + 25));
			location += ".";
                       
			System.out.println("Location :" + location);
		}
           
            
			
		// String soundString = "";
		// String betaString = "";
				
		if ((type.contains("APTF")) || (type.contains("HOUSEF"))
				|| (type.contains("BUILDF")) || (type.contains("APTF"))
				|| (type.contains("BUILDT")) || (type.contains("APTT"))
				|| (type.contains("APTT")) || (type.contains("BUILDT"))
				|| (type.contains("HOUSET")) || (type.contains("HOUSET"))
				|| (type.contains("HOUSEF")) || (type.contains("BUILDF"))
				|| (type.contains("TOWNHF")) || (type.contains("TOWNHF"))
				|| (type.contains("TOWNHT")) || (type.contains("TOWNHT")) && (station.contains("46")) && (units.contains("PE846"))){ 
                                        colorString = "yellow";  
                                        final String soundString = "FIRSTDUE";
					final String betaString1 = "FIRTS DUE";
                                        new AudioPlayer("sounds/"+ soundString +".wav").start();
						sign = FIRSTDUE;
                                                betaString = betaString1;
						System.out.println("First Due");
			}
	    
                else if ((type.contains("APTF")) || (type.contains("HOUSEF"))
						|| (type.contains("BUILDF")) || (type.contains("APTF"))
						|| (type.contains("BUILDT")) || (type.contains("APTT"))
						|| (type.contains("APTT")) || (type.contains("BUILDT"))
						|| (type.contains("HOUSET")) || (type.contains("HOUSET"))
						|| (type.contains("HOUSEF")) || (type.contains("BUILDF"))
						|| (type.contains("TOWNHF")) || (type.contains("TOWNHF"))
						|| (type.contains("TOWNHT")) || (type.contains("TOWNHT")) && (station.contains("!=46")) && (units.contains("PE846"))){
						colorString = "green";
                                                final String soundString = "ENGINEBOX";
						final String betaString1 = "Box Alarm";
                                                new AudioPlayer("sounds/"+ soundString +".wav").start();
						sign = ENGINEBOX;
                                                betaString = betaString1;
						System.out.println("Box Alarm");
			}
					
	    else if ((type.contains("APTG")) || (type.contains("HOUSEG"))
							|| (type.contains("BUILDG")) || (type.contains("COLAPS"))
							|| (type.contains("CONFSP")) || (type.contains("PLANE"))
							|| (type.contains("RTASK")) || (type.contains("STREET"))
							|| (type.contains("TOWNHG")) && (units.contains("PE846"))){
						colorString = "orange";
                                                final String soundString = "streetalarm";
						final String betaString1 = "Street Alarm";
                                                new AudioPlayer("sounds/"+ soundString +".wav").start();
						sign = streetalarm;
                                                betaString = betaString1;
						System.out.println("Street Alarm");
			}
            else if ((units.contains("PE846")) && (units.contains("A846")) && (units.contains("MD846"))){
			colorString = "red";
                        final String soundString = "engineambomedic";
			final String betaString1 = "Engine : Ambo : Medic ";
                        new AudioPlayer("sounds/"+ soundString +".wav").start();
			sign = engine;
                        betaString = betaString1;
                        System.out.println("Engine Ambo Medic");
			}
            else if ((units.contains("PE846")) && (units.contains("A846"))){
                        colorString = "red";
			final String soundString = "engineambo";
			final String betaString1 = "Engine : Ambo ";
                        new AudioPlayer("sounds/"+ soundString +".wav").start();
			sign = engine;
                        betaString = betaString1;
                        System.out.println("Engine Ambo");
			}
            else if ((units.contains("PE846")) && (units.contains("MD846"))){
                        colorString = "red";
			final String soundString = "enginemedic";
			final String betaString1 = "Engine : Medic ";
                        new AudioPlayer("sounds/"+ soundString +".wav").start();
			sign = engine;
                        betaString = betaString1;
                        System.out.println("Engine Medic");
			}
            else if ((units.contains("A846")) && (units.contains("MD846"))){
                        colorString = "red";
			final String soundString = "ambomedic";
			final String betaString1 = "Ambo : Medic ";
                        new AudioPlayer("sounds/"+ soundString +".wav").start();
			sign = ambo;
                        betaString = betaString1;
                        System.out.println("Ambo Medic");
			}
            else if (units.contains("PE846")){
                        colorString = "red";
			final String soundString = "engine";
			final String betaString1 = "Engine";
                        new AudioPlayer("sounds/"+ soundString +".wav").start();
			sign = engine;
                             betaString = betaString1;
                        System.out.println("Engine");
			}  
            else if (units.contains("A846")){
                                colorString = "red";
				final String soundString = "ambo";
				final String betaString1 = "Ambo";
                                new AudioPlayer("sounds/"+ soundString +".wav").start();
				sign = ambo;
                                betaString = betaString1;
                                System.out.println("Ambo");
			}
            else if (units.contains("MD846")){
                            colorString = "red";
				final String soundString = "medic";
				final String betaString1 = "Medic";
                                new AudioPlayer("sounds/"+ soundString +".wav").start();
				sign = medic;
                                betaString = betaString1;
                                System.out.println("Medic");
			}


	}
	


	
	public String toString() {
		return (ID + location + station);
	}

	public int hashCode() {
		return toString().hashCode();
	}
}

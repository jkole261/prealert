package prealert30;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.*;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

@SuppressWarnings("serial")
public class GUI extends JPanel{
	NetworkInterface[] devices;
	NetworkInterface deviceName;
	Reciever reciever;
	JpcapCaptor jpcap;

	AudioFormat audioFormat;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;
	boolean stopPlayback = false;

	public static void main(String args[]){
		new GUI();
		JButton exitbt = new JButton("EXIT");
		JFrame frame = new JFrame("Program Started"); //This is for when we extract to jar to show it is running
		frame.setSize(100, 100);
		exitbt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		
		frame.add(exitbt);
		
		frame.setVisible(true);
	//	frame.setDefaultCloseOperation(1);
	}

	public GUI(){

		devices = JpcapCaptor.getDeviceList();
		for (int i = 0; i < devices.length; i++) {
			System.out.println(devices[i].description);
		}
		for(int i = 0; i < devices.length; i ++){
			System.out.println(devices[i].datalink_name+"/"+devices[i].datalink_description);
		}
		deviceName = devices[0];
		reciever = new Reciever();
		try{
			jpcap = JpcapCaptor.openDevice(deviceName, 2000, true, 100);
		}
		catch (Exception e){
			System.out.println("errorJPcapCreation");
		}
		reciever.jpcap=jpcap;
		reciever.start();	
		
	//	new SetBoard(SetBoard.DEFAULT).start();
	}
}
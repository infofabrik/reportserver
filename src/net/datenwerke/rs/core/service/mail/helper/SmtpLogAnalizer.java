package net.datenwerke.rs.core.service.mail.helper;

import java.util.Scanner;

public class SmtpLogAnalizer {

	private final String log;

	public SmtpLogAnalizer(String log){
		this.log = log;
	}
	
	public String getLogWithoutData(){
		StringBuilder builder = new StringBuilder();
		
		if(null == log)
			return "";
		
		Scanner scanner = new Scanner(log);
		boolean inDataSection = false;
		while(scanner.hasNextLine()){
			String line = scanner.nextLine().trim();
			
			if(! inDataSection && "DATA".equals(line)){
				inDataSection = true;
				continue;
			}
			if(inDataSection && ".".equals(line)){
				inDataSection = false;
				continue;
			}
				
			if(! inDataSection && ! line.startsWith("DEBUG"))
				builder.append(line).append("\n");
		}
		
		return builder.toString();
	}
	
	public String getLog(){
		return log;
	}
	
	public String getMessageHeader(){
		StringBuilder builder = new StringBuilder();
		
		if(null == log)
			return "";
		
		Scanner scanner = new Scanner(log);
		boolean inHeaderSection = false;
		while(scanner.hasNextLine()){
			String line = scanner.nextLine().trim();
			
			if(! inHeaderSection && "DATA".equals(line)){
				inHeaderSection = true;
				continue;
			}
			if(inHeaderSection && "".equals(line)){
				inHeaderSection = false;
				continue;
			}
				
			if(inHeaderSection && ! line.startsWith("DEBUG"))
				builder.append(line).append("\n");
		}
		
		return builder.toString();		
	}
}

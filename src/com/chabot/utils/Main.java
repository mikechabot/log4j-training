package com.chabot.utils;

import org.apache.log4j.Logger;

public class Main {

    public static void main(String[] args) {
        
    	Logger logger = Logger.getLogger(Main.class);
    	
    	logger.debug("Entering Main");
    	logger.warn("Your system might suck."); 
    	    	   	
    	logger.info("** Starting SysInfo **");    	 
    	SysInfo sys = new SysInfo();
    	sys.getJRE();
    	sys.getDisks();    	
    	logger.info("**Ending SysInfo**");
    	 
    	logger.error("I don't feel so hot...");
    	logger.error("Oh nos I'm crashing...");
    	logger.fatal("(>_<)");
    	logger.debug("Leaving Main");
    	
    }
    
}

package com.chabot.utils;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import org.apache.log4j.Logger;

public class SysInfo {
	
	Logger logger = Logger.getLogger(SysInfo.class);		
			
	Map<String, Disk> disks =  new HashMap<String, Disk>();	
	
	public SysInfo() {
		findRoots();
	}
	
	public Collection<Disk> getDiskMap() {
		return disks.values();
	}
	
	public void getJRE() {		
		logger.debug("Getting runtime instance");
		Runtime r = Runtime.getRuntime();
		logger.info("---JRE Information---");
		logger.info("Available CPUs: " + r.availableProcessors()); // the maximum number of processors available to the JVM
		logger.info("   Free Memory: " + r.freeMemory()); 			// an approximation of the total amount of memory currently available for future use
		logger.info("  Total Memory: " + r.totalMemory()); 			// the total amount of memory currently available for current and future objects
		logger.info("    Max Memory: " + r.maxMemory()); 				// the maximum amount of memory that the JVM will attempt to use		
	}
	
	public void getDisks() {	
		for (Disk each : getDiskMap()) {			
			logger.info("---Disk Information---");
			logger.info(each.getPath() + "   Free Space: " + each.getFreeSpace());
			logger.info(each.getPath() + "  Total Space: " + each.getDiskSize());
			logger.info(each.getPath() + " Usable Space: " + each.getUsableSpace());
		}
	}	
	
	public void findRoots() {
		
		File[] roots;
		roots = File.listRoots();		
		
        for (File root : roots) {        	
        	Disk disk = new Disk();
        	 if(root.getTotalSpace() > 0) {
        		logger.debug("Setting path"); 
        		disk.setPath(root.getAbsolutePath());		
        		logger.debug("Setting diskSize"); 
        		disk.setDiskSize(root.getTotalSpace());		
        		logger.debug("Setting freeSpace");        		
        		disk.setFreeSpace(root.getFreeSpace());		
        		logger.debug("Setting usableSpace");	
        		disk.setUsableSpace(root.getUsableSpace());	  
        		logger.trace("Putting 'disk' in Map");
        		disks.put(disk.getPath(), disk);        				             
        	 }
        }    	 
	}
	
	private class Disk {	
		
		String path;
		long size; 
		long usableSpace;
		long freeSpace;		
		
		// root path (e.g. "C:\")
		public void setPath(String path) {
			this.path = path;
		}
		
		public String getPath() {
			return path;
		}
		
		// total space on the disk or partition
		public void setDiskSize(long size) {
			this.size = size;
		}
		
		public long getDiskSize() {
			return size;
		}
		
		// number of unallocated bytes on the disk or partition
		public void setFreeSpace(long freeSpace) {
			this.freeSpace = freeSpace;
		}
		
		public long getFreeSpace() {
			return freeSpace;
		}
		
		// total space available to JVM (think write permissions, OS reserved space, etc)
		public void setUsableSpace(long usableSpace) {
			this.usableSpace = usableSpace;
		}
		
		public long getUsableSpace() {
			return usableSpace;
		}			
	}	
}


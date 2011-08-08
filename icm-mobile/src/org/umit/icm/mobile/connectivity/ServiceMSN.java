/**
 * Copyright (C) 2011 Adriano Monteiro Marques
 *
 * Author:  Zubair Nabi <zn.zubairnabi@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
 */

package org.umit.icm.mobile.connectivity;


import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.umit.icm.mobile.process.Globals;

/**
 * This is the MSN Service class. Holds {@link ServiceMSN#connect()},
 * {@link ServiceMSN#getService()} and {@link ServiceMSN#getService()} methods.
 */

public class ServiceMSN {
	

	/**
	 * Returns a MSN Response String.
	 * 
	 *	 	                           	                          		             	            
	           
	@return      String
	 *
	 
	@see TCPClient
	 */
	public static String connect() throws SocketException, IOException {
		
		Globals.tcpClientConnectivity.openConnection("messenger.hotmail.com"
				, 1863);
		Globals.tcpClientConnectivity.writeLine(Globals.servicePacketsMap.get("msn"));
		String reply = Globals.tcpClientConnectivity.readLines();
		Globals.tcpClientConnectivity.closeConnection();
		return reply;
	}
	
	/**
	 * Returns a {@link Service} object for MSN. 
	 * 
	 *	 

	@see  Service
	 *  	                          		              
	            
	@return      Service
	 */	
	public static Service getService() {
		List<Integer> ports = new ArrayList<Integer>();
		ports.add(1863);												
		return new Service("msn", ports, "messenger.hotmail.com" , "open", "true");
	}
	
	/**
	 * Returns a String for service scanning URL. 
	 * 
	 *	  	                          		              
	            
	@return      String
	 */	
	public static String getServiceURL() {
		return "messenger.hotmail.com";
	}
}
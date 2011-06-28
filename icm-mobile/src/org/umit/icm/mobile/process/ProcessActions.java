/**
 * Copyright (C) 2011 Adriano Monteiro Marques
 *
 * Author:  Zubair Nabi <zn.zubairnabi@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either Test 2 of the License, or
 * (at your option) any later Test.
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

package org.umit.icm.mobile.process;

import java.io.IOException;
import java.util.List;

import org.umit.icm.mobile.proto.MessageProtos.Event;
import org.umit.icm.mobile.proto.MessageProtos.NewTestsResponse;
import org.umit.icm.mobile.proto.MessageProtos.NewVersionResponse;
import org.umit.icm.mobile.proto.MessageProtos.ResponseHeader;

public class ProcessActions {	
	
	public static void updateAgentVersion(ResponseHeader header) 
		throws IOException {
		
		if (header.getCurrentVersionNo() 
				> Globals.versionManager.getAgentVersion()) {
			Globals.versionManager.setAgentVersion(header.getCurrentVersionNo());
			// TODO call Aggregator checkVersion webservice
		}
	}
	
	public static void updateTestsVersion(ResponseHeader header)
		throws IOException {
		
		if (header.getCurrentTestVersionNo() 
				> Globals.versionManager.getTestsVersion()) {
			Globals.versionManager.setTestsVersion(header.getCurrentTestVersionNo());
			// TODO call aggregator newTests webservice
		}
	}
	
	public static void newTestsResponseAdd(NewTestsResponse response) throws IOException, RuntimeException {		
		for (int i = 0 ; i < response.getTestsCount() ; i++) {		
				if(response.getTests(i).equals(null))
					break;
				Globals.testManager.addTest(MessageConversion.testToTestObject(response.getTests(i)));			
		}		
		Globals.testManager.writeTests();
	}
	
	/*public static void initializeRequestHeader() throws IOException, RuntimeException {
		Globals.requestHeader = RequestHeader.newBuilder()
		.setAgentID(Globals.runtimeParameters.getAgentID())
		.setToken(Globals.runtimeParameters.getToken())
		.build();
	}*/
	
	public static boolean updateAgent(NewVersionResponse newVersionResponse) {
		// TODO patch current binary
		return true;
	}
	
	public static boolean updateTests(NewTestsResponse newTestsResponse) {
		try {
			newTestsResponseAdd(newTestsResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean updateEventsList(List<Event> events) {
		for(int i = 0 ; i < events.size(); i++)
			Globals.eventsList.add(events.get(i));
		return true;
	}
}
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

package org.umit.icm.mobile.p2p;

import java.io.IOException;

import org.umit.icm.mobile.process.Globals;
import org.umit.icm.mobile.proto.MessageProtos.AuthenticatePeer;

import android.util.Log;

public class P2PTesting {
	public static void testRequestResponse() throws IOException {
		String ip = "202.206.64.11";
		int port = 3128;
		Globals.tcpClientConnectivity.openConnection(ip, port);
		//Globals.tcpClientConnectivity.writeLine("hello");
		Globals.tcpClientConnectivity.writeLine(MessageBuilder.generateMessage(
				1, getTestMessage()));
		byte [] response = Globals.tcpClientConnectivity.readBytes2();
		Log.w("###Response: ", Integer.toString(response.length));	
		if(response.length!=0) {
			byte [] idbyte = MessageBuilder.getSubArray(response, 0, 3);
			Log.w("###idbyte: ", Integer.toString(idbyte.length));
			
			//byte [] lenbyte = MessageBuilder.getSubArray(response, 4, 8);
			//Log.w("###lenbyte: ", Integer.toString(lenbyte.length));
			
			int id = MessageBuilder.byteArrayToInt(idbyte);
			//int len = MessageBuilder.byteArrayToInt(lenbyte);
			//AuthenticatePeerResponse authenticatePeerResponse
			//= AuthenticatePeerResponse.parseFrom();
			Log.w("###ID: ", Integer.toString(id));				
			//Log.w("###Length: ", Integer.toString(len));
		} else {
			Log.w("### ", "Blank response");
		}
				
		Globals.tcpClientConnectivity.closeConnection();
        			
	}
	
	public static byte[] getTestMessage() {
		AuthenticatePeer authenticatePeer = AuthenticatePeer.newBuilder()
		.setAgentID(10)
		.setAgentPort(8000)
		.setAgentType(1)
		.setCipheredPublicKey("cipheredPublicKey")
		.build();
		return authenticatePeer.toByteArray();
	}
}
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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class TCPClientBytes {
	
	private Socket socket;
    private DataOutputStream dataOutputStream;
    private BufferedReader bufferedReader; 
    
    public TCPClientBytes() {
    	socket = null;
    	dataOutputStream = null;
    	bufferedReader = null;
    }
    
    public void openConnection(String ip, int port) throws UnknownHostException, IOException {
    	socket = new Socket(ip, port);
    	dataOutputStream = new DataOutputStream(socket.getOutputStream());
    	bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public String readLine() throws IOException {
    	return bufferedReader.readLine();
    }
    
    public List<String> readLines() throws IOException {
    	List<String> lines = new ArrayList<String>();
    	String line = bufferedReader.readLine();
    	while(line != null) {
    		line = bufferedReader.readLine();
    		lines.add(line);
    	}
    	
    	return lines;
    }
    
    public void writeBytes(byte[] bytes) throws IOException {
    	dataOutputStream.write(bytes);    	
    }
    
    public InetAddress getInetAddress() {
    	return socket.getInetAddress();
    }
    
    public int getPort() {
    	return socket.getPort();
    }
    public void closeConnection() throws IOException {
    	dataOutputStream.close();
    	bufferedReader.close();
    	socket.close();
    }
    
}
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
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpException;
import org.umit.icm.mobile.aggregator.AggregatorRetrieve;
import org.umit.icm.mobile.process.Constants;
import org.umit.icm.mobile.process.Globals;
import org.umit.icm.mobile.proto.MessageProtos.SendWebsiteReport;
import org.umit.icm.mobile.proto.MessageProtos.WebsiteReport;
import org.umit.icm.mobile.utils.SDCardReadWrite;

import android.util.Log;

/**
 * This is the WebsiteConnectivity class which extends {@link AbstractConnectivity}.
 */

public class WebsiteConnectivity extends AbstractConnectivity {		

	/**
	 * This is the default constructor. Populates the Websites list with the
	 * list from {@link Constants}.
	 */
	public WebsiteConnectivity() {
		super();		
	}
	
	/**
	 * scan method which overrides the {@link AbstractConnectivity#scan}. 
	 * Iterates through the websites list and for each website calls
	 * {@link WebsiteOpen#openURLConnection(String)},
	 * {@link WebsiteOpen#getHeaders(URLConnection)} and
	 * {@link WebsiteOpen#getContent(URLConnection)}. Passes the website content
	 * to {@link WebsiteConnectivity#clean(String, String, Map, long)} 
	 * @throws HttpException 
	 * 
	 * 
	 *
	 @see         WebsiteOpen
	 */
	@Override()
	public void scan() throws IOException {
		Website website = new Website();
		WebsiteReport websiteReport = WebsiteReport.getDefaultInstance();
		long totalSizeofContent = 0;
		long totalTime = 0;
		double averageThroughput = 0;
		Iterator<Website> iterator = Globals.runtimeList.websitesList.iterator();
		while(iterator.hasNext()) {
			website = iterator.next();
			WebsiteDetails websiteDetails = new WebsiteDetails(website);
			try {
				websiteDetails.setup();
				websiteReport = websiteDetails.getWebsiteReport();
				totalSizeofContent += websiteDetails.getWebsite().getContent().getBytes().length;
				totalTime += websiteDetails.getWebsite().getTimeTakentoDownload();

				SDCardReadWrite.writeWebsiteReport(Constants.WEBSITES_DIR, websiteReport);									
				
				if(Constants.DEBUG_MODE) {
					Log.w("######BW", Integer.toString(websiteReport.getReport().getBandwidth()));
					Log.w("######ResponseTime", Integer.toString(websiteReport.getReport().getResponseTime()));
					Log.w("######Code", Integer.toString(websiteReport.getReport().getStatusCode()));
					Log.w("######URL", websiteReport.getReport().getWebsiteURL());
					Log.w("######IP", websiteDetails.getIp());
					for (int i = 0; i < websiteDetails.getNsDNSRecord().length; i++) {
						Log.w("######NS Record", websiteDetails.getNsDNSRecord()[i]);
					}
					for (int i = 0; i < websiteDetails.getaDNSRecord().length; i++) {
						Log.w("######A Record", websiteDetails.getaDNSRecord()[i]);
					}
					for (int i = 0; i < websiteDetails.getSoaDNSRecord().length; i++) {
						Log.w("######SOA Record", websiteDetails.getSoaDNSRecord()[i]);
					}
				}
				SendWebsiteReport sendWebsiteReport = SendWebsiteReport.newBuilder()
				.setReport(websiteReport)
				.build();				
				if(Globals.aggregatorCommunication != false && website.getCheck()=="true") {
					AggregatorRetrieve.sendWebsiteReport(sendWebsiteReport);
					website.setCheck("false");
				}
				
			} catch(UnknownHostException e) {
				e.printStackTrace();
				throw new IOException("No Internet");
			} catch(HttpException e) {
				e.printStackTrace();
				throw new IOException("No Internet");
			} catch(IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		averageThroughput = totalSizeofContent / totalTime;
		Globals.runtimeParameters.setAverageThroughout(averageThroughput);		

	};		
}
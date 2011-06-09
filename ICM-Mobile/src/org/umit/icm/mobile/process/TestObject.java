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

import java.io.Serializable;

public class TestObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3221295685377211053L;
	private long testID;
	private String websiteURL;
	private int serviceCode;
	private long executeAtTimeUTC;
	
	public TestObject(long testID, String websiteURL, int serviceCode,
			long executeAtTimeUTC) {
		super();
		this.testID = testID;
		this.websiteURL = websiteURL;
		this.serviceCode = serviceCode;
		this.executeAtTimeUTC = executeAtTimeUTC;
	}

	public TestObject() {
		testID = 0;
		websiteURL = null;
		serviceCode = 0;
		executeAtTimeUTC = 0;
		
	}

	public long getTestID() {
		return testID;
	}

	public void setTestID(long testID) {
		this.testID = testID;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}

	public int getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}

	public long getExecuteAtTimeUTC() {
		return executeAtTimeUTC;
	}

	public void setExecuteAtTimeUTC(long executeAtTimeUTC) {
		this.executeAtTimeUTC = executeAtTimeUTC;
	}
	
	public boolean isEqual(TestObject testObject) {
		if(this.executeAtTimeUTC == testObject.executeAtTimeUTC
				&& this.serviceCode == testObject.serviceCode
				&& this.testID == testObject.testID
				&& this.websiteURL.equals(testObject.websiteURL))
			return true;
		return false;
	}
}
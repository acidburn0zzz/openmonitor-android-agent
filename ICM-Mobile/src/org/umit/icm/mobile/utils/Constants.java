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

package org.umit.icm.mobile.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	public static String MY_PUBLIC_KEY_FILE = "myPublicKey.pub";
	public static String MY_PRIVATE_KEY_FILE = "myPrivateKey.priv";
	public static String MY_SECRET_KEY_FILE = "mySecretKey.sec";
	public static String PEER_PUBLIC_KEY_FILE = "PublicKey.pub";
	public static int RSA_KEY_SIZE = 1024;
	public static int AES_KEY_SIZE = 128;
	public static String KEYS_DIR = "/keys";
	public static String PARAMETERS_DIR = "/params";
	public static String INTERVAL_FILE = "interval.param";
	public static String SCAN_FILE = "scan.param";
	public static int DEFAULT_SCAN_INTERVAL = 10;
	public static String DEFAULT_SCAN_STATUS = "On";
	public static String WEBSITES_DIR = "/websites";
	public static String WEBSITE_FILE = "-site.web";
	public static List<String> WEBSITE_LIST = new ArrayList<String>()
	{/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		add("http://www.google.com");
		add("http://www.facebook.com");
		add("http://www.youtube.com");
		add("http://www.twitter.com");
		add("http://www.yahoo.com");
		add("http://www.cnn.com");
		add("http://www.bbc.com");
		add("http://www.gmail.com");
		add("http://www.hotmail.com");
		add("http://www.linkedin.com");
	}};
	

}
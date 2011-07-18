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

package org.umit.icm.mobile.test.p2p;

import junit.framework.Assert;

import org.umit.icm.mobile.p2p.MessageBuilder;

import android.test.AndroidTestCase;

public class MessageBuilderTests extends AndroidTestCase {
	
	public void testByteArrayConversion() throws Throwable {
		int integer = 35;
		byte[] byteArray = MessageBuilder.intToByteArray(integer);
		Assert.assertEquals(integer, MessageBuilder.byteArrayToInt(byteArray));
	}
	
	public void testByteArrayAppend() throws Throwable {
		byte[] arrayA = new byte[2];
		byte[] arrayB = new byte[2];
		byte[] arrayC = new byte[4];
		
		arrayA[0] = (byte) 1;
		arrayA[1] = (byte) 2;
		arrayB[0] = (byte) 3;
		arrayB[1] = (byte) 4;
		
		arrayC[0] = (byte) 1;
		arrayC[1] = (byte) 2;
		arrayC[2] = (byte) 3;
		arrayC[3] = (byte) 4;

		Assert.assertEquals(arrayC.length, arrayA.length + arrayB.length);
		Assert.assertTrue(byteArrayEquals(arrayC, MessageBuilder.byteArrayAppend(arrayA, arrayB)));
	}
	
	private boolean byteArrayEquals(byte[] arrayA, byte[] arrayB) {
		if(arrayA.length != arrayB.length)
			return false;
		for(int i = 0; i < arrayA.length ; i++) {
			if(arrayA[i] != arrayB[i])
				return false;
		}
		return true;					
	}
}
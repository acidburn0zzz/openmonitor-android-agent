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

package org.umit.icm.mobile.gui;


import org.umit.icm.mobile.R;
import org.umit.icm.mobile.maps.GoogleMaps;
import org.umit.icm.mobile.maps.OSMMaps;
import org.umit.icm.mobile.process.Globals;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * This is the map activity. 
 */

public class MapActivityTab extends MapActivity{
    /** Called when the activity is first created. */
	String package1;	
	LocationManager locationManager;
	Location location;
	GeoPoint geoPoint;
	GeoPoint osmGeoPoint;
	GoogleMaps googleMap;
	OSMMaps osmMap; 
	
	/**
	 * The onCreate method which makes a call to either {@link GoogleMaps}
	 * or {@link OSMMaps}
	 * 
	 * 
	 
	 @see GoogleMaps
	 *
	 
	 @see OSMMaps
	 */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                       
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if((locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        		|| (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))) {
			if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER
						 , 0, 0, GPSLocationListener);
				location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				
			} else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER
						, 0, 0, networkProviderLocationListener);
				location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);				
			}				
	        
	        if(Globals.mapView.equals("Google")) {
	        	geoPoint = GoogleMaps.getGeoPoint(location.getLatitude()
						, location.getLongitude());
	        	googleMap = new GoogleMaps();                        			
	        	setContentView(googleMap.getView(this
	        			, location.getLatitude(), location.getLongitude()));
	            
	        } else if(Globals.mapView.equals("OSMDroid")) {                                                
	            osmMap = new OSMMaps();
	            setContentView(osmMap.getView(this
	            		, location.getLatitude(), location.getLongitude()));
	        }         
        }
        else {
        	CharSequence text = getString(R.string.location_disabled);     		
    		int duration = Toast.LENGTH_SHORT;

    		Toast toast = Toast.makeText(MapActivityTab.this, text, duration);
    		toast.show();
        }
    }
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	LocationListener GPSLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
        	 if(Globals.mapView.equals("Google")) {
        		 geoPoint = GoogleMaps.getGeoPoint(location.getLatitude()
     					, location.getLongitude());
             	
        		 setContentView(googleMap.getView(MapActivityTab.this
        				 ,location.getLatitude(), location.getLongitude()));
                 
             } else if(Globals.mapView.equals("OSMDroid")) {                                                
            	 geoPoint = GoogleMaps.getGeoPoint(location.getLatitude()
      					, location.getLongitude());
                 setContentView(osmMap.getView(MapActivityTab.this
                 		, location.getLatitude(), location.getLongitude()));
             }
                	     
        }

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			
    		CharSequence text = getString(R.string.gps_disabled);     		
    		int duration = Toast.LENGTH_SHORT;

    		Toast toast = Toast.makeText(MapActivityTab.this, text, duration);
    		toast.show();
		}

		@Override
		public void onProviderEnabled(String provider) {			
    		CharSequence text = getString(R.string.gps_enabled);     		
    		int duration = Toast.LENGTH_SHORT;

    		Toast toast = Toast.makeText(MapActivityTab.this, text, duration);
    		toast.show();
			
		}
    };
    
    LocationListener networkProviderLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
        
        	geoPoint = GoogleMaps.getGeoPoint(location.getLatitude()
					, location.getLongitude());
        	 setContentView(googleMap.getView(MapActivityTab.this
        			 , location.getLatitude(), location.getLongitude()));
        }

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
			
		}

		@Override
		public void onProviderDisabled(String provider) {
		
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
    };
}
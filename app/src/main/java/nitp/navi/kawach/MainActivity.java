package nitp.navi.kawach;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.telephony.SmsManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;


import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
    ImageView high, mid, low;
    protected LocationManager locationManager;

    ImageView alert_h_btn, alert_m_btn, alert_l_btn;
    SharedPreferences Pref;
    String txt = "Help! \n I'm stuck and need assistance at \n ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        high = (ImageView) findViewById(R.id.alert_h_iv);
        mid = (ImageView) findViewById(R.id.alert_m_iv);
        low = (ImageView) findViewById(R.id.alert_l_iv);

        high.setVisibility(View.VISIBLE);
        mid.setVisibility(View.GONE);
        low.setVisibility(View.GONE);

        setback();
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        alert_l_btn = (ImageView)findViewById(R.id.alert_l_iv);
        alert_m_btn = (ImageView)findViewById(R.id.alert_m_iv);
        alert_h_btn = (ImageView)findViewById(R.id.alert_h_iv);

        alert_l_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "location sent!!", Toast.LENGTH_SHORT).show();
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MINIMUM_TIME_BETWEEN_UPDATES,
                        MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                        new MainActivity.MyLocationListener()
                );
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {
                    String message = String.format("https://www.google.com/maps/?q=%1$s,%2$s", location.getLongitude(), location.getLatitude());

                    Log.e("check","first : "+Pref.getString("phn_1","0"));
                    sendSMS(Pref.getString("phn_1","0"), txt, message);
                    Log.e("check","second : "+Pref.getString("phn_2","0"));
                    sendSMS(Pref.getString("phn_2","0"), txt, message);
                    Log.e("check","third : "+Pref.getString("phn_3","0"));
                    sendSMS(Pref.getString("phn_3","0"), txt, message);

                }



            }
        });

        alert_m_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "location sent!!", Toast.LENGTH_SHORT).show();
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MINIMUM_TIME_BETWEEN_UPDATES,
                        MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                        new MainActivity.MyLocationListener()
                );
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {
                    String message = String.format("https://www.google.com/maps/?q=%1$s,%2$s", location.getLongitude(), location.getLatitude());

                    Log.e("check","first : "+Pref.getString("phn_1","0"));
                    sendSMS(Pref.getString("phn_1","0"), txt, message);
                    Log.e("check","second : "+Pref.getString("phn_2","0"));
                    sendSMS(Pref.getString("phn_2","0"), txt, message);
                    Log.e("check","third : "+Pref.getString("phn_3","0"));
                    sendSMS(Pref.getString("phn_3","0"), txt, message);

                }


            }
        });

        alert_h_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "sending location!", Toast.LENGTH_SHORT).show();
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MINIMUM_TIME_BETWEEN_UPDATES,
                        MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                        new MainActivity.MyLocationListener()
                );
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {
                    String message = String.format("https://www.google.com/maps/?q=%1$s,%2$s", location.getLongitude(), location.getLatitude());

                    Log.e("check","first : "+Pref.getString("phn_1","0"));
                    sendSMS(Pref.getString("phn_1","0"), txt, message);
                    Log.e("check","second : "+Pref.getString("phn_2","0"));
                    sendSMS(Pref.getString("phn_2","0"), txt, message);
                    Log.e("check","third : "+Pref.getString("phn_3","0"));
                    sendSMS(Pref.getString("phn_3","0"), txt, message);

                }
                else
                    Log.e("else","no loc");



            }
        });

        Pref = getSharedPreferences("bbt", MODE_PRIVATE);

        if(checkIfAlreadyhavePermission()==false){
            requestForSpecificPermission();

        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent setting_intent = new Intent(this, settings_activity.class);
            startActivityForResult(setting_intent, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {


        } else if (id == R.id.nav_report_abuse) {
            Intent report_page = new Intent(this, report_abuse_activity.class);
            startActivityForResult(report_page, 0);

        } else if (id == R.id.nav_emergency_numbers) {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_about) {
            Intent about = new Intent(this, about_activity.class);
            startActivityForResult(about, 0);

        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



private class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            String message = String.format(
                    "Unexpected Error \n Try Again"
            );
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        }
        public void onStatusChanged(String s, int i, Bundle b) {
            Toast.makeText(MainActivity.this, "Provider status changed \n Try Again",
                              Toast.LENGTH_LONG).show();
        }

        public void onProviderDisabled(String s) {
            Toast.makeText(MainActivity.this,
                              "Provider disabled by the user. GPS turned off \n Try Again",
                            Toast.LENGTH_LONG).show();
        }
        public void onProviderEnabled(String s) {
            Toast.makeText(MainActivity.this,
                              "Provider enabled by the user. GPS turned on \n Try Again",
                            Toast.LENGTH_LONG).show();
        }

    }

    private void sendSMS(String phoneNumber, String txt ,String message) {
        SmsManager sms = SmsManager.getDefault();
        StringBuffer smsBody = new StringBuffer();
        smsBody.append(txt);
        smsBody.append(message);
        //smsBody.append(myLocation.getLatitude());
        //smsBody.append(",");
        //smsBody.append(myLocation.getLongitude());
        sms.sendTextMessage(phoneNumber, null, smsBody.toString(), null, null);
        Log.e("sms",smsBody.toString());
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private int findCrime(String testdistrict) {
        JSONArray jsonArray=null;
        int temp = 0;

        try {

            InputStream is = getResources().getAssets().open("crime_record.json");

            int size = is.available();

            byte[] data = new byte[size];

            is.read(data);

            is.close();

            String json = new String(data, "UTF-8");

            jsonArray=new JSONArray(json);

        }catch (IOException e){

            e.printStackTrace();
            Log.e("catch1","c");

        }catch (JSONException je){

            je.printStackTrace();
            Log.e("catch2","c");

        }
        for (int j = 0; j < jsonArray.length(); j++) {
            JSONObject jobject = null;
            try {
                jobject = jsonArray.getJSONObject(j);
                String  district = jobject.getString("District");
                Log.e("dist", district);
                if(district.equalsIgnoreCase(testdistrict)){
                    temp = jobject.getInt("Crimes");
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("catch3","c");
            }
        }
        Log.e("temp",temp+"");
        return temp;
    }

    private void setback(){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new MainActivity.MyLocationListener()
        );
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {

            int crime = findCrime(getAddress(this, location.getLongitude(), location.getLatitude()));
            if (crime<268){
                Log.e("crime","low");
                high.setVisibility(View.GONE);
                mid.setVisibility(View.GONE);
                low.setVisibility(View.VISIBLE);
                //alert_co_btn = (ImageView)findViewById(R.id.alert_l_iv);
            }
            else if (crime<800){
                Log.e("crime","mid");
                high.setVisibility(View.GONE);
                mid.setVisibility(View.VISIBLE);
                low.setVisibility(View.GONE);
                //alert_co_btn = (ImageView)findViewById(R.id.alert_m_iv);
            }
            else{
                Log.e("crime","high");
                high.setVisibility(View.VISIBLE);
                mid.setVisibility(View.GONE);
                low.setVisibility(View.GONE);
               //alert_co_btn = (ImageView)findViewById(R.id.alert_h_iv);

            }
        }

    }

    public static String getAddress(Context context, double LATITUDE, double LONGITUDE) {

        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {



                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                Log.e("TAG", "getAddress:  address" + address);
                Log.e("TAG", "getAddress:  city" + city);
                Log.e("TAG", "getAddress:  state" + state);
                Log.e("TAG", "getAddress:  postalCode" + postalCode);
                Log.e("TAG", "getAddress:  knownName" + knownName);

                return city;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Agra";
    }


}




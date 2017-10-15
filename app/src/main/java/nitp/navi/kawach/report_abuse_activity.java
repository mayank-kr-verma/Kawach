package nitp.navi.kawach;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class report_abuse_activity extends AppCompatActivity implements View.OnClickListener {

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
    protected LocationManager locationManager;
    RadioGroup rg;
    RadioButton childabuse_rb, womenabuse_rb;
    TextView details_tv;
    EditText details_et;
    Button addpic_btn, addloc_btn, report_btn;
    String title, message , link = "null";


    public report_abuse_activity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report_abuse);
        if(checkIfAlreadyhavePermission()==false){
            requestForSpecificPermission();
        }
        rg = (RadioGroup) findViewById(R.id.rg);
        childabuse_rb = (RadioButton) findViewById(R.id.child_abuse_rb);
        womenabuse_rb = (RadioButton) findViewById(R.id.women_abuse_rb);
        details_tv = (TextView) findViewById(R.id.details_tv);
        details_et = (EditText) findViewById(R.id.details_et);
        addpic_btn = (Button) findViewById(R.id.add_pic_btn);
        addpic_btn.setOnClickListener(this);
        addloc_btn = (Button) findViewById(R.id.add_loc_btn);
        addloc_btn.setOnClickListener(this);
        report_btn = (Button) findViewById(R.id.report_btn);
        report_btn.setOnClickListener(this);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup rg, int checkedId){
                if (checkedId == R.id.child_abuse_rb){
                    title = "Reporting Child Abuse";
                }
                else
                    title = "Reporting Women Abuse";

            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.add_pic_btn){
            Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show();
        }
        if(view.getId() == R.id.add_loc_btn){
            Toast.makeText(this, "location added!", Toast.LENGTH_SHORT).show();
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    MINIMUM_TIME_BETWEEN_UPDATES,
                    MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                    new MyLocationListener()
            );
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                link = String.format("https://www.google.com/maps/?q=%1$s,%2$s", location.getLongitude(), location.getLatitude());

                Log.e("check",link);
            }
        }
        if(view.getId() == R.id.report_btn){
            Toast.makeText(this, "submitting", Toast.LENGTH_SHORT).show();
            message = "Abuse reported at " + link + "\nDetails:\n" + details_et.getText();
            Log.e("sms" , message);
            sendSMS("12345", message);

        }
    }

    private class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {
            String message = String.format(
                    "New Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(report_abuse_activity.this, message, Toast.LENGTH_LONG).show();
        }
        public void onStatusChanged(String s, int i, Bundle b) {
            Toast.makeText(report_abuse_activity.this, "Provider status changed",
                              Toast.LENGTH_LONG).show();
        }

        public void onProviderDisabled(String s) {
            Toast.makeText(report_abuse_activity.this,
                              "Provider disabled by the user. GPS turned off",
                            Toast.LENGTH_LONG).show();
        }
        public void onProviderEnabled(String s) {
            Toast.makeText(report_abuse_activity.this,
                              "Provider enabled by the user. GPS turned on",
                            Toast.LENGTH_LONG).show();
        }

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

    private void sendSMS(String phoneNumber,String message) {
        SmsManager sms = SmsManager.getDefault();
        StringBuffer smsBody = new StringBuffer();
        smsBody.append(message);
        //smsBody.append(myLocation.getLatitude());
        //smsBody.append(",");
        //smsBody.append(myLocation.getLongitude());
        //sms.sendTextMessage(Pref.getString("phn_1","0"), null, smsBody.toString(), null, null);
        Log.e("sms to "+phoneNumber,smsBody.toString());
        Toast.makeText(this,"message: "+message, Toast.LENGTH_LONG).show();
    }







}

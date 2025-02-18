package com.example.cofight_app.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cofight_app.MainActivity;
import com.example.cofight_app.R;
import com.example.cofight_app.adapter.VaccinationInfoAdapter;
import com.example.cofight_app.info;
import com.example.cofight_app.model.VaccineModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

public class Vaccine extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    final String TAG = "Vaccine";
    String baseURL="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?pincode=";
    private EditText areaPINCode;
    private Button forwardbtn;
    ProgressBar holdOnProgress;
    private ArrayList<VaccineModel> vaccination_centers;
    private RecyclerView resultRecyclerView;
    String areaPIN,avlDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.vaccine);
        mapViews();
        onClickSetup();
    }

    private void onClickSetup() {
        forwardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdOnProgress.setVisibility(View.VISIBLE);
                DialogFragment dp = new PickDate();
                dp.show(getSupportFragmentManager(),"pick a date");
            }
        });
    }

    private void mapViews() {
        forwardbtn = findViewById(R.id.getResult);
        holdOnProgress = findViewById(R.id.progress_circular);
        areaPINCode = findViewById(R.id.enterPincode);
        resultRecyclerView = findViewById(R.id.recyclerView);
        resultRecyclerView.setHasFixedSize(true);
        vaccination_centers = new ArrayList<VaccineModel>();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        Calendar k = Calendar.getInstance();
        k.set(Calendar.YEAR,year);
        k.set(Calendar.MONTH,month);
        k.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-YYYY");
        dateFormat.setTimeZone(k.getTimeZone());
        String d = dateFormat.format(k.getTime());
        setup(d);
    }

    private void setup(String d) {
        avlDate = d;
        fetchDataNow();
    }

    private void fetchDataNow() {
        vaccination_centers.clear();
        areaPIN = areaPINCode.getText().toString();
        String url_api = baseURL + areaPIN + "&date=" + avlDate;
        Log.d(TAG, "fetchDataNow: " + url_api);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray sessionArray = object.getJSONArray("sessions");
                    Log.d(TAG, "onResponse: " + sessionArray.length());
                    if(sessionArray.length() == 0){
                        Toast.makeText(Vaccine.this, "No centers are available on " + avlDate + " in your area", Toast.LENGTH_LONG).show();
                    }
                    for (int i = 0; i < sessionArray.length(); i++) {
                        JSONObject sesObject = sessionArray.getJSONObject(i);
                        VaccineModel vaccineModel = new VaccineModel();
                        vaccineModel.setVaccineCenter(sesObject.getString("name"));
                        vaccineModel.setVaccinationCenterAddress(sesObject.getString("address"));
                        vaccineModel.setVaccinationTimings(sesObject.getString("from"));
                        vaccineModel.setVaccineCenterTime(sesObject.getString("to"));
                        vaccineModel.setVaccineName(sesObject.getString("vaccine"));
                        vaccineModel.setVaccinationCharges(sesObject.getString("fee_type"));
                        vaccineModel.setVaccinationAge(sesObject.getString("min_age_limit"));
                        vaccineModel.setVaccineAvailable(sesObject.getString("available_capacity"));
                        vaccination_centers.add(vaccineModel);
                    }
                    VaccinationInfoAdapter vaccinationInfoAdapter = new VaccinationInfoAdapter(getApplicationContext(), vaccination_centers);
                    resultRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    resultRecyclerView.setAdapter(vaccinationInfoAdapter);
                    holdOnProgress.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    holdOnProgress.setVisibility(View.INVISIBLE);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                holdOnProgress.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Vaccine.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}

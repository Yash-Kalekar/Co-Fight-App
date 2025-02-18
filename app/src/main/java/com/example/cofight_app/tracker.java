    package com.example.cofight_app;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.example.cofight_app.api.ApiUtilities;
    import com.example.cofight_app.api.CountryData;


    import org.eazegraph.lib.charts.PieChart;
    import org.eazegraph.lib.models.PieModel;

    import java.text.DateFormat;
    import java.text.NumberFormat;
    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.List;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class tracker extends AppCompatActivity {

        private TextView TotalConfirm, TotalActive, TotalRecovered, TotalDeath, TotalTests;
        private TextView TodayConfirm, TodayRecovered, TodayDeath, dateTv;
        private PieChart pieChart;

        private List<CountryData> list;
        String country = "India";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tracker);

            getSupportActionBar().hide();

            list = new ArrayList<>();
            if (getIntent().getStringExtra("country") != null)
                country = getIntent().getStringExtra("country");


            init();

            TextView cname = findViewById(R.id.cname);
            cname.setText(country);


            cname.setOnClickListener(view ->
                    startActivity(new Intent(tracker.this, CountryActivity.class)));
            ApiUtilities.getApiInterface().getCountryData()
                    .enqueue(new Callback<List<CountryData>>()
            {

                        @Override
                        public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                            list.addAll(response.body());

                            for (int i = 0; i < list.size(); i++){
                                if (list.get(i).getCountry().equals(country)){
                                    int confirm = Integer.parseInt(list.get(i).getCases());
                                    int active = Integer.parseInt(list.get(i).getActive());
                                    int recovered = Integer.parseInt(list.get(i).getRecovered());
                                    int death = Integer.parseInt(list.get(i).getDeaths());

                                    TotalConfirm.setText(NumberFormat.getInstance().format(confirm));
                                    TotalActive.setText(NumberFormat.getInstance().format(active));
                                    TotalRecovered.setText(NumberFormat.getInstance().format(recovered));
                                    TotalDeath.setText(NumberFormat.getInstance().format(death));

                                    TodayDeath.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                                    TodayConfirm.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                                    TodayRecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
                                    TotalTests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));

                                    setText(list.get(i).getUpdated());

                                    pieChart.addPieSlice(new PieModel("Confirm",confirm, getResources() .getColor(R.color.orange)));
                                    pieChart.addPieSlice(new PieModel("Active",active, getResources() .getColor(R.color.blue_pie)));
                                    pieChart.addPieSlice(new PieModel("Recovered",recovered, getResources() .getColor(R.color.green_pie)));
                                    pieChart.addPieSlice(new PieModel("Death",death, getResources() .getColor(R.color.red_pie)));

                                    pieChart.startAnimation();



                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CountryData>> call, Throwable t) {
                            Toast.makeText(tracker.this, "Error : " +t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        private void setText(String updated) {
            DateFormat format = new SimpleDateFormat("MMM, dd , yyyy");
            long milliseconds = Long.parseLong(updated);
            Calendar calendar =Calendar.getInstance();
            calendar.setTimeInMillis(milliseconds);

            dateTv.setText("Updated At "+format.format(calendar.getTime()));

        }

        private void init() {

            TotalConfirm = findViewById(R.id.TotalConfirm);
            TotalActive = findViewById(R.id.TotalActive);
            TotalRecovered = findViewById(R.id.TotalRecovered);
            TotalDeath = findViewById(R.id.TotalDeath);
            TotalTests = findViewById(R.id.TotalTests);
            TodayConfirm = findViewById(R.id.TodayConfirm);
            TodayRecovered = findViewById(R.id.TodayRecovered);
            TodayDeath = findViewById(R.id.TodayDeath);
            dateTv= findViewById(R.id.date);
            pieChart = findViewById(R.id.pieChart);

        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            Intent intent = new Intent(tracker.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

package com.developerputra.myuserapigithub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {
    public static String EXTRA_USERNAME   = "extra_username";
    public static String EXTRA_COMPANY    = "extra_company";
    public static String EXTRA_LOCATION   = "extra_lcation";
    public static String EXTRA_IMG        = "extra_img";

    private TextView usernameM, companyM, locationM;
    private ImageView imgM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        usernameM = (TextView)findViewById(R.id.username);
        companyM = (TextView)findViewById(R.id.company);
        locationM = (TextView)findViewById(R.id.location);
        imgM = (ImageView)findViewById(R.id.img);

        String username = getIntent().getStringExtra(EXTRA_USERNAME );
        String company = getIntent().getStringExtra(EXTRA_COMPANY);
        String location = getIntent().getStringExtra(EXTRA_LOCATION);
        String poster_jpg = getIntent().getStringExtra(EXTRA_IMG);

        usernameM.setText(username);
        companyM.setText(company);
        locationM.setText(location);
        Picasso.get().load("http://image.tmdb.org/t/p/w500/" + poster_jpg).into(imgM);
    }
}
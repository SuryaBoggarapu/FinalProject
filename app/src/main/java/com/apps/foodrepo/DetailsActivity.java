package com.apps.foodrepo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    ImageView pimg;
    TextView pname,pdesc,pheight,ptype,pability,pweight,description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);



        pimg = findViewById(R.id.desc_imag);
        pname = findViewById(R.id.desc_name);
        pdesc = findViewById(R.id.desc_desc);
        pheight=findViewById(R.id.desc_height);
        pweight=findViewById(R.id.desc_weight);
        ptype=findViewById(R.id.desc_type);
        pability=findViewById(R.id.desc_ability);

        Intent i = getIntent();
        Food p= i.getParcelableExtra("data");


        Glide.with(this).load(p.getImg()).into(pimg);

        pname.setText(p.getName());
        pdesc.setText(p.getDesc());
        pheight.setText(p.getAlcohol());
        pweight.setText(p.getBarcode());
        ptype.setText(p.getUnit());
        pability.setText(p.getQuantity());

    }
}

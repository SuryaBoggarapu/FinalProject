package com.apps.foodrepo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    RecyclerView rvFood;

    List<Food> foods = new ArrayList<>();
    FoodsAdapter foodsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFood = findViewById(R.id.rvFoods);
        rvFood.setLayoutManager(new LinearLayoutManager(this));


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.foodrepo.org/api/v3/products",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("TAG", "onResponse: " + response);
                        //hiding the progressbar after completion
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i = 0; i <jsonArray.length() ; i++) {
                                JSONObject aProduct = jsonArray.getJSONObject(i);
                                Food food = new Food();
                                Log.e("TAG", "loop: " +aProduct.getJSONObject("name_translations"));
                                try {
                                    food.setName(aProduct.getJSONObject("name_translations").getString("en"));
                                }catch (Exception e){
                                    food.setName(aProduct.getJSONObject("name_translations").getString("de"));
                                }
                                food.setImg(aProduct.getJSONArray("images").getJSONObject(1).getString("thumb"));
                                try {
                                    food.setDesc(aProduct.getJSONObject("ingredients_translations").getString("en"));
                                }catch (Exception e){
                                    food.setDesc(aProduct.getJSONObject("ingredients_translations").getString("de"));
                                }

                                food.setQuantity("Quantity: "+aProduct.getString("quantity"));
                                food.setAlcohol("Alcohol: "+aProduct.getString("alcohol_by_volume"));
                                food.setUnit("Unit: "+aProduct.getString("unit"));
                                food.setBarcode("Barcode: "+aProduct.getString("barcode"));
                                foods.add(food);
                            }



                            foodsAdapter = new FoodsAdapter(foods,MainActivity.this);
                            rvFood.setAdapter(foodsAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap();
                headers.put("Authorization", "Token token=794ead27c46fd761ad522df1d9b6491d");
                return headers;
            }
        };



        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }



}

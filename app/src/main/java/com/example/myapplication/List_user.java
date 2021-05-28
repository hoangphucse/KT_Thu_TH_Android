package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class List_user extends AppCompatActivity {

    private final String url = "https://60ad9ba880a61f0017331403.mockapi.io/";
    private ListView listView;
    private ListViewAdapter adapter;
    private ArrayList<User> users;
    private Button btnAdd;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        listView = findViewById(R.id.list_view);

        users = GetData(List_user.this);
        btnAdd = findViewById(R.id.them_user);
        buildListView();
        handleEvent();


    }

    void handleEvent(){

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(List_user.this,addUser.class);
                startActivity(intent);

            }
        });
    }

    public void buildListView(){

        adapter = new ListViewAdapter(this,R.layout.item_list,users);
        listView.setAdapter(adapter);
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<User> GetData(Context context) {
        ArrayList<User> users = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url + "user",
                        response -> {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject object = (JSONObject) response.get(i);
                                    int id = Integer.valueOf(object.getString("id"));
                                    String name = object.getString("name");
                                    int age = Integer.valueOf(object.getString("age"));

                                    User user = new User(id, name, age);
                                    System.out.println(user.toString());
                                    users.add(user);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            buildListView();

                        }, error -> Toast.makeText(context, "Error by get Json Array!", Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return users;
    }
}
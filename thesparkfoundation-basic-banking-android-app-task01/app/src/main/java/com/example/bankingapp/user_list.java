package com.example.bankingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class user_list extends AppCompatActivity {
    List<Model> modelList_showlist = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    CustomeAdapter_userlist adapter;

    String phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_allusers);

        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        showData();
    }

    private void showData() {
        modelList_showlist.clear();
        Cursor cursor = new DatabaseHelper(this).readalldata();
        while(cursor.moveToNext()){
            String balancefromdb = cursor.getString(2);
            Double balance = Double.parseDouble(balancefromdb);

            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(true);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            String price = nf.format(balance);

            Model model = new Model(cursor.getString(0), cursor.getString(1), price);
            modelList_showlist.add(model);
        }

        adapter = new CustomeAdapter_userlist(user_list.this, modelList_showlist);
        mRecyclerView.setAdapter(adapter);

    }

    public void nextActivity(int position) {
        phonenumber = modelList_showlist.get(position).getPhoneno();
        Intent intent = new Intent(user_list.this, user_data.class);
        intent.putExtra("phonenumber",phonenumber);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_history){
            startActivity(new Intent(user_list.this, history_list.class));
        }
        return super.onOptionsItemSelected(item);
    }
}

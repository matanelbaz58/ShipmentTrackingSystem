package com.example.shipmenttrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewOrders;
    private Button btnNewShipment;
    private DBManager dbManager;
    private List<Order> orderList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> orderNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewOrders = findViewById(R.id.listViewOrders);
        btnNewShipment = findViewById(R.id.btnNewShipment);

        dbManager = new DBManager(this);
        loadOrders();

        btnNewShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open AddEditOrderActivity for creating a new order
                Intent intent = new Intent(MainActivity.this, AddEditOrderActivity.class);
                intent.putExtra("isEditMode", false);
                startActivity(intent);
            }
        });

        listViewOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Open the detail page for the selected order
                String selectedOrderNo = orderList.get(position).getOrderNo();
                Intent intent = new Intent(MainActivity.this, OrderDetailsActivity.class);
                intent.putExtra("orderNo", selectedOrderNo);
                startActivity(intent);
            }
        });
    }

    private void loadOrders() {
        orderList = dbManager.getAllOrders();
        orderNames = new ArrayList<>();

        for (Order o : orderList) {
            // Show the 'orderNo' or 'itemDescription' or anything you prefer
            orderNames.add("Order # " + o.getOrderNo());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orderNames);
        listViewOrders.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list every time activity resumes
        loadOrders();
    }
}

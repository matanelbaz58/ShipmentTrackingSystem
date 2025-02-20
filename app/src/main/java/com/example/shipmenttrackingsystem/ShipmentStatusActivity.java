package com.example.shipmenttrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShipmentStatusActivity extends AppCompatActivity {

    private ListView listViewShipments;
    private Button btnAddShipmentStatus;
    private DBManager dbManager;
    private String orderNo;
    private List<ShipmentStatus> shipmentStatuses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_status);

        listViewShipments = findViewById(R.id.listViewShipments);
        btnAddShipmentStatus = findViewById(R.id.btnAddShipmentStatus);
        dbManager = new DBManager(this);

        orderNo = getIntent().getStringExtra("orderNo");

        btnAddShipmentStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open AddEditShipmentStatusActivity for creating a new record
                Intent intent = new Intent(ShipmentStatusActivity.this, AddEditShipmentStatusActivity.class);
                intent.putExtra("isEditMode", false);
                intent.putExtra("orderNo", orderNo);
                startActivity(intent);
            }
        });

        listViewShipments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                // Options: edit or delete
                final ShipmentStatus status = shipmentStatuses.get(position);

                CharSequence[] options = {"Edit", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ShipmentStatusActivity.this);
                builder.setTitle("Choose an action");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            // Edit
                            Intent intent = new Intent(ShipmentStatusActivity.this, AddEditShipmentStatusActivity.class);
                            intent.putExtra("isEditMode", true);
                            intent.putExtra("id", status.getId());
                            intent.putExtra("orderNo", status.getOrderNo());
                            intent.putExtra("shipmentNo", status.getShipmentNo());
                            intent.putExtra("date", status.getDate());
                            intent.putExtra("time", status.getTime());
                            intent.putExtra("shipmentStatus", status.getShipmentStatus());
                            startActivity(intent);
                        } else if (which == 1) {
                            // Delete
                            showDeleteConfirmationDialog(status.getId());
                        }
                    }
                });
                builder.show();
            }
        });
    }

    private void showDeleteConfirmationDialog(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Record");
        builder.setMessage("Are you sure you want to delete this shipment status?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                boolean success = dbManager.deleteShipmentStatus(id);
                if (success) {
                    loadShipmentStatuses();
                }
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void loadShipmentStatuses() {
        shipmentStatuses = dbManager.getShipmentStatuses(orderNo);
        ArrayList<HashMap<String, String>> dataList = new ArrayList<>();

        for (ShipmentStatus s : shipmentStatuses) {
            HashMap<String, String> map = new HashMap<>();
            map.put("shipmentNo", "Shipment #: " + s.getShipmentNo());
            map.put("dateTime", "Date/Time: " + s.getDate() + " " + s.getTime());
            map.put("status", "Status: " + s.getShipmentStatus());
            dataList.add(map);
        }

        String[] from = {"shipmentNo", "dateTime", "status"};
        int[] to = {R.id.tvShipmentNo, R.id.tvDateTime, R.id.tvShipmentStatus};

        SimpleAdapter adapter = new SimpleAdapter(this, dataList, R.layout.shipment_status_item, from, to);
        listViewShipments.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadShipmentStatuses();
    }
}

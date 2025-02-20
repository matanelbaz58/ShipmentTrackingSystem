package com.example.shipmenttrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditShipmentStatusActivity extends AppCompatActivity {

    private EditText etOrderNo, etShipmentNo, etDate, etTime, etShipmentStatus;
    private Button btnSave, btnCancel;
    private DBManager dbManager;

    private boolean isEditMode = false;
    private int existingId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_shipment_status);

        etOrderNo = findViewById(R.id.etOrderNo);
        etShipmentNo = findViewById(R.id.etShipmentNo);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etShipmentStatus = findViewById(R.id.etShipmentStatus);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        dbManager = new DBManager(this);

        isEditMode = getIntent().getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            existingId = getIntent().getIntExtra("id", -1);
            String orderNo = getIntent().getStringExtra("orderNo");
            String shipmentNo = getIntent().getStringExtra("shipmentNo");
            String date = getIntent().getStringExtra("date");
            String time = getIntent().getStringExtra("time");
            String shipmentStatus = getIntent().getStringExtra("shipmentStatus");

            etOrderNo.setText(orderNo);
            etShipmentNo.setText(shipmentNo);
            etDate.setText(date);
            etTime.setText(time);
            etShipmentStatus.setText(shipmentStatus);
        } else {
            // We are adding a new record
            String orderNo = getIntent().getStringExtra("orderNo");
            etOrderNo.setText(orderNo);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditMode) {
                    updateShipmentStatus();
                } else {
                    insertShipmentStatus();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertShipmentStatus() {
        String orderNo = etOrderNo.getText().toString().trim();
        String shipmentNo = etShipmentNo.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();
        String shipmentStatus = etShipmentStatus.getText().toString().trim();

        if (orderNo.isEmpty() || shipmentNo.isEmpty()) {
            Toast.makeText(this, "OrderNo and ShipmentNo are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        ShipmentStatus s = new ShipmentStatus(orderNo, shipmentNo, date, time, shipmentStatus);
        boolean inserted = dbManager.insertShipmentStatus(s);
        if (inserted) {
            Toast.makeText(this, "Shipment status created", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to create record", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateShipmentStatus() {
        String orderNo = etOrderNo.getText().toString().trim();
        String shipmentNo = etShipmentNo.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();
        String shipmentStatusTxt = etShipmentStatus.getText().toString().trim();

        if (existingId == -1) {
            Toast.makeText(this, "Error: cannot update record", Toast.LENGTH_SHORT).show();
            return;
        }

        ShipmentStatus s = new ShipmentStatus(existingId, orderNo, shipmentNo, date, time, shipmentStatusTxt);
        boolean updated = dbManager.updateShipmentStatus(s);
        if (updated) {
            Toast.makeText(this, "Shipment status updated", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to update record", Toast.LENGTH_SHORT).show();
        }
    }
}

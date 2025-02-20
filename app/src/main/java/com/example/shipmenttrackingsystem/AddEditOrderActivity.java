package com.example.shipmenttrackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditOrderActivity extends AppCompatActivity {

    private EditText etOrderNo, etOrderDate, etItemNumber, etItemDescription, etCountryOfOrigin,
            etDepartureDate, etDestinationCountry, etEstimatedArrivalDate, etDeliveryReceiptDate,
            etOrderStatus, etShipmentNo;
    private Button btnSave, btnCancel;

    private DBManager dbManager;
    private boolean isEditMode = false;  // if true, we are editing
    private String existingOrderNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_order);

        dbManager = new DBManager(this);

        etOrderNo = findViewById(R.id.etOrderNo);
        etOrderDate = findViewById(R.id.etOrderDate);
        etItemNumber = findViewById(R.id.etItemNumber);
        etItemDescription = findViewById(R.id.etItemDescription);
        etCountryOfOrigin = findViewById(R.id.etCountryOfOrigin);
        etDepartureDate = findViewById(R.id.etDepartureDate);
        etDestinationCountry = findViewById(R.id.etDestinationCountry);
        etEstimatedArrivalDate = findViewById(R.id.etEstimatedArrivalDate);
        etDeliveryReceiptDate = findViewById(R.id.etDeliveryReceiptDate);
        etOrderStatus = findViewById(R.id.etOrderStatus);
        etShipmentNo = findViewById(R.id.etShipmentNo);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        // Check mode
        isEditMode = getIntent().getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            // We are editing an existing order
            existingOrderNo = getIntent().getStringExtra("orderNo");
            loadOrderData(existingOrderNo);
            etOrderNo.setEnabled(false);  // We don't allow changing the primary key
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditMode) {
                    updateOrder();
                } else {
                    createOrder();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // go back
            }
        });
    }

    private void loadOrderData(String orderNo) {
        Order order = dbManager.getOrder(orderNo);
        if (order != null) {
            etOrderNo.setText(order.getOrderNo());
            etOrderDate.setText(order.getOrderDate());
            etItemNumber.setText(order.getItemNumber());
            etItemDescription.setText(order.getItemDescription());
            etCountryOfOrigin.setText(order.getCountryOfOrigin());
            etDepartureDate.setText(order.getDepartureDate());
            etDestinationCountry.setText(order.getDestinationCountry());
            etEstimatedArrivalDate.setText(order.getEstimatedArrivalDate());
            etDeliveryReceiptDate.setText(order.getDeliveryReceiptDate());
            etOrderStatus.setText(order.getOrderStatus());
            etShipmentNo.setText(order.getShipmentNo());
        }
    }

    private void createOrder() {
        String orderNo = etOrderNo.getText().toString().trim();
        String orderDate = etOrderDate.getText().toString().trim();
        String itemNumber = etItemNumber.getText().toString().trim();
        String itemDescription = etItemDescription.getText().toString().trim();
        String countryOfOrigin = etCountryOfOrigin.getText().toString().trim();
        String departureDate = etDepartureDate.getText().toString().trim();
        String destinationCountry = etDestinationCountry.getText().toString().trim();
        String estimatedArrivalDate = etEstimatedArrivalDate.getText().toString().trim();
        String deliveryReceiptDate = etDeliveryReceiptDate.getText().toString().trim();
        String orderStatus = etOrderStatus.getText().toString().trim();
        String shipmentNo = etShipmentNo.getText().toString().trim();

        if (orderNo.isEmpty()) {
            Toast.makeText(this, "Order No. is required!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Construct the Order object
        Order order = new Order(orderNo, orderDate, itemNumber, itemDescription, countryOfOrigin, departureDate,
                destinationCountry, estimatedArrivalDate, deliveryReceiptDate, orderStatus, shipmentNo);

        boolean inserted = dbManager.insertOrder(order);
        if (inserted) {
            Toast.makeText(this, "Order created successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to create order", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateOrder() {
        String orderDate = etOrderDate.getText().toString().trim();
        String itemNumber = etItemNumber.getText().toString().trim();
        String itemDescription = etItemDescription.getText().toString().trim();
        String countryOfOrigin = etCountryOfOrigin.getText().toString().trim();
        String departureDate = etDepartureDate.getText().toString().trim();
        String destinationCountry = etDestinationCountry.getText().toString().trim();
        String estimatedArrivalDate = etEstimatedArrivalDate.getText().toString().trim();
        String deliveryReceiptDate = etDeliveryReceiptDate.getText().toString().trim();
        String orderStatus = etOrderStatus.getText().toString().trim();
        String shipmentNo = etShipmentNo.getText().toString().trim();

        Order order = new Order(existingOrderNo, orderDate, itemNumber, itemDescription,
                countryOfOrigin, departureDate, destinationCountry, estimatedArrivalDate,
                deliveryReceiptDate, orderStatus, shipmentNo);

        boolean updated = dbManager.updateOrder(order);
        if (updated) {
            Toast.makeText(this, "Order updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Failed to update order", Toast.LENGTH_SHORT).show();
        }
    }
}

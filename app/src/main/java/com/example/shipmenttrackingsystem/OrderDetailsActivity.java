package com.example.shipmenttrackingsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderDetailsActivity extends AppCompatActivity {

    private TextView tvOrderNo, tvOrderDate, tvItemNumber, tvItemDescription, tvCountryOfOrigin,
            tvDepartureDate, tvDestinationCountry, tvEstimatedArrivalDate, tvDeliveryReceiptDate,
            tvOrderStatus, tvShipmentNo;
    private Button btnEditOrder, btnDeleteOrder, btnViewShipments;

    private DBManager dbManager;
    private String orderNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        dbManager = new DBManager(this);

        tvOrderNo = findViewById(R.id.tvOrderNo);
        tvOrderDate = findViewById(R.id.tvOrderDate);
        tvItemNumber = findViewById(R.id.tvItemNumber);
        tvItemDescription = findViewById(R.id.tvItemDescription);
        tvCountryOfOrigin = findViewById(R.id.tvCountryOfOrigin);
        tvDepartureDate = findViewById(R.id.tvDepartureDate);
        tvDestinationCountry = findViewById(R.id.tvDestinationCountry);
        tvEstimatedArrivalDate = findViewById(R.id.tvEstimatedArrivalDate);
        tvDeliveryReceiptDate = findViewById(R.id.tvDeliveryReceiptDate);
        tvOrderStatus = findViewById(R.id.tvOrderStatus);
        tvShipmentNo = findViewById(R.id.tvShipmentNo);

        btnEditOrder = findViewById(R.id.btnEditOrder);
        btnDeleteOrder = findViewById(R.id.btnDeleteOrder);
        btnViewShipments = findViewById(R.id.btnViewShipments);

        orderNo = getIntent().getStringExtra("orderNo");
        loadOrderData(orderNo);

        btnEditOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderDetailsActivity.this, AddEditOrderActivity.class);
                intent.putExtra("isEditMode", true);
                intent.putExtra("orderNo", orderNo);
                startActivity(intent);
            }
        });

        btnDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog();
            }
        });

        btnViewShipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show ShipmentStatusActivity or similar
                Intent intent = new Intent(OrderDetailsActivity.this, ShipmentStatusActivity.class);
                intent.putExtra("orderNo", orderNo);
                startActivity(intent);
            }
        });
    }

    private void loadOrderData(String orderNo) {
        Order order = dbManager.getOrder(orderNo);
        if (order != null) {
            tvOrderNo.setText(order.getOrderNo());
            tvOrderDate.setText(order.getOrderDate());
            tvItemNumber.setText(order.getItemNumber());
            tvItemDescription.setText(order.getItemDescription());
            tvCountryOfOrigin.setText(order.getCountryOfOrigin());
            tvDepartureDate.setText(order.getDepartureDate());
            tvDestinationCountry.setText(order.getDestinationCountry());
            tvEstimatedArrivalDate.setText(order.getEstimatedArrivalDate());
            tvDeliveryReceiptDate.setText(order.getDeliveryReceiptDate());
            tvOrderStatus.setText(order.getOrderStatus());
            tvShipmentNo.setText(order.getShipmentNo());
        }
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Order");
        builder.setMessage("Are you sure you want to delete this order?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                boolean deleted = dbManager.deleteOrder(orderNo);
                if (deleted) {
                    Toast.makeText(OrderDetailsActivity.this, "Order deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(OrderDetailsActivity.this, "Failed to delete order", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadOrderData(orderNo);
    }
}

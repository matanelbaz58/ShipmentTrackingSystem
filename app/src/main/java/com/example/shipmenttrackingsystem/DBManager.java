package com.example.shipmenttrackingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbHelper;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * Insert a new Order
     */
    public boolean insertOrder(Order order) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_ORDER_NO, order.getOrderNo());
        values.put(DBHelper.COL_ORDER_DATE, order.getOrderDate());
        values.put(DBHelper.COL_ITEM_NUMBER, order.getItemNumber());
        values.put(DBHelper.COL_ITEM_DESCRIPTION, order.getItemDescription());
        values.put(DBHelper.COL_COUNTRY_OF_ORIGIN, order.getCountryOfOrigin());
        values.put(DBHelper.COL_DEPARTURE_DATE, order.getDepartureDate());
        values.put(DBHelper.COL_DESTINATION_COUNTRY, order.getDestinationCountry());
        values.put(DBHelper.COL_ESTIMATED_ARRIVAL_DATE, order.getEstimatedArrivalDate());
        values.put(DBHelper.COL_DELIVERY_RECEIPT_DATE, order.getDeliveryReceiptDate());
        values.put(DBHelper.COL_ORDER_STATUS, order.getOrderStatus());
        values.put(DBHelper.COL_SHIPMENT_NO, order.getShipmentNo());

        long result = db.insert(DBHelper.TABLE_ORDERS, null, values);
        db.close();
        return (result != -1);
    }

    /**
     * Get all Orders
     */
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DBHelper.TABLE_ORDERS + " ORDER BY " + DBHelper.COL_ORDER_NO + " ASC";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Order order = new Order();
                order.setOrderNo(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ORDER_NO)));
                order.setOrderDate(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ORDER_DATE)));
                order.setItemNumber(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ITEM_NUMBER)));
                order.setItemDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ITEM_DESCRIPTION)));
                order.setCountryOfOrigin(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_COUNTRY_OF_ORIGIN)));
                order.setDepartureDate(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DEPARTURE_DATE)));
                order.setDestinationCountry(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DESTINATION_COUNTRY)));
                order.setEstimatedArrivalDate(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ESTIMATED_ARRIVAL_DATE)));
                order.setDeliveryReceiptDate(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DELIVERY_RECEIPT_DATE)));
                order.setOrderStatus(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ORDER_STATUS)));
                order.setShipmentNo(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_SHIPMENT_NO)));
                orders.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return orders;
    }

    /**
     * Get a single Order by order_no
     */
    public Order getOrder(String orderNo) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DBHelper.TABLE_ORDERS + " WHERE " + DBHelper.COL_ORDER_NO + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{orderNo});

        if (cursor != null && cursor.moveToFirst()) {
            Order order = new Order();
            order.setOrderNo(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ORDER_NO)));
            order.setOrderDate(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ORDER_DATE)));
            order.setItemNumber(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ITEM_NUMBER)));
            order.setItemDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ITEM_DESCRIPTION)));
            order.setCountryOfOrigin(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_COUNTRY_OF_ORIGIN)));
            order.setDepartureDate(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DEPARTURE_DATE)));
            order.setDestinationCountry(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DESTINATION_COUNTRY)));
            order.setEstimatedArrivalDate(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ESTIMATED_ARRIVAL_DATE)));
            order.setDeliveryReceiptDate(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DELIVERY_RECEIPT_DATE)));
            order.setOrderStatus(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_ORDER_STATUS)));
            order.setShipmentNo(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_SHIPMENT_NO)));
            cursor.close();
            db.close();
            return order;
        }

        return null;
    }

    /**
     * Update an existing Order
     */
    public boolean updateOrder(Order order) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_ORDER_DATE, order.getOrderDate());
        values.put(DBHelper.COL_ITEM_NUMBER, order.getItemNumber());
        values.put(DBHelper.COL_ITEM_DESCRIPTION, order.getItemDescription());
        values.put(DBHelper.COL_COUNTRY_OF_ORIGIN, order.getCountryOfOrigin());
        values.put(DBHelper.COL_DEPARTURE_DATE, order.getDepartureDate());
        values.put(DBHelper.COL_DESTINATION_COUNTRY, order.getDestinationCountry());
        values.put(DBHelper.COL_ESTIMATED_ARRIVAL_DATE, order.getEstimatedArrivalDate());
        values.put(DBHelper.COL_DELIVERY_RECEIPT_DATE, order.getDeliveryReceiptDate());
        values.put(DBHelper.COL_ORDER_STATUS, order.getOrderStatus());
        values.put(DBHelper.COL_SHIPMENT_NO, order.getShipmentNo());

        int rows = db.update(DBHelper.TABLE_ORDERS, values, DBHelper.COL_ORDER_NO + "=?",
                new String[]{order.getOrderNo()});
        db.close();
        return (rows > 0);
    }

    /**
     * Delete an Order
     */
    public boolean deleteOrder(String orderNo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete(DBHelper.TABLE_ORDERS, DBHelper.COL_ORDER_NO + "=?", new String[]{orderNo});
        db.close();
        return (rows > 0);
    }

    /**
     * Insert a new ShipmentStatus
     */
    public boolean insertShipmentStatus(ShipmentStatus shipmentStatus) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_SHIP_ORDER_NO, shipmentStatus.getOrderNo());
        values.put(DBHelper.COL_SHIP_SHIPMENT_NO, shipmentStatus.getShipmentNo());
        values.put(DBHelper.COL_DATE, shipmentStatus.getDate());
        values.put(DBHelper.COL_TIME, shipmentStatus.getTime());
        values.put(DBHelper.COL_SHIPMENT_STATUS, shipmentStatus.getShipmentStatus());

        long result = db.insert(DBHelper.TABLE_SHIPMENT_STATUS, null, values);
        db.close();
        return (result != -1);
    }

    /**
     * Get all ShipmentStatuses for a given order_no
     */
    public List<ShipmentStatus> getShipmentStatuses(String orderNo) {
        List<ShipmentStatus> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DBHelper.TABLE_SHIPMENT_STATUS +
                " WHERE " + DBHelper.COL_SHIP_ORDER_NO + "=? ORDER BY " + DBHelper.COL_ID + " DESC";
        Cursor cursor = db.rawQuery(query, new String[]{orderNo});

        if (cursor.moveToFirst()) {
            do {
                ShipmentStatus status = new ShipmentStatus();
                status.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COL_ID)));
                status.setOrderNo(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_SHIP_ORDER_NO)));
                status.setShipmentNo(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_SHIP_SHIPMENT_NO)));
                status.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DATE)));
                status.setTime(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_TIME)));
                status.setShipmentStatus(cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_SHIPMENT_STATUS)));
                list.add(status);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * Delete a ShipmentStatus record by ID
     */
    public boolean deleteShipmentStatus(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete(DBHelper.TABLE_SHIPMENT_STATUS, DBHelper.COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return (rows > 0);
    }

    /**
     * Update a ShipmentStatus record
     */
    public boolean updateShipmentStatus(ShipmentStatus shipmentStatus) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.COL_SHIP_ORDER_NO, shipmentStatus.getOrderNo());
        values.put(DBHelper.COL_SHIP_SHIPMENT_NO, shipmentStatus.getShipmentNo());
        values.put(DBHelper.COL_DATE, shipmentStatus.getDate());
        values.put(DBHelper.COL_TIME, shipmentStatus.getTime());
        values.put(DBHelper.COL_SHIPMENT_STATUS, shipmentStatus.getShipmentStatus());

        int rows = db.update(DBHelper.TABLE_SHIPMENT_STATUS, values,
                DBHelper.COL_ID + "=?", new String[]{String.valueOf(shipmentStatus.getId())});
        db.close();
        return (rows > 0);
    }
}

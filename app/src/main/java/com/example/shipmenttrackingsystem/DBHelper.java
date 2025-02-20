package com.example.shipmenttrackingsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shipment_db";
    private static final int DATABASE_VERSION = 1;

    // Table: orders
    public static final String TABLE_ORDERS = "orders";
    public static final String COL_ORDER_NO = "order_no";
    public static final String COL_ORDER_DATE = "order_date";
    public static final String COL_ITEM_NUMBER = "item_number";
    public static final String COL_ITEM_DESCRIPTION = "item_description";
    public static final String COL_COUNTRY_OF_ORIGIN = "country_of_origin";
    public static final String COL_DEPARTURE_DATE = "departure_date";
    public static final String COL_DESTINATION_COUNTRY = "destination_country";
    public static final String COL_ESTIMATED_ARRIVAL_DATE = "estimated_arrival_date";
    public static final String COL_DELIVERY_RECEIPT_DATE = "delivery_receipt_date";
    public static final String COL_ORDER_STATUS = "order_status";
    public static final String COL_SHIPMENT_NO = "shipment_no";

    // Table: shipmentstatus
    public static final String TABLE_SHIPMENT_STATUS = "shipmentstatus";
    public static final String COL_ID = "id";
    public static final String COL_SHIP_ORDER_NO = "order_no";
    public static final String COL_SHIP_SHIPMENT_NO = "shipment_no";
    public static final String COL_DATE = "date";
    public static final String COL_TIME = "time";
    public static final String COL_SHIPMENT_STATUS = "shipment_status";

    // Create table queries
    private static final String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + " (" +
            COL_ORDER_NO + " TEXT PRIMARY KEY, " +
            COL_ORDER_DATE + " TEXT, " +
            COL_ITEM_NUMBER + " TEXT, " +
            COL_ITEM_DESCRIPTION + " TEXT, " +
            COL_COUNTRY_OF_ORIGIN + " TEXT, " +
            COL_DEPARTURE_DATE + " TEXT, " +
            COL_DESTINATION_COUNTRY + " TEXT, " +
            COL_ESTIMATED_ARRIVAL_DATE + " TEXT, " +
            COL_DELIVERY_RECEIPT_DATE + " TEXT, " +
            COL_ORDER_STATUS + " TEXT, " +
            COL_SHIPMENT_NO + " TEXT" +
            ")";

    private static final String CREATE_SHIPMENTSTATUS_TABLE = "CREATE TABLE " + TABLE_SHIPMENT_STATUS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_SHIP_ORDER_NO + " TEXT, " +
            COL_SHIP_SHIPMENT_NO + " TEXT, " +
            COL_DATE + " TEXT, " +
            COL_TIME + " TEXT, " +
            COL_SHIPMENT_STATUS + " TEXT" +
            ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ORDERS_TABLE);
        db.execSQL(CREATE_SHIPMENTSTATUS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHIPMENT_STATUS);
        onCreate(db);
    }
}

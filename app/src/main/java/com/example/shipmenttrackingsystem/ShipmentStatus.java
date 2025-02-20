package com.example.shipmenttrackingsystem;

public class ShipmentStatus {
    private int id;
    private String orderNo;
    private String shipmentNo;
    private String date;
    private String time;
    private String shipmentStatus;

    public ShipmentStatus() {
    }

    public ShipmentStatus(int id, String orderNo, String shipmentNo, String date, String time, String shipmentStatus) {
        this.id = id;
        this.orderNo = orderNo;
        this.shipmentNo = shipmentNo;
        this.date = date;
        this.time = time;
        this.shipmentStatus = shipmentStatus;
    }

    // Additional constructor without 'id' if needed
    public ShipmentStatus(String orderNo, String shipmentNo, String date, String time, String shipmentStatus) {
        this.orderNo = orderNo;
        this.shipmentNo = shipmentNo;
        this.date = date;
        this.time = time;
        this.shipmentStatus = shipmentStatus;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(String shipmentNo) {
        this.shipmentNo = shipmentNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
}

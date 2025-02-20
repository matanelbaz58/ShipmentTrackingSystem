package com.example.shipmenttrackingsystem;

public class Order {
    private String orderNo;
    private String orderDate;
    private String itemNumber;
    private String itemDescription;
    private String countryOfOrigin;
    private String departureDate;
    private String destinationCountry;
    private String estimatedArrivalDate;
    private String deliveryReceiptDate;
    private String orderStatus;
    private String shipmentNo;

    public Order() {
    }

    public Order(String orderNo, String orderDate, String itemNumber, String itemDescription,
                 String countryOfOrigin, String departureDate, String destinationCountry,
                 String estimatedArrivalDate, String deliveryReceiptDate, String orderStatus,
                 String shipmentNo) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.itemNumber = itemNumber;
        this.itemDescription = itemDescription;
        this.countryOfOrigin = countryOfOrigin;
        this.departureDate = departureDate;
        this.destinationCountry = destinationCountry;
        this.estimatedArrivalDate = estimatedArrivalDate;
        this.deliveryReceiptDate = deliveryReceiptDate;
        this.orderStatus = orderStatus;
        this.shipmentNo = shipmentNo;
    }

    // Getters and Setters

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getEstimatedArrivalDate() {
        return estimatedArrivalDate;
    }

    public void setEstimatedArrivalDate(String estimatedArrivalDate) {
        this.estimatedArrivalDate = estimatedArrivalDate;
    }

    public String getDeliveryReceiptDate() {
        return deliveryReceiptDate;
    }

    public void setDeliveryReceiptDate(String deliveryReceiptDate) {
        this.deliveryReceiptDate = deliveryReceiptDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(String shipmentNo) {
        this.shipmentNo = shipmentNo;
    }
}

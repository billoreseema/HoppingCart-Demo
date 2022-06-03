package model;

public class Product {

    private String productID;
    private int productQty;
    private double unitPrice;

    public Product(String productID, double unitPrice) {
        this.productID = productID;
        this.unitPrice = unitPrice;
    }
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

}

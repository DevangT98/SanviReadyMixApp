package com.dev.sanvireadymix;

public class CartItems {

    private  int productImage;
    private String productName,productType,productPrice,productQty;
    private  int productId;
    public CartItems(int productId, int productImage, String productName, String productType, String productPrice, String productQty) {
        this.productId = productId;
        this.productImage = productImage;
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productQty = productQty;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }
}

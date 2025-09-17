package com.tutorialsninja.models;

public class Product {
    private String name;
    private String imageUrl;
    private String description;
    private double price;
    private String originalPriceText;

    public Product(String name, String imageUrl, String description, double price, String originalPriceText) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.originalPriceText = originalPriceText;
    }

    public String getName() {
        return name;
    }

    public String getNameWithoutIPod() {
        return name.replace("iPod", "").trim();//dont remove "iPod" from DB only remove it when printing
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getOriginalPriceText() {
        return originalPriceText;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOriginalPriceText(String originalPriceText) {
        this.originalPriceText = originalPriceText;
    }

    @Override
    public String toString() {
        return String.format("Product{name='%s', price=%.2f, originalPrice='%s', imageUrl='%s', description='%s'}",
                name, price, originalPriceText, imageUrl, description);
    }

    public void printProductInfo() {
        System.out.println("Product Name: " + getNameWithoutIPod());//dont remove "iPod" from DB only remove it when printing
        System.out.println("Price: " + originalPriceText);
        System.out.println("Image URL: " + imageUrl);
        System.out.println("Description: " + description);
    }
}
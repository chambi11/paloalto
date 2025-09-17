package com.tutorialsninja.pages;

import com.tutorialsninja.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage {

    @FindBy(id = "list-view")//#list-view
    private WebElement listViewButton;

    @FindBy(id = "input-sort")//#input-sort
    private WebElement sortDropdown;

    @FindBy(css = ".product-layout")
    private List<WebElement> productElements;

    @FindBy(css = ".product-layout h4 a")
    private List<WebElement> productNames;

    @FindBy(css = ".product-layout .product-thumb")
    private List<WebElement> productThumbs;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void clickListView() {
        waitForElementToBeClickable(listViewButton);
        listViewButton.click();
    }

    public void sortBy(String sortOption) {
        waitForElementToBeClickable(sortDropdown);
        selectByVisibleText(sortDropdown, sortOption);
    }

    public List<String> getProductNames() {
        waitForElementToBeVisible(productNames.get(0));
        return productNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isProductListSortedAlphabetically() {
        List<String> productNames = getProductNames();

        for (int i = 0; i < productNames.size() - 1; i++) {
            if (productNames.get(i).compareToIgnoreCase(productNames.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public List<Product> getAllProductsWithDetails() {
        List<Product> products = new ArrayList<>();
        waitForElementToBeVisible(productThumbs.get(0));

        for (WebElement productThumb : productThumbs) {
            try {
                String name = productThumb.findElement(By.cssSelector("h4 a")).getText();
                String imageUrl = productThumb.findElement(By.cssSelector("img")).getAttribute("src");
                String description = productThumb.findElement(By.cssSelector("p")).getText();

                String priceText = "";
                List<WebElement> priceElements = productThumb.findElements(By.cssSelector(".price"));
                if (!priceElements.isEmpty()) {
                    priceText = priceElements.get(0).getText();
                }

                double price = extractPriceFromText(priceText);

                products.add(new Product(name, imageUrl, description, price, priceText));
            } catch (Exception e) {
                //TODO add exception handling
                //TODO add logging
                System.out.println("Error parsing product: " + e.getMessage());
            }
        }

        return products;
    }

    private double extractPriceFromText(String priceText) {
        if (priceText == null || priceText.isEmpty()) {
            return 0.0;
        }
        //$122.00\nEx Tax:$100.00
        String price = priceText.split("\n")[0];
        //$122.00
        String cleanPrice = price.replaceAll("[^0-9.]", "");

        try {
            return Double.parseDouble(cleanPrice);
        } catch (NumberFormatException e) {
            //TODO add exception handling
            return 0.0;
        }
    }

    public int getProductCount() {
        return productElements.size();
    }
}
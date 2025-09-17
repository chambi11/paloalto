package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "#form-currency > div > button")//
    private WebElement currencyDropdown;

    @FindBy(name = "search")
    private WebElement searchBox;

    @FindBy(css = "#search > span > button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isCurrencyDropdownVisible() {
        try {
            waitForElementToBeVisible(currencyDropdown);
            return currencyDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    //TODO add a wrapper for logging
    public void enterSearchText(String searchText) {
        waitForElementToBeVisible(searchBox);
        searchBox.clear();
        searchBox.sendKeys(searchText);
    }

    public SearchResultsPage clickSearchButton() {
        waitForElementToBeClickable(searchButton);
        searchButton.click();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage searchForProduct(String productName) {
        enterSearchText(productName);
        return clickSearchButton();
    }
}
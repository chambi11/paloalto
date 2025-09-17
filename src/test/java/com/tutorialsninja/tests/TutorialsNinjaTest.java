package com.tutorialsninja.tests;

import com.tutorialsninja.models.Product;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.SearchResultsPage;
import com.tutorialsninja.utils.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TutorialsNinjaTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Test(description = "Test sort alphabeticly ascending")
    public void testSortAlphabeticallyAscending() {
        logger.info("Starting testSortAlphabeticallyAscending");
        HomePage homePage = new HomePage(DriverManager.getDriver());

        Assert.assertTrue(homePage.isCurrencyDropdownVisible(),
                "Error ,Currency dropdown is not visible");

        SearchResultsPage searchResultsPage = homePage.searchForProduct("iPod");
        searchResultsPage.clickListView();
        searchResultsPage.sortBy("Name (A - Z)");//TODO fill this from an enum or a fetched array with options available
        Assert.assertTrue(searchResultsPage.isProductListSortedAlphabetically(),
                "Error ,Products should be alphabeticly sorted in ascending order");

        List<String> productNames = searchResultsPage.getProductNames();//TODO create utils func for pretty printing
        System.out.println("Products found and sorted:");
        for (String name : productNames) {
            System.out.println("- " + name);
        }

        Assert.assertTrue(productNames.size() > 0, "Error ,no product found");
        logger.info("Ending testSortAlphabeticallyAscending");
    }

    @Test(description = "Test find iPod min and max price")
    public void ipodStorage() {
        logger.info("Starting ipodStorage");
        HomePage homePage = new HomePage(DriverManager.getDriver());
        SearchResultsPage searchResultsPage = homePage.searchForProduct("iPod");
        searchResultsPage.clickListView();
        List<Product> allProducts = searchResultsPage.getAllProductsWithDetails();

        System.out.println("iPods :");//TODO create utils func for pretty printing
        for (Product product : allProducts) {
            product.printProductInfo();
        }

        Optional<Product> maxPriceProduct = allProducts.stream()
                .max(Comparator.comparing(Product::getPrice));

        Optional<Product> minPriceProduct = allProducts.stream()
                .filter(product -> product.getPrice() > 0)
                .min(Comparator.comparing(Product::getPrice));

        if (maxPriceProduct.isPresent()) {
            System.out.println("maximum price:");
            maxPriceProduct.get().printProductInfo();
        }

        if (minPriceProduct.isPresent()) {
            System.out.println("minimum price:");
            minPriceProduct.get().printProductInfo();
        }

        Assert.assertTrue(allProducts.size() > 0, "Error ,no products found");
        Assert.assertTrue(maxPriceProduct.isPresent(), "Error ,finding maximum price product");
        Assert.assertTrue(minPriceProduct.isPresent(), "Error ,finding minimum price product");
        logger.info("Ending ipodStorage");
    }
}
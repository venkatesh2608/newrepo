package com.example;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaywrightTest {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeClass
    public void setUp() {
        // Initialize Playwright 
        playwright = Playwright.create();
        
        // Explicitly enforce headless execution mode
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(true)
        );
        
        // Open a clean browser context tab
        page = browser.newPage();
    }

    @Test
    public void testPageTitle() {
        // Navigate to target site
        page.navigate("https://example.com");

        // Fetch page details
        String title = page.title();
        System.out.println("#####Running Headless Test. Page Title: " + title+"#####");

        // TestNG Verification
        Assert.assertEquals(title, "Example Domain");
    }

    @AfterClass
    public void tearDown() {
        // Safe closure of browser and instance stream
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
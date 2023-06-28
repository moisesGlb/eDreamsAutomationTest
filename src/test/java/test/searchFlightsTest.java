package test;

import Utils.ExcelHandler;
import base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;

import java.time.Duration;

public class searchFlightsTest {

    WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void openBrowser(ITestContext context) {
        driver = DriverFactory.OpenBrowser(driver, context);
    }


    @DataProvider(name = "searchFlight")
    public Object[][] DatosNuevos() throws Exception {
        String excelPath = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\eDreams_test.xlsx";
        String hoja = "testData";
        ExcelHandler.setExcelFile(excelPath, hoja);
        int iTestCaseRow = ExcelHandler.getRowUsed();
        return ExcelHandler.getTableArray(excelPath, hoja, iTestCaseRow, 8);
    }

    @Test(dataProvider = "searchFlight")
    public void searchFlight(String flightOrigin, String flightDestiny, String acronymFlightOrigin,
                             String acronymFlightDestiny, String departureDay, String departureMonth,
                             String arrivalDay, String arrivalMonth) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
        homePage.acceptCookies();
        homePage.enterFlightOrigin(acronymFlightOrigin, flightOrigin);
        homePage.enterFlightDestiny(acronymFlightDestiny, flightDestiny);
        homePage.enterDepartureDate(departureDay, departureMonth);
        homePage.enterArrivalDate(arrivalDay, arrivalMonth);
        homePage.clickSearchFlights();
        Assert.assertEquals(flightOrigin, searchResultsPage.getOriginInput().getText());
        Assert.assertEquals(flightDestiny, searchResultsPage.getDestinyInput().getText());
    }


    @AfterMethod
    public void killBrowser() {
        DriverFactory.KillBrowser(driver);
    }

}

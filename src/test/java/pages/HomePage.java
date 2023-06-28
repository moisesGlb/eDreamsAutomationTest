package pages;

import Utils.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriverWait wait;
    WebDriver driver;

    private Alert alert;

    public HomePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
    }

    @FindBy(css = "div#buttons  #didomi-notice-agree-button")
    private WebElement acceptCookiesBtn;

    //*********************************Search Tabs Navigatin Bar************************************\\
    @FindBy(css = "div.odf-tabs-sm div:nth-child(1)")
    private WebElement flightsTab;

    @FindBy(css = "div.odf-tabs-sm div:nth-child(2)")
    private WebElement hotelsTab;

    @FindBy(css = "div.odf-tabs-sm div:nth-child(3)")
    private WebElement flightAndHotelsTab;

    @FindBy(css = "div.odf-tabs-sm div:nth-child(4)")
    private WebElement carRentalTab;

    // *********************************** Flight Tab ************************************\\

    @FindBy(css = "input#tripTypeSwitcher_roundTrip")
    private WebElement roundTripCHeckBox;

    @FindBy(css = "div[test-id='airport-departure'] input")
    private WebElement flightOrigin;

    @FindBy(css = "div[test-id='airport-departure'] ul")
    private WebElement flightOriginOptions;

    @FindBy(css = "div[test-id='airport-destination'] input")
    private WebElement flightDestiny;

    @FindBy(css = "div[test-id='airport-destination'] ul")
    private WebElement flightDestinyOptions;

    @FindBy(css = "div[data-testid='departure-date-picker'] input")
    private WebElement departureDate;

    @FindBy(css = "div[data-testid='return-date-picker'] input")
    private WebElement arrivalDate;

    @FindBy(css = "button[test-id='search-flights-btn']")
    private WebElement searchFlights;

    //********************************** Date Picker selectors *************************************\\

    @FindBy(css = "div.odf-popup-flex:nth-child(1) div.odf-calendar-title")
    private List<WebElement> months;

    @FindBy(css = "div.odf-popup-flex:nth-child(1) div.odf-col-top:nth-child(2) div.odf-calendar-day:not([class*='disabled']")
    private List<WebElement> daysMonthLeft;

    @FindBy(css = "div.odf-popup-flex:nth-child(1) div.odf-col-top:nth-child(3) div.odf-calendar-day:not([class*='disabled']")
    private List<WebElement> daysMonthRight;

    @FindBy(css = "div.odf-popup-flex:nth-child(1) div.odf-col-nogutter:nth-child(1) button")
    private WebElement calendarArrowLeft;

    @FindBy(css = "div.odf-popup-flex:nth-child(1) div.odf-col-nogutter:nth-child(4) button")
    private WebElement calendarArrowRight;

    //********************************** End of selectors *************************************\\

    public void acceptCookies(){
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
        acceptCookiesBtn.click();
    }

    public void selectFlightTab(){
        flightsTab.click();
    }



    public void enterFlightOrigin(String acronym, String origin){
        wait.until(ExpectedConditions.visibilityOf(flightOrigin));
        flightOrigin.sendKeys(acronym);
        selectCity(flightOriginOptions.findElements(By.tagName("li")),origin);
    }

    public void enterFlightDestiny(String acronym,String destiny){
        wait.until(ExpectedConditions.elementToBeClickable(flightDestiny));
        flightDestiny.sendKeys(acronym);
        selectCity(flightDestinyOptions.findElements(By.tagName("li")),destiny);
    }

    private void selectCity(List<WebElement> liElements, String str2check){
        Reporter.log("City to find: "+str2check);
        for (WebElement liElement : liElements) {
            if (liElement.getText().contains(str2check)) {
                liElement.click();
                Reporter.log("Citi founded: "+liElement.getText());
                break;
            }
        }
    }

    public void enterDepartureDate(String day, String month) {
        departureDate.click();
        selectDate(day,month);
    }

    public void enterArrivalDate(String day, String month) {
        arrivalDate.click();
        selectDate(day,month);
    }

    public void clickSearchFlights(){
        searchFlights.click();
    }

    private void selectDate(String day, String month){
        wait.until(ExpectedConditions.visibilityOf(calendarArrowRight));
        if (Utilities.getMonth(months.get(0).getText()).equalsIgnoreCase(month)){
            Reporter.log("Found the date on the left  month");
            selectDay(daysMonthLeft,day);
        }else if (Utilities.getMonth(months.get(1).getText()).equalsIgnoreCase(month)){
            selectDay(daysMonthRight,day);
            Reporter.log("Found the date on the right  month");
        }else{
            selectMonth(month);
            selectDay(daysMonthLeft,day);
        }
    }

    private void selectMonth(String month){
        int clicks = Utilities.monthDiference(month);
        int clicksDone = 0;
        Reporter.log("Looking for: "+month);
        while (clicks>clicksDone){
            calendarArrowRight.click();
            Reporter.log("Click arrow right ->");
            clicksDone++;
        }
    }

    private void selectDay(List<WebElement>days, String day){
        for (int i = 0; i<days.size();i++){
            if (days.get(i).getText().equals(day)){
                days.get(i).click();
                //Reporter.log("day founded: "+days.get(i).getText());
                break;
            }
        }
    }
}

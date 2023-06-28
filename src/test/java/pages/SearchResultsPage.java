package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {
    private WebDriverWait wait;
    WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }

    @FindBy(css = "div#origin_field_container input")
    private WebElement originInput;

    @FindBy(css = "div#destination_field_container input")
    private WebElement destinyInput;

    @FindBy(css = "div.od-ui-datepicker-wrapper div.od-ui-datepicker-display")
    private List<WebElement> travelDates;

    @FindBy(css = "div.view_container div.prisma-input div span:nth-child(2)")
    private WebElement passengersLabel;


    public WebElement getOriginInput() {
        wait.until(ExpectedConditions.visibilityOf(originInput));
        return originInput;
    }

    public WebElement getDestinyInput() {
        wait.until(ExpectedConditions.visibilityOf(destinyInput));
        return destinyInput;
    }

    public WebElement getPassengersLabel() {
        wait.until(ExpectedConditions.visibilityOf(passengersLabel));
        return passengersLabel;
    }

    public WebElement getDepartureDate(){
        wait.until(ExpectedConditions.visibilityOf(destinyInput));
        return travelDates.get(0);
    }

    public WebElement getArrivaldate(){
        wait.until(ExpectedConditions.visibilityOf(destinyInput));
        return travelDates.get(1);
    }
}

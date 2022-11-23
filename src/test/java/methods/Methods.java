package methods;

import driver.DriverSetup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import model.SelectorInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import selector.Selector;
import selector.SelectorFactory;
import selector.SelectorType;

import javax.annotation.Nullable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Methods {

    AppiumDriver driver;
    FluentWait<AppiumDriver> wait;
    protected Selector selector = SelectorFactory.createElementHelper(SelectorType.ANDROID);

    public Methods() {
        driver = DriverSetup.driver;
        wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);
    }

    public MobileElement findElement(By by) {
        return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public List<MobileElement> findElements(By by) {
        return wait.until(new ExpectedCondition<List<MobileElement>>() {
            @Nullable
            @Override
            public List<MobileElement> apply(@Nullable WebDriver driver) {
                List<MobileElement> elements = driver.findElements(by);
                return elements.size() > 0 ? elements : null;
            }
        });
    }

    public MobileElement findElementBykey(String key) {
        SelectorInfo selectorInfo = selector.getSelectorInfo(key);
        return selectorInfo.getIndex() > 0 ? findElements(selectorInfo.getBy())
                .get(selectorInfo.getIndex()) : findElement(selectorInfo.getBy());
    }

    public void click(String key) {
        findElementBykey(key).click();
    }

    public void sendKeys(String key, String text) {
        findElementBykey(key).sendKeys(text);
    }

    public String getText(String key) {
        return findElementBykey(key).getText();
    }

    public String addDay(int day) {
        DateFormat dateFormat = new SimpleDateFormat("d");
        Date date = new Date(new Date().getTime() + 86400000L * day); // one day = 1000 * 60 * 60 * 24 = 86400000
        return dateFormat.format(date);
    }
}

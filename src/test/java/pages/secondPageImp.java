package pages;

import com.thoughtworks.gauge.Step;
import methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class secondPageImp {

    Methods methods;
    Logger logger = LogManager.getLogger(secondPageImp.class);

    public secondPageImp() {
        methods = new Methods();
    }

    @Step("Ornek click islemi yapildi")
    public void clickIslemi() {
        //methods.click("btnFrom");
        logger.info("btn is clicked");

    }
}

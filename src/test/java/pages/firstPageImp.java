package pages;

import com.thoughtworks.gauge.Step;
import methods.Methods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class firstPageImp {

    Methods methods;
    Logger logger = LogManager.getLogger(firstPageImp.class);

    public firstPageImp() {
        methods = new Methods();
    }

    @Step("first step")
    public void firstStep() {
        System.out.printf("step working");
        logger.info("step run");
    }

}

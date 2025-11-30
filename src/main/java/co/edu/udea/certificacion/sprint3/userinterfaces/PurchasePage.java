package co.edu.udea.certificacion.tallercalidad.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PurchasePage {
    public static final Target NAME_INPUT = Target.the("Name input").located(By.id("inputName"));
    public static final Target ADDRESS_INPUT = Target.the("Address input").located(By.id("address"));
    public static final Target CITY_INPUT = Target.the("City input").located(By.id("city"));
    public static final Target STATE_INPUT = Target.the("State input").located(By.id("state"));
    public static final Target ZIP_CODE_INPUT = Target.the("Zip Code input").located(By.id("zipCode"));
    public static final Target CARD_TYPE_DROPDOWN = Target.the("Card Type dropdown").located(By.id("cardType"));
    public static final Target CREDIT_CARD_NUMBER_INPUT = Target.the("Credit Card Number input").located(By.id("creditCardNumber"));
    public static final Target MONTH_INPUT = Target.the("Month input").located(By.id("creditCardMonth"));
    public static final Target YEAR_INPUT = Target.the("Year input").located(By.id("creditCardYear"));
    public static final Target NAME_ON_CARD_INPUT = Target.the("Name on Card input").located(By.id("nameOnCard"));
    public static final Target PURCHASE_FLIGHT_BUTTON = Target.the("Purchase Flight button").located(By.cssSelector("input[type='submit']"));
}
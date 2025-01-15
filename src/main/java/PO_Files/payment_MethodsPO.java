package PO_Files;

import Utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class payment_MethodsPO {
        private WebDriver driver;
        private By upi_method = By.xpath("//div[@data-value=\"upi\"]");
        private By upi_id = By.xpath("//input[@name=\"vpa\"]");
        private By verify_and_pay_button = By.xpath("//button[@data-testid=\"vpa-submit\"]");

        private By card_method = By.xpath("//div[@data-value=\"card\"]");
        private By card_number = By.xpath("//input[@name=\"card.number\"]");
        private By validity = By.xpath("//input[@name=\"card.expiry\"]");
        private By cvv = By.xpath("//input[@name=\"card.cvv\"]");
        private By continue_button = By.xpath("//button[@data-test-id=\"add-card-cta\"]");

        private By  emi_method = By.xpath("//div[@data-value=\"emi\"]");


        private By netbanking_method = By.xpath("//div[@data-value=\"netbanking\"]");
        private By enter_bank_name = By.xpath("//input[@name=\"bank\"]");
        private By successButton = By.xpath("//button[@class='success']");

        private By wallet_method = By.xpath("//div[@data-value=\"wallet\"]");

        private By payLater_method = By.xpath("//div[@data-value=\"Pay Later\"]");






        public payment_MethodsPO(WebDriver driver){
            this.driver = driver;
        }


        public void UPI_Method(WebDriver driver,String id){

            ElementUtils.actionClick(driver,upi_method);

            ElementUtils.actionSendkeys(driver,upi_id,id);

            ElementUtils.actionClick(driver,verify_and_pay_button);

        }

        public void Cards_Method(WebDriver driver,String cardNumber,String validThrough,String Cvv){

            ElementUtils.actionClick(driver,card_method);
            ElementUtils.actionSendkeys(driver,card_number,cardNumber);
            ElementUtils.actionSendkeys(driver,validity,validThrough);
            ElementUtils.actionSendkeys(driver,cvv,Cvv);
            ElementUtils.actionClick(driver,continue_button);

        }

        public  void EMI_Method(WebDriver driver){
            ElementUtils.actionClick(driver,emi_method);


        }

        public void Netbanking_Method(WebDriver driver, String bankName) {

            ElementUtils.actionClick(driver, netbanking_method);
            ElementUtils.actionSendkeys(driver,enter_bank_name,bankName);

            for (WebElement element : driver.findElements(By.xpath("//div[@role='button']"))) {
                if (element.getText().contains(bankName)) {
                    element.click();
                    break;
                }
            }

            ElementUtils.actionClick(driver,successButton);

        }


        public void Wallet_Method(WebDriver driver, String walletOption){

            ElementUtils.actionClick(driver,wallet_method);

            String xpath = "//div[@data-value='" + walletOption + "']";
            WebElement wallet = driver.findElement(By.xpath(xpath));
            wallet.click();
        }

        public  void PayLater_Method(WebDriver driver){

            ElementUtils.actionClick(driver,payLater_method);

        }

    }



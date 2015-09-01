package com.flipkart.android;

import com.flipkart.Pages.AppiumBasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 25/08/15 by kaushik.p.
 */
public class POC_Page extends AppiumBasePage{

    private WebDriverWait wait;
    private String individualTestdescription;
    private int screenTop;
    private int ScreenBottom;
    private int screenWidth;
    private int screenHeight;
    private Dimension viewSize;
    private int startOfView;
    private int i;


    public POC_Page(WebDriver driver) {
        super(driver);
        wait=new WebDriverWait(driver,20);
    }
    public String getExceptionMessage(Exception e){
        if(e.getClass().getSimpleName().equals("Exception")){
            return e.getMessage();
        }

        return e.getClass().getSimpleName();

    }


    public void ClickSubmit(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(loginPageLocators.get("submitbutton"))));
        driver.findElement(getBy(loginPageLocators.get("submitbutton"))).click();
    }

    public void SkipLogin(){
        Dimension dimension=driver.manage().window().getSize();
        setScreenBottom(dimension.getHeight()-10);
        setScreenWidth(dimension.getWidth());
        wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(loginPageLocators.get("skipbutton"))));

        driver.findElement(getBy(loginPageLocators.get("skipbutton"))).click();

        if (isElementPresent(getBy(homePageLocators.get("ToolTip")))) {
            driver.findElement(getBy(homePageLocators.get("ToolTip"))).click();

        }

    }
    public boolean login1(String email, String password) throws Exception {
        try {
            Dimension dimension=driver.manage().window().getSize();
            setScreenBottom(dimension.getHeight()-10);
            setScreenWidth(dimension.getWidth());

            WebElement webElement=null;
            wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(loginPageLocators.get("login_button"))));
            driver.findElement(getBy(loginPageLocators.get("login_button"))).click();

            driver.findElement(getBy(loginPageLocators.get("mobile_text"))).click();

            if (isElementPresent(getBy(loginPageLocators.get("close_button")))) {
                Thread.sleep(5000);

                driver.findElements(getBy(loginPageLocators.get("close_button"))).get(0).click();

            }
            else
                individualTestdescription+="\nIN CLASS " + Thread.currentThread().getStackTrace()[0].getClassName() + ", IN METHOD " + Thread.currentThread().getStackTrace()[0].getMethodName()+", IN LINE " + Thread.currentThread().getStackTrace()[0].getLineNumber();


            //        webElement=driver.findElement(getBy(loginPageLocators.get("mobile_text")));
            //          webElement.click();
//            webElement.sendKeys(email);
            driver.findElement(getBy(loginPageLocators.get("mobile_text"))).click();
            driver.findElement(getBy(loginPageLocators.get("mobile_text"))).sendKeys(email);

            driver.findElement(getBy(loginPageLocators.get("passwd_text"))).click();
            driver.findElement(getBy(loginPageLocators.get("passwd_text"))).sendKeys(password);

            if (isElementPresent(getBy(loginPageLocators.get("login_button"))))
                driver.findElement(getBy(loginPageLocators.get("login_button"))).click();
            else
                throw new Exception("login button not found in login");

            Thread.sleep(3000);
            if (isElementPresent(getBy(homePageLocators.get("skip_button"))))
                driver.findElement(getBy(homePageLocators.get("skip_button"))).click();

            if (isElementPresent(getBy(homePageLocators.get("introclose"))))
                driver.findElement(getBy(homePageLocators.get("introclose"))).click();




            if (isElementPresent(getBy(homePageLocators.get("ToolTip")))) {
                driver.findElement(getBy(homePageLocators.get("ToolTip"))).click();
                return true;
            }



        } catch (Exception e) {
            logger.info("There was an issue while logging in ");
            System.out.println(e.getMessage());
            individualTestdescription+=","+getExceptionMessage(e);
            return false;
        }
        return true;
    }

    public void SwipeCross(int x1,int y1,int x2,int y2){
        TouchAction touchAction = new TouchAction(this.driver);

        // appium converts press-wait-moveto-release to a swipe action
        touchAction.longPress(x1, y1)
                .moveTo(x2,y2).release();

        touchAction.perform();


    }

    public  void compareToTopAndBottom(WebElement webElement1) throws InterruptedException {
        System.out.println("Entered compare position");
        int X1,Y1,X2,Y2;
        X1=webElement1.getLocation().getX();
        Y1=webElement1.getLocation().getY();


//        System.out.println("Screen top : "+getScreenTop()+" Screen bottom : "+getScreenBottom());
        int height=getScreenBottom()-getScreenTop();
        System.out.println("height : "+height/6);
        if(Math.abs(Y1-getScreenTop())<=(height/6)){

            Thread.sleep(5000);
            SwipeToUpSmall();
        }
        else if(Math.abs(Y1-getScreenBottom())<=(height/6)){

            Thread.sleep(5000);
            SwipeToDownSmall();
        }
    }



    public boolean checkForOOS(){

        if(isElementPresent(getBy(productPageLocators.get("notifyme")))||isElementPresent(getBy(productPageLocators.get("oosText")))){
            System.out.println("oos status : "+true);
            return true;
        }
        System.out.println("oos status : "+false);
        return false;
    }

    public void RemoveAdFromScreen(){
        while(isElementPresent(getBy(landingPageLocators.get("adproducts")))){
            SwipeToBottom();
        }
    }

    public List<String> grep(Reader inReader, String searchFor) throws IOException {
        System.out.println("entered grep");
        BufferedReader reader = null;
        String line;
        List<String> lines = new ArrayList<String>();
        try {
            reader = new BufferedReader(inReader);

            while ((line = reader.readLine()) != null) {

                if (line.contains(searchFor)) {


                    lines.add(new String(line));

                }
            }
        } finally {

            if (reader != null) {
                reader.close();
            }
        }

        return lines;
    }
    public boolean CheckAdProd() throws Exception {
        int i;

        try {
//            for(i=0;i<12;i++)
//                SwipeToTop();
            for (i = 0; i < 15; i++) {
//                UiObject btView = new UiObject(new UiSelector().text("Bluetooth"));
                if (isElementPresent(getBy(landingPageLocators.get("adproducts")))) {
                    compareToTopAndBottom(driver.findElement(getBy(landingPageLocators.get("adproducts"))));



                    WebElement layoutelement= driver.findElementByXPath("(//android.widget.ImageView[@resource-id='com.flipkart.android.feature.AdsSdkInitializeChanges:id/product_list_ad_image']/ancestor::android.widget.LinearLayout)[last()]");
                        System.out.println(layoutelement.getLocation());
                    setViewSize(layoutelement.getSize());

//                    List<WebElement> webElements=driver.findElementsByXPath("//android.widget.ListView/android.widget.LinearLayout");
//
//                    WebElement w=webElements.get(0);

                    SwipeElementToScreenTop(layoutelement);
                    SwipeToTop(getViewSize().getHeight()/2);
                        System.out.println(layoutelement.getLocation());
                    layoutelement= driver.findElementByXPath("(//android.widget.ImageView[@resource-id='com.flipkart.android.feature.AdsSdkInitializeChanges:id/product_list_ad_image']/ancestor::android.widget.LinearLayout)[last()]");
                        System.out.println(layoutelement.getLocation());
                    SwipeElementToScreenBottom(layoutelement);


                    return true;
                } else
                    SwipeToBottom();
            }
            throw new Exception("No ad product was found in check ad prod");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            individualTestdescription+=","+getExceptionMessage(e);
            return false;
        }
    }

    public boolean OpenAdProd() throws Exception {

        try {

            WebElement webElement=null;

            System.out.println("reached open ad prod");
            i=0;
            while(!isElementPresent(getBy(landingPageLocators.get("adproducts")))&&i<20) {
                SwipeToBottom();


                i++;
            }

            if (!(isElementPresent(getBy(landingPageLocators.get("adproducts"))))) {

                throw new Exception("no ads found in open ad prod");
            }
            Thread.sleep(3000);
            compareToTopAndBottom(driver.findElement(getBy(landingPageLocators.get("adproducts"))));
            driver.findElement(getBy(landingPageLocators.get("adproducts"))).click();
//            if (isElementPresent(getBy(productPageLocators.get("ToolTip3")))) {
//                driver.findElement(getBy(productPageLocators.get("ToolTip3"))).click();
//                return true;
//            }
            if (isElementPresent(getBy(productPageLocators.get("buynow"))))
                return true;
            else if(checkForOOS()==true)
                return true;
            else
                throw new Exception("No tooltip or buy now button found in Prod");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            individualTestdescription+=","+getExceptionMessage(e);
            return false;
        }
    }


    public boolean EnterProd() throws Exception {
        try {

            if (isElementPresent(getBy(landingPageLocators.get("firstprodtext")))) {
                compareToTopAndBottom(driver.findElements(getBy(landingPageLocators.get("firstprodtext"))).get(0));

                driver.findElements(getBy(landingPageLocators.get("firstprodtext"))).get(0).click();

                if (isElementPresent(getBy(productPageLocators.get("ToolTip3"))))
                    driver.findElement(getBy(productPageLocators.get("ToolTip3"))).click();
                return true;
            }
            return true;

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            individualTestdescription+=","+getExceptionMessage(e);
            return false;
        }
    }

    public boolean Search(String searchString) throws Exception {

        try {
            WebElement webElement=null;
            individualTestdescription="";

            wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(homePageLocators.get("SearchButtonHomePage1"))));

            if (isElementPresent(getBy(homePageLocators.get("SearchButtonHomePage1")))) {
                driver.findElement(getBy(homePageLocators.get("SearchButtonHomePage1"))).click();


                driver.findElement(getBy(homePageLocators.get("SearchButtonHomePage3"))).sendKeys(searchString + "\n");                Thread.sleep(2500);
                Thread.sleep(2500);


                if (isElementPresent(getBy(productPageLocators.get("ToolTip2"))))
                    driver.findElement(getBy(productPageLocators.get("ToolTip2"))).click();


                if(isElementPresent(getBy(productPageLocators.get("continueSearch")))) {
                    driver.findElement(getBy(productPageLocators.get("continueSearch"))).click();
                    throw new Exception("continue search found in Search");
                }
                else if(isElementPresent(getBy(productPageLocators.get("browseagain")))) {
                    driver.findElement(getBy(productPageLocators.get("browseagain"))).click();
                    EnterProd();

                    Logo();
                    throw new Exception("browse again found in search");
                }
                while(!isElementPresent(getBy(landingPageLocators.get("Firstprod")),0)){
                    Toggle();
                }
                if (isElementPresent(getBy(landingPageLocators.get("Firstprod")),0)) {

                    setScreenTop(driver.findElements(getBy(landingPageLocators.get("Firstprod"))).get(0).getLocation().getY());

                    setScreenHeight(Math.abs(getScreenTop()-getScreenBottom()));
                    List<WebElement> webElements=driver.findElementsByXPath("//android.widget.ListView");
//                    WebElement webElement1=driver.findElementByXPath("//android.widget.ListView/following-sibling::android.widget.RelativeLayout[1]");
//                    System.out.println(webElement1.getLocation());
                    setStartOfView(webElements.get(0).getLocation().getY());

                }
                wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(landingPageLocators.get("toggle2"))));



                    if (isElementPresent(getBy(landingPageLocators.get("toggle2")))) {
                        return true;
                    } else {
                        throw new Exception("toggle view 2 not present in Search");
                    }

//                } else return true;
            } else
            {
                throw new Exception("Search text box not found in Search");
            }

        }
        catch (Exception e) {
            logger.info("There was an issue while searching for string : " + searchString);
            System.out.println(e.getMessage());
            individualTestdescription+=","+getExceptionMessage(e)+" Line Num "+Thread.currentThread().getStackTrace()[1].getLineNumber();
            return false;
        }

    }

    public boolean Logo() throws Exception {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(getBy(homePageLocators.get("FlipkartIcon"))));
            if (isElementPresent(getBy(homePageLocators.get("FlipkartIcon")))) {
                driver.findElement(getBy(homePageLocators.get("FlipkartIcon"))).click();
                return true;
            } else
                throw new Exception("Flipkart logo Not found in Logo");
        }
        catch (Exception e) {
            individualTestdescription+=","+getExceptionMessage(e);
            return false;
        }
    }


    public boolean Toggle() throws Exception {
        try {
            WebElement webElement=null;
            System.out.println("entered toggle check");

            if (isElementPresent(getBy(landingPageLocators.get("toggle2")))) {


                driver.findElement(getBy(landingPageLocators.get("toggle2"))).click();
                Thread.sleep(2000);




                return true;
            }
            else
                return false;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getIndividualTestdescription() {
        return individualTestdescription;
    }

    public int getScreenTop() {
        return screenTop;
    }

    public void setScreenTop(int screenTop) {
        this.screenTop = screenTop;
    }

    public int getScreenBottom() {
        return ScreenBottom;
    }

    public void setScreenBottom(int screenBottom) {
        ScreenBottom = screenBottom;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }


    public int ScreenHeight() {
        Dimension dimensions = driver.manage().window().getSize();
        int screenHeight = dimensions.getHeight();
        return screenHeight;
    }

    public int ScreeWidth() {
        Dimension dimensions = driver.manage().window().getSize();
        int screenWidth = dimensions.getWidth();
        return screenWidth;
    }

    public void SwipeToUpSmall() {
//        ((AppiumDriver) driver).swipe(Math.round(ScreeWidth() / 2), getScreenTop()+getScreenHeight()/6, Math.round(ScreeWidth() / 2), getScreenBottom()-getScreenHeight()/2, 0);
        ((AppiumDriver) driver).swipe(Math.round(ScreeWidth() / 2), (int) Math.round(ScreenHeight() / 2), Math.round(ScreeWidth() / 2), (int) Math.round(ScreenHeight() / 1.1), 0);
    }

    public void SwipeToDownSmall() {
//        ((AppiumDriver) driver).swipe(Math.round(ScreeWidth() / 2), getScreenBottom()-getScreenHeight()/6, Math.round(ScreeWidth() / 2), getScreenTop()+getScreenHeight()/4, 0);
        ((AppiumDriver) driver).swipe(Math.round(ScreeWidth() / 2), (int) Math.round(ScreenHeight() / 1.2), Math.round(ScreeWidth() / 2), (int) Math.round(ScreenHeight() /2.5), 0);
    }

    public void SwipeElementToScreenTop(WebElement webElement){
        WebElement adelement=driver.findElement(getBy(landingPageLocators.get("adproducts")));
        int topEnd=getStartOfView(); //getScreenTop();

        int halfwidth=ScreeWidth()/2;
        int elementY=webElement.getLocation().getY();

        //if(Math.abs(elementY-topEnd)<getScreenHeight())
//        driver.swipe(halfwidth, elementY,halfwidth, topEnd,5000);

        int distance=elementY-topEnd;
        if(distance>0)
            SwipeToBottom(Math.abs(distance));
        else
            SwipeToTop(Math.abs(distance));

        // appium converts press-wait-moveto-release to a swipe action


//        touchAction.perform();

    }

    public void SwipeElementToScreenBottom(WebElement webElement){

        WebElement adelement=driver.findElement(getBy(landingPageLocators.get("adproducts")));
        int bottomEnd=getScreenBottom();
        TouchAction touchAction = new TouchAction(this.driver);
        int halfwidth=ScreeWidth()/2;
        int elementY=webElement.getLocation().getY()+getViewSize().getHeight();

        // appium converts press-wait-moveto-release to a swipe action
        touchAction.press(halfwidth,  elementY).waitAction(2000)
                .moveTo(halfwidth, bottomEnd).release();

        int distance=bottomEnd-elementY;
        if(distance>0)
            SwipeToTop(Math.abs(distance));
        else
            SwipeToBottom(Math.abs(distance));

    }


    public void SwipeToBottom() {
        ((AppiumDriver) driver).swipe(Math.round(ScreeWidth() / 2), getScreenBottom()-getScreenHeight()/6, Math.round(ScreeWidth() / 2), getScreenTop()+getScreenHeight()/6, 2000);
    }


    public void SwipeToBottom(int distance) {
        ((AppiumDriver) driver).swipe(Math.round(ScreeWidth() / 2), getScreenBottom()-1, Math.round(ScreeWidth() / 2), getScreenBottom()-distance, 2000);
    }


    public void SwipeToTop() {
        ((AppiumDriver) driver).swipe(Math.round(ScreeWidth() / 2), getScreenTop()+getScreenHeight()/6, Math.round(ScreeWidth() / 2), getScreenBottom()-getScreenHeight()/6, 2000);
    }

    public void SwipeToTop(int distance) {
        ((AppiumDriver) driver).swipe(Math.round(ScreeWidth() / 2), getScreenTop()+1, Math.round(ScreeWidth() / 2), getScreenTop()+distance, 2000);
    }

    public void SwipeLeft(int x,int y) {

        ((AppiumDriver) driver).swipe(x, y, 0+3*getScreenWidth() / 4 , y, 2000);
    }


    public Dimension getViewSize() {
        return viewSize;
    }

    public void setViewSize(Dimension viewSize) {
        this.viewSize = viewSize;
    }
    public int getStartOfView() {
        return startOfView;
    }

    public void setStartOfView(int startOfView) {
        this.startOfView = startOfView;
    }


}

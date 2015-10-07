package com.flipkart.android;

import com.flipkart.Drivers.BaseConfig;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;

import static org.testng.Assert.assertEquals;

/**
 * Created on 25/08/15 by kaushik.p.
 */
public class POC extends BaseConfig {
    POC_Page pocpage;
    Boolean status1;
    @BeforeSuite
    public void POC_setup() throws Exception {
        System.out.println("came to poc");
        pocpage = new POC_Page(driver);
//        for(int i=0;i<5;i++)
//            pocpage.SwipeToDownSmall();
//        pocpage.ClickSubmit();
        pocpage.SkipLogin();
//        status1 = pocpage.login1("mafiaflipkart1@gmail.com", "Adplatformtest");
    }


    @AfterMethod
    public void tearDown(ITestResult result) {


            pocpage.BackInfinite();



    }

    @Test(priority =  0)
    public void AdBeacon() throws Exception {
        System.out.println("Search for AdBeacon");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        Process p1 = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat  -s AD_SDK_LOG  > src/main/resources/logcat.txt"});

        Thread.sleep(4000);

        if (pocpage.Search("tablet"))
            if (pocpage.CheckAdProd()) {
                File logcat = new File("src/main/resources/logcat.txt");
                boolean status = pocpage.grep(new FileReader(logcat), "\"event\":\"adBeacon\"", "Event String");

                logcat.delete();
                p1.destroy();
                System.out.println("status::::" + status);
                assertEquals(status, true, "Adbeacon issue");

            }
            else {
                throw new SkipException("Ads not found");

            }
    }

    @Test(priority = 1)
    public void AdView() throws Exception {
        System.out.println("SearchForAdAndRemoveAdFromScreen");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        pocpage.SwipeCross(960, 1430, 866, 1480);
        if(pocpage.Search("bottles"))

            if (pocpage.CheckAdProd()) {
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
                Process p1 = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat  -s AD_SDK_LOG  > src/main/resources/logcat.txt"});
                pocpage.RemoveAdFromScreen();

                Thread.sleep(4000);

                File logcat = new File("src/main/resources/logcat.txt");


                boolean status = pocpage.grep(new FileReader(logcat), "\"event\":\"adView\",\"maxViewPercentage\":100", "Event String");

                logcat.delete();
                p1.destroy();
                System.out.println("status::::"+status);
                assertEquals(status, true, "adview issue");
                pocpage.Back2();

            }
            else {
                throw new SkipException("Ads not found");

            }
    }



    @Test(priority = 2)
    public void AdInteraction_Tap() throws Exception {

        System.out.println("SearchForAdAndClickOnIt");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        if (pocpage.Search("bottles")){
            if(pocpage.CheckAdProd()) {
                WebElement adelement = pocpage.getAdElement();
                pocpage.SwipeElementToScreenBottom(adelement);
                Process p1 = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat  -s AD_SDK_LOG  > src/main/resources/logcat.txt"});


               pocpage.OpenAdProd();
                    Thread.sleep(1000);
                    File logcat = new File("src/main/resources/logcat.txt");

                    boolean status = pocpage.grep(new FileReader(logcat), "\"activity\":\"TAP\",\"event\":\"adInteraction\"", "Event String");

                    logcat.delete();
                    p1.destroy();
                    System.out.println("status::::" + status);
                    assertEquals(status, true, "adinteraction issue");


                    pocpage.Back1();

                    pocpage.Back2();
                }

        }
    }


    @Test(priority = 3)
    public void AdLead() throws Exception {
        System.out.println("SearchForAdAndToggleToGenerateEvent");

        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        if (pocpage.Search("mobiles"))

            if (pocpage.OpenAdProd()) {
                Thread.sleep(1000);

                Process p1 = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat  -s AD_SDK_LOG  > src/main/resources/logcat.txt"});
                pocpage.AddCartItems();

                File logcat = new File("src/main/resources/logcat.txt");

                boolean status = pocpage.grep(new FileReader(logcat), "\"activity\":\"ADD_CART\",\"event\":\"adLead\"", "Event String");

                logcat.delete();
                p1.destroy();
                System.out.println("status::::"+status);
                assertEquals(status, true, "adlead issue");

                pocpage.Back1();
                pocpage.Back2();
            }
            else {
                throw new SkipException("Ads not found");

            }
    }


    @Test(priority = 4)
    public void SearchForAdAndToggleToGenerateEvent() throws Exception {
        System.out.println("SearchForAdAndToggleToGenerateEvent");

        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        if(pocpage.Search("shirts")) {
            pocpage.CheckAdProd();
            while (pocpage.isAdPresentInScreen()) {
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
                WebElement adelement = pocpage.getAdElement();
                pocpage.SwipeElementToScreenBottom(adelement);
                pocpage.Toggle();
                Thread.sleep(1000);
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"SearchForAdAndToggleToGenerateEvent\" >> src/main/resources/logcat.txt"});
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
//
            }

        }
        pocpage.Back2();
    }



    @Test(priority = 5)
    public void SwipeOnADToProductEvent() throws Exception {
        System.out.println("SwipeOnADToProductEvent");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("shirts"))
           {
                if (pocpage.OpenAdProd()) {
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

                    pocpage.SwipeLeft(80,pocpage.getScreenHeight()/2);
                    Thread.sleep(1000);
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"SwipeOnADToProductEvent\" >> src/main/resources/logcat.txt"});
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
//

                    pocpage.Back1();
                    pocpage.Back2();

                }

           }

    }

    @Test(priority = 6)
    public void OpenDrawerViewToSeeAd() throws Exception {
        System.out.println("OpenDrawerViewToSeeAd");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("shoes"))
             {
                if (pocpage.OpenAdProd()) {

                    pocpage.openDrawerView();
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
                    pocpage.Click(30,3*pocpage.getScreenWidth()/4);
                    Thread.sleep(1000);
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"OpenDrawerViewToSeeAd\" >> src/main/resources/logcat.txt"});
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});

                    pocpage.Back1();
                    pocpage.Back2();
                }


            }

    }


    @Test(priority = 7)
    public void PressBackButtonOnAds() throws Exception {
        System.out.println("PressBackButtonOnAds");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("watches"))
             {
                if (pocpage.CheckAdProd()) {


                }

            }
        pocpage.Back2();
        Thread.sleep(1000);

        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"PressBackButtonOnAds\" >> src/main/resources/logcat.txt"});
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});



        if(pocpage.Search("shoes"))
        {
            if (pocpage.OpenAdProd()) {

            }

        }
        pocpage.Back1();
        Thread.sleep(1000);
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"PressBackButtonOnAds\" >> src/main/resources/logcat.txt"});
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});


        pocpage.Back2();
    }

    @Test(priority = 8)
    public void CheckForEventsWhenNoAdsAppear() throws Exception {
        System.out.println("CheckForEventsWhenNoAdsAppear");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("samsung s3 neo"))
        {
            if (pocpage.CheckAdProd()) {

            }

        }
        Thread.sleep(1000);
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"CheckForEventsWhenNoAdsAppear\" >> src/main/resources/logcat.txt"});
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});

        pocpage.Back2();
    }

    @Test(priority = 9)
    public void AdsBlockingByFilter() throws Exception {
        System.out.println("AdsBlockingByFilter");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("jeans"))
        {
            if (pocpage.CheckAdProd()) {
                pocpage.ApplyFilter();
                Thread.sleep(1000);
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"AdsBlockingByFilter\" >> src/main/resources/logcat.txt"});
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
            }

        }

        pocpage.Back1();
        pocpage.Back2();
    }


    @Test(priority = 10)
    public void AdsBlockingBySort() throws Exception {
        System.out.println("AdsBlockingBySort");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("jeans"))
        {
            if (pocpage.CheckAdProd()) {
                pocpage.ApplySort();
                Thread.sleep(1000);
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"AdsBlockingBySort\" >> src/main/resources/logcat.txt"});
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
            }

        }

        pocpage.Back1();
        pocpage.Back2();


    }



////    @Test
////    public void pressHomeButtonOnAd() throws Exception {
////        System.out.println("pressHomeButtonOnAd");
////        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
////
////        if(pocpage.Search("shirts"))
////        {
////            if (pocpage.CheckAdProd()) {
////                pocpage.pressHome();
////                Thread.sleep(1000);
////                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"CheckForEventsWhenNoAdsAppear\" >> src/main/resources/logcat.txt"});
////                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
////                driver.sendKeyEvent(0x000000bb);//switch between app
////                  List<WebElement> elem = _driver.findElements(By.className("android.widget.FrameLayout"));
////                  for (int i=0;i<elem.size()-1;i++){
////                  System.out.println("elem.get(i).getAttribute(name) :: " + elem.get(i).getAttribute("name")) ;
////                    if (elem.get(i).getAttribute("name").equals("App Name 1 text")){
////                        System.out.println("Tapping :: " + elem.get(i).getAttribute("name")) ;
////                        _driver.tap(1, elem.get(i), 100);
////                        break;
////                    }
////                  adb shell am start -n com.flipkart.android/.SplashActivity
////
////                  }
////                    pocpage.pressMenu();
////            }
////
////        }
//
//        pocpage.Back1();
//        pocpage.Back2();
//
//
//    }



    @Test(priority = 12)
    public void EventsGettingTriggeredEvenBeforeCrossingMinimumThreshold() throws Exception {
        System.out.println("EventsGettingTriggeredEvenBeforeCrossingMinimumThreshold");

        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        if(pocpage.Search("shirts")) {
            pocpage.CheckAdProd();
            while (pocpage.isAdPresentInScreen()) {
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
                WebElement adelement = pocpage.getAdElement();
                pocpage.SwipeElementToScreenBottom(adelement);
                pocpage.MoveDownByHalfViewLength();
                Thread.sleep(1000);
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"EventsGettingTriggeredEvenBeforeCrossingMinimumThreshold\" >> src/main/resources/logcat.txt"});
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
//
            }

        }
        pocpage.Back2();


    }

//
//    @Test
//    public void BringingAppBackFromStackAndCreatingAdEvents() throws Exception {
//
//
//    }


    @Test(priority = 14)
    public void openDrawerOnOrganicProductToSeeAdsANdCloseDrawerToSeeEvent() throws Exception {//pending positioning of add in drawer
        System.out.println("openDrawerOnOrganicProductToSeeAdsANdCloseDrawerToSeeEvent");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("shoes"))
        {
            if (pocpage.EnterProd()) {

                pocpage.openDrawerView();
                if(pocpage.CheckAdProdForDrawer()) {
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
                    WebElement adelement=pocpage.getAdElement();
                    pocpage.SwipeElementToScreenTop(adelement);
                    pocpage.Click(30, 3 * pocpage.getScreenWidth() / 4);
                    Thread.sleep(1000);
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"openDrawerOnOrganicProductToSeeAdsANdCloseDrawerToSeeEvent\" >> src/main/resources/logcat.txt"});
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
                }
                pocpage.Back1();
                pocpage.Back2();
            }


        }


    }

    @Test(priority = 15)
    public void CheckForMultipleAdviewEvents() throws Exception {

        System.out.println("CheckForMultipleAdviewEvents");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        pocpage.SwipeCross(960, 1430, 866, 1480);
        if(pocpage.Search("watches"))

            if (pocpage.CheckAdProd()) {
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
                pocpage.RemoveAdFromScreen();
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"CheckForMultipleAdviewEvents\" >> src/main/resources/logcat.txt"});
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});

            }


        pocpage.Back2();


    }


}

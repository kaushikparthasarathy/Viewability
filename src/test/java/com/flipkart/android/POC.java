package com.flipkart.android;

import com.flipkart.Drivers.BaseConfig;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

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

        if (result.getStatus() == ITestResult.FAILURE) {
            pocpage.BackInfinite();

        }

    }

    @Test(priority = 0)
    public void SearchForAdAndRemoveAdFromScreen() throws Exception {
        System.out.println("SearchForAdAndRemoveAdFromScreen");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        pocpage.SwipeCross(960, 1430, 866, 1480);
        if(pocpage.Search("watches"))

            if (pocpage.CheckAdProd()) {
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
                pocpage.RemoveAdFromScreen();
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"SearchForAdAndRemoveAdFromScreen\" >> src/main/resources/logcat.txt"});
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
//                Process p1 = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -d -s AD_SDK_LOG  > src/main/resources/logcat.txt"});
////                Thread.sleep(4000);
//
//                Thread.sleep(4000);
//
//                File logcat = new File("src/main/resources/logcat.txt");
//
//
//                List<String> adlines = pocpage.grep(new FileReader(logcat), "adView");
//
//                logcat.delete();
//                p1.destroy();
//                System.out.println(adlines.get(0));


            }


        pocpage.Back2();
    }
    @Test(priority = 1)
    public void SearchForAdAndClickOnIt() throws Exception {

        System.out.println("SearchForAdAndClickOnIt");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        if (pocpage.Search("shoes"))

            if (pocpage.OpenAdProd()) {

                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"SearchForAdAndClickOnIt\" >> src/main/resources/logcat.txt"});
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
//

                pocpage.Back1();

                pocpage.Back2();
            }
    }

    @Test(priority = 2)
    public void SearchForAdAndToggleToGenerateEvent() throws Exception {
        System.out.println("SearchForAdAndToggleToGenerateEvent");

        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
        if(pocpage.Search("power bank")) {
            pocpage.CheckAdProd();
            while (pocpage.isAdPresentInScreen()) {
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
                WebElement adelement = pocpage.getAdElement();
                pocpage.SwipeElementToScreenBottom(adelement);
                pocpage.Toggle();
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"SearchForAdAndToggleToGenerateEvent\" >> src/main/resources/logcat.txt"});
                Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
//
            }

        }
        pocpage.Back2();
    }

    @Test(priority = 3)
    public void SwipeOnADToProductEvent() throws Exception {
        System.out.println("SwipeOnADToProductEvent");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("power bank"))
           {
                if (pocpage.OpenAdProd()) {
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

                    pocpage.SwipeLeft(80,pocpage.getScreenHeight()/2);
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"SwipeOnADToProductEvent\" >> src/main/resources/logcat.txt"});
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});
//

                    pocpage.Back1();
                    pocpage.Back2();

                }

           }

    }

    @Test(priority = 4)
    public void OpenDrawerViewToSeeAd() throws Exception {
        System.out.println("OpenDrawerViewToSeeAd");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("shoes"))
             {
                if (pocpage.OpenAdProd()) {

                    pocpage.openDrawerView();
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});
                    pocpage.Click(30,3*pocpage.getScreenWidth()/4);
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"OpenDrawerViewToSeeAd\" >> src/main/resources/logcat.txt"});
                    Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});

                    pocpage.Back1();
                    pocpage.Back2();
                }


            }

    }


    @Test(priority = 5)
    public void PressBackButtonOnAds() throws Exception {
        System.out.println("PressBackButtonOnAds");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("watches"))
             {
                if (pocpage.CheckAdProd()) {


                }

            }
        pocpage.Back2();

        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"PressBackButtonOnAds\" >> src/main/resources/logcat.txt"});
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});



        if(pocpage.Search("shoes"))
        {
            if (pocpage.OpenAdProd()) {

            }

        }
        pocpage.Back1();
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"PressBackButtonOnAds\" >> src/main/resources/logcat.txt"});
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});


        pocpage.Back2();
    }

    @Test(priority = 6)
    public void CheckForEventsWhenNoAdsAppear() throws Exception {
        System.out.println("CheckForEventsWhenNoAdsAppear");
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -c"});

        if(pocpage.Search("samsung s3 neo"))
        {
            if (pocpage.CheckAdProd()) {

            }

        }


        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "echo \"CheckForEventsWhenNoAdsAppear\" >> src/main/resources/logcat.txt"});
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "adb logcat -t 5000 -s AD_SDK_LOG >> src/main/resources/logcat.txt"});

        pocpage.Back2();
    }

//    @Test
//    public void AdsBlockingByFilter() throws Exception {
//
//
//    }
//
//
//    @Test
//    public void AdsBlockingBySort() throws Exception {
//
//
//    }
//
//
//
//    @Test
//    public void pressHomeButtonOnAd() throws Exception {
//
//
//    }
//
//
//
//    @Test
//    public void EventsGettingTriggeredEvenBeforeCrossingMinimumThreshold() throws Exception {
//
//
//    }
//
//
//    @Test
//    public void BringingAppBackFromStackAndCreatingAdEvents() throws Exception {
//
//
//    }
//
//
//    @Test
//    public void openDrawerOnOrganicProductToSeeAdsANdCloseDrawerToSeeEvent() throws Exception {
//
//
//    }
//
//    @Test
//    public void CheckForMultipleAdviewEvents() throws Exception {
//
//
//    }


}

package com.flipkart.android;

import com.flipkart.Drivers.BaseConfig;
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



    @Test
    public void SearchForAd() throws Exception {
        pocpage.SwipeCross(960, 1430, 866, 1480);
        if(pocpage.Search("shoes"))
        for(int i=0;i<3;i++) {
            if (pocpage.CheckAdProd()) {
//                pocpage.RemoveAdFromScreen();
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
            pocpage.Toggle();
        }

    }
}

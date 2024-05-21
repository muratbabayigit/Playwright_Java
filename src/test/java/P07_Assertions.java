import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class P07_Assertions {

    public static void main(String[] args) {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://www.ebay.com/");

        // page assertions

        // hasUrl
        assertThat(page).hasURL("https://www.ebay.com/");

        // hasTitle
        assertThat(page).hasTitle("Electronics, Cars, Fashion, Collectibles & More | eBay");

        // not ()
        assertThat(page).not().hasTitle("test");

        page.close();
        browser.close();
        playwright.close();


    }

}
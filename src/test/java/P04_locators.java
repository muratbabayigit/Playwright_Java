import com.microsoft.playwright.*;

import java.awt.*;

public class P04_locators {

    public static void main(String[] args) throws InterruptedException {


        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width,height);


        page.navigate("https://getir.com/");
        System.out.println("title " + page.title());

        // css matching by text
        // 1. has text
        Locator loginText = page.locator("h5:has-text('Giriş yap veya kayıt ol')");
        System.out.println("1. login text " + loginText.innerText());

        // 2. text
        Locator loginText2 = page.locator("h5:text('Giriş yap veya kayıt ol')");
        System.out.println("2. login text " + loginText2.innerText());


        // CSS: elements matching one of the conditions
        Locator continuButton = page.locator("button:has-text('Telefon numarası ile devam et'),button:has-text('login button')");
        System.out.println("3. continue button " + continuButton.innerText());


        // CSS: pick n-th match from the query result
        Locator loginButton = page.locator(":nth-match(:text('Giriş yap'), 1)");
        System.out.println("4. login button " + loginButton.innerText());
        loginButton.click();
        Thread.sleep(3000);


        //id, data-testid, data-test-id, data-test selectors
        Locator loginPhoneNumber = page.locator("data-testid=modal").locator("data-testid=input");
        System.out.println("5. login phone number " + loginPhoneNumber.innerText());


        //XPath locator
        Locator loginContinueButton = page.locator("(//button[@type='submit'])[2]");
        System.out.println("6. loginContinueButton " + loginContinueButton.innerText());


        page.close();
        browser.close();
        playwright.close();

    }
}
import com.microsoft.playwright.*;

import java.awt.*;

import static com.microsoft.playwright.options.AriaRole.BUTTON;
import static com.microsoft.playwright.options.AriaRole.TEXTBOX;

public class P03_builtinLocators {

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


        // getByText
        Locator logintText = page.getByText("Giriş yap veya kayıt ol");
        System.out.println("1. text " + logintText.innerText());

        // Locate in Shadow DOM
        Locator shadowDom = page.locator("div", new Page.LocatorOptions().setHasText("Giriş yap veya kayıt ol")).last();
        System.out.println("shadowDom " + shadowDom.innerText());


        // getByRole
        Locator phoneNumber = page.getByRole(TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("2. phone number " + phoneNumber.innerText());
        phoneNumber.click();
        phoneNumber.fill("590-345");

        Thread.sleep(2000);

        // getByPlaceholder
        Locator phoneNumber2  = page.getByPlaceholder("Telefon Numarası");
        System.out.println("3. phone number " + phoneNumber2.innerText());


        // getByLabel
        Locator phoneContinueButton = page.getByLabel("login button");
        System.out.println("4.phoneContinueButton " + phoneContinueButton.innerText());


        // click login button
        // getByRole
        Locator loginButton = page.getByRole(BUTTON,new Page.GetByRoleOptions().setName("Giriş yap"));
        loginButton.click();

        // test id
        Locator loginPhoneNumber = page.getByTestId("modal").getByPlaceholder("Telefon Numarası");
        System.out.println("5. login phone number " + loginPhoneNumber.innerText());
        loginPhoneNumber.click();
        loginPhoneNumber.fill("6723");

        Locator cancelButton = page.getByTestId("modal").getByTestId("button").first();
        cancelButton.click();

        // getByAltText
        Locator beverage = page.getByAltText("Su & İçecek");


        // getByTest and filter options
        Locator beverage2 = page.getByTestId("text").filter(new Locator.FilterOptions().setHasText("Su & İçecek"));
        System.out.println("6. beverage " + beverage2.innerText());

        // css and filter options
        Locator beverage3 = page.locator("div").filter(new Locator.FilterOptions().setHasText("Su & İçecek"));
        Locator beverage4 = page.locator(".sc-b6b4847f-7 > .sc-6df7862-1").filter(new Locator.FilterOptions().setHasText("Su & İçecek"));

        beverage4.click();

        page.close();
        browser.close();
        playwright.close();

    }
}

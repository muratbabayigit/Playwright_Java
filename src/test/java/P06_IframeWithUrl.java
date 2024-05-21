import com.microsoft.playwright.Frame;
import com.microsoft.playwright.*;

import java.awt.*;
import java.util.List;

public class P06_IframeWithUrl {
    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);

        page.navigate("https://the-internet.herokuapp.com/iframe");

        Locator title = page.locator("//h3");
        System.out.println("title " + title.innerText());

        List<Frame> frames = page.frames();
        System.out.println("size " + frames.size());

        for (Frame frame : frames) {
            System.out.println("url " + frame.url());

        }

        // Frames by url
        Frame frame = page.frameByUrl("about:blank");

        Locator body = frame.getByText("Your content goes here.");
        body.click();
        body.clear();

        Locator  inputText = frame.getByLabel("Rich Text Area. Press ALT-0 for help.");
        inputText.fill("hello wise");

        Thread.sleep(2000);

        page.close();
        browser.close();
        playwright.close();

    }

}
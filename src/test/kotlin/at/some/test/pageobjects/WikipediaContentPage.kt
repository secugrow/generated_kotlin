package at.some.test.pageobjects;

import at.some.test.webdriversession.WebDriverSession;
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

class WikipediaContentPage(session: WebDriverSession) : MainPage(session) {
    val header: String
        get() = getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading"))).getText()
}
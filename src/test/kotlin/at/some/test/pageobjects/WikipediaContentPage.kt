package at.some.test.pageobjects;

import at.some.test.driverutil.WebDriverSession;
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

class WikipediaContentPage(session: WebDriverSession) : MainPage(session) {
    fun getHeader(): String = wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading"))).getText()
}
package at.some.test.pageobjects

import at.some.test.driverutil.WebDriverSession
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait


open class AbstractPage(val session: WebDriverSession) {

    protected val webDriver: WebDriver
        get() = session.webDriver

    protected val wdwait: WebDriverWait
        get() = session.wdwait


    init {
        session.activate(page = this)
    }


}

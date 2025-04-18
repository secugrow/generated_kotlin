package at.some.test.driverutil

import logger
import org.openqa.selenium.WebDriver
import java.time.Duration
import java.time.temporal.ChronoUnit

object DriverFactory {

    private const val TIMEOUT : Long = 10
    private val log by logger()

    fun createWebDriver(scenarioId: String): WebDriver {

        val webDriver: WebDriver
        val browserName = System.getProperty("browser", DriverType.CHROME.toString()).uppercase()
        val driverType = DriverType.valueOf(browserName)


        when (driverType) {
            DriverType.CHROME -> { //checked
                webDriver = ChromeWebDriverFactory().createDriver()
            }
            DriverType.CHROMIUM -> {
                webDriver = ChromiumWebDriverFactory().createDriver()
            }
            DriverType.FIREFOX -> { //checked
                webDriver = FirefoxWebDriverFactory().createDriver()
            }
            DriverType.EDGE -> { //checked
                webDriver = EdgeWebDriverFactory().createDriver()
            }
            DriverType.IE -> {
                webDriver = IEWebDriverFactory().createDriver()
            }

            DriverType.LOCAL_CHROME_MOBILE_EMULATION -> { //checked
                webDriver = ChromeMobileEmulationWebDriverFactory().createDriver()
            }
            /* REMOTE Implementations */

            DriverType.REMOTE_CHROME_MOBILE_EMULATION -> { //checked
                webDriver = RemoteChromeMobileEmulationWebDriverFactory().createDriver()
            }
            DriverType.REMOTE_CHROME -> { //checked
                webDriver = RemoteChromeWebDriverFactory().createDriver()
            }
            DriverType.REMOTE_FIREFOX -> { //checked
                webDriver = RemoteFirefoxWebDriverFactory().createDriver()
            }
            DriverType.REMOTE_CHROME_MOBILE -> {
                webDriver = RemoteChromeMobileWebDriverFactory().createDriver()
            }
            DriverType.REMOTE_ANDROID -> {
                webDriver = RemoteAndroidWebDriverFactory().createDriver()
            }
            DriverType.APPIUM_ANDROID_DEVICE -> {
                webDriver = AppiumAndroidWebDriverFactory().createDriver()
            }
        }

        webDriver.manage().timeouts().implicitlyWait(Duration.of(TIMEOUT, ChronoUnit.SECONDS))
        return webDriver
    }

}

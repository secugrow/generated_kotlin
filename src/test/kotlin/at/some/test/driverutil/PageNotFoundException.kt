package at.some.test.driverutil

import logger

class PageNotFoundException(s: String) : Throwable() {

    private val log by logger()

    init {
        log.error(s)
    }


}

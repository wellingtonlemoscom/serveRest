package apis

import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification

open class Base {

    private val URL_BASE = "https://serverest.dev"

    fun specBase() : RequestSpecification {
        return RequestSpecBuilder()
            .setBaseUri(URL_BASE)
            .build()
    }
}
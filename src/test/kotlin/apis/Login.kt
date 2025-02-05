package apis

import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.hamcrest.CoreMatchers.equalTo
import pojos.LoginPojo

class Login : Base() {

    private val PATH_LOGIN = "/login"

    fun loginUsuarios(loginPojo: LoginPojo): Response {

        return Given {
            spec(specBase())
            contentType(ContentType.JSON)
            body(loginPojo)
        } When {
            post(PATH_LOGIN)
        } Then {
            log().all()
        } Extract {
            response()
        }
    }
}
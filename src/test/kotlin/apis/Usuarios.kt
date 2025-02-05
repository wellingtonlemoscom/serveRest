package apis

import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import pojos.UsuariosPojo

class Usuarios: Base() {


    private val PATH_USUARIOS = "/usuarios"

    fun postUsuarios(usuariosPojo: UsuariosPojo): Response {

        return Given {
            spec(specBase())
            contentType(ContentType.JSON)
            body(usuariosPojo)
        } When {
            post(PATH_USUARIOS)
        } Then {
            log().all()
        } Extract {
            response()
        }

    }

    fun getUsuarios(id:String): Response {

        return Given {
            spec(specBase())
            contentType(ContentType.JSON)
            pathParams("_id", id)
        } When {
            get("$PATH_USUARIOS/{_id}")
        } Then {
            log().all()
        } Extract {
            response()
        }
    }

    fun deleteUsuario(id: String): Response {
        return Given {
            spec(specBase())
            contentType(ContentType.JSON)
            pathParams("_id", id)
        } When {
            delete("$PATH_USUARIOS/{_id}")
        } Then {
            log().all()
        } Extract {
            response()
        }
    }
}
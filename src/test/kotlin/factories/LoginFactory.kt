package factories

import pojos.LoginPojo
import pojos.UsuariosPojo

class LoginFactory {

    private val loginPojo = LoginPojo();

    fun bodySuccess (usuariosPojo: UsuariosPojo) : LoginPojo {

        loginPojo.email = usuariosPojo.email;
        loginPojo.password = "teste";

        return loginPojo;
    }
}
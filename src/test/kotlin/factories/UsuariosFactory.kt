package factories

import commands.Commands
import pojos.UsuariosPojo

class UsuariosFactory {

    private val usuariosPojo = UsuariosPojo();
    private val commands = Commands();

    fun bodySuccess(): UsuariosPojo {

        val emailRandom = commands.getRandomString(5)

        usuariosPojo.nome = "Gerson"
        usuariosPojo.email = "$emailRandom@teste.com"
        usuariosPojo.password = "teste"
        usuariosPojo.administrador = "true"

        return usuariosPojo;
    }

    fun bodyDuplicated() : UsuariosPojo {

        val bodysuccess = bodySuccess()

        bodysuccess.email = "testeTeste@teste.com"
        return bodysuccess
    }

}
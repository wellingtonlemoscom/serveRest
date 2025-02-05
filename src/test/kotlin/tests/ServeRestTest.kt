package tests

import apis.Login
import apis.Usuarios
import factories.LoginFactory
import factories.UsuariosFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("regression")
class ServerRestTest {

    private val usuarios = Usuarios();
    private val usuariosFactory = UsuariosFactory();

    private val login = Login();
    private val loginFactory = LoginFactory();

    @DisplayName("Realizar cadastro de usuario com sucesso")
    @Test
    @Tag("smoke")
    fun cadastraUsuarioSucesso() {

        val pojo = usuariosFactory.bodySuccess();
        val response = usuarios.postUsuarios(pojo);

        assertAll("Valida cadastro de Usuario",
            { assertNotNull(response) },
            { assertEquals(201, response.statusCode) },
            { assertEquals("Cadastro realizado com sucesso", response.jsonPath().getString("message")) }
        );
    }

    @DisplayName("Realizar cadastro de usuario com email já existente")
    @Test
    fun cadastraUsuarioEmailExistente() {

        val pojo = usuariosFactory.bodyDuplicated();
        usuarios.postUsuarios(pojo);
        val response = usuarios.postUsuarios(pojo);

        assertAll("Valida erro ao cadastrar usuario com email ja existente",
            { assertNotNull(response) },
            { assertEquals(400, response.statusCode) },
            { assertEquals("Este email já está sendo usado", response.jsonPath().getString("message")) }
        );
    }

    @DisplayName("Realizar login de usuario com sucesso")
    @Test
    @Tag("smoke")
    fun postLoginUsuario() {

        val usuarioPojo = usuariosFactory.bodySuccess();
        usuarios.postUsuarios(usuarioPojo);
        val loginPojo = loginFactory.bodySuccess(usuarioPojo);
        val response = login.loginUsuarios(loginPojo);


        assertAll("Validar login realizado com sucesso",
            { assertNotNull(response) },
            { assertEquals(200, response.statusCode) },
            { assertEquals("Login realizado com sucesso", response.jsonPath().getString("message")) }
        );
    }

    @DisplayName("Realizar busca de usuario com sucesso")
    @Test
    @Tag("smoke")
    fun getUsuario() {

        val pojo = usuariosFactory.bodySuccess();
        val id = usuarios.postUsuarios(pojo).jsonPath().getString("_id");

        val response = usuarios.getUsuarios(id);

        assertAll("Realizar busca de usuario com sucesso",
            { assertNotNull(response) },
            { assertEquals(200, response.statusCode) },
            { assertEquals(id, response.jsonPath().getString("_id")) }
        );
   }

    @DisplayName("Realizar a exclusão de usuario com sucesso")
    @Test
    @Tag("smoke")
    fun deletetUsuario() {

        val pojo = usuariosFactory.bodySuccess();
        val id = usuarios.postUsuarios(pojo).jsonPath().getString("_id");

        val response = usuarios.deleteUsuario(id);

        assertAll("Validar exclusão de usuario com sucesso",
            { assertNotNull(response) },
            { assertEquals(200, response.statusCode) },
            { assertEquals("Registro excluído com sucesso", response.jsonPath().getString("message")) }
        );
    }
}
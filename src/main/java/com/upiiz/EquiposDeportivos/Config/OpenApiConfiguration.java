package com.upiiz.EquiposDeportivos.Config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API de Equipos deportivos", description = "Esta API proporciona acceso a los recursos de la API de la API de la tienda moderna", version = "1.0.0", contact = @Contact(name = "Miguel Alejandro Rodríguez Cruz", email = "miguel.ale.rodri.cruz@gmail.com", url = "http://localhost:8080/contacto"), license = @License(), termsOfService = "Solo se permiten 400 solicitudes diarias"), servers = {
        @Server(description = "Servidor de pruebas", url = "http://pruebas.com.8081/api/v1"),
        @Server(description = "Servidor de produccion", url = "http://localhost:8081/api/v1/")
}, tags = {
        @Tag(name = "Equipos Deportivos", description = "Endpoint para los recursos de Eqipos Deportivos")
})
public class OpenApiConfiguration {

}

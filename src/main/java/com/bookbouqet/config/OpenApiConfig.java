package com.bookbouqet.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Nihat K.",
                        email = "contact@odunckitap.com",
                        url = "http://www.odunckitap.com"
                ),
                description = "OpenApi documentation for site",
                title = "OpenApi specification - odunckitap.com",
                license = @License(
                        name = "OduncKitap OpenApi Licence",
                        url = "http://www.odunckitap.com/api/licence"
                ),
                termsOfService = "Terms of service for OduncKitap"
        ),
        servers = {@Server(
                description = "DEV Env",
                url = "http://localhost:8088/api/v1"
        ), @Server(
                description = "PROD Env",
                url = "http://www.odunckitap.com/api/v1"
        )
        }
        , security = {@SecurityRequirement(
        name = "bearerAuth"
)
}
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Auth token security",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}

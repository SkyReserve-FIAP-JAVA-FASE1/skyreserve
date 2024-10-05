package org.skyreserve.infra.config.configStartLocal;


import org.skyreserve.app.service.TestLocal.DBServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestStart {

    @Autowired
    private DBServiceLocal dbServiceLocal;

    @Bean
    public void instanciaDB() {
        this.dbServiceLocal.instanciaDB();
    }
}

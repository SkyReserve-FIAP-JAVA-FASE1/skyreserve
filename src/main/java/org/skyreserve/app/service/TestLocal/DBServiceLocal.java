package org.skyreserve.app.service.TestLocal;

import org.skyreserve.app.service.postgres.AssentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBServiceLocal {

    @Autowired
    private AssentoService assentoService;

    public void instanciaDB() {
        System.out.println("Iniciando a aplicação com valores iniciais.");


    }
}

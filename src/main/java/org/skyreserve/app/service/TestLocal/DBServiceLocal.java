package org.skyreserve.app.service.TestLocal;

import org.skyreserve.app.service.postgres.AssentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DBServiceLocal {

    @Autowired
    private AssentoService assentoService;

    @Value("${local}")
    boolean local;

    public void instanciaDB() {
        if(local){
            System.out.println("Iniciando a aplicação com valores iniciais.");



        }
    }
}

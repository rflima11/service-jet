package br.com.businesstec.servicejet.utils;

import br.com.businesstec.servicejet.service.EntidadeStrategy;
import org.slf4j.Logger;

public class LogUtils {

    private LogUtils() { throw new  IllegalArgumentException("Classe utilitária não deve ser instanciada"); }

    public static void defaultInfoLog(Logger logger, String message) {
        logger.info("==============================");
        logger.info(message);
        logger.info("==============================");
    }


}

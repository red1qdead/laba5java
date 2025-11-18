
package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lab10LoggingDemo {

    private static final Logger logger = LogManager.getLogger(Lab10LoggingDemo.class);

    public static void main(String[] args) {

        logger.info("Старт програми");

        logger.debug("Це DEBUG-повідомлення");

        processData();

        try {
            int x = divide(10, 0);
            logger.info("Результат ділення: " + x);
        } catch (Exception e) {
            logger.error("Помилка при виконанні ділення: " + e.getMessage());
        }

        logger.warn("Завершення програми з попередженням.");

        logger.info("Кінець роботи");
    }

    private static void processData() {
        logger.info("Починаю обробку даних");

        for (int i = 1; i <= 3; i++) {
            logger.debug("Ітерація циклу: " + i); 
        }

        logger.info("Обробка завершена.");
    }

    private static int divide(int a, int b) {
        logger.info("Виклик divide(" + a + ", " + b + ")");
        return a / b; 
    }
}

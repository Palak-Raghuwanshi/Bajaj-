package com.example.assessment.finserv;

import com.example.assessment.finserv.dto.WebhookResponse;
import com.example.assessment.finserv.dto.SolutionRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupOperation implements CommandLineRunner {

    private final WebhookService webhookService;

    public StartupOperation(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override
    public void run(String... args) throws Exception {

        WebhookResponse response = webhookService.generateWebhook();

        String finalQuery =
                "SELECT p.AMOUNT AS SALARY, " +
                        "CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, " +
                        "TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE, " +
                        "d.DEPARTMENT_NAME " +
                        "FROM PAYMENTS p " +
                        "JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID " +
                        "JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID " +
                        "WHERE DAY(p.PAYMENT_TIME) <> 1 " +
                        "ORDER BY p.AMOUNT DESC " +
                        "LIMIT 1;";



        webhookService.submitSolution(
                response.getWebhookUrl(),
                response.getAccessToken(),
                new SolutionRequest(finalQuery)
        );
    }
}

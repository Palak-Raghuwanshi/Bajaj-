package com.example.assessment.finserv;



import com.example.assessment.finserv.dto.WebhookResponse;
import com.example.assessment.finserv.dto.SolutionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
        import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public WebhookResponse generateWebhook() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        String requestBody = """
                {
                  "name" : "Palak Raghuwanshi",
                  "regNo": "0002CD221047" ,
                  "email": "raghuwanshipalak157@gmail.com"
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<WebhookResponse> response =
                restTemplate.exchange(url, HttpMethod.POST, entity, WebhookResponse.class);

        return response.getBody();
    }

    public void submitSolution(String webhookUrl, String accessToken, SolutionRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);

        HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(webhookUrl, HttpMethod.POST, entity, String.class);

        System.out.println("Response from webhook: " + response.getBody());
    }
}

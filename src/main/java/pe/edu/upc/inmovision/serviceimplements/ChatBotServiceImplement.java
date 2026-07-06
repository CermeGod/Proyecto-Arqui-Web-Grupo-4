package pe.edu.upc.inmovision.serviceimplements;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.edu.upc.inmovision.serviceinterfaces.IChatBotService;

import java.util.List;
import java.util.Map;

@Service
public class ChatBotServiceImplement implements IChatBotService {

    @Value("${groq.api.url}")
    private String groqApiUrl;

    @Value("${groq.api.key}")
    private String groqApiKey;

    @Value("${groq.model}")
    private String groqModel;

    private final WebClient webClient;

    private static final String OUT_OF_SCOPE_RESPONSE =
            "Lo siento, únicamente puedo ayudarte con temas relacionados con InmoVision y el sector inmobiliario.";

    public ChatBotServiceImplement() {
        this.webClient = WebClient.builder().build();
    }

    @Override
    public String generateResponse(String userMessage) {

        if (!isInmoVisionRelated(userMessage)) {
            return OUT_OF_SCOPE_RESPONSE;
        }

        Map<String, Object> requestBody = Map.of(
                "model", groqModel,
                "messages", List.of(
                        Map.of(
                                "role", "system",
                                "content",
                                "Eres Chatcito.\n" +
                                        "\n" +
                                        "Solo ayudas con temas relacionados con el sector inmobiliario y el uso de la plataforma InmoVision.\n" +
                                        "\n" +
                                        "Todas las respuestas deben tener máximo 40 palabras.\n" +
                                        "\n" +
                                        "Si la pregunta no está relacionada con bienes raíces, propiedades, búsqueda de inmuebles, recorridos virtuales, InmoVision o el sector inmobiliario, responde exactamente:\n" +
                                        "\n" +
                                        "\"Lo siento, únicamente puedo ayudarte con temas relacionados con InmoVision y el sector inmobiliario.\"\n" +
                                        "\n" +
                                        "No agregues información adicional."
                        ),
                        Map.of(
                                "role", "user",
                                "content", userMessage
                        )
                ),
                "temperature", 0.5
        );


        Map response = webClient.post()
                .uri(groqApiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + groqApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response == null || response.get("choices") == null) {
            return "No pude generar una respuesta en este momento.";
        }

        List choices = (List) response.get("choices");

        if (choices.isEmpty()) {
            return "No pude generar una respuesta en este momento.";
        }

        Map firstChoice = (Map) choices.get(0);
        Map message = (Map) firstChoice.get("message");

        if (message == null || message.get("content") == null) {
            return "No pude generar una respuesta en este momento.";
        }

        return message.get("content").toString();
    }

    private boolean isInmoVisionRelated(String message) {
        if (message == null || message.trim().isEmpty()) {
            return false;
        }

        String text = message.toLowerCase();

        String[] allowedKeywords = {
                "inmovision",
                "inmobiliaria",
                "inmueble",
                "inmuebles",
                "propiedad",
                "propiedades",
                "casa",
                "casa",
                "departamento",
                "departamentos",
                "edificio",
                "condominio",
                "terreno",
                "venta",
                "vender",
                "comprar",
                "compra",
                "alquiler",
                "alquilar",
                "renta",
                "local",
                "oficina",
                "agente",
                "corredor",
                "cliente",
                "visita",
                "tour",
                "recorrido",
                "virtual",
                "precio",
                "hipoteca"
        };

        for (String keyword : allowedKeywords) {
            if (text.contains(keyword)) {
                return true;
            }
        }

        return false;
    }
}
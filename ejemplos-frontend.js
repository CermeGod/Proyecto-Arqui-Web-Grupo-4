// ejemplos-frontend.js
// Ejemplos de cómo consumir el API del Chatbot desde tu aplicación Frontend

const API_URL = "http://localhost:8080";

// ===== Ejemplo 1: Fetch API (JavaScript Vanilla) =====
async function consultarChatbot(mensaje, contexto = "") {
    try {
        const response = await fetch(`${API_URL}/api/v1/chatbot/consultar`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                mensaje: mensaje,
                contexto: contexto
            })
        });

        const data = await response.json();
        
        if (data.exito) {
            console.log("✓ Respuesta:", data.respuesta);
            console.log("⏱ Timestamp:", data.timestamp);
        } else {
            console.error("✗ Error:", data.mensaje);
        }
        
        return data;
    } catch (error) {
        console.error("Error de conexión:", error);
    }
}

// ===== Ejemplo 2: Axios (si lo tienes instalado) =====
// npm install axios

async function consultarChatbotConAxios(mensaje, contexto = "") {
    try {
        const response = await axios.post(`${API_URL}/api/v1/chatbot/consultar`, {
            mensaje: mensaje,
            contexto: contexto
        }, {
            headers: {
                "Content-Type": "application/json"
            }
        });

        console.log(response.data);
        return response.data;
    } catch (error) {
        console.error("Error:", error.message);
    }
}

// ===== Ejemplo 3: React Hook (Custom Hook) =====
import { useState } from 'react';

function useChatbot() {
    const [respuesta, setRespuesta] = useState("");
    const [cargando, setCargando] = useState(false);
    const [error, setError] = useState("");

    const consultarChatbot = async (mensaje, contexto = "") => {
        setCargando(true);
        setError("");
        
        try {
            const response = await fetch(`${API_URL}/api/v1/chatbot/consultar`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ mensaje, contexto })
            });

            const data = await response.json();
            
            if (data.exito) {
                setRespuesta(data.respuesta);
            } else {
                setError(data.mensaje);
            }
            
            return data;
        } catch (err) {
            setError("Error de conexión al servidor");
            console.error(err);
        } finally {
            setCargando(false);
        }
    };

    return { respuesta, cargando, error, consultarChatbot };
}

// ===== Ejemplo 4: Componente React =====
import React, { useState } from 'react';

function ChatbotComponent() {
    const [mensaje, setMensaje] = useState("");
    const [contexto, setContexto] = useState("");
    const { respuesta, cargando, error, consultarChatbot } = useChatbot();

    const handleSubmit = async (e) => {
        e.preventDefault();
        await consultarChatbot(mensaje, contexto);
    };

    return (
        <div className="chatbot-container">
            <h2>🤖 Chatbot Inmovision</h2>
            
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Tu Pregunta:</label>
                    <textarea
                        value={mensaje}
                        onChange={(e) => setMensaje(e.target.value)}
                        placeholder="Ej: ¿Qué propiedades tienes en Lima?"
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Contexto (opcional):</label>
                    <textarea
                        value={contexto}
                        onChange={(e) => setContexto(e.target.value)}
                        placeholder="Ej: El usuario busca apartamentos de 2 habitaciones"
                    />
                </div>

                <button type="submit" disabled={cargando}>
                    {cargando ? "Cargando..." : "Enviar Pregunta"}
                </button>
            </form>

            {error && <div className="error">{error}</div>}
            {respuesta && <div className="respuesta">{respuesta}</div>}
        </div>
    );
}

export default ChatbotComponent;

// ===== Ejemplo 5: Vue 3 Composition API =====
import { ref, reactive } from 'vue';

export function useChatbot() {
    const respuesta = ref("");
    const cargando = ref(false);
    const error = ref("");

    const consultarChatbot = async (mensaje, contexto = "") => {
        cargando.value = true;
        error.value = "";
        
        try {
            const response = await fetch(`${API_URL}/api/v1/chatbot/consultar`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ mensaje, contexto })
            });

            const data = await response.json();
            
            if (data.exito) {
                respuesta.value = data.respuesta;
            } else {
                error.value = data.mensaje;
            }
        } catch (err) {
            error.value = "Error de conexión al servidor";
        } finally {
            cargando.value = false;
        }
    };

    return { respuesta, cargando, error, consultarChatbot };
}

// ===== Ejemplo 6: Angular Service =====
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatbotService {
    private API_URL = 'http://localhost:8080/api/v1/chatbot';

    constructor(private http: HttpClient) { }

    consultarChatbot(mensaje: string, contexto: string = ""): Observable<any> {
        return this.http.post(`${this.API_URL}/consultar`, {
            mensaje,
            contexto
        });
    }

    verificarEstado(): Observable<any> {
        return this.http.get(`${this.API_URL}/ping`);
    }
}

// ===== Ejemplo de Uso en Componente Angular =====
import { Component } from '@angular/core';
import { ChatbotService } from './services/chatbot.service';

@Component({
  selector: 'app-chatbot',
  template: `
    <div class="chatbot">
      <h2>🤖 Chatbot Inmovision</h2>
      <textarea [(ngModel)]="mensaje" placeholder="Tu pregunta..."></textarea>
      <button (click)="consultar()" [disabled]="cargando">
        {{ cargando ? 'Cargando...' : 'Enviar' }}
      </button>
      <div *ngIf="respuesta" class="respuesta">{{ respuesta }}</div>
    </div>
  `
})
export class ChatbotComponent {
    mensaje = "";
    respuesta = "";
    cargando = false;

    constructor(private chatbotService: ChatbotService) { }

    consultar() {
        this.cargando = true;
        this.chatbotService.consultarChatbot(this.mensaje).subscribe(
            (data) => {
                this.respuesta = data.respuesta;
                this.cargando = false;
            },
            (error) => {
                console.error(error);
                this.cargando = false;
            }
        );
    }
}

// ===== Uso Básico =====
// Simplemente ejecuta en la consola del navegador:
/*
consultarChatbot(
    "¿Qué propiedades tienes disponibles?",
    "El usuario busca apartamentos"
);
*/

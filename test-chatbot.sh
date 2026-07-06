#!/bin/bash
# Script de prueba para el API del Chatbot de Inmovision
# Ejecuta: bash test-chatbot.sh

# Colores para output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

API_URL="http://localhost:8080"

echo -e "${BLUE}=== Test de Chatbot Inmovision ===${NC}\n"

# Test 1: Verificar que el chatbot está activo
echo -e "${GREEN}[TEST 1] Ping al chatbot...${NC}"
curl -X GET "$API_URL/api/v1/chatbot/ping" \
  -H "Content-Type: application/json" \
  -w "\n\n"

# Test 2: Consulta simple
echo -e "${GREEN}[TEST 2] Consulta simple...${NC}"
curl -X POST "$API_URL/api/v1/chatbot/consultar" \
  -H "Content-Type: application/json" \
  -d '{
    "mensaje": "¿Qué propiedades tienes disponibles en Lima?",
    "contexto": "El usuario es un cliente nuevo buscando apartamentos"
  }' \
  -w "\n\n"

# Test 3: Consulta con contexto
echo -e "${GREEN}[TEST 3] Consulta con contexto personalizado...${NC}"
curl -X POST "$API_URL/api/v1/chatbot/consultar" \
  -H "Content-Type: application/json" \
  -d '{
    "mensaje": "¿Cuál es el precio promedio?",
    "contexto": "Estamos enfocados en propiedades de lujo en San Isidro"
  }' \
  -w "\n\n"

# Test 4: Consulta sin contexto (campo opcional)
echo -e "${GREEN}[TEST 4] Consulta sin contexto...${NC}"
curl -X POST "$API_URL/api/v1/chatbot/consultar" \
  -H "Content-Type: application/json" \
  -d '{
    "mensaje": "Hola, ¿cómo funcionan los filtros de búsqueda?"
  }' \
  -w "\n\n"

# Test 5: Error - mensaje vacío
echo -e "${RED}[TEST 5] Error esperado - mensaje vacío...${NC}"
curl -X POST "$API_URL/api/v1/chatbot/consultar" \
  -H "Content-Type: application/json" \
  -d '{
    "mensaje": ""
  }' \
  -w "\n\n"

echo -e "${BLUE}=== Tests completados ===${NC}"

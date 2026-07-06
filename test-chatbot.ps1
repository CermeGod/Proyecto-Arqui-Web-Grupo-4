# Script de prueba para el API del Chatbot de Inmovision (Windows PowerShell)
# Ejecuta: .\test-chatbot.ps1

$API_URL = "http://localhost:8080"

Write-Host "=== Test de Chatbot Inmovision ===" -ForegroundColor Blue
Write-Host ""

# Test 1: Verificar que el chatbot está activo
Write-Host "[TEST 1] Ping al chatbot..." -ForegroundColor Green
$response1 = Invoke-WebRequest -Uri "$API_URL/api/v1/chatbot/ping" `
    -Method GET `
    -ContentType "application/json"
Write-Host $response1.Content
Write-Host ""

# Test 2: Consulta simple
Write-Host "[TEST 2] Consulta simple..." -ForegroundColor Green
$body2 = @{
    mensaje = "¿Qué propiedades tienes disponibles en Lima?"
    contexto = "El usuario es un cliente nuevo buscando apartamentos"
} | ConvertTo-Json

$response2 = Invoke-WebRequest -Uri "$API_URL/api/v1/chatbot/consultar" `
    -Method POST `
    -ContentType "application/json" `
    -Body $body2
Write-Host $response2.Content | ConvertFrom-Json | ConvertTo-Json -Depth 10
Write-Host ""

# Test 3: Consulta con contexto
Write-Host "[TEST 3] Consulta con contexto personalizado..." -ForegroundColor Green
$body3 = @{
    mensaje = "¿Cuál es el precio promedio?"
    contexto = "Estamos enfocados en propiedades de lujo en San Isidro"
} | ConvertTo-Json

$response3 = Invoke-WebRequest -Uri "$API_URL/api/v1/chatbot/consultar" `
    -Method POST `
    -ContentType "application/json" `
    -Body $body3
Write-Host $response3.Content | ConvertFrom-Json | ConvertTo-Json -Depth 10
Write-Host ""

# Test 4: Consulta sin contexto
Write-Host "[TEST 4] Consulta sin contexto..." -ForegroundColor Green
$body4 = @{
    mensaje = "Hola, ¿cómo funcionan los filtros de búsqueda?"
} | ConvertTo-Json

$response4 = Invoke-WebRequest -Uri "$API_URL/api/v1/chatbot/consultar" `
    -Method POST `
    -ContentType "application/json" `
    -Body $body4
Write-Host $response4.Content | ConvertFrom-Json | ConvertTo-Json -Depth 10
Write-Host ""

# Test 5: Error esperado - mensaje vacío
Write-Host "[TEST 5] Error esperado - mensaje vacío..." -ForegroundColor Red
$body5 = @{
    mensaje = ""
} | ConvertTo-Json

try {
    $response5 = Invoke-WebRequest -Uri "$API_URL/api/v1/chatbot/consultar" `
        -Method POST `
        -ContentType "application/json" `
        -Body $body5
    Write-Host $response5.Content | ConvertFrom-Json | ConvertTo-Json -Depth 10
} catch {
    Write-Host $_.Exception.Response.StatusCode
    Write-Host $_.Exception.Message
}
Write-Host ""

Write-Host "=== Tests completados ===" -ForegroundColor Blue

#!/bin/bash
# Script para limpiar configuraciones de IntelliJ y caché del proyecto

echo "=================================================="
echo "Limpiando configuraciones de IntelliJ y caché..."
echo "=================================================="

# Limpiar directorio .idea
if [ -d ".idea" ]; then
    echo "✓ Eliminando configuraciones de .idea..."
    rm -rf .idea/runConfigurations*
    rm -rf .idea/workspace.xml
    rm -rf .idea/tasks.xml
    rm -rf .idea/shelf
    echo "  Configuraciones de .idea eliminadas"
else
    echo "✗ No se encontró directorio .idea"
fi

# Limpiar build de Gradle
echo "✓ Limpiando build de Gradle..."
./gradlew clean --quiet
echo "  Build limpiado"

# Limpiar caché de Gradle
echo "✓ Limpiando caché local..."
rm -rf build/
rm -rf .gradle/
echo "  Caché limpiado"

echo ""
echo "=================================================="
echo "✅ LIMPIEZA COMPLETADA"
echo "=================================================="
echo ""
echo "PRÓXIMOS PASOS:"
echo "1. Cierra IntelliJ IDEA completamente"
echo "2. Vuelve a abrir el proyecto"
echo "3. Espera a que IntelliJ indexe el proyecto"
echo "4. Ve a: File > Invalidate Caches... > Invalidate and Restart"
echo "5. Después de reiniciar, ejecuta las nuevas pruebas"
echo ""
echo "PRUEBAS DISPONIBLES:"
echo "  - AutenticacionRunner.java"
echo "  - NotificacionesRunner.java"
echo ""


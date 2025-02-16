# 👻 GhostBusters Asturias - Proyecto en Java

## 📜 Descripción
Los habitantes de Asturias han comenzado a notar sucesos paranormales. Desde sombras gigantescas que inducen miedo hasta voces espectrales que manipulan la energía. Para combatir estas amenazas, se ha formado un nuevo equipo de **GhostBusters**.

Tu misión será capturar, analizar y, si es necesario, liberar fantasmas atrapados para mantener la seguridad de la región. Con trampas de protones avanzadas y un detector ectoplásmico de última generación, debes recorrer los lugares más emblemáticos de Asturias para documentar e investigar las entidades paranormales.

🌍 **Ubicación de la base:** Un almacén industrial en Avilés, acondicionado con trampas de almacenamiento ectoplásmico.

## 🚀 Funcionalidades Principales
- **Capturar fantasmas** y añadirlos a la base de datos.
- **Visualizar lista de fantasmas capturados**, con sus características.
- **Liberar fantasmas menos peligrosos** para hacer espacio en el contenedor ectoplásmico.
- **Filtrar fantasmas por clase** para facilitar la investigación.
- **Ver fantasmas capturados en un mes específico** para analizar patrones de actividad paranormal.
- **Salir del programa** para continuar en otra ocasión.

## 🕹️ Interacción con el Usuario (Ejemplo de Consola)
```
============================================
    ¡Bienvenido a la Base Ghostbusters Asturias!
    Gestiona tus fantasmas atrapados y protege la región
============================================
Opciones:
1. Capturar un nuevo fantasma
2. Ver lista de fantasmas capturados
3. Liberar un fantasma
4. Filtrar fantasmas por clase
5. Ver fantasmas capturados en un mes
6. Salir

Por favor, selecciona una opción (1-6):
> 1
Capturar un Nuevo Fantasma
============================================
Ingresa el nombre del fantasma:
> Espíritu del Pescador de Lastres

Selecciona la clase del fantasma:
1. Clase I - Manifestación menor
2. Clase II - Aparición móvil
3. Clase III - Entidad inteligente
4. Clase IV - Fantasma histórico
5. Clase V - Espíritu antropomorfo
6. Clase VI - Espíritu demoníaco
7. Clase VII - Entidad ultraterrena
> 4

Nivel de peligro (Bajo, Medio, Alto, Crítico):
> Bajo

Habilidad especial del fantasma:
> Aparecer durante tormentas en la costa

Fantasma "Espíritu del Pescador de Lastres" capturado exitosamente con nivel de afinidad ectoplásmica 7/10.
```

## 📌 Historias de Usuario
### 🏆 Capturar Fantasmas
**Como usuario, quiero** capturar un nuevo fantasma **para** expandir mi colección y proteger los lugares emblemáticos de Asturias.

### 🔍 Visualizar Fantasmas Atrapados
**Como usuario, quiero** ver todos los fantasmas capturados **para** analizar sus características y planear estrategias.

### 🚪 Liberar Fantasmas
**Como usuario, quiero** liberar fantasmas menos peligrosos o inofensivos **para** hacer espacio en mi contenedor ectoplásmico.

### 🎭 Filtrar Fantasmas por Clase
**Como usuario, quiero** filtrar los fantasmas por clase **para** priorizar cuáles estudiar o utilizar en mis investigaciones.

### 📅 Ver Fantasmas Capturados en un Mes Específico
**Como usuario, quiero** obtener un listado de los fantasmas atrapados en un mes **para** estudiar patrones de actividad paranormal en Asturias.

### 🔚 Salir del Programa
**Como usuario, quiero** salir del juego **para** guardar mi progreso y continuar en otra ocasión.

## 🛠️ Tecnologías y Metodología
- **Lenguaje:** Java
- **Arquitectura:** MVC
- **Testing:** TDD (Cobertura mínima del 70%)
- **Control de versiones:** Git & GitHub
- **Sprint:** 1 semana

## 📂 Estructura del Proyecto
```
📦 GhostBusters-Asturias
 ┣ 📂 src
 ┃ ┣ 📂 dev.lanny.controller
 ┃ ┃ ┗ 📜 HunterController.java
 ┃ ┣ 📂 dev.lanny.model
 ┃ ┃ ┣ 📜 GhostModel.java
 ┃ ┃ ┗ 📜 HunterModel.java
 ┃ ┣ 📂 dev.lanny.view
 ┃ ┃ ┣ 📜 MainView.java
 ┃ ┃ ┣ 📜 CaptureGhostView.java
 ┃ ┃ ┗ 📜 DeleteGhostView.java ┃ 
 ┃ ┣ 📂 tests
 ┃ ┣ 📜 GhostModelTests.java
 ┃ ┣ 📜 HunterModelTests.java
 ┃ ┗ 📜 MainViewTests.java
 ┣ 📜 README.md
 ┣ 📜 diagram.png (Diagrama de Clases)
 ┗ 📜 coverage_report.png (Cobertura de Pruebas)
```
## ✅ Entregables
- 📌 **Repositorio de GitHub:** https://github.com/LannyRivero/GhostBusters.git
- 📌 **Diagrama de Clases:** _![image](https://github.com/user-attachments/assets/adf6f4d2-9cf6-4653-bfca-e53176a2dd18)_
- 📌 **Cobertura de Pruebas:** _![image](https://github.com/user-attachments/assets/2073dd06-b38c-425a-b182-0a67a808cea2)
_


## 📢 Contribuciones
¡Cualquier cazafantasmas es bienvenido a colaborar en este proyecto! Para contribuir:
1. Haz un **fork** del repositorio.
2. Crea una **rama** (`feature/nueva-funcionalidad`).
3. **Haz commits** siguiendo buenas prácticas.
4. Envía un **Pull Request**.

## 🎮 Créditos
Desarrollado por el equipo de Cazafantasmis d’Asturies 🏰⚡

---
💡 *"No tengas miedo de los fantasmas... ¡haz que ellos te teman a ti!"*



# 👻 GhostBusters Asturias - Proyecto en Java

## 📜 Descripción

Los habitantes de Asturias han comenzado a notar sucesos paranormales. Desde sombras gigantescas que inducen miedo hasta voces espectrales que manipulan la energía. Para combatir estas amenazas, se ha formado un nuevo equipo de **GhostBusters**.

Tu misión será capturar, analizar y, si es necesario, liberar fantasmas atrapados para mantener la seguridad de la región. Con trampas de protones avanzadas y un detector ectoplásmico de última generación, debes recorrer los lugares más emblemáticos de Asturias para documentar e investigar las entidades paranormales.

🌍 **Ubicación de la base:** Un almacén industrial en Avilés, acondicionado con trampas de almacenamiento ectoplásmico.

## 🚀 Funcionalidades Principales

- **Capturar fantasmas** y añadirlos a la base de datos.
- **Visualizar lista de fantasmas capturados**, con sus características.
- **Liberar fantasmas menos peligrosos** para hacer espacio en el contenedor ectoplásmico.
- **Filtrar fantasmas por tipo de fantama** para facilitar la investigación.
- **Salir del programa** para continuar en otra ocasión.

## 🕹️ Interacción con el Usuario a través de una ventana (JFrame)

```
============================================
    👻 ¡Ghostbusters Asturias!
   Base de operaciones 👻
============================================

1. Capturar un nuevo fantasma.
  Ventana dodemos capturar al fantasma poniendo su nombre, clase, peligro, habilidad que deseemos y filtrar la fecha de su posible captura. Es viable capturar más de un fantasma.
2. Ver lista de fantasmas capturados.
  Ventana donde podemos ver la lista previamente capturados.
3. Liberar un fantasma.
  Escogemos un único fantasma de la lista y lo liberamos. Podremos volver a capturarlo
4. Filtrar fantasmas por tipo de fantasma.
   Queremos filtrar una clase de fantasma según el tipo de fantasma: manifesatción menor, aparición móvil.

5. Salir

```

## 📌 Historias de Usuario

### 🏆 Capturar Fantasmas

**Como usuario, quiero** capturar un nuevo fantasma **para** expandir mi colección y proteger los lugares emblemáticos de Asturias.

### 🔍 Visualizar Fantasmas Atrapados

**Como usuario, quiero** ver todos los fantasmas capturados **para** analizar sus características y planear estrategias.

### 🚪 Liberar Fantasmas

**Como usuario, quiero** liberar fantasmas menos peligrosos o inofensivos **para** hacer espacio en mi contenedor ectoplásmico.

### 🎭 Filtrar Fantasmas por tipo de fantasma

**Como usuario, quiero** filtrar los fantasmas por tipo **para** priorizar cuáles estudiar o utilizar en mis investigaciones.

### 🔚 Salir del Programa

**Como usuario, quiero** salir del juego **para** guardar mi progreso y continuar en otra ocasión.

## 🛠️ Tecnologías y Metodología

- **Lenguaje:** Java, GUIs.
- **Arquitectura:** MVC
- **Testing:** TDD (Cobertura mínima del 70%)
- **Control de versiones:** Git & GitHub
- **Sprint:** 1 semana

## 📂 Estructura del Proyecto

```
 ┣ 📂 src
 ┃ ┣ 📂 dev.lanny.ghost_busters
 ┃ ┃ ┗ 📜 App.java
 ┃ ┣ 📂 dev.lanny.ghost_busters.controller
 ┃ ┃ ┗ 📜 HunterModel.java
 ┃ ┣ 📂 dev.lanny.view
 ┃ ┃ ┣ 📜 MainView.java
 ┃ ┃ ┣ 📜 CaptureGhostView.java
 ┃ ┃ ┗ 📜 DeleteGhostView.java
 ┃ ┣ 📂 tests
 ┃ ┣ 📜 GhostModelTests.java
 ┃ ┣ 📜 HunterModelTests.java
 ┃ ┗ 📜 MainViewTests.java
 ┣ 📜 README.md
 ┣ 📜 diagram.png (Diagrama de Clases)
 ┗ 📜 coverage_report.png (

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

💡 _"No tengas miedo de los fantasmas... ¡haz que ellos te teman a ti!"_

# Lázaro

**Lázaro** es una aplicación móvil Android desarrollada en **Kotlin** que ayuda a personas con discapacidad visual a **identificar billetes mediante la cámara del dispositivo**.  
La app utiliza **tecnologías de visión por computadora y Firebase** para ofrecer una experiencia accesible, rápida y confiable.

---

## Descripción general

Lázaro está diseñada para mejorar la **autonomía y accesibilidad** de personas con problemas visuales, permitiéndoles reconocer el valor de los billetes chilenos a través del reconocimiento por cámara.

El usuario simplemente apunta la cámara al billete, y la aplicación **detecta y anuncia el valor** mediante una interfaz simplificada y soporte de audio.

---

## Características principales

-  **Detección de billetes** en tiempo real mediante la cámara.  
-  **Retroalimentación por voz** que comunica el valor del billete detectado.  
-  **Integración con Firebase** para autenticación y manejo de usuarios.  
-  **Persistencia de datos** con Firestore.  
-  Interfaz accesible y minimalista, adaptada a usuarios con baja visión.  

---

##  Tecnologías utilizadas

| Categoría | Tecnologías |
|------------|--------------|
| Lenguaje | Kotlin |
| Framework | Android Jetpack |
| Base de datos | Firebase Firestore |
| Servicios | Firebase Authentication |
| Arquitectura | MVVM (Model-View-ViewModel) |
| Librerías | CameraX, ML Kit / TensorFlow Lite, Coroutines, LiveData |

---

## Instalación y configuración

### Clonar el repositorio
```bash
git clone https://github.com/BriamCarrasco/Lazaro.git
cd Lazaro

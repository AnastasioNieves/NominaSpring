<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Bienvenido</title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/tu-archivo.css}" />
</head>
<body>

    <div class="container">
        <h1>Bienvenido a mi Aplicaci�n</h1>
        <p>�Gracias por visitar nuestro sitio!</p>
        
        <!-- Puedes agregar m�s contenido aqu� -->

        <a th:href="@{/login}">Iniciar Sesi�n</a>
    </div>

</body>
</html>

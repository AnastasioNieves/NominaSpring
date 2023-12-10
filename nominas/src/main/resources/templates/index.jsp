<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Menu empleados</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}" />
</head>
<body>
    <div class="container">

        <!-- Incluye la cabecera -->
        <div th:replace="bases/header"></div>

        <div class="content">

            <!-- Verifica si el atributo 'content' est� presente -->
            <div th:if="${#strings.isEmpty(request.getAttribute('content'))}">
                <!-- Incluye la p�gina por defecto -->
                <div th:include="/views/bienvenida :: content"></div>
            </div>
            <div th:unless="${#strings.isEmpty(request.getAttribute('content'))}">
                <!-- Incluye la p�gina especificada en el atributo 'content' -->
                <div th:include="${request.getAttribute('content')}"></div>
            </div>

        </div>

        <!-- Incluye el pie de p�gina -->
        <div th:replace="bases/footer"></div>
    </div>
</body>
</html>

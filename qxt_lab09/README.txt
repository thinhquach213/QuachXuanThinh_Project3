qxt_lab09 - Spring Boot 2.7.x project skeleton
----------------------------------------------
Files created in this zip are a minimal working example.

How to run:
1. Install JDK 11 and Maven.
2. Create MySQL database named 'qxt_lab09' and run:
   CREATE DATABASE qxt_lab09 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
3. Update src/main/resources/application.properties to set your DB username/password.
4. Build and run:
   mvn clean package
   mvn spring-boot:run
5. Open http://localhost:8080/books

Notes:
- Lombok is used. If you don't have Lombok plugin, you can remove Lombok annotations and add getters/setters.
- Uploaded example images (if present) were copied into src/main/resources/static/images:
d09b0a33-2212-40bc-ab5e-44f2ac1db4c2.png
b1d9743b-9728-4492-be7d-d52c5451c37c.png
0acb0579-eebe-4cc5-9944-4d90f4d1530c.png

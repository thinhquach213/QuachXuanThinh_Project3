QXT Admin Full Module
======================

This package contains a full Admin module for the QXT Bookstore project.

Files included:
- Repositories: UserRepository, CategoryRepository, BookRepository, OrderRepository, OrderItemRepository
- Services and implementations: UserServiceImpl, CategoryServiceImpl, BookServiceImpl, OrderServiceImpl
- Admin controllers: AdminDashboardController, AdminUserController, AdminCategoryController, AdminOrderController
- Thymeleaf templates: src/main/resources/templates/admin/*
- CSS: src/main/resources/static/css/admin.css

Integration steps:
1. Copy files into your Spring Boot project under the same package `k23cnt3.qxtWebbansach`.
2. Ensure your existing Entity models (User, Category, Book, Order, OrderItem) match the expected fields.
3. Add Spring Security rule to restrict /admin/** to ADMIN role.
4. Adjust any package names if your base package differs.
5. Build and run your application.

Notes:
- Some controllers call service methods (e.g., userService.getAllUsers()). If you already have services, you may remove duplicated files.
- Password encoding uses BCrypt in UserServiceImpl.

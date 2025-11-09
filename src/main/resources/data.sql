-- Очистка таблиц (порядок важен из-за foreign keys)
DELETE FROM quiz_submission;
DELETE FROM answer_option;
DELETE FROM question;
DELETE FROM quiz;
DELETE FROM submission;
DELETE FROM assignment;
DELETE FROM lesson;
DELETE FROM module;
DELETE FROM enrollment;
DELETE FROM course_review;
DELETE FROM course_tag;
DELETE FROM tag;
DELETE FROM course;
DELETE FROM category;
DELETE FROM profile;
DELETE FROM "user";

-- Сброс последовательностей
ALTER SEQUENCE user_id_seq RESTART WITH 1;
ALTER SEQUENCE profile_id_seq RESTART WITH 1;
ALTER SEQUENCE category_id_seq RESTART WITH 1;
ALTER SEQUENCE course_id_seq RESTART WITH 1;
ALTER SEQUENCE enrollment_id_seq RESTART WITH 1;
ALTER SEQUENCE module_id_seq RESTART WITH 1;
ALTER SEQUENCE lesson_id_seq RESTART WITH 1;
ALTER SEQUENCE assignment_id_seq RESTART WITH 1;
ALTER SEQUENCE submission_id_seq RESTART WITH 1;
ALTER SEQUENCE quiz_id_seq RESTART WITH 1;
ALTER SEQUENCE question_id_seq RESTART WITH 1;
ALTER SEQUENCE answer_option_id_seq RESTART WITH 1;
ALTER SEQUENCE quiz_submission_id_seq RESTART WITH 1;
ALTER SEQUENCE course_review_id_seq RESTART WITH 1;
ALTER SEQUENCE tag_id_seq RESTART WITH 1;

-- Пользователи
INSERT INTO "user" (name, email, role) VALUES
('Иван Петров', 'ivan@example.com', 'TEACHER'),
('Мария Сидорова', 'maria@example.com', 'TEACHER'),
('Алексей Смирнов', 'alex@example.com', 'STUDENT'),
('Елена Козлова', 'elena@example.com', 'STUDENT'),
('Дмитрий Волков', 'dmitry@example.com', 'STUDENT'),
('Анна Новикова', 'anna@example.com', 'STUDENT'),
('Администратор', 'admin@example.com', 'ADMIN');

-- Профили
INSERT INTO profile (user_id, bio, avatar_url) VALUES
(1, 'Опытный преподаватель Java с 10-летним стажем', '/avatars/ivan.jpg'),
(2, 'Специалист по базам данных и Spring Framework', '/avatars/maria.jpg'),
(3, 'Студент, изучаю backend разработку', '/avatars/alex.jpg'),
(4, 'Начинающий Java разработчик', '/avatars/elena.jpg'),
(5, 'Увлекаюсь программированием и алгоритмами', '/avatars/dmitry.jpg'),
(6, 'Студентка компьютерных наук', '/avatars/anna.jpg'),
(7, 'Системный администратор платформы', '/avatars/admin.jpg');

-- Категории курсов
INSERT INTO category (name) VALUES
('Программирование'),
('Базы данных'),
('Веб-разработка'),
('Мобильная разработка');

-- Теги
INSERT INTO tag (name) VALUES
('Java'),
('Spring'),
('Hibernate'),
('SQL'),
('PostgreSQL'),
('REST API'),
('JPA'),
('ORM');

-- Курсы
INSERT INTO course (title, description, duration, start_date, category_id, teacher_id) VALUES
('Java для начинающих', 'Основы программирования на Java', '3 месяца', '2024-09-01', 1, 1),
('Spring Boot и Hibernate', 'Современная разработка на Spring Framework', '4 месяца', '2024-09-15', 1, 2),
('Базы данных и SQL', 'Основы работы с реляционными базами данных', '2 месяца', '2024-10-01', 2, 1),
('REST API разработка', 'Создание веб-сервисов на Spring', '3 месяца', '2024-09-20', 3, 2);

-- Связи курсов с тегами
INSERT INTO course_tag (course_id, tag_id) VALUES
(1, 1), (1, 7),
(2, 1), (2, 2), (2, 3), (2, 6), (2, 7), (2, 8),
(3, 4), (3, 5),
(4, 2), (4, 6);

-- Записи на курсы
INSERT INTO enrollment (user_id, course_id, enroll_date, status) VALUES
(3, 1, '2024-08-25', 'ACTIVE'),
(4, 1, '2024-08-26', 'ACTIVE'),
(5, 1, '2024-08-27', 'ACTIVE'),
(3, 2, '2024-09-01', 'ACTIVE'),
(4, 2, '2024-09-02', 'ACTIVE'),
(6, 2, '2024-09-03', 'ACTIVE'),
(5, 3, '2024-09-20', 'ACTIVE'),
(6, 4, '2024-09-15', 'ACTIVE');

-- Модули курсов
INSERT INTO module (course_id, title, order_index, description) VALUES
-- Курс 1: Java для начинающих
(1, 'Введение в Java', 1, 'Основные концепции языка Java'),
(1, 'Объектно-ориентированное программирование', 2, 'Классы, объекты, наследование'),
(1, 'Коллекции и исключения', 3, 'Работа с коллекциями и обработка ошибок'),

-- Курс 2: Spring Boot и Hibernate
(2, 'Введение в Spring Framework', 1, 'Основы Spring и Dependency Injection'),
(2, 'Spring Boot', 2, 'Создание приложений с Spring Boot'),
(2, 'Работа с базами данных', 3, 'JPA и Hibernate'),
(2, 'Spring MVC', 4, 'Создание веб-приложений'),

-- Курс 3: Базы данных и SQL
(3, 'Основы SQL', 1, 'SELECT, INSERT, UPDATE, DELETE'),
(3, 'Продвинутый SQL', 2, 'JOIN, подзапросы, агрегатные функции'),

-- Курс 4: REST API разработка
(4, 'Основы REST', 1, 'Принципы REST архитектуры'),
(4, 'Spring REST', 2, 'Создание REST API с Spring');

-- Уроки
INSERT INTO lesson (module_id, title, content, video_url) VALUES
-- Модуль 1: Введение в Java
(1, 'Установка JDK', 'Установка и настройка Java Development Kit', '/videos/java-setup.mp4'),
(1, 'Первая программа', 'Создание первой программы Hello World', '/videos/java-hello.mp4'),
(1, 'Переменные и типы данных', 'Основные типы данных в Java', '/videos/java-variables.mp4'),

-- Модуль 2: ООП в Java
(2, 'Классы и объекты', 'Создание классов и объектов', '/videos/java-classes.mp4'),
(2, 'Наследование', 'Принципы наследования в Java', '/videos/java-inheritance.mp4'),
(2, 'Интерфейсы', 'Работа с интерфейсами', '/videos/java-interfaces.mp4'),

-- Модуль 4: Введение в Spring
(4, 'Введение в Spring', 'Основные концепции Spring Framework', '/videos/spring-intro.mp4'),
(4, 'Dependency Injection', 'Внедрение зависимостей', '/videos/spring-di.mp4'),

-- Модуль 6: Работа с базами данных
(6, 'Введение в JPA', 'Основы Java Persistence API', '/videos/jpa-intro.mp4'),
(6, 'Сущности и связи', 'Создание сущностей и настройка связей', '/videos/jpa-entities.mp4');

-- Задания
INSERT INTO assignment (lesson_id, title, description, due_date, max_score) VALUES
(1, 'Установка окружения', 'Установите JDK и настройте IDE', '2024-09-10', 10),
(2, 'Hello World', 'Напишите программу Hello World', '2024-09-12', 15),
(4, 'Создание класса', 'Создайте класс Student с полями', '2024-09-20', 20),
(5, 'Наследование', 'Реализуйте наследование между классами', '2024-09-25', 25),
(8, 'Spring Bean', 'Создайте и сконфигурируйте Spring Bean', '2024-10-05', 30),
(9, 'JPA Entity', 'Создайте сущность с JPA аннотациями', '2024-10-15', 35);

-- Решения заданий
INSERT INTO submission (assignment_id, student_id, submitted_at, content, score, feedback) VALUES
(1, 3, '2024-09-08 14:30:00', 'Установил JDK 21 и IntelliJ IDEA', 10, 'Отлично!'),
(1, 4, '2024-09-09 10:15:00', 'Настроил Eclipse с JDK 21', 8, 'Хорошо, но лучше использовать IntelliJ'),
(2, 3, '2024-09-10 16:45:00', 'public class Hello { public static void main(String[] args) { System.out.println("Hello"); } }', 15, 'Правильно!'),
(3, 3, '2024-09-18 12:20:00', 'public class Student { private String name; private int age; }', 18, 'Не хватает геттеров и сеттеров');

-- Тесты
INSERT INTO quiz (module_id, course_id, title, time_limit) VALUES
(1, 1, 'Тест по основам Java', 30),
(2, 1, 'Тест по ООП в Java', 45),
(4, 2, 'Тест по Spring Framework', 40),
(6, 2, 'Тест по JPA и Hibernate', 50);

-- Вопросы тестов
INSERT INTO question (quiz_id, text, type) VALUES
-- Вопросы для теста 1 (Основы Java)
(1, 'Что такое JVM?', 'SINGLE_CHOICE'),
(1, 'Какие примитивные типы есть в Java?', 'MULTIPLE_CHOICE'),
(1, 'Для чего используется ключевое слово final?', 'SINGLE_CHOICE'),

-- Вопросы для теста 2 (ООП в Java)
(2, 'Что такое наследование?', 'SINGLE_CHOICE'),
(2, 'Какие принципы ООП вы знаете?', 'MULTIPLE_CHOICE'),

-- Вопросы для теста 3 (Spring Framework)
(3, 'Что такое Dependency Injection?', 'SINGLE_CHOICE'),
(3, 'Какие аннотации Spring вы знаете?', 'MULTIPLE_CHOICE');

-- Варианты ответов
INSERT INTO answer_option (question_id, text, is_correct) VALUES
-- Ответы для вопроса 1
(1, 'Java Virtual Machine', true),
(1, 'Java Visual Manager', false),
(1, 'Java Version Manager', false),

-- Ответы для вопроса 2
(2, 'int', true),
(2, 'String', false),
(2, 'boolean', true),
(2, 'float', true),

-- Ответы для вопроса 3
(3, 'Для объявления констант', true),
(3, 'Для создания классов', false),
(3, 'Для импорта библиотек', false),

-- Ответы для вопроса 4
(4, 'Механизм создания новых классов на основе существующих', true),
(4, 'Способ скрытия реализации', false),
(4, 'Метод перегрузки операторов', false),

-- Ответы для вопроса 5
(5, 'Инкапсуляция', true),
(5, 'Наследование', true),
(5, 'Полиморфизм', true),
(5, 'Абстракция', true),

-- Ответы для вопроса 6
(6, 'Механизм внедрения зависимостей', true),
(6, 'Способ создания объектов', false),
(6, 'Метод оптимизации кода', false),

-- Ответы для вопроса 7
(7, '@Autowired', true),
(7, '@Service', true),
(7, '@Entity', false),
(7, '@RestController', true);

-- Результаты тестов
INSERT INTO quiz_submission (quiz_id, student_id, score, taken_at) VALUES
(1, 3, 85, '2024-09-15 14:30:00'),
(1, 4, 90, '2024-09-16 10:15:00'),
(2, 3, 78, '2024-09-25 16:20:00');

-- Отзывы о курсах
INSERT INTO course_review (course_id, student_id, rating, comment, created_at) VALUES
(1, 3, 5, 'Отличный курс для начинающих!', '2024-09-20 12:00:00'),
(1, 4, 4, 'Хороший курс, но можно добавить больше практики', '2024-09-21 14:30:00'),
(2, 3, 5, 'Прекрасный курс по Spring!', '2024-10-01 09:15:00');
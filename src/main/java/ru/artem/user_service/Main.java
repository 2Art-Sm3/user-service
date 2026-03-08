package ru.artem.user_service;

import ru.artem.user_service.dto.UserDto;
import ru.artem.user_service.exception.DatabaseException;
import ru.artem.user_service.exception.NotFoundException;
import ru.artem.user_service.exception.ValidationException;
import ru.artem.user_service.service.UserService;
import ru.artem.user_service.service.UserServiceImpl;
import ru.artem.user_service.util.InputUtil;

import java.util.List;

public class Main {

    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {

        while (true) {

            printMenu();

            try {

                int choice = InputUtil.readInt("Выберите пункт");

                switch (choice) {

                    case 1 -> createUser();
                    case 2 -> getUserById();
                    case 3 -> getAllUsers();
                    case 4 -> updateUser();
                    case 5 -> deleteUser();
                    case 0 -> {
                        System.out.println("Выход из программы...");
                        return;
                    }

                    default -> System.out.println("Неверный пункт меню");
                }

            } catch (ValidationException e) {

                System.out.println("Ошибка валидации: " + e.getMessage());

            } catch (NotFoundException e) {

                System.out.println("Ошибка: " + e.getMessage());

            } catch (DatabaseException e) {

                System.out.println("Ошибка базы данных");

            } catch (Exception e) {

                System.out.println("Неожиданная ошибка: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {

        System.out.println();
        System.out.println("1 - Создать пользователя");
        System.out.println("2 - Найти пользователя по id");
        System.out.println("3 - Показать всех пользователей");
        System.out.println("4 - Обновить пользователя");
        System.out.println("5 - Удалить пользователя");
        System.out.println("0 - Выход");
    }

    private static void createUser() {

        String name = InputUtil.readString("Имя");
        String email = InputUtil.readString("Email");
        int age = InputUtil.readInt("Возраст");

        UserDto dto = UserDto.builder()
                .name(name)
                .email(email)
                .age(age)
                .build();

        userService.createUser(dto);

        System.out.println("Пользователь создан");
    }

    private static void getUserById() {

        Long id = InputUtil.readLong("Введите id");

        UserDto user = userService.getUserById(id);

        System.out.println(user);
    }

    private static void getAllUsers() {

        List<UserDto> users = userService.getAllUsers();

        users.forEach(System.out::println);
    }

    private static void updateUser() {

        Long id = InputUtil.readLong("ID пользователя");

        String name = InputUtil.readString("Имя");
        String email = InputUtil.readString("Email");
        int age = InputUtil.readInt("Возраст");

        UserDto dto = UserDto.builder()
                .name(name)
                .email(email)
                .age(age)
                .build();

        userService.updateUser(id, dto);

        System.out.println("Пользователь обновлён");
    }

    private static void deleteUser() {

        Long id = InputUtil.readLong("ID пользователя");

        userService.deleteUser(id);

        System.out.println("Пользователь удалён");
    }
}

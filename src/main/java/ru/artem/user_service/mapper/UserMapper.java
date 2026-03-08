package ru.artem.user_service.mapper;

import ru.artem.user_service.dto.UserDto;
import ru.artem.user_service.entity.User;

public class UserMapper {

    public static User toEntity(UserDto dto) {

        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .age(dto.age())
                .createdAt(dto.createdAt())
                .build();
    }

    public static UserDto toDto(User user) {

        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .createdAt(user.getCreatedAt())
                .build();
    }
}

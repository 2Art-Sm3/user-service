package ru.artem.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDto(

        @NotBlank(message = "имя не должно быть пустым")
        String name,

        @Email(message = "невалидный email")
        @NotBlank(message = "email не должен быть пустым")
        String email,

        @Min(value = 0, message = "возраст  не может быть меньше 0")
        @Max(value = 100, message = "возраст должен быть реалистичным")
        Integer age,

        LocalDateTime createdAt
) {}

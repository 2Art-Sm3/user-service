package ru.artem.user_service.service;

import ru.artem.user_service.dao.UserDao;
import ru.artem.user_service.dao.UserDaoImpl;
import ru.artem.user_service.dto.UserDto;
import ru.artem.user_service.entity.User;
import ru.artem.user_service.exception.NotFoundException;
import ru.artem.user_service.mapper.UserMapper;
import ru.artem.user_service.validation.ValidationUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public UserDto createUser(UserDto userDto) {

        ValidationUtil.validate(userDto);

        User user = UserMapper.toEntity(userDto);
        user.setCreatedAt(LocalDateTime.now());

        User saved = userDao.save(user);

        return UserMapper.toDto(saved);
    }

    @Override
    public UserDto getUserById(Long id) {

        return userDao.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Пользователь с id " + id + " не найден"));
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userDao.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        ValidationUtil.validate(userDto);

        User existing = userDao.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Пользователь не найден"));

        existing.setName(userDto.name());
        existing.setEmail(userDto.email());
        existing.setAge(userDto.age());

        User updated = userDao.update(existing);

        return UserMapper.toDto(updated);
    }

    @Override
    public void deleteUser(Long id) {

        userDao.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Пользователь не найден"));

        userDao.delete(id);
    }
}

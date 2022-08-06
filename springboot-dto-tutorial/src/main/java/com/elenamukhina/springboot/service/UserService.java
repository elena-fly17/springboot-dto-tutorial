package com.elenamukhina.springboot.service;

import com.elenamukhina.springboot.dto.UserLocationDTO;
import com.elenamukhina.springboot.model.User;
import com.elenamukhina.springboot.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // этот метод вызывает метод, написанный ниже
    public List<UserLocationDTO> getAllUsersLocation() {
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // ИСПОЛЬЗОВАНИЕ DTO БЕЗ БИБЛИОТЕКИ-ПРЕОБРАЗОВАТЕЛЯ
    // метод, преобразовывающий объект User в UserLocationDTO
    // заметь, что в этот метод достаточно передать только User, а все данные, касающиеся
    // Location (его полей), мы вытягиваем через User
//    private UserLocationDTO convertEntityToDto(User user) {
//        UserLocationDTO userLocationDTO = new UserLocationDTO();
//        userLocationDTO.setUserId(user.getId());
//        userLocationDTO.setEmail(user.getEmail());
//        userLocationDTO.setPlace(user.getLocation().getPlace());
//        userLocationDTO.setLongitude(user.getLocation().getLongitude());
//        userLocationDTO.setLatitude(user.getLocation().getLatitude());
//        return userLocationDTO;
//    }

    // ИСПОЛЬЗОВАНИЕ DTO С БИБЛИОТЕКОЙ-ПРЕОБРАЗОВАТЕЛЕМ (В ДАННОМ СЛУЧАЕ С MODELMAPPER)
    // метод, преобразовывающий объект User в UserLocationDTO
    // заметь, что в этот метод достаточно передать только User, а все данные, касающиеся
    // Location (его полей), мы вытягиваем через User
    private UserLocationDTO convertEntityToDto(User user) {
        // стратегия LOOSE позволяет включать в сопоставление не только обычные поля примитивных типов,
        // но и поля с объектами других классов
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        UserLocationDTO userLocationDTO = new UserLocationDTO();
        // в метод map передаем объект класса, который будем преобразовывать (user),
        // и указываем, в объект какого класса его нужно преобразовать (UserLocationDTO.class)
        // ModelMapper возьмет из объекта user нужные поля и добавит их в объект класса userLocationDTO
        userLocationDTO = modelMapper.map(user, UserLocationDTO.class);
        return userLocationDTO;
    }

    // можно сделать и наоборот - преобразовать DTO-класс в какую-нибудь сущность
//    private User convertDtoToEntity(UserLocationDTO userLocationDTO) {
//        // стратегия LOOSE позволяет включать в сопоставление не только обычные поля примитивных типов,
//        // но и поля с объектами других классов
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
//        User user = new User();
//        // передаем в метод map объект userLocationDTO,
//        // и указываем, в объект какого класса его нужно преобразовать (User.class)
//        user = modelMapper.map(userLocationDTO, User.class);
//        return user;
//    }
}

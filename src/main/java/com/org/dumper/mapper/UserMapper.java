package com.org.dumper.mapper;

import com.org.dumper.dto.UserDto;
import com.org.dumper.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    List<User> map(List<UserDto> usersDtos);

//    List<User> map(List<UserDto> usersDto);
}

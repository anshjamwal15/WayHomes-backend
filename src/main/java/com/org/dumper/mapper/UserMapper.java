package com.org.dumper.mapper;

import com.org.dumper.dto.UserDto;
import com.org.dumper.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toEntity(User user);

    User toDto(UserDto userDto);

    List<User> map(List<UserDto> usersDtos);

//    List<User> map(List<UserDto> usersDto);
}

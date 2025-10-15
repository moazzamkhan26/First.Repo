package com.example.EmailMapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.EmailDTO.EmailRequest;
import com.example.EmailDTO.EmailResponse;
import com.example.EmailEntity.EmailEntity;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);

    EmailEntity toEntity(EmailRequest dto);
    EmailResponse toResponse(EmailEntity entity);
}
//
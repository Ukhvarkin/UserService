package com.aston.angularlaba.user.mapper;

import com.aston.angularlaba.user.dto.ClientDTO;
import com.aston.angularlaba.user.model.Client;
import com.aston.angularlaba.user.model.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "email", source = "clientRegistrationDTO.email")
    @Mapping(target = "password", source = "clientRegistrationDTO.password")
    UserProfile toUserProfile(ClientDTO clientRegistrationDTO);

    @Mapping(target = "firstName", source = "clientRegistrationDTO.firstName")
    @Mapping(target = "lastName", source = "clientRegistrationDTO.lastName")
    @Mapping(target = "mobilePhone", source = "clientRegistrationDTO.mobilePhone")
    @Mapping(target = "passportNumber", source = "clientRegistrationDTO.passportNumber")
    @Mapping(target = "accessionDate", source = "clientRegistrationDTO.accessionDate")
    Client toClient(ClientDTO clientRegistrationDTO);

}
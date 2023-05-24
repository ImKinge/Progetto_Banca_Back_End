package com.banca.banca.mapper;

import com.banca.banca.dto.CustomerDataDto;
import com.banca.banca.entity.CustomerData;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CustomerDataMapper {

    public CustomerDataDto toCustomerDataDto (CustomerData customerData);

    public CustomerData toCustomerData (CustomerDataDto customerDataDto);

    List<CustomerData> toListCustomerData (List<CustomerDataDto> customerDataDtoList);

    List<CustomerDataDto> toListCustomerDataDto (List<CustomerData> customerDataList);
}

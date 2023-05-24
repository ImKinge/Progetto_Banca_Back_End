package com.banca.banca.mapper;

import com.banca.banca.dto.CurrentAccountDto;
import com.banca.banca.entity.CurrentAccount;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CurrentAccountMapper {

    CurrentAccountDto toCurrentAccountDto (CurrentAccount currentAccount);

    CurrentAccount toCurrentAccount (CurrentAccountDto currentAccountDto);

    List<CurrentAccount> toListCurrentAccount (List<CurrentAccountDto> currentAccountDtoList);

    List<CurrentAccountDto> toListCurrentAccountDto (List<CurrentAccount> currentAccountList);
}

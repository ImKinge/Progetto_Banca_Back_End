package com.banca.banca.service;

import com.banca.banca.dto.IbanTransactionDto;
import com.banca.banca.dto.ReportTransactionRequestDto;
import com.banca.banca.dto.ReportTransactionResponseDto;
import com.banca.banca.exception.TransferException;
import com.banca.banca.security.jwt.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private IbanTransactionService ibanTransactionService;

    @Autowired
    private JWTGenerator jwtGenerator;


    @Override
    public List<ReportTransactionResponseDto> getReportTransaction(ReportTransactionRequestDto reportTransactionRequestDto, String token) throws TransferException {

        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);

        List<IbanTransactionDto> ibanTransactions = new ArrayList<>();
        List<ReportTransactionResponseDto> reportTransactionResponseDtos = new ArrayList<>();

        String operation = reportTransactionRequestDto.getOperation();
        Date reportDateStart = reportTransactionRequestDto.getDateStart();
        Date reportDateEnd = reportTransactionRequestDto.getDateEnd();

        LocalDateTime localDateTimeStart = LocalDateTime.ofInstant(reportDateStart.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTimeEnd = LocalDateTime.ofInstant(reportDateEnd.toInstant(), ZoneId.systemDefault());


        if(operation.equals("Iban")) {
            ibanTransactions = ibanTransactionService.getRangeDateTransaction(localDateTimeStart, localDateTimeEnd, fiscalCode);
        }

        for (IbanTransactionDto ibanTransactionDto : ibanTransactions) {

            ReportTransactionResponseDto reportTransactionResponseDto = new ReportTransactionResponseDto();

            reportTransactionResponseDto.setDescription(ibanTransactionDto.getDescriptionTransaction());
            reportTransactionResponseDto.setDateTransaction(ibanTransactionDto.getDateTransaction());
            reportTransactionResponseDto.setAmount(ibanTransactionDto.getAmount());

            reportTransactionResponseDtos.add(reportTransactionResponseDto);
        }

        return reportTransactionResponseDtos;

    }
}

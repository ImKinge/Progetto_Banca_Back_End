package com.banca.banca.service;

import com.banca.banca.dto.ReportTransactionRequestDto;
import com.banca.banca.dto.ReportTransactionResponseDto;
import com.banca.banca.exception.TransferException;

import java.util.List;

public interface ReportService {

    List<ReportTransactionResponseDto> getReportTransaction (ReportTransactionRequestDto reportTransactionRequestDto, String token) throws TransferException;
}

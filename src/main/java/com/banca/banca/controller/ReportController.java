package com.banca.banca.controller;

import com.banca.banca.dto.ReportTransactionRequestDto;
import com.banca.banca.dto.ReportTransactionResponseDto;
import com.banca.banca.dto.ResponseDto;
import com.banca.banca.exception.TransferException;

import com.banca.banca.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ReportController {


    @Autowired
    private ReportService reportService;

    @PostMapping("/download")
    public ResponseEntity<ResponseDto<String>> getReportTransaction (@RequestHeader (HttpHeaders.AUTHORIZATION) String token,
                                                                     @RequestBody ReportTransactionRequestDto reportTransactionRequestDto,
                                                                     HttpServletResponse response) throws IOException {
        ICsvBeanWriter csvWriter = null;

        try {
            csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
            List<ReportTransactionResponseDto> reportTransactionResponseDtos = reportService.getReportTransaction(reportTransactionRequestDto, token);
            String[] cvsHeader = {"Description", "Date Transaction", "Amount"};
            String[] propertyName = {"Description", "dateTransaction", "amount"};

            csvWriter.writeHeader(cvsHeader);
            for (ReportTransactionResponseDto reportTransactionResponseDto : reportTransactionResponseDtos) {
                csvWriter.write(reportTransactionResponseDto, propertyName);
            }

            HttpStatus httpStatus = HttpStatus.OK;
            if (reportTransactionResponseDtos.isEmpty()) {
                httpStatus = HttpStatus.NO_CONTENT;
            }

            csvWriter.close();

            response.setContentType("text/csv"); //mime
            response.setHeader("Content-Disposition", "attachment; filename=report.csv"); //serve al browser per farmi capire che c'è un allegato

            return new ResponseEntity<>(new ResponseDto<>("Successful download", httpStatus, true), HttpStatus.OK);

        } catch (TransferException ex) {
            response.reset();//il reset toglie l'evento così prende il 400
            return new ResponseEntity<>(new ResponseDto<>(ex.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    false), HttpStatus.BAD_REQUEST);
        }

    }

}

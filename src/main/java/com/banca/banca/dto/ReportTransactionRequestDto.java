package com.banca.banca.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class ReportTransactionRequestDto {

    private String operation;

    private Date dateStart;

    private Date dateEnd;

    private String format;


    public ReportTransactionRequestDto () {

    }

    public ReportTransactionRequestDto(String operation, Date dateStart, Date dateEnd, String format) {
        this.operation = operation;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.format = format;
    }


    //Getter and Setter

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }


    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }


    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }


    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}

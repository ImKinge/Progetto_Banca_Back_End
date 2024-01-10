package com.banca.banca.dto;

public class ServiceBankDto {

    private Integer id;

    private String category;
    private String serviceName;

    private Double monthlyFee;

    private String description;

    public ServiceBankDto() {
    }

    public ServiceBankDto(Integer id, String category, String serviceName, Double monthlyFee, String description) {
        this.id = id;
        this.category = category;
        this.serviceName = serviceName;
        this.monthlyFee = monthlyFee;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

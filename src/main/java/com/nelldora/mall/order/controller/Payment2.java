package com.nelldora.mall.order.controller;

public class Payment2 {

    private String imp_uid;
    private String merchant_uid;

    private int paid_amount; // 결제 성공
    private String status; // 결제 상태
    private String name; // 주문자명
    private String paid_method; //결제 금액
    private boolean success; //결저 성공 여부

    public Payment2() {
    }

    public static Payment2 createPayment(String impUid, long merchantUid, int paidAmount, String status, String paidMethod,
                                         boolean success, String pgProvider) {
        Payment2 paymentDTO = new Payment2();
        paymentDTO.imp_uid = impUid;
        paymentDTO.merchant_uid = merchantUid;
        paymentDTO.paid_amount = paidAmount;
        paymentDTO.status = status;
        paymentDTO.paid_method = paidMethod;
        paymentDTO.success = success;
        paymentDTO.pg_provider = pgProvider;
        return paymentDTO;
    }


    public Payment2(String imp_uid, String merchant_uid) {
        this.imp_uid = imp_uid;
        this.merchant_uid = merchant_uid;
    }

    public String getImp_uid() {
        return imp_uid;
    }

    public void setImp_uid(String imp_uid) {
        this.imp_uid = imp_uid;
    }

    public String getMerchant_uid() {
        return merchant_uid;
    }

    public void setMerchant_uid(String merchant_uid) {
        this.merchant_uid = merchant_uid;
    }

    public int getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(int paid_amount) {
        this.paid_amount = paid_amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaid_method() {
        return paid_method;
    }

    public void setPaid_method(String paid_method) {
        this.paid_method = paid_method;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

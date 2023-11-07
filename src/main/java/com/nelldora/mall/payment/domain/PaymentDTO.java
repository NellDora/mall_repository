package com.nelldora.mall.payment.domain;

public class PaymentDTO {

    private String impUid;
    private long merchantUid;
    private int paidAmount;
    private String status;
    private String paidMethod;
    private boolean success;



    public PaymentDTO() {
        super();
    }


    public static PaymentDTO createPayment(String impUid, long merchantUid, int paidAmount, String status, String paidMethod,
                                           boolean success) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.impUid = impUid;
        paymentDTO.merchantUid = merchantUid;
        paymentDTO.paidAmount = paidAmount;
        paymentDTO.status = status;
        paymentDTO.paidMethod = paidMethod;
        paymentDTO.success = success;
        return paymentDTO;
    }


    public String getImpUid() {
        return impUid;
    }





    public long getMerchantUid() {
        return merchantUid;
    }


    public int getPaidAmount() {
        return paidAmount;
    }


    public String getStatus() {
        return status;
    }




    public String getPaidMethod() {
        return paidMethod;
    }


    public boolean isSuccess() {
        return success;
    }


    @Override
    public String toString() {
        return "PaymentDTO [impUid=" + impUid + ", merchantUid=" + merchantUid + ", paidAmount=" + paidAmount
                + ", status=" + status + ", paidMethod=" + paidMethod + ", success=" + success + "]";
    }




}

package com.nelldora.mall.payment.domain;

public class Payment {

    private String impUid;
    private long merchantUid;
    private int paidAmount;
    private String status;

    private String pgProvider;
    private String paidMethod;
    private boolean success;



    public Payment() {
        super();
    }


    public static Payment createPayment(String impUid, long merchantUid, int paidAmount, String status, String paidMethod,
                                        boolean success, String pgProvider) {
        Payment paymentDTO = new Payment();
        paymentDTO.impUid = impUid;
        paymentDTO.merchantUid = merchantUid;
        paymentDTO.paidAmount = paidAmount;
        paymentDTO.status = status;
        paymentDTO.pgProvider = pgProvider;
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


    public String getPgProvider() {
        return pgProvider;
    }
}

package com.example.eshopping.model.paytm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Body {

    private ResultInfo resultInfo;
    private String txnToken;
    private Boolean isPromoCodeValid;
    private Boolean authenticated;

    public String getTxnToken() {
        return txnToken;
    }

    public Boolean getIsPromoCodeValid() {
        return isPromoCodeValid;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setTxnToken(String txnToken) {
        this.txnToken = txnToken;
    }

    public void setIsPromoCodeValid(Boolean isPromoCodeValid) {
        isPromoCodeValid = isPromoCodeValid;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
    }

    public void setPromoCodeValid(Boolean promoCodeValid) {
        isPromoCodeValid = promoCodeValid;
    }
}

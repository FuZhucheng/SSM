package com.fuzhu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${符柱成} on 2017/4/6.
 */
/**Commodity_list*/
public class GoodDetails implements Serializable {

    private String goodId;
    private String goodName;
    private String storeName;
    private String goodBrand;
    private String goodPrice;
    private Integer monthsaleNum;
    private String storeAdd;
    private Integer goodHot;
    private String sellerCredit;
    private Integer commentNum;
    private Integer ishasLicense;

    private String goodAfterRate;
    private String industryCompare;

    private String goodLink;
    private String storeLink;
    private BigDecimal descriptionSituation;
    private BigDecimal serviceAttitude;
    private BigDecimal logisticsService;
    private String colorClassification;
    private List<String> picPathList =new ArrayList<String>();

    public GoodDetails() {

    }

    public GoodDetails(String goodId, String goodName, String storeName,
                       String goodBrand, String goodPrice, Integer monthsaleNum,
                       String storeAdd, Integer goodHot, String sellerCredit,
                       Integer commentNum, Integer ishasLicense, String goodAfterRate,
                       String industryCompare, String goodLink, String storeLink,
                       BigDecimal descriptionSituation, BigDecimal serviceAttitude,
                       BigDecimal logisticsService, String colorClassification,
                        List<String> picPathList) {
        super();
        this.goodId = goodId;
        this.goodName = goodName;
        this.storeName = storeName;
        this.goodBrand = goodBrand;
        this.goodPrice = goodPrice;
        this.monthsaleNum = monthsaleNum;
        this.storeAdd = storeAdd;
        this.goodHot = goodHot;
        this.sellerCredit = sellerCredit;
        this.commentNum = commentNum;
        this.ishasLicense = ishasLicense;
        this.goodAfterRate = goodAfterRate;
        this.industryCompare = industryCompare;
        this.goodLink = goodLink;
        this.storeLink = storeLink;
        this.descriptionSituation = descriptionSituation;
        this.serviceAttitude = serviceAttitude;
        this.logisticsService = logisticsService;
        this.colorClassification = colorClassification;
        this.picPathList = picPathList;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getGoodBrand() {
        return goodBrand;
    }

    public void setGoodBrand(String goodBrand) {

        this.goodBrand = goodBrand;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        if (goodPrice.contains("-")) {
            String[] ary_price = goodPrice.split("\\-");
            this.goodPrice = ary_price[0];
//			int i = goodPrice.indexOf("-");
//			this.goodPrice =goodPrice.substring(0,i);
        }else {
            this.goodPrice = goodPrice;
        }



    }

    public Integer getMonthsaleNum() {
        return monthsaleNum;
    }

    public void setMonthsaleNum(Integer monthsaleNum) {
        this.monthsaleNum = monthsaleNum;
    }

    public String getStoreAdd() {
        return storeAdd;
    }

    public void setStoreAdd(String storeAdd) {
        this.storeAdd = storeAdd;
    }

    public Integer getGoodHot() {
        return goodHot;
    }

    public void setGoodHot(Integer goodHot) {
        this.goodHot = goodHot;
    }

    public String getSellerCredit() {
        return sellerCredit;
    }

    public void setSellerCredit(String sellerCredit) {
        this.sellerCredit = sellerCredit;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getIshasLicense() {
        return ishasLicense;
    }

    public void setIshasLicense(Integer ishasLicense) {
        this.ishasLicense = ishasLicense;
    }

    public String getGoodAfterRate() {
        return goodAfterRate;
    }

    public void setGoodAfterRate(String goodAfterRate) {
        this.goodAfterRate = goodAfterRate;
    }

    public String getIndustryCompare() {
        return industryCompare;
    }

    public void setIndustryCompare(String industryCompare) {
        this.industryCompare = industryCompare;
    }

    public String getGoodLink() {
        return goodLink;
    }

    public void setGoodLink(String goodLink) {
        this.goodLink = goodLink;
    }

    public String getStoreLink() {
        return storeLink;
    }

    public void setStoreLink(String storeLink) {
        this.storeLink = storeLink;
    }

    public BigDecimal getDescriptionSituation() {
        return descriptionSituation;
    }

    public void setDescriptionSituation(BigDecimal descriptionSituation) {
        this.descriptionSituation = descriptionSituation;
    }

    public BigDecimal getServiceAttitude() {
        return serviceAttitude;
    }

    public void setServiceAttitude(BigDecimal serviceAttitude) {
        this.serviceAttitude = serviceAttitude;
    }

    public BigDecimal getLogisticsService() {
        return logisticsService;
    }

    public void setLogisticsService(BigDecimal logisticsService) {
        this.logisticsService = logisticsService;
    }

    public String getColorClassification() {
        return colorClassification;
    }

    public void setColorClassification(String colorClassification) {
        this.colorClassification = colorClassification;
    }


    public List<String> getPicPathList() {
        return picPathList;
    }

    public void setPicPathList(List<String> picPathList) {
        this.picPathList = picPathList;
    }

    @Override
    public String toString() {
        return "GoodDetails{" +
                "goodId='" + goodId + '\'' +
                ", goodName='" + goodName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", goodBrand='" + goodBrand + '\'' +
                ", goodPrice='" + goodPrice + '\'' +
                ", monthsaleNum=" + monthsaleNum +
                ", storeAdd='" + storeAdd + '\'' +
                ", goodHot=" + goodHot +
                ", sellerCredit='" + sellerCredit + '\'' +
                ", commentNum=" + commentNum +
                ", ishasLicense=" + ishasLicense +
                ", goodAfterRate='" + goodAfterRate + '\'' +
                ", industryCompare='" + industryCompare + '\'' +
                ", goodLink='" + goodLink + '\'' +
                ", storeLink='" + storeLink + '\'' +
                ", descriptionSituation=" + descriptionSituation +
                ", serviceAttitude=" + serviceAttitude +
                ", logisticsService=" + logisticsService +
                ", colorClassification='" + colorClassification + '\'' +
                ", picPathList=" + picPathList +
                '}';
    }
}

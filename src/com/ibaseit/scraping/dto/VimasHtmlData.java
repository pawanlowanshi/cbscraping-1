package com.ibaseit.scraping.dto;

import java.io.Serializable;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class VimasHtmlData implements Serializable {

	private static final long serialVersionUID = 768894073623054432L;
	private String CaseNum;
	private String Intmid;
	private String receivedDate;
	private String dueDate;
	private String amount;
	private String transactionDate;
	private String reasonCodeDesc;
	private String familyId;
	private String refNum;
	private String cardNumber;
	private String cardType;

	public VimasHtmlData(Elements trow) {
		this.CaseNum = extractCaseNum(trow.get(0));
		this.Intmid = extractIntmid(trow.get(0));
		this.receivedDate = trow.get(3).text();
		this.dueDate = trow.get(4).text();
		this.amount = trow.get(5).text();
		this.transactionDate = trow.get(6).text();
		this.reasonCodeDesc = trow.get(8).text();
		this.familyId = trow.get(10).text();
		this.refNum = trow.get(12).text();

	}
	public String extractCaseNum(Element caseNumStr)
	{
		String strCaseNum = caseNumStr.toString();
		return strCaseNum.substring(strCaseNum.indexOf("CaseNum=")+8, strCaseNum.indexOf("&"));
	}
	
	public String extractIntmid(Element caseNumStr)
	{
		String strCaseNum = caseNumStr.toString();
		return strCaseNum.substring(strCaseNum.indexOf("Intmid=")+7, strCaseNum.indexOf("')"));
		
	}

	public String getCaseNum() {
		return CaseNum;
	}

	public String getIntmid() {
		return Intmid;
	}

	public void setCaseNum(String caseNum) {
		CaseNum = caseNum;
	}

	public void setIntmid(String intmid) {
		Intmid = intmid;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getAmount() {
		return amount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public String getReasonCodeDesc() {
		return reasonCodeDesc;
	}

	public String getFamilyId() {
		return familyId;
	}

	public String getRefNum() {
		return refNum;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public void setReasonCodeDesc(String reasonCodeDesc) {
		this.reasonCodeDesc = reasonCodeDesc;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "VimasHtmlData [CaseNum=" + CaseNum + ", Intmid=" + Intmid
				+ ", receivedDate=" + receivedDate + ", dueDate=" + dueDate
				+ ", amount=" + amount + ", transactionDate=" + transactionDate
				+ ", reasonCodeDesc=" + reasonCodeDesc + ", familyId=" + familyId
				+ ", refNum=" + refNum + ", cardNumber=" + cardNumber + ", cardType="
				+ cardType + "]";
	}

}

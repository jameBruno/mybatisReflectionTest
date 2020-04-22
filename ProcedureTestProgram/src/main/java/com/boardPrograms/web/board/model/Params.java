
package com.boardPrograms.web.board.model;

import java.util.HashMap;
import java.util.List;

public class Params {
	
	@DBtable(columnName = "CAMP_ID")
	private String CampID;
	@DBtable(columnName = "CAMP_STAT_CD")
	private String sWorkSect;
	@DBtable(columnName = "SCENARIO_NAME")
	private String sCallListName;
	@DBtable(columnName = "CAMP_NAME")
	private String sPreNext;
	@DBtable(columnName = "CAMP_NAME")
	private int iSequence;
	@DBtable(columnName = "GRP_VDN")
	private String sFieldName;
	@DBtable(columnName = "GRP_VDN")
	private String sAccount;
	@DBtable(columnName = "TR_NAME")
	private String sText;
	@DBtable(columnName = "CALLLIST_NAME")
	private String sFilterSect;
	private List<AccessVO> result;	
	
	/*
	 * public Params(String campID, String sWorkSect, String sCallListName, String
	 * sPreNext, int iSequence, String sFieldName, String sAccount, String sText,
	 * String sFilterSect) { //super(); this.CampID = campID; this.sWorkSect =
	 * sWorkSect; this.sCallListName = sCallListName; this.sPreNext = sPreNext;
	 * this.iSequence = iSequence; this.sFieldName = sFieldName; this.sAccount =
	 * sAccount; this.sText = sText; this.sFilterSect = sFilterSect; }
	 */
	
	public Params() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCampID() {
		return CampID;
	}
	public void setCampID(String campID) {
		CampID = campID;
	}
	public String getsWorkSect() {
		return sWorkSect;
	}
	public void setsWorkSect(String sWorkSect) {
		this.sWorkSect = sWorkSect;
	}
	public String getsCallListName() {
		return sCallListName;
	}
	public void setsCallListName(String sCallListName) {
		this.sCallListName = sCallListName;
	}
	public String getsPreNext() {
		return sPreNext;
	}
	public void setsPreNext(String sPreNext) {
		this.sPreNext = sPreNext;
	}
	public int getiSequence() {
		return iSequence;
	}
	public void setiSequence(int iSequence) {
		this.iSequence = iSequence;
	}
	public String getsFieldName() {
		return sFieldName;
	}
	public void setsFieldName(String sFieldName) {
		this.sFieldName = sFieldName;
	}
	public String getsAccount() {
		return sAccount;
	}
	public void setsAccount(String sAccount) {
		this.sAccount = sAccount;
	}
	public String getsText() {
		return sText;
	}
	public void setsText(String sText) {
		this.sText = sText;
	}
	public String getsFilterSect() {
		return sFilterSect;
	}
	public void setsFilterSect(String sFilterSect) {
		this.sFilterSect = sFilterSect;
	}
	public List<AccessVO> getResult() {
		return result;
	}
	public void setResult(List<AccessVO> result) {
		this.result = result;
	}
	
	
}

package kr.pe.krh.vo;

import java.util.Date;

public class AttachVO {
	private int ANO;
	private int BNO;
	private int MNO;
	private String UPLOADPATH;
	private String FILENAME;
	private String FILETYPE;
	private Date REGDATE;
	
	public AttachVO() { }
	public AttachVO(int aNO, int bNO, int mNO, String uPLOADPATH, String fILENAME, String fILETYPE, Date rEGDATE) {
		super();
		ANO = aNO;
		BNO = bNO;
		MNO = mNO;
		UPLOADPATH = uPLOADPATH;
		FILENAME = fILENAME;
		FILETYPE = fILETYPE;
		REGDATE = rEGDATE;
	}
	@Override
	public String toString() {
		return "AttachVO [ANO=" + ANO + ", BNO=" + BNO + ", MNO=" + MNO + ", UPLOADPATH=" + UPLOADPATH + ", FILENAME="
				+ FILENAME + ", FILETYPE=" + FILETYPE + ", REGDATE=" + REGDATE + "]";
	}
	
	public int getANO() {
		return ANO;
	}
	public void setANO(int aNO) {
		ANO = aNO;
	}
	public int getBNO() {
		return BNO;
	}
	public void setBNO(int bNO) {
		BNO = bNO;
	}
	public int getMNO() {
		return MNO;
	}
	public void setMNO(int mNO) {
		MNO = mNO;
	}
	public String getUPLOADPATH() {
		return UPLOADPATH;
	}
	public void setUPLOADPATH(String uPLOADPATH) {
		UPLOADPATH = uPLOADPATH;
	}
	public String getFILENAME() {
		return FILENAME;
	}
	public void setFILENAME(String fILENAME) {
		FILENAME = fILENAME;
	}
	public String getFILETYPE() {
		return FILETYPE;
	}
	public void setFILETYPE(String fILETYPE) {
		FILETYPE = fILETYPE;
	}
	public Date getREGDATE() {
		return REGDATE;
	}
	public void setREGDATE(Date rEGDATE) {
		REGDATE = rEGDATE;
	}
	
	
			
	
	
}

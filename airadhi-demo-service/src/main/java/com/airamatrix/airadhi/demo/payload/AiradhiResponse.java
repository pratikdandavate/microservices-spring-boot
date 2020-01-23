/**
 * 
 */
package com.airamatrix.airadhi.demo.payload;

/**
 * @author Jaikishan Gurav
 *
 */
public class AiradhiResponse {

    private int id;

    private String title;

    private String eplProjectNo;

    private String eplAccessionNo;

    private String sacrifice;

    private String stain;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getEplProjectNo() {
	return eplProjectNo;
    }

    public void setEplProjectNo(String eplProjectNo) {
	this.eplProjectNo = eplProjectNo;
    }

    public String getEplAccessionNo() {
	return eplAccessionNo;
    }

    public void setEplAccessionNo(String eplAccessionNo) {
	this.eplAccessionNo = eplAccessionNo;
    }

    public String getSacrifice() {
	return sacrifice;
    }

    public void setSacrifice(String sacrifice) {
	this.sacrifice = sacrifice;
    }

    public String getStain() {
	return stain;
    }

    public void setStain(String stain) {
	this.stain = stain;
    }
}

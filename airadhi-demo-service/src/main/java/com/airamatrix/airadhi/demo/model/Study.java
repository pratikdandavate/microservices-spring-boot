/**
 * 
 */
package com.airamatrix.airadhi.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Jaikishan Gurav
 *
 */
@Entity
@Table(name = "study")
public class Study {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_pk", unique = true, nullable = false)
    private int studyPk;

    @Column(length = 256)
    private String title;

    @Column(name = "epl_project_no", length = 256)
    private String eplProjectNo;

    @Column(name = "epl_accession_no", length = 256)
    private String eplAccessionNo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_timestamp")
    private Date createTimestamp;

    @Column(name = "sacrifice", length = 256)
    private String sacrifice;

    @Column(name = "stain", length = 256)
    private String stain;

    public int getStudyPk() {
	return studyPk;
    }

    public void setStudyPk(int studyPk) {
	this.studyPk = studyPk;
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

    public Date getCreateTimestamp() {
	return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
	this.createTimestamp = createTimestamp;
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

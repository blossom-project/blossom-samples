package com.blossomproject.sample.generated;

import java.math.BigDecimal;
import java.util.Date;
import com.blossomproject.core.common.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "SampleEntity")
@Table(name = "sample_entity")
public class SampleEntity
    extends AbstractEntity
{
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "description")
    @Lob
    private String description;
    @Column(name = "bigdecimalfield")
    private BigDecimal bigDecimalField;
    @Column(name = "blobfield")
    @Lob
    private Byte[] blobField;
    @Column(name = "booleanfield")
    private Boolean booleanField;
    @Column(name = "datefield")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateField;
    @Column(name = "enumfield")
    @Enumerated(EnumType.STRING)
    private EnumFieldValues enumField;
    @Column(name = "integerfield")
    private Integer integerField;
    @Column(name = "localdatetimefield")
    @Temporal(TemporalType.TIMESTAMP)
    private Date localDateTimeField;
    @Column(name = "longfield")
    private Long longField;
    @Column(name = "stringfield", length = 255)
    private String stringField;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBigDecimalField() {
        return bigDecimalField;
    }

    public void setBigDecimalField(BigDecimal bigDecimalField) {
        this.bigDecimalField = bigDecimalField;
    }

    public Byte[] getBlobField() {
        return blobField;
    }

    public void setBlobField(Byte[] blobField) {
        this.blobField = blobField;
    }

    public Boolean getBooleanField() {
        return booleanField;
    }

    public void setBooleanField(Boolean booleanField) {
        this.booleanField = booleanField;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    public EnumFieldValues getEnumField() {
        return enumField;
    }

    public void setEnumField(EnumFieldValues enumField) {
        this.enumField = enumField;
    }

    public Integer getIntegerField() {
        return integerField;
    }

    public void setIntegerField(Integer integerField) {
        this.integerField = integerField;
    }

    public Date getLocalDateTimeField() {
        return localDateTimeField;
    }

    public void setLocalDateTimeField(Date localDateTimeField) {
        this.localDateTimeField = localDateTimeField;
    }

    public Long getLongField() {
        return longField;
    }

    public void setLongField(Long longField) {
        this.longField = longField;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public enum EnumFieldValues {
        first,second, }
}

package com.blossomproject.sample.generated;

import java.math.BigDecimal;
import java.util.Date;
import com.blossomproject.core.common.dto.AbstractDTO;
import com.blossomproject.sample.generated.SampleEntity.EnumFieldValues;

public class SampleEntityDTO
    extends AbstractDTO
{
    private String name;
    private String description;
    private BigDecimal bigDecimalField;
    private Byte[] blobField;
    private Boolean booleanField;
    private Date dateField;
    private EnumFieldValues enumField;
    private Integer integerField;
    private Date localDateTimeField;
    private Long longField;
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
}

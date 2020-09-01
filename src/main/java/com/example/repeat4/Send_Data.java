
//This class takes the json data as input



package com.example.repeat4;

public class Send_Data {

private String product;
private String type;
private EncodeInfo [] encodeinfo;

public EncodeInfo[] getEncodeinfo() {
	return encodeinfo;
}
public void setEncodeinfo(EncodeInfo[] encodeinfo) {
	this.encodeinfo = encodeinfo;
}
public String getProduct() {
	return product;
}
public void setProduct(String product) {
	this.product = product;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

}
/*
public class Send_Data
{
    private String product;

    private EncodeInfo[] encodeInfo;

    private String type;

    public String getProduct ()
    {
        return product;
    }

    public void setProduct (String product)
    {
        this.product = product;
    }

    public EncodeInfo[] getEncodeInfo ()
    {
        return encodeInfo;
    }

    public void setEncodeInfo (EncodeInfo[] encodeInfo)
    {
        this.encodeInfo = encodeInfo;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

   
}*/
			
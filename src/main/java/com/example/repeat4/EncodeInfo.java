//Main json data that will be displayed


package com.example.repeat4;

public class EncodeInfo {
private String key;
private String value;
private String data_type;
public String getData_type() {
	return data_type;
}
public void setData_type(String data_type) {
	this.data_type = data_type;
}
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}

}
/*
public class EncodeInfo
{
    private String value;

    private String key;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+", key = "+key+"]";
    }
}*/

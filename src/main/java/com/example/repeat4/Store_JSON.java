
//Entity class for JSON storage




package com.example.repeat4;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Store_JSON {
@Id
private long key_gen;
private long client_id;
@Column(length=1000000)
private String json_data;
@Column(length=1000000)
private byte [] qrcode;
public long getClient_id() {
	return client_id;
}
public void setClient_id(long client_id) {
	this.client_id = client_id;
}
public long getKey_gen() {
	return key_gen;
}
public byte[] getQrcode() {
	return qrcode;
}
public void setQrcode(byte[] qrcode) {
	this.qrcode = qrcode;
}
public void setKey_gen(long key_gen) {
	this.key_gen = key_gen;
}
public String getJson_data() {
	return json_data;
}
public void setJson_data(String json_data) {
	this.json_data = json_data;
}

}

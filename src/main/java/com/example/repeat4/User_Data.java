
//This class takes all the information that is required to send mail

package com.example.repeat4;
public class User_Data {
private long key_gen;
private String email;
private long client_id;
public long getClient_id() {
	return client_id;
}
public void setClient_id(long client_id) {
	this.client_id = client_id;
}
public long getKey_gen() {
	return key_gen;
}
public void setKey_gen(long key_gen) {
	this.key_gen = key_gen;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}

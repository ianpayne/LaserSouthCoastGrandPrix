package com.idp.grandprix.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable{
	private String id;
	private String name;
	private String email;
	private String phone;
	
	public Contact(String name, String email, String phone) {
		this.id = "";
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public Contact(String id, String name, String email, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public Contact(Parcel in) {
		//Log.v("Contact PARCEL", "read from Parcel...");
		id = in.readString();
		name = in.readString();
		email = in.readString();
		phone = in.readString();
		//Log.v("Contact PARCEL", "read from Parcel...DONE");
	}
	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getEmail(){
		return email;
	}
	public String getPhone(){
		return phone;
	}
	
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel desc, int flags) {
		/*
		desc.writeStringArray(new String[] {
				getName(),
				getEmail(),
				getPhone()
		});
		*/
		//Log.v("Contact PARCEL", "writeToParcel..."+ flags);
		desc.writeString(id);
		desc.writeString(name);
		desc.writeString(email);
		desc.writeString(phone);
		//Log.v("Contact PARCEL", "writeToParcel...DONE"+ flags);
	}
	
	public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
		public Contact createFromParcel(Parcel in) {
			return new Contact(in); 
		}
		
		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};

}

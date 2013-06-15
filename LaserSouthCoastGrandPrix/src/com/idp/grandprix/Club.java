package com.idp.grandprix;

import android.location.Address;
import android.os.Parcel;
import android.os.Parcelable;

public class Club implements Parcelable {
	protected String id;
	protected String name;
	protected Address address;
	protected String email;
	protected String flag;
	protected String website;

	public Club( String name, Address address, String email, String flag, String website){
		this.name = name;
		this.address = address;
		this.email = email;
		this.flag = flag;
		this.website = website;
	}
	
	public Club(String id, String name, Address address, String email, String flag, String website){
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.flag = flag;
		this.website = website;
	}
	
	public Club(Parcel in) {
		//Log.v("Club PARCEL", "read from Parcel...");
		id = in.readString();
		name = in.readString();
		email = in.readString();
		flag = in.readString();
		website = in.readString();
		
		address = in.readParcelable(Address.class.getClassLoader());
		//Log.v("Club PARCEL", "read from Parcel...DONE");
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
	public Address getAddress(){
		return address;
	}
	public String getFlag(){
		return flag;
	}
	public String getWebsite(){
		return website;
	}	
	
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel desc, int flags) {
		//Log.v("Club PARCEL", "writeToParcel..."+ flags);
		desc.writeString(id);
		desc.writeString(name);
		desc.writeString(email);
		desc.writeString(flag);
		desc.writeString(website);
		
		desc.writeParcelable(getAddress(), flags);
		//Log.v("Club PARCEL", "writeToParcel..."+ flags);
	}
	
	public static final Parcelable.Creator<Club> CREATOR = new Parcelable.Creator<Club>() {
		public Club createFromParcel(Parcel in) {
			return new Club(in); 
		}
		
		public Club[] newArray(int size) {
			return new Club[size];
		}
	};


}

package com.idp.grandprix.model;

import android.location.Address;
import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable 
{
	private String id;
	private String date;
	private String time;
	private String resultsUrl;
	private String norUrl;
	private String siUrl;
	private String entryFormUrl;
	
	private Club club;
	private Contact contact;
	private Address address;
		
	
	public Event(Club club, Address address, Contact contact, String date, String time, String norUrl, String siUrl, String entryFormUrl, String resultsUrl){
		super();
		this.date = date;
		this.time = time;
		this.resultsUrl = resultsUrl;
		this.norUrl = norUrl;
		this.siUrl = siUrl;
		this.entryFormUrl = entryFormUrl;		
		this.club = club;
		this.contact = contact;
		this.address = address;
		this.club = club;
	}
	
	public Event(String id, Club club, Address address, Contact contact, String date, String time, String norUrl, String siUrl, String entryFormUrl, String resultsUrl){
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.resultsUrl = resultsUrl;
		this.norUrl = norUrl;
		this.siUrl = siUrl;
		this.entryFormUrl = entryFormUrl;		
		this.club = club;
		this.contact = contact;
		this.address = address;
		this.club = club;
	}
	
	public Event(Parcel in) {
		//Log.v("EVENT PARCEL", "read from Parcel...");
		this.id = in.readString();
		this.date = in.readString();
		this.time = in.readString();
		this.resultsUrl = in.readString();
		this.norUrl = in.readString();
		this.siUrl = in.readString();
		this.entryFormUrl = in.readString();	
		
		address = in.readParcelable(Address.class.getClassLoader());
		contact = in.readParcelable(Contact.class.getClassLoader());
		club = in.readParcelable(Club.class.getClassLoader());
		
		//Log.v("Event PARCEL", "read from Parcel...DONE");
	}
	public String getId(){
		return id;
	}	
	public String getDate(){
		return date;
	}
	public String getTime(){
		return time;
	}
	public String getResultsUrl(){
		return resultsUrl;
	}
	public String getNorUrl(){
		return norUrl;
	}
	public String getSiUrl(){
		return siUrl;
	}
	public String getEntryFormUrl(){
		return entryFormUrl;
	}
	public Address getAddress(){
		return address;
	}
	public Club getClub(){
		return club;
	}
	public Contact getContact(){
		return contact;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel desc, int flags) {
		//Log.v("EVENT PARCEL", "writeToParcel..."+ flags);
		desc.writeString(id);
		desc.writeString(date);
		desc.writeString(time);
		desc.writeString(resultsUrl);
		desc.writeString(norUrl);
		desc.writeString(siUrl);
		desc.writeString(entryFormUrl);	
		
		desc.writeParcelable(getAddress(), flags);
		desc.writeParcelable(getContact(), flags);
		desc.writeParcelable(getClub(), flags);
		//Log.v("EVENT PARCEL", "writeToParcel...DONE"+ flags);
	}
	
	public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
		public Event createFromParcel(Parcel in) {
			return new Event(in); 
		}
		
		public Event[] newArray(int size) {
			return new Event[size];
		}
	};
	
}


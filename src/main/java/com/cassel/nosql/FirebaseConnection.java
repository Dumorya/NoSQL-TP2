package com.cassel.nosql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class FirebaseConnection
{
	public FirebaseConnection() {
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("no-sql-tp2-firebase-adminsdk-n6poo-05be20c436.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		FirebaseOptions options = null;
		try {
			options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			  .setDatabaseUrl("https://no-sql-tp2.firebaseio.com/")
			  .build();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FirebaseApp.initializeApp(options);
	}
	
	public DatabaseReference getDbReference() {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		return database.getReference("");
	}
	
	public String addUser(User user) {
		DatabaseReference usersRef = this.getDbReference().child("currentUsers");
		DatabaseReference pushedUserRef = usersRef.push();
		pushedUserRef.setValueAsync(user);
		return pushedUserRef.getKey();
	}
	
}

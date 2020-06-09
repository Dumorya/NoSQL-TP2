package com.cassel.nosql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInteractions
{
	private FirebaseConnection dbManager;
	private User currentUser;
	Scanner sc = Singleton.getInstance().getScanner();
	
	public UserInteractions(FirebaseConnection dbManager)
	{
		this.dbManager = dbManager;
	}
	
	public void run()
	{
		while(true)
		{
			getUserChoice();
		}
	}
	
	private void getUserChoice()
	{
		String choice = sc.nextLine();
		
		try
		{	
			if(choice == "/log")
			{
				this.connectUser(choice);
			}
			
		}
		catch(IllegalArgumentException e)
		{
			//this.help();
		}
	}
	
	
	private void connectUser(String input) {
		String[] args = input.split(" ");
		
		if(args.length < 2)
		{
			throw new IllegalArgumentException();
		}
		
		if(this.currentUser != null)
		{
			System.out.println(this.currentUser + "est connecté.");
			return;
		}
		
		String username = args[1];
		
		this.currentUser = new User(username);
		
		String newKey = this.dbManager.addUser(this.currentUser);
		this.currentUser.setKey(newKey);
		
		System.out.println("Bienvenue " + this.currentUser + " !");
	}
}

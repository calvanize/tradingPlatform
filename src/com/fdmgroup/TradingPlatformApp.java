package com.fdmgroup;

import java.util.List;

import com.fdmgroup.controller.Controller;
import com.fdmgroup.controller.GuestController;
import com.fdmgroup.controller.ShareHolderController;
import com.fdmgroup.view.GuestView;
import com.fdmgroup.view.ShareHolderView;
import com.fdmgroup.view.View;

public class TradingPlatformApp {
	
	public static void main(String[] args) {
		View view = new GuestView();
		Controller controller = new GuestController(view);
		controller.run();
		List <Integer> roles = controller.getUser().getRoleID();
		if (roles.size() == 1 )
		{
			switch( roles.get(0))
			{
			case 1:
				view = new ShareHolderView();
				controller = new ShareHolderController(view, controller.getUser());
				break;
			}
			controller.run();
		}
		/*for (Integer integer : roles) {
			switch
		}*/
		
	}

}

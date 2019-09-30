package com.fdmgroup.model.test;

import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.model.Company;
import com.fdmgroup.model.Person;
import com.fdmgroup.model.Request;

public class RequestTest {
	
	private Request testRequest;
	private Request request2;
	Request requestConstructor;
	private Person user;
	private Calendar date;
	private Company company;
	private Request request3;
	

	@Before
	public void setUp() throws Exception {
	
		user = new Person("Calvanize");
		date = Calendar.getInstance();
		company = new Company();
		request2 = new Request(2, null, 0, user, date, "SELL", "ACTIVE",
				company, 35, 35, "IMMEDIATE OR CANCEL", 34.3, 0);
	
		testRequest = new Request(2, null, 0, user, date, "BUY", "ACTIVE",
				company, 37, 10, "DAY ONLY", 34.3, 0);
		
		request3 = new Request(2, null, 0, user, date, "BUY", "ACTIVE",
				company, 37, 10, "GOOD UNTIL CANCELLED", 34.3, 0);

	}
	
	@Test
	public void testRequestConstructor_for_legal_sharesFilled_argument_0_() {
		requestConstructor = new Request(1, null, 0, user, date, "BUY", "ACTIVE", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRequestConstructor_for_Illegal_sharesFilled_argument_3_() {
		requestConstructor = new Request(1, null, 3, user, date, "BUY", "ACTIVE", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}
	
	@Test
	public void testRequestConstructor_for_legal_parentRequest_argument_testRequest_() {
		requestConstructor = new Request(1, testRequest, 0, user, date, "BUY", "ACTIVE", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}
	
	@Test
	public void testRequestConstructor_for_legal_parentRequest_argument_null_() {
		requestConstructor = new Request(1, null, 0, user, date, "BUY", "ACTIVE", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRequestConstructor_for_Illegal_parentRequest_argument_request2_() {
		requestConstructor = new Request(1, request2, 0, user, date, "BUY", "ACTIVE", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}
	
	@Test
	public void testRequestConstructor_for_legal_requestType_argument_BUY_() {
		requestConstructor = new Request(1, null, 0, user, date, "BUY", "ACTIVE", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}
	@Test
	public void testRequestConstructor_for_legal_requestType_argument_SELL_() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRequestConstructor_for_Illegal_requestType_argument_BUY_MORE_() {
		requestConstructor = new Request(1, null, 0, user, date, "BUY MORE", "ACTIVE", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}
	
	@Test
	public void testRequestConstructor_for_Legal_requestStatus_argument_ACTIVE_() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRequestConstructor_for_Illegal_requestStatus_argument_PARTIAL_() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "PARTIAL", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}
	
	@Test
	public void testRequestConstructor_for_Legal_timeInForce_argument_GOOD_UNTIL_CANCELLED_() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 8, "GOOD UNTIL CANCELLED", 58.8, 0);
	}
	
	@Test
	public void testRequestConstructor_for_Legal_timeInForce_argument_IMMEDIATE_OR_CANCEL_with_legal_minShares_argument() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 89, "IMMEDIATE OR CANCEL", 58.8, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRequestConstructor_for_Legal_timeInForce_argument_IMMEDIATE_OR_CANCEL_with_Illegal_minShares_argument() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 8, "IMMEDIATE OR CANCEL", 58.8, 0);
	}
	
	@Test
	public void testRequestConstructor_for_Legal_timeInForce_argument_DAY_ONLY_() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 8, "DAY ONLY", 58.8, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRequestConstructor_for_ILLegal_timeInForce_argument_DAY_ONLY1_() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 8, "DAY ONLY1", 58.8, 0);
	}
	
	@Test
	public void testRequestConstructor_for_Legal_limitPriceANDstopPrice_argument__89_0() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 8, "DAY ONLY", 89, 0);
	}
	
	@Test
	public void testRequestConstructor_for_Legal_limitPriceANDstopPrice_argument__0_45() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 8, "DAY ONLY", 0, 45);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRequestConstructor_for_Legal_limitPriceANDstopPrice_argument__0_0() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 8, "DAY ONLY", 0, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRequestConstructor_for_Legal_limitPriceANDstopPrice_argument__4_45() {
		requestConstructor = new Request(1, null, 0, user, date, "SELL", "ACTIVE", company, 89, 8, "DAY ONLY", 4, 45);
	}
	
	

	@Test
	public void testSetSharesFilled_for_legalArgument_12_() {
		testRequest.setSharesFilled(12);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSharesFilled_for_illegalArgument_3_lessThanTheMinimumShares_() {
		testRequest.setSharesFilled(3);
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSharesFilled_for_illegalArgument_80_GreaterThanTheShares() {
		testRequest.setSharesFilled(80);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSharesFilled_for_CANCELLED_REQUESTS_() {
		testRequest.setRequestStatus("CANCELLED");
		testRequest.setSharesFilled(80);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetSharesFilled_for_COMPLETED_REQUESTS_() {
		testRequest.setSharesFilled(37);
		testRequest.setRequestStatus("COMPLETED");
		testRequest.setSharesFilled(80);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testSetRequestStatus_ToUpdateStatusToCompletedWhenSharesIsNotTotallyFilled_ForImmediateOrCancelRequest() {
		request2.setRequestStatus("COMPLETED");
	}
	
	@Test
	public void testSetRequestStatus_ToUpdateStatusToCancelled_ForImmediateOrCancelRequest() {
		request2.setRequestStatus("CANCELLED");
	}
	
	@Test
	public void testSetRequestStatus_ToUpdateStatusToCompletedWhenSharesIsTotallyFilled_35_ForImmediateOrCancelRequest() {
		request2.setSharesFilled(35);
		request2.setRequestStatus("COMPLETED");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetRequestStatus_ToUpdateStatusToPatiallyFilled_ForImmediateOrCancelRequest() {
		request2.setRequestStatus("PARTIAL");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetRequestStatus_ToUpdateStatusFromActiveToCompleted_ForGoodUntilCancelledRequest_WhenSharesIsNotCompletelyFilled() {
		request3.setRequestStatus("COMPLETED");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetRequestStatus_ToUpdateStatusFromPartialFilledToCompleted_ForGoodUntilCancelledRequest_WhenSharesIsNotCompletelyFilled() {
		request3.setSharesFilled(15);
		request3.setRequestStatus("PARTIAL");
		request3.setRequestStatus("COMPLETED");
	}
	
	@Test
	public void testSetRequestStatus_ToUpdateStatusFromActiveToCompleted_ForGoodUntilCancelledRequest_WhenSharesIsCompletelyFilled() {
		request3.setSharesFilled(37);
		request3.setRequestStatus("COMPLETED");
	
	}
	
	@Test
	public void testSetRequestStatus_ToUpdateStatusFromPartialToCompleted_ForGoodUntilCancelledRequest_WhenSharesIsCompletelyFilled() {
		request3.setSharesFilled(30);
		request3.setRequestStatus("PARTIAL");
		request3.setSharesFilled(37);
		request3.setRequestStatus("COMPLETED");
	}
	
	@Test
	public void testSetRequestStatus_ToUpdateStatusFromPartiallyFilledToCompleted_ForDayOnly_WhenTimeRunOutAtCloseOfDay() {
		testRequest.setSharesFilled(31);
		testRequest.setRequestStatus("PARTIAL");
		testRequest.setRequestStatus("COMPLETED");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetRequestStatus_ToUpdateStatusFromActiveToCompleted_ForDayOnly_WhenTimeRunOutAtCloseOfDay() {
		testRequest.setRequestStatus("COMPLETED");
	}
	
	
	@Test
	public void testSetRequestStatus_ToUpdateStatusFromActiveToPariallyFilled_WhenSharesFilledIsLessThanShares() {
		request3.setSharesFilled(31);
		request3.setRequestStatus("PARTIAL");
		//request3.setRequestStatus("PARTIAL");
	}
	
	
	@Test
	public void testSetRequestStatus_ToUpdateStatusFromPartialToPariallyFilled_WhenSharesFilledIsLessThanShares() {
		request3.setSharesFilled(31);
		request3.setRequestStatus("PARTIAL");
		request3.setSharesFilled(35);
		request3.setRequestStatus("PARTIAL");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetRequestStatus_ToUpdateStatusFromActiveToPariallyFilled_WhenSharesFilledIsEqualToShares() {
		request3.setSharesFilled(37);
		request3.setRequestStatus("PARTIAL");
		//request3.setSharesFilled(35);
		//request3.setRequestStatus("PARTIAL");
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetRequestStatus_ToUpdateStatusFromPartialToPariallyFilled_WhenSharesFilledIsEqualToShares() {
		request3.setSharesFilled(35);
		request3.setRequestStatus("PARTIAL");
		request3.setSharesFilled(37);
		request3.setRequestStatus("PARTIAL");
	}
	
	
	@Test
	public void testSetRequestStatus_ToUpdateStatusToCancelled_() {
		request3.setRequestStatus("CANCELLED");
	}
	
	
	@Test
	public void testSetRequestStatus_ToUpdateStatusToCompleted_() {
		request3.setSharesFilled(37);
		request3.setRequestStatus("COMPLETED");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetRequestStatus_ToUpdateStatusToIllegalStatus_() {
		request3.setSharesFilled(37);
		request3.setRequestStatus("NOTANYSTATUS");
	}
	
	
	/*
	@Test
	public void testSetMinimumShares() {
		fail("Not yet implemented"); // TODO
	}
*/
}

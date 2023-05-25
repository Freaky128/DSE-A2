package unisa.dse.a2.students;

import java.util.HashMap;
import java.util.Scanner;

import unisa.dse.a2.interfaces.ListGeneric;

public class SecuritiesExchange {

	/**
	 * Exchange name
	 */
	private String name;
	
	public String getName() {
		return name;
	}
	
	/**
	 * List of brokers on this exchange
	 */
	public ListGeneric<StockBroker> brokers;
	
	/**
	 * List of announcements of each trade processed
	 */
	public ListGeneric<String> announcements;
	
	/**
	 * HashMap storing the companies, stored based on their company code as the key
	 */
	public HashMap<String, ListedCompany> companies;

	/**
	 * Initialises the exchange ready to handle brokers, announcements, and companies
	 * @param name
	 */
	public SecuritiesExchange(String name) {
		this.name = name;
		brokers = new DSEListGeneric<StockBroker>();
		announcements = new DSEListGeneric<String>();
		companies = new HashMap<String, ListedCompany>();
	}
	
	/**
	 * Adds the given company to the list of listed companies on the exchange
	 * @param company
	 * @return true if the company was added, false if it was not
	 */
	public boolean addCompany(ListedCompany company) {
		if (company == null || companies.containsKey(company.getCode())) { // Checks if company is null or if it's already listed
			return false;
		}
		else {
			companies.put(company.getCode(), company);
			return true;
		}
	}

	/**
	 * Adds the given broker to the list of brokers on the exchange
	 * @param company
	 */
	public boolean addBroker(StockBroker broker) {
		if (broker == null || brokers.contains(broker)) { // Checks if the broker is null or if it's already added
			return false;
		}
		else {
			brokers.add(broker);
			return true;
		}
	}
	
	/**
	 * Process the next trade provided by each broker, processing brokers starting from index 0 through to the end
	 * 
	 * If the exchange has three brokers, each with trades in their queue, then three trades will processed, one from each broker.
	 * 
	 * If a broker has no pending trades, that broker is skipped
	 * 
	 * Each processed trade should also make a formal announcement of the trade to the announcements list in the form a string:
	 * "Trade: QUANTITY COMPANY_CODE @ PRICE_BEFORE_TRADE via BROKERNAME", 
	 * e.g. "Trade: 100 DALL @ 99 via Honest Harry Broking" for a sale of 100 DALL shares if they were valued at $99
	 * Price shown should be the price of the trade BEFORE it's processed. Each trade should add its announcement at 
	 * the end of the announcements list
	 * 
	 * @return The number of successful trades completed across all brokers
	 * @throws UntradedCompanyException when traded company is not listed on this exchange
	 */
	public int processTradeRound() throws UntradedCompanyException{
		StockBroker broker = null;
		ListedCompany company = null;
		int currentPrice = 0;
		int count = 0; // Number of trades completed
		
		for (int i = 0; i < brokers.size(); i++) { // Cycles brokers
			broker = brokers.get(i); // Gets the next broker
			Trade t = broker.getNextTrade(); // Gets the brokers trade
			
			if (t != null) { // Checks if the broker actually has a next trade
				company = companies.get(t.getCompanyCode()); // Gets the company associated with the trade
				if (company != null) { // Checks the company is listed
					currentPrice = company.getCurrentPrice(); // Gets the share price before the trade
					company.processTrade(t.getShareQuantity());
					
					announcements.add("Trade: " + t.getShareQuantity() + " " + t.listedCompanyCode + " @ " + currentPrice + " via " + broker.getName());
					count++; // Increments successful trade count
				}
				else {
					throw new UntradedCompanyException(t.getCompanyCode());
				}
			}
		}
		
		return count;
	}
	
	// What is up with testList_CopyConstructor and testStockBroker_GetNextTrade.
	// TestStockBroker_GetNextTrade is straight out a copy of testStockBroker_GetPendingTradeCount
	
	public int runCommandLineExchange(Scanner sc)
	{
		return 0;
	}
}

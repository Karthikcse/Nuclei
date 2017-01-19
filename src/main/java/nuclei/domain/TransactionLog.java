/**
 * 
 */
package nuclei.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Karthikeyan
 *
 */

public class TransactionLog extends Entity {

	private String date_time_executed;
	private String result;
	private String response_payload;
	private String run_no;
	private boolean isDeleted;

	public TransactionLog() {
		
	}

	public TransactionLog(String date_time_executed, String result, String response_payload,String run_no, boolean isDeleted) {
		this.date_time_executed = date_time_executed;
		this.result = result;
		this.response_payload = response_payload;
		this.run_no=run_no;
		this.isDeleted = isDeleted;
	}

	public String getDate_time_executed() {
		return date_time_executed;
	}

	public void setDate_time_executed(String date_time_executed) {
		this.date_time_executed = date_time_executed;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResponse_payload() {
		return response_payload;
	}

	public void setResponse_payload(String response_payload) {
		this.response_payload = response_payload;
	}

	public String getRun_no() {
		return run_no;
	}

	public void setRun_no(String run_no) {
		this.run_no = run_no;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}	
}
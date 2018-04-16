package utils;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class ProgressUtil {
	
	private static ProgressUtil progress;
	
	private long totalCommits = 0;
	private Set<String> visitedCommits = new HashSet<>();
	private long lastUpdate = 0;
	
	
	private ProgressUtil(){}
	
	public static ProgressUtil getInstance(){
		if(progress == null){
			progress = new ProgressUtil();
		}
		return progress;
	}
	
	private void setTotalCommits(long totalCommits){
		if (this.totalCommits == 0) {
			this.totalCommits = totalCommits;
		}
	}
	
	private void visit(String commitHash) {
		visitedCommits.add(commitHash);
	}
	
	private void showProgress(Calendar date) {
		if (visitedCommits.size() > lastUpdate) {
			System.out.print("\nVisiting commit of "
					+ Utils.formatCalendar(date) + " #" + visitedCommits.size()
					+ " out of " + totalCommits);
			lastUpdate = visitedCommits.size();
		} else {
			System.out.print(".");
		}
	}
	
	public void updateProgress(Calendar date, long totalCommits, String commitHash){
		setTotalCommits(totalCommits);
		visit(commitHash);
		showProgress(date);
	}
	
	public void resetProgress(){
		 lastUpdate = 0;
		 totalCommits = 0;
		 visitedCommits.clear();
	}
}

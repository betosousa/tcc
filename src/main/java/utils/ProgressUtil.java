package utils;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class ProgressUtil {
	
	private static ProgressUtil progress;
	
	private long totalCommits = 0;
	private Set<String> visitedCommits = new HashSet<>();
	private long lastUpdate = 0;
	private Calendar date;
	
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
	
	private void visit(String commitHash, Calendar commitDate) {
		visitedCommits.add(commitHash);
		date = commitDate;
	}
	
	private void showProgress() {
		if (visitedCommits.size() > lastUpdate) {
			System.out.print("\nVisiting commit of "
					+ getActualCommitDate() + " #" + visitedCommits.size()
					+ " out of " + totalCommits);
			lastUpdate = visitedCommits.size();
		} else {
			System.out.print(".");
		}
	}
	
	public void updateProgress(Calendar date, long totalCommits, String commitHash){
		setTotalCommits(totalCommits);
		visit(commitHash, date);
		showProgress();
	}
	
	public void resetProgress(){
		 lastUpdate = 0;
		 totalCommits = 0;
		 visitedCommits.clear();
	}
	
	public String getActualCommitDate(){
		if (date != null) {
			return Utils.formatCalendar(date);
		} else {
			return Strings.NO_COMMIT_DATE_LOG;
		}
	}
}

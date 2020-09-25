package at.technikumwien;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Statistics {
	private DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics();
	
	public Statistics(int... numbers) {
		if (numbers != null) {
			for (int number : numbers) {
				descriptiveStatistics.addValue(number);
			}
		}
	}
	
	public long cnt() {
		return descriptiveStatistics.getN();
	}
	
	public long sum() {
		return (long) descriptiveStatistics.getSum();
	}
	
	public double avg() {
		return descriptiveStatistics.getMean();
	}
}

package HW2;

public class Interval {

	private TimePoint beginTime;
	private TimePoint endTime;

	public Interval(TimePoint beginTime, TimePoint endTime) {
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	public TimePoint getBeginTime() {
		return this.beginTime;
	}

	public TimePoint getEndTime() {
		return this.endTime;
	}

}

package BorrowedSoftware;
//source: https://github.com/AvansTi/TI1.2-BoeBot-Library/blob/master/TI/Timer.java

public class Timer
{
    private int interval;
    private long lasttick;

    /**
     * Creates the timer, and sets it to fire after the specified interval
     * @param interval The interval, in milliseconds
     */
    public Timer(int interval)
    {
        this.interval = interval;
        this.lasttick = System.currentTimeMillis();
    }

    /**
     * Used to indicate if this timer has passed the interval. If the interval has been passed twice, calling this method twice will return true multiple times
     * @return whether or not the timeout has passed
     */
    public boolean timeout()
    {
        long currentTick = System.currentTimeMillis();
        while(currentTick > lasttick + interval)
        {
            lasttick += interval;
            return true;
        }
        return false;
    }

    /**
     * Reset the timer
     */
    public void mark()
    {
        this.lasttick = System.currentTimeMillis();
    }

    /**
     * Sets the interval to another time, and reset the timer
     * @param interval the new interval, in milliseconds
     */
    public void setInterval(int interval)
    {
        this.interval = interval;
        this.lasttick = System.currentTimeMillis();
    }

}

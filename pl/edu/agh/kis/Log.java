package pl.edu.agh.kis;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Implementation of Logger able to print logs to any <b>OutputStream</b>.
 *
 * Format of log is [ty of log] [date] -- [log message]
 */
public class Log implements Logger
{
    /**
     * Determines what level of logs will be printed
     */
    private Level logRange;

    /**
     * Holds reference to PrintWriter that writes to output stream defined in constructor
     */
    private PrintWriter out;

    /**
     * Constructor of Log
     *
     * @param out output stream
     * @param logRange level from which logs should be saved
     */
    public Log(OutputStream out, Level logRange)
    {
        try
        {
            this.out = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
            this.logRange = logRange;
        }
        catch(Exception e)
        {
            System.out.println("Cannot init logging output");
            e.printStackTrace();
        }
    }

    @Override
    public void trace(String lineToLog)
    {
        out.println("TRACE " + new Date() + " -- " + lineToLog);
    }

    @Override
    public void debug(String lineToLog)
    {
        out.println("DEBUG " + new Date() + " -- " + lineToLog);
    }

    @Override
    public void info(String lineToLog)
    {
        out.println("INFO " + new Date() + " -- " + lineToLog);
    }

    @Override
    public void warn(String lineToLog)
    {
        out.println("WARN " + new Date() + " -- " + lineToLog);
    }

    @Override
    public void error(String lineToLog)
    {
        out.println("ERROR " + new Date() + " -- " + lineToLog);
    }

    @Override
    public void fatal(String lineToLog)
    {
        out.println("FATAL " + new Date() + " -- " + lineToLog);
    }

    @Override
    public void log(Level lvl, String lineToLog)
    {
        if(logRange.compareTo(lvl) <= 0 && Level.TRACE.compareTo(lvl) == 0)
        {
            trace(lineToLog);
        }
        else if(logRange.compareTo(lvl) <= 0 && Level.DEBUG.compareTo(lvl) == 0)
        {
            debug(lineToLog);
        }
        else if(logRange.compareTo(lvl) <= 0 && Level.INFO.compareTo(lvl) == 0)
        {
            info(lineToLog);
        }
        else if(logRange.compareTo(lvl) <= 0 && Level.WARN.compareTo(lvl) == 0)
        {
            warn(lineToLog);
        }
        else if(logRange.compareTo(lvl) <= 0 && Level.ERROR.compareTo(lvl) == 0)
        {
            error(lineToLog);
        }
        else if(logRange.compareTo(lvl) <= 0 && Level.FATAL.compareTo(lvl) == 0)
        {
            fatal(lineToLog);
        }

        out.flush();
    }
}
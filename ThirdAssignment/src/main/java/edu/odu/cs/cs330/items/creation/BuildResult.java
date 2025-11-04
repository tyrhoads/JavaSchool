package edu.odu.cs.cs330.items.creation;

import edu.odu.cs.cs330.util.EventLog;
import edu.odu.cs.cs330.util.Pair;


/**
 * Result of building an object with an EventLog.
 *
 * @param <E> This specifies the Event Enum
 * @param <T> This is the type of object that was built
 */
public class BuildResult<E extends Enum, T> extends Pair<EventLog<E>, T>
{
    public BuildResult(final EventLog<E> log, final T obj)
    {
        super(log, obj);
    }

    public EventLog<E> eventLog()
    {
        return super.first;
    }

    public T object()
    {
        return super.second;
    }
}


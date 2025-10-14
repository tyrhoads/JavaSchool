package edu.odu.cs.cs330.util;

import java.util.List;
import java.util.ArrayList;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.joining;


/**
 * Record all of Inventory build events and the corresponding items
 */
@SuppressWarnings({
    "PMD.BeanMembersShouldSerialize",
})
public class EventLog<E extends Enum>
{
    /**
     * A record of all attempted operations.
     */
    private final List<Entry<E>> updateRecord;

    /**
     * A single entry in the log. It consists of an Event and a short description.
     */
    public class Entry<E> extends Pair<E, String>
    {
        public Entry(final E event, final String description)
        {
            super(event, description);
        }

        public E event()
        {
            return super.first;
        }

        public String description()
        {
            return super.second;
        }

        @SuppressWarnings({
            "PMD.LawOfDemeter"
        })
        public String render(final BiFunction<E, String, String> renderer)
        {
            return renderer.apply(this.event(), this.description());
        }
    }

    public EventLog()
    {
        this.updateRecord = new ArrayList<>();
    }

    public void record(final E event, final String description)
    {
        updateRecord.add(new Entry(event, description));
    }

    public List<Entry<E>> getRecords()
    {
        return this.updateRecord;
    }

    public String renderRecords(final BiFunction<E, String, String> renderer)
    {
        return this.getRecords()
            .stream()
            .map(entry -> entry.render(renderer))
            .collect(
                joining(System.lineSeparator(), "", System.lineSeparator())
            );
    }
}

package edu.odu.cs.cs330.util;

@SuppressWarnings("PMD")
public class Pair<T, U>
{
    public T first;
    public U second;

    public Pair(T first, U second)
    {
        this.first = first;
        this.second = second;
    }
}

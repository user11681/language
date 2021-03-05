package user11681.language;

import java.util.ListIterator;
import java.util.stream.Collectors;

public class StringIterator implements ListIterator<Character>, Iterable<Character> {
    protected final ListIterator<Character> iterator;

    public StringIterator(String string) {
        this.iterator = string.chars().mapToObj((int codePoint) -> (char) codePoint).collect(Collectors.toList()).listIterator();
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public Character next() {
        return this.iterator.next();
    }

    @Override
    public boolean hasPrevious() {
        return this.iterator.hasPrevious();
    }

    @Override
    public Character previous() {
        return this.iterator.previous();
    }

    @Override
    public int nextIndex() {
        return this.iterator.nextIndex();
    }

    @Override
    public int previousIndex() {
        return this.iterator.previousIndex();
    }

    @Override
    public void remove() {
        this.iterator.remove();
    }

    @Override
    public void set(Character character) {
        this.iterator.set(character);
    }

    @Override
    public void add(Character character) {
        this.iterator.add(character);
    }

    @Override
    public ListIterator<Character> iterator() {
        return this;
    }
}

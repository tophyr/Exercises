package com.artbeatte.exercises.memorygame;

/**
 * Created by csarbora on 1/22/16.
 */
public final class Card {
    private final int mNumber;

    public Card(int mNumber) {
        this.mNumber = mNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return mNumber == card.mNumber;
    }

    @Override
    public int hashCode() {
        return mNumber % 8;
    }
}

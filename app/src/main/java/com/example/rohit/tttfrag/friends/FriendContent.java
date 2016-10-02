package com.example.rohit.tttfrag.friends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class FriendContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<FriendData> ITEMS = new ArrayList<FriendData>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, FriendData> ITEM_MAP = new HashMap<String, FriendData>();

    private static int COUNT = 3;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(FriendData item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static FriendData createDummyItem(int position) {
        return new FriendData(String.valueOf(position), "Item " + position, makeDetails(position));
    }
    public static FriendData createDummyItem2(String src) {
        return new FriendData(String.valueOf(++COUNT), src, makeDetails(COUNT));
    }
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class FriendData {
        public final String id;
        public final String content;
        public final String details;

        public FriendData(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}

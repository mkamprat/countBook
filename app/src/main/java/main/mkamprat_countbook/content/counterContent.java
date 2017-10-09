package main.mkamprat_countbook.content;

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
public class counterContent {

    /**
     * An array of counter items.
     */
    public static final List<counterObject> ITEMS = new ArrayList<counterObject>();

    /**
     * A map of counter items, by ID.
     */
    public static final Map<String, counterObject> ITEM_MAP = new HashMap<String, counterObject>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createCounter(i, "hello"));
        }
    }

    private static void addItem(counterObject item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static counterObject createCounter(int position, String counterName) {
        int startingValue = 1;
        int currentValue = 2;
        String counterComment = "hello2";

        return new counterObject(String.valueOf(position), counterName,
                startingValue, currentValue, counterComment, makeDetails(counterComment));
    }

    private static String makeDetails(String counterName) {
        StringBuilder builder = new StringBuilder();
        builder.append("Counter Name: ").append(counterName);
        return builder.toString();
    }
    /**
     * A dummy item representing a piece of content.
     */
    public static class counterObject {
        public final String id;
        public String counterName;
        public int startingValue;
        public int currentValue;
        public String counterComment;
        public String details;

        public counterObject(String id, String counterName, int startingValue, int currentValue, String counterComment, String details) {
            this.id = id;
            this.counterName = counterName;
            this.startingValue = startingValue;
            this.currentValue = currentValue;
            this.counterComment = counterComment;
            this.details = details;
        }


        public int counterMath(int binaryValue){
            if(binaryValue == 1) {
               this.currentValue += 1;
            } else if(binaryValue == -1){
                if (this.currentValue == 0) {
                }
               this.currentValue -= 1;
            }
            return this.currentValue;
        }


        @Override
        public String toString() {
            return counterName;/*
            return startingValue;
            return currentValue;
            return counterComment;*/
        }
    }
}

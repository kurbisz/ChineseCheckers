package client;

public enum ClientType {

    OBSERVER {
        @Override
        public String getName() {
            return "Obserwator";
        }
    },
    PLAYER {
        @Override
        public String getName() {
            return "Uczestnik";
        }
    };

    public String getName() {
        return null;
    }

    public static ClientType getByString(String str) {
        if(str.equals(OBSERVER.getName())) return OBSERVER;
        return PLAYER;
    }

}

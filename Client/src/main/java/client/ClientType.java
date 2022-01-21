package client;

public enum ClientType {

    VIEWER {
        @Override
        public String getName() {
            return "Viewer";
        }
    },
    PLAYER {
        @Override
        public String getName() {
            return "Player";
        }
    };

    public String getName() {
        return null;
    }

    public static ClientType getByString(String str) {
        if(str.equals(VIEWER.getName())) return VIEWER;
        return PLAYER;
    }

}

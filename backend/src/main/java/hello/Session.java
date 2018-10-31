package hello;

public class Session {
    private String name;
    private String game;

    //private final long id;
    //private final String content;

    public Session(String name, String game) {
        this.name = name;
        this.game = game;
    }

    /*public Session(long id, String content) {
        this.id = id;
        this.content = content;
    }*/

    /*public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }*/

    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    @Override
    public String toString() {
        return "Session: name=" + name + " game=" + game;
    }
}

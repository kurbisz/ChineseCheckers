package sternhalma.board;

public class FactoryProducer {
    private static FactoryProducer instance=new FactoryProducer();
    public static FactoryProducer getInstance()
    {
        return instance;
    }
    private final static String classic = "classic";
    public RulesFactory getFactory(String rules)
    {
        if (classic.equalsIgnoreCase(rules)) {
            return new ClassicRulesFactory();
        }
        return null;
    }
}
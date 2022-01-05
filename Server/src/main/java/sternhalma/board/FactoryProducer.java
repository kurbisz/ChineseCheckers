package sternhalma.board;

import java.util.Locale;

public class FactoryProducer {
    private static FactoryProducer instance=new FactoryProducer();
    public static FactoryProducer getInstance()
    {
        return instance;
    }
    public RulesFactory getFactory(String rules)
    {
        if ( rules.equalsIgnoreCase("classic")) {
            return new ClassicRulesFactory();
        }
        return null;
    }
}
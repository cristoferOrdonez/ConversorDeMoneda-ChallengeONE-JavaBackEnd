public enum Option {

    USD_TO_ARS(1),
    ARS_TO_USD(2),
    USD_TO_BRL(3),
    BRL_TO_USD(4),
    USD_TO_COP(5),
    COP_TO_USD(6),
    EXIT(7),
    UNDEFINED(-1);

    private final int value;

    Option(int value){
        this.value = value;
    }

    public static Option getOption(int value){

        for (Option option : Option.values()){
            if (value == option.value){
                return option;
            }
        }

        return UNDEFINED;
    }

}

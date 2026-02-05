public record ExchangeRateAPIResponse(
        String baseCode, String targetCode,
        double conversionRate, double conversionResult
) {
    public ExchangeRateAPIResponse(
            String baseCode, String targetCode,
            String conversionRate, String conversionResult
    ){
        this(
                baseCode,targetCode,
                Double.parseDouble(conversionRate),Double.parseDouble(conversionResult)
        );
    }
}

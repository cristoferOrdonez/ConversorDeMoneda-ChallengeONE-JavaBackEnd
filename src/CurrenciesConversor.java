import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrenciesConversor {

    private static final String menu = """
            *******************************************************
            Sea bienvenido/a al Conversor de Moneda =]
                            
            1) Dólar =>> Peso argentino
            2) Peso argentino =>> Dólar
            3) Dólar =>> Real brasileño
            4) Real brasileño =>> Dólar
            5) Dólar =>> Peso colombiano
            6) Peso colombiano =>> Dólar
            7) Salir
            Elija una opción válida:
            *******************************************************
            """;

    private static final String noValidOptionMessage = "No ha seleccionado una opción valida";
    private static final String noValidAmountMessage = "No ha colocado un monto valido";

    static void main() {
        startCurrenciesConversor();
    }

    private static void startCurrenciesConversor(){

        List<Currency> currencies = new ArrayList<>(4);

        currencies.add(new Currency("USD", "Dólar"));
        currencies.add(new Currency("ARS", "Peso argentino"));
        currencies.add(new Currency("BRL", "Real Brasileño"));
        currencies.add(new Currency("COP", "Peso colombiano"));

        Scanner scanner = new Scanner(System.in);

        int input, amount;
        Option selectedOption = Option.UNDEFINED;
        Currency baseCurrency, targetCurrency;

        do {

            System.out.println(menu);

            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e){
                System.out.println(noValidOptionMessage);
                continue;
            }
            selectedOption = Option.getOption(input);

            System.out.println("Ingresa el valor que deseas convertir:");
            try {
                amount = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(noValidAmountMessage);
                continue;
            }

            if (amount < 0){
                System.out.println(noValidAmountMessage);
                continue;
            }

            switch (selectedOption){
                case USD_TO_ARS:
                case USD_TO_COP:
                case USD_TO_BRL:
                    baseCurrency = currencies.getFirst();
                    targetCurrency = currencies.get((input + 1)/2);
                    printConversionResult(
                            baseCurrency.getCode(),
                            targetCurrency.getCode(),
                            amount,
                            baseCurrency.covertAmount(targetCurrency, amount)
                    );

                    break;
                case ARS_TO_USD:
                case BRL_TO_USD:
                case COP_TO_USD:
                    baseCurrency = currencies.get(input/2);
                    targetCurrency = currencies.getFirst();
                    printConversionResult(
                            baseCurrency.getCode(),
                            targetCurrency.getCode(),
                            amount,
                            baseCurrency.covertAmount(targetCurrency, amount)
                    );
                    break;
                default:
                    System.out.println(noValidOptionMessage);
                    break;
            }


        } while (selectedOption != Option.EXIT);

    }

    private static void printConversionResult(
            String baseCode, String targetCode,
            double amount, double conversionResult
    ){

        System.out.printf("El valor %.1f [%s] corresponde al valor final de =>>> %.2f [%s]\n",amount, baseCode, conversionResult, targetCode);

    }
}

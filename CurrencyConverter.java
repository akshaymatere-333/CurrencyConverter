//TASK 3- Develop a currency converter :
//Create a currency converter application that converts currencies from one currency to another based on current exchange rates.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverter {

    public static void main(String[] args) {
        try {
            // Replace 'YOUR_API_KEY' with your actual API key from ExchangeRates-API
            String apiKey = "YOUR_API_KEY";
            String baseUrl = "https://open.er-api.com/v6/latest/";

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the amount: ");
            double amount = Double.parseDouble(reader.readLine());

            System.out.print("Enter the source currency (e.g., USD): ");
            String sourceCurrency = reader.readLine().toUpperCase();

            System.out.print("Enter the target currency (e.g., EUR): ");
            String targetCurrency = reader.readLine().toUpperCase();

            // Fetch the exchange rates from the API
            String urlStr = baseUrl + sourceCurrency + "?apikey=" + apiKey;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader apiResponseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = apiResponseReader.readLine()) != null) {
                response.append(line);
            }
            apiResponseReader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            double conversionRate = jsonResponse.getJSONObject("rates").getDouble(targetCurrency);

            double convertedAmount = amount * conversionRate;

            System.out.println(amount + " " + sourceCurrency + " is equal to " + convertedAmount + " " + targetCurrency);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching data from the API.");
        }
    }
}

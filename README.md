# REST-API-CLIENT

*COMPANY* : CODETECH IT SOLUTIONS

*NAME* : RUTIKSHA KAMLESH SANDHA

*INTERN ID* : CT04DK903

*DOMAIN* : JAVA PROGRAMMING

*DURATION* : 4 WEEKS

*MENTOR* : NEELA SANTOSH

*DESCRIPTION* : 

This is the java program application which is designed to allow the user to fetch and view the current weather condition of a city or country by just entering the name of it. This program provides the real time weather data to the user by parsing the JSON responses .
The GUI is built using the java Swing toolkit to make it interactive by creating a user friendly interface . The GUI consists of a JFrame, JLabel , a JTextField to take input from the user , and a JButton by clicking on which the real time weather will be displayed. To make it more interesting the Frame’s background color also changes according to the weather condition . The weather conditions are provided by the API, which are translated by the program into relevant labels like sunny, rainy, etc.  An Image relative to the weather is also displayed at the center of the frame. It dynamically displays the city or country name , temperature , feels like temperature ,  wind speed , humidity , weather type (e.g sunny , rainy , etc.) and date and time of the city or country . The image icons of search , humidity, wind speed and weather types are also included in the GUI. 
This java program interface integrate 2 REST API’s : one for Geocoding and one for WeatherForcasting. When the user enters the city or country name ,  the application uses the Open meteo Geocoding Api to fetch the location of the city/ country. Then the location coordinate of the city/ country are used to call the open Meteo Forecast API ,  which returns the data in JSON Format . The program parses the returned JSON responses into relevant data by using the JSON.simple library .
The Network calls are made by the java’s HttpURLConnection . The input streams are managed by the Scanner and the BufferedReader to read the responses received from the API .
The structure of the program is in such a way that is separates the API logic and GUI logic to avoid any confusion and to provide a clear view of the program. The JSON responses are parsed through the JSONParser class . Error handling is also implemented to catch the failed API calls , incorrect user input , etc. to ensure the stability of the program during the connectivity failures or issues . This project demonstrates the effective use of API’s and also integrates the real- world weather data .  This java program application gives an example for learning and understanding the HTTP connections , JSON processing  and GUI designing in JAVA . 
In conclusion the code provides a solution for building an interactive weather application that consumes the public REST API’s and displays the real time weather data of any city or country that the user wishes to fetch and view .This type of application can be used by different domains to provide real time weather condition updates and forcast it to the users . 

# OUTPUT

![Image](https://github.com/user-attachments/assets/6aa64b00-4911-495e-9d10-900c2e2a3c02)
